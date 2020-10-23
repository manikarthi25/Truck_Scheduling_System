package com.vendor.helper;

import org.modelmapper.ModelMapper;

import com.vendor.dto.VendorDTO;
import com.vendor.entity.VendorEO;

public class MapperUtils {

	ModelMapper modelMapper = new ModelMapper();

	public VendorDTO mapToVendorDTO(VendorEO vendorEO) {

		return modelMapper.map(vendorEO, VendorDTO.class);

	}

	public VendorEO mapToVendorEO(VendorDTO vendorDTO) {

		return modelMapper.map(vendorDTO, VendorEO.class);
	}

}
