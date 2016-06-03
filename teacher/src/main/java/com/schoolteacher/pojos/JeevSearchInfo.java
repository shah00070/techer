package com.schoolteacher.pojos;

import java.util.List;

public class JeevSearchInfo {
    private int TotalRecords;
    private int TotalRecommended;
    private int TotalFilteredRecords;
    private int TotalFilteredRecommended;
    private List<JeevSearchResult> SearchResults;

    //public object TopResults { get; set; }
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

    public List<JeevSearchResult> getSearchResults() {
        return SearchResults;
    }

    public void setSearchResults(List<JeevSearchResult> searchResults) {
        SearchResults = searchResults;
    }

}
