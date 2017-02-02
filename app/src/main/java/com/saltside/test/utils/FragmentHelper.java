package com.saltside.test.utils;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.saltside.test.ui.BaseActivity;

public class FragmentHelper {

    /** This method will add fragment to back-stack */
    public static void addContentFragment(@NonNull final BaseActivity activity, final int containerId,
                                          final Fragment fragment) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        fragmentManager.beginTransaction().add(containerId, fragment,fragment.getClass().getSimpleName()).addToBackStack(null).commit();
        fragmentManager.executePendingTransactions();
    }

    /** This method won't add fragment to back-stack */
    public static void replaceFragment(@NonNull final BaseActivity activity,
                                       final int containerId, final Fragment fragment) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(containerId, fragment,fragment.getClass().getSimpleName()).commit();
        fragmentManager.executePendingTransactions();
    }

}
