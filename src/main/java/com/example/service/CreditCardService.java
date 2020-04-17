package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.domain.CreditCard;
import com.example.form.CreditCardForm;

/**
 * クレジットカードの情報のやりとりをAPIと行うサービス.
 * 
 * @author nonaa
 *
 */
@Service
@Transactional
public class CreditCardService {

	@Autowired
	private RestTemplate restTemplate;

	private static final String URL = "http://153.126.174.131:8080/sample-credit-card-web-api/credit-card/payment";

	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	public CreditCard getCreditCard( CreditCardForm creditCardForm) {
        return restTemplate.postForObject(URL,creditCardForm, CreditCard.class);
    }
}
