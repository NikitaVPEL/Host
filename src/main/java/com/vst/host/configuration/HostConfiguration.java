//package com.vst.host.configuration;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.index.Index;
//import org.springframework.stereotype.Component;
//import org.springframework.data.domain.Sort;
//
//
//@Component
//public class HostConfiguration {
//
//	@Autowired
//	MongoTemplate mongoTemplate;
//	
//	 @PostConstruct
//	    public void createIndexes() {
//	        mongoTemplate.indexOps("host")
//	            .ensureIndex(new Index().on("hostId", Sort.Direction.ASC));
//	    }
//	 
//	 @PostConstruct
//	    public void createIndexes1() {
//	        mongoTemplate.indexOps("host")
//	            .ensureIndex(new Index().on("hostFirstName", Sort.Direction.ASC));
//	    }
//	 
//	 @PostConstruct
//	    public void createIndexes2() {
//	        mongoTemplate.indexOps("host")
//	            .ensureIndex(new Index().on("hostLastName", Sort.Direction.ASC));
//	    }
//	
//	 
//	 @PostConstruct
//	    public void createIndexes4() {
//	        mongoTemplate.indexOps("host")
//	            .ensureIndex(new Index().on("hostEmail", Sort.Direction.ASC));
//	    }
//	 
//	 @PostConstruct
//	    public void createIndexes5() {
//	        mongoTemplate.indexOps("host")
//	            .ensureIndex(new Index().on("hostCity", Sort.Direction.ASC));
//	    }
//
//}
