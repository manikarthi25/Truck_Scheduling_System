package com.dc.slot.exception;

public class DCSlotException extends Exception {

	private static final long serialVersionUID = 1L;

	public DCSlotException(String message) {
		super(message);
	}

	public DCSlotException(String message, Throwable ex) {
		super(message, ex);
	}

}
