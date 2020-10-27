package com.dc.service;

import java.util.List;

import com.dc.dto.DistributedCenterDTO;
import com.dc.exception.DistributedCenterException;

public interface IDistributedCenterService {

	DistributedCenterDTO addDC(DistributedCenterDTO distributedCenterDTO) throws DistributedCenterException;

	List<DistributedCenterDTO> getAllDCs();

	DistributedCenterDTO searchDCById(Integer dcId);

	DistributedCenterDTO deleteDCById(Integer dcId);

	List<DistributedCenterDTO> deleteAllDC();

	DistributedCenterDTO updateDC(DistributedCenterDTO dcDTO) throws DistributedCenterException;

}
