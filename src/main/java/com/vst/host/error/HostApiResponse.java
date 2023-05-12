package com.vst.host.error;

/**
* exception fields whenever any exception throws.
*
* @author Nikita Chakole <nikita.chakole@vpel.in>
* @since  21/12/2022
*/

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HostApiResponse {

	private String message;
    private HttpStatus status;
    private String statusCode;
    private LocalDateTime timeStamp;
 
	private String serviceName;
	private String serviceCode;
	private String className;
	private String methodName;
	private int lineNumber;
	private String functionality;
	
}
