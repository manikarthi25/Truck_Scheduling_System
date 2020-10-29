package com.downstream.controller;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.downstream.constant.ErrorCode;
import com.downstream.constant.ErrorMessage;
import com.downstream.dto.DownstreamMessage;
import com.downstream.exception.DownstreamException;
import com.downstream.producer.TruckSchedulingEventProducer;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/downstream")
@Slf4j
public class DownstreamMessageController {

	@Autowired
	private TruckSchedulingEventProducer truckSchedulingEventProducer;

	@PostMapping(value = "/post/appointment", produces = { MediaType.APPLICATION_JSON,
			MediaType.APPLICATION_XML }, consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<DownstreamMessage> postTruckSchedulingEvent(
			@Valid @RequestBody DownstreamMessage downstreamMessage)
			throws JsonProcessingException, DownstreamException {

		log.info("Post scheduler Event - Downstream Message : {}", downstreamMessage);
		try {
			truckSchedulingEventProducer.sendTruckSchedulingEvent(downstreamMessage);
			log.info("Truck scheduling information sent successfully : Truck Scheduling Information: {}",
					downstreamMessage);
			return ResponseEntity.status(HttpStatus.OK).body(downstreamMessage);
		} catch (JsonProcessingException e) {
			log.debug(
					"DownstreamMessageController :: postTruckSchedulingEvent :: DownstreamMessage : {}, Exception : {}",
					downstreamMessage, e.getMessage());
			throw new DownstreamException(ErrorCode.TRUCK_APPOINTMENT_PUBLISH_ERROR,
					ErrorMessage.TRUCK_APPOINTMENT_PUBLISH_FAILED);
		}

	}
}
