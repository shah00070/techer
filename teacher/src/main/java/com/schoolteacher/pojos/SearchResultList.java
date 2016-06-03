package com.schoolteacher.pojos;

import com.schoolteacher.mylibrary.pojo.ContactInformation;

import java.io.Serializable;
import java.util.List;

public class SearchResultList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7770340711712600903L;
	private int Id;
	private String Title;
	private String Name;
	private String ProfilePhoto;
	private String Fees;
	private String Phone;
	private String Email;
	private String MaskedPhone;
	private String MaskedEmail;
	private String Experence;
	private boolean IsRecommended;
	private boolean IsPremium;
	private String About;
	private boolean IsRecordVerifiedBySupport;

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

	public String getMaskedPhone() {
		return MaskedPhone;
	}

	public void setMaskedPhone(String maskedPhone) {
		MaskedPhone = maskedPhone;
	}

	public String getMaskedEmail() {
		return MaskedEmail;
	}

	public void setMaskedEmail(String maskedEmail) {
		MaskedEmail = maskedEmail;
	}

	public String getExperence() {
		return Experence;
	}

	public void setExperence(String experence) {
		Experence = experence;
	}

	public boolean isIsRecommended() {
		return IsRecommended;
	}

	public void setIsRecommended(boolean isRecommended) {
		IsRecommended = isRecommended;
	}

	public boolean isIsPremium() {
		return IsPremium;
	}

	public void setIsPremium(boolean isPremium) {
		IsPremium = isPremium;
	}

	public String getAbout() {
		return About;
	}

	public void setAbout(String about) {
		About = about;
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

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public int getRank() {
		return Rank;
	}

	public void setRank(int rank) {
		Rank = rank;
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

	private int type;
	private String Gender;
	private int Rank;
	private List<ContactInformation> ContactInformations;
	private List<String> EducationalQualifications;
	private List<String> Specialities;
}
