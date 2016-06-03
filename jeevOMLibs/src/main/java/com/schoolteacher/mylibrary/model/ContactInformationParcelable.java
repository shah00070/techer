package com.schoolteacher.mylibrary.model;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

public class ContactInformationParcelable implements Parcelable ,Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String PrimaryPhone;
	private String SecondaryPhone;
	private String TertiaryPhone;
	private String SecondaryEmail;
	private String TertiaryEmail;

	public String getPrimaryPhone() {
		return PrimaryPhone;
	}

	public void setPrimaryPhone(String primaryPhone) {
		PrimaryPhone = primaryPhone;
	}

	public String getSecondaryPhone() {
		return SecondaryPhone;
	}

	public void setSecondaryPhone(String secondaryPhone) {
		SecondaryPhone = secondaryPhone;
	}

	public String getTertiaryPhone() {
		return TertiaryPhone;
	}

	public void setTertiaryPhone(String tertiaryPhone) {
		TertiaryPhone = tertiaryPhone;
	}

	public String getSecondaryEmail() {
		return SecondaryEmail;
	}

	public void setSecondaryEmail(String secondaryEmail) {
		SecondaryEmail = secondaryEmail;
	}

	public String getTertiaryEmail() {
		return TertiaryEmail;
	}

	public void setTertiaryEmail(String tertiaryEmail) {
		TertiaryEmail = tertiaryEmail;
	}

	public ContactInformationParcelable() {
		// TODO Auto-generated constructor stub
	}

	public ContactInformationParcelable(Parcel in) {
		readFromParcel(in);
	}

	public static final Parcelable.Creator<ContactInformationParcelable> CREATOR = new Creator<ContactInformationParcelable>() {

		@Override
		public ContactInformationParcelable createFromParcel(Parcel source) {
//			ContactInformationParcelable contact = new ContactInformationParcelable();
//
//			contact.PrimaryPhone = source.readString();
//			contact.SecondaryPhone = source.readString();
//			contact.TertiaryPhone = source.readString();
//			contact.SecondaryEmail = source.readString();
//			contact.TertiaryEmail = source.readString();
//
//			return contact;
			return new ContactInformationParcelable(source);
		}

		@Override
		public ContactInformationParcelable[] newArray(int size) {
			return new ContactInformationParcelable[size];
		}
	};

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(PrimaryPhone);
		dest.writeString(SecondaryPhone);
		dest.writeString(TertiaryPhone);
		dest.writeString(SecondaryEmail);
		dest.writeString(TertiaryEmail);

	}

	private void readFromParcel(Parcel in) {

		PrimaryPhone = in.readString();
		SecondaryPhone = in.readString();
		TertiaryPhone = in.readString();
		SecondaryEmail = in.readString();
		TertiaryEmail = in.readString();

	}
}
