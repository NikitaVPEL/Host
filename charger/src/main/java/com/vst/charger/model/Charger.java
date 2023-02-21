package com.vst.charger.model;

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
	@Indexed
	private String chargerInputVoltage;
	@Indexed
	private String chargerOutputVoltage;
	private String chargerMinInputAmpere;
	private String chargerMaxInputAmpere;
	private String chargerOutputAmpere;
	private String chargerInputFrequency;
	private String chargerOutputFrequency;
	private String chargerIPRating;
	@Indexed
	private String chargerMountType;
	private String isRFID;
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
}
