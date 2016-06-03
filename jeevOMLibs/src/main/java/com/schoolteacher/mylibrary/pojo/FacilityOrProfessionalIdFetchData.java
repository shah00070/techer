package com.schoolteacher.mylibrary.pojo;

import java.util.List;

public class FacilityOrProfessionalIdFetchData {
	private String MemberUniqueId;
	private List<MemberProfileType> MemberProfiles;
	
	public String getDefaultProfileType() {
		return MemberUniqueId;
	}

	public void setDefaultProfileType(String defaultProfileType) {
		MemberUniqueId = defaultProfileType;
	}

	public List<MemberProfileType> getMemberProfiles() {
		return MemberProfiles;
	}

	public void setMemberProfiles(List<MemberProfileType> memberProfiles) {
		MemberProfiles = memberProfiles;
	}
}
