package com.schoolteacher.mylibrary.model;

public class AddExternalUserRequest {
	private String email;
	private String IsExternal;
	private String ExternalProviderId;
	private String ExternalProviderName;
	private String FirstName;
	private String LastName;

	public AddExternalUserRequest(String firstName, String lastName,
			String email, String isExternal, String externalId,
			String externalProviderName) {
		super();
		this.FirstName = firstName;
		this.LastName = lastName;
		this.email = email;
		IsExternal = isExternal;
		setExternalProviderId(externalId);
		ExternalProviderName = externalProviderName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the isExternal
	 */
	public String getIsExternal() {
		return IsExternal;
	}

	/**
	 * @param isExternal
	 *            the isExternal to set
	 */
	public void setIsExternal(String isExternal) {
		IsExternal = isExternal;
	}

	/**
	 * @return the externalProviderName
	 */
	public String getExternalProviderName() {
		return ExternalProviderName;
	}

	/**
	 * @param externalProviderName
	 *            the externalProviderName to set
	 */
	public void setExternalProviderName(String externalProviderName) {
		ExternalProviderName = externalProviderName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getExternalProviderId() {
		return ExternalProviderId;
	}

	public void setExternalProviderId(String externalProviderId) {
		ExternalProviderId = externalProviderId;
	}

}
