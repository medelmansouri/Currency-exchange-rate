package com.chalenge.exchangerate.ui.currencydetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.chalenge.exchangerate.R;
import com.chalenge.exchangerate.data.model.Currency;
import com.chalenge.exchangerate.databinding.FragmentRatesHistoryBinding;
import com.chalenge.exchangerate.ui.common.BaseFragment;
import com.chalenge.exchangerate.ui.currencylist.CurrencyListFragment;
import java.util.Map;

public class CurrencyDetailsFragment extends BaseFragment {
    public static final String FRAGMENT_ID = "CurrencyDetailsFragment";

    private CurrencyDetailsViewModel mViewModel;
    private Currency currency;

    public static @NonNull
    CurrencyDetailsFragment newInstance(@NonNull Map map) {
        Bundle args = new Bundle();
        CurrencyDetailsFragment fragment = new CurrencyDetailsFragment();
        args.putSerializable(CurrencyListFragment.CURRENCY_NAME, (Currency) map.get(CurrencyListFragment.CURRENCY_NAME));
        fragment.setArguments(args);
        return fragment;
    }

    public String getFragmentId() {
        return FRAGMENT_ID;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currency = (Currency) getArguments().getSerializable(CurrencyListFragment.CURRENCY_NAME);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(false);
        FragmentRatesHistoryBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rates_history, container, false);
        mViewModel = new CurrencyDetailsViewModel(currency,Currency.USD);
        binding.setViewmodel(mViewModel);
        binding.setLifecycleOwner(this);
        mViewModel.exchangeRateHistoryLiveData.observe(this, exchangeRateHistory -> {});
        mViewModel.lineGraphSeries.observe(this,dataPointLineGraphSeries -> {});
        return binding.getRoot();
    }

    @Override
    public boolean addToBackstack() {
        return true;
    }
}
