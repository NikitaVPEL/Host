package com.vst.vehicle.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

import com.vst.vehicle.model.Vehicle;

import javax.annotation.PostConstruct;

public class VehicleMongoConfiguration {

	@Autowired
	private MongoTemplate mongoTemplate;

	@PostConstruct
	public void vehicleBrandNameIndexes() {
		mongoTemplate.indexOps(Vehicle.class).ensureIndex(new Index().on("vehicleBrandName", Sort.Direction.ASC));
	}

	@PostConstruct
	public void vehicleModelNameIndexes() {
		mongoTemplate.indexOps(Vehicle.class).ensureIndex(new Index().on("vehicleModelName", Sort.Direction.ASC));
	}

	@PostConstruct
	public void vehicleClassIndexes() {
		mongoTemplate.indexOps(Vehicle.class).ensureIndex(new Index().on("vehicleClass", Sort.Direction.ASC));
	}
	
	@PostConstruct
	public void vehicleBatteryTypeIndexes() {
		mongoTemplate.indexOps(Vehicle.class).ensureIndex(new Index().on("vehicleBatteryType", Sort.Direction.ASC));
	}
	
	@PostConstruct
	public void vehicleBatteryCapacityIndexes() {
		mongoTemplate.indexOps(Vehicle.class).ensureIndex(new Index().on("vehicleBatteryCapacity", Sort.Direction.ASC));
	}
	
	@PostConstruct
	public void vehicleAdaptorTypeIndexes() {
		mongoTemplate.indexOps(Vehicle.class).ensureIndex(new Index().on("vehicleAdaptorType", Sort.Direction.ASC));
	}
	
	@PostConstruct
	public void vehicleMotorTypeIndexes() {
		mongoTemplate.indexOps(Vehicle.class).ensureIndex(new Index().on("vehicleMotorType", Sort.Direction.ASC));
	}
	
	@PostConstruct
	public void vehicleMotorPowerIndexes() {
		mongoTemplate.indexOps(Vehicle.class).ensureIndex(new Index().on("vehicleMotorPower", Sort.Direction.ASC));
	}
	
	@PostConstruct
	public void vehicleMotorTorqueIndexes() {
		mongoTemplate.indexOps(Vehicle.class).ensureIndex(new Index().on("vehicleMotorTorque", Sort.Direction.ASC));
	}

}
