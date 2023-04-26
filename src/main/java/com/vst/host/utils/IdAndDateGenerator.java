package com.vst.host.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IdAndDateGenerator {
	

	public String idGenerator() {

		Date dNow = new Date();
		SimpleDateFormat idDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String idGen = idDateFormat.format(dNow);
		return idGen;
	}

	public String dateSetter() {
		Date dNow = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String date = dateFormat.format(dNow);
		return date;
	}

}
