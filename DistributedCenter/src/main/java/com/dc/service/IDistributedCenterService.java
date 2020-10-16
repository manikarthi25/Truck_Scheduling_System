package com.dc.service;

import java.util.List;

import com.dc.dto.DistributedCenterDTO;

public interface IDistributedCenterService {

	public DistributedCenterDTO addDC(DistributedCenterDTO distributedCenterDTO) throws Exception;

	public List<DistributedCenterDTO> getAllDCs();
	
	public DistributedCenterDTO getDCById(Integer dcId);
	
	public DistributedCenterDTO deleteDCById(Integer dcId);
	
	public List<DistributedCenterDTO> deleteAllDC();
	
	public DistributedCenterDTO updateDC(DistributedCenterDTO dcDTO) throws Exception;

}
