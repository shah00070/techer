package com.schoolteacher.mylibrary.model;

public class EmailAndTokenTypeDictionary {
    private String Email;
    private String CellNumber;
    private int TokenType;
    private int ServiceRequestId;
    private int ServiceRequestMessageId;

    private String ClaimFromName;
    private String ClaimFromEmail;
    private String ClaimFromContactNumber;
    private String ClaimOnName;
    private int ClaimOnUserID;
    private String ClaimOnEmail;
    private String ClaimOnContactNumber;
    private String Token;

    public String getEmail() {
        return Email;
    }

    public String getClaimFromEmail() {
        return ClaimFromEmail;
    }

    public void setClaimFromEmail(String claimFromEmail) {
        ClaimFromEmail = claimFromEmail;
    }

    public String getClaimFromContactNumber() {
        return ClaimFromContactNumber;
    }

    public void setClaimFromContactNumber(String claimFromContactNumber) {
        ClaimFromContactNumber = claimFromContactNumber;
    }

    public String getClaimOnName() {
        return ClaimOnName;
    }

    public void setClaimOnName(String claimOnName) {
        ClaimOnName = claimOnName;
    }

    public int getClaimOnUserID() {
        return ClaimOnUserID;
    }

    public void setClaimOnUserID(int claimOnUserID) {
        ClaimOnUserID = claimOnUserID;
    }

    public String getClaimOnEmail() {
        return ClaimOnEmail;
    }

    public void setClaimOnEmail(String claimOnEmail) {
        ClaimOnEmail = claimOnEmail;
    }

    public String getClaimOnContactNumber() {
        return ClaimOnContactNumber;
    }

    public void setClaimOnContactNumber(String claimOnContactNumber) {
        ClaimOnContactNumber = claimOnContactNumber;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getTokenType() {
        return TokenType;
    }

    public void setTokenType(int tokenType) {
        TokenType = tokenType;
    }

    public String getClaimFromName() {
        return ClaimFromName;
    }

    public void setClaimFromName(String claimFromName) {
        ClaimFromName = claimFromName;
    }

    public int getServiceRequestId() {
        return ServiceRequestId;
    }

    public void setServiceRequestId(int serviceRequestId) {
        ServiceRequestId = serviceRequestId;
    }

    public int getServiceRequestMessageId() {
        return ServiceRequestMessageId;
    }

    public void setServiceRequestMessageId(int serviceRequestMessageId) {
        ServiceRequestMessageId = serviceRequestMessageId;
    }

    public String getCellNumber() {
        return CellNumber;
    }

    public void setCellNumber(String cellNumber) {
        CellNumber = cellNumber;
    }

}
