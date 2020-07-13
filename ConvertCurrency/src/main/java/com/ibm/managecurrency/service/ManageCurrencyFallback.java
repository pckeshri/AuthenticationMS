package com.ibm.managecurrency.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ibm.managecurrency.model.CurrencyDTO;

@Component
public class ManageCurrencyFallback implements ManageCurrencyProxy {

	@Override
	public ResponseEntity<CurrencyDTO> getCurrencyCode(String currencyCode) {
		// TODO Auto-generated method stub
		return null;//ResponseEntity.new CurrencyDTO().setCountryName("Fallback : Method getCurrency() calling....");
	}

	@Override
	public ResponseEntity<CurrencyDTO> saveCurrency(String currencyCode, double currencyRate, String countryName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<CurrencyDTO>> getCurrencyAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<CurrencyDTO> updateCurrency(String currencyCode, double currencyRate) {
		// TODO Auto-generated method stub
		return null;
	}

}
