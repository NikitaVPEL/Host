package com.vst.vehicle.service;

import java.util.List;

import com.vst.vehicle.dto.VehicleDto;
import com.vst.vehicle.model.Vehicle;

public interface VehicleServiceInterface {
	
	public String add(VehicleDto vehicleDto);
	
	public Vehicle show(String vehicleId);
	
	public List<Vehicle> showAll();
	
	public void remove(String vehicleId);
	
	public void edit(String vehvehicleBatteryCapacityicleId, VehicleDto vehicleDto);
	
	public List<Vehicle> showByVehicleBrandName(String vehicleBrandName);
	
	public List<Vehicle> showByVehicleModelName(String vehicleModelName);

	public List<Vehicle> showByVehicleClass(String vehicleClass);
	
	public List<Vehicle> showByVehicleBatteryType(String vehicleBatteryType);

	public List<Vehicle> showByVehicleBatteryCapacity(String vehicleBatteryCapacity);

	public List<Vehicle> showByVehicleAdaptorType(String vehicleAdaptorType);

	public List<Vehicle> showByVehicleMotorType(String vehicleMotorType);

	public List<Vehicle> showByVehicleMotorPower(String vehicleMotorPower);

	public List<Vehicle> showByVehicleMotorTorque(String vehicleMotorTorque);



}
