package com.vst.host.exception;

/**
* when any host exception occure this call will be use. it is manually defined exception class.
*
* @author Nikita Chakole <nikita.chakole@vpel.in>
* @since  21/12/2022
*/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HostException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String serviceCode;
	private String serviceName;
	private String className;
	private String methodName;
	private int lineNumber;
	private String functionality;
	private String message;


	public HostException(HostException exception) {
		super();
	}
//
//	public HostException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
//		super(message, cause, enableSuppression, writableStackTrace);
//	}
//
//	public HostException(String message, Throwable cause) {
//		super(message, cause);
//	}
//
//	public HostException(String message) {
//		super(message);
//	}
//
//	public HostException(Throwable cause) {
//		super(cause);
//	}
//
//	public HostException(String errorCode, String statusCode, String status, String methodName, String lineNumber,
//			String functionality, String message) {
//		super();
//		this.errorCode = errorCode;
//		this.statusCode = statusCode;
//		this.status = status;
//		this.methodName = methodName;
//		this.lineNumber = lineNumber;
//		this.functionality = functionality;
//		this.message = message;
//	}
//
//	public String getErrorCode() {
//		return errorCode;
//	}
//
//	public void setErrorCode(String errorCode) {
//		this.errorCode = errorCode;
//	}
//
//	public String getStatusCode() {
//		return statusCode;
//	}
//
//	public void setStatusCode(String statusCode) {
//		this.statusCode = statusCode;
//	}
//
//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	public String getMethodName() {
//		return methodName;
//	}
//
//	public void setMethodName(String methodName) {
//		this.methodName = methodName;
//	}
//
//	public String getLineNumber() {
//		return lineNumber;
//	}
//
//	public void setLineNumber(String lineNumber) {
//		this.lineNumber = lineNumber;
//	}
//
//	public String getFunctionality() {
//		return functionality;
//	}
//
//	public void setFunctionality(String functionality) {
//		this.functionality = functionality;
//	}
//
//	public String getMessage() {
//		return message;
//	}
//
//	public void setMessage(String message) {
//		this.message = message;
//	}
//
//	@Override
//	public String toString() {
//		return "HostException [errorCode=" + errorCode + ", statusCode=" + statusCode + ", status=" + status
//				+ ", methodName=" + methodName + ", lineNumber=" + lineNumber + ", functionality=" + functionality
//				+ ", message=" + message + "]";
//	}

}
