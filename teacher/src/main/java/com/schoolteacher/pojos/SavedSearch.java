package com.schoolteacher.pojos;

public class SavedSearch {
	private String Name ;
    private String CriteriaJson ;
    private String FilterJson;
    private int MemberId ;
	private String SavedOnUTC;

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

	public int getMemberId() {
		return MemberId;
	}

	public void setMemberId(int memberId) {
		MemberId = memberId;
	}

	public String getSavedOnUTC() {
		return SavedOnUTC;
	}

	public void setSavedOnUTC(String savedOnUTC) {
		SavedOnUTC = savedOnUTC;
	}
}
