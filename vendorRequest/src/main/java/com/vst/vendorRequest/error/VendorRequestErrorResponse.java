package com.vst.vendorRequest.error;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class VendorRequestErrorResponse {
	

	private String message;
	private HttpStatus status;
	private String statusCode;
	private LocalDateTime timeStamp;


}
