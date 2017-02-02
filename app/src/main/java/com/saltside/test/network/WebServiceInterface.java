package com.saltside.test.network;


import java.io.File;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;


public interface WebServiceInterface {


    @GET("/maclir/f715d78b49c3b4b3b77f/raw/8854ab2fe4cbe2a5919cea97d71b714ae5a4838d/items.json")
    Call<ResponseBody> jsonFile();

    @GET
    Call<ResponseBody> getImage(@Url String url);
}
