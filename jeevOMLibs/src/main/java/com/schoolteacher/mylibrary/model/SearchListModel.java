package com.schoolteacher.mylibrary.model;


import java.util.List;

public class SearchListModel {
	private int StartIndex;
	private int NumberOfRecords;
	private List<String> CityList;
	private List<String> AreaList;
	private List<String> SearchWhat;
	private List<String> TextInputList;

	public List<String> getCityList() {
		return CityList;
	}

	public void setCityList(List<String> cityList) {
		CityList = cityList;
	}

	public List<String> getAreaList() {
		return AreaList;
	}

	public void setAreaList(List<String> areaList) {
		AreaList = areaList;
	}

	public List<String> getSearchWhat() {
		return SearchWhat;
	}

	public void setSearchWhat(List<String> searchWhat) {
		SearchWhat = searchWhat;
	}

	public List<String> getTextInputList() {
		return TextInputList;
	}

	public void setTextInputList(List<String> textInputList) {
		TextInputList = textInputList;
	}

	public int getStartIndex() {
		return StartIndex;
	}

	public void setStartIndex(int startIndex) {
		StartIndex = startIndex;
	}

	public int getNumberOfRecords() {
		return NumberOfRecords;
	}

	public void setNumberOfRecords(int numberOfRecords) {
		NumberOfRecords = numberOfRecords;
	}
}
