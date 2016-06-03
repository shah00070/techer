package com.schoolteacher.pojos;

public class BmrRecommendation {

	private double bmr;
	private String typeOfActivity;
	private double DailyCalorieRequirement;
	/**
	 * @return the typeOfActivity
	 */
	public String getTypeOfActivity() {
		return typeOfActivity;
	}
	/**
	 * @param typeOfActivity the typeOfActivity to set
	 */
	public void setTypeOfActivity(String typeOfActivity) {
		this.typeOfActivity = typeOfActivity;
	}
	/**
	 * @return the bmr
	 */
	public double getBmr() {
		return bmr;
	}
	/**
	 * @param bmr the bmr to set
	 */
	public void setBmr(double bmr) {
		this.bmr = bmr;
	}
	/**
	 * @return the dailyCalorieRequirement
	 */
	public double getDailyCalorieRequirement() {
		return DailyCalorieRequirement;
	}
	/**
	 * @param dailyCalorieRequirement the dailyCalorieRequirement to set
	 */
	public void setDailyCalorieRequirement(double dailyCalorieRequirement) {
		DailyCalorieRequirement = dailyCalorieRequirement;
	}
}
