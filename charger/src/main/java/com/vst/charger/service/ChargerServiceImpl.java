package com.vst.charger.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vst.charger.controller.ChargerController;
import com.vst.charger.converter.ChargerConverter;
import com.vst.charger.dto.ChargerDto;
import com.vst.charger.exception.IdNotAcceptableException;
import com.vst.charger.exception.ChargerNotFoundException;
import com.vst.charger.model.Charger;
import com.vst.charger.repository.ChargerRepository;

@Service
public class ChargerServiceImpl implements ChargerServiceInterface {

	@Autowired
	ChargerRepository chargerRepository;

	@Autowired
	ChargerConverter chargerConverter;

	@Autowired
	ChargerSequenceGeneratorService chargerSequenceGeneratorService;

	public static final Logger logger = LogManager.getLogger(ChargerController.class);

	@Override
	@Transactional
	public String add(ChargerDto chargerDto) {

		Charger charger = chargerConverter.dtoToEntity(chargerDto);
		charger.setChargerId(chargerSequenceGeneratorService.idGenerator());
		charger.setActive(true);
		chargerRepository.save(charger);
		return "charger saved successfully";
	}

	@Override
	@Transactional
	public Charger show(String chargerId) {

		if (!chargerId.trim().isEmpty()) {

			Charger charger = chargerRepository.findByChargerIdAndIsActiveTrue(chargerId);
			if (charger != null) {

				return charger;
			} else {
				throw new ChargerNotFoundException("given id is not available");
			}
		} else {
			throw new IdNotAcceptableException("please enter correct id");
		}

	}

	@Override
	@Transactional
	public List<Charger> showAll() {
		List<Charger> list = chargerRepository.findAllByIsActiveTrue();
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new ChargerNotFoundException("data is not available");
		}
	}

	@Override
	@Transactional
	public void edit(String chargerId, ChargerDto chargerDto) {

		if (!chargerId.trim().isEmpty()) {
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
			} else {
				throw new ChargerNotFoundException("given id is not available");
			}
		} else {
			throw new IdNotAcceptableException("please enter correct id");

		}
	}

	@Override
	@Transactional
	public void remove(String chargerId) {

		if (!chargerId.trim().isEmpty()) {

			Charger charger = chargerRepository.findByChargerIdAndIsActiveTrue(chargerId);
			if (charger != null) {
				charger.setActive(false);
				chargerRepository.save(charger);
			} else {
				throw new ChargerNotFoundException("charger is not found");
			}
		} else {
			throw new IdNotAcceptableException("");
		}
	}

}
