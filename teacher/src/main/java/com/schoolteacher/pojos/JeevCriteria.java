package com.schoolteacher.pojos;

import java.io.Serializable;

public class JeevCriteria implements Serializable {

	private static final long serialVersionUID = -2579744332754008866L;
	private String searchString;
	private JeevSearchCategory category;
	private JeevSearchGender gender;
	private JeevSearchAvailability availability;
	private JeevSearchTiming timing;
	private JeevSearchRequisition requisition;
	private boolean isVerified;
	private boolean isValued;
	private boolean isDiscount;

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public JeevSearchCategory getCategory() {
		return category;
	}

	public void setCategory(JeevSearchCategory category) {
		this.category = category;
	}

	public JeevSearchGender getGender() {
		return gender;
	}

	public void setGender(JeevSearchGender gender) {
		this.gender = gender;
	}

	public JeevSearchAvailability getAvailability() {
		return availability;
	}

	public void setAvailability(JeevSearchAvailability availability) {
		this.availability = availability;
	}

	public JeevSearchTiming getTiming() {
		return timing;
	}

	public void setTiming(JeevSearchTiming timing) {
		this.timing = timing;
	}

	public JeevSearchRequisition getRequisition() {
		return requisition;
	}

	public void setRequisition(JeevSearchRequisition requisition) {
		this.requisition = requisition;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public boolean isValued() {
		return isValued;
	}

	public void setValued(boolean isValued) {
		this.isValued = isValued;
	}

	public boolean isDiscount() {
		return isDiscount;
	}

	public void setDiscount(boolean isDiscount) {
		this.isDiscount = isDiscount;
	}
}
