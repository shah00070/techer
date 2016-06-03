package com.schoolteacher.mylibrary.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MemberShipAuthenticData implements Serializable {

	/**
	 * 
	 */

	private String IsMemberinvitedSuccessfully;

	public String getIsMemberinvitedSuccessfully() {
		return IsMemberinvitedSuccessfully;
	}

	public void setIsMemberinvitedSuccessfully(String isMemberinvitedSuccessfully) {
		IsMemberinvitedSuccessfully = isMemberinvitedSuccessfully;
	}
private boolean isDemoUser;
	private int Id;
	private int FacilityId;
	private int ProfessionalId;
	private boolean IsExternal;
	private String ExternalId;
	private String ExternalProviderName;
	private String UserType;
	private boolean Verified;
	private boolean isUserVerifiedViaEmail;
	private boolean isUserVerifiedViaMobile;
	private String DefaultPasswordChanged;
	private boolean IsSuperAdmin;
	private boolean HasFilledBasicProfile;
	private String CellNumber;
	private MemberContactInfo ContactInformation;
	private String CreatedOnUTC;
	private String UpdatedOnUTC;
	private int LoginAttempt;
	private String FirstName;
	private String MedicalConditions;
	private String HereditaryMedicalConditions;
	private int DefaultProfileId;
	private String DefaultProfileType;
	private String LastName;
	private String FullName;
	private String Email;
	private String Gender;
	private String AuthToken;
	private String Title;
	private boolean IsAutogeneratePassword;
	private boolean isHealthCareProfessional;
	private boolean isFacilityOwner;
	private List<MembershipAuthenticateProfile> Profiles = new ArrayList<MembershipAuthenticateProfile>();
	private MembershipAuthenticBasicProfile BasicProfile;
	private String MedicalProfile;
	private String EmergencyProfile;
	private boolean IsRecordVerifiedBySupport;
	private boolean IsClaimed;
	private String DateOfBirth;
	private boolean IsPhoneVerified;
	private boolean IsEmailVerified;
	private boolean IsVoulantryBloodDonation;
	private String SignUpMobileVerificationCode;
	private String SignUpEmailVerificationCode;
	private String VerificationCode;
	private boolean IsSystemUser;
	private String UniquePublicID;
	private boolean IsDeleted;
	private boolean sendVerificationEmail;
	private boolean IsImport;



	public boolean isDemoUser() {
		return isDemoUser;
	}

	public void setIsDemoUser(boolean isDemoUser) {
		this.isDemoUser = isDemoUser;
	}

	public boolean isClaimCheckRequired() {
		return IsClaimCheckRequired;
	}

	public void setIsClaimCheckRequired(boolean isClaimCheckRequired) {
		IsClaimCheckRequired = isClaimCheckRequired;
	}

	public String getProProfilel() {
		return ProProfilel;
	}

	public void setProProfilel(String proProfilel) {
		ProProfilel = proProfilel;
	}

	public String getFacProfile() {
		return FacProfile;
	}

	public void setFacProfile(String facProfile) {
		FacProfile = facProfile;
	}

	public String getConProfile() {
		return ConProfile;
	}

	public void setConProfile(String conProfile) {
		ConProfile = conProfile;
	}

	public String getSalesInformation() {
		return SalesInformation;
	}

	public void setSalesInformation(String salesInformation) {
		SalesInformation = salesInformation;
	}

	public String getInvitation() {
		return Invitation;
	}

	public void setInvitation(String invitation) {
		Invitation = invitation;
	}

	public boolean isProfileCompleted() {
		return IsProfileCompleted;
	}

	public void setIsProfileCompleted(boolean isProfileCompleted) {
		IsProfileCompleted = isProfileCompleted;
	}

	public int getFacilityId() {
		return FacilityId;
	}

	public void setFacilityId(int facilityId) {
		FacilityId = facilityId;
	}

	public int getProfessionalId() {
		return ProfessionalId;
	}

	public void setProfessionalId(int professionalId) {
		ProfessionalId = professionalId;
	}

	public boolean isExternal() {
		return IsExternal;
	}

	public void setIsUserVerifiedViaEmail(boolean isUserVerifiedViaEmail) {
		this.isUserVerifiedViaEmail = isUserVerifiedViaEmail;
	}

	public void setIsUserVerifiedViaMobile(boolean isUserVerifiedViaMobile) {
		this.isUserVerifiedViaMobile = isUserVerifiedViaMobile;
	}

	public boolean isSuperAdmin() {
		return IsSuperAdmin;
	}

	public String getMedicalConditions() {
		return MedicalConditions;
	}

	public void setMedicalConditions(String medicalConditions) {
		MedicalConditions = medicalConditions;
	}

	public String getHereditaryMedicalConditions() {
		return HereditaryMedicalConditions;
	}

	public void setHereditaryMedicalConditions(String hereditaryMedicalConditions) {
		HereditaryMedicalConditions = hereditaryMedicalConditions;
	}

	public boolean isAutogeneratePassword() {
		return IsAutogeneratePassword;
	}

	public void setIsHealthCareProfessional(boolean isHealthCareProfessional) {
		this.isHealthCareProfessional = isHealthCareProfessional;
	}

	public void setIsFacilityOwner(boolean isFacilityOwner) {
		this.isFacilityOwner = isFacilityOwner;
	}

	public boolean isRecordVerifiedBySupport() {
		return IsRecordVerifiedBySupport;
	}

	public boolean isClaimed() {
		return IsClaimed;
	}

	public boolean isPhoneVerified() {
		return IsPhoneVerified;
	}

	public boolean isEmailVerified() {
		return IsEmailVerified;
	}

	public boolean isVoulantryBloodDonation() {
		return IsVoulantryBloodDonation;
	}

	public boolean isSystemUser() {
		return IsSystemUser;
	}

	public boolean isDeleted() {
		return IsDeleted;
	}

	public boolean isImport() {
		return IsImport;
	}

	public String getBloodGroupType() {
		return BloodGroupType;
	}

	public void setBloodGroupType(String bloodGroupType) {
		BloodGroupType = bloodGroupType;
	}

	public boolean isMemberRegisteredSuccessfully() {
		return isMemberRegisteredSuccessfully;
	}

	public void setIsMemberRegisteredSuccessfully(boolean isMemberRegisteredSuccessfully) {
		this.isMemberRegisteredSuccessfully = isMemberRegisteredSuccessfully;
	}

	public String getMemberRegisteredOnUTC() {
		return MemberRegisteredOnUTC;
	}

	public void setMemberRegisteredOnUTC(String memberRegisteredOnUTC) {
		MemberRegisteredOnUTC = memberRegisteredOnUTC;
	}

	public int getCreatedBy() {
		return CreatedBy;
	}

	public void setCreatedBy(int createdBy) {
		CreatedBy = createdBy;
	}

	public int getUpdatedBy() {
		return UpdatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		UpdatedBy = updatedBy;
	}

	public String getSendVerificationEmailMsg() {
		return sendVerificationEmailMsg;
	}

	public void setSendVerificationEmailMsg(String sendVerificationEmailMsg) {
		this.sendVerificationEmailMsg = sendVerificationEmailMsg;
	}

	public boolean isRegisteredMember() {
		return IsRegisteredMember;
	}

	public void setIsRegisteredMember(boolean isRegisteredMember) {
		IsRegisteredMember = isRegisteredMember;
	}

	public boolean isInvitedByJeevomMember() {
		return IsInvitedByJeevomMember;
	}

	public void setIsInvitedByJeevomMember(boolean isInvitedByJeevomMember) {
		IsInvitedByJeevomMember = isInvitedByJeevomMember;
	}

	public String getInvitedByJeevomMemberName() {
		return InvitedByJeevomMemberName;
	}

	public void setInvitedByJeevomMemberName(String invitedByJeevomMemberName) {
		InvitedByJeevomMemberName = invitedByJeevomMemberName;
	}

	public String getInvitedPotentialMemberName() {
		return InvitedPotentialMemberName;
	}

	public void setInvitedPotentialMemberName(String invitedPotentialMemberName) {
		InvitedPotentialMemberName = invitedPotentialMemberName;
	}

	public boolean isUserLocked() {
		return IsUserLocked;
	}

	public void setIsUserLocked(boolean isUserLocked) {
		IsUserLocked = isUserLocked;
	}

	public boolean isMemberEmailVerifiedl() {
		return IsMemberEmailVerifiedl;
	}

	public void setIsMemberEmailVerifiedl(boolean isMemberEmailVerifiedl) {
		IsMemberEmailVerifiedl = isMemberEmailVerifiedl;
	}

	public boolean isMemberCellNumberVerified() {
		return IsMemberCellNumberVerified;
	}

	public void setIsMemberCellNumberVerified(boolean isMemberCellNumberVerified) {
		IsMemberCellNumberVerified = isMemberCellNumberVerified;
	}

	public boolean isTrusted() {
		return IsTrusted;
	}

	public void setIsTrusted(boolean isTrusted) {
		IsTrusted = isTrusted;
	}

	private String BloodGroupType;
	private List<String> Roles;
	private boolean isMemberRegisteredSuccessfully;
	private String MemberRegisteredOnUTC;
	private int CreatedBy;
	private int UpdatedBy;
	private String sendVerificationEmailMsg;
private boolean IsRegisteredMember;
	private boolean IsInvitedByJeevomMember;
	private String InvitedByJeevomMemberName;
	private String InvitedPotentialMemberName;
	private boolean IsUserLocked;
	private  boolean IsMemberEmailVerifiedl;
	private boolean IsMemberCellNumberVerified;
	private boolean IsTrusted;
	private boolean IsDemoUser;
	private boolean IsClaimCheckRequired;
	private String ProProfilel;
	private String FacProfile;
	private String ConProfile;
	private String SalesInformation;
	private String Invitation;
	private boolean IsProfileCompleted;
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
	 * @return the isExternal
	 */
	public boolean isIsExternal() {
		return IsExternal;
	}

	/**
	 * @param isExternal
	 *            the isExternal to set
	 */
	public void setIsExternal(boolean isExternal) {
		IsExternal = isExternal;
	}

	/**
	 * @return the externalId
	 */
	public String getExternalId() {
		return ExternalId;
	}

	/**
	 * @param externalId
	 *            the externalId to set
	 */
	public void setExternalId(String externalId) {
		ExternalId = externalId;
	}

	/**
	 * @return the externalProviderName
	 */
	public String getExternalProviderName() {
		return ExternalProviderName;
	}

	/**
	 * @param externalProviderName
	 *            the externalProviderName to set
	 */
	public void setExternalProviderName(String externalProviderName) {
		ExternalProviderName = externalProviderName;
	}

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return UserType;
	}

	/**
	 * @param userType
	 *            the userType to set
	 */
	public void setUserType(String userType) {
		UserType = userType;
	}

	/**
	 * @return the verified
	 */
	public boolean isVerified() {
		return Verified;
	}

	/**
	 * @param verified
	 *            the verified to set
	 */
	public void setVerified(boolean verified) {
		Verified = verified;
	}

	/**
	 * @return the isUserVerifiedViaEmail
	 */
	public boolean isUserVerifiedViaEmail() {
		return isUserVerifiedViaEmail;
	}

	/**
	 * @param isUserVerifiedViaEmail
	 *            the isUserVerifiedViaEmail to set
	 */
	public void setUserVerifiedViaEmail(boolean isUserVerifiedViaEmail) {
		this.isUserVerifiedViaEmail = isUserVerifiedViaEmail;
	}

	/**
	 * @return the isUserVerifiedViaMobile
	 */
	public boolean isUserVerifiedViaMobile() {
		return isUserVerifiedViaMobile;
	}

	/**
	 * @param isUserVerifiedViaMobile
	 *            the isUserVerifiedViaMobile to set
	 */
	public void setUserVerifiedViaMobile(boolean isUserVerifiedViaMobile) {
		this.isUserVerifiedViaMobile = isUserVerifiedViaMobile;
	}

	/**
	 * @return the defaultPasswordChanged
	 */
	public String getDefaultPasswordChanged() {
		return DefaultPasswordChanged;
	}

	/**
	 * @param defaultPasswordChanged
	 *            the defaultPasswordChanged to set
	 */
	public void setDefaultPasswordChanged(String defaultPasswordChanged) {
		DefaultPasswordChanged = defaultPasswordChanged;
	}

	/**
	 * @return the isSuperAdmin
	 */
	public boolean isIsSuperAdmin() {
		return IsSuperAdmin;
	}

	/**
	 * @param isSuperAdmin
	 *            the isSuperAdmin to set
	 */
	public void setIsSuperAdmin(boolean isSuperAdmin) {
		IsSuperAdmin = isSuperAdmin;
	}

	/**
	 * @return the hasFilledBasicProfile
	 */
	public boolean isHasFilledBasicProfile() {
		return HasFilledBasicProfile;
	}

	/**
	 * @param hasFilledBasicProfile
	 *            the hasFilledBasicProfile to set
	 */
	public void setHasFilledBasicProfile(boolean hasFilledBasicProfile) {
		HasFilledBasicProfile = hasFilledBasicProfile;
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
	 * @return the contactInformation
	 */
	public MemberContactInfo getContactInformation() {
		return ContactInformation;
	}

	/**
	 * @param contactInformation
	 *            the contactInformation to set
	 */
	public void setContactInformation(MemberContactInfo contactInformation) {
		ContactInformation = contactInformation;
	}

	/**
	 * @return the createdOnUTC
	 */
	public String getCreatedOnUTC() {
		return CreatedOnUTC;
	}

	/**
	 * @param createdOnUTC
	 *            the createdOnUTC to set
	 */
	public void setCreatedOnUTC(String createdOnUTC) {
		CreatedOnUTC = createdOnUTC;
	}

	/**
	 * @return the updatedOnUTC
	 */
	public String getUpdatedOnUTC() {
		return UpdatedOnUTC;
	}

	/**
	 * @param updatedOnUTC
	 *            the updatedOnUTC to set
	 */
	public void setUpdatedOnUTC(String updatedOnUTC) {
		UpdatedOnUTC = updatedOnUTC;
	}

	/**
	 * @return the loginAttempt
	 */
	public int getLoginAttempt() {
		return LoginAttempt;
	}

	/**
	 * @param loginAttempt
	 *            the loginAttempt to set
	 */
	public void setLoginAttempt(int loginAttempt) {
		LoginAttempt = loginAttempt;
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
	 * @return the defaultProfileId
	 */
	public int getDefaultProfileId() {
		return DefaultProfileId;
	}

	/**
	 * @param defaultProfileId
	 *            the defaultProfileId to set
	 */
	public void setDefaultProfileId(int defaultProfileId) {
		DefaultProfileId = defaultProfileId;
	}

	/**
	 * @return the defaultProfileType
	 */
	public String getDefaultProfileType() {
		return DefaultProfileType;
	}

	/**
	 * @param defaultProfileType
	 *            the defaultProfileType to set
	 */
	public void setDefaultProfileType(String defaultProfileType) {
		DefaultProfileType = defaultProfileType;
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
	 * @return the fullName
	 */
	public String getFullName() {
		return FullName;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(String fullName) {
		FullName = fullName;
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
	 * @return the isAutogeneratePassword
	 */
	public boolean isIsAutogeneratePassword() {
		return IsAutogeneratePassword;
	}

	/**
	 * @param isAutogeneratePassword
	 *            the isAutogeneratePassword to set
	 */
	public void setIsAutogeneratePassword(boolean isAutogeneratePassword) {
		IsAutogeneratePassword = isAutogeneratePassword;
	}

	/**
	 * @return the isHealthCareProfessional
	 */
	public boolean isHealthCareProfessional() {
		return isHealthCareProfessional;
	}

	/**
	 * @param isHealthCareProfessional
	 *            the isHealthCareProfessional to set
	 */
	public void setHealthCareProfessional(boolean isHealthCareProfessional) {
		this.isHealthCareProfessional = isHealthCareProfessional;
	}

	/**
	 * @return the isFacilityOwner
	 */
	public boolean isFacilityOwner() {
		return isFacilityOwner;
	}

	/**
	 * @param isFacilityOwner
	 *            the isFacilityOwner to set
	 */
	public void setFacilityOwner(boolean isFacilityOwner) {
		this.isFacilityOwner = isFacilityOwner;
	}

	/**
	 * @return the profiles
	 */
	public List<MembershipAuthenticateProfile> getProfiles() {
		return Profiles;
	}

	/**
	 * @param profiles
	 *            the profiles to set
	 */
	public void setProfiles(List<MembershipAuthenticateProfile> profiles) {
		Profiles = profiles;
	}

	/**
	 * @return the basicProfile
	 */
	public MembershipAuthenticBasicProfile getBasicProfile() {
		return BasicProfile;
	}

	/**
	 * @param basicProfile
	 *            the basicProfile to set
	 */
	public void setBasicProfile(MembershipAuthenticBasicProfile basicProfile) {
		BasicProfile = basicProfile;
	}

	/**
	 * @return the medicalProfile
	 */
	public String getMedicalProfile() {
		return MedicalProfile;
	}

	/**
	 * @param medicalProfile
	 *            the medicalProfile to set
	 */
	public void setMedicalProfile(String medicalProfile) {
		MedicalProfile = medicalProfile;
	}

	/**
	 * @return the emergencyProfile
	 */
	public String getEmergencyProfile() {
		return EmergencyProfile;
	}

	/**
	 * @param emergencyProfile
	 *            the emergencyProfile to set
	 */
	public void setEmergencyProfile(String emergencyProfile) {
		EmergencyProfile = emergencyProfile;
	}

	/**
	 * @return the isRecordVerifiedBySupport
	 */
	public boolean isIsRecordVerifiedBySupport() {
		return IsRecordVerifiedBySupport;
	}

	/**
	 * @param isRecordVerifiedBySupport
	 *            the isRecordVerifiedBySupport to set
	 */
	public void setIsRecordVerifiedBySupport(boolean isRecordVerifiedBySupport) {
		IsRecordVerifiedBySupport = isRecordVerifiedBySupport;
	}

	/**
	 * @return the isClaimed
	 */
	public boolean isIsClaimed() {
		return IsClaimed;
	}

	/**
	 * @param isClaimed
	 *            the isClaimed to set
	 */
	public void setIsClaimed(boolean isClaimed) {
		IsClaimed = isClaimed;
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
	 * @return the isPhoneVerified
	 */
	public boolean isIsPhoneVerified() {
		return IsPhoneVerified;
	}

	/**
	 * @param isPhoneVerified
	 *            the isPhoneVerified to set
	 */
	public void setIsPhoneVerified(boolean isPhoneVerified) {
		IsPhoneVerified = isPhoneVerified;
	}

	/**
	 * @return the isEmailVerified
	 */
	public boolean isIsEmailVerified() {
		return IsEmailVerified;
	}

	/**
	 * @param isEmailVerified
	 *            the isEmailVerified to set
	 */
	public void setIsEmailVerified(boolean isEmailVerified) {
		IsEmailVerified = isEmailVerified;
	}

	/**
	 * @return the isVoulantryBloodDonation
	 */
	public boolean isIsVoulantryBloodDonation() {
		return IsVoulantryBloodDonation;
	}

	/**
	 * @param isVoulantryBloodDonation
	 *            the isVoulantryBloodDonation to set
	 */
	public void setIsVoulantryBloodDonation(boolean isVoulantryBloodDonation) {
		IsVoulantryBloodDonation = isVoulantryBloodDonation;
	}

	/**
	 * @return the signUpMobileVerificationCode
	 */
	public String getSignUpMobileVerificationCode() {
		return SignUpMobileVerificationCode;
	}

	/**
	 * @param signUpMobileVerificationCode
	 *            the signUpMobileVerificationCode to set
	 */
	public void setSignUpMobileVerificationCode(
			String signUpMobileVerificationCode) {
		SignUpMobileVerificationCode = signUpMobileVerificationCode;
	}

	/**
	 * @return the signUpEmailVerificationCode
	 */
	public String getSignUpEmailVerificationCode() {
		return SignUpEmailVerificationCode;
	}

	/**
	 * @param signUpEmailVerificationCode
	 *            the signUpEmailVerificationCode to set
	 */
	public void setSignUpEmailVerificationCode(
			String signUpEmailVerificationCode) {
		SignUpEmailVerificationCode = signUpEmailVerificationCode;
	}

	/**
	 * @return the verificationCode
	 */
	public String getVerificationCode() {
		return VerificationCode;
	}

	/**
	 * @param verificationCode
	 *            the verificationCode to set
	 */
	public void setVerificationCode(String verificationCode) {
		VerificationCode = verificationCode;
	}

	/**
	 * @return the isSystemUser
	 */
	public boolean isIsSystemUser() {
		return IsSystemUser;
	}

	/**
	 * @param isSystemUser
	 *            the isSystemUser to set
	 */
	public void setIsSystemUser(boolean isSystemUser) {
		IsSystemUser = isSystemUser;
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
	 * @return the sendVerificationEmail
	 */
	public boolean isSendVerificationEmail() {
		return sendVerificationEmail;
	}

	/**
	 * @param sendVerificationEmail
	 *            the sendVerificationEmail to set
	 */
	public void setSendVerificationEmail(boolean sendVerificationEmail) {
		this.sendVerificationEmail = sendVerificationEmail;
	}

	/**
	 * @return the isImport
	 */
	public boolean isIsImport() {
		return IsImport;
	}

	/**
	 * @param isImport
	 *            the isImport to set
	 */
	public void setIsImport(boolean isImport) {
		IsImport = isImport;
	}

	/**
	 * @return the roles
	 */
	public List<String> getRoles() {
		return Roles;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(List<String> roles) {
		Roles = roles;
	}

	public String getAuthToken() {
		return AuthToken;
	}

	public void setAuthToken(String authToken) {
		AuthToken = authToken;
	}

}
