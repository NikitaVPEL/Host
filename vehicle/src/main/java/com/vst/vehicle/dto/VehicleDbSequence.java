package com.vst.vehicle.dto;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "db_Sequence")
public class VehicleDbSequence {
	
	@Id
	private String id;
	private int seq;
	

}
