package com.schoolteacher.pojos;

public class Chat {
	private String IPAddress ;
    private String SentById ;
    private String Message ;
    private String SessionId ;
	private String SentOnOnUTC ;
	public String getIPAddress() {
		return IPAddress;
	}
	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
	}
	public String getSentById() {
		return SentById;
	}
	public void setSentById(String sentById) {
		SentById = sentById;
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
	public String getSentOnOnUTC() {
		return SentOnOnUTC;
	}
	public void setSentOnOnUTC(String sentOnOnUTC) {
		SentOnOnUTC = sentOnOnUTC;
	}
}
