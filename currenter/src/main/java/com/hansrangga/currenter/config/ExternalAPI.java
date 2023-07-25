package com.hansrangga.currenter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
public class ExternalAPI {

    @Value("${hansrangga.exchange.host}")
    private String host;

    @Value("${hansrangga.exchange.api-key}")
    private String apiKey;

    private String latestRate = "/latest";
    private String historicalRate = "/{historyDate}";

    public String getHost() {
        return this.host;
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public String getLatestRate() {
        return UriComponentsBuilder.fromHttpUrl(this.host + this.latestRate)
                .queryParam("access_key", this.apiKey)
                .toUriString();
    }

    public String getHistoricalRate() {
        return UriComponentsBuilder.fromHttpUrl(this.host + this.historicalRate)
                .queryParam("access_key", this.apiKey)
                .toUriString();
    }

}
