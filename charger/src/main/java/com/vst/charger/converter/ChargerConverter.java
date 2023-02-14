package com.vst.charger.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.vst.charger.dto.ChargerDto;
import com.vst.charger.model.Charger;

@Component
public class ChargerConverter {
	
	public Charger dtoToEntity(ChargerDto chargerDto) {
		Charger charger = new Charger();
		BeanUtils.copyProperties(chargerDto, charger);
		return charger;
	}
	
	public ChargerDto entityToDto(Charger charger) {
		ChargerDto chargerDto = new ChargerDto();
		BeanUtils.copyProperties(charger, chargerDto);
		return chargerDto;
	}

}
