package com.schoolteacher.appointment;

import java.io.Serializable;
import java.util.List;

public class ServiceRequisitionData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Id;
	private String PublicId;
	private int ByMemberId;
	private String ByMemberTitle;
	private String ByMemberFirstName;
	private String ByMemberLastName;
	private String ByMemberDateOfBirth;
	private String ByMemberGender;
	private String ByMemberPublicId;
	private String ByMemberEmail;
	private boolean ByMemberEmailVerified;
	private String ByMemberCellNumber;
	private boolean ByMemberCellNumberVerified;
	private String ServiceConfigurationCode;
	private int ForMemberId;
	private String ForMemberTitle;
	private String ForMemberFirstName;
	private String ForMemberLastName;
	private String ForMemberDateOfBirth;
	private String ForMemberGender;
	private String ForMemberPublicId;
	private int ToProfessionalId;
	private String ToProfessionalTitle;
	private String ToProfessionalFirstName;
	private String ToProfessionalLastName;
	private String ToProfessionalDateOfBirth;
	private String ToProfessionalGender;
	private String ToProfessionalPublicId;
	private String ToProfessionalEmail;
	private boolean ToProfessionalEmailVerified;
	private String ToProfessionalCellNumber;
	private boolean ToProfessionalCellNumberVerified;
	private int ToFacilityId;
	private String ToFacilityName;
	private String ToFacilityPublicId;
	private String ToFacilityOwnerEmail;
	private boolean ToFacilityOwnerEmailVerifed;
	private String ToFacilityOwnerCellNumber;
	private boolean ToFacilityOwnerCellNumberVerfied;
	private String AppointmentTime;
	private int SessionId;
	private String AppointmentDate;
	private int AppointmentStatus;
	private int AppointmentSlotId;
	private int StatusId;
	private String StatusText;
	private String StatusDescription;
	private int AppointmentInterval;
	private String ServiceConfigurationName;
	private String CreatedOnUTC;
	private List<Message> Messages;
	// public object PaymentId;
	// public object PaymentMode;
	// public object PaymentAmount;
	// public object PaymentAmountAfterPromotion;
	private String PaymentStatusText;

	// used for setting headers
	private String upComing;
	private String past;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getByMemberId() {
		return ByMemberId;
	}

	public void setByMemberId(int byMemberId) {
		ByMemberId = byMemberId;
	}

	public String getByMemberTitle() {
		return ByMemberTitle;
	}

	public void setByMemberTitle(String byMemberTitle) {
		ByMemberTitle = byMemberTitle;
	}

	public String getByMemberFirstName() {
		return ByMemberFirstName;
	}

	public void setByMemberFirstName(String byMemberFirstName) {
		ByMemberFirstName = byMemberFirstName;
	}

	public String getByMemberLastName() {
		return ByMemberLastName;
	}

	public void setByMemberLastName(String byMemberLastName) {
		ByMemberLastName = byMemberLastName;
	}

	public String getByMemberDateOfBirth() {
		return ByMemberDateOfBirth;
	}

	public void setByMemberDateOfBirth(String byMemberDateOfBirth) {
		ByMemberDateOfBirth = byMemberDateOfBirth;
	}

	public String getByMemberGender() {
		return ByMemberGender;
	}

	public void setByMemberGender(String byMemberGender) {
		ByMemberGender = byMemberGender;
	}

	public String getByMemberPublicId() {
		return ByMemberPublicId;
	}

	public void setByMemberPublicId(String byMemberPublicId) {
		ByMemberPublicId = byMemberPublicId;
	}

	public String getByMemberEmail() {
		return ByMemberEmail;
	}

	public void setByMemberEmail(String byMemberEmail) {
		ByMemberEmail = byMemberEmail;
	}

	public boolean isByMemberEmailVerified() {
		return ByMemberEmailVerified;
	}

	public void setByMemberEmailVerified(boolean byMemberEmailVerified) {
		ByMemberEmailVerified = byMemberEmailVerified;
	}

	public String getByMemberCellNumber() {
		return ByMemberCellNumber;
	}

	public void setByMemberCellNumber(String byMemberCellNumber) {
		ByMemberCellNumber = byMemberCellNumber;
	}

	public boolean isByMemberCellNumberVerified() {
		return ByMemberCellNumberVerified;
	}

	public void setByMemberCellNumberVerified(boolean byMemberCellNumberVerified) {
		ByMemberCellNumberVerified = byMemberCellNumberVerified;
	}

	public String getServiceConfigurationCode() {
		return ServiceConfigurationCode;
	}

	public void setServiceConfigurationCode(String serviceConfigurationCode) {
		ServiceConfigurationCode = serviceConfigurationCode;
	}

	public int getForMemberId() {
		return ForMemberId;
	}

	public void setForMemberId(int forMemberId) {
		ForMemberId = forMemberId;
	}

	public String getForMemberTitle() {
		return ForMemberTitle;
	}

	public void setForMemberTitle(String forMemberTitle) {
		ForMemberTitle = forMemberTitle;
	}

	public String getForMemberFirstName() {
		return ForMemberFirstName;
	}

	public void setForMemberFirstName(String forMemberFirstName) {
		ForMemberFirstName = forMemberFirstName;
	}

	public String getForMemberLastName() {
		return ForMemberLastName;
	}

	public void setForMemberLastName(String forMemberLastName) {
		ForMemberLastName = forMemberLastName;
	}

	public String getForMemberDateOfBirth() {
		return ForMemberDateOfBirth;
	}

	public void setForMemberDateOfBirth(String forMemberDateOfBirth) {
		ForMemberDateOfBirth = forMemberDateOfBirth;
	}

	public String getForMemberGender() {
		return ForMemberGender;
	}

	public void setForMemberGender(String forMemberGender) {
		ForMemberGender = forMemberGender;
	}

	public String getForMemberPublicId() {
		return ForMemberPublicId;
	}

	public void setForMemberPublicId(String forMemberPublicId) {
		ForMemberPublicId = forMemberPublicId;
	}

	public int getToProfessionalId() {
		return ToProfessionalId;
	}

	public void setToProfessionalId(int toProfessionalId) {
		ToProfessionalId = toProfessionalId;
	}

	public String getToProfessionalTitle() {
		return ToProfessionalTitle;
	}

	public void setToProfessionalTitle(String toProfessionalTitle) {
		ToProfessionalTitle = toProfessionalTitle;
	}

	public String getToProfessionalFirstName() {
		return ToProfessionalFirstName;
	}

	public void setToProfessionalFirstName(String toProfessionalFirstName) {
		ToProfessionalFirstName = toProfessionalFirstName;
	}

	public String getToProfessionalLastName() {
		return ToProfessionalLastName;
	}

	public void setToProfessionalLastName(String toProfessionalLastName) {
		ToProfessionalLastName = toProfessionalLastName;
	}

	public String getToProfessionalDateOfBirth() {
		return ToProfessionalDateOfBirth;
	}

	public void setToProfessionalDateOfBirth(String toProfessionalDateOfBirth) {
		ToProfessionalDateOfBirth = toProfessionalDateOfBirth;
	}

	public String getToProfessionalGender() {
		return ToProfessionalGender;
	}

	public void setToProfessionalGender(String toProfessionalGender) {
		ToProfessionalGender = toProfessionalGender;
	}

	public String getToProfessionalPublicId() {
		return ToProfessionalPublicId;
	}

	public void setToProfessionalPublicId(String toProfessionalPublicId) {
		ToProfessionalPublicId = toProfessionalPublicId;
	}

	public String getToProfessionalEmail() {
		return ToProfessionalEmail;
	}

	public void setToProfessionalEmail(String toProfessionalEmail) {
		ToProfessionalEmail = toProfessionalEmail;
	}

	public boolean isToProfessionalEmailVerified() {
		return ToProfessionalEmailVerified;
	}

	public void setToProfessionalEmailVerified(
			boolean toProfessionalEmailVerified) {
		ToProfessionalEmailVerified = toProfessionalEmailVerified;
	}

	public String getToProfessionalCellNumber() {
		return ToProfessionalCellNumber;
	}

	public void setToProfessionalCellNumber(String toProfessionalCellNumber) {
		ToProfessionalCellNumber = toProfessionalCellNumber;
	}

	public boolean isToProfessionalCellNumberVerified() {
		return ToProfessionalCellNumberVerified;
	}

	public void setToProfessionalCellNumberVerified(
			boolean toProfessionalCellNumberVerified) {
		ToProfessionalCellNumberVerified = toProfessionalCellNumberVerified;
	}

	public int getToFacilityId() {
		return ToFacilityId;
	}

	public void setToFacilityId(int toFacilityId) {
		ToFacilityId = toFacilityId;
	}

	public String getToFacilityName() {
		return ToFacilityName;
	}

	public void setToFacilityName(String toFacilityName) {
		ToFacilityName = toFacilityName;
	}

	public String getToFacilityPublicId() {
		return ToFacilityPublicId;
	}

	public void setToFacilityPublicId(String toFacilityPublicId) {
		ToFacilityPublicId = toFacilityPublicId;
	}

	public String getToFacilityOwnerEmail() {
		return ToFacilityOwnerEmail;
	}

	public void setToFacilityOwnerEmail(String toFacilityOwnerEmail) {
		ToFacilityOwnerEmail = toFacilityOwnerEmail;
	}

	public boolean isToFacilityOwnerEmailVerifed() {
		return ToFacilityOwnerEmailVerifed;
	}

	public void setToFacilityOwnerEmailVerifed(
			boolean toFacilityOwnerEmailVerifed) {
		ToFacilityOwnerEmailVerifed = toFacilityOwnerEmailVerifed;
	}

	public boolean isToFacilityOwnerCellNumberVerfied() {
		return ToFacilityOwnerCellNumberVerfied;
	}

	public void setToFacilityOwnerCellNumberVerfied(
			boolean toFacilityOwnerCellNumberVerfied) {
		ToFacilityOwnerCellNumberVerfied = toFacilityOwnerCellNumberVerfied;
	}

	public String getAppointmentTime() {
		return AppointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		AppointmentTime = appointmentTime;
	}

	public int getSessionId() {
		return SessionId;
	}

	public void setSessionId(int sessionId) {
		SessionId = sessionId;
	}

	public String getAppointmentDate() {
		return AppointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		AppointmentDate = appointmentDate;
	}

	public int getAppointmentStatus() {
		return AppointmentStatus;
	}

	public void setAppointmentStatus(int appointmentStatus) {
		AppointmentStatus = appointmentStatus;
	}

	public int getAppointmentSlotId() {
		return AppointmentSlotId;
	}

	public void setAppointmentSlotId(int appointmentSlotId) {
		AppointmentSlotId = appointmentSlotId;
	}

	public int getStatusId() {
		return StatusId;
	}

	public void setStatusId(int statusId) {
		StatusId = statusId;
	}

	public String getStatusText() {
		return StatusText;
	}

	public void setStatusText(String statusText) {
		StatusText = statusText;
	}

	public String getStatusDescription() {
		return StatusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		StatusDescription = statusDescription;
	}

	public int getAppointmentInterval() {
		return AppointmentInterval;
	}

	public void setAppointmentInterval(int appointmentInterval) {
		AppointmentInterval = appointmentInterval;
	}

	public String getServiceConfigurationName() {
		return ServiceConfigurationName;
	}

	public void setServiceConfigurationName(String serviceConfigurationName) {
		ServiceConfigurationName = serviceConfigurationName;
	}

	public List<Message> getMessages() {
		return Messages;
	}

	public void setMessages(List<Message> messages) {
		Messages = messages;
	}

	public String getPaymentStatusText() {
		return PaymentStatusText;
	}

	public void setPaymentStatusText(String paymentStatusText) {
		PaymentStatusText = paymentStatusText;
	}

	public String getUpComing() {
		return upComing;
	}

	public void setUpComing(String upComing) {
		this.upComing = upComing;
	}

	public String getPast() {
		return past;
	}

	public void setPast(String past) {
		this.past = past;
	}

	public String getToFacilityOwnerCellNumber() {
		return ToFacilityOwnerCellNumber;
	}

	public void setToFacilityOwnerCellNumber(String toFacilityOwnerCellNumber) {
		ToFacilityOwnerCellNumber = toFacilityOwnerCellNumber;
	}

	public String getPublicId() {
		return PublicId;
	}

	public void setPublicId(String publicId) {
		PublicId = publicId;
	}

	public String getCreatedOnUTC() {
		return CreatedOnUTC;
	}

	public void setCreatedOnUTC(String createdOnUTC) {
		CreatedOnUTC = createdOnUTC;
	}

}
