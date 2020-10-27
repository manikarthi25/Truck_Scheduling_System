package com.appointment.service1;

import org.springframework.stereotype.Service;

import com.appointment.model.Truck;
import com.appointment.model.TruckEO;
import com.appointment.model.TruckType;
import com.appointment.model.TruckTypeEO;

@Service
public class TruckService {

	public Truck mapToModel (TruckEO truckEntity) {
		Truck truckModel = new Truck();
		
		truckModel.setId(truckEntity.getId());
		truckModel.setTruckNumber(truckEntity.getTruckNumber());
		truckModel.setTruckName(truckEntity.getTruckName());
		
		TruckType truckTypeModel = new TruckType();
		truckTypeModel.setId (truckEntity.getTruckType().getId());
		truckTypeModel.setType(truckEntity.getTruckType().getTruckType());
		
		truckModel.setTruckType(truckTypeModel);
		
		return truckModel;
	}
	
	public TruckEO mapToEntity (Truck truckModel) {
		TruckEO truckEntity = new TruckEO();
		
		truckEntity.setId(truckModel.getId());
		truckEntity.setTruckNumber(truckModel.getTruckNumber());
		truckEntity.setTruckName(truckModel.getTruckName());
		 
		TruckTypeEO truckTypeEntity = new TruckTypeEO();
		truckTypeEntity.setId (truckModel.getTruckType().getId());
		truckTypeEntity.setTruckType(truckModel.getTruckType().getType());
		
		truckEntity.setTruckType(truckTypeEntity);
		
		return truckEntity;
	}
	
}
