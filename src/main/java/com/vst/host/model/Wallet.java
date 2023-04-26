package com.vst.host.model;

/**
* it is a container class which contain the data of application.
*
* Inherited in : {@link : @Host}
*
* @author Nikita Chakole <nikita.chakole@vpel.in>
* @since  21/12/2022
*/

import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Wallet {

	@Id
	private String walletId;
	private String walletAmount;
	private String walletCurrency;
	private String walletStatus;
	private String walletType;
	private String walletPaymentType;
	private String walletHistory;
	private Date createdDate;
	private Date modifiedDate;
	private String createdBy;
	private String modifiedBy;
	boolean isActive;

}
