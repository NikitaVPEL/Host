package com.vst.faq.service;

import java.util.List;
import com.vst.faq.dto.FaqDto;
import com.vst.faq.model.Faq;


public interface FaqServiceInterface {
	
	public String add(FaqDto faqDto);
	
	public Faq show(String faqId);
	
	public List<Faq> showAll();
	
	public void remove(String faqId);
	
	public void edit(String faqId, FaqDto faqDto);
	
	public List<Faq> showByFaqQuestion(String faqQuestion);

	public List<Faq> showByFaqCategory(String faqCategory);
	
	public List<Faq> showByFaqType(String faqType);
}
