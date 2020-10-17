package com.truck.controller;

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

import com.truck.dto.TruckDTO;
import com.truck.service.impl.TruckService;

@RestController
@RequestMapping("/truck")
public class TruckController {
	@Autowired
	private TruckService truckService;

	@PostMapping("/add")
	public ResponseEntity<TruckDTO> addTruck(@RequestBody TruckDTO truckDTO) throws Exception {

		TruckDTO truck = truckService.addTruck(truckDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(truck);

	}

	@GetMapping("/get/all")
	public ResponseEntity<List<TruckDTO>> getAllTrucks() {
		List<TruckDTO> truckList = truckService.getAllTrucks();
		if (!CollectionUtils.isEmpty(truckList)) {
			return ResponseEntity.status(HttpStatus.OK).body(truckList);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(truckList);
		}
	}

	@GetMapping("/get/{truckId}")
	public ResponseEntity<TruckDTO> getTruckById(@PathVariable("truckId") Integer truckId) {
		TruckDTO truck = truckService.getTruckById(truckId);
		if (!ObjectUtils.isEmpty(truck)) {
			return ResponseEntity.status(HttpStatus.OK).body(truck);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(truck);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<TruckDTO> updateTruck(@RequestBody TruckDTO truckDTO) throws Exception {

		TruckDTO truck = truckService.updateTruck(truckDTO);
		if (!ObjectUtils.isEmpty(truck)) {
			return ResponseEntity.status(HttpStatus.OK).body(truck);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(truck);
		}

	}

	@DeleteMapping("/delete/all")
	public ResponseEntity<List<TruckDTO>> deleteAllTruck() {
		List<TruckDTO> truckList = truckService.deleteAllTruck();
		if (!CollectionUtils.isEmpty(truckList)) {
			return ResponseEntity.status(HttpStatus.OK).body(truckList);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(truckList);
		}
	}

	@DeleteMapping("/delete/{truckId}")
	public ResponseEntity<TruckDTO> deleteTruckById(@PathVariable("truckId") Integer truckId) {
		TruckDTO truck = truckService.deleteTruckById(truckId);
		if (!ObjectUtils.isEmpty(truck)) {
			return ResponseEntity.status(HttpStatus.OK).body(truck);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(truck);
		}
	}

}
