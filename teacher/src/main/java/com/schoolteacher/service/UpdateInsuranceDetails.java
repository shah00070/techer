package com.schoolteacher.service;


import com.schoolteacher.pojos.InsuranceDetailsResult;
import com.schoolteacher.pojos.InsuranceRequest;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Header;
import retrofit.http.PUT;

public interface UpdateInsuranceDetails {
	@PUT("/Profile/Update")
	void updateInsurance(@Header("authorization") String token, @Body InsuranceRequest object, Callback<InsuranceDetailsResult> callback);

}
