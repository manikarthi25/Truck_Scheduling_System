package com.vendor.exception;

public class VendorException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public VendorException() {
		super();
	}

	public VendorException(String message) {
		super(message);
	}

	public VendorException(String message, Throwable ex) {
		super(message, ex);
	}

}
