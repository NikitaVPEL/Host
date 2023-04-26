package com.vst.host.utils;

/**
* utility to set currunt date and time / sanitize the query / convert string into tital case 
* its a common class used in various classes and packages. 
*
* @author Nikita Chakole <nikita.chakole@vpel.in>
* @since  21/12/2022
*/

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {

	/**
	 * Usage : to sanitize the query or query validation
	 * 
	 * @param input
	 * @return input but remove all characters except letters and digits
	 */
	public String sanitize(String input) {
		String sanitizeInput = input.replaceAll("[^a-zA-Z0-9]", ""); // remove all characters except letters and digits}
		return sanitizeInput;
	}

	/**
	 * Usage : used to change the string into to titale case
	 * 
	 * @param input
	 * @return input but convert input into to title case
	 */
	public String toTitleCase(String input) {
		StringBuilder titleCase = new StringBuilder(input.length());
		boolean nextTitleCase = true;
		for (char c : input.toCharArray()) {
			if (Character.isSpaceChar(c)) {
				nextTitleCase = true;
			} else if (nextTitleCase) {
				c = Character.toTitleCase(c);
				nextTitleCase = false;
			}
			titleCase.append(c);
		}
		return titleCase.toString();
	}

	/**
	 * Usage : use to generate unique id with current date and time
	 * 
	 * @return id with pattern yyyyMMddHHmmssSSS
	 */
	public String idGenerator() {

		Date dNow = new Date();
		SimpleDateFormat idDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String idGen = idDateFormat.format(dNow);
		return idGen;
	}

	/**
	 * Usage : use to generate current date and time
	 * 
	 * @return current date in yyyy/MM/dd format
	 */
	public Date dateSetter() {
		Date dNow = new Date();
		return dNow;
	}
}
