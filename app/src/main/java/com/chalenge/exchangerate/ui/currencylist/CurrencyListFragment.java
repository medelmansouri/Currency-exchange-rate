package com.chalenge.exchangerate.ui.currencylist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chalenge.exchangerate.R;
import com.chalenge.exchangerate.databinding.FragmentCurrencyListBinding;
import com.chalenge.exchangerate.ui.common.BaseFragment;
import com.chalenge.exchangerate.utils.CurrencyAdapter;


public class CurrencyListFragment extends BaseFragment {

    public static final String FRAGMENT_ID = "CurrencyListFragment";
    public static final String  CURRENCY_NAME = "CURRENCY_NAME";
    public static final String  CURRENCY_VALUE = "CURRENCY_VALUE";
    private CurrencyListViewModel mViewModel;


    public static @NonNull
    CurrencyListFragment newInstance() {
        Bundle args = new Bundle();
        CurrencyListFragment fragment = new CurrencyListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public String getFragmentId() {
        return FRAGMENT_ID;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(false);
        FragmentCurrencyListBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_currency_list, container, false);
        mViewModel = ViewModelProviders.of(this).get(CurrencyListViewModel.class);
        binding.setViewmodel(mViewModel);
        CurrencyAdapter currencyAdapter = new CurrencyAdapter(getActivity(),item ->
            mViewModel.navigateToCurrencyHistory(item.first));
        binding.rvMain.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvMain.setAdapter(currencyAdapter);
        mViewModel.exchangeRateByCurrency.observe(this, exchangeRate -> {});
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    @Override
    public boolean addToBackstack() {
        return true;
    }

}