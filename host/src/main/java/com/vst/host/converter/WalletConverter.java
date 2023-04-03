package com.vst.host.converter;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.vst.host.dto.WalletDto;
import com.vst.host.model.Wallet;

@Component
public class WalletConverter {

	public Wallet dtoToEntity(@Valid WalletDto walletDto) {
		Wallet wallet = new Wallet();
		BeanUtils.copyProperties(walletDto, wallet);
		return wallet;
	}

	public WalletDto entityToDto(Wallet wallet) {
		WalletDto walletDto = new WalletDto();
		BeanUtils.copyProperties(wallet, walletDto);
		return walletDto;
	}
}
