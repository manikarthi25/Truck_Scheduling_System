package com.downstream.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.downstream.constant.ErrorCode;

@ControllerAdvice
public class DownstreamExceptionHandler {

	@ExceptionHandler(DownstreamException.class)
	public ResponseEntity<DownstreamException> handleException(final DownstreamException ex) {
		return new ResponseEntity<DownstreamException>(ex, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<DownstreamException> handleException(final Exception ex) {
		DownstreamException downstreamException = new DownstreamException(ErrorCode.TRUCK_PAYLOAD_ERROR,
				ex.getLocalizedMessage());
		return new ResponseEntity<DownstreamException>(downstreamException, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
