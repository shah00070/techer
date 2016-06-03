package com.schoolteacher.service;


import com.schoolteacher.pojos.JeevFacilityResult;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Path;

public interface GetFacilityProfile {
	@GET("/facility/profileByPublicId/{profileId}")
//	void getFacilityDetails(@Header("X-Jeevom-RequestLocation")String locationObject,@Path("profileId") String profileId,
//			@Header("x-jeevom-android-app-version") String version,@Header("authorization") String token,
//			Callback<JeevFacilityResult> callback);
	void getFacilityDetails(@Path("profileId") String profileId,
							@Header("x-jeevom-android-app-version") String version, @Header("authorization") String token,
							Callback<JeevFacilityResult> callback);

}
