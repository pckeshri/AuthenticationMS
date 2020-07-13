package com.ibm.currency.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "currency")
public class Currency {
	
	@Id
	@Column (name ="currencycode", table="currency") 
	private String currencyCode;
	
	@Column (name ="currencyrate", table="currency")
	private double currencyRate;
	
	@Column (name ="countryname", table="currency")
	private String countryName;
	
	public Currency() {
		super();
		// TODO Auto-generated constructor stub
	}
		
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public double getCurrencyRate() {
		return currencyRate;
	}
	public void setCurrencyRate(double rate) {
		this.currencyRate = rate;
	}
}
