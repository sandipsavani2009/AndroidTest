package com.saltside.test.network;

/**
 * Created by sandip on 1/2/17.
 */

public interface NetworkResponseListener {
    /**
     * Network responded success
     * @param statusCode - local success code
     * @param result - response result
     */
    void onSuccess(int statusCode, Object result);

    /**
     * Faillure callback for view-controllers
     * @param statusCode - local failure code
     * @param errResult - msg
     */
    void onFailure(int statusCode, Object errResult);
}
