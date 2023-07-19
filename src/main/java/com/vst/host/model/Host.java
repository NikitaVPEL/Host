package com.vst.host.model;

/**
* it is a container class which contain the data of application.
*
* Inherited from : {@link : @Settlement , @Wallet}
* 
* @author Nikita Chakole <nikita.chakole@vpel.in>
* @since  21/12/2022
*/

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Document(collection = "host")
public class Host {

	@Id
	private String hostId;
	private String hostFirstName;
	private String hostMiddleName;
	private String hostLastName;
	private String hostEmail;
	private String hostContactNo;
	private String hostAddress;
	private String hostCity;
	private String hostImage;
	private String password;
	private Date createdDate;
	private Date modifiedDate;
	private String createdBy;
	private String modifiedBy;
	private boolean isActive ;
	private Wallet wallets;
	private List<Settlement> settlements;
	
	
	
}
