package com.ibm.loginservice.controller;

import com.ibm.loginservice.entity.TokenObj;
import com.ibm.loginservice.entity.User;
import com.ibm.loginservice.util.JwtUtil;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;
    @Value("${grant_type}")
    private String grant_type;
    
    @PostMapping("/authenticate")
    public TokenObj generateToken(@RequestBody User user) throws Exception {
        try {
        	
    		System.out.println("Going to Call IBM IAM Service....");
    		RestTemplate restTemplate = new RestTemplate();
    		HttpHeaders headers = new HttpHeaders();
    		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    		String access_token_url = "https://iam.cloud.ibm.com/identity/token";
    		MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
    		requestBody.add("grant_type", grant_type);
    		requestBody.add("apikey", user.getApikey());
    	
    		//requestBody.add("apikey", "elVm1Ok13Ek97PjCVvLWjmeHy_6j_qMoEDfBHOiDmWX6");
    		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(requestBody, headers);
    		Object loginresponse = restTemplate.postForObject(access_token_url, httpEntity, Object.class);
    		System.out.println("Got Success Rest Response : " + loginresponse.toString());
    		if(loginresponse == null)
    			return null;
    	
    		Map<String, Object> map = (Map<String, Object>)loginresponse;
    		map.forEach((k, v) -> System.out.println(k+": "+v));
    		
        	String accessToken = (String) map.get("access_token");
        	//Generate Token
        	String transactionToken=jwtUtil.generateToken(accessToken); 
        	String serviceToken=jwtUtil.generateToken(accessToken);
        	
        	//Encoded User Object
        	String userdetails=user.getId()+"###"+user.getUserName()+"###"+user.getPassword()+"###"+user.getApikey();
        	System.out.println("user details ---------" +userdetails);
        	String userToken = new String(Base64.encodeBase64String(userdetails.getBytes()));
        	System.out.println("Access Token Response ---------" +userToken);
        	
        	TokenObj tokenobj =new TokenObj(userToken,transactionToken,serviceToken); 
            return tokenobj;
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
    } 
}
