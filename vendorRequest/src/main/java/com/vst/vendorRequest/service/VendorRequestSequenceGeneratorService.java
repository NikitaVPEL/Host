package com.vst.vendorRequest.service;

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

import com.vst.vendorRequest.dto.VendorRequestDbSequence;
import com.vst.vendorRequest.dto.VendorRequestDto;

@Service
public class VendorRequestSequenceGeneratorService {
	
	 @Autowired
	    private MongoOperations mongoOperations;
	
	 public int getSequenceNumber(String sequenceName) {
		 
		 // by this we get sequence name or number
		 Query query = new Query(Criteria.where("id").is(sequenceName));
		 // it will update the sequence no. by 1
		 Update update =new Update().inc("seq", 1);
		 
		 VendorRequestDbSequence counter = mongoOperations
	                .findAndModify(query,
	                        update, options().returnNew(true).upsert(true),
	                        VendorRequestDbSequence.class);
		 
		 return !Objects.isNull(counter) ? counter.getSeq() : 1;
		 
	 }
	 
	 public String idGenerator() {
			String numberData = "";
			Date dNow = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
			int num = getSequenceNumber(VendorRequestDto.SEQUENCE_NAME);
			if (num >= 1 && num <= 9)
				numberData = numberData + "000000" + num;
			else if (num >= 10 && num <= 99)
				numberData = numberData + "00000" + num;
			else if (num >= 100 && num <= 999)
				numberData = numberData + "0000" + num;
			else if (num >= 1000 && num <= 9999)
				numberData = numberData + "000" + num;
			else if (num >= 10000 && num <= 99999)
				numberData = numberData + "00" + num;
			else if (num >= 10000 && num <= 999999)
				numberData = numberData + "0" + num;
			else if (num >= 10000 && num <= 9999999)
				numberData = numberData + "" + num;
			return "VRQ" + dateFormat.format(dNow) + numberData;
	 }

}
