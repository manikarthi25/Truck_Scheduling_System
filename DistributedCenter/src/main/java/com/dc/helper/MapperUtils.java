package com.dc.helper;

import org.modelmapper.ModelMapper;

import com.dc.dto.DistributedCenterDTO;
import com.dc.dto.DistributedCenterTypeDTO;
import com.dc.entity.DistributedCenterEO;
import com.dc.entity.DistributedCenterTypeEO;

public class MapperUtils {

	ModelMapper modelMapper = new ModelMapper();

	public DistributedCenterDTO mapToDTO(DistributedCenterEO dcEO, DistributedCenterTypeEO dcTypeEO) {

		DistributedCenterTypeDTO dcTypeDTO = modelMapper.map(dcTypeEO, DistributedCenterTypeDTO.class);
		DistributedCenterDTO dcDTO = modelMapper.map(dcEO, DistributedCenterDTO.class);
		dcDTO.setDistributedCenterTypeDTO(dcTypeDTO);
		return dcDTO;

	}

	public DistributedCenterEO mapToEO(DistributedCenterDTO dcDTO) {

		DistributedCenterEO distributedCenterEO = modelMapper.map(dcDTO, DistributedCenterEO.class);
		return distributedCenterEO;

	}

}
