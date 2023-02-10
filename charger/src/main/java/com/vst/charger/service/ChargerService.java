package com.vst.charger.service;

import java.util.List;
import com.vst.charger.model.Charger;
import com.vst.chargerdto.ChargerDTO;


public interface ChargerService {
	
	
	public String saveDetails(ChargerDTO chargerDTO);
	
	public Charger getDetails(String chargerId);
	
	public List<Charger> getAllDetails();
	
	public void deleteDetails(String chargerId);
	
	public boolean updateDetails(String chargerId, ChargerDTO chargerDTO);

}
