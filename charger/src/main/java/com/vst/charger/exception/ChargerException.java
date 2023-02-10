package com.vst.charger.exception;

public class ChargerException extends RuntimeException{
	
	private static final long serialVersionUID = 215232344516490651L;

	/**
	 * this method handle the runtime exception 
	 * @param message
	 * return string message
	 */
    public ChargerException(String message) {
        super(message);
        
        
    }


}
