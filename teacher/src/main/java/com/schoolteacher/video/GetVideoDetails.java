package com.schoolteacher.video;

import com.schoolteacher.library.pojo.ApiResponse;
import com.schoolteacher.pojos.VideoRequestData;
import com.schoolteacher.pojos.VideoResponse;

import retrofit.http.Body;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;

public interface GetVideoDetails {
	@POST("/intv/video/Create/{appointmentId}")
	void getVideoDetails(@Path("appointmentId") String app_id,
						 @Header("authorization") String token,
						 @Body VideoRequestData videoRequestData,
						 retrofit.Callback<ApiResponse<VideoResponse>> callback);

}
