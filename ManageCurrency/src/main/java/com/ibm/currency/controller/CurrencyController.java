package com.ibm.currency.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.currency.model.Currency;
import com.ibm.currency.model.CurrencyDTO;
import com.ibm.currency.services.CurrencyService;

@RestController
public class CurrencyController {

	@Autowired
	CurrencyService currencyService;

	@RequestMapping(path = "/currency/{currencycode}", method = RequestMethod.GET)
	public ResponseEntity<CurrencyDTO> getCurrency(@PathVariable("currencycode") String currencyCode) {	
		CurrencyDTO currencyDTO = currencyService.getCurrency(currencyCode);
		if (currencyDTO == null) {
			ResponseEntity<CurrencyDTO> response = new ResponseEntity<CurrencyDTO>(HttpStatus.NOT_FOUND);
			return response;
		} else {
			ResponseEntity<CurrencyDTO> response = new ResponseEntity<CurrencyDTO>(currencyDTO, HttpStatus.FOUND);
			return response;
		}
	}

	@RequestMapping(path = "/currency/currencylist", method = RequestMethod.GET)
	public ResponseEntity<List<Currency>> getCurrencyAll() {
		List<Currency> list = currencyService.findAll();
		if (list == null) {
			ResponseEntity<List<Currency>> response = new ResponseEntity<List<Currency>>(HttpStatus.NOT_FOUND);
			return response;
		} else {
			ResponseEntity<List<Currency>> response = new ResponseEntity<List<Currency>>(list, HttpStatus.FOUND);
			return response;
		}
	}

	@RequestMapping(path = "/currency/{currencycode}/{currencyrate}/{countryname}", method = RequestMethod.GET)
	public ResponseEntity<Currency> saveCurrency(@PathVariable("currencycode") String currencyCode,
		@PathVariable("currencyrate") double currencyRate, @PathVariable("countryname") String countryName) {
		Currency currency = currencyService.saveCurrency(currencyCode, currencyRate, countryName);
		if (currency == null) {
			ResponseEntity<Currency> response = new ResponseEntity<Currency>(HttpStatus.NOT_FOUND);
			return response;
		} else {
			ResponseEntity<Currency> response = new ResponseEntity<Currency>(currency, HttpStatus.FOUND);
			return response;
		}
	}
	
	@RequestMapping(path = "/currency/{currencycode}/{currencyrate}", method = RequestMethod.GET)
	public ResponseEntity<Currency> updateCurrency(@PathVariable("currencycode") String currencyCode,
		@PathVariable("currencyrate") double currencyRate) {
		Currency currency = currencyService.saveCurrency(currencyCode, currencyRate, null);
		if (currency == null) {
			ResponseEntity<Currency> response = new ResponseEntity<Currency>(HttpStatus.NOT_FOUND);
			return response;
		} else {
			ResponseEntity<Currency> response = new ResponseEntity<Currency>(currency, HttpStatus.FOUND);
			return response;
		}
	}
	
	@RequestMapping(value = "/conversionfactor/{currencycode}", method = RequestMethod.GET)
	public CurrencyDTO getCurrencyCode(@PathVariable("currencycode") String currencyCode) {	
		CurrencyDTO currencyDTO = currencyService.getCurrency(currencyCode);	
		if (currencyDTO == null) {
			currencyDTO = new CurrencyDTO();
		}
		return currencyDTO;
	}

}
