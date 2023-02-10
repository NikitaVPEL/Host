package com.vst.charger.controller;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vst.charger.exception.ChargerException;
import com.vst.charger.model.Charger;
import com.vst.charger.service.ChargerServiceImpl;
import com.vst.chargerdto.ChargerDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/charger")
public class ChargerController {
	
	@Autowired
	ChargerServiceImpl service;

	// Get the logs into the console
	public static final Logger logger = LogManager.getLogger(ChargerController.class);
	
	/**
	 * this method is used to save the details in the database
	 * 
	 * @param chargerDTO all the details of chargerDTO class
	 * @return it returns the string if details are stored in database successfully.
	 *         else it will throw the exception and also return the httpStatus
	 */
	@PostMapping
	public ResponseEntity<String> saveCharger(@Valid @RequestBody ChargerDTO chargerDTO) {

		try {
			service.saveDetails(chargerDTO);
			return new ResponseEntity<>("Data saved Successfully", HttpStatus.OK);
		} catch (ChargerException e) {
			logger.error(e.getLocalizedMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	/**
	 * 
	 * this method is used to get or read the particular charger details using id,
	 * type casting is used to convert string to integer because charger id is in
	 * string.
	 * 
	 * @param chargerId value of id
	 * @return if users isActiveStatus is true then it will return charger details
	 *         otherwise it will throw the exception
	 */
	@GetMapping("get/{chargerId}")
	public ResponseEntity<Charger> getCharger(@PathVariable String chargerId) {

		if (chargerId != null) {
			Charger obj = service.getDetails(chargerId);
			if (obj != null) {
				return new ResponseEntity<>(obj, HttpStatus.OK);
			} else {
				throw new ChargerException("data doesn't exist");
			}
		} else {
			throw new ChargerException("please enter correct id");
		}
	}

	/**
	 * This method is used to read all charger details
	 * 
	 * @return it will return list of charger which is present in database with is
	 *         active status true. and if there is no details available in the DB it
	 *         will throw the exception.
	 */
	@GetMapping
	public ResponseEntity<List<Charger>> getAllCharger() {

		List<Charger> list = service.getAllDetails();
		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		} else {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
	}

	/**
	 * method used for soft delete it will update the isActiveStatus true to false
	 * firstly find isActiveStatus is true or not then if true it will update to
	 * false. 
	 * type casting is used to convert string to integer because charger id is
	 * in string.
	 * 
	 * @param chargerId , for get the details of particular id
	 * @return String value, if status changed to false then return string value
	 *         else throw the exception
	 * 
	 */
	@DeleteMapping("/{chargerId}")
	public ResponseEntity<String> deleteCharger(@PathVariable String chargerId) {

		if (chargerId != null) {
			service.deleteDetails(chargerId);
			return new ResponseEntity<>("Charger deleted", HttpStatus.ACCEPTED);
		} else {
			logger.error("Entered id is not correct");
			return new ResponseEntity<>("please enter valid charger id", HttpStatus.NOT_ACCEPTABLE);

		}
	}

	/**
	 * method is used to update the details of particular id.
	 * type casting to convert string value to integer,
	 * if id is present it will update the details otherwise
	 * throw the httpStatus and message.
	 * 
	 * @param chargerId
	 * @param ChargerDTO
	 *  chargerId to get the details 
	 *  chargerDto to update the details
	 * @return string value if charger updated successfully otherwise send the
	 *         httpStatus code with message
	 * 
	 */
	@PutMapping("/{chargerId}")
	public ResponseEntity<String> updateCharger(@Valid @PathVariable String chargerId,
			@RequestBody ChargerDTO chargerDTO) {

		if (chargerId != null) {
		
				if(service.updateDetails(chargerId, chargerDTO)) {
					return new ResponseEntity<>("Details updated sucessfully.", HttpStatus.ACCEPTED);
				}else {
					logger.error("entered id or details are not correct");
					return new ResponseEntity<>("Please enter correct id or details.", HttpStatus.NOT_ACCEPTABLE);
				}
		} else {
			logger.error("Entered id is not correct");
			return new ResponseEntity<>("Please enter correct Id.", HttpStatus.NOT_ACCEPTABLE);
		}

	}


}
