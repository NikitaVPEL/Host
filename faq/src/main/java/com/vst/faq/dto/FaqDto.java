package com.vst.faq.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FaqDto {

	@Transient
	public static final String SEQUENCE_NAME = "faq";
	
	@Id
	private String faqId;
	
	@NotBlank(message = "faqQuestion should not be Blank")
	@NotNull(message = "faqQuestion should not be NULL")
	private String faqQuestion;
	
	@NotBlank(message = "faqAnswer should not be Blank")
	@NotNull(message = "faqAnswer should not be NULL")
	private String faqAnswer;
	
	@NotBlank(message = "faqCategory should not be Blank")
	@NotNull(message = "faqCategory should not be NULL")
	private String faqCategory;
	
	@NotBlank(message = "faqType should not be Blank")
	@NotNull(message = "faqType should not be NULL")
	private String faqType;
	
	
	private String createdDate;
	
	private String modifiedDate;
	
	@NotBlank(message = "chargerName should not be Blank")
	@NotNull(message = "chargerName should not be NULL")
	private String createdBy;
	
	@NotBlank(message = "chargerName should not be Blank")
	@NotNull(message = "chargerName should not be NULL")
	private String modifiedBy;
	
	private boolean isActive;

}
