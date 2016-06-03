package com.schoolteacher.mylibrary.model;

import java.io.Serializable;

public class ServiceRequestList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Id;
	private int UniquePublicID;
	private String Name;
	private boolean IsDeleted;
	private String Date;
	private String Time;
	private String ServiceRequestType;
	private String ContactNumber;
	private String ServiceRequestTypeImageURL;
	private String Status;
	private boolean IsApproved;
	private int ServiceRequestMessageId;
	private String Message;
	private String Email;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getUniquePublicID() {
		return UniquePublicID;
	}

	public void setUniquePublicID(int uniquePublicID) {
		UniquePublicID = uniquePublicID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public boolean isIsDeleted() {
		return IsDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		IsDeleted = isDeleted;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public String getServiceRequestType() {
		return ServiceRequestType;
	}

	public void setServiceRequestType(String serviceRequestType) {
		ServiceRequestType = serviceRequestType;
	}

	public String getContactNumber() {
		return ContactNumber;
	}

	public void setContactNumber(String contactNumber) {
		ContactNumber = contactNumber;
	}

	public String getServiceRequestTypeImageURL() {
		return ServiceRequestTypeImageURL;
	}

	public void setServiceRequestTypeImageURL(String serviceRequestTypeImageURL) {
		ServiceRequestTypeImageURL = serviceRequestTypeImageURL;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public boolean isIsApproved() {
		return IsApproved;
	}

	public void setIsApproved(boolean isApproved) {
		IsApproved = isApproved;
	}

	public int getServiceRequestMessageId() {
		return ServiceRequestMessageId;
	}

	public void setServiceRequestMessageId(int serviceRequestMessageId) {
		ServiceRequestMessageId = serviceRequestMessageId;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}
}
