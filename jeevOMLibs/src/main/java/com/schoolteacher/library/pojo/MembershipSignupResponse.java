package com.schoolteacher.library.pojo;

import java.io.Serializable;

public class MembershipSignupResponse implements Serializable {
	/**
	 * Gaurav Gupta -- 5/24/2015
	 */
	private static final long serialVersionUID = -7120431933362862550L;
	private int MemberId;
	private boolean IsSignedUpSuccessfully;
	private boolean IsLoggedInSuccessfully;
	private boolean IsClaimProfileCheckRequired;

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
}
