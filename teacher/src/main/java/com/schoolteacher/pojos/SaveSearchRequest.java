package com.schoolteacher.pojos;

public class SaveSearchRequest {

	private String Name;
	private String CriteriaJson;
	private String FilterJson;
	private String MemberId;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getCriteriaJson() {
		return CriteriaJson;
	}

	public void setCriteriaJson(String criteriaJson) {
		CriteriaJson = criteriaJson;
	}

	public String getFilterJson() {
		return FilterJson;
	}

	public void setFilterJson(String filterJson) {
		FilterJson = filterJson;
	}

	public String getMemberId() {
		return MemberId;
	}

	public void setMemberId(String memberId) {
		MemberId = memberId;
	}
}
