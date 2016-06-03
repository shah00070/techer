package com.schoolteacher.interfaces;

import com.schoolteacher.library.pojo.ApiResponse;
import com.schoolteacher.mylibrary.pojo.Status;
import com.schoolteacher.pojos.UpdateCellEmailInfo;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Header;
import retrofit.http.POST;

public interface UpdateCellEmail {
	@POST("/membership/UpdateEmailCellNumber")
	void updateCellEmail(@Header("X-Jeevom-RequestLocation") String locationObject, @Header("authorization") String token,
						 @Body UpdateCellEmailInfo updateCellEmail,
						 Callback<ApiResponse<Status>> callback);
}
