package com.vst.vehicle.dto;

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
public class VehicleDto {

	@Transient
	public static final String SEQUENCE_NAME = "vehicle";

	@Id
	private String vehicleId;
	
	@NotBlank(message = "vehicleBrandName should not be Blank")
	@NotNull(message = "vehicleBrandName should not be NULL")
	@Pattern(regexp = "^[A-Za-z. -]+$")
	private String vehicleBrandName;
	
	@NotBlank(message = "vehicleModelName should not be Blank")
	@NotNull(message = "vehicleModelName should not be NULL")
	private String vehicleModelName;
	
	@NotBlank(message = "vehicleClass should not be Blank")
	@NotNull(message = "vehicleClass should not be NULL")
	private String vehicleClass;
	
	@NotBlank(message = "vehicleColour should not be Blank")
	@NotNull(message = "vehicleColour should not be NULL")
	private String vehicleColour;
	
	@NotBlank(message = "vehicleBatteryType should not be Blank")
	@NotNull(message = "vehicleBatteryType should not be NULL")
	private String vehicleBatteryType;
	
	@NotBlank(message = "vehicleBatteryCapacity should not be Blank")
	@NotNull(message = "vehicleBatteryCapacity should not be NULL")
	private String vehicleBatteryCapacity;
	
	@NotBlank(message = "vehicleAdaptorType should not be Blank")
	@NotNull(message = "vehicleAdaptorType should not be NULL")
	private String vehicleAdaptorType;
	
	@NotBlank(message = "vehicleTimeToChargeRegular should not be Blank")
	@NotNull(message = "vehicleTimeToChargeRegular should not be NULL")
	private String vehicleTimeToChargeRegular;
	
	@NotBlank(message = "vehicleTimeToChargeFast should not be Blank")
	@NotNull(message = "vehicleTimeToChargeFast should not be NULL")
	private String vehicleTimeToChargeFast;
	
	@NotBlank(message = "vehicleChargingStandard should not be Blank")
	@NotNull(message = "vehicleChargingStandard should not be NULL")
	private String vehicleChargingStandard;
	
	@NotBlank(message = "vehicleRange should not be Blank")
	@NotNull(message = "vehicleRange should not be NULL")
	private String vehicleRange;
	
	@NotBlank(message = "vehicleMotorType should not be Blank")
	@NotNull(message = "vehicleMotorType should not be NULL")
	private String vehicleMotorType;
	
	@NotBlank(message = "vehicleMotorPower should not be Blank")
	@NotNull(message = "vehicleMotorPower should not be NULL")
	private String vehicleMotorPower;
	
	@NotBlank(message = "vehicleMotorTorque should not be Blank")
	@NotNull(message = "vehicleMotorTorque should not be NULL")
	private String vehicleMotorTorque;
	
	@NotBlank(message = "vehicleDriveModes should not be Blank")
	@NotNull(message = "vehicleDriveModes should not be NULL")
	private String vehicleDriveModes;
	
	@NotBlank(message = "vehicleDimentions should not be Blank")
	@NotNull(message = "vehicleDimentions should not be NULL")
	private String vehicleDimentions;
	
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
