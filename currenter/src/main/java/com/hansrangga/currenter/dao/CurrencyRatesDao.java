package com.hansrangga.currenter.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hansrangga.currenter.model.CurrencyRates;

@Repository
public interface CurrencyRatesDao extends JpaRepository<CurrencyRates, Integer> {

}
