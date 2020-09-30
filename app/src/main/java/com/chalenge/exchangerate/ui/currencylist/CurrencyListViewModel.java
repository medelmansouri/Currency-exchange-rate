package com.chalenge.exchangerate.ui.currencylist;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.chalenge.exchangerate.data.model.Currency;
import com.chalenge.exchangerate.data.model.ExchangeRate;
import com.chalenge.exchangerate.data.model.ExchangeRateHistory;
import com.chalenge.exchangerate.data.repositories.ApiRepository;
import java.util.Map;


public class CurrencyListViewModel extends ViewModel {

    ApiRepository apiRepository;
    public MediatorLiveData<Map<Currency,String>> exchangeRateByCurrency = new MediatorLiveData<>();
    public MutableLiveData<ExchangeRateHistory> exchangeRateHistoryLiveData = new MutableLiveData<>();
    public CurrencyListViewModel() {
        apiRepository = ApiRepository.getInstance();
        exchangeRateByCurrency.addSource(getCurrencies(), exchangeRate -> {
            exchangeRateByCurrency.setValue(exchangeRate.getRates());
        });
    }

    public MutableLiveData<ExchangeRate> getCurrencies(){
       return apiRepository.getExchangeRateLiveData("USD");
    }

    public void getExchangeRateHistory(){
        exchangeRateHistoryLiveData =  apiRepository.getExchangeRateHistoryLiveData("2018-01-01",
                "2018-09-01","CAD","USD");
    }

}