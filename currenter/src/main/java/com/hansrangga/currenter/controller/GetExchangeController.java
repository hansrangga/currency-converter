package com.hansrangga.currenter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hansrangga.currenter.dto.CurrencyRate;
import com.hansrangga.currenter.services.CurrencyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/currenter")
public class GetExchangeController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/latest")
    public ResponseEntity<?> getLatestCurrencyRates() {
        currencyService.getAndSaveFromApi();
        List<CurrencyRate> currencyRates = currencyService.getLatestCurrencyRates();

        log.info("Data Kurs: {}", currencyService.getLatestCurrencyRates());
        if (currencyRates.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data Kurs Tidak Ditemukan");
        } else {
            return ResponseEntity.ok(currencyRates);
        }
    }
}
