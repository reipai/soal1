package com.reivai.soal1;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static final String BASE_URL = "192.168.0.1";
    private static Api api;
    private Retrofit retrofit;

    private Api() {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static synchronized Api getInstance() {
        if (api==null) {
            api = new Api();
        }
        return api;
    }

    public InterfaceApi getApi() {
        return retrofit.create(InterfaceApi.class);
    }
}
