package com.schoolteacher.pojos;

public class PkgPromotion {
	private int Id;
	private String Name;
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
	public double getAmount() {
		return Amount;
	}
	public void setAmount(double amount) {
		Amount = amount;
	}
	public int getDiscountPercentage() {
		return DiscountPercentage;
	}
	public void setDiscountPercentage(int discountPercentage) {
		DiscountPercentage = discountPercentage;
	}
	public String getValidTill() {
		return ValidTill;
	}
	public void setValidTill(String validTill) {
		ValidTill = validTill;
	}
	private String Code;
	private double Amount;
	private int DiscountPercentage;
	private String ValidTill;
}
