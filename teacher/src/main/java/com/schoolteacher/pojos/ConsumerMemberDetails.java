package com.schoolteacher.pojos;

public class ConsumerMemberDetails {

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
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return DateOfBirth;
	}

	/**
	 * @param dateOfBirth
	 *            the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		DateOfBirth = dateOfBirth;
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
	 * @return the gender
	 */
	public String getGender() {
		return Gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		Gender = gender;
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
	 * @return the bloodGroupType
	 */
	public int getBloodGroupType() {
		return BloodGroupType;
	}

	/**
	 * @param bloodGroupType the bloodGroupType to set
	 */
	public void setBloodGroupType(int bloodGroupType) {
		BloodGroupType = bloodGroupType;
	}

	private String FirstName;
	private String LastName;
	private String Email;
	private String DateOfBirth;
	private String CellNumber;
	private String Gender;
	private String Title;
	private int BloodGroupType;

	// @Override
	// public String toString() {
	// // TODO Auto-generated method stub
	// return "{" + "FirstName" + ":" + getFirstName() + "LastName" + ":" +
	// getLastName() + "Email" + ":" + getEmail() + "DateOfBirth" + ":" +
	// getDateOfBirth() + "CellNumber" + ":" + getCellNumber()
	// + "Gender" + ":" + getGender() + "Title" + ":" + getTitle() + "}";
	// }
}
