package com.schoolteacher.pojos;

import java.io.Serializable;

public class MemberEmergencyDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Id;
	private int MemberId;
	private String Title;
	private String FirstName;
	private String LastName;
	private String Email;
	private String CellNumber;
	private boolean IsPrimary;

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
	 * @return the memberId
	 */
	public int getMemberId() {
		return MemberId;
	}

	/**
	 * @param memberId
	 *            the memberId to set
	 */
	public void setMemberId(int memberId) {
		MemberId = memberId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return Title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		Title = title;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return FirstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return LastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		LastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return Email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		Email = email;
	}

	/**
	 * @return the cellNumber
	 */
	public String getCellNumber() {
		return CellNumber;
	}

	/**
	 * @param cellNumber
	 *            the cellNumber to set
	 */
	public void setCellNumber(String cellNumber) {
		CellNumber = cellNumber;
	}

	/**
	 * @return the isPrimary
	 */
	public boolean isIsPrimary() {
		return IsPrimary;
	}

	/**
	 * @param isPrimary
	 *            the isPrimary to set
	 */
	public void setIsPrimary(boolean isPrimary) {
		IsPrimary = isPrimary;
	}

}
