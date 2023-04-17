package com.vst.host.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class HostException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	private String statusCode;
	private String status;
	private String methodName;
	private String lineNumber;
	private String functionality;
	private String message;

}
