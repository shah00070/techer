package com.schoolteacher.mylibrary.service.interfaces;

import com.schoolteacher.mylibrary.pojo.Status;
import com.schoolteacher.mylibrary.pojo.UpdateDefaultModel;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.PUT;

public interface UpdateDefulats {

	@PUT("/Profile/Update")
	void updateDefaults(@Body UpdateDefaultModel object, Callback<Status> updated);
}
