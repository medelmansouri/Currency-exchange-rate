package com.chalenge.exchangerate.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.chalenge.exchangerate.data.model.FragmentCall;
import com.chalenge.exchangerate.data.repositories.FragmentRepository;
import com.chalenge.exchangerate.ui.currencylist.CurrencyListFragment;

public class MainActivityViewModel extends ViewModel {

    private FragmentRepository fragmentRepository;


    public MainActivityViewModel(FragmentRepository fragmentRepository) {
        this.fragmentRepository = fragmentRepository;
        fragmentRepository.setCurrentFragment(new FragmentCall(CurrencyListFragment.FRAGMENT_ID));
    }

    @NonNull
    LiveData<FragmentCall> getFragment() {
        return fragmentRepository.getCurrentFragment();
    }

}
