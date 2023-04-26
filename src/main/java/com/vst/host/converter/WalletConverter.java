package com.vst.host.converter;

/**
* wallet converter class is to convert the dataof dto class to entity and entity class to dto to not expose the sensitive information.
*
* @author Nikita Chakole <nikita.chakole@vpel.in>
* @since  21/12/2022
*/

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.vst.host.dto.WalletDto;
import com.vst.host.model.Wallet;

@Component
public class WalletConverter {

	/**
	 * Usage : convert/transfer data from dto to entity
	 * 
	 * @param walletDto
	 * @return wallet entity or model
	 */
	public Wallet dtoToEntity(WalletDto walletDto) {
		Wallet wallet = new Wallet();
		BeanUtils.copyProperties(walletDto, wallet);
		return wallet;
	}

	/**
	 * Usage : convert/transfer data from entity to dto
	 * 
	 * @param walletEntity
	 * @return wallet Dto
	 */
	public WalletDto entityToDto(Wallet wallet) {
		WalletDto walletDto = new WalletDto();
		BeanUtils.copyProperties(wallet, walletDto);
		return walletDto;
	}
}
