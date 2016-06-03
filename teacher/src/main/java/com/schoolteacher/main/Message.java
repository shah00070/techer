package com.schoolteacher.main;

public class Message {
	private int Id ;
    //private object Subject ;
    private String Message ;
    private int FromMemberId ;
//    private object FromProfessionalId ;
//    private object FromSupportId ;
    private String FromSupportName ;
//    private object FromSupportPhoto ;
//    private object FromFacilityId ;
    private String CreatedOnUtc ;
    private String FromProfessionalName ;
//    private object FromProfessionalPhoto ;
//    private object FromFacilityName ;
//    private object FromFacilityPhoto ;
    private String FromConsumerName ;
	private String FromConsumerPhoto ;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public int getFromMemberId() {
		return FromMemberId;
	}
	public void setFromMemberId(int fromMemberId) {
		FromMemberId = fromMemberId;
	}
	public String getFromSupportName() {
		return FromSupportName;
	}
	public void setFromSupportName(String fromSupportName) {
		FromSupportName = fromSupportName;
	}
	public String getCreatedOnUtc() {
		return CreatedOnUtc;
	}
	public void setCreatedOnUtc(String createdOnUtc) {
		CreatedOnUtc = createdOnUtc;
	}
	public String getFromProfessionalName() {
		return FromProfessionalName;
	}
	public void setFromProfessionalName(String fromProfessionalName) {
		FromProfessionalName = fromProfessionalName;
	}
	public String getFromConsumerName() {
		return FromConsumerName;
	}
	public void setFromConsumerName(String fromConsumerName) {
		FromConsumerName = fromConsumerName;
	}
	public String getFromConsumerPhoto() {
		return FromConsumerPhoto;
	}
	public void setFromConsumerPhoto(String fromConsumerPhoto) {
		FromConsumerPhoto = fromConsumerPhoto;
	}
}
