package com.schoolteacher.pojos;

import java.io.Serializable;

public class MemberAssociation implements Serializable {
    private int Id;
    private int AssociateOfId;
    private int AssociationIdKey;
    private String AssociateNameString;
    private int BloodGroupType;
    private String Gender;
    private String Title;
    private String FirstName;
    private String LastName;
    private String dob;
    private String Email;
    private String CellNumber;
    private int AssociateWithId;
    private String CreatedOnUTC;
    private String UpdatedOnUTC;
    private int CreatedBy;
    private int UpdatedBy;
    private boolean IsEmergencyContact;
    private boolean CreateJeevOmId;
    private boolean IsMemberRegisteredSucessFully;

    /**
     * @return the id
     */
    public int getId() {
        return Id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        Id = id;
    }

    /**
     * @return the memberId
     */
    public int getAssociateOfId() {
        return AssociateOfId;
    }

    /**
     * @param associateOfId the associateOfId to set
     */
    public void setAssociateOfId(int associateOfId) {
        AssociateOfId = associateOfId;
    }

    /**
     * @return the familyRelation
     */
    public int getAssociationIdKey() {
        return AssociationIdKey;
    }

    /**
     * @param associationIdKey the associationIdKey to set
     */
    public void setAssociationIdKey(int associationIdKey) {
        AssociationIdKey = associationIdKey;
    }

    /**
     * @return the familyRelationString
     */
    public String getAssociateNameString() {
        return AssociateNameString;
    }

    /**
     * @param associateNameString the associateNameString to set
     */
    public void setAssociateNameString(String associateNameString) {
        AssociateNameString = associateNameString;
    }

    /**
     * @return the bloodGroupType
     */
    public int getBloodGroupType() {
        return BloodGroupType;
    }

    /**
     * @param bloodGroupType the bloodGroupType to set
     */
    public void setBloodGroupType(int bloodGroupType) {
        BloodGroupType = bloodGroupType;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return Title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        Title = title;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return FirstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return LastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        LastName = lastName;
    }

    /**
     * @return the dob
     */
    public String getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        Email = email;
    }

    /**
     * @return the cellNumber
     */
    public String getCellNumber() {
        return CellNumber;
    }

    /**
     * @param cellNumber the cellNumber to set
     */
    public void setCellNumber(String cellNumber) {
        CellNumber = cellNumber;
    }

    /**
     * @return the withMemberId
     */
    public int getAssociateWithId() {
        return AssociateWithId;
    }

    /**
     * @param associateWithId the associateWithId to set
     */
    public void setAssociateWithId(int associateWithId) {
        AssociateWithId = associateWithId;
    }

    /**
     * @return the createdOnUTC
     */
    public String getCreatedOnUTC() {
        return CreatedOnUTC;
    }

    /**
     * @param createdOnUTC the createdOnUTC to set
     */
    public void setCreatedOnUTC(String createdOnUTC) {
        CreatedOnUTC = createdOnUTC;
    }

    /**
     * @return the updatedOnUTC
     */
    public String getUpdatedOnUTC() {
        return UpdatedOnUTC;
    }

    /**
     * @param updatedOnUTC the updatedOnUTC to set
     */
    public void setUpdatedOnUTC(String updatedOnUTC) {
        UpdatedOnUTC = updatedOnUTC;
    }

    /**
     * @return the createdBy
     */
    public int getCreatedBy() {
        return CreatedBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(int createdBy) {
        CreatedBy = createdBy;
    }

    /**
     * @return the updatedBy
     */
    public int getUpdatedBy() {
        return UpdatedBy;
    }

    /**
     * @param updatedBy the updatedBy to set
     */
    public void setUpdatedBy(int updatedBy) {
        UpdatedBy = updatedBy;
    }

    /**
     * @return the isEmergencyContact
     */
    public boolean isIsEmergencyContact() {
        return IsEmergencyContact;
    }

    /**
     * @param isEmergencyContact the isEmergencyContact to set
     */
    public void setIsEmergencyContact(boolean isEmergencyContact) {
        IsEmergencyContact = isEmergencyContact;
    }

    /**
     * @return the createJeevOmId
     */
    public boolean isCreateJeevOmId() {
        return CreateJeevOmId;
    }

    /**
     * @param createJeevOmId the createJeevOmId to set
     */
    public void setCreateJeevOmId(boolean createJeevOmId) {
        CreateJeevOmId = createJeevOmId;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return Gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        Gender = gender;
    }

    /**
     * @return the isMemberRegisteredSucessFully
     */
    public boolean isIsMemberRegisteredSucessFully() {
        return IsMemberRegisteredSucessFully;
    }

    /**
     * @param isMemberRegisteredSucessFully the isMemberRegisteredSucessFully to set
     */
    public void setIsMemberRegisteredSucessFully(boolean isMemberRegisteredSucessFully) {
        IsMemberRegisteredSucessFully = isMemberRegisteredSucessFully;
    }
}
