package com.schoolteacher.pojos;

import java.util.List;

public class DropDownInfo {
	private boolean IsAdded;
	private List<DropDownObject> DataDictionary;

	// private Object DictionaryType ;
	/**
	 * @return the isAdded
	 */
	public boolean isIsAdded() {
		return IsAdded;
	}

	/**
	 * @param isAdded
	 *            the isAdded to set
	 */
	public void setIsAdded(boolean isAdded) {
		IsAdded = isAdded;
	}

	/**
	 * @return the dataDictionary
	 */
	public List<DropDownObject> getDataDictionary() {
		return DataDictionary;
	}

	/**
	 * @param dataDictionary
	 *            the dataDictionary to set
	 */
	public void setDataDictionary(List<DropDownObject> dataDictionary) {
		DataDictionary = dataDictionary;
	}

}
