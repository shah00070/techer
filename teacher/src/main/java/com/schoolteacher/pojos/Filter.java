package com.schoolteacher.pojos;

import java.io.Serializable;

public class Filter implements Serializable {
	 private double Distance;
	
	 private boolean EmailConsultation;
	
	 private boolean HomeVisitConsultation;
	
	 private boolean PhoneConsultation;
	
	 private boolean VideoConsultation;
	
	 private boolean TextChatConsultation;
	
	 private boolean FaceToFaceConsultation;
	
	 private boolean IsVerified;
	
	 private boolean IsPremium;
	
	 private boolean IsRecommended;
	
	 private boolean OnlyMales;
	
	 private boolean OnlyFemales;
	
	 private String Type;
	
	 /**
	 * @return the distance
	 */
	 public double getDistance() {
	 return Distance;
	 }
	
	 /**
	 * @param distance
	 * the distance to set
	 */
	 public void setDistance(double distance) {
	 Distance = distance;
	 }
	
	 /**
	 * @return the emailConsultation
	 */
	 public boolean isEmailConsultation() {
	 return EmailConsultation;
	 }
	
	 /**
	 * @param emailConsultation
	 * the emailConsultation to set
	 */
	 public void setEmailConsultation(boolean emailConsultation) {
	 EmailConsultation = emailConsultation;
	 }
	
	 /**
	 * @return the homeVisitConsultation
	 */
	 public boolean isHomeVisitConsultation() {
	 return HomeVisitConsultation;
	 }
	
	 /**
	 * @param homeVisitConsultation
	 * the homeVisitConsultation to set
	 */
	 public void setHomeVisitConsultation(boolean homeVisitConsultation) {
	 HomeVisitConsultation = homeVisitConsultation;
	 }
	
	 /**
	 * @return the phoneConsultation
	 */
	 public boolean isPhoneConsultation() {
	 return PhoneConsultation;
	 }
	
	 /**
	 * @param phoneConsultation
	 * the phoneConsultation to set
	 */
	 public void setPhoneConsultation(boolean phoneConsultation) {
	 PhoneConsultation = phoneConsultation;
	 }
	
	 /**
	 * @return the videoConsultation
	 */
	 public boolean isVideoConsultation() {
	 return VideoConsultation;
	 }
	
	 /**
	 * @param videoConsultation
	 * the videoConsultation to set
	 */
	 public void setVideoConsultation(boolean videoConsultation) {
	 VideoConsultation = videoConsultation;
	 }
	
	 /**
	 * @return the textChatConsultation
	 */
	 public boolean isTextChatConsultation() {
	 return TextChatConsultation;
	 }
	
	 /**
	 * @param textChatConsultation
	 * the textChatConsultation to set
	 */
	 public void setTextChatConsultation(boolean textChatConsultation) {
	 TextChatConsultation = textChatConsultation;
	 }
	
	 /**
	 * @return the faceToFaceConsultation
	 */
	 public boolean isFaceToFaceConsultation() {
	 return FaceToFaceConsultation;
	 }
	
	 /**
	 * @param faceToFaceConsultation
	 * the faceToFaceConsultation to set
	 */
	 public void setFaceToFaceConsultation(boolean faceToFaceConsultation) {
	 FaceToFaceConsultation = faceToFaceConsultation;
	 }
	
	 /**
	 * @return the isVerified
	 */
	 public boolean isIsVerified() {
	 return IsVerified;
	 }
	
	 /**
	 * @param isVerified
	 * the isVerified to set
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
	 * @param isPremium
	 * the isPremium to set
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
	 * @param isRecommended
	 * the isRecommended to set
	 */
	 public void setIsRecommended(boolean isRecommended) {
	 IsRecommended = isRecommended;
	 }
	
	 /**
	 * @return the onlyMales
	 */
	 public boolean isOnlyMales() {
	 return OnlyMales;
	 }
	
	 /**
	 * @param onlyMales
	 * the onlyMales to set
	 */
	 public void setOnlyMales(boolean onlyMales) {
	 OnlyMales = onlyMales;
	 }
	
	 /**
	 * @return the onlyFemales
	 */
	 public boolean isOnlyFemales() {
	 return OnlyFemales;
	 }
	
	 /**
	 * @param onlyFemales
	 * the onlyFemales to set
	 */
	 public void setOnlyFemales(boolean onlyFemales) {
	 OnlyFemales = onlyFemales;
	 }
	
	 /**
	 * @return the type
	 */
	 public String getType() {
	 return Type;
	 }
	
	 /**
	 * @param type
	 * the type to set
	 */
	 public void setType(String type) {
	 Type = type;
	 }
	
	 @Override
	 public String toString() {
	 StringBuilder builder = new StringBuilder();
	
	 builder.append(Distance);
	 builder.append(',');
	 builder.append(EmailConsultation ? 1 : 0);
	 builder.append(',');
	 builder.append(HomeVisitConsultation ? 1 : 0);
	 builder.append(',');
	 builder.append(PhoneConsultation ? 1 : 0);
	 builder.append(',');
	 builder.append(VideoConsultation ? 1 : 0);
	 builder.append(',');
	 builder.append(TextChatConsultation ? 1 : 0);
	 builder.append(',');
	 builder.append(FaceToFaceConsultation ? 1 : 0);
	 builder.append(',');
	 builder.append(IsVerified ? 1 : 0);
	 builder.append(',');
	 builder.append(IsPremium ? 1 : 0);
	 builder.append(',');
	 builder.append(IsRecommended ? 1 : 0);
	 builder.append(',');
	 builder.append(OnlyMales ? 1 : 0);
	 builder.append(',');
	 builder.append(OnlyFemales ? 1 : 0);
	 builder.append(',');
	 builder.append(getType());
	
	
	 return builder.toString();
	 }
	
//	private double distance;
//	private int rating;
//	private int recommended;
//	private int premium;
//	private int verified;
//	private int feeAvailable;
//	private int online;
//	private String type;
//
//	/**
//	 * @return the distance
//	 */
//	public double getDistance() {
//		return distance;
//	}
//
//	/**
//	 * @param distance
//	 *            the distance to set
//	 */
//	public void setDistance(double distance) {
//		this.distance = distance;
//	}
//
//	/**
//	 * @return the rating
//	 */
//	public int getRating() {
//		return rating;
//	}
//
//	/**
//	 * @param rating
//	 *            the rating to set
//	 */
//	public void setRating(int rating) {
//		this.rating = rating;
//	}
//
//	/**
//	 * @return the recommended
//	 */
//	public int getRecommended() {
//		return recommended;
//	}
//
//	/**
//	 * @param recommended
//	 *            the recommended to set
//	 */
//	public void setRecommended(int recommended) {
//		this.recommended = recommended;
//	}
//
//	/**
//	 * @return the premium
//	 */
//	public int getPremium() {
//		return premium;
//	}
//
//	/**
//	 * @param premium
//	 *            the premium to set
//	 */
//	public void setPremium(int premium) {
//		this.premium = premium;
//	}
//
//	/**
//	 * @return the verified
//	 */
//	public int getVerified() {
//		return verified;
//	}
//
//	/**
//	 * @param verified
//	 *            the verified to set
//	 */
//	public void setVerified(int verified) {
//		this.verified = verified;
//	}
//
//	/**
//	 * @return the feeAvailable
//	 */
//	public int getFeeAvailable() {
//		return feeAvailable;
//	}
//
//	/**
//	 * @param feeAvailable
//	 *            the feeAvailable to set
//	 */
//	public void setFeeAvailable(int feeAvailable) {
//		this.feeAvailable = feeAvailable;
//	}
//
//	/**
//	 * @return the online
//	 */
//	public int getOnline() {
//		return online;
//	}
//
//	/**
//	 * @param online
//	 *            the online to set
//	 */
//	public void setOnline(int online) {
//		this.online = online;
//	}
//
//	/**
//	 * @return the type
//	 */
//	public String getType() {
//		return type;
//	}
//
//	/**
//	 * @param type
//	 *            the type to set
//	 */
//	public void setType(String type) {
//		this.type = type;
//	}

}
