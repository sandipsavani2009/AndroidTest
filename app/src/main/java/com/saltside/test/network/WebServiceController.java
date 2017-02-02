package com.saltside.test.network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;

import com.saltside.test.BuildConfig;
import com.saltside.test.SaltSideApplication;
import com.saltside.test.utils.Constants;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A Web controller handles web-service communication and provides appropriate response to UI-controllers.
 * It manages all IO branches and simplifies call and response for controllers
 */
public class WebServiceController {

    private static final String TAG = WebServiceController.class.getSimpleName();
    private WebServiceInterface mWebServiceInterface;
    private static WebServiceController sInstance = null;

    private WebServiceController() {
        initializeWebServiceAdapter();
    }

    /**
     * Should be used singleton instance as its secure against multi-threads and single access points.
     * @return
     */
    synchronized public static WebServiceController getInstance() {
        if (sInstance == null) {
            sInstance = new WebServiceController();
        }

        return sInstance;
    }

    private void initializeWebServiceAdapter() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(Constants.NetworkIo.READ_TIME_OUT, TimeUnit.SECONDS);
        builder.connectTimeout(Constants.NetworkIo.CONNECTION_TIME_OUT, TimeUnit.SECONDS);

        if (SaltSideApplication.getBuildType() != Constants.BuildTypes.PRODUCTION) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }

        OkHttpClient client = builder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        mWebServiceInterface = retrofit.create(WebServiceInterface.class);
    }

    private void handleIoError(NetworkResponseListener networkResponseListener) {
        if (networkResponseListener != null) {
            networkResponseListener.onFailure(Constants.NetworkCodes.NETWORK_FAILURE, null);
        }
    }

    private void onJsonDataSuccessResponse(Response<ResponseBody> serverResponse, @NonNull NetworkResponseListener networkResponseListener) {
        if (serverResponse != null && serverResponse.isSuccessful()
                && serverResponse.body() != null && networkResponseListener != null) {

            InputStream inputStream = serverResponse.body().byteStream();
            if (inputStream != null) {

                String jsonContent = "";
                try {
                    BufferedInputStream bis = new BufferedInputStream(inputStream);
                    ByteArrayOutputStream buf = new ByteArrayOutputStream();
                    int result = bis.read();
                    while(result != -1) {
                        buf.write((byte) result);
                        result = bis.read();
                    }
                    jsonContent = buf.toString();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                networkResponseListener.onSuccess(Constants.NetworkCodes.NETWORK_SUCCESS, jsonContent);
            } else {
                networkResponseListener.onFailure(Constants.NetworkCodes.NETWORK_FAILURE, null);
            }
        } else {
            handleIoError(networkResponseListener);
        }
    }

    /**
     * Network call for JSON data file download
     * @param networkResponseListener - network response callback
     */
    public void downloadJsonDataFile(final NetworkResponseListener networkResponseListener) {
        final Call<ResponseBody> call = mWebServiceInterface.jsonFile();
        if (call != null) {
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    onJsonDataSuccessResponse(response, networkResponseListener);
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    handleIoError(networkResponseListener);
                }
            });
        } else {
            handleIoError(networkResponseListener);
        }
    }

    /**
     * Network call for image download
     * @param url - image URL
     * @param networkResponseListener - Response callback
     */
    public void downloadImage(String url, final NetworkResponseListener networkResponseListener) {
        final Call<ResponseBody> call = mWebServiceInterface.getImage(url);
        if (call != null) {
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    onImageDownloaded(response, networkResponseListener);
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    handleIoError(networkResponseListener);
                }
            });
        } else {
            handleIoError(networkResponseListener);
        }
    }

    private void onImageDownloaded(Response<ResponseBody> serverResponse, NetworkResponseListener networkResponseListener) {
        if (serverResponse != null && serverResponse.isSuccessful()
                && serverResponse.body() != null && networkResponseListener != null) {

            Bitmap bmpImage = BitmapFactory.decodeStream(serverResponse.body().byteStream());
            networkResponseListener.onSuccess(Constants.NetworkCodes.NETWORK_SUCCESS, bmpImage);
        } else {
            handleIoError(networkResponseListener);
        }
    }
}
