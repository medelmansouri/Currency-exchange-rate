package com.chalenge.exchangerate.ui.common;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


/**
 * A base fragment for all fragments.
 * Contains common method that can be used in the application fragments.
 *
 * @author Mohamed EL MANSOURI
 */
public abstract class BaseFragment extends Fragment {

    public abstract String getFragmentId();

    /**
     * Remove the fragment from the backstack.
     */
    public void closeFragment() {
        Activity activity = getActivity();
        if (activity != null) {
            activity.onBackPressed();
        }
    }

    /**
     * A flag to indicate when the fragment is added if it must be kept in the backstack.
     * That is useful to hide temporary fragments like authentication.
     * By default, the fragment is not kept.
     *
     * @return if true, the fragment is added
     */
    public boolean addToBackstack() {
        return false;
    }

    /**
     * A flag to indicate if back press does remove fragment from back stack
     *
     * @return if true, the fragment will be removed
     */
    public boolean doesPopBackStack() {
        return true;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
}
