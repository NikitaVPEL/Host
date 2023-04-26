package com.vst.host.dto;

/**
* to validate the model class and do not expose the sensitive information
*
* @author Nikita Chakole <nikita.chakole@vpel.in>
* @since  21/12/2022
*/

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HostDto {

	@Transient 
	public static final String SEQUENCE_NAME = "host_sequense";
	
	@Id
	private String hostId;
	
	@NotBlank(message = "Please Enter Valid FirstName")
	@Size(min = 3, max = 50, message = "host first name should be min=3 character and max=50 character")
	@NotNull(message = "Please provide FirstName")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "host first name should only contain letters")
	private String hostFirstName;
	
	@NotBlank(message = "Please Enter Valid MiddleName ")
	@Size(min = 3, max = 50, message = "host middle name should be min=3 character and max=50 character")
	@NotNull(message = "Please Enter Valid MiddleName ")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "host middle name should only contain letters")
	private String hostMiddleName;
	
	@NotBlank(message = "Please Enter Valid LastName ")
	@Size(min = 3, max = 50, message = " host last name should be min=3 character and max=50 character")
	@NotNull(message = "Please Enter Valid LastName ")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "host last name should only contain letters") 
	private String hostLastName;
	
	@NotBlank(message = "Please Enter valid Email")
	@Email(message = "Please Enter valid Email")
	@NotNull(message = "Please Enter valid Email")
	private String hostEmail;
	
	@NotBlank(message = "Please Enter Correct ContactNo")
	@Pattern(regexp = ("(0|91)?[6-9][0-9]{9}"), message = "please Enter Valid ContactNo")
	private String hostContactNo;
	
	@NotBlank(message = "Please Enter Valid Address")
	private String hostAddress;
	
	@NotBlank(message = "Please Enter VehicleRegNo")
	@NotNull(message = "Please Provide VehicleRegNo")
	@Pattern(regexp ="^[A-Z]{2}\\s[0-9]{2}\\s[A-Z]{2}\\s[0-9]{4}$",message ="please Enter Correct VehicalRegNo ")
	private String hostVehicleRegistrationNo;
	
	@NotNull(message = "Please Provide VehicleChargerType ")
	@NotBlank(message ="Please Enter VehicleChargerType ")
	private String hostVehicleChargerType;
	
	@NotNull(message = "Please Provide City")
	@NotBlank(message = "Please Enter City")
	@Pattern(regexp = "^[a-zA-Z]+$", message = " City should only contain letters") 
	private String hostCity;
	
 
	private Date createdDate;
	
	private Date modifiedDate;
	
	private String createdBy;
	
	private String modifiedBy;
	
	private boolean isActive ; 
	
	private WalletDto wallets;
	
	private List<SettlementDto> settlements;
}
