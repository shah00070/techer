package com.schoolteacher.mylibrary.pojo;

import java.io.Serializable;
import java.util.List;

public class SearchResultInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int TotalRecords;
	private int TotalRecommended;
	private int TotalFilteredRecords;
	private int TotalFilteredRecommended;
	private List<SearchResult> SearchResults;

	public int getTotalRecords() {
		return TotalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		TotalRecords = totalRecords;
	}

	public int getTotalRecommended() {
		return TotalRecommended;
	}

	public void setTotalRecommended(int totalRecommended) {
		TotalRecommended = totalRecommended;
	}

	public int getTotalFilteredRecords() {
		return TotalFilteredRecords;
	}

	public void setTotalFilteredRecords(int totalFilteredRecords) {
		TotalFilteredRecords = totalFilteredRecords;
	}

	public int getTotalFilteredRecommended() {
		return TotalFilteredRecommended;
	}

	public void setTotalFilteredRecommended(int totalFilteredRecommended) {
		TotalFilteredRecommended = totalFilteredRecommended;
	}

	public List<SearchResult> getSearchResults() {
		return SearchResults;
	}

	public void setSearchResults(List<SearchResult> searchResults) {
		SearchResults = searchResults;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
