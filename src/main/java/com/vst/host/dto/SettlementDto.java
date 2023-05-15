package com.vst.host.dto;

/**
* to validate the model class and do not expose the sensitive information
*
* @author Nikita Chakole <nikita.chakole@vpel.in>
* @since  21/12/2022
*/

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SettlementDto {
	
	@Id
	private String settlementId;
	
	@NotNull
	@NotBlank
	private String settlementTransactionAmount;
	
	@NotNull
	@NotBlank
	private String settlementPayerId;
	
	@NotNull
	@NotBlank
	private String settlementPayeeId;
	
	@NotNull
	@NotBlank
	private String settlementDate;
	
	@NotNull
	@NotBlank
	private String settlementAmount;
	
	@NotNull
	@NotBlank
	private String settlementFees;
	
	@NotNull
	@NotBlank
	private String settlementTax;
	
	@NotNull
	@NotBlank
	private String settlementUTR;
	
	@NotNull
	@NotBlank
	private String settlementCurrency;
	
	@NotBlank
	@NotNull
	private String settlementStatus;
	
	private Date createdDate;
	private Date modifiedDate;
	private String createdBy;
	private String modifiedBy;
	private boolean isActive;
	
	
	

}
