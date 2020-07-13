package com.ibm.managecurrency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ibm.managecurrency.model.CurrencyDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

//@RibbonClient(name = "managecurrency")
@Service
@Component
public class ConvertCurrencyService {

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	LoadBalancerClient lbClient;

	@Bean
	@LoadBalanced
	RestTemplate createRestTemplate() {
		RestTemplateBuilder b = new RestTemplateBuilder();
		return b.build();
	}

	@Autowired
	@Lazy
	RestTemplate restTemplate;
	
	@Autowired
	ManageCurrencyProxy manageCurrencyProxy;
	
	@HystrixCommand(fallbackMethod = "getDefaultCurrencyValue")
	public ResponseEntity<CurrencyDTO> convertCurrency(String currencyCode, double conversionFactor) {	
		ResponseEntity<CurrencyDTO> response = manageCurrencyProxy.getCurrencyCode(currencyCode);
		CurrencyDTO currencyDTO = response.getBody();
		currencyDTO.setCurrencyRate(conversionFactor/currencyDTO.getCurrencyRate());
		return response;
	}
	
	public ResponseEntity<CurrencyDTO> getDefaultCurrencyValue(String currencyCode, double conversionFactor) {	
		ResponseEntity<CurrencyDTO> response = new ResponseEntity<CurrencyDTO>(null);
		CurrencyDTO currencyDTO = response.getBody();
		currencyDTO.setCurrencyRate(conversionFactor);
		return response;
	}


	/*
	 * public ProductDTO applyDiscount01(Product p) { DiscountRequest dRequest =
	 * createDiscountRequest(p);
	 * 
	 * List<ServiceInstance> instances = discoveryClient.getInstances("discountms");
	 * System.out.println("Instances of discountms found =" + instances.size()); for
	 * (ServiceInstance instance : instances) {
	 * System.out.println(instance.getHost() + ":" + instance.getPort()); }
	 * 
	 * ServiceInstance instance = instances.get(0); String url = "http://" +
	 * instance.getHost() + ":" + instance.getPort() + "/caldisc";
	 * System.out.println("Calling URL :" + url);
	 * 
	 * RestTemplate restTemplate = new RestTemplate(); HttpEntity<DiscountRequest>
	 * discountHttpEntity = new HttpEntity<DiscountRequest>(dRequest);
	 * ResponseEntity<DiscountResponse> dResponseEntity = restTemplate.exchange(url,
	 * HttpMethod.POST, discountHttpEntity, DiscountResponse.class);
	 * 
	 * DiscountResponse dResponse = dResponseEntity.getBody(); return
	 * ceateProductResponseDTO(dResponse, p);
	 * 
	 * }
	 */
	/*
	 * public ProductDTO applyDiscount02(Product p) { DiscountRequest dRequest =
	 * createDiscountRequest(p); ServiceInstance instance =
	 * lbClient.choose("discountms"); String url = "http://" + instance.getHost() +
	 * ":" + instance.getPort() + "/caldisc";
	 * 
	 * RestTemplate restTemplate = new RestTemplate(); HttpEntity<DiscountRequest>
	 * discountHttpEntity = new HttpEntity<DiscountRequest>(dRequest);
	 * ResponseEntity<DiscountResponse> dResponseEntity = restTemplate.exchange(url,
	 * HttpMethod.POST, discountHttpEntity, DiscountResponse.class);
	 * 
	 * DiscountResponse dResponse = dResponseEntity.getBody(); return
	 * ceateProductResponseDTO(dResponse, p);
	 * 
	 * }
	 * 
	 * public ProductDTO applyDiscount03(Product p) { DiscountRequest dRequest =
	 * createDiscountRequest(p);
	 * 
	 * HttpEntity<DiscountRequest> discountHttpEntity = new
	 * HttpEntity<DiscountRequest>(dRequest);
	 * 
	 * ResponseEntity<DiscountResponse> dResponseEntity =
	 * lbrestTemplate.exchange("http://discountms/caldisc", HttpMethod.POST,
	 * discountHttpEntity, DiscountResponse.class);
	 * 
	 * DiscountResponse dResponse = dResponseEntity.getBody(); return
	 * ceateProductResponseDTO(dResponse, p);
	 * 
	 * }
	 * 
	 * @HystrixCommand(fallbackMethod = "discountfallback") public ProductDTO
	 * applyDiscount04(Product p) { DiscountRequest dRequest =
	 * createDiscountRequest(p);
	 * 
	 * HttpEntity<DiscountRequest> discountHttpEntity = new
	 * HttpEntity<DiscountRequest>(dRequest);
	 * 
	 * ResponseEntity<DiscountResponse> dResponseEntity =
	 * lbrestTemplate.exchange("http://discountms/caldisc", HttpMethod.POST,
	 * discountHttpEntity, DiscountResponse.class);
	 * 
	 * DiscountResponse dResponse = dResponseEntity.getBody(); return
	 * ceateProductResponseDTO(dResponse, p);
	 * 
	 * }
	 * 
	 */	
}
