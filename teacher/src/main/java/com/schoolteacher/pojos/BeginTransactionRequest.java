package com.schoolteacher.pojos;

import java.io.Serializable;

public class BeginTransactionRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4003881461963004261L;
	private int MemberId;
	private String Email;
	private String CellNumber;
	private float TransactionAmount;
	private String FirstName;
	private String ExternalTransactionId;
	private int PackageId;
	private int Quantity;
	private String PaymentSourceText;
	private String MemberIPAddress;
	private float AmountPayable;

	private Integer PromotionId;

	/**
	 * @return the memberId
	 */
	public int getMemberId() {
		return MemberId;
	}

	/**
	 * @param memberId
	 *            the memberId to set
	 */
	public void setMemberId(int memberId) {
		MemberId = memberId;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return Email;
	}

	/**
	 * @param email
	 *            the email to set
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
	 * @param cellNumber
	 *            the cellNumber to set
	 */
	public void setCellNumber(String cellNumber) {
		CellNumber = cellNumber;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return FirstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	/**
	 * @return the packageId
	 */
	public int getPackageId() {
		return PackageId;
	}

	/**
	 * @param packageId
	 *            the packageId to set
	 */
	public void setPackageId(int packageId) {
		PackageId = packageId;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return Quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public float getTransactionAmount() {
		return TransactionAmount;
	}

	public void setTransactionAmount(float transactionAmount) {
		TransactionAmount = transactionAmount;
	}

	public String getPaymentSourceText() {
		return PaymentSourceText;
	}

	public void setPaymentSourceText(String paymentSourceText) {
		PaymentSourceText = paymentSourceText;
	}

	public String getExternalTransactionId() {
		return ExternalTransactionId;
	}

	public void setExternalTransactionId(String externalTransactionId) {
		ExternalTransactionId = externalTransactionId;
	}

	public float getAmountPayable() {
		return AmountPayable;
	}

	public void setAmountPayable(float amountPayable) {
		AmountPayable = amountPayable;
	}

	public String getMemberIPAddress() {
		return MemberIPAddress;
	}

	public void setMemberIPAddress(String memberIPAddress) {
		MemberIPAddress = memberIPAddress;
	}

	public Integer getPromotionId() {
		return PromotionId;
	}

	public void setPromotionId(Integer promotionId) {
		PromotionId = promotionId;
	}

}
