package com.chalenge.exchangerate.utils;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.chalenge.exchangerate.data.model.Currency;
import com.chalenge.exchangerate.ui.currencydetails.CustomGraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Map;
import java.util.SortedMap;


/**
 * Adapter class for databinding.
 *
 * @author Mohamed EL MANSOURI
 */
public final class AppAdapters {
    private AppAdapters() {
        // This class has only static methods, no constructor
    }
    @BindingAdapter("app:currencies")
    public static void setCurrencyList(@NonNull RecyclerView view, @Nullable SortedMap<Currency,String> exchangeRates) {
        if (view.getAdapter() instanceof CurrencyAdapter && exchangeRates != null) {
            ((CurrencyAdapter) view.getAdapter()).replaceAll(exchangeRates);
        }
    }

    @BindingAdapter("app:series")
    public static void setGrahViewData(@NonNull CustomGraphView view, @Nullable LineGraphSeries<DataPoint> lineGraphSeries) {
        if (lineGraphSeries != null) {
            view.setGraphViewData(lineGraphSeries);
        }
    }

    @BindingAdapter("app:xAxis")
    public static void setXaxis(@NonNull CustomGraphView view, @Nullable Map.Entry<Long,Long> minMax) {
        if (minMax != null) {
            view.setXaxisMinAndMax(minMax);
        }
    }
}
