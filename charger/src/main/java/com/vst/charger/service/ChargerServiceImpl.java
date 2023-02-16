package com.vst.charger.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.vst.charger.controller.ChargerController;
import com.vst.charger.converter.ChargerConverter;
import com.vst.charger.dto.ChargerDto;
import com.vst.charger.exception.IdNotAcceptableException;
import com.vst.charger.exception.ChargerNotFoundException;
import com.vst.charger.model.Charger;
import com.vst.charger.repository.ChargerRepository;

import jakarta.validation.Valid;

@Service
public class ChargerServiceImpl implements ChargerServiceInterface {

	@Autowired
	ChargerRepository chargerRepository;

	@Autowired
	ChargerConverter chargerConverter;

	@Autowired
	ChargerSequenceGeneratorService chargerSequenceGeneratorService;

	public static final Logger logger = LogManager.getLogger(ChargerServiceImpl.class);

	@Override
	@Transactional
	public String add(ChargerDto chargerDto) {
		
		//data the given data is not valid then it will throw MethodArgumentNotValidException and that exception will catch
		// by handleInvalidException method of ApiError class.

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
				
				throw new ChargerNotFoundException("data of given id is not available in the database");
			}
		} else {
			throw new IdNotAcceptableException("entered id is null or not valid ,please enter correct id");
		}

	}

	@Override
	@Transactional
	public List<Charger> showAll() {
		List<Charger> list = chargerRepository.findAllByIsActiveTrue();
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new ChargerNotFoundException("there is no data available in database");
		}
	}

	@Override
	@Transactional
	public void edit( String chargerId,@Valid ChargerDto chargerDto) {

		if (!chargerId.trim().isEmpty()) {
			Charger charger = chargerConverter.dtoToEntity(chargerDto);
			Charger obj = chargerRepository.findByChargerIdAndIsActiveTrue(chargerId);
			if (obj != null) {
				
				//if data is not valid then it will throw nullPointerException. that exception will catch by chargerApiError 
				//class method nullpoint

				if (!charger.getChargerName().trim().isEmpty())
					obj.setChargerName(charger.getChargerName());

				if (!charger.getChargerInputVoltage().trim().isEmpty())
					obj.setChargerInputVoltage(charger.getChargerInputVoltage());

				if (!charger.getChargerOutputVoltage().trim().isEmpty())
					obj.setChargerOutputVoltage(charger.getChargerOutputVoltage());

				if (!charger.getChargerMinInputAmpere().trim().isEmpty())
					obj.setChargerMinInputAmpere(charger.getChargerMinInputAmpere());

				if (!charger.getChargerMaxInputAmpere().trim().isEmpty())
					obj.setChargerMaxInputAmpere(charger.getChargerMaxInputAmpere());

				if (!charger.getChargerOutputAmpere().trim().isEmpty())
					obj.setChargerOutputAmpere(charger.getChargerOutputAmpere());

				if (!charger.getChargerInputFrequency().trim().isEmpty())
					obj.setChargerInputFrequency(charger.getChargerInputFrequency());

				if (!charger.getChargerOutputFrequency().trim().isEmpty())
					obj.setChargerOutputFrequency(charger.getChargerOutputFrequency());

				if (!charger.getChargerIPRating().trim().isEmpty())
					obj.setChargerIPRating(charger.getChargerIPRating());

				if (!charger.getChargerMountType().trim().isEmpty())
					obj.setChargerMountType(charger.getChargerMountType());

				if (!charger.getIsRFID().trim().isEmpty())
					obj.setIsRFID(charger.getIsRFID());

				if (!charger.getIsAppSupport().trim().isEmpty())
					obj.setIsAppSupport(charger.getIsAppSupport());

				if (!charger.getIsTBCutOff().trim().isEmpty())
					obj.setIsTBCutOff(charger.getIsTBCutOff());

				if (!charger.getIsAntitheft().trim().isEmpty())
					obj.setIsAntitheft(charger.getIsAntitheft());

				if (!charger.getIsLEDDisplay().trim().isEmpty())
					obj.setIsLEDDisplay(charger.getIsLEDDisplay());

				if (!charger.getIsLEDIndications().trim().isEmpty())
					obj.setIsLEDIndications(charger.getIsLEDIndications());

				if (!charger.getIsSmart().trim().isEmpty())
					obj.setIsSmart(charger.getIsSmart());

				if (!charger.getCreatedDate().trim().isEmpty())
					obj.setCreatedDate(charger.getCreatedDate());

				if (!charger.getModifiedDate().trim().isEmpty())
					obj.setModifiedDate(charger.getModifiedDate());

				if (!charger.getCreatedBy().trim().isEmpty())
					obj.setCreatedBy(charger.getCreatedBy());

				if (!charger.getModifiedBy().trim().isEmpty())
					obj.setModifiedBy(charger.getModifiedBy());

				chargerRepository.save(obj);
			} else {
				throw new ChargerNotFoundException("data of given id is not available in the database");
			}
		} else {
			throw new IdNotAcceptableException("entered id is null or not valid ,please enter correct id");

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
				throw new ChargerNotFoundException("charger is not found in database, either it is deleted or not available");
			}
		} else {
			throw new IdNotAcceptableException("givrn id is not correct, may be it is null or not valid");
		}
	}

}
