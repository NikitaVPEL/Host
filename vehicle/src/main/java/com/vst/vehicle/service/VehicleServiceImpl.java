package com.vst.vehicle.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vst.vehicle.converter.VehicleConverter;
import com.vst.vehicle.dto.VehicleDto;
import com.vst.vehicle.exception.VehicleIdNotAcceptableException;
import com.vst.vehicle.exception.VehicleNotFoundException;
import com.vst.vehicle.model.Vehicle;
import com.vst.vehicle.repository.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleServiceInterface {

	@Autowired
	VehicleRepository vehicleRepository;

	@Autowired
	VehicleConverter vehicleConverter;

	@Autowired
	VehicleSequenceGeneratorService vehicleSequenceGeneratorService;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();

	// Get the logs into the console
	public static final Logger logger = LogManager.getLogger(VehicleServiceImpl.class);

	@Override
	@Transactional
	public String add(VehicleDto vehicleDto) {

		Vehicle vehicle = vehicleConverter.dtoToEntity(vehicleDto);
		vehicle.setVehicleId(vehicleSequenceGeneratorService.idGenerator());
		vehicle.setCreatedDate(dateFormat.format(date));
		vehicle.setModifiedDate(dateFormat.format(date));
		vehicle.setActive(true);
		vehicleRepository.save(vehicle);
		return "vehicle saved successfully";
	}

	@Override
	@Transactional
	public Vehicle show(String vehicleId) {

		if (!vehicleId.isBlank()) {

			Vehicle vehicle = vehicleRepository.findByVehicleIdAndIsActiveTrue(vehicleId);
			if (vehicle != null) {

				return vehicle;
			} else {

				throw new VehicleNotFoundException("data of given id is not available in the database");
			}
		} else {
			throw new VehicleIdNotAcceptableException("entered id is null or not valid ,please enter correct id");
		}
	}

	@Override
	@Transactional
	public List<Vehicle> showAll() {

		List<Vehicle> list = vehicleRepository.findAllByIsActiveTrue();
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new VehicleNotFoundException("there is no data available in database");
		}
	}

	@Override
	@Transactional
	public void remove(String vehicleId) {

		if (!vehicleId.isBlank()) {

			Vehicle vehicle = vehicleRepository.findByVehicleIdAndIsActiveTrue(vehicleId);
			if (vehicle != null) {
				vehicle.setActive(false);
				vehicleRepository.save(vehicle);
			} else {
				throw new VehicleNotFoundException(
						"vehicle is not found in database, either it is deleted or not available");
			}
		} else {
			throw new VehicleIdNotAcceptableException("given id is not correct, it is null or not valid");
		}
	}

	@Override
	@Transactional
	public void edit(String vehicleId, VehicleDto vehicleDto) {

		if (!vehicleId.isBlank()) {
			Vehicle vehicle = vehicleConverter.dtoToEntity(vehicleDto);
			Vehicle obj = vehicleRepository.findByVehicleIdAndIsActiveTrue(vehicleId);

			if (obj != null) {
				if (vehicle.getVehicleBrandName() != null && !vehicle.getVehicleBrandName().isBlank())
						obj.setVehicleBrandName(vehicle.getVehicleBrandName());

				if (vehicle.getVehicleModelName() != null && !vehicle.getVehicleModelName().isBlank())
						obj.setVehicleModelName(vehicle.getVehicleModelName());

				if (vehicle.getVehicleClass() != null && !vehicle.getVehicleClass().isBlank())
						obj.setVehicleClass(vehicle.getVehicleClass());

				if (vehicle.getVehicleColour() != null && !vehicle.getVehicleColour().isBlank())
						obj.setVehicleColour(vehicle.getVehicleColour());

				if (vehicle.getVehicleBatteryType() != null)
					if (!vehicle.getVehicleBatteryType().isBlank())
						obj.setVehicleBatteryType(vehicle.getVehicleBatteryType());

				if (vehicle.getVehicleBatteryCapacity() != null && !vehicle.getVehicleBatteryCapacity().isBlank())
						obj.setVehicleBatteryCapacity(vehicle.getVehicleBatteryCapacity());

				if (vehicle.getVehicleAdaptorType() != null && !vehicle.getVehicleAdaptorType().isBlank())
						obj.setVehicleAdaptorType(vehicle.getVehicleAdaptorType());

				if (vehicle.getVehicleTimeToChargeRegular() != null && !vehicle.getVehicleTimeToChargeRegular().isBlank())
						obj.setVehicleTimeToChargeRegular(vehicle.getVehicleTimeToChargeRegular());

				if (vehicle.getVehicleTimeToChargeFast() != null && !vehicle.getVehicleTimeToChargeFast().isBlank())
						obj.setVehicleTimeToChargeFast(vehicle.getVehicleTimeToChargeFast());

				if (vehicle.getVehicleChargingStandard() != null && !vehicle.getVehicleChargingStandard().isBlank())
						obj.setVehicleChargingStandard(vehicle.getVehicleChargingStandard());

				if (vehicle.getVehicleRange() != null && !vehicle.getVehicleRange().isBlank())
						obj.setVehicleRange(vehicle.getVehicleRange());

				if (vehicle.getVehicleMotorType() != null && !vehicle.getVehicleMotorType().isBlank())
						obj.setVehicleMotorType(vehicle.getVehicleMotorType());

				if (vehicle.getVehicleMotorPower() != null && !vehicle.getVehicleMotorPower().isBlank())
						obj.setVehicleMotorPower(vehicle.getVehicleMotorPower());

				if (vehicle.getVehicleMotorTorque() != null && !vehicle.getVehicleMotorTorque().isBlank())
						obj.setVehicleMotorTorque(vehicle.getVehicleMotorTorque());

				if (vehicle.getVehicleDriveModes() != null && !vehicle.getVehicleDriveModes().isBlank())
						obj.setVehicleDriveModes(vehicle.getVehicleDriveModes());

				if (vehicle.getVehicleDimentions() != null && !vehicle.getVehicleDimentions().isBlank())
						obj.setVehicleDimentions(vehicle.getVehicleDimentions());

				if (vehicle.getCreatedBy() != null && !vehicle.getCreatedBy().isBlank())
						obj.setCreatedBy(vehicle.getCreatedBy());

				if (vehicle.getModifiedBy() != null && !vehicle.getModifiedBy().isBlank())
						obj.setModifiedBy(vehicle.getModifiedBy());
				
				obj.setModifiedDate(dateFormat.format(date));

				vehicleRepository.save(obj);
			} else {
				throw new VehicleNotFoundException("data of given id is not available in the database");
			}
		} else {
			throw new VehicleIdNotAcceptableException("entered id is null or not valid ,please enter correct id");

		}
	}

	@Override
	@Transactional
	public List<Vehicle> showByVehicleBrandName(String vehicleBrandName) {

		if (!vehicleBrandName.isBlank()) {
			List<Vehicle> vehicle = vehicleRepository.findByVehicleBrandNameAndIsActiveTrue(vehicleBrandName);
			if (!vehicle.isEmpty()) {
				return vehicle;
			} else {
				throw new VehicleNotFoundException("data of given id is not available in the database");
			}
		} else {
			throw new VehicleIdNotAcceptableException("entered details is null or not valid ,please enter correct id");
		}
	}

	@Override
	@Transactional
	public List<Vehicle> showByVehicleModelName(String vehicleModelName) {

		if (!vehicleModelName.isBlank()) {
			List<Vehicle> vehicle = vehicleRepository.findByVehicleModelNameAndIsActiveTrue(vehicleModelName);
			if (!vehicle.isEmpty()) {
				return vehicle;
			} else {
				throw new VehicleNotFoundException("data of given id is not available in the database");
			}
		} else {
			throw new VehicleIdNotAcceptableException("entered details is null or not valid ,please enter correct id");
		}
	}

	@Override
	@Transactional
	public List<Vehicle> showByVehicleClass(String vehicleClass) {

		if (!vehicleClass.isBlank()) {
			List<Vehicle> vehicle = vehicleRepository.findByVehicleClassAndIsActiveTrue(vehicleClass);
			if (!vehicle.isEmpty()) {
				return vehicle;
			} else {
				throw new VehicleNotFoundException("data of given id is not available in the database");
			}
		} else {
			throw new VehicleIdNotAcceptableException("entered details is null or not valid ,please enter correct id");
		}
	}

	@Override
	@Transactional
	public List<Vehicle> showByVehicleBatteryType(String vehicleBatteryType) {

		if (!vehicleBatteryType.isBlank()) {
			List<Vehicle> vehicle = vehicleRepository.findByVehicleBatteryTypeAndIsActiveTrue(vehicleBatteryType);
			if (!vehicle.isEmpty()) {
				return vehicle;
			} else {
				throw new VehicleNotFoundException("data of given id is not available in the database");
			}
		} else {
			throw new VehicleIdNotAcceptableException("entered details is null or not valid ,please enter correct id");
		}
	}

	@Override
	@Transactional
	public List<Vehicle> showByVehicleBatteryCapacity(String vehicleBatteryCapacity) {

		if (!vehicleBatteryCapacity.isBlank()) {
			List<Vehicle> vehicle = vehicleRepository
					.findByVehicleBatteryCapacityAndIsActiveTrue(vehicleBatteryCapacity);
			if (!vehicle.isEmpty()) {
				return vehicle;
			} else {
				throw new VehicleNotFoundException("data of given id is not available in the database");
			}
		} else {
			throw new VehicleIdNotAcceptableException("entered details is null or not valid ,please enter correct id");
		}
	}

	@Override
	@Transactional
	public List<Vehicle> showByVehicleAdaptorType(String vehicleAdaptorType) {

		if (!vehicleAdaptorType.isBlank()) {
			List<Vehicle> vehicle = vehicleRepository.findByVehicleAdaptorTypeAndIsActiveTrue(vehicleAdaptorType);
			if (!vehicle.isEmpty()) {
				return vehicle;
			} else {
				throw new VehicleNotFoundException("data of given id is not available in the database");
			}
		} else {
			throw new VehicleIdNotAcceptableException("entered details is null or not valid ,please enter correct id");
		}
	}

	@Override
	@Transactional
	public List<Vehicle> showByVehicleMotorType(String vehicleMotorType) {

		if (!vehicleMotorType.isBlank()) {
			List<Vehicle> vehicle = vehicleRepository.findByVehicleMotorTypeAndIsActiveTrue(vehicleMotorType);
			if (!vehicle.isEmpty()) {
				return vehicle;
			} else {
				throw new VehicleNotFoundException("data of given id is not available in the database");
			}
		} else {
			throw new VehicleIdNotAcceptableException("entered details is null or not valid ,please enter correct id");
		}
	}

	@Override
	@Transactional
	public List<Vehicle> showByVehicleMotorPower(String vehicleMotorPower) {

		if (!vehicleMotorPower.isBlank()) {

			List<Vehicle> vehicle = vehicleRepository.findByVehicleMotorPowerAndIsActiveTrue(vehicleMotorPower);
			if (!vehicle.isEmpty()) {

				return vehicle;
			} else {

				throw new VehicleNotFoundException("data of given id is not available in the database");
			}
		} else {
			throw new VehicleIdNotAcceptableException("entered details is null or not valid ,please enter correct id");
		}
	}

	@Override
	@Transactional
	public List<Vehicle> showByVehicleMotorTorque(String vehicleMotorTorque) {

		if (!vehicleMotorTorque.isBlank()) {

			List<Vehicle> vehicle = vehicleRepository.findByVehicleMotorTorqueAndIsActiveTrue(vehicleMotorTorque);
			if (!vehicle.isEmpty()) {

				return vehicle;
			} else {

				throw new VehicleNotFoundException("data of given id is not available in the database");
			}
		} else {
			throw new VehicleIdNotAcceptableException("entered details is null or not valid ,please enter correct id");
		}
	}
}
