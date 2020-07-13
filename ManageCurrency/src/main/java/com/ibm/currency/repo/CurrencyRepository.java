package com.ibm.currency.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.currency.model.Currency;


public interface CurrencyRepository extends JpaRepository<Currency, String> {

}
