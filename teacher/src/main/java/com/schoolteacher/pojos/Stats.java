package com.schoolteacher.pojos;

import java.io.Serializable;

public class Stats implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3743375526675601578L;
	private String ViewCount;
	private String AverageRating;
	private String RatedCount;

	public String getViewCount() {
		return ViewCount;
	}

	public void setViewCount(String viewCount) {
		ViewCount = viewCount;
	}

	public String getAverageRating() {
		return AverageRating;
	}

	public void setAverageRating(String averageRating) {
		AverageRating = averageRating;
	}

	public String getRatedCount() {
		return RatedCount;
	}

	public void setRatedCount(String ratedCount) {
		RatedCount = ratedCount;
	}
}
