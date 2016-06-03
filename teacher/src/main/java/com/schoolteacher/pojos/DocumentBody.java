package com.schoolteacher.pojos;

public class DocumentBody {

	private String BinaryContent;
	private String DocumentFileName;
	private int UserId;

	/**
	 * @return the binaryContent
	 */
	public String getBinaryContent() {
		return BinaryContent;
	}

	/**
	 * @param binaryContent
	 *            the binaryContent to set
	 */
	public void setBinaryContent(String binaryContent) {
		BinaryContent = binaryContent;
	}

	/**
	 * @return the documentFileName
	 */
	public String getDocumentFileName() {
		return DocumentFileName;
	}

	/**
	 * @param documentFileName
	 *            the documentFileName to set
	 */
	public void setDocumentFileName(String documentFileName) {
		DocumentFileName = documentFileName;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return UserId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		UserId = userId;
	}

}
