package com.truck.service;

import java.util.List;

import com.truck.dto.TruckDTO;
import com.truck.exception.TruckException;

public interface ITruckService {

	TruckDTO addTruck(TruckDTO truckDTO) throws TruckException;

	List<TruckDTO> getAllTrucks();

	TruckDTO searchTruckById(Integer trcukId);

	TruckDTO deleteTruckById(Integer trcukId);

	List<TruckDTO> deleteAllTruck();

	TruckDTO updateTruck(TruckDTO trcukDTO) throws TruckException;

}
