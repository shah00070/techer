package com.schoolteacher.mylibrary.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Data implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Area> Areas;
	private List<SearchResultList> SearchResultList;
	private List<ServiceRequestTypeList> ServiceRequestTypeList;
	private int ServiceRequestId;
	private int ServiceRequestMessageId;
	private String MessageFormat;
	private List<ServiceRequestList> ServiceRequestList;
	private List<ServiceRequestMessageList> ServiceRequestMessageList;
	private int UserId;
	private int Id;
	private String FullName;
	private String Email;
	private String PhoneNumber;
	private String UserType;
	private String DateOfBirth;
	private List<SpecialityGroup> SpecialityGroups;
	private List<Facility> Facilities;
	private int TotalRecords;
	private ArrayList<Doctor> DoctorList;
	private Doctor doctor;
	private ArrayList<Doctor> FacilityList;
	private Doctor DoctorProfile;
	private Doctor FacilityProfile;
	private String FileUrl;
	private boolean Data;
	private List<DataDictionary> DataDictionary;
	private List<String> EducationDegreeList;
	private List<Speciality> Specialities;
	private List<BloodGroup> BloodGroups;
	private List<Country> Countries;
	private List<State> States;
	private List<City> Cities;
	// Missed Call Code Fields
	private String UniqueRequestId;
	private boolean IsVerified;
	private String CallToVerifyNumberAsText;
	private List<HealthCareProfessionalType> HealthCareProfessionalTypes;
	private boolean IsUserVerified;
	private boolean isUserVerifiedViaEmail;
	private boolean isUserVerifiedViaMobile;
	private String Gender;
	private String Area;
	private String City;
	
	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getArea() {
		return Area;
	}

	public void setArea(String area) {
		Area = area;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	// end
	public List<DataDictionary> getDataDictionary() {
		return DataDictionary;
	}

	public void setDataDictionary(List<DataDictionary> dataDictionary) {
		DataDictionary = dataDictionary;
	}

	public List<Facility> getFacilities() {
		return Facilities;
	}

	public void setFacilities(List<Facility> facilities) {
		Facilities = facilities;
	}

	public int getTotalRecords() {
		return TotalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		TotalRecords = totalRecords;
	}

	public ArrayList<Doctor> getDoctorList() {
		return DoctorList;
	}

	public void setDoctorList(ArrayList<Doctor> doctorList) {
		DoctorList = doctorList;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public ArrayList<Doctor> getFacilityList() {
		return FacilityList;
	}

	public void setFacilityList(ArrayList<Doctor> facilityList) {
		FacilityList = facilityList;
	}

	public Doctor getDoctorProfile() {
		return DoctorProfile;
	}

	public void setDoctorProfile(Doctor doctorProfile) {
		DoctorProfile = doctorProfile;
	}

	public Doctor getFacilityProfile() {
		return FacilityProfile;
	}

	public void setFacilityProfile(Doctor facilityProfile) {
		FacilityProfile = facilityProfile;
	}

	public List<SpecialityGroup> getSpecialityGroups() {
		return SpecialityGroups;
	}

	public void setSpecialityGroups(List<SpecialityGroup> specialityGroups) {
		SpecialityGroups = specialityGroups;
	}

	public boolean isData() {
		return Data;
	}

	public void setData(boolean data) {
		Data = data;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getFullName() {
		return FullName;
	}

	public void setFullName(String fullName) {
		FullName = fullName;
	}

	public String getUserType() {
		return UserType;
	}

	public void setUserType(String userType) {
		UserType = userType;
	}

	public String getFileUrl() {
		return FileUrl;
	}

	public void setFileUrl(String fileUrl) {
		FileUrl = fileUrl;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public List<ServiceRequestTypeList> getServiceRequestTypeList() {
		return ServiceRequestTypeList;
	}

	public void setServiceRequestTypeList(List<ServiceRequestTypeList> serviceRequestTypeList) {
		ServiceRequestTypeList = serviceRequestTypeList;
	}

	public int getServiceRequestId() {
		return ServiceRequestId;
	}

	public void setServiceRequestId(int serviceRequestId) {
		ServiceRequestId = serviceRequestId;
	}

	public int getServiceRequestMessageId() {
		return ServiceRequestMessageId;
	}

	public void setServiceRequestMessageId(int serviceRequestMessageId) {
		ServiceRequestMessageId = serviceRequestMessageId;
	}

	public String getMessageFormat() {
		return MessageFormat;
	}

	public void setMessageFormat(String messageFormat) {
		MessageFormat = messageFormat;
	}

	public List<ServiceRequestList> getServiceRequestList() {
		return ServiceRequestList;
	}

	public void setServiceRequestList(List<ServiceRequestList> serviceRequestList) {
		ServiceRequestList = serviceRequestList;
	}

	public List<ServiceRequestMessageList> getServiceRequestMessageList() {
		return ServiceRequestMessageList;
	}

	public void setServiceRequestMessageList(List<ServiceRequestMessageList> serviceRequestMessageList) {
		ServiceRequestMessageList = serviceRequestMessageList;
	}

	public List<BloodGroup> getBloodGroups() {
		return BloodGroups;
	}

	public void setBloodGroups(List<BloodGroup> bloodGroups) {
		BloodGroups = bloodGroups;
	}

	public List<Speciality> getSpecialities() {
		return Specialities;
	}

	public void setSpecialities(List<Speciality> specialities) {
		Specialities = specialities;
	}

	public List<Country> getCountries() {
		return Countries;
	}

	public void setCountries(List<Country> countries) {
		Countries = countries;
	}

	public List<State> getStates() {
		return States;
	}

	public void setStates(List<State> states) {
		States = states;
	}

	public List<City> getCities() {
		return Cities;
	}

	public void setCities(List<City> cities) {
		Cities = cities;
	}

	public List<String> getEducationDegreeList() {
		return EducationDegreeList;
	}

	public void setEducationDegreeList(List<String> educationDegreeList) {
		EducationDegreeList = educationDegreeList;
	}

	public String getUniqueRequestId() {
		return UniqueRequestId;
	}

	public void setUniqueRequestId(String uniqueRequestId) {
		UniqueRequestId = uniqueRequestId;
	}

	public String getCallToVerifyNumberAsText() {
		return CallToVerifyNumberAsText;
	}

	public void setCallToVerifyNumberAsText(String callToVerifyNumberAsText) {
		CallToVerifyNumberAsText = callToVerifyNumberAsText;
	}

	public boolean isIsVerified() {
		return IsVerified;
	}

	public void setIsVerified(boolean isVerified) {
		IsVerified = isVerified;
	}

	public List<HealthCareProfessionalType> getHealthCareProfessionalTypes() {
		return HealthCareProfessionalTypes;
	}

	public void setHealthCareProfessionalTypes(List<HealthCareProfessionalType> healthCareProfessionalTypes) {
		HealthCareProfessionalTypes = healthCareProfessionalTypes;
	}

	public List<SearchResultList> getSearchResultList() {
		return SearchResultList;
	}

	public void setSearchResultList(List<SearchResultList> searchResultList) {
		SearchResultList = searchResultList;
	}

	public List<Area> getAreas() {
		return Areas;
	}

	public void setAreas(List<Area> areas) {
		Areas = areas;
	}

	public boolean isIsUserVerified() {
		return IsUserVerified;
	}

	public void setIsUserVerified(boolean isUserVerified) {
		IsUserVerified = isUserVerified;
	}

	public boolean isUserVerifiedViaEmail() {
		return isUserVerifiedViaEmail;
	}

	public void setUserVerifiedViaEmail(boolean isUserVerifiedViaEmail) {
		this.isUserVerifiedViaEmail = isUserVerifiedViaEmail;
	}

	public boolean isUserVerifiedViaMobile() {
		return isUserVerifiedViaMobile;
	}

	public void setUserVerifiedViaMobile(boolean isUserVerifiedViaMobile) {
		this.isUserVerifiedViaMobile = isUserVerifiedViaMobile;
	}

	public String getDateOfBirth() {
		return DateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}

}
