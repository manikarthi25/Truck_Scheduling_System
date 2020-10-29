package com.downstream.exception;

public class DownstreamException extends Exception {

	private static final long serialVersionUID = 1L;

	private String message;
	private String code;

	public DownstreamException() {
		super();
	}

	public DownstreamException(String message) {
		super(message);
		this.message = message;
	}

	public DownstreamException(String message, Throwable ex) {
		super(message, ex);
		this.message = message;
	}

	public DownstreamException(String message, String code) {
		super(message);
		this.message = message;
		this.code = code;
	}

}
