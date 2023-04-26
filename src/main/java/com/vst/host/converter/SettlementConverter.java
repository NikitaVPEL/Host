package com.vst.host.converter;

/**
* Settlement converter class is to convert the data of dto class to entity and entity class to dto to not expose the sensitive information.
*
* @author Nikita Chakole <nikita.chakole@vpel.in>
* @since  21/12/2022
*/

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.vst.host.dto.SettlementDto;
import com.vst.host.model.Settlement;

@Component
public class SettlementConverter {

	/**
	 * Usage : convert/transfer data from dto to entity
	 * 
	 * @param settlementDto
	 * @return settlement entity or model
	 */
	public Settlement dtoToEntity(SettlementDto settlementDto) {
		Settlement settlement = new Settlement();
		BeanUtils.copyProperties(settlementDto, settlement);
		return settlement;
	}

	/**
	 * Usage : convert/transfer data from entity to dto
	 * 
	 * @param SettlementEntity
	 * @return Settlement Dto
	 */
	public SettlementDto entityToDto(Settlement settlement) {
		SettlementDto settlementDto = new SettlementDto();
		BeanUtils.copyProperties(settlement, settlementDto);
		return settlementDto;

	}

}
