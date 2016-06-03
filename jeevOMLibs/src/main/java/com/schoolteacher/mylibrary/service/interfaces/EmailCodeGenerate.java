package com.schoolteacher.mylibrary.service.interfaces;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

import com.schoolteacher.mylibrary.pojo.PhoneOrEmailCodeGenerateResponse;

public interface EmailCodeGenerate {
	@POST("/membership/ResendEmailVerificationCode")
	void onEmailCodeGenerate(@Body String email, Callback<PhoneOrEmailCodeGenerateResponse> callback);

}
