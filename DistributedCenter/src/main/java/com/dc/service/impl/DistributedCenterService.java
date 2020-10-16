package com.dc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.dto.DistributedCenterDTO;
import com.dc.entity.DistributedCenterEO;
import com.dc.entity.DistributedCenterTypeEO;
import com.dc.helper.MapperUtils;
import com.dc.repository.DistributedCenterRepo;
import com.dc.repository.DistributedCenterTypeRepo;
import com.dc.service.IDistributedCenterService;

@Service
public class DistributedCenterService implements IDistributedCenterService {

	@Autowired
	private DistributedCenterRepo dcRepo;

	@Autowired
	private DistributedCenterTypeRepo dcTypeRepo;

	private MapperUtils mapperUtils = new MapperUtils();

	@Override
	public DistributedCenterDTO addDC(DistributedCenterDTO dcDTO) throws Exception {

		DistributedCenterEO dcEO = mapperUtils.mapToEO(dcDTO);
		DistributedCenterTypeEO distributedCenterTypeEO = new DistributedCenterTypeEO();
		Integer dcTypeId = getDCTypeId(dcDTO.getDistributedCenterTypeDTO().getDcTypeName().toLowerCase());
		if (null != dcTypeId) {
			distributedCenterTypeEO.setDcTypeId(dcTypeId);
			distributedCenterTypeEO.setDcTypeName(dcDTO.getDistributedCenterTypeDTO().getDcTypeName());
			dcEO.setDistributedCenterTypeEO(distributedCenterTypeEO);
		} else {
			throw new Exception("check dy type name");
		}
		DistributedCenterEO distributedCenterEO = dcRepo.save(dcRepo.save(dcEO));
		return mapperUtils.mapToDTO(distributedCenterEO, distributedCenterEO.getDistributedCenterTypeEO());

	}

	@Override
	public List<DistributedCenterDTO> getAllDCs() {
		List<DistributedCenterEO> dcEOList = dcRepo.findAll();
		List<DistributedCenterDTO> dcList = new ArrayList<>();
		for (DistributedCenterEO dcEO : dcEOList) {
			Optional<DistributedCenterTypeEO> dcTypeEO = dcTypeRepo
					.findById(dcEO.getDistributedCenterTypeEO().getDcTypeId());
			dcList.add(mapperUtils.mapToDTO(dcEO, dcTypeEO.get()));
		}
		return dcList;
	}

	private Integer getDCTypeId(String dcTypeName) {

		Map<String, Integer> dcTypeMap = new HashMap<>();
		List<DistributedCenterTypeEO> dcTypeEOList = dcTypeRepo.findAll();
		for (DistributedCenterTypeEO dcTypeEO : dcTypeEOList) {
			dcTypeMap.put(dcTypeEO.getDcTypeName(), dcTypeEO.getDcTypeId());
		}
		if (dcTypeMap.containsKey(dcTypeName)) {
			return dcTypeMap.get(dcTypeName);
		} else {
			return null;
		}

	}

	@Override
	public DistributedCenterDTO getDCById(Integer dcId) {
		return getDC(dcRepo.findById(dcId));
	}

	private DistributedCenterDTO getDC(Optional<DistributedCenterEO> dcOptionalEO) {
		if (dcOptionalEO.isPresent()) {
			DistributedCenterEO dcEO = dcOptionalEO.get();
			Optional<DistributedCenterTypeEO> dcTypeEO = dcTypeRepo
					.findById(dcEO.getDistributedCenterTypeEO().getDcTypeId());
			return mapperUtils.mapToDTO(dcEO, dcTypeEO.get());
		} else {
			return null;
		}
	}

	@Override
	public DistributedCenterDTO deleteDCById(Integer dcId) {
		Optional<DistributedCenterEO> dcOptionalEO = dcRepo.findById(dcId);
		if (dcOptionalEO.isPresent()) {
			dcRepo.deleteById(dcId);
			return getDC(dcOptionalEO);
		} else {
			return null;
		}
	}

	@Override
	public List<DistributedCenterDTO> deleteAllDC() {
		dcRepo.deleteAll();
		return getAllDCs();
	}

	@Override
	public DistributedCenterDTO updateDC(DistributedCenterDTO dcDTO) throws Exception {
		Optional<DistributedCenterEO> dcOptionalEO = dcRepo.findById(dcDTO.getDcId());
		if (dcOptionalEO.isPresent()) {
			DistributedCenterEO dcEO = getDCEO(dcDTO);
			DistributedCenterEO distributedCenterEO = dcRepo.saveAndFlush(dcEO);
			return mapperUtils.mapToDTO(distributedCenterEO, distributedCenterEO.getDistributedCenterTypeEO());
		} else {
			return null;
		}
	}

	private DistributedCenterEO getDCEO(DistributedCenterDTO dcDTO) throws Exception {
		DistributedCenterEO dcEO = mapperUtils.mapToEO(dcDTO);
		DistributedCenterTypeEO distributedCenterTypeEO = new DistributedCenterTypeEO();
		Integer dcTypeId = getDCTypeId(dcDTO.getDistributedCenterTypeDTO().getDcTypeName().toLowerCase());
		if (null != dcTypeId) {
			distributedCenterTypeEO.setDcTypeId(dcTypeId);
			distributedCenterTypeEO.setDcTypeName(dcDTO.getDistributedCenterTypeDTO().getDcTypeName());
			dcEO.setDistributedCenterTypeEO(distributedCenterTypeEO);
		} else {
			throw new Exception("check dc type name");
		}
		return dcEO;
	}

}
