package com.schoolteacher.pojos;

public class DropDownObject {

	// private object UniquePublicID;
	private boolean IsDeleted;
	private int Id;
	private String TypeIds;
	private String Term;
	private String ShortDefinition;
	private String LongDefinition;

	// private String Name;

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
	 * @return the typeIds
	 */
	public String getTypeIds() {
		return TypeIds;
	}

	/**
	 * @param typeIds
	 *            the typeIds to set
	 */
	public void setTypeIds(String typeIds) {
		TypeIds = typeIds;
	}

	/**
	 * @return the term
	 */
	public String getTerm() {
		return Term;
	}

	/**
	 * @param term
	 *            the term to set
	 */
	public void setTerm(String term) {
		Term = term;
	}

	/**
	 * @return the shortDefinition
	 */
	public String getShortDefinition() {
		return ShortDefinition;
	}

	/**
	 * @param shortDefinition
	 *            the shortDefinition to set
	 */
	public void setShortDefinition(String shortDefinition) {
		ShortDefinition = shortDefinition;
	}

	/**
	 * @return the longDefinition
	 */
	public String getLongDefinition() {
		return LongDefinition;
	}

	/**
	 * @param longDefinition
	 *            the longDefinition to set
	 */
	public void setLongDefinition(String longDefinition) {
		LongDefinition = longDefinition;
	}

}
