package com.vst.host.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.vst.host.dto.SettlementDto;
import com.vst.host.model.Settlement;

@Component
public class SettlementConverter {

	public Settlement dtoToEntity(SettlementDto settlementDto) {
		Settlement settlement = new Settlement();
		BeanUtils.copyProperties(settlementDto, settlement);
		return settlement;
	}

	public SettlementDto entityToDto(Settlement settlement) {
		SettlementDto settlementDto = new SettlementDto();
		BeanUtils.copyProperties(settlement, settlementDto);
		return settlementDto;

	}

}
