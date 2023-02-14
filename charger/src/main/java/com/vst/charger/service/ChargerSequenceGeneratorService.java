package com.vst.charger.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.vst.chargerdto.ChargerDto;
import com.vst.chargerdto.ChargerDbSequence;

@Service
public class ChargerSequenceGeneratorService {
	
	 @Autowired
	    private MongoOperations mongoOperations;
	
	 public int getSequenceNumber(String sequenceName) {
		 
		 // by this we get sequence name or number
		 Query query = new Query(Criteria.where("id").is(sequenceName));
		 // it will update the sequence no. by 1
		 Update update =new Update().inc("seq", 1);
		 
		 ChargerDbSequence counter = mongoOperations
	                .findAndModify(query,
	                        update, options().returnNew(true).upsert(true),
	                        ChargerDbSequence.class);
		 
		 return !Objects.isNull(counter) ? counter.getSeq() : 1;
		 
	 }
	 
	 public String idGenerator() {
			Date dNow = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy_HHmmss");
			return dateFormat.format(dNow) + "_" + getSequenceNumber(ChargerDto.SEQUENCE_NAME);
			
	 }

}
