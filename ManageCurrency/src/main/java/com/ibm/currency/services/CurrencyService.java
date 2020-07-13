package com.ibm.currency.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ibm.currency.model.Currency;
import com.ibm.currency.model.CurrencyDTO;
import com.ibm.currency.repo.CurrencyRepository;

@Service
@Component
public class CurrencyService {
	
	@Autowired
	private CurrencyRepository currencyRepo;
	
	public CurrencyDTO getCurrency(String currencyCode) {
		Optional<Currency> currency = currencyRepo.findById(currencyCode);
		CurrencyDTO currencyDTO = new CurrencyDTO();
		if (currency.isPresent()) {
			currencyDTO.setCountryName(currency.get().getCountryName());
			currencyDTO.setCurrencyRate(currency.get().getCurrencyRate());
			currencyDTO.setCurrencyName(currency.get().getCurrencyCode());
		}
		return currencyDTO;
	}
	
	public List<Currency> findAll() {
		List<Currency> list = currencyRepo.findAll();
		if(list == null) {
			list = new ArrayList<Currency>();
		}
		return list;
	
	}
	
	public Currency saveCurrency(String currencyCode, double currencyRate, String countryName) {
		Currency currency = new Currency();
		CurrencyDTO currencyDTO = getCurrency(currencyCode);
		currency.setCurrencyCode(currencyCode.toUpperCase());
		currency.setCurrencyRate(currencyRate);
		if(countryName == null && currencyDTO.getCountryName() != null) {
			currency.setCountryName(currencyDTO.getCountryName());
		} else {
			currency.setCountryName(countryName);
		}
		currency = currencyRepo.save(currency);
		return currency;
	}

}
