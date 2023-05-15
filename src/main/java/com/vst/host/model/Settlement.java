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
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Settlement {
	
	@Id
	private String settlementId;
	private String settlementTransactionAmount;
	private String settlementPayerId;
	private String settlementPayeeId;
	private String settlementDate;
	private String settlementAmount;
	private String settlementFees;
	private String settlementTax;
	private String settlementUTR;
	private String settlementCurrency;
	private String settlementStatus;
	private Date createdDate;
	private Date modifiedDate;
	private String createdBy;
	private String modifiedBy;
	private boolean isActive;
	
	
	
	
	
}
