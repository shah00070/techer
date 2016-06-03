package com.schoolteacher.library.apiInterfaces;

import com.schoolteacher.library.pojo.ApiResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface SendSetPasswordRequest {
	@GET("/membership/SendSetPasswordRequest")
	void sendSetPasswordRequest(@Query("Email") String email,
			@Query("mobile") String mobile,
			Callback<ApiResponse<Boolean>> callback);
}
