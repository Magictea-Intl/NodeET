package com.stareating.nodeet.network.api;


import com.stareating.nodeet.network.common.CommonResponse;
import com.stareating.nodeet.network.entity.Token;
import com.stareating.nodeet.network.entity.User;
import com.stareating.nodeet.network.entity.UserVerifyResult;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Stardust on 2017/11/8.
 */

public interface UserApi {


    @GET("/api/v1/users/{uid}")
    Call<User> getUser(@Path("uid") String uid);

    @FormUrlEncoded
    @POST("/api/ns/login")
    Call<UserVerifyResult> verify(@Field("username") String userName, @Field("password") String password);


    @FormUrlEncoded
    @POST("/api/v2/users/{uid}/tokens")
    Call<CommonResponse<Token>> generateToken(@Path("uid") String uid, @Field("password") String password);
}
