package com.hansrangga.currenter.services;

import java.util.Date;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hansrangga.currenter.config.ExternalAPI;
import com.hansrangga.currenter.dao.CurrencyRatesDao;
import com.hansrangga.currenter.dto.ApiResponse;
import com.hansrangga.currenter.model.CurrencyRates;

@Service
public class GetExchangeRatesImp implements GetExchangeRates {

    @Autowired
    private ExternalAPI externalAPI;
    @Autowired
    private CurrencyRatesDao currencyRatesDao;

    @Override
    @Transactional
    public void fetchAndSave() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = externalAPI.getLatestRate();
        ApiResponse response = restTemplate.getForObject(apiUrl, ApiResponse.class);

        if (response != null && response.isSuccess()) {
            Map<String, Double> rates = response.getRates();
            Date lastUpdated = new Date(response.getTimestamp() * 1000);

            for (Map.Entry<String, Double> entry : rates.entrySet()) {
                String currencyCode = entry.getKey();
                double exchangeRate = entry.getValue();

                CurrencyRates currencyRates = new CurrencyRates();
                currencyRates.setCurrencyCode(currencyCode);
                currencyRates.setRateExchange(exchangeRate);
                currencyRates.setLastUpdated(lastUpdated);
                currencyRatesDao.save(currencyRates);
            }
        }
    }

}
