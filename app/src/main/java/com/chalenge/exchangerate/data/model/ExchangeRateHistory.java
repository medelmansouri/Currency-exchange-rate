package com.chalenge.exchangerate.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class ExchangeRateHistory {

    @SerializedName("start_at")
    String startDate;

    @SerializedName("end_at")
    String endDate;

    @SerializedName("base")
    Currency baseCurrency;

    @SerializedName("rates")
    Map<String, Map<Currency, String>> rates;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Currency getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(Currency baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public Map<String, Map<Currency, String>> getRates() {
        return rates;
    }

    public void setRates(Map<String, Map<Currency, String>> rates) {
        this.rates = rates;
    }
}
