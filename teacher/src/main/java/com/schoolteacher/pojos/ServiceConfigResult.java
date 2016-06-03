package com.schoolteacher.pojos;

import java.util.List;

public class ServiceConfigResult {
	private int Id;
	private String PublicId;
	private String Title;
	private String Fees;
	private String Name;
	private List<ServiceConfiguration> ServiceConfigurations;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getPublicId() {
		return PublicId;
	}

	public void setPublicId(String publicId) {
		PublicId = publicId;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getFees() {
		return Fees;
	}

	public void setFees(String fees) {
		Fees = fees;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public List<ServiceConfiguration> getServiceConfigurations() {
		return ServiceConfigurations;
	}

	public void setServiceConfigurations(
			List<ServiceConfiguration> serviceConfigurations) {
		ServiceConfigurations = serviceConfigurations;
	}
}
