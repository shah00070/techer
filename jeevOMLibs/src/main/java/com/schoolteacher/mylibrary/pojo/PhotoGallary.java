package com.schoolteacher.mylibrary.pojo;

public class PhotoGallary {
	private int Id;
	private String Name;
	// private object Owner ;
	// private object Tags ;
	// private object Description ;
	private String CreatedOnUtc;
	// private object Extension ;
	private String DocType;
	/**
	 * @return the id
	 */
	public int getId() {
		return Id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		Id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
	}
	/**
	 * @return the createdOnUtc
	 */
	public String getCreatedOnUtc() {
		return CreatedOnUtc;
	}
	/**
	 * @param createdOnUtc the createdOnUtc to set
	 */
	public void setCreatedOnUtc(String createdOnUtc) {
		CreatedOnUtc = createdOnUtc;
	}
	/**
	 * @return the docType
	 */
	public String getDocType() {
		return DocType;
	}
	/**
	 * @param docType the docType to set
	 */
	public void setDocType(String docType) {
		DocType = docType;
	}
	/**
	 * @return the documentURL
	 */
	public String getDocumentURL() {
		return DocumentURL;
	}
	/**
	 * @param documentURL the documentURL to set
	 */
	public void setDocumentURL(String documentURL) {
		DocumentURL = documentURL;
	}
	// private object BinaryContent ;
	// private object ContainerName ;
	// private object DownloadUri ;
	private String DocumentURL;
	// private object StringContent ;
}
