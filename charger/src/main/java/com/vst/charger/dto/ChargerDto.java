package com.vst.charger.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChargerDto {
	
	@Transient
	public static final String SEQUENCE_NAME = "charger";
	
	@Id
	private String chargerId;
	@NotNull
	private String chargerName;
	@NotNull
	private String chargerInputVoltage;
	@NotNull
	private String chargerOutputVoltage;
	@NotNull
	private String chargerMinInputAmpere;
	@NotNull
	private String chargerMaxInputAmpere;
	@NotNull
	private String chargerOutputAmpere;
	@NotNull
	private String chargerInputFrequency;
	@NotNull
	private String chargerOutputFrequency;
	@NotNull
	private String chargerIPRating;
	@NotNull
	private String chargerMountType;
	@NotNull
	private String isRFID;
	@NotNull
	private String isAppSupport;
	@NotNull
	private String isTBCutOff;
	@NotNull
	private String isAntitheft;
	@NotNull
	private String isLEDDisplay;
	@NotNull
	private String isLEDIndications;
	@NotNull
	private String isSmart;
	@NotNull
	private String createdDate;
	@NotNull
	private String modifiedDate;
	@NotNull
	private String createdBy;
	@NotNull
	private String modifiedBy;
	private boolean isActive;

}
