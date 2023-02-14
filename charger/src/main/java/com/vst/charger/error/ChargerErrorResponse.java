package com.vst.charger.error;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ChargerErrorResponse {
	

	private String message;
	private HttpStatus status;
	private String statusCode;
	private LocalDateTime timeStamp;


}
