package com.example.aakash.loginandregistration.Remote;

import com.example.aakash.loginandregistration.Model.APIResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ThisMyAPI {

    @FormUrlEncoded
    @POST("login.php")
    Call<APIResponse> loginUser(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("register.php")
    Call<APIResponse> registerUser(@Field("name") String name,@Field("email") String email, @Field("password") String password);
}
