package com.vst.charger.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vst.charger.converter.ChargerConverter;
import com.vst.charger.dto.ChargerDto;
import com.vst.charger.exception.ChargerIdNotAcceptableException;
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
	private MongoTemplate mongoTemplate;

	@Autowired
	ChargerSequenceGeneratorService chargerSequenceGeneratorService;

	public static final Logger logger = LogManager.getLogger(ChargerServiceImpl.class);

	Date date = new Date();

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");



	@Override
	@Transactional
	public String add(ChargerDto chargerDto) {

		// data the given data is not valid then it will throw
		// MethodArgumentNotValidException and that exception will catch by
		// handleInvalidException method of ApiError class.
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss.SSS");
		String idFormat = "CHR" + dateFormat.format(date);

		Charger charger = chargerConverter.dtoToEntity(chargerDto);
		charger.setChargerId(idFormat);
		charger.setCreatedDate(dateFormat.format(date));
		charger.setModifiedDate(dateFormat.format(date));
		charger.setActive(true);
		chargerRepository.save(charger);
		return "charger saved successfully";
	}

	@Override
	@Transactional
	public Charger show(String chargerId) {

		if (!chargerId.isBlank()) {

			Charger charger = chargerRepository.findByChargerIdAndIsActiveTrue(chargerId);
			if (charger != null) {

				return charger;
			} else {

				throw new ChargerNotFoundException("data of given id is not available in the database");
			}
		} else {
			throw new ChargerIdNotAcceptableException("entered id is null or not valid ,please enter correct id");
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
	public void edit(String chargerId, ChargerDto chargerDto) {

		if (!chargerId.isBlank()) {
			Charger charger = chargerConverter.dtoToEntity(chargerDto);
			Charger obj = chargerRepository.findByChargerIdAndIsActiveTrue(chargerId);

			if (obj != null) {

				// if data is not valid then it will throw nullPointerException. that exception
				// will catch by chargerApiError class method nullpoint

				if (charger.getChargerName() != null && !charger.getChargerName().isBlank())
						obj.setChargerName(charger.getChargerName());

				if (charger.getChargerInputVoltage() != null && !charger.getChargerInputVoltage().isBlank())
						obj.setChargerInputVoltage(charger.getChargerInputVoltage());

				if (charger.getChargerOutputVoltage() != null && !charger.getChargerOutputVoltage().isBlank())
						obj.setChargerOutputVoltage(charger.getChargerOutputVoltage());

				if (charger.getChargerMinInputAmpere() != null && !charger.getChargerMinInputAmpere().isBlank())
						obj.setChargerMinInputAmpere(charger.getChargerMinInputAmpere());

				if (charger.getChargerMaxInputAmpere() != null && !charger.getChargerMaxInputAmpere().isBlank())
						obj.setChargerMaxInputAmpere(charger.getChargerMaxInputAmpere());

				if (charger.getChargerOutputAmpere() != null && !charger.getChargerOutputAmpere().isBlank())
						obj.setChargerOutputAmpere(charger.getChargerOutputAmpere());

				if (charger.getChargerInputFrequency() != null && !charger.getChargerInputFrequency().isBlank())
						obj.setChargerInputFrequency(charger.getChargerInputFrequency());

				if (charger.getChargerOutputFrequency() != null && !charger.getChargerOutputFrequency().isBlank())
						obj.setChargerOutputFrequency(charger.getChargerOutputFrequency());

				if (charger.getChargerIPRating() != null && !charger.getChargerIPRating().isBlank())
						obj.setChargerIPRating(charger.getChargerIPRating());

				if (charger.getChargerMountType() != null && !charger.getChargerMountType().isBlank())
						obj.setChargerMountType(charger.getChargerMountType());

				if (charger.getIsRFID() != null && !charger.getIsRFID().isBlank())
						obj.setIsRFID(charger.getIsRFID());

				if (charger.getIsAppSupport() != null && !charger.getIsAppSupport().isBlank())
						obj.setIsAppSupport(charger.getIsAppSupport());

				if (charger.getIsTBCutOff() != null && !charger.getIsTBCutOff().isBlank())
						obj.setIsTBCutOff(charger.getIsTBCutOff());

				if (charger.getIsAntitheft() != null && !charger.getIsAntitheft().isBlank())
						obj.setIsAntitheft(charger.getIsAntitheft());

				if (charger.getIsLEDDisplay() != null && !charger.getIsLEDDisplay().isBlank())
						obj.setIsLEDDisplay(charger.getIsLEDDisplay());

				if (charger.getIsLEDIndications() != null && !charger.getIsLEDIndications().isBlank())
						obj.setIsLEDIndications(charger.getIsLEDIndications());

				if (charger.getIsSmart() != null && !charger.getIsSmart().isBlank())
						obj.setIsSmart(charger.getIsSmart());

				obj.setModifiedDate(dateFormat.format(date));

				if (charger.getCreatedBy() != null && !charger.getCreatedBy().isBlank())
						obj.setCreatedBy(charger.getCreatedBy());

				if (charger.getModifiedBy() != null && !charger.getModifiedBy().isBlank())
						obj.setModifiedBy(charger.getModifiedBy());

				chargerRepository.save(obj);
			} else {
				throw new ChargerNotFoundException("data of given id is not available in the database");
			}
		} else {
			throw new ChargerIdNotAcceptableException("entered id is null or not valid ,please enter correct id");

		}
	}

	@Override
	@Transactional
	public void remove(String chargerId) {

		if (!chargerId.isBlank()) {

			Charger charger = chargerRepository.findByChargerIdAndIsActiveTrue(chargerId);
			if (charger != null) {
				charger.setActive(false);
				chargerRepository.save(charger);
			} else {
				throw new ChargerNotFoundException(
						"charger is not found in database, either it is deleted or not available");
			}
		} else {
			throw new ChargerIdNotAcceptableException("given id is not correct, it is null or not valid");
		}
	}

	@Override
	public List<Charger> showByChargerName(String chargerName) {
		if (!chargerName.isBlank()) {

			List<Charger> charger = chargerRepository.findByChargerNameAndIsActiveTrue(chargerName);
			if (!charger.isEmpty()) {

				return charger;
			} else {
				throw new ChargerNotFoundException("data of given id is not available in the database");
			}
		} else {
			throw new ChargerIdNotAcceptableException("entered details is null or not valid ,please enter correct id");
		}
	}


	
	@Override
	public List<Charger> showByChargerInputVoltage(String chargerInputVoltage) {

		if (!chargerInputVoltage.isBlank()) {

			List<Charger> charger = chargerRepository.findByChargerInputVoltageAndIsActiveTrue(chargerInputVoltage);
			if (!charger.isEmpty()) {

				return charger;
			} else {
				throw new ChargerNotFoundException("data of given id is not available in the database");
			}
		} else {
			throw new ChargerIdNotAcceptableException("entered details is null or not valid ,please enter correct id");
		}
	}

	@Override
	public List<Charger> showByChargerOutputVoltage(String chargerOutputVoltage) {
		if (!chargerOutputVoltage.isBlank()) {

			List<Charger> charger = chargerRepository.findByChargerOutputVoltageAndIsActiveTrue(chargerOutputVoltage);
			if (!charger.isEmpty()) {
				return charger;
			} else {
				throw new ChargerNotFoundException("data of given id is not available in the database");
			}
		} else {
			throw new ChargerIdNotAcceptableException("entered details is null or not valid ,please enter correct id");
		}
	}

	@Override
	public List<Charger> showByChargerMountType(String chargerMountType) {
		if (!chargerMountType.isBlank()) {

			List<Charger> charger = chargerRepository.findByChargerMountTypeAndIsActiveTrue(chargerMountType);
			if (!charger.isEmpty()) {
				return charger;
			} else {
				throw new ChargerNotFoundException("data of given id is not available in the database");
			}
		} else {
			throw new ChargerIdNotAcceptableException("entered details is null or not valid ,please enter correct id");
		}
	}

//public List<Charger> findByChargerName(String name) {
//    List<Charger> documents = mongoTemplate.find(
//        Query.query(Criteria.where("name").is(name)), Charger.class);
//    System.out.println(mongoTemplate
//            .explain(Query.query(Criteria.where("name").is(name)), Charger.class));
//    return documents;
//}

}
