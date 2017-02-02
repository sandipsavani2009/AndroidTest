package com.saltside.test.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.URI;
import java.net.URL;

/**
 * Created by sandip on 14/11/16.
 *
 * Network related common code to be accessible everywhere
 */
public class NetworkUtils {

    /**
     * Used to encode URL from string for its correct URL pattern match
     * String may have improper characters/symbols which do not match to URL
     * @param url as String
     * @return - proper url pattern
     */
    public static String getEncodedUrl(String url) {
        String encodedUrl = url;

        try {
            URL urlInstance = new URL(url);
            encodedUrl = new URI(urlInstance.getProtocol(), urlInstance.getAuthority(),
                    urlInstance.getPath(), urlInstance.getQuery(),
                    urlInstance.getRef()).toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return encodedUrl;
    }

    /**
     * Check for Internet connection
     * @param context - Context for caller's life
     * @return - Internet connection state
     */
    public static boolean isInternetOn(Context context) {
        boolean isInternetOn = false;

        ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conn.getActiveNetworkInfo();
        if (networkInfo != null) {
            isInternetOn = networkInfo.isConnected();
        }

        return isInternetOn;
    }
}
