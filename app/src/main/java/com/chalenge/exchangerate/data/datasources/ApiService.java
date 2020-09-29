package com.chalenge.exchangerate.data.datasources;

import com.chalenge.exchangerate.data.model.ExchangeRate;
import com.chalenge.exchangerate.data.model.ExchangeRateHistory;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiService {
    @GET("latest")
    Call<ExchangeRate> getCurrencyRates(@Query("base") String base);

    @GET("history")
    Call<ExchangeRateHistory> getHistoricalRatesAgainstUSD(@Query("start_at") String startAt,
                                                           @Query("end_at") String endAt,
                                                           @Query("symbols") String symbols,
                                                           @Query("base") String base);
}
