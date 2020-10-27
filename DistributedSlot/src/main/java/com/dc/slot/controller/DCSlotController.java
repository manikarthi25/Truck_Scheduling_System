package com.dc.slot.controller;

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

import com.dc.slot.dto.DCSlotDTO;
import com.dc.slot.exception.DCSlotException;
import com.dc.slot.service.IDCSlotService;

@RestController
@RequestMapping("/dcslot")
public class DCSlotController {

	@Autowired
	private IDCSlotService dcSlotService;

	@PostMapping(path = "/add", produces = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON }, consumes = {
			MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ResponseEntity<DCSlotDTO> addDCSlot(@RequestBody DCSlotDTO dcSlotDTO) throws DCSlotException {

		DCSlotDTO dcDTO = dcSlotService.addDCSlot(dcSlotDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(dcDTO);

	}

	@GetMapping(path = "/get/all", produces = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON }, consumes = {
			MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ResponseEntity<List<DCSlotDTO>> getAllDCSlots() {
		List<DCSlotDTO> dcSlotList = dcSlotService.getAllDCSlots();
		if (!CollectionUtils.isEmpty(dcSlotList)) {
			return ResponseEntity.status(HttpStatus.OK).body(dcSlotList);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(dcSlotList);
		}
	}

	@GetMapping(path = "/search/{dcslotId}", produces = { MediaType.APPLICATION_XML,
			MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ResponseEntity<DCSlotDTO> searchDCSlotById(@PathVariable("dcslotId") Integer dcslotId) {
		DCSlotDTO dcSlotDTO = dcSlotService.searchDCSlotById(dcslotId);
		if (!ObjectUtils.isEmpty(dcSlotDTO)) {
			return ResponseEntity.status(HttpStatus.OK).body(dcSlotDTO);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(dcSlotDTO);
		}
	}

	@PutMapping(path = "/update", produces = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON }, consumes = {
			MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ResponseEntity<DCSlotDTO> updateDCSlot(@RequestBody DCSlotDTO dcSlotDTO) throws DCSlotException {

		DCSlotDTO dcSlot = dcSlotService.updateDCSlot(dcSlotDTO);
		if (!ObjectUtils.isEmpty(dcSlot)) {
			return ResponseEntity.status(HttpStatus.OK).body(dcSlot);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(dcSlot);
		}

	}

	@DeleteMapping(path = "/delete/all", produces = { MediaType.APPLICATION_XML,
			MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ResponseEntity<List<DCSlotDTO>> deleteAllDCSlots() {
		List<DCSlotDTO> dcSlotList = dcSlotService.deleteAllDCSlots();
		if (!CollectionUtils.isEmpty(dcSlotList)) {
			return ResponseEntity.status(HttpStatus.OK).body(dcSlotList);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(dcSlotList);
		}
	}

	@DeleteMapping(path = "/delete/{dcslotId}", produces = { MediaType.APPLICATION_XML,
			MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ResponseEntity<DCSlotDTO> deleteDCSlotById(@PathVariable("dcslotId") Integer dcslotId) {
		DCSlotDTO dcSlotDTO = dcSlotService.deleteDCSlotById(dcslotId);
		if (!ObjectUtils.isEmpty(dcSlotDTO)) {
			return ResponseEntity.status(HttpStatus.OK).body(dcSlotDTO);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(dcSlotDTO);
		}
	}

}
