package com.chalenge.exchangerate.utils;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.chalenge.exchangerate.data.model.Currency;

import java.util.List;
import java.util.Map;


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
    public static void setCurrencyList(@NonNull RecyclerView view, @Nullable Map<Currency,String> exchangeRates) {
        if (view.getAdapter() instanceof CurrencyAdapter && exchangeRates != null) {
            ((CurrencyAdapter) view.getAdapter()).replaceAll(exchangeRates);
        }
    }
}
