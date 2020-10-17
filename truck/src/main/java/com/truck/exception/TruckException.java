package com.truck.exception;

public class TruckException extends Exception {

	private static final long serialVersionUID = 1L;

	public TruckException(String message) {
		super(message);
	}

	public TruckException(String message, Throwable ex) {
		super(message, ex);
	}

}
