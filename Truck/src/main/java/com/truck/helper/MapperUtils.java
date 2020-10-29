package com.truck.helper;

import org.modelmapper.ModelMapper;

import com.truck.dto.TruckDTO;
import com.truck.dto.TruckTypeDTO;
import com.truck.entity.TruckEO;
import com.truck.entity.TruckTypeEO;

public class MapperUtils {

	ModelMapper modelMapper = new ModelMapper();

	public TruckDTO mapToTruckDTO(TruckEO truckEO) {

		return modelMapper.map(truckEO, TruckDTO.class);

	}

	public TruckEO mapToTruckEO(TruckDTO truckDTO) {

		return modelMapper.map(truckDTO, TruckEO.class);

	}

	public TruckTypeDTO mapToTruckTypeDTO(TruckTypeEO truckTypeEO) {

		return modelMapper.map(truckTypeEO, TruckTypeDTO.class);

	}

}
