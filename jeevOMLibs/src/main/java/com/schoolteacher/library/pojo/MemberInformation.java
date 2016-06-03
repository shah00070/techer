package com.schoolteacher.library.pojo;

import java.io.Serializable;

public class MemberInformation implements Serializable {
    /**
     * Gaurav Gupta -- 5/24/2015
     */
    private static final long serialVersionUID = -2968768513233622280L;
    private String FirstName;
    private String LastName;
    private boolean IsHealthCareProfessional;
    private boolean IsBusinessOwner;
    private String Gender;
    private String PasswordHash;
    private String ExternalProviderId;
    private String Email;
    private String Mobile;
    private String ExternalProviderName;
    private boolean IsCheckAlreadyExists;

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    private String DateOfBirth;

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPasswordHash() {
        return PasswordHash;
    }

    public void setPasswordHash(String passwordHash) {
        PasswordHash = passwordHash;
    }

    public String getExternalProviderId() {
        return ExternalProviderId;
    }

    public void setExternalProviderId(String externalProviderId) {
        ExternalProviderId = externalProviderId;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getExternalProviderName() {
        return ExternalProviderName;
    }

    public void setExternalProviderName(String externalProviderName) {
        ExternalProviderName = externalProviderName;
    }

    public boolean isIsCheckAlreadyExists() {
        return IsCheckAlreadyExists;
    }

    public void setIsCheckAlreadyExists(boolean isCheckAlreadyExists) {
        IsCheckAlreadyExists = isCheckAlreadyExists;
    }

    public boolean isIsHealthCareProfessional() {
        return IsHealthCareProfessional;
    }

    public void setIsHealthCareProfessional(boolean isHealthCareProfessional) {
        IsHealthCareProfessional = isHealthCareProfessional;
    }

    public boolean isIsBusinessOwner() {
        return IsBusinessOwner;
    }

    public void setIsBusinessOwner(boolean isBusinessOwner) {
        IsBusinessOwner = isBusinessOwner;
    }

}
