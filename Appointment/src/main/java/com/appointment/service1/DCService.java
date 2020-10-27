package com.appointment.service1;

import org.springframework.stereotype.Service;

import com.appointment.model.DC;
import com.appointment.model.DCEO;
import com.appointment.model.DCType;
import com.appointment.model.DCTypeEO;

@Service
public class DCService {
	
	public DC mapTOModel(DCEO dcEntity) {
		DC dcModel = new DC();
		
		dcModel.setId(dcEntity.getId());
		dcModel.setDcNumber(dcEntity.getDcNumber());
		dcModel.setCity(dcEntity.getCity());
		
		DCType dcTypeModel = new DCType();
		dcTypeModel.setId(dcEntity.getDcType().getId());
		dcTypeModel.setType(dcEntity.getDcType().getDcType());
		
		dcModel.setDcType(dcTypeModel);
		
		return dcModel;
	}
	
	public DCEO mapToEntity (DC dcModel) {
		DCEO dcEntity = new DCEO();
		
		dcEntity.setId(dcModel.getId());
		dcEntity.setDcNumber(dcModel.getDcNumber());
		dcEntity.setCity(dcModel.getCity());
		
		DCTypeEO dcTypeEntity = new DCTypeEO();
		dcTypeEntity.setId(dcModel.getDcType().getId());
		dcTypeEntity.setDcType(dcModel.getDcType().getType());
		
		dcEntity.setDcType(dcTypeEntity);
		
		return dcEntity;
	}

}
