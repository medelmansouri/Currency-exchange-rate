package com.chalenge.exchangerate.data.model;

import java.util.Map;

public class ExchangeRate {

    private String base;

    private String date;

    private Map<Currency,String> rates;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<Currency, String> getRates() {
        return rates;
    }

    public void setRates(Map<Currency, String> rates) {
        this.rates = rates;
    }
}
