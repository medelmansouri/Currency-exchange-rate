package com.chalenge.exchangerate.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.chalenge.exchangerate.data.datasources.ApiDataSource;
import com.chalenge.exchangerate.data.model.ExchangeRate;
import com.chalenge.exchangerate.data.model.ExchangeRateHistory;

public class ApiRepository {

    private static final ApiRepository apiRepositoryInstance = new ApiRepository();
    ApiDataSource apiDataSource;

    public static ApiRepository getInstance() {
        return apiRepositoryInstance;
    }

    public ApiRepository() {
            this.apiDataSource = ApiDataSource.getInstance();
    }


    public MutableLiveData<ExchangeRate> getExchangeRateLiveData(String base) {
        return apiDataSource.requestExchangeRates(base);
    }

    public MutableLiveData<ExchangeRateHistory> getExchangeRateHistoryLiveData(String startAt,
                                                                               String endAt,
                                                                               String symbols,
                                                                               String base) {
        return apiDataSource.requestExchangeRatesHistory(startAt,endAt,symbols,base);
    }
}
