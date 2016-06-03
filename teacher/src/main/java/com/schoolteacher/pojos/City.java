package com.schoolteacher.pojos;

import com.google.gson.annotations.Expose;

public class City {

	@Expose
	private int Id;
	@Expose
	private String Name;
	@Expose
	private int CreatedBy;
	@Expose
	private int ModifiedBy;
	@Expose
	private String CreatedOnUTC;
	@Expose
	private String ModifiedOnUTC;

	/**
	 * 
	 * @return The Id
	 */
	public int getId() {
		return Id;
	}

	/**
	 * 
	 * @param Id
	 *            The Id
	 */
	public void setId(int Id) {
		this.Id = Id;
	}

	/**
	 * 
	 * @return The Name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * 
	 * @param Name
	 *            The Name
	 */
	public void setName(String Name) {
		this.Name = Name;
	}

	/**
	 * 
	 * @return The CreatedBy
	 */
	public int getCreatedBy() {
		return CreatedBy;
	}

	/**
	 * 
	 * @param CreatedBy
	 *            The CreatedBy
	 */
	public void setCreatedBy(int CreatedBy) {
		this.CreatedBy = CreatedBy;
	}

	/**
	 * 
	 * @return The ModifiedBy
	 */
	public int getModifiedBy() {
		return ModifiedBy;
	}

	/**
	 * 
	 * @param ModifiedBy
	 *            The ModifiedBy
	 */
	public void setModifiedBy(int ModifiedBy) {
		this.ModifiedBy = ModifiedBy;
	}

	/**
	 * 
	 * @return The CreatedOnUTC
	 */
	public String getCreatedOnUTC() {
		return CreatedOnUTC;
	}

	/**
	 * 
	 * @param CreatedOnUTC
	 *            The CreatedOnUTC
	 */
	public void setCreatedOnUTC(String CreatedOnUTC) {
		this.CreatedOnUTC = CreatedOnUTC;
	}

	/**
	 * 
	 * @return The ModifiedOnUTC
	 */
	public String getModifiedOnUTC() {
		return ModifiedOnUTC;
	}

	/**
	 * 
	 * @param ModifiedOnUTC
	 *            The ModifiedOnUTC
	 */
	public void setModifiedOnUTC(String ModifiedOnUTC) {
		this.ModifiedOnUTC = ModifiedOnUTC;
	}

}
