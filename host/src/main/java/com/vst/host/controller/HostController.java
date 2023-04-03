package com.vst.host.controller;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vst.host.dto.HostDto;
import com.vst.host.dto.SettlementDto;
import com.vst.host.dto.WalletDto;
import com.vst.host.model.Host;
import com.vst.host.model.Settlement;
import com.vst.host.model.Wallet;
import com.vst.host.service.HostServiceImpl;

@RestController
@RequestMapping("manageHost")
public class HostController {

	@Autowired
	HostServiceImpl hostServiceImpl;

	@Autowired
	ObjectMapper objectMapper;

	@PostMapping("/addHost")
	public ResponseEntity<String> addHost(@Valid @RequestBody HostDto hostDto) {
		hostServiceImpl.add(hostDto);
		return new ResponseEntity<>("host data added successfully", HttpStatus.OK);
	}

	@GetMapping("/getHost")
	public ResponseEntity<Host> getHost(@RequestParam("hostId") String hostId) {
		return ResponseEntity.ok(hostServiceImpl.show(hostId));

	}

	@GetMapping("/getHosts")
	public ResponseEntity<List<Host>> getAllHost() {
		return ResponseEntity.ok(hostServiceImpl.showAll());

	}

	@GetMapping("/getHostFirstName")
	public ResponseEntity<List<Host>> getByHosFirstName(@RequestParam("hostFirstName") String hostFirstName) {
		return ResponseEntity.ok(hostServiceImpl.showByHostFirstName(hostFirstName));
	}

	@GetMapping("/getHostLastName")
	public ResponseEntity<List<Host>> getByHostLastName(@RequestParam("hostLastName") String hostLastName) {
		return ResponseEntity.ok(hostServiceImpl.showByHostLastName(hostLastName));
	}

	@GetMapping("/getHostEmail")
	public ResponseEntity<List<Host>> getByEmail(@RequestParam("hostEmail") String hostEmail) {
		return ResponseEntity.ok(hostServiceImpl.showByHostEmail(hostEmail));

	}

	@GetMapping("/getHostVehicleRegNo")
	public ResponseEntity<List<Host>> getByHostVehicleRegNo(@RequestParam("hostVehicleRegNo") String hostVehicleRegNo) {
		return ResponseEntity.ok(hostServiceImpl.showByHostVehicleRegNo(hostVehicleRegNo));
	}

	@GetMapping("/getHostVehicleChargerType")
	public ResponseEntity<List<Host>> getByHostVehicleChargerType(
			@RequestParam("hostVehicleChargerType") String hostVehicleChargerType) {
		return ResponseEntity.ok(hostServiceImpl.showByHostVehicleChargerType(hostVehicleChargerType));

	}

	@GetMapping("/getHostCity")
	public ResponseEntity<List<Host>> getByHostCity(@RequestParam("hostCity") String hostCity) {
		return ResponseEntity.ok(hostServiceImpl.showByHostCity(hostCity));
	}

	@GetMapping("/getCreatedBy")
	public ResponseEntity<List<Host>> getByCreatedBy(@RequestParam("createdBy") String createdBy) {
		return ResponseEntity.ok(hostServiceImpl.showByHostCreatedBy(createdBy));
	}

	@GetMapping("/getModifiedBy")
	public ResponseEntity<List<Host>> getByModifiedBy(@RequestParam("modifiedBy") String modifiedBy) {
		return ResponseEntity.ok(hostServiceImpl.showByHostModifiedBy(modifiedBy));
	}

	@GetMapping("/getHostFullName")
	public ResponseEntity<List<Host>> getByFullName(@RequestParam("hostFirstName") String hostFirstName,
			@RequestParam("hostMiddleName") String hostMiddleName, @RequestParam("hostLastName") String hostLastName) {
		return ResponseEntity.ok(hostServiceImpl.showByFullName(hostFirstName, hostMiddleName, hostLastName));
	}

	/* creating a delete mapping that deletes a specified user */

	@DeleteMapping("/deleteHost")
	public ResponseEntity<String> deleteUser(@RequestParam("hostId") String hostId) {
		hostServiceImpl.remove(hostId);
		return new ResponseEntity<>("Data successfully deleted", HttpStatus.OK);

	}

	/* creating a delete mapping that deletes a specified user */

	@PutMapping("/updateHost")
	public ResponseEntity<String> updateHost(@RequestParam("hostId") String hostId, @RequestBody HostDto hostDto) {
		hostServiceImpl.edit(hostId, hostDto);
		return new ResponseEntity<>("Data update successfully", HttpStatus.OK);
	}

	@PostMapping("/addSettlement")
	public ResponseEntity<String> addSettlement(@Valid @RequestParam("hostId") String hostId,
			@RequestBody SettlementDto settlementDto) {

		hostServiceImpl.addSettlement(hostId, settlementDto);
		return new ResponseEntity<>("settlement added successfully", HttpStatus.OK);
	}

	@PostMapping("/addWallet") // host can add wallet using this method
	public ResponseEntity<String> addWallet(@RequestParam("hostId") String hostId,
			@Valid @RequestBody WalletDto walletDto) {

		hostServiceImpl.addWallet(hostId, walletDto);
		return new ResponseEntity<>("wallet added successfully", HttpStatus.OK);
	}

	@GetMapping("/getSettlement")
	public ResponseEntity<List<Settlement>> getBysettSettlementDate(@RequestParam("hostId") String hostId,
			@RequestParam("getSettlementByDate") String settlementDate) {

		return ResponseEntity.ok(hostServiceImpl.getByHostIdAndSettlementDate(hostId, settlementDate));
	}

	@GetMapping("/getBySettlement1")
	public List<Settlement> printSettlementsByDate1(@RequestParam("hostId") String hostId,
			@RequestParam("getSettlementByDate") String settlementDate) {

		List<Settlement> settlements = hostServiceImpl.getByHostIdAndSettlementsDate1(hostId, settlementDate);
		return settlements;
	}
	
	@GetMapping("/getHostDetails")
	public ResponseEntity<Host> getHostDetails(@RequestParam("hostId") String hostId){
		return ResponseEntity.ok(hostServiceImpl.getHostDetailsById(hostId));
	}

}
