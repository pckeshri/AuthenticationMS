package com.ibm.managecurrency.service;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ibm.managecurrency.model.CurrencyDTO;


@RibbonClient(name="managecurrency")
@FeignClient(name = "managecurrency", url="http://localhost:8302/conversionfactor", fallback = ManageCurrencyFallback.class)
public interface ManageCurrencyProxy {
	
	@RequestMapping(value = "/{currencycode}", method = RequestMethod.GET)
	public ResponseEntity<CurrencyDTO> getCurrencyCode(@PathVariable("currencycode") String currencyCode);
	
	@RequestMapping(path = "currency/{currencycode}/{currencyrate}/{countryname}", method = RequestMethod.GET)
	public ResponseEntity<CurrencyDTO> saveCurrency(@PathVariable("currencycode") String currencyCode,
		@PathVariable("currencyrate") double currencyRate, @PathVariable("countryname") String countryName);

	@RequestMapping(path = "currency/currencylist", method = RequestMethod.GET)
	public ResponseEntity<List<CurrencyDTO>> getCurrencyAll();

	@RequestMapping(path = "currency/{currencycode}/{currencyrate}", method = RequestMethod.GET)
	public ResponseEntity<CurrencyDTO> updateCurrency(@PathVariable("currencycode") String currencyCode,
			@PathVariable("currencyrate") double currencyRate);

}
