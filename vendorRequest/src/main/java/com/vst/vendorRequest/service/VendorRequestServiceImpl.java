package com.vst.vendorRequest.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vst.vendorRequest.converter.VendorRequestConverter;
import com.vst.vendorRequest.dto.VendorRequestDto;
import com.vst.vendorRequest.exception.VendorRequestIdNotAcceptableException;
import com.vst.vendorRequest.exception.VendorRequestNotFoundException;
import com.vst.vendorRequest.model.VendorRequest;
import com.vst.vendorRequest.repository.VendorRequestRepository;

@Service
public class VendorRequestServiceImpl implements VendorRequestServiceInterface {

	@Autowired
	VendorRequestRepository vendorRequestRepository;

	@Autowired
	VendorRequestSequenceGeneratorService vendorRequestSequenceGeneratorService;

	@Autowired
	VendorRequestConverter vendorRequestConverter;

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();

	// Get the logs into the console
	public static final Logger logger = LogManager.getLogger(VendorRequestServiceImpl.class);

	@Override
	@Transactional
	public String add(VendorRequestDto vendorRequestDto) {

		VendorRequest vendorRequest = vendorRequestConverter.dtoToEntity(vendorRequestDto);
		vendorRequest.setVendorRequestId(vendorRequestSequenceGeneratorService.idGenerator());
		vendorRequest.setCreatedDate(dateFormat.format(date));
		vendorRequest.setModifiedDate(dateFormat.format(date));
		vendorRequest.setActive(true);
		vendorRequestRepository.save(vendorRequest);
		return "Data stored successfully";

	}

	@Override
	@Transactional
	public VendorRequest show(String vendorRequestId) {

		if (!vendorRequestId.isBlank()) {

			VendorRequest vendorRequest = vendorRequestRepository.findByVendorRequestIdAndIsActiveTrue(vendorRequestId);
			if (vendorRequest != null) {

				return vendorRequest;
			} else {

				throw new VendorRequestNotFoundException("data of given id is not available in the database");
			}
		} else {
			throw new VendorRequestIdNotAcceptableException("entered id is null or not valid ,please enter correct id");
		}
	}

	@Override
	@Transactional
	public List<VendorRequest> showAll() {

		List<VendorRequest> list = vendorRequestRepository.findAllByIsActiveTrue();
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new VendorRequestNotFoundException("there is no data available in database");
		}
	}

	@Override
	@Transactional
	public void remove(String vendorRequestId) {

		if (!vendorRequestId.isBlank()) {

			VendorRequest vendorRequest = vendorRequestRepository.findByVendorRequestIdAndIsActiveTrue(vendorRequestId);
			if (vendorRequest != null) {
				vendorRequest.setActive(false);
				vendorRequestRepository.save(vendorRequest);
			} else {
				throw new VendorRequestNotFoundException(
						"VendorRequest is not found in database, either it is deleted or not available");
			}
		} else {
			throw new VendorRequestIdNotAcceptableException("given id is not correct, it is null or not valid");
		}
	}

	@Override
	@Transactional
	public void edit(String vendorRequestId, VendorRequestDto vendorRequestDto) {

		if (!vendorRequestId.isBlank()) {

			VendorRequest vendorRequest = vendorRequestConverter.dtoToEntity(vendorRequestDto);
			VendorRequest obj = vendorRequestRepository.findByVendorRequestIdAndIsActiveTrue(vendorRequestId);
			if (obj != null) {

				if (vendorRequest.getVendorRequestHostId() != null && !vendorRequest.getVendorRequestHostId().isBlank())
					obj.setVendorRequestHostId(vendorRequest.getVendorRequestHostId());

				if (vendorRequest.getVendorRequestType() != null && !vendorRequest.getVendorRequestType().isBlank())
					obj.setVendorRequestType(vendorRequest.getVendorRequestType());

				if (vendorRequest.getVendorRequestNoOfChargersRequired() != null
						&& !vendorRequest.getVendorRequestNoOfChargersRequired().isBlank())
					obj.setVendorRequestNoOfChargersRequired(vendorRequest.getVendorRequestNoOfChargersRequired());

				if (vendorRequest.getVendorRequestLocation() != null
						&& !vendorRequest.getVendorRequestLocation().isBlank())
					obj.setVendorRequestLocation(vendorRequest.getVendorRequestLocation());

				if (vendorRequest.getVendorRequestStatus() != null && !vendorRequest.getVendorRequestStatus().isBlank())
					obj.setVendorRequestStatus(vendorRequest.getVendorRequestStatus());

				if (vendorRequest.getVendorRequestDate() != null && !vendorRequest.getVendorRequestDate().isBlank())
					obj.setVendorRequestDate(vendorRequest.getVendorRequestDate());

				if (vendorRequest.getCreatedBy() != null && !vendorRequest.getCreatedBy().isBlank())
					obj.setCreatedBy(vendorRequest.getCreatedBy());

				if (vendorRequest.getModifiedBy() != null && !vendorRequest.getModifiedBy().isBlank())
					obj.setModifiedBy(vendorRequest.getModifiedBy());
				
				obj.setModifiedDate(dateFormat.format(date));

				vendorRequestRepository.save(obj);
			} else {
				throw new VendorRequestNotFoundException("data of given id is not available in the database");
			}
		} else {
			throw new VendorRequestIdNotAcceptableException("entered id is null or not valid ,please enter correct id");

		}

	}

	@Override
	@Transactional
	public List<VendorRequest> showByVendorRequestHostId(String vendorRequestHostId) {

		if (!vendorRequestHostId.isBlank()) {
			List<VendorRequest> vendorRequest = vendorRequestRepository
					.findByVendorRequestHostIdAndIsActiveTrue(vendorRequestHostId);
			if (vendorRequest != null) {
				return vendorRequest;
			} else {
				throw new VendorRequestNotFoundException("data of given id is not available in the database");
			}
		} else {
			throw new VendorRequestIdNotAcceptableException(
					"entered details is null or not valid ,please enter correct id");
		}
	}

	@Override
	@Transactional
	public List<VendorRequest> showByVendorRequestType(String vendorRequestType) {

		if (!vendorRequestType.isBlank()) {
			List<VendorRequest> vendorRequest = vendorRequestRepository
					.findByVendorRequestTypeAndIsActiveTrue(vendorRequestType);
			if (vendorRequest != null) {
				return vendorRequest;
			} else {
				throw new VendorRequestNotFoundException("data of given id is not available in the database");
			}
		} else {
			throw new VendorRequestIdNotAcceptableException(
					"entered details is null or not valid ,please enter correct id");
		}
	}

	@Override
	@Transactional
	public List<VendorRequest> showByVendorRequestLocation(String vendorRequestLocation) {

		if (!vendorRequestLocation.isBlank()) {
			List<VendorRequest> vendorRequest = vendorRequestRepository
					.findByVendorRequestLocationAndIsActiveTrue(vendorRequestLocation);
			if (vendorRequest != null) {
				return vendorRequest;
			} else {
				throw new VendorRequestNotFoundException("data of given id is not available in the database");
			}
		} else {
			throw new VendorRequestIdNotAcceptableException(
					"entered details is null or not valid ,please enter correct id");
		}
	}

	@Override
	@Transactional
	public List<VendorRequest> showByVendorRequestDate(String vendorRequestDate) {

		if (!vendorRequestDate.isBlank()) {
			List<VendorRequest> vendorRequest = vendorRequestRepository
					.findByVendorRequestDateAndIsActiveTrue(vendorRequestDate);
			if (vendorRequest != null) {
				return vendorRequest;
			} else {
				throw new VendorRequestNotFoundException("data of given id is not available in the database");
			}
		} else {
			throw new VendorRequestIdNotAcceptableException(
					"entered details is null or not valid ,please enter correct id");
		}
	}
}
