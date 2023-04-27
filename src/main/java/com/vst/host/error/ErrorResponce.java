package com.vst.host.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponce {

	private String errorCode;
	private String statusCode;
	private String status;
	private String methodName;
	private String lineNumber;
	private String functionality;
	private String message;
}
