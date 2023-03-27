package com.vst.faq.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vst.faq.model.Faq;


@Repository
public interface FaqRepository extends MongoRepository<Faq, String>{

	// create method to find details by id and details are deleted or not
	public Faq findByFaqIdAndIsActiveTrue(String faqId);

	// create method to find all the available details and details are deleted or not
	public List<Faq> findAllByIsActiveTrue();
	
	public List<Faq> findByFaqQuestionAndIsActiveTrue(String faqQuestion);

	public List<Faq> findByFaqCategoryAndIsActiveTrue(String faqCategory);

	public List<Faq> findByFaqTypeAndIsActiveTrue(String faqType);

}
