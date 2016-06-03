package com.schoolteacher.mylibrary.model;

public class NavDrawerItem {

	private String title;

	public NavDrawerItem(String navMenuTitles) {
		this.title = navMenuTitles;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
