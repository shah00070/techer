package com.schoolteacher.mylibrary.model;

import java.util.List;

public class UpdateServiceExpertise {

	private String Services;
	private String Expertise;
	private List<String> Specialities;
	private int UserId;

	public String getServices() {
		return Services;
	}

	public void setServices(String services) {
		Services = services;
	}

	public String getExpertise() {
		return Expertise;
	}

	public void setExpertise(String expertise) {
		Expertise = expertise;
	}

	public List<String> getSpecialities() {
		return Specialities;
	}

	public void setSpecialities(List<String> specialities) {
		Specialities = specialities;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}
}
