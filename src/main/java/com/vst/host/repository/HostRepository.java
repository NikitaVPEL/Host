package com.vst.host.repository;

/**
* it used to connect to the data base or manage the data in application

*
* Inherited from : {@link : @MongoRepository }
*
* @author snehal matke <snehal.matke@vpel.in>
* @since  31/05/2023
*/

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vst.host.model.Host;
import com.vst.host.model.Settlement;

public interface HostRepository extends MongoRepository<Host, String> {

	// find host object by host id
	Host findByHostIdAndIsActiveTrue(String hostId);

	// find the list of hosts which are currently active
	List<Host> findAllByIsActiveTrue();

	// find the list of host with similar first name and currently active
	List<Host> findByHostFirstNameAndIsActiveTrue(String hostFirstName);

	// find the list of host with similar last name and currently active
	List<Host> findByHostLastNameAndIsActiveTrue(String hostLastName);

	// find the list of host with similar host email and currently active
	Host findByHostEmailAndIsActiveTrue(String hostEmail);

	@Query(value = "{'hostContactNo': ?0, 'isActive': true}", fields = "{'wallets': 0, 'settlements': 0}")
	Host findByHostContactNoAndIsActiveTrue(String hostContactNo);
	

	// find the list of host with similar host city and currently active
	List<Host> findByHostCityAndIsActiveTrue(String hostCity);

	// find the list of host with similar created by and currently active
	List<Host> findByCreatedByAndIsActiveTrue(String createdBy);

	// find the list of host with similar modified by and currently active
	List<Host> findByModifiedByAndIsActiveTrue(String modifiedBy);

	// // find the list of host with similar first name, middle name, last name and
	// currently active
	List<Host> findByHostFirstNameAndHostMiddleNameAndHostLastNameAndIsActiveTrue(String hostFirstName,
			String hostMiddleName, String hostLastName);

	// find the list of host with similar settlement date and currently active
	@Aggregation(pipeline = { "{ '$match' : { 'hostId' : ?0,'isActive':true}}",
			"{ '$unwind' : {'path' :'$settlements'}}", "{ '$match' : { 'settlements.settlementDate' : ?1}}",
			"{ '$group' : { '_id' : '$_id', 'settlements' : { '$push' : '$settlements'}}}",
			"{ '$project' : { 'settlements' : 1}}" })
	Host findBySettlementMatching(String hostId, String settlementDate);

	// find the host with host id and currently active without settlement and wallet
	// details
	@Query(value = "{'_id': ?0, 'isActive': true}", fields = "{'settlements': 0, 'wallets': 0}")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	Host findByHostId(String hostId);

	@Query(value = "{'hostId':?0, 'isActive':true }", fields = "{'settlements':1}")
	Host findSettlementByHostId(String hostId);

}
