package com.vst.charger.error;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vst.charger.exception.IdNotAcceptableException;
import com.vst.charger.exception.ChargerNotFoundException;

@RestControllerAdvice
public class ChargerApiError {
	
	String errorMessage = "error message";



	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		
/**
 * 		List<FieldError> errors = ex.getFieldErrors();
 *      for (FieldError error : errors) {
 *			errorMap.put(error.getField(), error.getDefaultMessage());
 *		}
 */
		
		
		return errorMap;
	}

	@ExceptionHandler(ChargerNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, Object> userNotFound(ChargerNotFoundException ex) {
		Map<String, Object> errorMap = new HashMap<>();

		ChargerErrorResponse response = new ChargerErrorResponse();
		response.setMessage("details you have given is not present");
		response.setStatus(HttpStatus.NOT_FOUND);
		response.setStatusCode("404");
		response.setTimeStamp(LocalDateTime.now());

		errorMap.put(errorMessage, response);
		return errorMap;

	}

	@ExceptionHandler(IdNotAcceptableException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public Map<String, Object> idNotFound(IdNotAcceptableException ex) {

		Map<String, Object> errorMap = new HashMap<>();

		ChargerErrorResponse response = new ChargerErrorResponse();
		response.setMessage("given id is not correct or not available");
		response.setStatus(HttpStatus.NOT_ACCEPTABLE);
		response.setStatusCode("406");
		response.setTimeStamp(LocalDateTime.now());

		errorMap.put(errorMessage, response);
		return errorMap;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NullPointerException.class)
	public Map<String, Object> nullPoint(NullPointerException ex) {
		Map<String, Object> errorMap = new HashMap<>();
		
		ChargerErrorResponse response = new ChargerErrorResponse();
		response.setMessage("please provide valid request");
		response.setStatus(HttpStatus.BAD_REQUEST);
		response.setStatusCode("406");
		response.setTimeStamp(LocalDateTime.now());

		errorMap.put(errorMessage, response);
		return errorMap;
	} 

}
