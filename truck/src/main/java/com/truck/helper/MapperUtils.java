package com.truck.helper;

import org.modelmapper.ModelMapper;

import com.truck.dto.TruckDTO;
import com.truck.dto.TruckTypeDTO;
import com.truck.entity.TruckEO;
import com.truck.entity.TruckTypeEO;

public class MapperUtils {

	ModelMapper modelMapper = new ModelMapper();

	public TruckDTO mapToDTO(TruckEO truckEO, TruckTypeEO truckTypeEO) {

		TruckTypeDTO truckTypeDTO = modelMapper.map(truckTypeEO, TruckTypeDTO.class);
		TruckDTO truckDTO = modelMapper.map(truckEO, TruckDTO.class);
		truckDTO.setTruckTypeDTO(truckTypeDTO);
		return truckDTO;

	}

	public TruckEO mapToEO(TruckDTO truckDTO) {

		TruckEO TruckEO = modelMapper.map(truckDTO, TruckEO.class);
		return TruckEO;

	}

}
