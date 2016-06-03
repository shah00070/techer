package com.schoolteacher.pojos;

public class AddFamilyMember {
	private String Id;
	private String Type;
	private FamilyMember Data;

	/**
	 * @return the id
	 */
	public String getId() {
		return Id;
	}

	/**
	 * @param id
	 *            the id to set
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
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		Type = type;
	}

	/**
	 * @return the data
	 */
	public FamilyMember getData() {
		return Data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(FamilyMember data) {
		Data = data;
	}

	
}
