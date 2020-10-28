package com.appointment.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import com.appointment.AppointmentException;
import com.appointment.dto.AppointmentDTO;
import com.appointment.dto.AppointmentPoDTO;
import com.appointment.dto.DCSlotDTO;
import com.appointment.entity.AppointmentEO;
import com.appointment.entity.AppointmentPoEO;
import com.appointment.entity.PurchaseOrderEO;
import com.appointment.helper.MapperUtils;
import com.appointment.model.DownstreamMessage;
import com.appointment.repository.AppointmentPoRepo;
import com.appointment.repository.AppointmentRepo;
import com.appointment.repository.DCSlotRepo;
import com.appointment.repository.PurchaseOrderRepo;
import com.appointment.service.IAppointmentService;

@Service
public class AppointmentService implements IAppointmentService {

	@Autowired
	private PurchaseOrderRepo purchaseOrderRepo;

	@Autowired
	private AppointmentRepo appointmentRepo;

	@Autowired
	private AppointmentPoRepo appointmentPoRepo;

	@Autowired
	private DCSlotRepo dcSlotRepo;

	@Autowired
	RestTemplate restTemplate;

	MapperUtils mapperUtils = new MapperUtils();

	@Override
	public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) throws AppointmentException {

		int usedTruckCount = appointmentRepo.getCountBySlotId(appointmentDTO.getDcSlotDTO().getDcSlotId());
		int availableSlotCount = availableSlotCount(appointmentDTO.getDcSlotDTO().getDcSlotId());
		boolean poAvailaleStatus = isPOAvailable(appointmentDTO.getAppointmentPoDTOList());
		if (poAvailaleStatus) {
			if (usedTruckCount <= availableSlotCount) {
				AppointmentEO appointmentEO = mapperUtils.mapToAppointmentEO(appointmentDTO);

				AppointmentEO responseEO = appointmentRepo.save(appointmentEO);

				List<String> availablePOs = new ArrayList<>();
				for (AppointmentPoDTO appointmentPoDTO : appointmentDTO.getAppointmentPoDTOList()) {
					appointmentPoRepo.save(getAppointmentPoEO(appointmentPoDTO));
					availablePOs.add(appointmentPoDTO.getPoNumber());
				}

				DownstreamMessage downstreamMessage = new DownstreamMessage();
				downstreamMessage.setAppointmentId(responseEO.getApptId());
				downstreamMessage.setTruckNumber(appointmentDTO.getTruckDTO().getTruckNumber());
				downstreamMessage.setDcNumber(appointmentDTO.getDistributedCenterDTO().getDcNumber());
				downstreamMessage.setTimeSlot(appointmentDTO.getDcSlotDTO().getDcTimeSlot());
				downstreamMessage.setPos(availablePOs);

				sendDownstreamMessage(downstreamMessage);
				return mapperUtils.mapToAppointmentDTO(responseEO);
			} else {
				throw new AppointmentException("Max truck count reached for the slot");
			}
		} else {
			throw new AppointmentException("PO is not available to create appointment");
		}
	}

	@Override
	public AppointmentDTO searchAppointmentById(Long apptId) throws AppointmentException {
		AppointmentDTO appointmentDTO = appointmentRepo.findById(apptId).map(appointment -> {
			return mapperUtils.mapToAppointmentDTO(appointment);
		}).orElseThrow(() -> new AppointmentException("No Slots Found"));

		List<AppointmentPoEO> appointmentPoEOList = appointmentPoRepo.getAppointmentPoByApptId(apptId);
		if (!CollectionUtils.isEmpty(appointmentPoEOList)) {
			appointmentDTO.setAppointmentPoDTOList(mapperUtils.getApptPoDTO(appointmentPoEOList));
		}
		return appointmentDTO;
	}

	@Override
	public void deleteAppointmentById(Long apptId) throws AppointmentException {
		Optional<AppointmentEO> appointmentEO = appointmentRepo.findById(apptId);
		if (appointmentEO.isPresent()) {
			List<AppointmentPoEO> appointmentPos = appointmentPoRepo.getAppointmentPoByApptId(apptId);
			for (AppointmentPoEO appointmentPo : appointmentPos) {
				appointmentPoRepo.deleteById(appointmentPo.getApptPoId());
			}

			appointmentRepo.delete(appointmentEO.get());
		} else {
			throw new AppointmentException("No Appointment Found");
		}
	}

	@Override
	@Transactional
	public AppointmentDTO update(AppointmentDTO appointmentDTO) throws AppointmentException {
		int usedTruckCount = appointmentRepo.getCountBySlotId(appointmentDTO.getDcSlotDTO().getDcSlotId());
		int availableSlotCount = availableSlotCount(appointmentDTO.getDcSlotDTO().getDcSlotId());

		AppointmentEO responseAppointmentEO = null;
		boolean poAvailableStatus = isPOAvailable(appointmentDTO.getAppointmentPoDTOList());
		if (poAvailableStatus) {
			if (usedTruckCount <= availableSlotCount) {
				if (appointmentRepo.findById(appointmentDTO.getApptId()).isPresent()) {
					responseAppointmentEO = appointmentRepo
							.saveAndFlush(mapperUtils.mapToAppointmentEO(appointmentDTO));

					List<AppointmentPoEO> appointmentPoEOList = appointmentPoRepo
							.getAppointmentPoByApptId(appointmentDTO.getApptId());
					for (AppointmentPoEO appointmentPoEO : appointmentPoEOList) {
						appointmentPoRepo.deleteById(appointmentPoEO.getApptPoId());
					}

					for (AppointmentPoDTO appointmentPoDTO : appointmentDTO.getAppointmentPoDTOList()) {
						appointmentPoRepo.save(getAppointmentPoEO(appointmentPoDTO));
					}

				} else {
					throw new AppointmentException("No Appointments found to update");
				}
			} else {
				throw new AppointmentException("Slots were reached with max truck count");
			}
		} else {
			throw new AppointmentException("One of the po is not available to update the appointment");
		}

		return (!ObjectUtils.isEmpty(responseAppointmentEO)) ? mapperUtils.mapToAppointmentDTO(responseAppointmentEO)
				: null;
	}

	private int availableSlotCount(Integer dcSlotId) {
		int slotCount = 0;
		try {
			slotCount = getDCSlots(dcSlotId).getMaxTrucks();
		} catch (AppointmentException e) {
			e.printStackTrace();
		}
		return slotCount;
	}

	private Boolean isPOAvailable(List<AppointmentPoDTO> appointmentPoDTOList) {
		for (AppointmentPoDTO appointmentPoDTO : appointmentPoDTOList) {
			List<PurchaseOrderEO> purchaseOrderEOList = purchaseOrderRepo
					.findByPoNumber(appointmentPoDTO.getPoNumber());
			if (CollectionUtils.isEmpty(purchaseOrderEOList)) {
				return false;
			}
		}
		return true;
	}

	private DCSlotDTO getDCSlots(Integer dcSlotId) throws AppointmentException {
		return dcSlotRepo.findById(dcSlotId).map(dcSlots -> {
			return mapperUtils.mapToDCSlotDTO(dcSlots);
		}).orElseThrow(() -> new AppointmentException("No Slots Found"));
	}

	private void sendDownstreamMessage(DownstreamMessage downstreamMessage) {
		String url = "http://downstream-ms/downstream/post/appointment";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<DownstreamMessage> requestEntity = new HttpEntity<>(downstreamMessage, headers);

		restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
	}

	private AppointmentPoEO getAppointmentPoEO(AppointmentPoDTO appointmentPoDTO) {

		AppointmentPoEO appointmentPoEO = new AppointmentPoEO();

		appointmentPoEO.setApptPoId(appointmentPoDTO.getApptPoId());
		appointmentPoEO.setPoNumber(appointmentPoDTO.getPoNumber());
		appointmentPoEO.setCreatedBy(appointmentPoDTO.getCreatedBy());
		appointmentPoEO.setCreatedTS(appointmentPoDTO.getCreatedTS());
		appointmentPoEO.setLastUpdatedBy(appointmentPoDTO.getLastUpdatedBy());
		appointmentPoEO.setLastUpdatedTS(appointmentPoDTO.getLastUpdatedTS());

		return appointmentPoEO;
	}

}