package com.vst.charger.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.vst.charger.dto.ChargerDto;
import com.vst.charger.model.Charger;
import com.vst.charger.service.ChargerServiceImpl;


@RestController
@RequestMapping("/vst1")
public class ChargerController {

	@Autowired
	ChargerServiceImpl chargerServiceImpl;

	@PostMapping("charger")
	public ResponseEntity<String> addCharger(@Valid @RequestBody ChargerDto chargerDto) {
		chargerServiceImpl.add(chargerDto);
		return new ResponseEntity<>("Data saved Successfully", HttpStatus.OK);
	}

	@GetMapping("charger")
	public ResponseEntity<Charger> getCharger(@RequestParam("chargerId") String chargerId) {
		return ResponseEntity.ok(chargerServiceImpl.show(chargerId));
	}

	@GetMapping("chargers")
	public ResponseEntity<List<Charger>> getAllCharger() {
		return ResponseEntity.ok(chargerServiceImpl.showAll());
	}

	@PutMapping("charger")
	public ResponseEntity<String> updateCharger( @RequestParam("chargerId") String chargerId,
		@Valid	@RequestBody ChargerDto chargerDto) {

		chargerServiceImpl.edit(chargerId, chargerDto);
		return new ResponseEntity<>("Details updated sucessfully", HttpStatus.OK);

	}

	@DeleteMapping("charger")
	public ResponseEntity<String> deleteCharger( @RequestParam("chargerId") String chargerId) {

		chargerServiceImpl.remove(chargerId);
		return new ResponseEntity<>("Charger deleted successfully", HttpStatus.OK);

	}

}
