package com.schoolteacher.pojos;

import java.io.Serializable;

public class MemberInsuranceDetail implements Serializable {
	private int Id;
	private int MemberId;
	private String ProviderName;
	private String ProviderUrl;
	private String PolicyName;
	private String PolicyNumber;
	private String ValidFrom;
	private String ValidTill;
	private String CoverageAmount;
	private String ClaimedAmount;
	private String PrimaryInsCellNumber;
	private String PrimaryInsEmail;
	private String PrimaryInsTitle;
	private String PrimaryInsFirstName;
	private String PrimaryInsLastName;
	private String TPAOrganisationName;
	private String TPACellNumber;
	private String TPAEmail;

	/**
	 * @return the id
	 */
	public int getId() {
		return Id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		Id = id;
	}

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
	 * @return the providerName
	 */
	public String getProviderName() {
		return ProviderName;
	}

	/**
	 * @param providerName
	 *            the providerName to set
	 */
	public void setProviderName(String providerName) {
		ProviderName = providerName;
	}

	/**
	 * @return the providerUrl
	 */
	public String getProviderUrl() {
		return ProviderUrl;
	}

	/**
	 * @param providerUrl
	 *            the providerUrl to set
	 */
	public void setProviderUrl(String providerUrl) {
		ProviderUrl = providerUrl;
	}

	/**
	 * @return the policyName
	 */
	public String getPolicyName() {
		return PolicyName;
	}

	/**
	 * @param policyName
	 *            the policyName to set
	 */
	public void setPolicyName(String policyName) {
		PolicyName = policyName;
	}

	/**
	 * @return the policyNumber
	 */
	public String getPolicyNumber() {
		return PolicyNumber;
	}

	/**
	 * @param policyNumber
	 *            the policyNumber to set
	 */
	public void setPolicyNumber(String policyNumber) {
		PolicyNumber = policyNumber;
	}

	/**
	 * @return the validFrom
	 */
	public String getValidFrom() {
		return ValidFrom;
	}

	/**
	 * @param validFrom
	 *            the validFrom to set
	 */
	public void setValidFrom(String validFrom) {
		ValidFrom = validFrom;
	}

	/**
	 * @return the validTill
	 */
	public String getValidTill() {
		return ValidTill;
	}

	/**
	 * @param validTill
	 *            the validTill to set
	 */
	public void setValidTill(String validTill) {
		ValidTill = validTill;
	}

	/**
	 * @return the coverageAmount
	 */
	public String getCoverageAmount() {
		return CoverageAmount;
	}

	/**
	 * @param coverageAmount
	 *            the coverageAmount to set
	 */
	public void setCoverageAmount(String coverageAmount) {
		CoverageAmount = coverageAmount;
	}

	/**
	 * @return the claimedAmount
	 */
	public String getClaimedAmount() {
		return ClaimedAmount;
	}

	/**
	 * @param claimedAmount
	 *            the claimedAmount to set
	 */
	public void setClaimedAmount(String claimedAmount) {
		ClaimedAmount = claimedAmount;
	}

	/**
	 * @return the primaryInsCellNumber
	 */
	public String getPrimaryInsCellNumber() {
		return PrimaryInsCellNumber;
	}

	/**
	 * @param primaryInsCellNumber
	 *            the primaryInsCellNumber to set
	 */
	public void setPrimaryInsCellNumber(String primaryInsCellNumber) {
		PrimaryInsCellNumber = primaryInsCellNumber;
	}

	/**
	 * @return the primaryInsEmail
	 */
	public String getPrimaryInsEmail() {
		return PrimaryInsEmail;
	}

	/**
	 * @param primaryInsEmail
	 *            the primaryInsEmail to set
	 */
	public void setPrimaryInsEmail(String primaryInsEmail) {
		PrimaryInsEmail = primaryInsEmail;
	}

	/**
	 * @return the primaryInsTitle
	 */
	public String getPrimaryInsTitle() {
		return PrimaryInsTitle;
	}

	/**
	 * @param primaryInsTitle
	 *            the primaryInsTitle to set
	 */
	public void setPrimaryInsTitle(String primaryInsTitle) {
		PrimaryInsTitle = primaryInsTitle;
	}

	/**
	 * @return the primaryInsFirstName
	 */
	public String getPrimaryInsFirstName() {
		return PrimaryInsFirstName;
	}

	/**
	 * @param primaryInsFirstName
	 *            the primaryInsFirstName to set
	 */
	public void setPrimaryInsFirstName(String primaryInsFirstName) {
		PrimaryInsFirstName = primaryInsFirstName;
	}

	/**
	 * @return the primaryInsLastName
	 */
	public String getPrimaryInsLastName() {
		return PrimaryInsLastName;
	}

	/**
	 * @param primaryInsLastName
	 *            the primaryInsLastName to set
	 */
	public void setPrimaryInsLastName(String primaryInsLastName) {
		PrimaryInsLastName = primaryInsLastName;
	}

	/**
	 * @return the tPAOrganisationName
	 */
	public String getTPAOrganisationName() {
		return TPAOrganisationName;
	}

	/**
	 * @param tPAOrganisationName
	 *            the tPAOrganisationName to set
	 */
	public void setTPAOrganisationName(String tPAOrganisationName) {
		TPAOrganisationName = tPAOrganisationName;
	}

	/**
	 * @return the tPACellNumber
	 */
	public String getTPACellNumber() {
		return TPACellNumber;
	}

	/**
	 * @param tPACellNumber
	 *            the tPACellNumber to set
	 */
	public void setTPACellNumber(String tPACellNumber) {
		TPACellNumber = tPACellNumber;
	}

	/**
	 * @return the tPAEmail
	 */
	public String getTPAEmail() {
		return TPAEmail;
	}

	/**
	 * @param tPAEmail
	 *            the tPAEmail to set
	 */
	public void setTPAEmail(String tPAEmail) {
		TPAEmail = tPAEmail;
	}

}
