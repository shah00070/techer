package com.schoolteacher.pojos;

public class AppointmentRequestData {

	private String MemberId; // done
	private int ProfessionProfileId;// done
	private int FacilityId;// done
	private int AvailabilityId;// done
	private boolean IsBookedSelf;// done
	private String BookedForMemberId;
	private String BookedForName;// done
	private int BookedForAge;// done
	private String BookedForGender;
	private String AppointmentDate;// done
	private String AppointmentTime;// done
	private String SlotTime;// done
	private int AppointmentMode;// done
	private boolean IsBookedFromDefaultAvailability;// done
	private int Slot;// done
	private String Message;// done

	/**
	 * @return the memberId
	 */
	public String getMemberId() {
		return MemberId;
	}

	/**
	 * @param memberId
	 *            the memberId to set
	 */
	public void setMemberId(String memberId) {
		MemberId = memberId;
	}

	/**
	 * @return the professionProfileId
	 */
	public int getProfessionProfileId() {
		return ProfessionProfileId;
	}

	/**
	 * @param professionProfileId
	 *            the professionProfileId to set
	 */
	public void setProfessionProfileId(int professionProfileId) {
		ProfessionProfileId = professionProfileId;
	}

	/**
	 * @return the facilityId
	 */
	public int getFacilityId() {
		return FacilityId;
	}

	/**
	 * @param facilityId
	 *            the facilityId to set
	 */
	public void setFacilityId(int facilityId) {
		FacilityId = facilityId;
	}

	/**
	 * @return the availabilityId
	 */
	public int getAvailabilityId() {
		return AvailabilityId;
	}

	/**
	 * @param availabilityId
	 *            the availabilityId to set
	 */
	public void setAvailabilityId(int availabilityId) {
		AvailabilityId = availabilityId;
	}

	/**
	 * @return the isBookedSelf
	 */
	public boolean isIsBookedSelf() {
		return IsBookedSelf;
	}

	/**
	 * @param isBookedSelf
	 *            the isBookedSelf to set
	 */
	public void setIsBookedSelf(boolean isBookedSelf) {
		IsBookedSelf = isBookedSelf;
	}

	/**
	 * @return the bookedForMemberId
	 */
	public String getBookedForMemberId() {
		return BookedForMemberId;
	}

	/**
	 * @param bookedForMemberId
	 *            the bookedForMemberId to set
	 */
	public void setBookedForMemberId(String bookedForMemberId) {
		BookedForMemberId = bookedForMemberId;
	}

	/**
	 * @return the bookedForName
	 */
	public String getBookedForName() {
		return BookedForName;
	}

	/**
	 * @param bookedForName
	 *            the bookedForName to set
	 */
	public void setBookedForName(String bookedForName) {
		BookedForName = bookedForName;
	}

	/**
	 * @return the bookedForAge
	 */
	public int getBookedForAge() {
		return BookedForAge;
	}

	/**
	 * @param bookedForAge
	 *            the bookedForAge to set
	 */
	public void setBookedForAge(int bookedForAge) {
		BookedForAge = bookedForAge;
	}

	/**
	 * @return the bookedForGender
	 */
	public String getBookedForGender() {
		return BookedForGender;
	}

	/**
	 * @param bookedForGender
	 *            the bookedForGender to set
	 */
	public void setBookedForGender(String bookedForGender) {
		BookedForGender = bookedForGender;
	}

	/**
	 * @return the appointmentDate
	 */
	public String getAppointmentDate() {
		return AppointmentDate;
	}

	/**
	 * @param appointmentDate
	 *            the appointmentDate to set
	 */
	public void setAppointmentDate(String appointmentDate) {
		AppointmentDate = appointmentDate;
	}

	/**
	 * @return the appointmentTime
	 */
	public String getAppointmentTime() {
		return AppointmentTime;
	}

	/**
	 * @param appointmentTime
	 *            the appointmentTime to set
	 */
	public void setAppointmentTime(String appointmentTime) {
		AppointmentTime = appointmentTime;
	}

	/**
	 * @return the slotTime
	 */
	public String getSlotTime() {
		return SlotTime;
	}

	/**
	 * @param slotTime
	 *            the slotTime to set
	 */
	public void setSlotTime(String slotTime) {
		SlotTime = slotTime;
	}

	/**
	 * @return the appointmentMode
	 */
	public int getAppointmentMode() {
		return AppointmentMode;
	}

	/**
	 * @param appointmentMode
	 *            the appointmentMode to set
	 */
	public void setAppointmentMode(int appointmentMode) {
		AppointmentMode = appointmentMode;
	}

	/**
	 * @return the isBookedFromDefaultAvailability
	 */
	public boolean isIsBookedFromDefaultAvailability() {
		return IsBookedFromDefaultAvailability;
	}

	/**
	 * @param isBookedFromDefaultAvailability
	 *            the isBookedFromDefaultAvailability to set
	 */
	public void setIsBookedFromDefaultAvailability(boolean isBookedFromDefaultAvailability) {
		IsBookedFromDefaultAvailability = isBookedFromDefaultAvailability;
	}

	/**
	 * @return the slot
	 */
	public int getSlot() {
		return Slot;
	}

	/**
	 * @param slot
	 *            the slot to set
	 */
	public void setSlot(int slot) {
		Slot = slot;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return Message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		Message = message;
	}

}
