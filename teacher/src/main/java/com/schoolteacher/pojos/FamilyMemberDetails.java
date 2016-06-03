package com.schoolteacher.pojos;

public class FamilyMemberDetails {
    private String Id;
    private String AssociationIdKey;
    private String BloodGroupType;
    private String Title;
    private String Gender;
    private String FirstName;
    private String LastName;
    private String dob;
    private String Email;
    private String CellNumber;
    private boolean IsEmergencyContact;
    private boolean CreateJeevOmId;

    public FamilyMemberDetails(String id, String associationIdKey, String bloodGroupType, String title, String firstName, String lastName, String dob, String email, String cellNumber,
                               boolean isEmergencyContact, boolean createJeevOmId, String gender) {
        super();

        Id = id;
        AssociationIdKey = associationIdKey;
        BloodGroupType = bloodGroupType;
        Title = title;
        FirstName = firstName;
        LastName = lastName;
        this.dob = dob;
        Email = email;
        Gender = gender;
        CellNumber = cellNumber;
        IsEmergencyContact = isEmergencyContact;
        CreateJeevOmId = createJeevOmId;
    }

}
