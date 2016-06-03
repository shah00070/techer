package com.schoolteacher.interfaces;

import com.schoolteacher.mylibrary.pojo.AddressOfUser;
import com.schoolteacher.pojos.FamilyMembersResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Path;

public interface FamilyMemberGetService {
	@GET("/Profile/Member/Family/{id}")
	void getFamilyMembers(@Header("X-Jeevom-RequestLocation") AddressOfUser locationObject, @Header("authorization") String token, @Path("id") String id, Callback<FamilyMembersResponse> callback);
}
