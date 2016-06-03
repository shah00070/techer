package com.schoolteacher.mylibrary.pojo;

import java.io.Serializable;

public class FacilityType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Id;
	private String Name;
	private int Count;
	private int CreatedBy;
	private String UniquePublicID;
	private boolean IsDeleted;

	/**
	 * @return the id
	 */
	public int getId() {
		return Id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		Id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return Count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(int count) {
		Count = count;
	}

	/**
	 * @return the createdBy
	 */
	public int getCreatedBy() {
		return CreatedBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(int createdBy) {
		CreatedBy = createdBy;
	}

	/**
	 * @return the uniquePublicID
	 */
	public String getUniquePublicID() {
		return UniquePublicID;
	}

	/**
	 * @param uniquePublicID
	 *            the uniquePublicID to set
	 */
	public void setUniquePublicID(String uniquePublicID) {
		UniquePublicID = uniquePublicID;
	}

	/**
	 * @return the isDeleted
	 */
	public boolean isIsDeleted() {
		return IsDeleted;
	}

	/**
	 * @param isDeleted
	 *            the isDeleted to set
	 */
	public void setIsDeleted(boolean isDeleted) {
		IsDeleted = isDeleted;
	}

	/**
	 * @return the isMedical
	 */
	public boolean isIsMedical() {
		return IsMedical;
	}

	/**
	 * @param isMedical
	 *            the isMedical to set
	 */
	public void setIsMedical(boolean isMedical) {
		IsMedical = isMedical;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return Description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		Description = description;
	}

	private boolean IsMedical;
	private String Description;
}
