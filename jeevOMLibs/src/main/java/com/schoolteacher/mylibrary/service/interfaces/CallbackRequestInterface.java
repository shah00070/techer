package com.schoolteacher.mylibrary.service.interfaces;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

import com.schoolteacher.mylibrary.pojo.CallbackRequestBody;
import com.schoolteacher.mylibrary.pojo.CallbackRequestResponse;

public interface CallbackRequestInterface {
	@POST("/ServiceRequest/Message/Create")
	void requestCallback(@Body CallbackRequestBody callbackRequest, Callback<CallbackRequestResponse> callback);
}
