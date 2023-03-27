package com.vst.vendorRequest.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import com.vst.vendorRequest.dto.VendorRequestDto;
import com.vst.vendorRequest.model.VendorRequest;

@Component
public class VendorRequestConverter {
	
	public VendorRequest dtoToEntity(VendorRequestDto vendorRequestDto) {
		VendorRequest vendorRequest = new VendorRequest();
		BeanUtils.copyProperties(vendorRequestDto, vendorRequest);
		return vendorRequest;
	}
	
	public VendorRequestDto entityToDto(VendorRequest vendorRequest) {
		VendorRequestDto vendorRequestDto = new VendorRequestDto();
		BeanUtils.copyProperties(vendorRequest, vendorRequestDto);
		return vendorRequestDto;
	}
	

}
