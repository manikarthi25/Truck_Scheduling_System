package com.downstream.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.downstream.dto.DownstreamMessage;
import com.downstream.producer.SchedulerEventProducer;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/scheduler")
@Slf4j
public class DownstreamMessageController {

	@Autowired
	private SchedulerEventProducer schedulerEventProducer;

	@PostMapping(value = "/postschedulerevent", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> postSchedulerEvent(@RequestBody @Valid DownstreamMessage downstreamMessage)
			throws JsonProcessingException {

		log.info("Post scheduler Event - Downstream Message : {}", downstreamMessage);

		if (downstreamMessage.getAppointmentNumber() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please check scheduler event");
		}

		schedulerEventProducer.sendSchedulerEvent(downstreamMessage);
		return ResponseEntity.status(HttpStatus.OK).body(downstreamMessage);

	}

}
