package com.vst.faq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

import com.vst.faq.model.Faq;

import javax.annotation.PostConstruct;

public class FaqMongoConfiguration {

	@Autowired
	private MongoTemplate mongoTemplate;

	@PostConstruct
	public void faqQuestionIndexes() {
		mongoTemplate.indexOps(Faq.class).ensureIndex(new Index().on("faqQuestion", Sort.Direction.ASC));
	}

	@PostConstruct
	public void faqCategoryIndexes() {
		mongoTemplate.indexOps(Faq.class).ensureIndex(new Index().on("faqCategory", Sort.Direction.ASC));
	}

	@PostConstruct
	public void faqTypeIndexes() {
		mongoTemplate.indexOps(Faq.class).ensureIndex(new Index().on("faqType", Sort.Direction.ASC));
	}

}
