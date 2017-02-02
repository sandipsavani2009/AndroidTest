package com.saltside.test.utils;

/**
 * Created by sandip on 1/2/17.
 *
 * Constants for programs as single access
 */

public class Constants {

    public interface NetworkIo {
        int READ_TIME_OUT = 30 * 1000;  // 30 sec
        int CONNECTION_TIME_OUT = 10 * 1000;    // 10 sec
    }

    public interface NetworkCodes {
        int NETWORK_SUCCESS = 1;
        int NETWORK_FAILURE = 2;
    }

    public interface BuildTypes {
        int DEVELOPMENT = 1;
        int UAT = 2;
        int PRODUCTION = 3;
    }

    public interface SplashScreen {
        int TIME_OUT = 1000 * 5;   // 5 sec
    }

    public interface Args {
        String SELECTED_ITEM = "selected_item";
    }
}
