package com.schoolteacher.mylibrary.pojo;



public class UpdateDefaultModel {
	private String Id;
	private String Type;
	private MemberDetailsInfo Data;
	/**
	 * @return the id
	 */
	public String getId() {
		return Id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		Id = id;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return Type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		Type = type;
	}
	/**
	 * @return the data
	 */
	public MemberDetailsInfo getData() {
		return Data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(MemberDetailsInfo data) {
		Data = data;
	}
}
