package com.schoolteacher.interfaces;

import com.schoolteacher.library.pojo.ApiResponse;
import com.schoolteacher.mylibrary.pojo.AddressOfUser;
import com.schoolteacher.pojos.BeginTransactionRequest;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Header;
import retrofit.http.POST;

public interface BeginTransactionInterface {
	@POST("/TransactionV1/Begin")
	void beginTransaction(@Header("X-Jeevom-RequestLocation") AddressOfUser locationObject, @Body BeginTransactionRequest object,
						  Callback<ApiResponse<Integer>> callback);
}