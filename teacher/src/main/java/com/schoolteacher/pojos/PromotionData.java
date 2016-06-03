package com.schoolteacher.pojos;

/**
 * Created by chandan on 31/12/15.
 */
public class PromotionData {

    public int Id;
    public String Name;
    public String Code;
    public String Amount;
    public String DiscountPercentage;
    public String ValidTill;
    public int Value;
    public int ValueType;
    public int MaxAmountToBeGiven;
    public boolean IsCashBack;
    public boolean IsDiscount;

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

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getDiscountPercentage() {
        return DiscountPercentage;
    }

    public void setDiscountPercentage(String discountPercentage) {
        DiscountPercentage = discountPercentage;
    }

    public String getValidTill() {
        return ValidTill;
    }

    public void setValidTill(String validTill) {
        ValidTill = validTill;
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

    public boolean isCashBack() {
        return IsCashBack;
    }

    public void setIsCashBack(boolean isCashBack) {
        IsCashBack = isCashBack;
    }

    public boolean isDiscount() {
        return IsDiscount;
    }

    public void setIsDiscount(boolean isDiscount) {
        IsDiscount = isDiscount;
    }
}
