package com.appointment.helper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.appointment.dto.AppointmentDTO;
import com.appointment.dto.AppointmentPoDTO;
import com.appointment.dto.DCSlotDTO;
import com.appointment.dto.DistributedCenterDTO;
import com.appointment.dto.TruckDTO;
import com.appointment.entity.AppointmentEO;
import com.appointment.entity.AppointmentPoEO;
import com.appointment.entity.DCSlotEO;
import com.appointment.entity.DistributedCenterEO;
import com.appointment.entity.TruckEO;

public class MapperUtils {

	ModelMapper modelMapper = new ModelMapper();

	public AppointmentDTO mapToAppointmentDTO(AppointmentEO appointmentEO) {
		AppointmentDTO appointmentDTO = modelMapper.map(appointmentEO, AppointmentDTO.class);
		appointmentDTO.setDistributedCenterDTO(mapToDCDTO(appointmentEO.getDistributedCenterEO()));
		appointmentDTO.setDcSlotDTO(mapToDCSlotDTO(appointmentEO.getDcSlotEO()));
		appointmentDTO.setTruckDTO(mapToTruckDTO(appointmentEO.getTruckEO()));
		return appointmentDTO;
	}

	public AppointmentEO mapToAppointmentEO(AppointmentDTO appointmentDTO) {
		AppointmentEO appointmentEO = modelMapper.map(appointmentDTO, AppointmentEO.class);
		appointmentEO.setDistributedCenterEO(mapToDCEO(appointmentDTO.getDistributedCenterDTO()));
		appointmentEO.setDcSlotEO(mapToDCSlotEO(appointmentDTO.getDcSlotDTO()));
		appointmentEO.setTruckEO(mapToTruckEO(appointmentDTO.getTruckDTO()));
		return appointmentEO;
	}

	public DCSlotDTO mapToDCSlotDTO(DCSlotEO dcSlotEO) {
		return modelMapper.map(dcSlotEO, DCSlotDTO.class);
	}

	public DCSlotEO mapToDCSlotEO(DCSlotDTO dcSlotDTO) {
		return modelMapper.map(dcSlotDTO, DCSlotEO.class);
	}

	public TruckDTO mapToTruckDTO(TruckEO truckEO) {
		return modelMapper.map(truckEO, TruckDTO.class);
	}

	public TruckEO mapToTruckEO(TruckDTO truckDTO) {
		return modelMapper.map(truckDTO, TruckEO.class);
	}

	public DistributedCenterDTO mapToDCDTO(DistributedCenterEO distributedCenterEO) {
		return modelMapper.map(distributedCenterEO, DistributedCenterDTO.class);
	}

	public DistributedCenterEO mapToDCEO(DistributedCenterDTO distributedCenterDTO) {
		return modelMapper.map(distributedCenterDTO, DistributedCenterEO.class);
	}

	public AppointmentPoDTO mapToApptPoDTO(AppointmentPoEO appointmentPoEO) {
		return modelMapper.map(appointmentPoEO, AppointmentPoDTO.class);
	}

	public AppointmentPoEO mapToApptPoEO(AppointmentPoDTO appointmentPoDTO) {
		return modelMapper.map(appointmentPoDTO, AppointmentPoEO.class);
	}

	public List<AppointmentPoDTO> getApptPoDTO(List<AppointmentPoEO> appointmentPoEOList) {
		List<AppointmentPoDTO> appointmentPoDTOList = new ArrayList<>();
		for (AppointmentPoEO appointmentPoEO : appointmentPoEOList) {
			appointmentPoDTOList.add(mapToApptPoDTO(appointmentPoEO));
		}
		return appointmentPoDTOList;
	}
}
