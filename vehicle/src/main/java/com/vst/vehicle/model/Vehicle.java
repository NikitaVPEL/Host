package com.vst.vehicle.model;

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
@Document(collection = "vehicle")

public class Vehicle {

	@Id
	private String vehicleId;
	private String vehicleBrandName;
	private String vehicleModelName;
	private String vehicleClass;
	private String vehicleColour;
	private String vehicleBatteryType;
	private String vehicleBatteryCapacity;
	private String vehicleAdaptorType;
	private String vehicleTimeToChargeRegular;
	private String vehicleTimeToChargeFast;
	private String vehicleChargingStandard;
	private String vehicleRange;
	private String vehicleMotorType;
	private String vehicleMotorPower;
	private String vehicleMotorTorque;
	private String vehicleDriveModes;
	private String vehicleDimentions;
	private String createdDate;
	private String modifiedDate;
	private String createdBy;
	private String modifiedBy;
	private boolean isActive;

}
