package com.schoolteacher.pojos;

import java.io.Serializable;

public class DocumentObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6552524117897210469L;
	private String Name;
	private String FileName;
	private String DocumentDate;
	private String Description;
	private String Tags;
	private String DocumentTypeId;
	private String OwnerId;
	private String DocumentContent;
	private String DocumentSize;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getFileName() {
		return FileName;
	}

	public void setFileName(String fileName) {
		FileName = fileName;
	}

	public String getDocumentDate() {
		return DocumentDate;
	}

	public void setDocumentDate(String documentDate) {
		DocumentDate = documentDate;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getTags() {
		return Tags;
	}

	public void setTags(String tags) {
		Tags = tags;
	}

	public String getDocumentTypeId() {
		return DocumentTypeId;
	}

	public void setDocumentTypeId(String documentTypeId) {
		DocumentTypeId = documentTypeId;
	}

	public String getOwnerId() {
		return OwnerId;
	}

	public void setOwnerId(String ownerId) {
		OwnerId = ownerId;
	}

	public String getDocumentContent() {
		return DocumentContent;
	}

	public void setDocumentContent(String documentContent) {
		DocumentContent = documentContent;
	}

	public String getDocumentSize() {
		return DocumentSize;
	}

	public void setDocumentSize(String documentSize) {
		DocumentSize = documentSize;
	}
}
