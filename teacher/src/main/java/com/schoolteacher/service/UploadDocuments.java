package com.schoolteacher.service;

import com.schoolteacher.pojos.Documents;
import com.schoolteacher.pojos.UploadDocumentObject;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Header;
import retrofit.http.POST;

public interface UploadDocuments {
	@POST("/Document/Upload")
	void startUploadingDocuments(@Header("X-Jeevom-RequestLocation") String locationObject, @Body UploadDocumentObject object,
								 Callback<Documents> result);
}
