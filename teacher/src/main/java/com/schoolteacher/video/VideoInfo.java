package com.schoolteacher.video;

public class VideoInfo {
	private String SessionId;
	private String Token;
	private String ApiKey;
	private String ApiSecret;
	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return SessionId;
	}
	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		SessionId = sessionId;
	}
	/**
	 * @return the token
	 */
	public String getToken() {
		return Token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		Token = token;
	}
	/**
	 * @return the apiKey
	 */
	public String getApiKey() {
		return ApiKey;
	}
	/**
	 * @param apiKey the apiKey to set
	 */
	public void setApiKey(String apiKey) {
		ApiKey = apiKey;
	}
	/**
	 * @return the apiSecret
	 */
	public String getApiSecret() {
		return ApiSecret;
	}
	/**
	 * @param apiSecret the apiSecret to set
	 */
	public void setApiSecret(String apiSecret) {
		ApiSecret = apiSecret;
	}

}
