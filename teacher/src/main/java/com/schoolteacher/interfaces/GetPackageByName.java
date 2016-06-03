package com.schoolteacher.interfaces;

import com.schoolteacher.mylibrary.pojo.AddressOfUser;
import com.schoolteacher.pojos.GetPackageResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Query;

public interface GetPackageByName {
	@GET("/Package/GetPackageByName")
	void getPackageByName(@Header("X-Jeevom-RequestLocation") AddressOfUser locationObject, @Query("packageName") String value,
						  Callback<GetPackageResponse> callback);
}