package com.schoolteacher.pojos;

import java.util.List;

public class VideoResponse {
	private String CreatedByIPAddress;
	private String CreatedById;
	private String SRPublicId;
	private String CreatedOnOnUTC;
	// private object DisconnectedOnUTC ;
	private String SessionId;
	private String OTAPIKey;
	private String OTAPISecretKey;
	private String OTAPITokenKey;
	private int SessionType;
	private int SessionStatus;
	private List<Chat> Chats;

	public String getCreatedByIPAddress() {
		return CreatedByIPAddress;
	}

	public void setCreatedByIPAddress(String createdByIPAddress) {
		CreatedByIPAddress = createdByIPAddress;
	}

	public String getCreatedById() {
		return CreatedById;
	}

	public void setCreatedById(String createdById) {
		CreatedById = createdById;
	}

	public String getSRPublicId() {
		return SRPublicId;
	}

	public void setSRPublicId(String sRPublicId) {
		SRPublicId = sRPublicId;
	}

	public String getCreatedOnOnUTC() {
		return CreatedOnOnUTC;
	}

	public void setCreatedOnOnUTC(String createdOnOnUTC) {
		CreatedOnOnUTC = createdOnOnUTC;
	}

	public String getSessionId() {
		return SessionId;
	}

	public void setSessionId(String sessionId) {
		SessionId = sessionId;
	}

	public String getOTAPIKey() {
		return OTAPIKey;
	}

	public void setOTAPIKey(String oTAPIKey) {
		OTAPIKey = oTAPIKey;
	}

	public String getOTAPISecretKey() {
		return OTAPISecretKey;
	}

	public void setOTAPISecretKey(String oTAPISecretKey) {
		OTAPISecretKey = oTAPISecretKey;
	}

	public String getOTAPITokenKey() {
		return OTAPITokenKey;
	}

	public void setOTAPITokenKey(String oTAPITokenKey) {
		OTAPITokenKey = oTAPITokenKey;
	}

	public int getSessionType() {
		return SessionType;
	}

	public void setSessionType(int sessionType) {
		SessionType = sessionType;
	}

	public int getSessionStatus() {
		return SessionStatus;
	}

	public void setSessionStatus(int sessionStatus) {
		SessionStatus = sessionStatus;
	}

	public List<Chat> getChats() {
		return Chats;
	}

	public void setChats(List<Chat> chats) {
		Chats = chats;
	}

}
