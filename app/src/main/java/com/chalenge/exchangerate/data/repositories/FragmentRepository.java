package com.chalenge.exchangerate.data.repositories;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.chalenge.exchangerate.data.model.FragmentCall;

public final class FragmentRepository {

    // SINGLETON
    private static final FragmentRepository ourInstance = new FragmentRepository();

    public static FragmentRepository getInstance() {
        return ourInstance;
    }

    private FragmentRepository() {
    }

    /**
     * The stream containing the current fragment intent to show.
     */
    private MutableLiveData<FragmentCall> currentFragment = new MutableLiveData<>();
    /**
     * The stream containing the previously showed fragment intent to show.
     * in case we need to get back to it
     */
    private MutableLiveData<FragmentCall> previousFragment = new MutableLiveData<>();

    /**
     * This method returns an Intent that contains id of fragment
     *
     * @return Intent
     */
    public @NonNull
    LiveData<FragmentCall> getCurrentFragment() {
        return currentFragment;
    }

    public MutableLiveData<FragmentCall> getPreviousFragment() {
        return previousFragment;
    }

    /**
     * Change the current fragment showed.
     *
     * @param fragment the fragment Intent (contains action which equals the id value previously)
     */
    public void setCurrentFragment(@Nullable FragmentCall fragment) {
        previousFragment.setValue(currentFragment.getValue());
        currentFragment.setValue(fragment);
    }
}
