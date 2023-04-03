package com.vst.host.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 215232344516490651L;

	public NotFoundException(String message) {
		super(message);
	}
}
