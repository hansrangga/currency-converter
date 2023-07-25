package com.hansrangga.currenter.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hansrangga.currenter.dao.CurrencyRatesDao;
import com.hansrangga.currenter.dto.CurrencyRate;
import com.hansrangga.currenter.model.CurrencyRates;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CurrencyServiceImp implements CurrencyService {

    @Autowired
    private GetExchangeRates getExchangeRates;
    @Autowired
    private CurrencyRatesDao currencyRatesDao;

    @Override
    public List<CurrencyRate> getLatestCurrencyRates() {
        try {

            List<CurrencyRates> currency = currencyRatesDao.findAll();
            List<CurrencyRate> liveCurrency = convertToList(currency);
            return liveCurrency;
        } catch (Exception e) {
            log.error("Terjadi error saat mengambil data kurs: {}", e.getMessage());
            log.error("Trace Stack Error: ", e);
            return null;
        }
        // List<CurrencyRates> currency = currencyRatesDao.findAll();
        // List<CurrencyRate> liveCurrency = convertToList(currency);
        // return liveCurrency;
    }

    private List<CurrencyRate> getDefaultCurrencyRates() {
        // Implementasi untuk memberikan data kurs default jika terjadi kesalahan
        // Misalnya, mengembalikan data kurs default untuk base currency tertentu
        // ...

        // Contoh fallback data default (belum disesuaikan dengan implementasi yang
        // sebenarnya)
        List<CurrencyRate> defaultCurrencyRates = new ArrayList<>();
        defaultCurrencyRates.add(new CurrencyRate());
        defaultCurrencyRates.add(new CurrencyRate());
        defaultCurrencyRates.add(new CurrencyRate());
        return defaultCurrencyRates;
    }

    @Override
    public void getAndSaveFromApi() {
        getExchangeRates.fetchAndSave();
    }

    @Override
    public List<CurrencyRate> convertToList(List<CurrencyRates> currencyRates) {
        return currencyRates.stream()
                .map(this::getData)
                .collect(Collectors.toList());
    }

    CurrencyRate getData(CurrencyRates currencyRates) {
        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.setCurrencyCode(currencyRates.getCurrencyCode());
        currencyRate.setExchangeRate(currencyRates.getRateExchange());
        currencyRate.setLastUpdated(currencyRates.getLastUpdated());
        return currencyRate;
    }

}
