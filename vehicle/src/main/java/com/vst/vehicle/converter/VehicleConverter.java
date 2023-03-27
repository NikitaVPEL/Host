package com.vst.vehicle.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import com.vst.vehicle.dto.VehicleDto;
import com.vst.vehicle.model.Vehicle;

@Component
public class VehicleConverter {
	
	public Vehicle dtoToEntity(VehicleDto vehicleDto) {
		Vehicle vehicle = new Vehicle();
		BeanUtils.copyProperties(vehicleDto, vehicle);
		return vehicle;
	}
	
	public VehicleDto entityToDto(Vehicle vehicle) {
		VehicleDto vehicleDto = new VehicleDto();
		BeanUtils.copyProperties(vehicle, vehicleDto);
		return vehicleDto;
	}

}
