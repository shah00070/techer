package com.schoolteacher.mylibrary.service.interfaces;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

import com.schoolteacher.library.pojo.ExternalLoginInfo;
import com.schoolteacher.mylibrary.pojo.MembershipAuthenticateResponse;

public interface ExternalMemberShipAuthenticate {
	@POST("/membership/Authenticate")
	void onSuccessFullMemberSignIn(
			@Body ExternalLoginInfo memAuthenticateRequest,
			Callback<MembershipAuthenticateResponse> callback);
}
