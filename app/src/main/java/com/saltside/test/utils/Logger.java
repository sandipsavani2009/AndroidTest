package com.saltside.test.utils;

import android.util.Log;

import com.saltside.test.SaltSideApplication;

/**
 * Created by sandip on 1/2/17.
 *
 * Custom Logger can be modifies to its appropriate level to show log details as per
 * requirement in future.
 */

public class Logger {

    public static void v(String tag, String msg) {
        switch (SaltSideApplication.getBuildType()) {
            case Constants.BuildTypes.DEVELOPMENT:
            case Constants.BuildTypes.UAT:
                Log.v(tag, msg);
                break;

        }
    }

    public static void d(String tag, String msg) {
        switch (SaltSideApplication.getBuildType()) {

            case Constants.BuildTypes.DEVELOPMENT:
            case Constants.BuildTypes.UAT:
                Log.d(tag, msg);
                break;

        }
    }

    public static void i(String tag, String msg) {
        switch (SaltSideApplication.getBuildType()) {

            case Constants.BuildTypes.DEVELOPMENT:
            case Constants.BuildTypes.UAT:
                Log.i(tag, msg);
                break;

        }
    }

    public static void e(String tag, String msg) {
        switch (SaltSideApplication.getBuildType()) {
            case Constants.BuildTypes.DEVELOPMENT:
            case Constants.BuildTypes.UAT:
                Log.e(tag, msg);
                break;

        }
    }

    public static void w(String tag, String msg) {
        switch (SaltSideApplication.getBuildType()) {
            case Constants.BuildTypes.DEVELOPMENT:
            case Constants.BuildTypes.UAT:
                Log.w(tag, msg);
                break;

        }
    }
}
