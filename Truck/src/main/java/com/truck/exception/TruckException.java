package com.truck.exception;

public class TruckException extends Exception {

	private static final long serialVersionUID = 1L;

	private String message;
	private String code;

	public TruckException() {
		super();
	}

	public TruckException(String message) {
		super(message);
		this.message = message;
	}

	public TruckException(String message, Throwable ex) {
		super(message, ex);
		this.message = message;
	}

	public TruckException(String message, String code) {
		super(message);
		this.message = message;
		this.code = code;
	}

}
