package com.schoolteacher.pojos;

public class FilterSearch {

	private String searchWhat;
	private String searchWhere;
	private double latitude;
	private double longitude;
	private int noOfRecords;
	private int pageNo;
	/**
	 * @return the searchWhat
	 */
	public String getSearchWhat() {
		return searchWhat;
	}
	/**
	 * @param searchWhat the searchWhat to set
	 */
	public void setSearchWhat(String searchWhat) {
		this.searchWhat = searchWhat;
	}
	/**
	 * @return the searchWhere
	 */
	public String getSearchWhere() {
		return searchWhere;
	}
	/**
	 * @param searchWhere the searchWhere to set
	 */
	public void setSearchWhere(String searchWhere) {
		this.searchWhere = searchWhere;
	}
	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the noOfRecords
	 */
	public int getNoOfRecords() {
		return noOfRecords;
	}
	/**
	 * @param noOfRecords the noOfRecords to set
	 */
	public void setNoOfRecords(int noOfRecords) {
		this.noOfRecords = noOfRecords;
	}
	/**
	 * @return the pageNo
	 */
	public int getPageNo() {
		return pageNo;
	}
	/**
	 * @param pageNo the pageNo to set
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
}
