package com.saltside.test;

import android.app.Application;

import com.saltside.test.appState.AppStateHolder;
import com.saltside.test.utils.Constants;

/**
 * Created by sandip on 1/2/17.
 */

public class SaltSideApplication extends Application {

    /* App state holder instance should be available through out app */
    private static AppStateHolder mAppStateHolder;

    /**
     * Build type specifies test & production related changes to manage in side app programs.
     * @return - Build type
     */
    public static int getBuildType() {
        return Constants.BuildTypes.DEVELOPMENT;
    }

    /**
     * State holder reference provider by application
     * @return
     */
    public static AppStateHolder getStateHolder() {
        if (mAppStateHolder == null) {
            mAppStateHolder = new AppStateHolder();
        }
        return mAppStateHolder;
    }

}
