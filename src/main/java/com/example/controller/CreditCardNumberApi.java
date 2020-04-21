package com.example.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * クレジットカード番号から会社を特定するAPI.
 * 
 * @author nonaa
 *
 */
@RestController
@RequestMapping(value="")
public class CreditCardNumberApi {
	
	//AMEX
	public static final String REG_AMEX_FULL = "^3[47][0-9]{13}$";

	//DINERS
	public static final String REG_DINERS_FULL= "^3((?:0[0-5]|[689][0-9])[0-9]{11}|095[0-9]{10})$";

	//JCB
	public static final String REG_JCB_FULL = "^(?:2131|1800|35\\d{3})\\d{11}$";

	//VISA
	public static final String REG_VISA_FULL = "^4[0-9]{15}$";

	//MC
	public static final String REG_MASTER_FULL = "^5[1-5][0-9]{14}$";
	
	@RequestMapping(value="/check_credit_number",method = RequestMethod.POST)
	public Map<String, String> checkCreditNumber(String creditNumber){
		Map<String, String> map = new HashMap<>();
		String companyName = null;
		
		Pattern amexPattern = Pattern.compile(REG_AMEX_FULL);
		Pattern dinersPattern = Pattern.compile(REG_DINERS_FULL);
		Pattern jcbPattern = Pattern.compile(REG_JCB_FULL);
		Pattern visaPattern = Pattern.compile(REG_VISA_FULL);
		Pattern masterPattern = Pattern.compile(REG_MASTER_FULL);
		
		Matcher amexMatcher = amexPattern.matcher(creditNumber);
		Matcher dinersMatcher = dinersPattern.matcher(creditNumber);
		Matcher jcbMatcher = jcbPattern.matcher(creditNumber);
		Matcher visaMatcher = visaPattern.matcher(creditNumber);
		Matcher masterMatcher = masterPattern.matcher(creditNumber);	

		if(amexMatcher.matches()) {
			companyName = "：AMEX";
		}else if(dinersMatcher.matches()) {
			companyName = "：DINERS";
		}else if(jcbMatcher.matches()) {
			companyName = "：JCB";  
		}else if(visaMatcher.matches()) {
			companyName = "：VISA";
		}else if(masterMatcher.matches()) {
			companyName = "：MASTER";
		}else {
			companyName = "：Don't exist";
		}
		map.put("companyName", companyName);
		return map;
	}
	
}
