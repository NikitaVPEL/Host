package com.vst.charger.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

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
	@NotBlank @NotNull(message = "name should not be null")
	private String chargerName;
	@NotNull
	private String chargerInputVoltage;
	@NotNull(message = "not null")
	private String chargerOutputVoltage;
	@NotNull(message = "not null")
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
