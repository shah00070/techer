package com.schoolteacher.pojos;

import java.io.Serializable;

public class ProfileSearchCategory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 862206618813761632L;
	private int SearchCategoryId; 
	public int getSearchCategoryId() {
		return SearchCategoryId;
	}
	public void setSearchCategoryId(int searchCategoryId) {
		SearchCategoryId = searchCategoryId;
	}
	public String getSearchCategoryName() {
		return SearchCategoryName;
	}
	public void setSearchCategoryName(String searchCategoryName) {
		SearchCategoryName = searchCategoryName;
	}
	private String SearchCategoryName;
}
