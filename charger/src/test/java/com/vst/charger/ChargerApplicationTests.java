//package com.vst.charger;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.vst.charger.model.Charger;
//import com.vst.charger.repository.ChargerRepository;
//import com.vst.charger.service.ChargerServiceImpl;
//
//@SpringBootTest
//class FaqApplicationTests {
//	
//	@Autowired
//	ChargerRepository repository;
//	
//	@Autowired
//	ChargerServiceImpl service;
//
//	@Test
//	public void saveUserTest() {
//		Charger charger = new Charger();
//		when(repository.save(charger)).thenReturn(charger);
//		assertEquals(charger, service.addUser(charger));
//	}
//
//}
