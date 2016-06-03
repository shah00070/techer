package com.schoolteacher.service;

import com.schoolteacher.mylibrary.pojo.AddressOfUser;
import com.schoolteacher.pojos.AssociationResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;

public interface AssociationService {
	@GET("/Profile/Association")
	void getMemberAssociations(@Header("X-Jeevom-RequestLocation") AddressOfUser locationObject, @Header("authorization") String token, Callback<AssociationResponse> callback);
}
