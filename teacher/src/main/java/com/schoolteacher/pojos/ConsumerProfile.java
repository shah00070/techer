package com.schoolteacher.pojos;

public class ConsumerProfile {
	private String IdenitityMarks;
	private String MedicalConditions;
	private String Allergies;
	private String Weight;
	private String Height;
	private String HeightUnit;

//	@Override
//	public String toString() {
//		// TODO Auto-generated method stub
//		return "{" + "IdenitityMarks" + ":" + getIdenitityMarks() + "MedicalConditions" + ":" + getMedicalConditions() + "Allergies" + ":" + getAllergies() + "Weight" + ":" + getWeight() + "Height"
//				+ ":" + getHeight() + "HeightUnit" + "}";
//	}

	/**
	 * @return the idenitityMarks
	 */
	public String getIdenitityMarks() {
		return IdenitityMarks;
	}

	/**
	 * @param idenitityMarks
	 *            the idenitityMarks to set
	 */
	public void setIdenitityMarks(String idenitityMarks) {
		IdenitityMarks = idenitityMarks;
	}

	/**
	 * @return the medicalConditions
	 */
	public String getMedicalConditions() {
		return MedicalConditions;
	}

	/**
	 * @param medicalConditions
	 *            the medicalConditions to set
	 */
	public void setMedicalConditions(String medicalConditions) {
		MedicalConditions = medicalConditions;
	}

	/**
	 * @return the allergies
	 */
	public String getAllergies() {
		return Allergies;
	}

	/**
	 * @param allergies
	 *            the allergies to set
	 */
	public void setAllergies(String allergies) {
		Allergies = allergies;
	}

	/**
	 * @return the weight
	 */
	public String getWeight() {
		return Weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(String weight) {
		Weight = weight;
	}

	/**
	 * @return the height
	 */
	public String getHeight() {
		return Height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(String height) {
		Height = height;
	}

	/**
	 * @return the heightUnit
	 */
	public String getHeightUnit() {
		return HeightUnit;
	}

	/**
	 * @param heightUnit
	 *            the heightUnit to set
	 */
	public void setHeightUnit(String heightUnit) {
		HeightUnit = heightUnit;
	}
}
