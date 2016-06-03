package com.schoolteacher.pojos;

public class ConsumerEmergencyDetails {
	private int Id;
	private int MemberId;
	private String Title;
	private String FirstName;
	private String LastName;
	private String Email;
	private String CellNumber;
	private boolean IsPrimary;
	public ConsumerEmergencyDetails(int id,int memberId, String title, String firstName, String lastName, String email, String cellNumber, boolean isPrimary) {
		super();
		Id=id;
		MemberId = memberId;
		Title = title;
		FirstName = firstName;
		LastName = lastName;
		Email = email;
		CellNumber = cellNumber;
		IsPrimary = isPrimary;
	}

	
}
