package com.vst.charger.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vst.charger.converter.ChargerConverter;
import com.vst.charger.exception.ChargerException;
import com.vst.charger.model.Charger;
import com.vst.charger.repository.ChargerRepository;
import com.vst.chargerdto.ChargerDto;

@Service
public class ChargerServiceImpl implements ChargerServiceInterface {

	@Autowired
	ChargerRepository chargerRepository;

	@Autowired
	ChargerConverter chargerConverter;

	@Autowired
	ChargerSequenceGeneratorService chargerSequenceGeneratorService;


	@Override
	@Transactional
	public ChargerDto add(ChargerDto chargerDto) {

		chargerDto.setChargerId(chargerSequenceGeneratorService.idGenerator());
		chargerDto.setActive(true);
		Charger charger = chargerConverter.dtoToEntity(chargerDto);

			chargerRepository.save(charger);
			return chargerConverter.entityToDto(charger);
		
	}


	@Override
	@Transactional
	public Charger show(String chargerId) {
		return chargerRepository.findByChargerIdAndIsActiveTrue(chargerId);

	}


	@Override
	@Transactional
	public List<Charger> showAll() {
		return chargerRepository.findAllByIsActiveTrue();

	}
	
	@Override
	@Transactional
	public boolean edit(String chargerId, ChargerDto chargerDto) {
		Charger charger = chargerConverter.dtoToEntity(chargerDto);

		Charger obj = chargerRepository.findByChargerIdAndIsActiveTrue(chargerId);

		if (obj != null) {
			
			if (charger.getChargerName() != null)
				obj.setChargerName(charger.getChargerName());

			if (charger.getChargerInputVoltage() != null)
				obj.setChargerInputVoltage(charger.getChargerInputVoltage());

			if (charger.getChargerOutputVoltage() != null)
				obj.setChargerOutputVoltage(charger.getChargerOutputVoltage());

			if (charger.getChargerMinInputAmpere() != null)
				obj.setChargerMinInputAmpere(charger.getChargerMinInputAmpere());

			if (charger.getChargerMaxInputAmpere() != null)
				obj.setChargerMaxInputAmpere(charger.getChargerMaxInputAmpere());

			if (charger.getChargerOutputAmpere() != null)
				obj.setChargerOutputAmpere(charger.getChargerOutputAmpere());

			if (charger.getChargerInputFrequency() != null)
				obj.setChargerInputFrequency(charger.getChargerInputFrequency());

			if (charger.getChargerOutputFrequency() != null)
				obj.setChargerOutputFrequency(charger.getChargerOutputFrequency());

			if (charger.getChargerIPRating() != null)
				obj.setChargerIPRating(charger.getChargerIPRating());

			if (charger.getChargerMountType() != null)
				obj.setChargerMountType(charger.getChargerMountType());

			if (charger.getIsRFID() != null)
				obj.setIsRFID(charger.getIsRFID());

			if (charger.getIsAppSupport() != null)
				obj.setIsAppSupport(charger.getIsAppSupport());

			if (charger.getIsTBCutOff() != null)
				obj.setIsTBCutOff(charger.getIsTBCutOff());

			if (charger.getIsAntitheft() != null)
				obj.setIsAntitheft(charger.getIsAntitheft());

			if (charger.getIsLEDDisplay() != null)
				obj.setIsLEDDisplay(charger.getIsLEDDisplay());

			if (charger.getIsLEDIndications() != null)
				obj.setIsLEDIndications(charger.getIsLEDIndications());

			if (charger.getIsSmart() != null)
				obj.setIsSmart(charger.getIsSmart());

			if (charger.getCreatedDate() != null)
				obj.setCreatedDate(charger.getCreatedDate());

			if (charger.getModifiedDate() != null)
				obj.setModifiedDate(charger.getModifiedDate());

			if (charger.getCreatedBy() != null)
				obj.setCreatedBy(charger.getCreatedBy());

			if (charger.getModifiedBy() != null)
				obj.setModifiedBy(charger.getModifiedBy());

			chargerRepository.save(obj);
			return true;
		} else {
			return false;
		}
	}


	@Override
	@Transactional
	public boolean remove(String chargerId) {
		Charger charger = chargerRepository.findByChargerIdAndIsActiveTrue(chargerId);
		if (charger != null) {
			charger.setActive(false);
			chargerRepository.save(charger);
			return true;

		} else 
			return false;
	}



}
