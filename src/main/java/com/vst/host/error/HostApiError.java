package com.vst.host.error;

/**
* throws the exception whenever any error occure or validation fails.
*
* @author Nikita Chakole <nikita.chakole@vpel.in>
* @since  21/12/2022
*/

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vst.host.exception.HostException;
import com.vst.host.exception.MethodFailureException;
import com.vst.host.exception.NotAcceptableException;
import com.vst.host.exception.NotFoundException;

/**
 * 
 * @param exception
 * @return error message
 */

@RestControllerAdvice
public class HostApiError {

	String message = "error ";

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public Map<String, Object> notFound(NotFoundException exception) {
		Map<String, Object> errorMap = new HashMap<>();
		HostApiResponse error = new HostApiResponse();
		error.setCode("404");
		error.setMessage(exception.getMessage());
		error.setDescription("Details is not available  ");
		error.setTimeStamp(LocalDateTime.now());
		error.setError(HttpStatus.NOT_FOUND);
		error.setReason("Data not available ");
		errorMap.put(message, error);
		return errorMap;
	}

	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	@ExceptionHandler(NotAcceptableException.class)
	public Map<String, Object> notFound(NotAcceptableException exception) {
		Map<String, Object> errorMap = new HashMap<>();
		HostApiResponse error = new HostApiResponse();
		error.setCode("400");
		error.setMessage("Please Enter Valid Data");
		error.setDescription("NOT ACCEPTABLE ");
		error.setTimeStamp(LocalDateTime.now());
		error.setError(HttpStatus.NOT_ACCEPTABLE);
		error.setReason("Provide Valid data ");
		errorMap.put(message, error);
		return errorMap;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	 
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException exception) {
		Map<String, String> errorMap = new HashMap<>();
		exception.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return errorMap;
	}

	@ResponseStatus(HttpStatus.METHOD_FAILURE)
	@ExceptionHandler(MethodFailureException.class)
	public Map<String, Object> methodFailure(MethodFailureException exception) {
		Map<String, Object> errorMap = new HashMap<>();
		HostApiResponse error = new HostApiResponse();
		error.setCode("420");
		error.setMessage("Something Went Wrong");
		error.setDescription("Not Allowed ");
		error.setTimeStamp(LocalDateTime.now());
		error.setError(HttpStatus.METHOD_FAILURE);
		error.setReason("There is a problem");

		errorMap.put(message, error);
		return errorMap;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HostException.class)
	public <T> ApiResponce<?> hostException(HostException exception) {

		ApiResponce<T> serviceResponse = new ApiResponce<T>();

		serviceResponse.setStatus("FAILED");
		serviceResponse.setErrors(Collections.singletonList(new ErrorResponce(exception.getErrorCode(),
				exception.getFunctionality(), exception.getLineNumber(), exception.getMessage(),
				exception.getMethodName(), exception.getStatus(), exception.getStatusCode())));
		return serviceResponse;
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public Map<String, Object> hostExceptionError(HostException exception) {
		Map<String, Object> errorMap = new HashMap<>();
		HostApiResponse error = new HostApiResponse();
		error.setCode("404");
		error.setMessage(exception.getMessage());
		error.setDescription("Details is not available  ");
		error.setTimeStamp(LocalDateTime.now());
		error.setError(HttpStatus.NOT_FOUND);
		error.setReason("Data not available ");
		error.setLineNumber(exception.getLineNumber());
		errorMap.put(message, error);
		return errorMap;
	}


}
