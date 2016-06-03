package com.schoolteacher.service;

import com.schoolteacher.pojos.DocumentBody;
import com.schoolteacher.pojos.UploadImageResponse;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Header;
import retrofit.http.POST;

public interface UploadProfImageInterface {
	@POST("/doctor/Profile/Photo/Upload")
	void upload(@Header("authorization") String token,
				@Body DocumentBody uploadImageRequest,
				Callback<UploadImageResponse> callback);
}
