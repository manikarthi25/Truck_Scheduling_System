package com.vendor.service;

import java.util.List;

import com.vendor.dto.VendorDTO;
import com.vendor.exception.VendorException;

public interface IVendorService {

	VendorDTO addVendor(VendorDTO vendorDTO) throws VendorException;

	List<VendorDTO> getAllVendors();

	VendorDTO searchVendorById(Integer dcSlotId);

	VendorDTO deleteVendorById(Integer dcSlotId);

	List<VendorDTO> deleteAllVendors();

	VendorDTO updateVendor(VendorDTO vendorDTO) throws VendorException;

}
