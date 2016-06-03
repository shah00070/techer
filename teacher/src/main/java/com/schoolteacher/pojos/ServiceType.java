package com.schoolteacher.pojos;

public class ServiceType {
	private int Id;
	private String UniquePublicID;
	private String Name;
	private String Description;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getUniquePublicID() {
		return UniquePublicID;
	}
	public void setUniquePublicID(String uniquePublicID) {
		UniquePublicID = uniquePublicID;
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
}
