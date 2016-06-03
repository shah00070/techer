package com.schoolteacher.pojos;

import java.io.Serializable;

public class WalletAmountResponse implements Serializable {
	/**
	 * Gaurav Gupta -- 6/4/2015
	 */
	private static final long serialVersionUID = 7423497487668760471L;
	private int Id;
	private int MemberId;
	private float Balance;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getMemberId() {
		return MemberId;
	}

	public void setMemberId(int memberId) {
		MemberId = memberId;
	}

	public String getCurrency() {
		return Currency;
	}

	public void setCurrency(String currency) {
		Currency = currency;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public float getBalance() {
		return Balance;
	}

	public void setBalance(float balance) {
		Balance = balance;
	}

	private String Currency;
}
