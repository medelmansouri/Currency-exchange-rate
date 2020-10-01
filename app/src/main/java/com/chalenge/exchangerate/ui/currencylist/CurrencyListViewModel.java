package com.chalenge.exchangerate.ui.currencylist;


import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.chalenge.exchangerate.data.model.Currency;
import com.chalenge.exchangerate.data.model.ExchangeRate;
import com.chalenge.exchangerate.data.model.FragmentCall;
import com.chalenge.exchangerate.data.repositories.ApiRepository;
import com.chalenge.exchangerate.data.repositories.FragmentRepository;
import com.chalenge.exchangerate.ui.currencydetails.CurrencyDetailsFragment;

import java.util.SortedMap;


public class CurrencyListViewModel extends ViewModel {

    ApiRepository apiRepository;
    FragmentRepository fragmentRepository;
    public MutableLiveData<String> baseCurrency = new MutableLiveData<>();
    public MediatorLiveData<SortedMap<Currency,String>> exchangeRateByCurrency = new MediatorLiveData<>();
    public CurrencyListViewModel() {
        apiRepository = ApiRepository.getInstance();
        fragmentRepository = FragmentRepository.getInstance();
        baseCurrency.setValue("USD");
        addExchangeSource();

    }

    public MutableLiveData<ExchangeRate> getCurrencies(String currency){
       return apiRepository.getExchangeRateLiveData(currency);
    }


    public void navigateToCurrencyHistory(Currency currencyName){
        FragmentCall fragmentCall = new FragmentCall(CurrencyDetailsFragment.FRAGMENT_ID);
        fragmentCall.putArgument(CurrencyListFragment.CURRENCY_NAME, currencyName);
        fragmentRepository.setCurrentFragment(fragmentCall);
    }

    public final void addExchangeSource(){
        exchangeRateByCurrency.addSource(getCurrencies(baseCurrency.getValue()), exchangeRate ->
                exchangeRateByCurrency.setValue(exchangeRate.getRates()));
    }
}