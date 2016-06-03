package com.schoolteacher.mylibrary.service.interfaces;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

import com.schoolteacher.mylibrary.pojo.MembershipAuthenticateRequest;
import com.schoolteacher.mylibrary.pojo.MembershipAuthenticateResponse;

public interface MemberShipAuthenticate {
	@GET("/api/v1/membership/token/")
	void onSuccessFullMemberSignIn(@Query("loginid") String loginid, @Query("password") String cellnumber, Callback<MembershipAuthenticateResponse> callback);
}
