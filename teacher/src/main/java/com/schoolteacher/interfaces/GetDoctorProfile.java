package com.schoolteacher.interfaces;


import com.schoolteacher.pojos.JeevProfileResult;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Path;

public interface GetDoctorProfile {
	@GET("/doctor/profileByPublicId/{profileId}")
//	void getProfessionalDetails(@Header("X-Jeevom-RequestLocation")String locationObject,@Path("profileId") String profileId,
//			@Header("x-jeevom-android-app-version") String version,@Header("authorization") String token,
//			Callback<JeevProfileResult> callback);

	void getProfessionalDetails(@Path("profileId") String profileId,
								@Header("x-jeevom-android-app-version") String version, @Header("authorization") String token,
								Callback<JeevProfileResult> callback);

}
