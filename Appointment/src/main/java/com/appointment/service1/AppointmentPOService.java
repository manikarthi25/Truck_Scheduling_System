package com.appointment.service1;

import org.springframework.stereotype.Service;

import com.appointment.model.AppointmentPO;
import com.appointment.model.AppointmentPOEO;

@Service
public class AppointmentPOService {
	
	public AppointmentPO mapToModel (AppointmentPOEO appointmentPOEntity) {
		AppointmentPO appointmentPOModel = new AppointmentPO();
		
		return appointmentPOModel;
	}
	
	public AppointmentPOEO mapToEntity (AppointmentPO appointmentPOModel) {
		AppointmentPOEO appointmentPOEntity =  new AppointmentPOEO();
		
		return appointmentPOEntity;
	}

}
