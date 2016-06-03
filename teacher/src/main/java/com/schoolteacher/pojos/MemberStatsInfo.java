package com.schoolteacher.pojos;

public class MemberStatsInfo {
	private BmiRange bmiRange;
	private BmrRecommendation bmrRecommendation;
	/**
	 * @return the bmiRange
	 */
	public BmiRange getBmiRange() {
		return bmiRange;
	}
	/**
	 * @param bmiRange the bmiRange to set
	 */
	public void setBmiRange(BmiRange bmiRange) {
		this.bmiRange = bmiRange;
	}
	/**
	 * @return the bmrRecommendation
	 */
	public BmrRecommendation getBmrRecommendation() {
		return bmrRecommendation;
	}
	/**
	 * @param bmrRecommendation the bmrRecommendation to set
	 */
	public void setBmrRecommendation(BmrRecommendation bmrRecommendation) {
		this.bmrRecommendation = bmrRecommendation;
	}
}
