package com.schoolteacher.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class DocumentList implements Serializable, Parcelable {
	private static final long serialVersionUID = 304823979811589427L;
	private int Id;
	private String Name;
	private String Description;
	private String Tags;
	private int DocumentTypeId;
	private String DocumentType;
	private int OwnerId;
	private int ProfileId;

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(Id);
		dest.writeString(Name);
		dest.writeString(Description);
		dest.writeString(Tags);
		dest.writeInt(DocumentTypeId);
		dest.writeString(DocumentType);
		dest.writeInt(OwnerId);
		dest.writeInt(ProfileId);

	}

	private DocumentList(Parcel in) {
		this.Id = in.readInt();
		this.Name = in.readString();
		this.Description = in.readString();
		this.Tags = in.readString();
		this.DocumentTypeId = in.readInt();
		this.DocumentType = in.readString();
		this.OwnerId = in.readInt();
		this.ProfileId = in.readInt();
	}

	public DocumentList() {
		// TODO Auto-generated constructor stub
	}

	public static final Creator<DocumentList> CREATOR = new Creator<DocumentList>() {

		@Override
		public DocumentList createFromParcel(Parcel source) {
			return new DocumentList(source);
		}

		@Override
		public DocumentList[] newArray(int size) {
			return new DocumentList[size];
		}
	};

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getTags() {
		return Tags;
	}

	public void setTags(String tags) {
		Tags = tags;
	}

	public int getDocumentTypeId() {
		return DocumentTypeId;
	}

	public void setDocumentTypeId(int documentTypeId) {
		DocumentTypeId = documentTypeId;
	}

	public String getDocumentType() {
		return DocumentType;
	}

	public void setDocumentType(String documentType) {
		DocumentType = documentType;
	}

	public int getOwnerId() {
		return OwnerId;
	}

	public void setOwnerId(int ownerId) {
		OwnerId = ownerId;
	}

	public int getProfileId() {
		return ProfileId;
	}

	public void setProfileId(int profileId) {
		ProfileId = profileId;
	}

	public int getAppointmentId() {
		return AppointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		AppointmentId = appointmentId;
	}

	public int getServiceRequestId() {
		return ServiceRequestId;
	}

	public void setServiceRequestId(int serviceRequestId) {
		ServiceRequestId = serviceRequestId;
	}

	public int getProfessionalId() {
		return ProfessionalId;
	}

	public void setProfessionalId(int professionalId) {
		ProfessionalId = professionalId;
	}

	public int getFacilityId() {
		return FacilityId;
	}

	public void setFacilityId(int facilityId) {
		FacilityId = facilityId;
	}

	public int getMemberTypeId() {
		return MemberTypeId;
	}

	public void setMemberTypeId(int memberTypeId) {
		MemberTypeId = memberTypeId;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getDocumentDate() {
		return DocumentDate;
	}

	public void setDocumentDate(String documentDate) {
		DocumentDate = documentDate;
	}

	public String getDocDate() {
		return DocDate;
	}

	public void setDocDate(String docDate) {
		DocDate = docDate;
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

	public String getCreatedOnUtc() {
		return CreatedOnUtc;
	}

	public void setCreatedOnUtc(String createdOnUtc) {
		CreatedOnUtc = createdOnUtc;
	}

	public String getUpdatedOnUtc() {
		return UpdatedOnUtc;
	}

	public void setUpdatedOnUtc(String updatedOnUtc) {
		UpdatedOnUtc = updatedOnUtc;
	}

	private int AppointmentId;
	private int ServiceRequestId;
	private int ProfessionalId;
	private int FacilityId;
	private int MemberTypeId;
	private String URL;
	private String DocumentDate;
	private String DocDate;
	private int CreatedBy;
	private int UpdatedBy;
	private String CreatedOnUtc;
	private String UpdatedOnUtc;
}
