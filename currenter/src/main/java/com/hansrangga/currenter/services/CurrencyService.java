package com.hansrangga.currenter.services;

import java.util.List;

import com.hansrangga.currenter.dto.CurrencyRate;
import com.hansrangga.currenter.model.CurrencyRates;

public interface CurrencyService {

    List<CurrencyRate> getLatestCurrencyRates();

    void getAndSaveFromApi();

    List<CurrencyRate> convertToList(List<CurrencyRates> currencyRates);
}
