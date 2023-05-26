package com.vst.host.exception;

/**
* when any NotFound exception occure this class will be use. it is manually defined exception class.
*
* @author Nikita Chakole <nikita.chakole@vpel.in>
* @since  21/12/2022
*/

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 215232344516490651L;

	public NotFoundException(String message) {
		super(message);
	}
}
