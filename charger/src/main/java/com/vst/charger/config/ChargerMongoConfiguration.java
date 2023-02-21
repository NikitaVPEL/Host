package com.vst.charger.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

import com.vst.charger.model.Charger;

import javax.annotation.PostConstruct;

public class ChargerMongoConfiguration {

	@Autowired
	private MongoTemplate mongoTemplate;

	@PostConstruct
	public void chargerNameIndexes() {
		mongoTemplate.indexOps(Charger.class).ensureIndex(new Index().on("chargerName", Sort.Direction.ASC));
	}

	@PostConstruct
	public void chargerInputVoltageIndexes() {
		mongoTemplate.indexOps(Charger.class).ensureIndex(new Index().on("chargerInputVoltage", Sort.Direction.ASC));
	}

	@PostConstruct
	public void chargerOutputVoltageIndexes() {
		mongoTemplate.indexOps(Charger.class).ensureIndex(new Index().on("chargerOutputVoltage", Sort.Direction.ASC));
	}

	@PostConstruct
	public void chargerMountTypeIndexes() {
		mongoTemplate.indexOps(Charger.class).ensureIndex(new Index().on("chargerMountType", Sort.Direction.ASC));
	}
}
