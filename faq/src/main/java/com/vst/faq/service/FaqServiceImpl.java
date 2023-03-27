package com.vst.faq.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vst.faq.converter.FaqConverter;
import com.vst.faq.dto.FaqDto;
import com.vst.faq.exception.FaqNotFoundException;
import com.vst.faq.model.Faq;
import com.vst.faq.exception.FaqIdNotAcceptableException;
import com.vst.faq.repository.FaqRepository;

@Service
public class FaqServiceImpl implements FaqServiceInterface {

	@Autowired
	FaqRepository faqRepository;

	@Autowired
	FaqConverter faqConverter;

	@Autowired
	FaqSequenceGeneratorService faqSequenceGeneratorService;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();

	@Override
	@Transactional
	public String add(FaqDto faqDto) {

		Faq faq = faqConverter.dtoToEntity(faqDto);
		faq.setFaqId(faqSequenceGeneratorService.idGenerator());
		faq.setCreatedDate(dateFormat.format(date));
		faq.setModifiedDate(dateFormat.format(date));
		faq.setActive(true);
		faqRepository.save(faq);
		return "Data stored successfully";

	}

	@Override
	@Transactional
	public Faq show(String faqId) {

		if (!faqId.isBlank()) {

			Faq faq = faqRepository.findByFaqIdAndIsActiveTrue(faqId);
			if (faq != null) {

				return faq;
			} else {

				throw new FaqNotFoundException("data of given id is not available in the database");
			}
		} else {
			throw new FaqIdNotAcceptableException("entered id is null or not valid ,please enter correct id");
		}
	}

	@Override
	@Transactional
	public List<Faq> showAll() {

		List<Faq> list = faqRepository.findAllByIsActiveTrue();
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new FaqNotFoundException("there is no data available in database");
		}
	}

	@Override
	@Transactional
	public void edit(String faqId, FaqDto faqDto) {

		if (!faqId.isBlank()) {
			Faq faq = faqConverter.dtoToEntity(faqDto);
			Faq obj = faqRepository.findByFaqIdAndIsActiveTrue(faqId);
			if (obj != null) {

				if (faq.getFaqQuestion() != null)
					if (!faq.getFaqQuestion().isBlank())
						obj.setFaqQuestion(faq.getFaqQuestion());

				if (faq.getFaqAnswer() != null)
					if (!faq.getFaqAnswer().isBlank())
						obj.setFaqAnswer(faq.getFaqAnswer());

				if (faq.getFaqCategory() != null)
					if (!faq.getFaqCategory().isBlank())
						obj.setFaqCategory(faq.getFaqCategory());

				if (faq.getFaqType() != null)
					if (!faq.getFaqType().isBlank())
						obj.setFaqType(faq.getFaqType());

						obj.setModifiedDate(dateFormat.format(date));

				if (faq.getCreatedBy() != null)
					if (!faq.getCreatedBy().isBlank())
						obj.setCreatedBy(faq.getCreatedBy());

				if (faq.getModifiedBy() != null)
					if (!faq.getModifiedBy().isBlank())
						obj.setModifiedBy(faq.getModifiedBy());

				faqRepository.save(obj);
			} else {
				throw new FaqNotFoundException("data of given id is not available in the database");
			}
		} else {
			throw new FaqIdNotAcceptableException("entered id is null or not valid ,please enter correct id");

		}
	}

	@Override
	@Transactional
	public void remove(String faqId) {

		if (!faqId.isBlank()) {

			Faq faq = faqRepository.findByFaqIdAndIsActiveTrue(faqId);
			if (faq != null) {
				faq.setActive(false);
				faqRepository.save(faq);
			} else {
				throw new FaqNotFoundException("Faq is not found in database, either it is deleted or not available");
			}
		} else {
			throw new FaqIdNotAcceptableException("given id is not correct, it is null or not valid");
		}
	}

	@Override
	public List<Faq> showByFaqQuestion(String faqQuestion) {
		if (!faqQuestion.isBlank()) {

			List<Faq> faq = faqRepository.findByFaqQuestionAndIsActiveTrue(faqQuestion);
			if (faq != null) {

				return faq;
			} else {
				throw new FaqNotFoundException("data of given id is not available in the database");
			}
		} else {
			throw new FaqIdNotAcceptableException("entered Question is null or not valid ,please enter correct id");
		}
	}

	@Override
	public List<Faq> showByFaqCategory(String faqCategory) {
		if (!faqCategory.isBlank()) {

			List<Faq> faq = faqRepository.findByFaqCategoryAndIsActiveTrue(faqCategory);
			if (faq != null) {

				return faq;
			} else {
				throw new FaqNotFoundException("data of given id is not available in the database");
			}
		} else {
			throw new FaqIdNotAcceptableException("entered Category is null or not valid ,please enter correct id");
		}
	}

	@Override
	public List<Faq> showByFaqType(String faqType) {
		if (!faqType.isBlank()) {

			List<Faq> faq = faqRepository.findByFaqTypeAndIsActiveTrue(faqType);
			if (faq != null) {

				return faq;
			} else {

				throw new FaqNotFoundException("data of given id is not available in the database");
			}
		} else {
			throw new FaqIdNotAcceptableException("entered Type is null or not valid ,please enter correct id");
		}

	}

}
