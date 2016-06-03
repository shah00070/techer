package com.schoolteacher.mylibrary.model;

import java.util.List;

public class Degrees {
	private List<String> Degrees;
	private int UserId;

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public List<String> getDegrees() {
		return Degrees;
	}

	public void setDegrees(List<String> degrees) {
		Degrees = degrees;
	}
}
