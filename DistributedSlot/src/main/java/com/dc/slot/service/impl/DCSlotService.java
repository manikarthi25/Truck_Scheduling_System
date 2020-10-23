package com.dc.slot.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.slot.dto.DCSlotDTO;
import com.dc.slot.entity.DCSlotEO;
import com.dc.slot.entity.DistributedCenterEO;
import com.dc.slot.exception.DCSlotException;
import com.dc.slot.helper.MapperUtils;
import com.dc.slot.repository.DCSlotRepo;
import com.dc.slot.repository.DistributedCenterRepo;
import com.dc.slot.service.IDCSlotService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DCSlotService implements IDCSlotService {

	@Autowired
	private DCSlotRepo dcSlotRepo;

	@Autowired
	private DistributedCenterRepo distributedCenterRepo;

	private MapperUtils mapperUtils = new MapperUtils();

	@Override
	public DCSlotDTO addDCSlot(DCSlotDTO dcSlotDTO) throws DCSlotException {
		try {
			DCSlotEO dcSlotEO = mapperUtils.mapToDCSlotEO(dcSlotDTO);
			return mapperUtils.mapToDCSlotDTO(dcSlotRepo.save(dcSlotEO));

		} catch (Exception ex) {
			log.info("DC Slot Service : addDCSlot : DC Slot :{} Exception : {}", dcSlotDTO, ex);
			throw new DCSlotException("Excetion in during add new DC Slot", ex);
		}
	}

	@Override
	public List<DCSlotDTO> getAllDCSlots() {
		List<DCSlotEO> dcSlotEOList = dcSlotRepo.findAll();
		List<DCSlotDTO> dcList = new ArrayList<>();
		for (DCSlotEO dcSlotEO : dcSlotEOList) {
			Optional<DistributedCenterEO> optionalDCEO = distributedCenterRepo
					.findById(dcSlotEO.getDistributedCenterEO().getDcId());
			if (optionalDCEO.isPresent()) {
				dcSlotEO.setDistributedCenterEO(optionalDCEO.get());
			}
			dcList.add(mapperUtils.mapToDCSlotDTO(dcSlotEO));
		}
		return dcList;
	}

	@Override
	public DCSlotDTO searchDCSlotById(Integer dcSlotId) {
		return getDCSlot(dcSlotRepo.findById(dcSlotId));
	}

	@Override
	public DCSlotDTO deleteDCSlotById(Integer dcSlotId) {
		Optional<DCSlotEO> dcOptionalEO = dcSlotRepo.findById(dcSlotId);
		if (dcOptionalEO.isPresent()) {
			dcSlotRepo.deleteById(dcSlotId);
			return getDCSlot(dcOptionalEO);
		} else {
			return null;
		}
	}

	@Override
	public List<DCSlotDTO> deleteAllDCSlots() {
		dcSlotRepo.deleteAll();
		return getAllDCSlots();
	}

	@Override
	public DCSlotDTO updateDCSlot(DCSlotDTO dcSlotDTO) throws DCSlotException {

		try {
			Optional<DCSlotEO> dcOptionalEO = dcSlotRepo.findById(dcSlotDTO.getDcSlotId());
			if (dcOptionalEO.isPresent()) {
				DCSlotEO dcSlotEO = mapperUtils.mapToDCSlotEO(dcSlotDTO);
				DCSlotEO slotEO = dcSlotRepo.saveAndFlush(dcSlotEO);
				return mapperUtils.mapToDCSlotDTO(slotEO);
			} else {
				return null;
			}
		} catch (Exception ex) {
			log.info("DCSLotService :  updateDCSlot : DCSlot  :{} Exception : {}", dcSlotDTO, ex);
			throw new DCSlotException("Excetion in during update DC Slot", ex);
		}

	}

	private DCSlotDTO getDCSlot(Optional<DCSlotEO> dcOptionalEO) {
		if (dcOptionalEO.isPresent()) {
			DCSlotEO dcSlotEO = dcOptionalEO.get();
			Optional<DistributedCenterEO> dcOptionalDCEO = distributedCenterRepo
					.findById(dcSlotEO.getDistributedCenterEO().getDcId());
			if (dcOptionalDCEO.isPresent()) {
				dcSlotEO.setDistributedCenterEO(dcOptionalDCEO.get());
			}
			return mapperUtils.mapToDCSlotDTO(dcSlotEO);
		} else {
			return null;
		} 
	}

}
