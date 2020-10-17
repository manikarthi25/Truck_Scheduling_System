package com.dc.exception;

public class DistributedCenterException extends Exception {

	private static final long serialVersionUID = 1L;

	public DistributedCenterException(String message) {
		super(message);
	}

	public DistributedCenterException(String message, Throwable ex) {
		super(message, ex);
	}

}
