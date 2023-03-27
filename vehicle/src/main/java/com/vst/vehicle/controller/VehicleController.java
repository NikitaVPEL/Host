package com.vst.vehicle.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.vst.vehicle.dto.VehicleDto;
import com.vst.vehicle.model.Vehicle;
import com.vst.vehicle.service.VehicleServiceImpl;

@RestController
@RequestMapping("/vst1")
public class VehicleController {

	@Autowired
	private VehicleServiceImpl vehicleServiceImpl;

	@PostMapping("/vehicle")
	public ResponseEntity<String> addVehicle(@Valid @RequestBody VehicleDto vehicleDto) {

			vehicleServiceImpl.add(vehicleDto);
			return new ResponseEntity<String>("Data saved Successfully", HttpStatus.OK);
	}

	@GetMapping("/vehicle")
	public ResponseEntity<Vehicle> getVehicle(@RequestParam("vehicleId") String vehicleId) {
     
		return ResponseEntity.ok(vehicleServiceImpl.show(vehicleId));
	}

	@GetMapping("/vehicles")
	public ResponseEntity<List<Vehicle>> getAllVehicle() {

		return ResponseEntity.ok(vehicleServiceImpl.showAll());
	}

	@DeleteMapping("/vehicle")
	public ResponseEntity<String> deleteVehicle(@RequestParam("vehicleId") String vehicleId) {

		vehicleServiceImpl.remove(vehicleId);
		return new ResponseEntity<>("vehicle deleted successfully", HttpStatus.OK);
	}

	@PutMapping("/vehicle")
	public ResponseEntity<String> updateVehicle(@Valid @RequestParam("vehicleId") String vehicleId,
			@RequestBody VehicleDto vehicleDto) {
		
		vehicleServiceImpl.edit(vehicleId, vehicleDto);
		return new ResponseEntity<>("Details updated sucessfully", HttpStatus.OK);
	}
	
	@GetMapping("vehicleBrandName")
	public ResponseEntity<List<Vehicle>> getByVehicleBrandName(@RequestParam("vehicleBrandName") String vehicleBrandName){
		return ResponseEntity.ok(vehicleServiceImpl.showByVehicleBrandName(vehicleBrandName));
	}
	
	@GetMapping("vehicleModelName")
	public ResponseEntity<List<Vehicle>> getByVehicleModelName(@RequestParam("vehicleModelName") String vehicleModelName){
		return ResponseEntity.ok(vehicleServiceImpl.showByVehicleModelName(vehicleModelName));
	}
	
	@GetMapping("vehicleClass")
	public ResponseEntity<List<Vehicle>> getByVehicleClass(@RequestParam("vehicleClass") String vehicleClass){
		return ResponseEntity.ok(vehicleServiceImpl.showByVehicleClass(vehicleClass));
	}
	
	@GetMapping("vehicleBatteryType")
	public ResponseEntity<List<Vehicle>> getByVehicleBatteryType(@RequestParam("vehicleBatteryType") String vehicleBatteryType){
		return ResponseEntity.ok(vehicleServiceImpl.showByVehicleBatteryType(vehicleBatteryType));
	}
	
	@GetMapping("vehicleBatteryCapacity")
	public ResponseEntity<List<Vehicle>> getByVehicleBatteryCapacity(@RequestParam("vehicleBatteryCapacity") String vehicleBatteryCapacity){
		return ResponseEntity.ok(vehicleServiceImpl.showByVehicleBatteryCapacity(vehicleBatteryCapacity));
	}
	
	@GetMapping("vehicleAdaptorType")
	public ResponseEntity<List<Vehicle>> getByVehicleAdaptorType(@RequestParam("vehicleAdaptorType") String vehicleAdaptorType){
		return ResponseEntity.ok(vehicleServiceImpl.showByVehicleAdaptorType(vehicleAdaptorType));
	}
	
	@GetMapping("vehicleMotorType")
	public ResponseEntity<List<Vehicle>> getByVehicleMotorType(@RequestParam("vehicleMotorType") String vehicleMotorType){
		return ResponseEntity.ok(vehicleServiceImpl.showByVehicleMotorType(vehicleMotorType));
	}
	
	@GetMapping("vehicleMotorPower")
	public ResponseEntity<List<Vehicle>> getByVehicleMotorPower(@RequestParam("vehicleMotorPower") String vehicleMotorPower){
		return ResponseEntity.ok(vehicleServiceImpl.showByVehicleMotorPower(vehicleMotorPower));
	}
	
	@GetMapping("vehicleMotorTorque")
	public ResponseEntity<List<Vehicle>> getByVehicleMotorTorque(@RequestParam("vehicleMotorTorque") String vehicleMotorTorque){
		return ResponseEntity.ok(vehicleServiceImpl.showByVehicleMotorTorque(vehicleMotorTorque));
	}
}
