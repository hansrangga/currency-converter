package com.hansrangga.currenter.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(schema = "currenter", name = "currency_rates")
public class CurrencyRates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial", name = "currency_id")
    private int currencyId;

    @Column(name = "currency_code", length = 3, nullable = false)
    private String currencyCode;

    @Column(name = "exchange_rates", precision = 16, scale = 6, nullable = false)
    private double rateExchange;

    @Column(name = "last_updated", nullable = false)
    private Date lastUpdated;
}
