package com.schoolteacher.library.apiInterfaces;

import com.schoolteacher.library.pojo.ApiResponse;
import com.schoolteacher.library.pojo.MemberInformation;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface GetMemberInformation {
	@GET("/membership/MembershipDetails")
	void getMemberInformation(@Query("Email") String email,
			@Query("mobile") String mobile,
			Callback<ApiResponse<MemberInformation>> callback);
}
