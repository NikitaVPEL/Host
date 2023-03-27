package com.vst.vehicle.error;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vst.vehicle.exception.VehicleIdNotAcceptableException;
import com.vst.vehicle.exception.VehicleNotFoundException;

@RestControllerAdvice
public class VehicleApiError {
	
	String errorMessage = "error";



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

	@ExceptionHandler(VehicleNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, Object> userNotFound(VehicleNotFoundException ex) {
		Map<String, Object> errorMap = new HashMap<>();

		VehicleErrorResponse response = new VehicleErrorResponse();
		response.setMessage("details you have given is not present");
		response.setStatus(HttpStatus.NOT_FOUND);
		response.setStatusCode("VC34324");
		response.setTimeStamp(LocalDateTime.now());

		errorMap.put(errorMessage, response);
		return errorMap;

	}

	@ExceptionHandler(VehicleIdNotAcceptableException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public Map<String, Object> idNotFound(VehicleIdNotAcceptableException ex) {

		Map<String, Object> errorMap = new HashMap<>();

		VehicleErrorResponse response = new VehicleErrorResponse();
		response.setMessage("given id is not correct or not available");
		response.setStatus(HttpStatus.NOT_ACCEPTABLE);
		response.setStatusCode("406");
		response.setTimeStamp(LocalDateTime.now());

		errorMap.put(errorMessage, response);
		return errorMap;
	}
	
	/**
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
	} */

}
