package com.schoolteacher.mylibrary.service.interfaces;

import com.schoolteacher.mylibrary.pojo.SearchResultsResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface SearchProfilesForClaim {
	@GET("/Membership/GetUnClaimedProfile/{userid}")
	void getProfilesForClaim(@Path("userid") String userid, Callback<SearchResultsResponse> callback);
}
