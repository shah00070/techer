package com.schoolteacher.interfaces;

import com.schoolteacher.mylibrary.pojo.AddressOfUser;
import com.schoolteacher.pojos.ProfileFacilityResult;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Path;

public interface FacilityProfile {
	@GET("/FacilityProfile/{id}")
	void getFacilityProfile(@Header("X-Jeevom-RequestLocation") AddressOfUser locationObject, @Header("authorization") String token, @Path("id") String id,
							Callback<ProfileFacilityResult> callback);
}
