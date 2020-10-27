package com.appointment.helper;

import org.modelmapper.ModelMapper;

import com.appointment.dto.DCSlotDTO;
import com.appointment.entity.DCSlotEO;

public class MapperUtils {

	ModelMapper modelMapper = new ModelMapper();

	public DCSlotDTO mapTODCSlotDTO(DCSlotEO dcSlotEO) {
		return modelMapper.map(dcSlotEO, DCSlotDTO.class);
	}

	public DCSlotEO mapTODCSlotEO(DCSlotDTO dcSlotDTO) {
		return modelMapper.map(dcSlotDTO, DCSlotEO.class);
	}

}
