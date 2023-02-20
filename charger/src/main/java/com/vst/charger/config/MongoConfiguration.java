package com.vst.charger.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

import com.vst.charger.model.Charger;


import javax.annotation.PostConstruct;

public class MongoConfiguration {

    @Autowired
    private MongoTemplate mongoTemplate;
    
    @PostConstruct
    public void initIndexes() {
        mongoTemplate.indexOps(Charger.class)
            .ensureIndex(new Index().on("chargerName",Sort.Direction.ASC));
    }
}
