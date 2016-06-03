package com.schoolteacher.mylibrary.service.interfaces;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

import com.schoolteacher.mylibrary.pojo.CallbackRequestResponse;
import com.schoolteacher.mylibrary.pojo.ClaimRequestBody;

public interface ClaimInterface {
	@POST("/ServiceRequest/Message/Create")
	void requestClaim(@Body ClaimRequestBody callbackRequest, Callback<CallbackRequestResponse> callback);
}
