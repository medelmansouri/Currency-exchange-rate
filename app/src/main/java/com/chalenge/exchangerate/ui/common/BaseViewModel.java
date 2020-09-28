package com.chalenge.exchangerate.ui.common;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * A base view model for all view models.
 * Contains common method that can be used in several view model.
 *
 * @author Mohamed EL MANSOURI
 */
public class BaseViewModel extends ViewModel {
    private MutableLiveData<Boolean> mustCloseFragment = new MutableLiveData<>();

    public LiveData<Boolean> mustCloseFragment() {
        return mustCloseFragment;
    }

    public void close() {
        mustCloseFragment.setValue(Boolean.TRUE);
    }
}