package com.schoolteacher.appointment;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Path;
import retrofit.http.Query;

public interface AppointmentURL {
	@GET("/serviceRequisition/c/{publicId}")
	// void getAllEmailRequest(@Path("publicId") String publicId, @Query("code")
	// String serviceConfiguration, @Query("pageIndex") String pageIndex,
	// @Query("PageSize") String pageSize,
	// @Query("Status") String statusText, Callback<AppointmentRequestResponse>
	// callback);
	void getAllEmailRequest(@Path("publicId") String publicId,
							@Query("onlyAppointments") String booleanValue,
							@Header("authorization") String token,
							Callback<AppointmentRequestResponse> callback);
}
