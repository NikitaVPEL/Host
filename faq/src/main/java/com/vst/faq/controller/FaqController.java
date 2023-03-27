package com.vst.faq.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vst.faq.dto.FaqDto;
import com.vst.faq.model.Faq;
import com.vst.faq.service.FaqServiceImpl;

@RestController
@RequestMapping("/vst1")
public class FaqController {


	@Autowired
	private FaqServiceImpl faqServiceImpl;

	@PostMapping("/faq")
	public ResponseEntity<String> addFaq(@Valid @RequestBody FaqDto faqDto) {
		
		faqServiceImpl.add(faqDto);
		return new ResponseEntity<>("Data saved Successfully", HttpStatus.OK);
	}

	@GetMapping("/faq")
	public ResponseEntity<Faq> getFAQ(@RequestParam("faqId") String faqId) {
				return ResponseEntity.ok(faqServiceImpl.show(faqId));
	}

	@GetMapping("/faqs")
	public ResponseEntity<List<Faq>> getAllFAQ() {
		
		return ResponseEntity.ok(faqServiceImpl.showAll());
	}


	@PutMapping("/faq")
	public ResponseEntity<String> updateFaq(@Valid @RequestParam("faqId") String faqId, @RequestBody FaqDto faqDto) {

		faqServiceImpl.edit(faqId, faqDto);
		return new ResponseEntity<>("Details updated sucessfully", HttpStatus.OK);	
	}

	@DeleteMapping("/faq")
	public ResponseEntity<String> deleteFAQ(@RequestParam("faqId") String faqId) {

		faqServiceImpl.remove(faqId);
		return new ResponseEntity<>("faq deleted successfully", HttpStatus.OK);

	}
	
	@GetMapping("faqQuestion")
	public ResponseEntity<List<Faq>> getByFaqQuestion(@RequestParam("faqQuestion") String faqQuestion) {
		return ResponseEntity.ok(faqServiceImpl.showByFaqQuestion(faqQuestion));
	}
	
	@GetMapping("faqCategory")
	public ResponseEntity<List<Faq>> getByFaqCategory(@RequestParam("faqCategory") String faqCategory) {
		return ResponseEntity.ok(faqServiceImpl.showByFaqCategory(faqCategory));
	}
	
	@GetMapping("faqType")
	public ResponseEntity<List<Faq>> getByfaqType(@RequestParam("faqType") String faqType) {
		return ResponseEntity.ok(faqServiceImpl.showByFaqType(faqType));
	}
}
