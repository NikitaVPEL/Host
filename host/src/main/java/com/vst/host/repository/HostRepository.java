package com.vst.host.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.vst.host.model.Host;
import com.vst.host.model.Settlement;
import com.vst.host.model.Wallet;

public interface HostRepository extends MongoRepository<Host, String> {

	Host findByHostIdAndIsActiveTrue(String hostId);

	List<Host> findAllByIsActiveTrue();

	List<Host> findByHostFirstNameAndIsActiveTrue(String hostFirstName);

	List<Host> findByHostLastNameAndIsActiveTrue(String hostLastName);

	List<Host> findByHostEmailAndIsActiveTrue(String hostEmail);

	List<Host> findByHostVehicleRegNoAndIsActiveTrue(String hostVehicleRegNo);

	List<Host> findByHostVehicleChargerTypeAndIsActiveTrue(String hostVehicleChargerType);

	List<Host> findByHostCityAndIsActiveTrue(String hostCity);

	List<Host> findByCreatedByAndIsActiveTrue(String createdBy);

	List<Host> findByModifiedByAndIsActiveTrue(String modifiedBy);

	List<Host> findByHostFirstNameAndHostMiddleNameAndHostLastNameAndIsActiveTrue(String hostFirstName,
			String hostMiddleName, String hostLastName);

	@Query("{ 'hostId': ?0, 'settlements.settlementDate': ?1 }")
	Host findByHostIdAndSettlementDate(String hostId, String settlementDate);

	@Aggregation(pipeline = { "{ '$match' : { 'hostId' : ?0}}", "{ '$unwind' : {'path' :'$settlements'}}",
			"{ '$match' : { 'settlements.settlementDate' : ?1}}",
			"{ '$group' : { '_id' : '$_id', 'settlements' : { '$push' : '$settlements'}}}",
			"{ '$project' : { 'settlements' : 1}}" }) 
	Host findBySettlementMatching(String hostId, String settlementDate);

	Host findByHostId
	
}
