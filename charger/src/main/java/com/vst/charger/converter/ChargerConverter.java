package com.vst.charger.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import com.vst.charger.model.Charger;
import com.vst.chargerdto.ChargerDTO;

@Component
public class ChargerConverter {
	
	public Charger dtoToEntity(ChargerDTO chargerDTO) {
		Charger charger = new Charger();
		BeanUtils.copyProperties(chargerDTO, charger);
		return charger;
	}
	
	public ChargerDTO entityToDto(Charger charger) {
		ChargerDTO chargerDTO = new ChargerDTO();
		BeanUtils.copyProperties(charger, chargerDTO);
		return chargerDTO;
	}

}
