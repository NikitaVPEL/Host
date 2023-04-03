package com.vst.charger.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Gun {
	
	private String chargerGunId ;
	private String chargerGunNumber;
	private String chargerGunOutput;
	private String chargerGunStatus;
	private String chargerGunMeterUnit;
	private String createdDate;
	private String modifiedDate;
	private String createdBy;
	private String modifiedBy;
	private boolean isActive;

}
