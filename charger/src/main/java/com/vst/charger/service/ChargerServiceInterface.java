package com.vst.charger.service;

import java.util.List;
import com.vst.charger.model.Charger;
import com.vst.chargerdto.ChargerDto;


public interface ChargerServiceInterface {
	
	
	public ChargerDto add(ChargerDto chargerDto);
	
	public Charger show(String chargerId);
	
	public List<Charger> showAll();
	
	public boolean edit(String chargerId, ChargerDto chargerDto);
	
	public boolean remove(String chargerId);

}
