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
	@NotBlank
	@NotNull(message = "name should not be null")
	private String chargerName;
	@NotNull
	@NotBlank
	private String chargerInputVoltage;
	@NotBlank
	@NotNull(message = "not null")
	private String chargerOutputVoltage;
	@NotBlank
	@NotNull(message = "not null")
	private String chargerMinInputAmpere;
	@NotBlank
	@NotNull
	private String chargerMaxInputAmpere;
	@NotBlank
	@NotNull
	private String chargerOutputAmpere;
	@NotBlank
	@NotNull
	private String chargerInputFrequency;
	@NotBlank
	@NotNull
	private String chargerOutputFrequency;
	@NotBlank
	@NotNull
	private String chargerIPRating;
	@NotBlank
	@NotNull
	private String chargerMountType;
	@NotBlank
	@NotNull
	private String isRFID;
	@NotBlank
	@NotNull
	private String isAppSupport;
	@NotBlank
	@NotNull
	private String isTBCutOff;
	@NotBlank
	@NotNull
	private String isAntitheft;
	@NotBlank
	@NotNull
	private String isLEDDisplay;
	@NotBlank
	@NotNull
	private String isLEDIndications;
	@NotBlank
	@NotNull
	private String isSmart;
	@NotBlank
	@NotNull
	private String createdDate;
	@NotBlank
	@NotNull
	private String modifiedDate;
	@NotBlank
	@NotNull
	private String createdBy;
	@NotBlank
	@NotNull
	private String modifiedBy;
	private boolean isActive;

}
