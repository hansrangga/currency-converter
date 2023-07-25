package com.hansrangga.currenter.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class CurrencyRate {

    private String currencyCode;
    private double exchangeRate;
    private Date lastUpdated;

}
