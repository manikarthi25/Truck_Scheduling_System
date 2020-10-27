package com.appointment.service;

import com.appointment.model.Appointment;

public interface IAppointmentService {
	
	Appointment createAppointment (Appointment appointmentModel) throws RuntimeException;
	
	public Appointment updateAppointment (Appointment appointmentModel) throws RuntimeException;
	
	public void deleteAppointment (Integer appointmentId) throws RuntimeException;
	
	public Appointment searchAppointmentDetails (Integer id) throws RuntimeException;

}
