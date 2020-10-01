package com.chalenge.exchangerate.ui.currencydetails;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.chalenge.exchangerate.data.model.Currency;
import com.chalenge.exchangerate.data.model.ExchangeRateHistory;
import com.chalenge.exchangerate.data.model.Pair;
import com.chalenge.exchangerate.data.repositories.ApiRepository;
import com.chalenge.exchangerate.utils.DateUtils;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class CurrencyDetailsViewModel extends ViewModel {
    public MediatorLiveData<ExchangeRateHistory> exchangeRateHistoryLiveData = new MediatorLiveData<>();
    ApiRepository apiRepository;
    public MutableLiveData<String> startsAt = new MutableLiveData<>();
    public MutableLiveData<String> endsAt = new MutableLiveData<>();
    public MutableLiveData<String> symbols = new MutableLiveData<>();
    public MutableLiveData<String> base = new MutableLiveData<>();
    public MutableLiveData<LineGraphSeries<DataPoint>> lineGraphSeries = new MutableLiveData<>();
    public MutableLiveData<Map.Entry<Long,Long>> minMaxDatePair = new MutableLiveData<>();

    public CurrencyDetailsViewModel(Currency mSymbols, Currency mBase) {
        apiRepository = ApiRepository.getInstance();
        String  datebeforeTwoMonthsAAAAMMJJ= DateUtils.getTwoMonthsEarlierDateAAAAMMJJ();
        String dateNowAAAAMMJJ = DateUtils.getCurrentDateAAAAMMJJ();
        Map.Entry<Long,Long> minMax = new Pair<>(DateUtils.fromISO8601UTC(datebeforeTwoMonthsAAAAMMJJ),
                DateUtils.fromISO8601UTC(dateNowAAAAMMJJ));

        minMaxDatePair.setValue(minMax);
        startsAt.setValue(datebeforeTwoMonthsAAAAMMJJ);
        endsAt.setValue(dateNowAAAAMMJJ);
        base.setValue(mBase.name());
        symbols.setValue(mSymbols.name());
        addDataSource();
    }

    public MutableLiveData<ExchangeRateHistory> getExchangeRateHistory(String startAt, String endAt,
                                                                       String symbols, String base){
        return apiRepository.getExchangeRateHistoryLiveData(startAt,endAt,symbols,base);
    }

    public final void addDataSource(){
        exchangeRateHistoryLiveData.addSource(getExchangeRateHistory(startsAt.getValue(), endsAt.getValue(),
                symbols.getValue(),base.getValue()),
                exchangeHistoryRate -> {
                    exchangeRateHistoryLiveData.setValue(exchangeHistoryRate);
                    SortedMap<Double, Double> sortedPointsByDate = getSortedPointsByDate(exchangeHistoryRate.getRates());
                    lineGraphSeries.setValue(new LineGraphSeries(getGraphDataPoints(sortedPointsByDate)));
                });
    }

   public Map.Entry<Currency,String> getCurrencyValuePair(Map map){
       return (Map.Entry<Currency,String>)map.entrySet().iterator().next();
   }


    public SortedMap<Double, Double> getSortedPointsByDate(SortedMap map) {
        SortedMap<Double, Double> sortedMap = new TreeMap<>();
        Collection collection = map.entrySet();
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Map<Currency, String>> entry = (Map.Entry<String, Map<Currency, String>>) iterator.next();
            Map.Entry<Currency, String> currencyValuePair = getCurrencyValuePair(entry.getValue());
            double date = DateUtils.fromISO8601UTC(entry.getKey());
            double currentRate = Double.parseDouble(currencyValuePair.getValue());
            sortedMap.put(date, currentRate);
        }
        return sortedMap;
    }


    public  DataPoint[] getGraphDataPoints(SortedMap map) {
        List<DataPoint> dataPoints = new LinkedList<>();
        Collection collection = map.entrySet();
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()){
            Map.Entry<Double,Double> entry = (Map.Entry<Double,Double> ) iterator.next();
            dataPoints.add(new DataPoint(entry.getKey(),entry.getValue()));
        }
        DataPoint[] dataPoints1 = new DataPoint[dataPoints.size()];
        dataPoints.toArray();
        dataPoints1 = dataPoints.toArray(dataPoints1);
        return dataPoints1;
    }
}
