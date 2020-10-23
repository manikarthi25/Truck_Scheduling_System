package com.vendor.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.vendor.dto.VendorDTO;
import com.vendor.entity.VendorEO;
import com.vendor.exception.VendorException;
import com.vendor.helper.MapperUtils;
import com.vendor.repository.VendorRepo;
import com.vendor.service.IVendorService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VendorService implements IVendorService {

	@Autowired
	private VendorRepo vendorRepo;

	private MapperUtils mapperUtils = new MapperUtils();

	@Override
	public VendorDTO addVendor(VendorDTO vendorDTO) throws VendorException {
		List<VendorEO> vendorEOList = vendorRepo.findByVendorNumber(vendorDTO.getVendorNumber());
		if (CollectionUtils.isEmpty(vendorEOList)) {
			try {
				VendorEO vendorEO = mapperUtils.mapToVendorEO(vendorDTO);
				return mapperUtils.mapToVendorDTO(vendorRepo.save(vendorEO));

			} catch (Exception ex) {
				log.info("VendorService : addVendor : vendor :{} Exception : {}", vendorDTO, ex);
				throw new VendorException("Excetion in during add vendor", ex);
			}
		} else {
			throw new VendorException("This vendor is already available");
		}

	}

	@Override
	public List<VendorDTO> getAllVendors() {
		List<VendorEO> vendorEOList = vendorRepo.findAll();
		List<VendorDTO> vendorDTOList = new ArrayList<>();
		for (VendorEO vendorEO : vendorEOList) {
			vendorDTOList.add(mapperUtils.mapToVendorDTO(vendorEO));
		}
		return vendorDTOList;
	}

	@Override
	public VendorDTO searchVendorById(Integer vendorId) {
		return getVendor(vendorRepo.findById(vendorId));
	}

	@Override
	public VendorDTO deleteVendorById(Integer vendorId) {
		Optional<VendorEO> dcOptionalEO = vendorRepo.findById(vendorId);
		if (dcOptionalEO.isPresent()) {
			vendorRepo.deleteById(vendorId);
			return getVendor(dcOptionalEO);
		} else {
			return null;
		}
	}

	@Override
	public List<VendorDTO> deleteAllVendors() {
		vendorRepo.deleteAll();
		return getAllVendors();
	}

	@Override
	public VendorDTO updateVendor(VendorDTO vendorDTO) throws VendorException {

		try {
			Optional<VendorEO> dcOptionalEO = vendorRepo.findById(vendorDTO.getVendorId());
			if (dcOptionalEO.isPresent()) {
				VendorEO vendorEO = mapperUtils.mapToVendorEO(vendorDTO);
				return mapperUtils.mapToVendorDTO(vendorRepo.saveAndFlush(vendorEO));
			} else {
				return null;
			}
		} catch (Exception ex) {
			log.info("DCSLotService :  updateDCSlot : DCSlot  :{} Exception : {}", vendorDTO, ex);
			throw new VendorException("Excetion in during update DC Slot", ex);
		}

	}

	private VendorDTO getVendor(Optional<VendorEO> dcOptionalEO) {
		if (dcOptionalEO.isPresent()) {
			return mapperUtils.mapToVendorDTO(dcOptionalEO.get());
		} else {
			return null;
		}
	}

}
