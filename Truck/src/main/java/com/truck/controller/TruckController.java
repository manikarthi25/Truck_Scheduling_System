package com.truck.controller;

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

import com.truck.dto.TruckDTO;
import com.truck.exception.TruckException;
import com.truck.service.ITruckService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/truck")
@Slf4j
public class TruckController {
	@Autowired
	private ITruckService truckService;

	@PostMapping(path = "/add", produces = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON }, consumes = {
			MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ResponseEntity<TruckDTO> addTruck(@RequestBody TruckDTO truckDTO) throws TruckException {

		log.info("TruckController :: addTruck :: TruckDTO : {}", truckDTO);
		TruckDTO truck = truckService.addTruck(truckDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(truck);

	}

	@GetMapping(path = "/get/all", produces = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON }, consumes = {
			MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ResponseEntity<List<TruckDTO>> getAllTrucks() {

		List<TruckDTO> truckList = truckService.getAllTrucks();
		log.info("TruckController :: getAllTrucks :: List<TruckDTO> : {}", truckList);
		if (!CollectionUtils.isEmpty(truckList)) {
			return ResponseEntity.status(HttpStatus.OK).body(truckList);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(truckList);
		}

	}

	@GetMapping(path = "/search/{truckId}", produces = { MediaType.APPLICATION_XML,
			MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ResponseEntity<TruckDTO> searchTruckById(@PathVariable("truckId") Integer truckId) {

		TruckDTO truck = truckService.searchTruckById(truckId);
		log.info("TruckController :: searchTruckById :: TruckDTO : {}", truck);
		if (!ObjectUtils.isEmpty(truck)) {
			return ResponseEntity.status(HttpStatus.OK).body(truck);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(truck);
		}

	}

	@PutMapping(path = "/update", produces = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON }, consumes = {
			MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ResponseEntity<TruckDTO> updateTruck(@RequestBody TruckDTO truckDTO) throws TruckException {

		TruckDTO truck = truckService.updateTruck(truckDTO);
		log.info("TruckController :: updateTruck :: TruckDTO : {}", truck);
		if (!ObjectUtils.isEmpty(truck)) {
			return ResponseEntity.status(HttpStatus.OK).body(truck);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(truck);
		}

	}

	@DeleteMapping(path = "/delete/all", produces = { MediaType.APPLICATION_XML,
			MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ResponseEntity<List<TruckDTO>> deleteAllTruck() {

		List<TruckDTO> truckList = truckService.deleteAllTruck();
		log.info("TruckController :: deleteAllTruck :: List<TruckDTO> : {}", truckList);
		if (!CollectionUtils.isEmpty(truckList)) {
			return ResponseEntity.status(HttpStatus.OK).body(truckList);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(truckList);
		}

	}

	@DeleteMapping(path = "/delete/{truckId}", produces = { MediaType.APPLICATION_XML,
			MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ResponseEntity<TruckDTO> deleteTruckById(@PathVariable("truckId") Integer truckId) {

		TruckDTO truck = truckService.deleteTruckById(truckId);
		log.info("TruckController :: deleteTruckById :: TruckDTO : {}", truck);
		if (!ObjectUtils.isEmpty(truck)) {
			return ResponseEntity.status(HttpStatus.OK).body(truck);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(truck);
		}

	}

}
