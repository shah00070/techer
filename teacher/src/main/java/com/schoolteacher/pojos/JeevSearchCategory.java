package com.schoolteacher.pojos;

import java.io.Serializable;

public class JeevSearchCategory implements Serializable {

	private static final long serialVersionUID = 1177670180841026096L;
	private boolean doctorClinic;
	private boolean hospitalNursing;
	private boolean labDiagnostic;
	private boolean pharmacies;
	private boolean gymFitness;
	private boolean spaWellness;
	private boolean alternateMedicine;
	private boolean healthcareSupport;
	private boolean healing;

	public boolean isDoctorClinic() {
		return doctorClinic;
	}

	public void setDoctorClinic(boolean doctorClinic) {
		this.doctorClinic = doctorClinic;
	}

	public boolean isHospitalNursing() {
		return hospitalNursing;
	}

	public void setHospitalNursing(boolean hospitalNursing) {
		this.hospitalNursing = hospitalNursing;
	}

	public boolean isLabDiagnostic() {
		return labDiagnostic;
	}

	public void setLabDiagnostic(boolean labDiagnostic) {
		this.labDiagnostic = labDiagnostic;
	}

	public boolean isPharmacies() {
		return pharmacies;
	}

	public void setPharmacies(boolean pharmacies) {
		this.pharmacies = pharmacies;
	}

	public boolean isGymFitness() {
		return gymFitness;
	}

	public void setGymFitness(boolean gymFitness) {
		this.gymFitness = gymFitness;
	}

	public boolean isSpaWellness() {
		return spaWellness;
	}

	public void setSpaWellness(boolean spaWellness) {
		this.spaWellness = spaWellness;
	}

	public boolean isAlternateMedicine() {
		return alternateMedicine;
	}

	public void setAlternateMedicine(boolean alternateMedicine) {
		this.alternateMedicine = alternateMedicine;
	}

	public boolean isHealthcareSupport() {
		return healthcareSupport;
	}

	public void setHealthcareSupport(boolean healthcareSupport) {
		this.healthcareSupport = healthcareSupport;
	}

	public boolean isHealing() {
		return healing;
	}

	public void setHealing(boolean healing) {
		this.healing = healing;
	}

}
