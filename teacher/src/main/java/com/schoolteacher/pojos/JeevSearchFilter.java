package com.schoolteacher.pojos;

import java.io.Serializable;

public class JeevSearchFilter implements Serializable {

	private static final long serialVersionUID = -6934112289007799103L;
	private String Location;
	private double Latitude;
	private double Longitude;
	private int Top;
	private int Skip;
	private int Distance;
	private boolean IsVerified;
	private boolean IsPremium;
	private boolean IsRecommended;
	private boolean IsDiscountOffered;
	private boolean IsLocationBasedSearch;
	private boolean IsOnlinePaymentOffered;

	public boolean isIsLocationBasedSearch() {
		return IsLocationBasedSearch;
	}

	public void setIsLocationBasedSearch(boolean isLocationBasedSearch) {
		IsLocationBasedSearch = isLocationBasedSearch;
	}

	public boolean isIsOnlinePaymentOffered() {
		return IsOnlinePaymentOffered;
	}

	public void setIsOnlinePaymentOffered(boolean isOnlinePaymentOffered) {
		IsOnlinePaymentOffered = isOnlinePaymentOffered;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public double getLatitude() {
		return Latitude;
	}

	public void setLatitude(double latitude) {
		Latitude = latitude;
	}

	public double getLongitude() {
		return Longitude;
	}

	public void setLongitude(double longitude) {
		Longitude = longitude;
	}

	public int getTop() {
		return Top;
	}

	public void setTop(int top) {
		Top = top;
	}

	public int getSkip() {
		return Skip;
	}

	public void setSkip(int skip) {
		Skip = skip;
	}

	public int getDistance() {
		return Distance;
	}

	public void setDistance(int distance) {
		Distance = distance;
	}

	public boolean isIsVerified() {
		return IsVerified;
	}

	public void setIsVerified(boolean isVerified) {
		IsVerified = isVerified;
	}

	public boolean isIsPremium() {
		return IsPremium;
	}

	public void setIsPremium(boolean isPremium) {
		IsPremium = isPremium;
	}

	public boolean isIsRecommended() {
		return IsRecommended;
	}

	public void setIsRecommended(boolean isRecommended) {
		IsRecommended = isRecommended;
	}

	public boolean isIsDiscountOffered() {
		return IsDiscountOffered;
	}

	public void setIsDiscountOffered(boolean isDiscountOffered) {
		IsDiscountOffered = isDiscountOffered;
	}
}
