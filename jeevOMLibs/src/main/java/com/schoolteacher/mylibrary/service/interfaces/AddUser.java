package com.schoolteacher.mylibrary.service.interfaces;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

import com.schoolteacher.mylibrary.pojo.AddUserRequest;
import com.schoolteacher.mylibrary.pojo.AddUserResponse;

public interface AddUser {

	@POST("/membership/adduser")
	void user(@Body AddUserRequest addUserRequest, Callback<AddUserResponse> callback);
}
