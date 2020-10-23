package com.truck.service;

import java.util.List;

import com.truck.dto.TruckDTO;
import com.truck.exception.TruckException;

public interface ITruckService {

	public TruckDTO addTruck(TruckDTO truckDTO) throws TruckException;

	public List<TruckDTO> getAllTrucks();

	public TruckDTO searchTruckById(Integer trcukId);

	public TruckDTO deleteTruckById(Integer trcukId);

	public List<TruckDTO> deleteAllTruck();

	public TruckDTO updateTruck(TruckDTO trcukDTO) throws TruckException;

}
