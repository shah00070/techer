package com.schoolteacher.library.pojo;

public class AddExternalUserResponse {
	/*
	 * gaurav gupta - 5/26/2015
	 */
	private int MemberId;

	public int getMemberId() {
		return MemberId;
	}

	public void setMemberId(int memberId) {
		MemberId = memberId;
	}

	public boolean isIsSignedUpSuccessfully() {
		return IsSignedUpSuccessfully;
	}

	public void setIsSignedUpSuccessfully(boolean isSignedUpSuccessfully) {
		IsSignedUpSuccessfully = isSignedUpSuccessfully;
	}

	public boolean isIsLoggedInSuccessfully() {
		return IsLoggedInSuccessfully;
	}

	public void setIsLoggedInSuccessfully(boolean isLoggedInSuccessfully) {
		IsLoggedInSuccessfully = isLoggedInSuccessfully;
	}

	public boolean isIsClaimProfileCheckRequired() {
		return IsClaimProfileCheckRequired;
	}

	public void setIsClaimProfileCheckRequired(
			boolean isClaimProfileCheckRequired) {
		IsClaimProfileCheckRequired = isClaimProfileCheckRequired;
	}

	private boolean IsSignedUpSuccessfully;
	private boolean IsLoggedInSuccessfully;
	private boolean IsClaimProfileCheckRequired;
}
