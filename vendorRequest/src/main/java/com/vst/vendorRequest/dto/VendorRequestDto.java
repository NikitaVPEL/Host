package com.vst.vendorRequest.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VendorRequestDto {

	@Transient
	public static final String SEQUENCE_NAME = "vendorRequest";

	@Id
	private String vendorRequestId;

	@NotBlank(message = "vendorRequestHostId should not be Blank")
	@NotNull(message = "vendorRequestHostId should not be NULL")
	private String vendorRequestHostId;

	@NotBlank(message = "vendorRequestType should not be Blank")
	@NotNull(message = "vendorRequestType should not be NULL")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "please enter correct details")
	private String vendorRequestType;

	@NotBlank(message = "vendorRequestNoOfChargersRequired should not be Blank")
	@NotNull(message = "vendorRequestNoOfChargersRequired should not be NULL")
	@Pattern(regexp = "^[0-9]{1,5}+$", message = "please enter correct details")
	private String vendorRequestNoOfChargersRequired;

	@NotBlank(message = "vendorRequestLocation should not be Blank")
	@NotNull(message = "vendorRequestLocation should not be NULL")
	private String vendorRequestLocation;

	@NotBlank(message = "vendorRequestStatus should not be Blank")
	@NotNull(message = "vendorRequestStatus should not be NULL")
	private String vendorRequestStatus;

	@NotBlank(message = "vendorRequestDate should not be Blank")
	@NotNull(message = "vendorRequestDate should not be NULL")
	@Pattern(regexp = "^(?:(?:(?:0[1-9]|1\\d|2[0-8])\\/(?:0[1-9]|1[0-2])|(?:29|30)\\/(?:0[13-9]|1[0-2])|31\\/(?:0"
			+ "[13578]|1[02]))\\/[1-9]\\d{3}|29\\/02(?:\\/[1-9]\\d(?:0[48]|[2468][048]|[13579][26])|(?:[2468][048]"
			+ "|[13579][26])00))$",message = "date must be dd/mm/yyyy")
	private String vendorRequestDate;

	private String createdDate;

	private String modifiedDate;

	@NotBlank(message = "createdBy should not be Blank")
	@NotNull(message = "createdBy should not be NULL")
	private String createdBy;

	@NotBlank(message = "modifiedBy should not be Blank")
	@NotNull(message = "modifiedBy should not be NULL")
	private String modifiedBy;

	private boolean isActive;
}
