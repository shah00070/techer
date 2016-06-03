package com.schoolteacher.mylibrary.service.interfaces;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

import com.schoolteacher.library.pojo.AddExternalUserResponse;
import com.schoolteacher.library.pojo.ApiResponse;
import com.schoolteacher.mylibrary.model.AddExternalUserRequest;

public interface AddExternalUser {

	@POST("/membership/LoginAsExternalProvider")
	void addExternal(@Body AddExternalUserRequest addExternalUser,
			Callback<ApiResponse<AddExternalUserResponse>> callback);
}
