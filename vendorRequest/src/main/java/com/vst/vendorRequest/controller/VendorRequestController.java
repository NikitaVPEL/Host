package com.vst.vendorRequest.controller;

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

import com.vst.vendorRequest.dto.VendorRequestDto;
import com.vst.vendorRequest.model.VendorRequest;
import com.vst.vendorRequest.service.VendorRequestServiceImpl;


@RestController
@RequestMapping("/vst1")
public class VendorRequestController {

	@Autowired
	VendorRequestServiceImpl vendorRequestServiceImpl;

	@PostMapping("vendorRequest")
	public ResponseEntity<String> addVehicle(@Valid @RequestBody VendorRequestDto vendorRequestDto) {
		vendorRequestServiceImpl.add(vendorRequestDto);
		return new ResponseEntity<>("Data saved Successfully", HttpStatus.OK);
	}

	@GetMapping("vendorRequest")
	public ResponseEntity<VendorRequest> getVehicle(@RequestParam("vendorRequestId") String vendorRequestId) {
		return ResponseEntity.ok(vendorRequestServiceImpl.show(vendorRequestId));

	}

	@GetMapping("vendorRequests")
	public ResponseEntity<List<VendorRequest>> getAllVehicle() {
		return ResponseEntity.ok(vendorRequestServiceImpl.showAll());

	}

	@DeleteMapping("vendorRequest")
	public ResponseEntity<String> deleteVehicle(@RequestParam("vendorRequestId") String vendorRequestId) {
		vendorRequestServiceImpl.remove(vendorRequestId);
		return new ResponseEntity<>("Charger deleted successfully", HttpStatus.OK);

	}

	@PutMapping("vendorRequest")
	public ResponseEntity<String> updateVehicle(@Valid @RequestParam("vendorRequestId") String vendorRequestId,
			@RequestBody VendorRequestDto vendorRequestDto) {
		vendorRequestServiceImpl.edit(vendorRequestId, vendorRequestDto);
		return new ResponseEntity<>("Details updated sucessfully", HttpStatus.OK);

	}
	
	@GetMapping("vendorRequestHostId")
	public ResponseEntity<List<VendorRequest>> getByVendorRequestHostId(@RequestParam("vendorRequestHostId") String vendorRequestHostId) {
		return ResponseEntity.ok(vendorRequestServiceImpl.showByVendorRequestHostId(vendorRequestHostId));

	}
	
	@GetMapping("vendorRequestType")
	public ResponseEntity<List<VendorRequest>> getByVendorRequestType(@RequestParam("vendorRequestType") String vendorRequestType) {
		return ResponseEntity.ok(vendorRequestServiceImpl.showByVendorRequestType(vendorRequestType));

	}

	@GetMapping("vendorRequestLocation")
	public ResponseEntity<List<VendorRequest>> getByVendorRequestLocation(@RequestParam("vendorRequestLocation") String vendorRequestLocation) {
		return ResponseEntity.ok(vendorRequestServiceImpl.showByVendorRequestLocation(vendorRequestLocation));

	}

	@GetMapping("vendorRequestDate")
	public ResponseEntity<List<VendorRequest>> getByVendorRequestDate(@RequestParam("vendorRequestDate") String vendorRequestDate) {
		return ResponseEntity.ok(vendorRequestServiceImpl.showByVendorRequestDate(vendorRequestDate));

	}


}
