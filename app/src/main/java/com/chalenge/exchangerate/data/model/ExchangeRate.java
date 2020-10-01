package com.chalenge.exchangerate.data.model;

import java.util.LinkedHashMap;
import java.util.SortedMap;

public class ExchangeRate {

    private String base;

    private String date;

    private SortedMap<Currency,String> rates;

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

    public SortedMap<Currency, String> getRates() {
        return rates;
    }

    public void setRates(SortedMap<Currency, String> rates) {
        if(rates instanceof LinkedHashMap) {
            this.rates = rates;
        }
    }
}
