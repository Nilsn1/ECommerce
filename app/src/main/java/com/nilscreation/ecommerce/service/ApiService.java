package com.nilscreation.ecommerce.service;

import com.nilscreation.ecommerce.model.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("users")
    Call<Void> registerUser(@Body UserModel user);
}
