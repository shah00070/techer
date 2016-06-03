package com.schoolteacher.library.apiInterfaces;

import com.schoolteacher.library.pojo.ApiResponse;
import com.schoolteacher.library.pojo.MemberInformation;
import com.schoolteacher.library.pojo.MembershipSignupResponse;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface MemberShipSignup {

	@POST("/membership/Signup")
	void memberShipSignUp(@Body MemberInformation memberInformation,
			Callback<ApiResponse<MembershipSignupResponse>> callback);

}
