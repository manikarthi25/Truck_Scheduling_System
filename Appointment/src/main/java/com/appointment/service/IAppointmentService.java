package com.appointment.service;

import com.appointment.AppointmentException;
import com.appointment.dto.AppointmentDTO;

public interface IAppointmentService {

	AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) throws AppointmentException;

	AppointmentDTO searchAppointmentById(Long apptId) throws AppointmentException;

	void deleteAppointmentById(Long apptId) throws AppointmentException;

}
