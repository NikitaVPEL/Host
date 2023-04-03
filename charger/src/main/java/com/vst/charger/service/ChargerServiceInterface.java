package com.vst.charger.service;

import java.util.List;

import com.vst.charger.dto.ChargerDto;
import com.vst.charger.model.Charger;

public interface ChargerServiceInterface {

	public String add(ChargerDto chargerDto);
	
	public Charger show(String chargerId);

	public List<Charger> showAll();

	public void edit(String chargerId, ChargerDto chargerDto);

	public void remove(String chargerId);

	public List<Charger> showByChargerName(String chargerName);

	public List<Charger> showByChargerInputVoltage(String chargerInputVoltage);

	public List<Charger> showByChargerOutputVoltage(String chargerOutputVoltage);

	public List<Charger> showByChargerMountType(String chargerMountType);

}
