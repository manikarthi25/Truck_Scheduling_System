package com.truck.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truck.dto.TruckDTO;
import com.truck.entity.TruckEO;
import com.truck.entity.TruckTypeEO;
import com.truck.exception.TruckException;
import com.truck.helper.MapperUtils;
import com.truck.repository.TruckRepo;
import com.truck.repository.TruckTypeRepo;
import com.truck.service.ITruckService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TruckService implements ITruckService {

	@Autowired
	private TruckRepo truckRepo;

	@Autowired
	private TruckTypeRepo truckTypeRepo;

	private MapperUtils mapperUtils = new MapperUtils();

	@Override
	public TruckDTO addTruck(TruckDTO truckDTO) throws TruckException {
		TruckDTO TruckDTO = new TruckDTO();
		try {
			TruckEO truckEO = mapperUtils.mapToEO(truckDTO);
			TruckTypeEO TruckTypeEO = new TruckTypeEO();
			Integer truckTypeId = getTruckTypeId(truckDTO.getTruckTypeDTO().getTruckTypeName().toLowerCase());
			if (null != truckTypeId) {
				TruckTypeEO.setTruckTypeId(truckTypeId);
				TruckTypeEO.setTruckTypeName(truckDTO.getTruckTypeDTO().getTruckTypeName());
				truckEO.setTruckTypeEO(TruckTypeEO);
			} else {
				throw new TruckException("Check Truck Type");
			}
			TruckEO TruckEO = truckRepo.save(truckRepo.save(truckEO));
			TruckDTO = mapperUtils.mapToDTO(TruckEO, TruckEO.getTruckTypeEO());

		} catch (Exception ex) {
			log.info("TruckService : addtruck : truck :{} Exception : {}", truckDTO, ex);
			throw new TruckException("Excetion in during add new truck", ex);
		}
		return TruckDTO;
	}

	@Override
	public List<TruckDTO> getAllTrucks() {
		List<TruckEO> truckEOList = truckRepo.findAll();
		List<TruckDTO> truckList = new ArrayList<>();
		for (TruckEO truckEO : truckEOList) {
			Optional<TruckTypeEO> truckTypeEO = truckTypeRepo.findById(truckEO.getTruckTypeEO().getTruckTypeId());
			truckList.add(mapperUtils.mapToDTO(truckEO, truckTypeEO.get()));
		}
		return truckList;
	}

	private Integer getTruckTypeId(String truckTypeName) {

		Map<String, Integer> truckTypeMap = new HashMap<>();
		List<TruckTypeEO> truckTypeEOList = truckTypeRepo.findAll();
		for (TruckTypeEO truckTypeEO : truckTypeEOList) {
			truckTypeMap.put(truckTypeEO.getTruckTypeName(), truckTypeEO.getTruckTypeId());
		}
		if (truckTypeMap.containsKey(truckTypeName)) {
			return truckTypeMap.get(truckTypeName);
		} else {
			return null;
		}

	}

	@Override
	public TruckDTO getTruckById(Integer truckId) {
		return getTruck(truckRepo.findById(truckId));
	}

	private TruckDTO getTruck(Optional<TruckEO> truckOptionalEO) {
		if (truckOptionalEO.isPresent()) {
			TruckEO truckEO = truckOptionalEO.get();
			Optional<TruckTypeEO> truckTypeEO = truckTypeRepo.findById(truckEO.getTruckTypeEO().getTruckTypeId());
			return mapperUtils.mapToDTO(truckEO, truckTypeEO.get());
		} else {
			return null;
		}
	}

	@Override
	public TruckDTO deleteTruckById(Integer truckId) {
		Optional<TruckEO> truckOptionalEO = truckRepo.findById(truckId);
		if (truckOptionalEO.isPresent()) {
			truckRepo.deleteById(truckId);
			return getTruck(truckOptionalEO);
		} else {
			return null;
		}
	}

	@Override
	public List<TruckDTO> deleteAllTruck() {
		truckRepo.deleteAll();
		return getAllTrucks();
	}

	@Override
	public TruckDTO updateTruck(TruckDTO truckDTO) throws TruckException {

		try {
			Optional<TruckEO> truckOptionalEO = truckRepo.findById(truckDTO.getTruckId());
			if (truckOptionalEO.isPresent()) {
				TruckEO truckEO = gettruckEO(truckDTO);
				TruckEO TruckEO = truckRepo.saveAndFlush(truckEO);
				return mapperUtils.mapToDTO(TruckEO, TruckEO.getTruckTypeEO());
			} else {
				return null;
			}
		} catch (Exception ex) {
			log.info("TruckService :  updatetruck : truck :{} Exception : {}", truckDTO, ex);
			throw new TruckException("Excetion in during update truck", ex);
		}

	}

	private TruckEO gettruckEO(TruckDTO truckDTO) throws Exception {
		TruckEO truckEO = mapperUtils.mapToEO(truckDTO);
		TruckTypeEO TruckTypeEO = new TruckTypeEO();
		Integer truckTypeId = getTruckTypeId(truckDTO.getTruckTypeDTO().getTruckTypeName().toLowerCase());
		if (null != truckTypeId) {
			TruckTypeEO.setTruckTypeId(truckTypeId);
			TruckTypeEO.setTruckTypeName(truckDTO.getTruckTypeDTO().getTruckTypeName());
			truckEO.setTruckTypeEO(TruckTypeEO);
		} else {
			throw new TruckException("Check Truck Name");
		}
		return truckEO;
	}

}
