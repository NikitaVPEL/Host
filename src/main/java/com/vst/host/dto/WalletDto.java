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
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WalletDto {

	@Id
	private String walletId;
	@NotNull(message = "Wallet balance must be provided Not Be Null")
	@NotBlank(message = "Wallet balance must be provided Not Be Blank")
	private String walletAmount;
	@NotNull(message = "Wallet currency must be provided Not Be Null")
	@NotBlank(message = "Wallet currency must be provided Not Be Blank")
	private String walletCurrency;
	@NotNull
	@NotBlank
	private String walletStatus;
	@NotNull
	@NotBlank
	private String walletType;
	@NotNull
	@NotBlank
	private String walletPaymentType;
	@NotNull
	@NotBlank
	private String walletHistory;

	private Date createdDate;
	private Date modifiedDate;
	@NotNull(message = "Wallet currency must be provided Not Be Null")
	@NotBlank(message = "Wallet currency must be provided Not Be Blank")
	private String createdBy;
	@NotNull(message = "Wallet currency must be provided Not Be Null")
	@NotBlank(message = "Wallet currency must be provided Not Be Blank")
	private String modifiedBy;
	boolean isActive;

	

}
