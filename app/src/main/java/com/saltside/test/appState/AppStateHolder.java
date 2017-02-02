package com.saltside.test.appState;

/**
 * Created by sandip on 1/2/17.
 *
 * During App session, number of data state operations need to be managed.
 * StateHolder keeps track for state management while user interactions during single access app sessions.
 */

public class AppStateHolder {

    private String serverJsonFile;

    public String getServerJsonFile() {
        return serverJsonFile;
    }

    public void setServerJsonFile(String serverJsonFile) {
        this.serverJsonFile = serverJsonFile;
    }
}
