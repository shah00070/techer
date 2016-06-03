package com.schoolteacher.pojos;

public class SRStatusLog {
	private int Id;
	private int ServiceRequisitionId;
	private int FromStatusId;
	private String FromStatusName;
	private String FromStatusDescription;
	private int FromStatusPublicKey;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getServiceRequisitionId() {
		return ServiceRequisitionId;
	}

	public void setServiceRequisitionId(int serviceRequisitionId) {
		ServiceRequisitionId = serviceRequisitionId;
	}

	public int getFromStatusId() {
		return FromStatusId;
	}

	public void setFromStatusId(int fromStatusId) {
		FromStatusId = fromStatusId;
	}

	public String getFromStatusName() {
		return FromStatusName;
	}

	public void setFromStatusName(String fromStatusName) {
		FromStatusName = fromStatusName;
	}

	public String getFromStatusDescription() {
		return FromStatusDescription;
	}

	public void setFromStatusDescription(String fromStatusDescription) {
		FromStatusDescription = fromStatusDescription;
	}

	public int getFromStatusPublicKey() {
		return FromStatusPublicKey;
	}

	public void setFromStatusPublicKey(int fromStatusPublicKey) {
		FromStatusPublicKey = fromStatusPublicKey;
	}

	public int getToStatusId() {
		return ToStatusId;
	}

	public void setToStatusId(int toStatusId) {
		ToStatusId = toStatusId;
	}

	public String getToStatusName() {
		return ToStatusName;
	}

	public void setToStatusName(String toStatusName) {
		ToStatusName = toStatusName;
	}

	public String getToStatusDescription() {
		return ToStatusDescription;
	}

	public void setToStatusDescription(String toStatusDescription) {
		ToStatusDescription = toStatusDescription;
	}

	public int getToStatusPublicKey() {
		return ToStatusPublicKey;
	}

	public void setToStatusPublicKey(int toStatusPublicKey) {
		ToStatusPublicKey = toStatusPublicKey;
	}

	public int getChangedBy() {
		return ChangedBy;
	}

	public void setChangedBy(int changedBy) {
		ChangedBy = changedBy;
	}

	public int getChangedByUserType() {
		return ChangedByUserType;
	}

	public void setChangedByUserType(int changedByUserType) {
		ChangedByUserType = changedByUserType;
	}

	public String getChangedOn() {
		return ChangedOn;
	}

	public void setChangedOn(String changedOn) {
		ChangedOn = changedOn;
	}

	public String getChangeType() {
		return ChangeType;
	}

	public void setChangeType(String changeType) {
		ChangeType = changeType;
	}

	private int ToStatusId;
	private String ToStatusName;
	private String ToStatusDescription;
	private int ToStatusPublicKey;
	private int ChangedBy;
	private int ChangedByUserType;
	private String ChangedOn;
	private String ChangeType;
}
