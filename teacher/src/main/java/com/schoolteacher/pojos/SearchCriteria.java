package com.schoolteacher.pojos;

public class SearchCriteria {

	private final String ONE = "1";
	private final String ZERO = "0";

	private String Search;
	private boolean EmailConsultation;  //false
	private boolean HomeVisitConsultation; //false
	private boolean PhoneConsultation; //false
	private boolean VideoConsultation; //false
	private boolean TextChatConsultation; //false
	private boolean FaceToFaceConsultation; //false
	private boolean OnlyMales; //false
	private boolean OnlyFemales; //false
	private String Type; //Both

	/**
	 * @return the search
	 */
	public String getSearch() {
		return Search;
	}

	/**
	 * @param search
	 *            the search to set
	 */
	public void setSearch(String search) {
		Search = search;
	}

	/**
	 * @return the emailConsultation
	 */
	public boolean isEmailConsultation() {
		return EmailConsultation;
	}

	/**
	 * @param emailConsultation
	 *            the emailConsultation to set
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
	 *            the homeVisitConsultation to set
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
	 *            the phoneConsultation to set
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
	 *            the videoConsultation to set
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
	 *            the textChatConsultation to set
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
	 *            the faceToFaceConsultation to set
	 */
	public void setFaceToFaceConsultation(boolean faceToFaceConsultation) {
		FaceToFaceConsultation = faceToFaceConsultation;
	}

	/**
	 * @return the onlyMales
	 */
	public boolean isOnlyMales() {
		return OnlyMales;
	}

	/**
	 * @param onlyMales
	 *            the onlyMales to set
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
	 *            the onlyFemales to set
	 */
	public void setOnlyFemales(boolean onlyFemales) {
		OnlyFemales = onlyFemales;
	}

	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();

		builder.append(Search);
		builder.append('|');
		builder.append(EmailConsultation ? 1 : 0);
		builder.append('|');
		builder.append(HomeVisitConsultation ? 1 : 0);
		builder.append('|');
		builder.append(PhoneConsultation ? 1 : 0);
		builder.append('|');
		builder.append(VideoConsultation ? 1 : 0);
		builder.append('|');
		builder.append(TextChatConsultation ? 1 : 0);
		builder.append('|');
		builder.append(FaceToFaceConsultation ? 1 : 0);
		builder.append('|');
		builder.append(OnlyMales ? 1 : 0);
		builder.append('|');
		builder.append(OnlyFemales ? 1 : 0);
		builder.append('|');
		builder.append(Type);
		//builder.append('|');

		return builder.toString();

	}

	/**
	 * @return the type
	 */
	public String getType() {
		return Type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		Type = type;
	}

}
