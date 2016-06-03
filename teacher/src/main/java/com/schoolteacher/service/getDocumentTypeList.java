package com.schoolteacher.service;

import com.schoolteacher.mylibrary.pojo.AddressOfUser;
import com.schoolteacher.pojos.DocumentListResult;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;

public interface getDocumentTypeList {
	@GET("/DocumentType/list/all")
	void getDocumentTypeList(@Header("X-Jeevom-RequestLocation") AddressOfUser locationObject, Callback<DocumentListResult> callback);
}
