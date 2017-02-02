package com.saltside.test.ui.home;

import android.os.Bundle;

import com.saltside.test.R;
import com.saltside.test.ui.BaseActivity;
import com.saltside.test.ui.listeners.ActivityActionListener;
import com.saltside.test.utils.FragmentHelper;

public class HomeActivity extends BaseActivity implements ActivityActionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_home);
        loadHomeFragment();
    }

    private void loadHomeFragment() {
        FragmentHelper.replaceFragment(this, R.id.fragmentContainer, new ItemListFragment());
    }

    @Override
    public void setScreenTitle(String title) {
        setPageTitle(title);
    }

    @Override
    public void launchItemDetails(Bundle args) {
        ItemDetailsFragment fragment = new ItemDetailsFragment();
        fragment.setArguments(args);
        FragmentHelper.addContentFragment(this, R.id.fragmentContainer, fragment);
        setPageTitle(getString(R.string.details));
    }
}
