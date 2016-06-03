package com.schoolteacher.pojos;

/**
 * Created by chandan on 9/12/15.
 */
public class DealsAndOfferResponse {

    public int Id;
    public String Name;
    public String Code;
    public int Value;
    public int ValueType;
    public int MaxAmountToBeGiven;
    public int MinTransactionAmount;
    public String CreatedOnUtc;
    public String UpdatedOnUtc;
    public String ValidFrom;
    public String ValidTill;
    public int AppliesOn;
    public boolean IsCashBack;
    public boolean CanMultipleRedeem;
    public boolean IsDiscount;
    public boolean IsActive;
    public String BusinessPublicId;
    public String ProfessionalPublicId;
    public String ServiceRequistionType;
    public String Description;
    public String TermAndConditions;
    public boolean IsClaimable;
    public int Amount;

    public boolean isClaimable() {
        return IsClaimable;
    }

    public void setIsClaimable(boolean isClaimable) {
        IsClaimable = isClaimable;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public int getValue() {
        return Value;
    }

    public void setValue(int value) {
        Value = value;
    }

    public int getValueType() {
        return ValueType;
    }

    public void setValueType(int valueType) {
        ValueType = valueType;
    }

    public int getMaxAmountToBeGiven() {
        return MaxAmountToBeGiven;
    }

    public void setMaxAmountToBeGiven(int maxAmountToBeGiven) {
        MaxAmountToBeGiven = maxAmountToBeGiven;
    }

    public int getMinTransactionAmount() {
        return MinTransactionAmount;
    }

    public void setMinTransactionAmount(int minTransactionAmount) {
        MinTransactionAmount = minTransactionAmount;
    }

    public String getCreatedOnUtc() {
        return CreatedOnUtc;
    }

    public void setCreatedOnUtc(String createdOnUtc) {
        CreatedOnUtc = createdOnUtc;
    }

    public String getUpdatedOnUtc() {
        return UpdatedOnUtc;
    }

    public void setUpdatedOnUtc(String updatedOnUtc) {
        UpdatedOnUtc = updatedOnUtc;
    }

    public String getValidFrom() {
        return ValidFrom;
    }

    public void setValidFrom(String validFrom) {
        ValidFrom = validFrom;
    }

    public String getValidTill() {
        return ValidTill;
    }

    public void setValidTill(String validTill) {
        ValidTill = validTill;
    }

    public int getAppliesOn() {
        return AppliesOn;
    }

    public void setAppliesOn(int appliesOn) {
        AppliesOn = appliesOn;
    }

    public boolean isCashBack() {
        return IsCashBack;
    }

    public void setIsCashBack(boolean isCashBack) {
        IsCashBack = isCashBack;
    }

    public boolean isCanMultipleRedeem() {
        return CanMultipleRedeem;
    }

    public void setCanMultipleRedeem(boolean canMultipleRedeem) {
        CanMultipleRedeem = canMultipleRedeem;
    }

    public boolean isDiscount() {
        return IsDiscount;
    }

    public void setIsDiscount(boolean isDiscount) {
        IsDiscount = isDiscount;
    }

    public boolean isActive() {
        return IsActive;
    }

    public void setIsActive(boolean isActive) {
        IsActive = isActive;
    }

    public String getBusinessPublicId() {
        return BusinessPublicId;
    }

    public void setBusinessPublicId(String businessPublicId) {
        BusinessPublicId = businessPublicId;
    }

    public String getProfessionalPublicId() {
        return ProfessionalPublicId;
    }

    public void setProfessionalPublicId(String professionalPublicId) {
        ProfessionalPublicId = professionalPublicId;
    }

    public String getServiceRequistionType() {
        return ServiceRequistionType;
    }

    public void setServiceRequistionType(String serviceRequistionType) {
        ServiceRequistionType = serviceRequistionType;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTermAndConditions() {
        return TermAndConditions;
    }

    public void setTermAndConditions(String termAndConditions) {
        TermAndConditions = termAndConditions;
    }


}
