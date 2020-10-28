package com.appointment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appointment.AppointmentException;
import com.appointment.dto.AppointmentDTO;
import com.appointment.service.IAppointmentService;

@RestController
@RequestMapping("appointment")
public class AppointmentController {

	@Autowired
	private IAppointmentService appointmentService;

	@GetMapping("/search/{apptId}")
	public ResponseEntity<AppointmentDTO> searchAppointmentById(@PathVariable Long apptId) throws AppointmentException {
		AppointmentDTO responseAppointment = appointmentService.searchAppointmentById(apptId);
		return ResponseEntity.status(HttpStatus.OK).body(responseAppointment);
	}

	@PostMapping("/add")
	public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO appointmentDTO)
			throws AppointmentException {
		ResponseEntity<AppointmentDTO> response = null;
		AppointmentDTO responseAppointment = appointmentService.createAppointment(appointmentDTO);
		if (null != responseAppointment) {
			response = ResponseEntity.status(HttpStatus.CREATED).body(responseAppointment);
		} else {
			response = ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
		return response;
	}

	@DeleteMapping("/delete/{apptId}")
	public ResponseEntity<AppointmentDTO> deleteAppointmentById(@PathVariable Long apptId) throws AppointmentException {
		appointmentService.deleteAppointmentById(apptId);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@PutMapping("/update")
	public ResponseEntity<AppointmentDTO> updateAppointment(@RequestBody AppointmentDTO appointmentDTO)
			throws AppointmentException {
		ResponseEntity<AppointmentDTO> response = ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		try {
			AppointmentDTO responseAppointmentDTO = appointmentService.update(appointmentDTO);
			if (null != responseAppointmentDTO) {
				response = ResponseEntity.status(HttpStatus.OK).body(responseAppointmentDTO);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return response;
	}

}
