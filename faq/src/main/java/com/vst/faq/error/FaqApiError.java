package com.vst.faq.error;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vst.faq.exception.FaqNotFoundException;
import com.vst.faq.exception.FaqIdNotAcceptableException;


@RestControllerAdvice
public class FaqApiError {
	
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

	@ExceptionHandler(FaqNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, Object> userNotFound(FaqNotFoundException ex) {
		Map<String, Object> errorMap = new HashMap<>();

		FaqErrorResponse response = new FaqErrorResponse();
		response.setMessage("details you have given is not present");
		response.setStatus(HttpStatus.NOT_FOUND);
		response.setStatusCode("VC34324");
		response.setTimeStamp(LocalDateTime.now());

		errorMap.put(errorMessage, response);
		return errorMap;

	}

	@ExceptionHandler(FaqIdNotAcceptableException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public Map<String, Object> idNotFound(FaqIdNotAcceptableException ex) {

		Map<String, Object> errorMap = new HashMap<>();

		FaqErrorResponse response = new FaqErrorResponse();
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
