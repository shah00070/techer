package com.schoolteacher.pojos;

public class AdditionalConsumerDetail {
	public int Id;
	public String Weight;
	public String Height;
	public Object PulseRate;
	public Object BP;
	public String BloodGroup;
	public String BMI;
	public String BMR;
	public String SpecificMedicalCondition;
	public String Allergies;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getWeight() {
		return Weight;
	}

	public void setWeight(String weight) {
		Weight = weight;
	}

	public String getHeight() {
		return Height;
	}

	public void setHeight(String height) {
		Height = height;
	}

	public Object getPulseRate() {
		return PulseRate;
	}

	public void setPulseRate(Object pulseRate) {
		PulseRate = pulseRate;
	}

	public Object getBP() {
		return BP;
	}

	public void setBP(Object bP) {
		BP = bP;
	}

	public String getBloodGroup() {
		return BloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		BloodGroup = bloodGroup;
	}

	public String getBMI() {
		return BMI;
	}

	public void setBMI(String bMI) {
		BMI = bMI;
	}

	public String getBMR() {
		return BMR;
	}

	public void setBMR(String bMR) {
		BMR = bMR;
	}

	public String getSpecificMedicalCondition() {
		return SpecificMedicalCondition;
	}

	public void setSpecificMedicalCondition(String specificMedicalCondition) {
		SpecificMedicalCondition = specificMedicalCondition;
	}

	public String getAllergies() {
		return Allergies;
	}

	public void setAllergies(String allergies) {
		Allergies = allergies;
	}

	public String getHeredityMedicalCondition() {
		return HeredityMedicalCondition;
	}

	public void setHeredityMedicalCondition(String heredityMedicalCondition) {
		HeredityMedicalCondition = heredityMedicalCondition;
	}

	private String HeredityMedicalCondition;
}
