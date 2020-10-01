package com.chalenge.exchangerate.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;

public class ExchangeRateHistory {

    @SerializedName("start_at")
    String startDate;

    @SerializedName("end_at")
    String endDate;

    @SerializedName("base")
    Currency baseCurrency;

    @SerializedName("rates")
    SortedMap<String, Map<Currency, String>> rates;

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

    public SortedMap<String, Map<Currency, String>> getRates() {
        return rates;
    }

    public void setRates(SortedMap<String, Map<Currency, String>> rates) {
        if(rates instanceof LinkedHashMap) {
            this.rates = rates;
        }
    }
}
