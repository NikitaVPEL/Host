package com.vst.charger.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vst.charger.converter.ChargerConverter;
import com.vst.charger.exception.ChargerException;
import com.vst.charger.model.Charger;
import com.vst.charger.repository.ChargerRepository;
import com.vst.chargerdto.ChargerDTO;

@Service
public class ChargerServiceImpl implements ChargerService {

	@Autowired
	ChargerRepository repository;

	@Autowired
	ChargerConverter converter;

	@Autowired
	DbService dbService;

	/**
	 * this method is save the details in DB using repository it auto generate the
	 * id and isActiveStatus true
	 */
	@Override
	@Transactional
	public String saveDetails(ChargerDTO chargerDTO) {

		chargerDTO.setChargerId(dbService.idGenerator());
		chargerDTO.setActive(true);
		Charger charger = converter.dtoToEntity(chargerDTO);

		try {
			repository.save(charger);
			return "Data stored successfully";
		} catch (Exception e) {
			return "Something went wrong, please check the details";
		}
	}

	/**
	 * this method is used to read the details of particular id send by user also it
	 * check the details are deleted or not, if details are available then it will
	 * send the details otherwise send the null value
	 */
	@Override
	@Transactional
	public Charger getDetails(String chargerId) {
		return repository.findByChargerIdAndIsActiveTrue(chargerId);

	}

	/**
	 * this method is used to get list of all the available details in database
	 */
	@Override
	@Transactional
	public List<Charger> getAllDetails() {
		return repository.findAllByIsActiveTrue();

	}

	/**
	 * this method is used for soft delete, if user hit the delete method it will
	 * change the IsActive Status true to false details still present in DB
	 */
	@Override
	@Transactional
	public void deleteDetails(String chargerId) {
		Charger charger = repository.findByChargerIdAndIsActiveTrue(chargerId);
		if (charger != null) {
			charger.setActive(false);
			repository.save(charger);

		} else {
			throw new ChargerException("data is not available");
		}
	}

	/**
	 * This method is used to update the details first it will convert the DTO to
	 * entity then it will store the essential details to the DB
	 */
	@Override
	@Transactional
	public boolean updateDetails(String chargerId, ChargerDTO chargerDTO) {
		Charger charger = converter.dtoToEntity(chargerDTO);

		Charger obj = repository.findByChargerIdAndIsActiveTrue(chargerId);

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

			repository.save(obj);
			return true;
		} else {
			return false;
		}
	}

}
