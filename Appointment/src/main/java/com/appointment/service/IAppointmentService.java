package com.appointment.service;

import com.appointment.dto.AppointmentDTO;
import com.appointment.exception.AppointmentException;

public interface IAppointmentService {

	AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) throws AppointmentException;

	AppointmentDTO searchAppointmentById(Long apptId) throws AppointmentException;

	void deleteAppointmentById(Long apptId) throws AppointmentException;

	AppointmentDTO update(AppointmentDTO appointmentDTO) throws AppointmentException;

}
