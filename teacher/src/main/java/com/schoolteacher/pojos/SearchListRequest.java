package com.schoolteacher.pojos;

import java.util.List;

public class SearchListRequest {
	public String getNumberOfRecords() {
		return NumberOfRecords;
	}

	public void setNumberOfRecords(String numberOfRecords) {
		NumberOfRecords = numberOfRecords;
	}

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

	private String StartIndex;
	private String NumberOfRecords;
	private List<String> CityList;
	private List<String> AreaList;
	private List<String> SearchWhat;
	private List<String> TextInputList;

	public SearchListRequest() {

	}

	public SearchListRequest(String startIndex, String numberOfRecords, List<String> cityList, List<String> areaList, List<String> searchWhat, List<String> textInputList) {
		this.setStartIndex(startIndex);
		this.NumberOfRecords = numberOfRecords;
		this.CityList = cityList;
		this.AreaList = areaList;
		this.SearchWhat = searchWhat;
		this.TextInputList = textInputList;
	}

	public String getStartIndex() {
		return StartIndex;
	}

	public void setStartIndex(String startIndex) {
		StartIndex = startIndex;
	}
}
