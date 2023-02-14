package com.vst.charger.error;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vst.charger.exception.IdNotAcceptableException;
import com.vst.charger.exception.ChargerNotFoundException;

@RestControllerAdvice
public class ChargerApiError {
	

	@ExceptionHandler(ChargerNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> UserNotFound(ChargerNotFoundException ex){
    	Map<String, Object> errorMap = new HashMap<>();
    	
    	ChargerErrorResponse response = new ChargerErrorResponse();
		response.setMessage("details you have given is not present");
		response.setStatus(HttpStatus.NOT_FOUND);
		response.setStatusCode("404");
		response.setTimeStamp(LocalDateTime.now());
		
    	errorMap.put("error message", response);
    	return errorMap;
    	
    }
    
	@ExceptionHandler(IdNotAcceptableException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Map<String, Object> IdNotFound(IdNotAcceptableException ex){
		
    	Map<String, Object> errorMap = new HashMap<>();
    	
    	ChargerErrorResponse response = new ChargerErrorResponse();
		response.setMessage("given id is not correct or not available");
		response.setStatus(HttpStatus.NOT_ACCEPTABLE);
		response.setStatusCode("406");
		response.setTimeStamp(LocalDateTime.now());
		
    	errorMap.put("error message", response);
    	return errorMap;
	}

}
