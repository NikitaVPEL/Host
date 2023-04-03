package com.vst.host.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import com.vst.host.dto.HostDto;
import com.vst.host.model.HostDbSequence;

@Service
public class HostSequenceGeneratorService {

	@Autowired
	private MongoOperations mongoOperations; // perform mongo operation

	public int getSequenceNumber(String sequenceName) { // whatever pass through SEQUENCE_NAME="host_sequense
		Query query = new Query(Criteria.where("id").is(sequenceName)); // get sequence no

		Update update = new Update().inc("seq", 1); // update the sequence no

		HostDbSequence counter = mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(true),
				HostDbSequence.class); // modify in document

		return !Objects.isNull(counter) ? counter.getSeq() : 1;
	}
	
	public String getGenratedId() {
		String number = "";
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("ddMMyyyy_hhmmss");
		int num =getSequenceNumber(HostDto.SEQUENCE_NAME);
		if(num>=1 && num<=9) 
			number = number + "000000" + num;
		else if(num>=10 && num<=99)
			number = number + "00000" + num;
		else if(num>=100 && num<=999)
			number = number + "0000" + num;
		else if(num>=1000 && num<=9999)
			number = number + "0000" + num;
		else if(num>=10000 && num<=99999)
			number = number + "000" + num;
		else if(num>=100000 && num<=999999)
			number = number + "00" + num;
		else if(num>=100000 && num<=9999999)
			number = number + "0" + num;
		else if(num>1000000 && num<=99999999)
			number = number + "" + num;
		
		return "STL"+ft.format(dNow)+ number;

		
		
	}
}
