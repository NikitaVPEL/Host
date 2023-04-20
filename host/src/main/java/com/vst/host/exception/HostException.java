package com.vst.host.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@Data
public class HostException extends RuntimeException {

//	public HostException(HostException exception) {
//	}
	private static final long serialVersionUID = 1L;
	
	private String errorCode ;
	private String statusCode;
	private String status;
	private String methodName;
	private String lineNumber;
	private String functionality;
	private String message;
	public HostException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HostException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
	public HostException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public HostException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public HostException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	public HostException(String errorCode, String statusCode, String status, String methodName, String lineNumber,
			String functionality, String message) {
		super();
		this.errorCode = errorCode;
		this.statusCode = statusCode;
		this.status = status;
		this.methodName = methodName;
		this.lineNumber = lineNumber;
		this.functionality = functionality;
		this.message = message;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}
	public String getFunctionality() {
		return functionality;
	}
	public void setFunctionality(String functionality) {
		this.functionality = functionality;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "HostException [errorCode=" + errorCode + ", statusCode=" + statusCode + ", status=" + status
				+ ", methodName=" + methodName + ", lineNumber=" + lineNumber + ", functionality=" + functionality
				+ ", message=" + message + "]";
	}
	
	
	

}
