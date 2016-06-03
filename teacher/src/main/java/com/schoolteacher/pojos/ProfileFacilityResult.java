package com.schoolteacher.pojos;

import com.schoolteacher.mylibrary.pojo.Status;

public class ProfileFacilityResult {

	private Status Status;
	private ProfileFacilityInfo Data;

	public Status getStatus() {
		return Status;
	}

	public void setStatus(Status status) {
		Status = status;
	}

	public ProfileFacilityInfo getData() {
		return Data;
	}

	public void setData(ProfileFacilityInfo data) {
		Data = data;
	}
}
