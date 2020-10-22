package com.dc.slot.helper;

import org.modelmapper.ModelMapper;

import com.dc.slot.dto.DCSlotDTO;
import com.dc.slot.dto.DistributedCenterDTO;
import com.dc.slot.entity.DCSlotEO;
import com.dc.slot.entity.DistributedCenterEO;

public class MapperUtils {

	ModelMapper modelMapper = new ModelMapper();

	public DCSlotDTO mapToDCSlotDTO(DCSlotEO dcSlotEO) {

		DCSlotDTO dcSlotDTO = modelMapper.map(dcSlotEO, DCSlotDTO.class);
		dcSlotDTO.setDistributedCenterDTO(mapToDCDTO(dcSlotEO.getDistributedCenterEO()));
		return dcSlotDTO;
	}

	public DCSlotEO mapToDCSlotEO(DCSlotDTO dcDTO) {

		DCSlotEO dcSlotEO = modelMapper.map(dcDTO, DCSlotEO.class);
		dcSlotEO.setDistributedCenterEO(mapToDCEO(dcDTO.getDistributedCenterDTO()));
		return dcSlotEO;
	}

	public DistributedCenterDTO mapToDCDTO(DistributedCenterEO dcEO) {

		return modelMapper.map(dcEO, DistributedCenterDTO.class);

	}

	public DistributedCenterEO mapToDCEO(DistributedCenterDTO dcDTO) {

		return modelMapper.map(dcDTO, DistributedCenterEO.class);

	}

}
