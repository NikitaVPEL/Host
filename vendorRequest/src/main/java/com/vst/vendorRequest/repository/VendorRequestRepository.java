package com.vst.vendorRequest.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.vst.vendorRequest.model.VendorRequest;

public interface VendorRequestRepository extends MongoRepository<VendorRequest, String> {

	// create method to find details by id and details are deleted or not
	public VendorRequest findByVendorRequestIdAndIsActiveTrue(String vehicleId);

	// create method to find all the available details and details are deleted or not
	public List<VendorRequest> findAllByIsActiveTrue();
	
	public List<VendorRequest> findByVendorRequestHostIdAndIsActiveTrue(String vendorRequestHostId);
	
	public List<VendorRequest> findByVendorRequestTypeAndIsActiveTrue(String vendorRequestType);

	public List<VendorRequest> findByVendorRequestLocationAndIsActiveTrue(String vendorRequestLocation);

	public List<VendorRequest> findByVendorRequestDateAndIsActiveTrue(String vendorRequestDate);

}
