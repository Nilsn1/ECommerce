package com.nilscreation.ecommerce.service;

import com.nilscreation.ecommerce.model.ProductDetails;
import com.nilscreation.ecommerce.model.UserLogin;
import com.nilscreation.ecommerce.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("users")
    Call<Void> registerUser(@Body UserModel user);

    @POST("auth/login")
    Call<Void> loginUser(@Body UserLogin userLogin);

    @GET("products")
    Call<List<ProductDetails>> getProducts();
}
