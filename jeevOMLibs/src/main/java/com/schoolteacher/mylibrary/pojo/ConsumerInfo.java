package com.schoolteacher.mylibrary.pojo;

import java.util.List;

// This model class if consumer info for the service "/profile/Member/Profiles/{member id}"
public class ConsumerInfo {
	private String MemberUniqueId;
	private List<ConsumerProfiles> MemberProfiles;
	/**
	 * @return the memberUniqueId
	 */
	public String getMemberUniqueId() {
		return MemberUniqueId;
	}
	/**
	 * @param memberUniqueId the memberUniqueId to set
	 */
	public void setMemberUniqueId(String memberUniqueId) {
		MemberUniqueId = memberUniqueId;
	}
	/**
	 * @return the memberProfiles
	 */
	public List<ConsumerProfiles> getMemberProfiles() {
		return MemberProfiles;
	}
	/**
	 * @param memberProfiles the memberProfiles to set
	 */
	public void setMemberProfiles(List<ConsumerProfiles> memberProfiles) {
		MemberProfiles = memberProfiles;
	}
}
