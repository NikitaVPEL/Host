package com.vst.host.dto;

import java.util.Date;
import java.util.List;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserDto {

	@Id
	private String userId;
	
	private String userFirstName;
	
	private String userLastName;

	private String userGender;

	private String userDateOfBirth;
	
	@Indexed(unique = true)
	private String userEmail;

	@Indexed(unique = true)
	private String userContactNo;

	private String userAddress;

	private String userCity;

	private String userState;

	private String userPincode;

	private String userProfilePhoto;
	
	private String password;

	private Date createdDate;

	private Date modifiedDate;

	private String createdBy;

	private String modifiedBy;

	private boolean isActive;

	

}

