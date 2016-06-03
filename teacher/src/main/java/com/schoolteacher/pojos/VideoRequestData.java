package com.schoolteacher.pojos;

public class VideoRequestData {
	private String IPAddress;
	private int MemberId;
	private String Message;
	private String SessionId;

	public String getIPAddress() {
		return IPAddress;
	}

	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
	}

	public int getMemberId() {
		return MemberId;
	}

	public void setMemberId(int memberId) {
		MemberId = memberId;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public String getSessionId() {
		return SessionId;
	}

	public void setSessionId(String sessionId) {
		SessionId = sessionId;
	}
}
