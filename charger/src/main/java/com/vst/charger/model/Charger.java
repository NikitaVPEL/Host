package com.vst.charger.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "charger")


public class Charger {
	
	@Id
	private String chargerId;
	@Indexed
	private String chargerName;
	private String chargerInputVoltage;
	private String chargerOutputVoltage;
	private String chargerMinInputAmpere;
	private String chargerMaxInputAmpere;
	private String chargerOutputAmpere;
	private String chargerInputFrequency;
	private String chargerOutputFrequency;
	private String chargerIPRating;
	private String chargerMountType;
	private String isRFID;
//	private String RFID;
//	private String chargerSerialNumber;
//	private String chargerProtocol;
//	private String chargerConnectorType;
	private String isAppSupport;
	private String isTBCutOff;
	private String isAntitheft;
	private String isLEDDisplay;
	private String isLEDIndications;
	private String isSmart;
	private String createdDate;
	private String modifiedDate;
	private String createdBy;
	private String modifiedBy;
	private boolean isActive;
	private List<Gun> guns;
}
