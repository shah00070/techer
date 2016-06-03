package com.schoolteacher.pojos;

public class SearchFilter {
	private String Location;
	private double Latitude;//0
	private double Longitude;//0
	private double Distance; //0
	private int Top; 
	private int Skip;
	private boolean IsVerified;  //false
	private boolean IsPremium; //false
	private boolean IsRecommended; //false

	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();

		builder.append(Location);
		builder.append('|');
		builder.append(Latitude);
		builder.append('|');
		builder.append(Longitude);
		builder.append('|');
		builder.append(Distance);
		builder.append('|');
		builder.append(Top);
		builder.append('|');
		builder.append(Skip);
		builder.append('|');
		builder.append(IsVerified?1:0);
		builder.append('|');
		builder.append(IsPremium?1:0);
		builder.append('|');
		builder.append(IsRecommended?1:0);

		return builder.toString();

	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return Location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		Location = location;
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return Latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		Latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return Longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		Longitude = longitude;
	}

	/**
	 * @return the distance
	 */
	public double getDistance() {
		return Distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(double distance) {
		Distance = distance;
	}

	/**
	 * @return the top
	 */
	public int getTop() {
		return Top;
	}

	/**
	 * @param top the top to set
	 */
	public void setTop(int top) {
		Top = top;
	}

	/**
	 * @return the skip
	 */
	public int getSkip() {
		return Skip;
	}

	/**
	 * @param skip the skip to set
	 */
	public void setSkip(int skip) {
		Skip = skip;
	}

	/**
	 * @return the isVerified
	 */
	public boolean isIsVerified() {
		return IsVerified;
	}

	/**
	 * @param isVerified the isVerified to set
	 */
	public void setIsVerified(boolean isVerified) {
		IsVerified = isVerified;
	}

	/**
	 * @return the isPremium
	 */
	public boolean isIsPremium() {
		return IsPremium;
	}

	/**
	 * @param isPremium the isPremium to set
	 */
	public void setIsPremium(boolean isPremium) {
		IsPremium = isPremium;
	}

	/**
	 * @return the isRecommended
	 */
	public boolean isIsRecommended() {
		return IsRecommended;
	}

	/**
	 * @param isRecommended the isRecommended to set
	 */
	public void setIsRecommended(boolean isRecommended) {
		IsRecommended = isRecommended;
	}

}
