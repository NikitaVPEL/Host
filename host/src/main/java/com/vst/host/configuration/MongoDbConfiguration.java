package com.vst.host.configuration;

import javax.annotation.PreDestroy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mongodb.MongoClient;

@Configuration
public class MongoDbConfiguration {

	public static final Logger logger = LogManager.getLogger(MongoDbConfiguration.class);

	private MongoClient mongoClient;

	@Bean
	public MongoClient mongoClient() {
		if (mongoClient == null) {
			logger.info("Creating new MongoDB client connection..."); 
			mongoClient = new MongoClient();
		}
		return mongoClient;
	}

	@PreDestroy
	public void cleanUp() {
		logger.info("Closing MongoDB client connection...");
		if (mongoClient != null) {
			mongoClient.close();
		}
	}
}
