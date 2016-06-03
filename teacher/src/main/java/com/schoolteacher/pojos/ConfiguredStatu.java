package com.schoolteacher.pojos;

public class ConfiguredStatu {
	private int Id;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public int getPublicKey() {
		return PublicKey;
	}

	public void setPublicKey(int publicKey) {
		PublicKey = publicKey;
	}

	public int getServiceConfigurationStatusId() {
		return ServiceConfigurationStatusId;
	}

	public void setServiceConfigurationStatusId(int serviceConfigurationStatusId) {
		ServiceConfigurationStatusId = serviceConfigurationStatusId;
	}

	private String Name;
	private String Description;
	private int PublicKey;
	private int ServiceConfigurationStatusId ;
}
