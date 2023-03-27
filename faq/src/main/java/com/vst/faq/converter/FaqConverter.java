package com.vst.faq.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import com.vst.faq.dto.FaqDto;
import com.vst.faq.model.Faq;


@Component
public class FaqConverter {
	
	public Faq dtoToEntity(FaqDto faqDto) {
		Faq faq = new Faq();
		BeanUtils.copyProperties(faqDto, faq);
		return faq;
	}
	
	public FaqDto entityToDto(Faq faq) {
		FaqDto faqDto = new FaqDto();
		BeanUtils.copyProperties(faq, faqDto);
		return faqDto;
	}

}
