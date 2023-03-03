package com.vst.charger.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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

	@NotBlank(message = "chargerName should not be Blank")
	@NotNull(message = "chargerName should not be NULL")
	@Pattern(regexp = "^[A-Za-z0-9 .-]+$", message = "should be correct")
	private String chargerName;

	@NotBlank(message = "chargerInputVoltage should not be Blank")
	@NotNull(message = "chargerInputVoltage should not be NULL")
	private String chargerInputVoltage;

	@NotBlank(message = "chargerOutputVoltage should not be Blank")
	@NotNull(message = "chargerOutputVoltage should not be null")
	private String chargerOutputVoltage;

	@NotBlank(message = "chargerMinInputAmpere should not be Blank")
	@NotNull(message = "chargerMinInputAmpere should not be NULL")
	private String chargerMinInputAmpere;

	@NotBlank(message = "chargerMaxInputAmpere should not be Blank")
	@NotNull(message = "chargerMaxInputAmpere should not be null")
	private String chargerMaxInputAmpere;   

	@NotBlank(message = "chargerOutputAmpere should not be Blank")
	@NotNull(message = "chargerOutputAmpere should not be NULL")
	private String chargerOutputAmpere;

	@NotBlank(message = "chargerInputFrequency should not be Blank")
	@NotNull(message = "chargerInputFrequency should not be NULL")
	private String chargerInputFrequency;

	@NotBlank(message = "chargerOutputFrequency should not be Blank")
	@NotNull(message = "chargerOutputFrequency should not be NULL")
	private String chargerOutputFrequency;

	@NotBlank(message = "chargerIPRating should not be Blank")
	@NotNull(message = "chargerIPRating should not be NULL")
	@Pattern(regexp = "^[I][P][0-9]{1,4}$", message = "must be Eg: IP45")
	private String chargerIPRating;

	@NotBlank(message = "chargerMountType should not be Blank")
	@NotNull(message = "chargerMountType should not be NULL")
	@Pattern(regexp = "^[Ww]all|[Ss]tand$", message = "must be Wall or Stand")
	private String chargerMountType;

	@NotBlank(message = "isRFID should not be Blank")
	@NotNull(message = "isRFID should not be NULL")
	@Pattern(regexp = "^[Yy][Ee][Ss]|[Nn][Oo]$", message = "Must be Yes or No.")
	private String isRFID;

	@NotBlank(message = "isAppSupport should not be Blank")
	@NotNull(message = "isAppSupport should not be NULL")
	@Pattern(regexp = "^[Yy][Ee][Ss]|[Nn][Oo]$", message = "Must be Yes or No.")
	private String isAppSupport;

	@NotBlank(message = "isTBCutOff should not be Blank")
	@NotNull(message = "isTBCutOff should not be NULL")
	@Pattern(regexp = "^[Yy][Ee][Ss]|[Nn][Oo]$", message = "Must be Yes or No.")
	private String isTBCutOff;

	@NotBlank(message = "isAntitheft should not be Blank")
	@NotNull(message = "isAntitheft should not be NULL")
	@Pattern(regexp = "^[Yy][Ee][Ss]|[Nn][Oo]$", message = "Must be Yes or No.")
	private String isAntitheft;

	@NotBlank(message = "isLEDDisplay should not be Blank")
	@NotNull(message = "isLEDDisplay should not be NULL")
	@Pattern(regexp = "^[Yy][Ee][Ss]|[Nn][Oo]$", message = "Must be Yes or No.")
	private String isLEDDisplay;

	@NotBlank(message = "isLEDIndications should not be Blank")
	@NotNull(message = "isLEDIndications should not be NULL")
	@Pattern(regexp = "^[Yy][Ee][Ss]|[Nn][Oo]$", message = "Must be Yes or No.")
	private String isLEDIndications;

	@NotBlank(message = "isSmart should not be Blank")
	@NotNull(message = "isSmart should not be NULL")
	@Pattern(regexp = "^[Yy][Ee][Ss]|[Nn][Oo]$", message = "Must be Yes or No.")
	private String isSmart;

	private String createdDate;

	private String modifiedDate;

	@NotBlank(message = "createdBy should not be Blank")
	@NotNull(message = "createdBy should not be NULL")
	private String createdBy;

	@NotBlank(message = "modifiedBy should not be Blank")
	@NotNull(message = "modifiedBy should not be NULL")
	private String modifiedBy;

	private boolean isActive;

}
