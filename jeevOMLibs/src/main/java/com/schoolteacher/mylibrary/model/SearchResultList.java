package com.schoolteacher.mylibrary.model;

import java.io.Serializable;
import java.util.List;

public class SearchResultList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Id;
	private String Title;
	private String Name;
	private String ProfilePhoto;
	private String Fees;
	private String Phone;
	private String Email;

	// private object Experence ;
	private boolean IsRecommended;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getProfilePhoto() {
		return ProfilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		ProfilePhoto = profilePhoto;
	}

	public String getFees() {
		return Fees;
	}

	public void setFees(String fees) {
		Fees = fees;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public boolean isIsRecommended() {
		return IsRecommended;
	}

	public void setIsRecommended(boolean isRecommended) {
		IsRecommended = isRecommended;
	}

	public boolean isIsRecordVerifiedBySupport() {
		return IsRecordVerifiedBySupport;
	}

	public void setIsRecordVerifiedBySupport(boolean isRecordVerifiedBySupport) {
		IsRecordVerifiedBySupport = isRecordVerifiedBySupport;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<ContactInformation> getContactInformations() {
		return ContactInformations;
	}

	public void setContactInformations(List<ContactInformation> contactInformations) {
		ContactInformations = contactInformations;
	}

	public List<String> getEducationalQualifications() {
		return EducationalQualifications;
	}

	public void setEducationalQualifications(List<String> educationalQualifications) {
		EducationalQualifications = educationalQualifications;
	}

	public List<String> getSpecialities() {
		return Specialities;
	}

	public void setSpecialities(List<String> specialities) {
		Specialities = specialities;
	}

	private boolean IsRecordVerifiedBySupport;
	private int type;
	private List<ContactInformation> ContactInformations;
	private List<String> EducationalQualifications;
	private List<String> Specialities;
}
