package com.vst.charger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.vst.charger.converter.ChargerConverter;
import com.vst.charger.dto.ChargerDto;
import com.vst.charger.model.Charger;
import com.vst.charger.repository.ChargerRepository;
import com.vst.charger.service.ChargerServiceImpl;

@SpringBootTest
 class ChargerApplicationTests {

		
	@MockBean
	ChargerRepository repository;
		
		@Autowired
		ChargerServiceImpl service;
		
		@Autowired
		ChargerConverter converter;

		@Test
		 void saveChargerTest() {
			Charger charger1 = new Charger();
		    charger1.setChargerId("C001");
		    charger1.setChargerName("Fast Charger");
		    charger1.setChargerInputVoltage("100V");
		    charger1.setChargerOutputVoltage("200V");
		    charger1.setChargerMinInputAmpere("5A");
		    charger1.setChargerMaxInputAmpere("10A");
		    charger1.setChargerOutputAmpere("7A");
		    charger1.setChargerInputFrequency("50Hz");
		    charger1.setChargerOutputFrequency("60Hz");
		    charger1.setChargerIPRating("IP55");
		    charger1.setChargerMountType("Wall Mount");
		    charger1.setIsRFID("Yes");
		    charger1.setIsAppSupport("Yes");
		    charger1.setIsTBCutOff("No");
		    charger1.setIsAntitheft("No");
		    charger1.setIsLEDDisplay("Yes");
		    charger1.setIsLEDIndications("Yes");
		    charger1.setIsSmart("No");
		    charger1.setCreatedBy("User1");
		    charger1.setModifiedBy("User1");
		    charger1.setActive(true);
		    ChargerDto chargerDto = converter.entityToDto(charger1);
		    when(repository.save(charger1)).thenReturn(charger1);
			assertEquals("charger saved successfully", service.add(chargerDto));
		
		}
		
		@Test
		void getAllChargerTest() {
			when(repository.findAllByIsActiveTrue()).thenReturn(Stream
					.of(new Charger("C001","Fast Charger","100V","200V","5A","10A","7A","50Hz",
							"60Hz","IP55","Wall Mount","Yes","Yes","No","No","Yes","Yes",
							"No","User1","User1","User1","User1",true), new Charger("C001","Fast Charger","100V","200V","5A","10A","7A","50Hz",
							"60Hz","IP55","Wall Mount","Yes","Yes","No","No","Yes","Yes",
								"No","User1","User1","User1","User1",true)).collect(Collectors.toList()));
			assertEquals(2, service.showAll().size());
		
		}
		
//		@Test
//		void getChargerIdTest() {
//			String  chargerId = "02082023142335_1";
//			Charger charger = new Charger("C001","Fast Charger","100V","200V","5A","10A","7A","50Hz",
//					"60Hz","IP55","Wall Mount","Yes","Yes","No","No","Yes","Yes",
//					"No","User1","User1","User1","User1",true);
//			
//			when(repository.findByChargerIdAndIsActiveTrue(chargerId))
//			.thenReturn(Stream.of(new Charger(charger)).collect(Collectors.toList()));
//			
//
//	}
		
//		@Test
//		void deleteChargerTest() {
//			Charger charger = new Charger("C001","Fast Charger","100V","200V","5A","10A","7A","50Hz","60Hz","IP55","Wall Mount","Yes","Yes","No","No","Yes","Yes",
//"No","User1","User1","User1","User1",true);
//			
//			
//			
//			
//		}
}

