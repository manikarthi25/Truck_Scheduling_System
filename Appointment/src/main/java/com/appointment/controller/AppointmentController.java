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

import com.appointment.model.Appointment;
import com.appointment.service1.AppointmentService;

@RestController
@RequestMapping("appointment")
public class AppointmentController {
	
	@Autowired
	AppointmentService appointmentService;
	
	@GetMapping("/search/{appointmentId}")
	public ResponseEntity<Appointment> searchAppointment (@PathVariable Integer appointmentId) {
		Appointment responseModel = appointmentService.searchAppointmentDetails(appointmentId);
		return ResponseEntity.status(HttpStatus.OK).body(responseModel);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Appointment> createAppointment (@RequestBody Appointment appointment) {
		ResponseEntity<Appointment> response = null;
		Appointment responseModel = appointmentService.createAppointment(appointment);
		if (null != responseModel) {
			response = ResponseEntity.status(HttpStatus.CREATED).body(responseModel) ;
		} else {
			response = ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
		return response;
	}
	
	@PutMapping("/update")
	public ResponseEntity<Appointment> updateAppointment (@RequestBody Appointment appointment) {
		ResponseEntity<Appointment> response = ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		try {
			Appointment responseModel = appointmentService.updateAppointment(appointment);
			if (null != responseModel) {
				response = ResponseEntity.status(HttpStatus.OK).body(responseModel) ;
			} 
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@DeleteMapping("/delete/{appointmentId}")
	public ResponseEntity<Appointment> deleteAppointment (@PathVariable Integer appointmentId) {
		appointmentService.deleteAppointment(appointmentId);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

}
