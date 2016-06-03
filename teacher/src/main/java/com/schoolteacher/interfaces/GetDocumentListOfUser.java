package com.schoolteacher.interfaces;


import com.schoolteacher.mylibrary.pojo.AddressOfUser;
import com.schoolteacher.pojos.Documents;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Query;

public interface GetDocumentListOfUser {

	@GET("/Document/List")
	void getDocumentList(@Header("X-Jeevom-RequestLocation") AddressOfUser locationObject, @Query("MemberId") String memberId, Callback<Documents> callback);
}
