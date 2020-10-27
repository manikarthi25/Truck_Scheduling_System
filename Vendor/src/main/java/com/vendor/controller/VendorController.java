package com.vendor.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vendor.dto.VendorDTO;
import com.vendor.exception.VendorException;
import com.vendor.service.IVendorService;

@RestController
@RequestMapping("/vendor")
public class VendorController {

	@Autowired
	private IVendorService vendorService;

	@PostMapping(path = "/add", produces = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON }, consumes = {
			MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ResponseEntity<VendorDTO> addVendor(@RequestBody VendorDTO vendorDTO) throws VendorException {

		VendorDTO vendor = vendorService.addVendor(vendorDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(vendor);

	}

	@GetMapping(path = "/get/all", produces = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON }, consumes = {
			MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ResponseEntity<List<VendorDTO>> getAllVendors() {
		List<VendorDTO> vendorDTOList = vendorService.getAllVendors();
		if (!CollectionUtils.isEmpty(vendorDTOList)) {
			return ResponseEntity.status(HttpStatus.OK).body(vendorDTOList);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(vendorDTOList);
		}
	}

	@GetMapping(path = "/search/{vendorId}", produces = { MediaType.APPLICATION_XML,
			MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ResponseEntity<VendorDTO> searchVendorById(@PathVariable("vendorId") Integer vendorId) {
		VendorDTO vendorDTO = vendorService.searchVendorById(vendorId);
		if (!ObjectUtils.isEmpty(vendorDTO)) {
			return ResponseEntity.status(HttpStatus.OK).body(vendorDTO);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(vendorDTO);
		}
	}

	@PutMapping(path = "/update", produces = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON }, consumes = {
			MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ResponseEntity<VendorDTO> updateVendor(@RequestBody VendorDTO vendorDTO) throws VendorException {

		VendorDTO vendor = vendorService.updateVendor(vendorDTO);
		if (!ObjectUtils.isEmpty(vendor)) {
			return ResponseEntity.status(HttpStatus.OK).body(vendor);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(vendor);
		}

	}

	@DeleteMapping(path = "/delete/all", produces = { MediaType.APPLICATION_XML,
			MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ResponseEntity<List<VendorDTO>> deleteAllVendors() {
		List<VendorDTO> vendorDTOList = vendorService.deleteAllVendors();
		if (!CollectionUtils.isEmpty(vendorDTOList)) {
			return ResponseEntity.status(HttpStatus.OK).body(vendorDTOList);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(vendorDTOList);
		}
	}

	@DeleteMapping(path = "/delete/{vendorId}", produces = { MediaType.APPLICATION_XML,
			MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ResponseEntity<VendorDTO> deleteVendorById(@PathVariable("vendorId") Integer vendorId) {
		VendorDTO vendorDTO = vendorService.deleteVendorById(vendorId);
		if (!ObjectUtils.isEmpty(vendorDTO)) {
			return ResponseEntity.status(HttpStatus.OK).body(vendorDTO);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(vendorDTO);
		}
	}

}
