package com.vst.faq.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "Faq")
public class Faq {
	
	@Id
	private String faqId;
	@Indexed
	private String faqQuestion;
	private String faqAnswer;
	@Indexed
	private String faqCategory;
	@Indexed
	private String faqType;
	private String createdDate;
	private String modifiedDate;
	private String createdBy;
	private String modifiedBy;
	private boolean isActive;


}
