package com.chalenge.exchangerate.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.chalenge.exchangerate.R;
import com.chalenge.exchangerate.data.model.FragmentCall;
import com.chalenge.exchangerate.data.repositories.FragmentRepository;
import com.chalenge.exchangerate.databinding.ActivityMainBinding;
import com.chalenge.exchangerate.ui.common.BaseFragment;
import com.chalenge.exchangerate.ui.currencylist.CurrencyListFragment;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new MainActivityViewModel(FragmentRepository.getInstance());
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewmodel(viewModel);
        viewModel.getFragment().observe(this, this::updateFragment);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        int fragmentsCount = getSupportFragmentManager().getBackStackEntryCount();
        if (fragmentsCount == 1) {
            finish();
        } else if (fragmentsCount > 1) {
            getSupportFragmentManager().popBackStackImmediate();
        } else {
            super.onBackPressed();
        }
    }


    /**
     * Event : fragment intent to show its action update status.
     *
     * @param fragmentCall action is the new id
     */
    private void updateFragment(@Nullable FragmentCall fragmentCall) {
        if (fragmentCall == null || fragmentCall.getFragmentName() == null) {
            // do nothing if null
            return;
        }
        Fragment current = getSupportFragmentManager().findFragmentById(R.id.fragment_parent);
        if (current instanceof BaseFragment && fragmentCall.getFragmentName().equals(((BaseFragment) current).getFragmentId())) {
            // do nothing if fragment already visible
            return;
        }

        BaseFragment fragment = null;
        switch (fragmentCall.getFragmentName()) {
            case CurrencyListFragment.FRAGMENT_ID:
                fragment = CurrencyListFragment.newInstance();
                break;
            default:
                Log.e("MainActivity", "Request of an unknown fragment id:" + fragmentCall.getFragmentName());
        }

        if (fragment != null) {
            Fragment fx = getSupportFragmentManager().findFragmentByTag(fragment.getFragmentId());
            if (fx != null && fragment.doesPopBackStack()) {
                // if the fragment is already in the backstack, we remove all of the above
                getSupportFragmentManager().popBackStack(fx.getTag(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (fragment.addToBackstack()) {
                // we add the fragment in backstack only if requested
                transaction.addToBackStack(fragment.getFragmentId());
            }
            transaction.replace(R.id.fragment_parent, fragment, fragment.getFragmentId()).commit();
        }
    }
}
