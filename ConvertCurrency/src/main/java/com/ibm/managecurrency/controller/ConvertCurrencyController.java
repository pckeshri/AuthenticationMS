package com.ibm.managecurrency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.managecurrency.model.CurrencyDTO;
import com.ibm.managecurrency.service.ConvertCurrencyService;

@RestController
@RequestMapping(path = "/convertcurrency")
public class ConvertCurrencyController {

	@Autowired
	ConvertCurrencyService convertCurrencyService;
	
	@RequestMapping(path = "/{currencycode}/{currencyrate}", method = RequestMethod.GET)
	public ResponseEntity<CurrencyDTO> convertCurrency(@PathVariable("currencycode") String currencyCode,
		@PathVariable("currencyrate") double conversionRate) {
		ResponseEntity<CurrencyDTO> currencyDTO = convertCurrencyService.convertCurrency(currencyCode, conversionRate);
		return currencyDTO;
	}

}
