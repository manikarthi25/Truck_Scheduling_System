package com.dc.controller;

import java.util.List;

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

import com.dc.dto.DistributedCenterDTO;
import com.dc.exception.DistributedCenterException;
import com.dc.service.impl.DistributedCenterService;

@RestController
@RequestMapping("/dc")
public class DistributedCenterController {

	@Autowired
	private DistributedCenterService dcService;

	@PostMapping("/add")
	public ResponseEntity<DistributedCenterDTO> addDC(@RequestBody DistributedCenterDTO distributedCenterDTO)
			throws DistributedCenterException {

		DistributedCenterDTO dcDTO = dcService.addDC(distributedCenterDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(dcDTO);

	}

	@GetMapping("/get/all")
	public ResponseEntity<List<DistributedCenterDTO>> getAllDCs() {
		List<DistributedCenterDTO> dcList = dcService.getAllDCs();
		if (!CollectionUtils.isEmpty(dcList)) {
			return ResponseEntity.status(HttpStatus.OK).body(dcList);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(dcList);
		}
	}

	@GetMapping("/search/{dcId}")
	public ResponseEntity<DistributedCenterDTO> searchDCById(@PathVariable("dcId") Integer dcId) {
		DistributedCenterDTO dc = dcService.searchDCById(dcId);
		if (!ObjectUtils.isEmpty(dc)) {
			return ResponseEntity.status(HttpStatus.OK).body(dc);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(dc);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<DistributedCenterDTO> updateDC(@RequestBody DistributedCenterDTO dcDTO) throws DistributedCenterException {

		DistributedCenterDTO dc = dcService.updateDC(dcDTO);
		if (!ObjectUtils.isEmpty(dc)) {
			return ResponseEntity.status(HttpStatus.OK).body(dc);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(dc);
		}

	}

	@DeleteMapping("/delete/all")
	public ResponseEntity<List<DistributedCenterDTO>> deleteAllDC() {
		List<DistributedCenterDTO> dcList = dcService.deleteAllDC();
		if (!CollectionUtils.isEmpty(dcList)) {
			return ResponseEntity.status(HttpStatus.OK).body(dcList);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(dcList);
		}
	}

	@DeleteMapping("/delete/{dcId}")
	public ResponseEntity<DistributedCenterDTO> deleteDCById(@PathVariable("dcId") Integer dcId) {
		DistributedCenterDTO dc = dcService.deleteDCById(dcId);
		if (!ObjectUtils.isEmpty(dc)) {
			return ResponseEntity.status(HttpStatus.OK).body(dc);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(dc);
		}
	}

}
