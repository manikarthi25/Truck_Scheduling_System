package com.truck.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
		List<TruckEO> truckEOList = truckRepo.findByTruckNumber(truckDTO.getTruckNumber());
		log.info("TruckService :: addTruck :: truckNUmber : {}, findByTruckNumber : {}", truckDTO.getTruckNumber(),
				truckEOList);
		if (CollectionUtils.isEmpty(truckEOList)) {
			try {
				TruckEO truckEO = mapperUtils.mapToEO(truckDTO);
				TruckTypeEO truckTypeEO = new TruckTypeEO();
				String truckName = truckDTO.getTruckTypeDTO().getTruckTypeName().toLowerCase();
				Integer truckTypeId = getTruckTypeId(truckTypeRepo.findAll(), truckName);
				log.info("TruckService :: addTruck :: truckName : {}, truckTypeId : {}", truckName, truckTypeId);
				if (null != truckTypeId) {
					truckTypeEO.setTruckTypeId(truckTypeId);
					truckTypeEO.setTruckTypeName(truckDTO.getTruckTypeDTO().getTruckTypeName());
					truckEO.setTruckTypeEO(truckTypeEO);
				} else {
					throw new TruckException("Check Truck Type");
				}
				truckEO = truckRepo.save(truckEO);
				return mapperUtils.mapToDTO(truckEO, truckEO.getTruckTypeEO());

			} catch (Exception ex) {
				log.info("TruckService : addtruck : truck :{} Exception : {}", truckDTO, ex);
				throw new TruckException("Excetion in during add new truck", ex);
			}
		} else {
			throw new TruckException("This Truck number is already available");
		}
	}

	@Override
	public List<TruckDTO> getAllTrucks() {
		List<TruckEO> truckEOList = truckRepo.findAll();
		List<TruckDTO> truckList = new ArrayList<>();
		for (TruckEO truckEO : truckEOList) {
			Optional<TruckTypeEO> optionalTruckTypeEO = truckTypeRepo
					.findById(truckEO.getTruckTypeEO().getTruckTypeId());
			if (optionalTruckTypeEO.isPresent()) {
				truckList.add(mapperUtils.mapToDTO(truckEO, optionalTruckTypeEO.get()));
			}
		}
		return truckList;
	}

	@Override
	public TruckDTO searchTruckById(Integer truckId) {
		return getTruck(truckRepo.findById(truckId));
	}

	@Override
	public TruckDTO updateTruck(TruckDTO truckDTO) throws TruckException {

		try {
			Optional<TruckEO> truckOptionalEO = truckRepo.findById(truckDTO.getTruckId());
			if (truckOptionalEO.isPresent()) {
				TruckEO truckEO = getTruckEO(truckDTO);
				truckEO = truckRepo.saveAndFlush(truckEO);
				return mapperUtils.mapToDTO(truckEO, truckEO.getTruckTypeEO());
			} else {
				return null;
			}
		} catch (Exception ex) {
			log.info("TruckService :  updatetruck : truck :{} Exception : {}", truckDTO, ex);
			throw new TruckException("Excetion in during update truck", ex);
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

	private Integer getTruckTypeId(List<TruckTypeEO> truckTypeEOList, String truckTypeName) {

		Map<String, Integer> truckTypeMap = new HashMap<>();

		for (TruckTypeEO truckTypeEO : truckTypeEOList) {
			truckTypeMap.put(truckTypeEO.getTruckTypeName(), truckTypeEO.getTruckTypeId());
		}
		return (truckTypeMap.containsKey(truckTypeName)) ? truckTypeMap.get(truckTypeName) : null;

	}

	private TruckDTO getTruck(Optional<TruckEO> truckOptionalEO) {
		TruckDTO truckDTO = new TruckDTO();
		if (truckOptionalEO.isPresent()) {
			TruckEO truckEO = truckOptionalEO.get();
			Optional<TruckTypeEO> truckTypeEO = truckTypeRepo.findById(truckEO.getTruckTypeEO().getTruckTypeId());
			if (truckTypeEO.isPresent()) {
				truckDTO = mapperUtils.mapToDTO(truckEO, truckTypeEO.get());
			}
		}
		return truckDTO;
	}

	private TruckEO getTruckEO(TruckDTO truckDTO) throws TruckException {
		TruckEO truckEO = mapperUtils.mapToEO(truckDTO);
		TruckTypeEO truckTypeEO = new TruckTypeEO();
		String truckName = truckDTO.getTruckTypeDTO().getTruckTypeName().toLowerCase();
		Integer truckTypeId = getTruckTypeId(truckTypeRepo.findAll(), truckName);
		if (null != truckTypeId) {
			truckTypeEO.setTruckTypeId(truckTypeId);
			truckTypeEO.setTruckTypeName(truckDTO.getTruckTypeDTO().getTruckTypeName());
			truckEO.setTruckTypeEO(truckTypeEO);
		} else {
			throw new TruckException("Check Truck Name");
		}
		return truckEO;
	}

}
