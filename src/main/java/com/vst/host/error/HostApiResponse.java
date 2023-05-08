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

	LocalDateTime TimeStamp;
	String code;
	HttpStatus error;
	String message;
	String description;
	String reason;
	
}
