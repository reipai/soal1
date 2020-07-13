package com.reivai.soal1;

import com.reivai.soal1.Login.UserModel;
import com.reivai.soal1.ui.home.HomeModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface InterfaceApi {

    @POST("/login")
    Call<Response<UserModel>> getUser(@Query("username") String username, @Query("password") String password);

    @GET("/image")
    Call<List<HomeModel>> getImage();
}
