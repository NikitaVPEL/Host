package com.vst.host.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vst.host.dto.HostDto;
import com.vst.host.dto.SettlementDto;
import com.vst.host.dto.WalletDto;
import com.vst.host.model.Host;
import com.vst.host.model.Settlement;
import com.vst.host.model.Wallet;

@Component
public class HostConverter {

	@Autowired
	WalletConverter walletConverter;

	@Autowired
	SettlementConverter settlementConverter;

	public Host dtoToEntity(HostDto hostDto) {

		Host host = new Host();
		BeanUtils.copyProperties(hostDto, host);

		WalletDto walletDto = hostDto.getWallets();

		if (walletDto != null) {
			Wallet wallet = walletConverter.dtoToEntity(walletDto);
			host.setWallets(wallet);
		}

		List<SettlementDto> settlementList = hostDto.getSettlements();
		if (settlementList != null) {
			List<Settlement> settlements = new ArrayList<>();

			for (SettlementDto settlementDto : settlementList) {
				settlements.add(settlementConverter.dtoToEntity(settlementDto));
			}
			host.setSettlements(settlements);
		}
		return host;
	}

	public HostDto entityToDto(Host host) {
		HostDto hostDto = new HostDto();
		BeanUtils.copyProperties(host, hostDto);

		Wallet wallet = host.getWallets();
		WalletDto walletDto = walletConverter.entityToDto(wallet);
		hostDto.setWallets(walletDto);

		List<Settlement> settlementlList = host.getSettlements();
		List<SettlementDto> settlementDto = new ArrayList<>();

		for (Settlement settlement : settlementlList) {
			settlementDto.add(settlementConverter.entityToDto(settlement));
		}
		hostDto.setSettlements(settlementDto);
		return hostDto;

	}

}
