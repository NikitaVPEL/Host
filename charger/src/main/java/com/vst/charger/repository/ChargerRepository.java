package com.vst.charger.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.vst.charger.model.Charger;

/**
 * @Repository to indicate that the class provides the mechanism for storage,
 *             retrieval, search, update and delete operation on objects.
 */

public interface ChargerRepository extends MongoRepository<Charger, String> {

	// create method to find details by id and details are deleted or not
	Charger findByChargerIdAndIsActiveTrue(String chargerId);

	// create method to find all the available details and details are deleted or
	// not
	List<Charger> findAllByIsActiveTrue();

	public Charger findByChargerName(String chargerName);

}
