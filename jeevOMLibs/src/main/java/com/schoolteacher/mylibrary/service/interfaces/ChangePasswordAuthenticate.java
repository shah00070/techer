package com.schoolteacher.mylibrary.service.interfaces;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Header;
import retrofit.http.POST;

import com.schoolteacher.mylibrary.pojo.ChangePasswordAuthenticateRequest;
import com.schoolteacher.mylibrary.pojo.ChangePasswordAuthenticateResponse;

public interface ChangePasswordAuthenticate {
    @POST("/Membership/ChangePassword")
    void onSuccessFullChangePassword(@Header("authorization") String token, @Body ChangePasswordAuthenticateRequest changePasswordAuthenticateRequest, Callback<ChangePasswordAuthenticateResponse> callback);

}
