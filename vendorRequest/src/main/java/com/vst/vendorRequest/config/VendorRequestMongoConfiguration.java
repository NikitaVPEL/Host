package com.vst.vendorRequest.config;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

import com.vst.vendorRequest.model.VendorRequest;


public class VendorRequestMongoConfiguration {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@PostConstruct
	public void vendorRequestHostIdIndexes() {
		mongoTemplate.indexOps(VendorRequest.class).ensureIndex(new Index().on("vendorRequestHostId", Sort.Direction.ASC));
	}

	@PostConstruct
	public void vendorRequestTypeIndexes() {
		mongoTemplate.indexOps(VendorRequest.class).ensureIndex(new Index().on("vendorRequestType", Sort.Direction.ASC));
	}

	@PostConstruct
	public void vendorRequestLocationIndexes() {
		mongoTemplate.indexOps(VendorRequest.class).ensureIndex(new Index().on("vendorRequestLocation", Sort.Direction.ASC));
	}

	@PostConstruct
	public void vendorRequestDateIndexes() {
		mongoTemplate.indexOps(VendorRequest.class).ensureIndex(new Index().on("vendorRequestDate", Sort.Direction.ASC));
	}

}
