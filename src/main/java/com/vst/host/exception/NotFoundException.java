package com.vst.host.exception;

/**
* whwn any NotFound exception occure this call will be use. it is manually defined exception class.
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
