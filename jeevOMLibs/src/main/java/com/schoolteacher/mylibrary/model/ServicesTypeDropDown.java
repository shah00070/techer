package com.schoolteacher.mylibrary.model;

public class ServicesTypeDropDown {

	private int Id;
	private String Name;
	private int typeId;
	private String timeDate;
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
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTimeDate() {
		return timeDate;
	}
	public void setTimeDate(String timeDate) {
		this.timeDate = timeDate;
	}
}
