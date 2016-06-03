package com.schoolteacher.mylibrary.service.interfaces;

import retrofit.Callback;
import retrofit.http.GET;

import com.schoolteacher.mylibrary.pojo.ServiceRequestReasonListResponse;

public interface MessageRequestReasonService {

	@GET("/ServiceRequest/ServiceRequestReason/5")
	void getReasons(Callback<ServiceRequestReasonListResponse> callback);
}
