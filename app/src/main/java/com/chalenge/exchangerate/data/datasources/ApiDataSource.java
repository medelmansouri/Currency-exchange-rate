package com.chalenge.exchangerate.data.datasources;

import androidx.lifecycle.MutableLiveData;

import com.chalenge.exchangerate.BuildConfig;
import com.chalenge.exchangerate.data.model.ExchangeRate;
import com.chalenge.exchangerate.data.model.ExchangeRateHistory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiDataSource {

    private static Retrofit retrofit;
    static final String BASE_URL = BuildConfig.BASE_URL;
    public MutableLiveData<ExchangeRate> exchangeRates = new MutableLiveData<>();
    public MutableLiveData<ExchangeRateHistory> exchangeRatesHistory = new MutableLiveData<>();
    private static final ApiDataSource apiDataSourceInstance = new ApiDataSource();
    ApiService apiInterface = getRetrofitInstance().create(ApiService.class);

    public static ApiDataSource getInstance() {
        return apiDataSourceInstance;
    }
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public MutableLiveData<ExchangeRate>  requestExchangeRates(String base) {
        Call<ExchangeRate> call = apiInterface.getCurrencyRates(base);
        call.enqueue(new Callback<ExchangeRate>() {
            @Override
            public void onResponse(Call<ExchangeRate> call, Response<ExchangeRate> response) {
                if (response.body() != null) {
                    exchangeRates.postValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<ExchangeRate> call, Throwable t) {

            }
        });
        return exchangeRates;
    }

    public MutableLiveData<ExchangeRateHistory> requestExchangeRatesHistory(String startAt,
                                                                            String endAt,
                                                                            String symbols,
                                                                            String base) {
        Call<ExchangeRateHistory> call = apiInterface.getHistoricalRatesAgainstUSD(startAt, endAt,symbols,base);
        call.enqueue(new Callback<ExchangeRateHistory>() {
            @Override
            public void onResponse(Call<ExchangeRateHistory> call, Response<ExchangeRateHistory> response) {
                if (response.body() != null) {
                    exchangeRatesHistory.postValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<ExchangeRateHistory> call, Throwable t) {

            }
        });
        return exchangeRatesHistory;
    }
}
