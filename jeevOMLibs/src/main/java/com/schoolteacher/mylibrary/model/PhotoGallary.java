package com.schoolteacher.mylibrary.model;

import java.io.Serializable;

public class PhotoGallary implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	public String getOwner() {
		return Owner;
	}
	public void setOwner(String owner) {
		Owner = owner;
	}
	public String getTags() {
		return Tags;
	}
	public void setTags(String tags) {
		Tags = tags;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getCreatedOnUtc() {
		return CreatedOnUtc;
	}
	public void setCreatedOnUtc(String createdOnUtc) {
		CreatedOnUtc = createdOnUtc;
	}
	public String getExtension() {
		return Extension;
	}
	public void setExtension(String extension) {
		Extension = extension;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getBinaryContent() {
		return BinaryContent;
	}
	public void setBinaryContent(String binaryContent) {
		BinaryContent = binaryContent;
	}
	public String getStringContent() {
		return StringContent;
	}
	public void setStringContent(String stringContent) {
		StringContent = stringContent;
	}
	public String getDownloadUri() {
		return DownloadUri;
	}
	public void setDownloadUri(String downloadUri) {
		DownloadUri = downloadUri;
	}
	public String getDocumentURL() {
		return DocumentURL;
	}
	public void setDocumentURL(String documentURL) {
		DocumentURL = documentURL;
	}
	private int Id;
	private String Name;
	private String Owner;
	private String Tags;
	private String Description;
	private String CreatedOnUtc;
	private String Extension;
	private String Type;
	private String BinaryContent;
	private String StringContent;
	private String DownloadUri;
	private String DocumentURL;
}
