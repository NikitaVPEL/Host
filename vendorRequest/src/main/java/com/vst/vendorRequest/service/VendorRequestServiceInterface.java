package com.vst.vendorRequest.service;

import java.util.List;
import com.vst.vendorRequest.dto.VendorRequestDto;
import com.vst.vendorRequest.model.VendorRequest;

public interface VendorRequestServiceInterface {
	
	public String add(VendorRequestDto vendorRequestDto);
	
	public VendorRequest show(String vendorRequestId);
	
	public List<VendorRequest> showAll();
	
	public void remove(String vendorRequestId);
	
	public void edit(String vendorRequestId, VendorRequestDto vendorRequestDto);
	
	public List<VendorRequest> showByVendorRequestHostId(String vendorRequestHostId);
	
	public List<VendorRequest> showByVendorRequestType(String vendorRequestType);

	public List<VendorRequest> showByVendorRequestLocation(String vendorRequestLocation);

	public List<VendorRequest> showByVendorRequestDate(String vendorRequestDate);




}
