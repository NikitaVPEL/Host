package com.vst.vehicle.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.vst.vehicle.model.Vehicle;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String> {

	// create method to find details by id and details are deleted or not
	public Vehicle findByVehicleIdAndIsActiveTrue(String vehicleId);

	// create method to find all the available details and details are deleted or not
	public List<Vehicle> findAllByIsActiveTrue();
	
	public List<Vehicle> findByVehicleBrandNameAndIsActiveTrue(String vehicleBrandName);
	
	public List<Vehicle> findByVehicleModelNameAndIsActiveTrue(String vehicleModelName);

	public List<Vehicle> findByVehicleClassAndIsActiveTrue(String vehicleClass);

	public List<Vehicle> findByVehicleBatteryTypeAndIsActiveTrue(String vehicleBatteryType);

	public List<Vehicle> findByVehicleBatteryCapacityAndIsActiveTrue(String vehicleBatteryCapacity);

	public List<Vehicle> findByVehicleAdaptorTypeAndIsActiveTrue(String vehicleAdaptorType);

	public List<Vehicle> findByVehicleMotorTypeAndIsActiveTrue(String vehicleMotorType);

	public List<Vehicle> findByVehicleMotorPowerAndIsActiveTrue(String vehicleMotorPower);

	public List<Vehicle> findByVehicleMotorTorqueAndIsActiveTrue(String vehicleMotorTorque);


}
