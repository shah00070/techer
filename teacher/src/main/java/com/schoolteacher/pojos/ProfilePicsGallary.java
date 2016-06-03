package com.schoolteacher.pojos;

import java.io.Serializable;

public class ProfilePicsGallary implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4812107121199382947L;
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

	public String getCreatedOnUtc() {
		return CreatedOnUtc;
	}

	public void setCreatedOnUtc(String createdOnUtc) {
		CreatedOnUtc = createdOnUtc;
	}

	public String getDocType() {
		return DocType;
	}

	public void setDocType(String docType) {
		DocType = docType;
	}

	public String getDocumentURL() {
		return DocumentURL;
	}

	public void setDocumentURL(String documentURL) {
		DocumentURL = documentURL;
	}

	public String getSharedAccessExpiryTime() {
		return SharedAccessExpiryTime;
	}

	public void setSharedAccessExpiryTime(String sharedAccessExpiryTime) {
		SharedAccessExpiryTime = sharedAccessExpiryTime;
	}

	private int Id;
	private String Name;
	// private object Owner ;
	// private object Tags ;
	// private object Description ;
	private String CreatedOnUtc;
	// private object Extension ;
	private String DocType;
	// private object BinaryContent ;
	// private object ContainerName ;
	// private object DownloadUri ;
	private String DocumentURL;
	private String SharedAccessExpiryTime;
	// private object StringContent ;
}
