package com.schoolteacher.mylibrary.session;

/**
 * Created by shah on 18/3/16.
 */

import android.content.Context;
import android.content.SharedPreferences;


public class Home {
    // Shared Preferences
    SharedPreferences pref;
    // Editor for Shared preferences
    SharedPreferences.Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;


    public static final String PREF_NAME = "home";
//home data
public String Key_HomeId="home";
    public String Key_Profile="profile";
    public   String KEY_ProfileId = "profileid";
    public  String KEY_MemberId="memberid";
    public   String KEY_FirstName= "firstname";
    public   String KEY_LastName= "lastname";
    public   String KEY_Weight= "weight";
    public   String KEY_Height= "height";
    public   String KEY_HeightUnit= "heightunit";
    public   String KEY_Title= "title";
    public   String KEY_FullName= "fullname";
    public   String KEY_Gender= "gender";
    public   String KEY_Email= "email";
    public   String KEY_CellNumber= "cellnumber";
    public   String KEY_DateOfBirth= "dob";
    public   String KEY_PhotoURL= "photourl";
    public   String KEY_ConsultationFees= "consultationfees";
    public   String KEY_IdentityMarks= "identitymarks";
    public   String KEY_StartYear="startyear";
    public   String KEY_MedicalConditions= "medical";
    public   String KEY_Allergies= "allergies";
    public   String KEY_BloodGroupType="bloodgrouptype";
    public   String KEY_ServicesOffered= "servicesoffered";
    public   String KEY_Features= "features";
    public   String KEY_Degrees= "degrees";
    public   String KEY_Expertises = "expertises";
    public   String KEY_Specialities = "specialities";
    public   String KEY_IsFacilityProfile = "IsFacilityProfile";
    public   String KEY_ConsultationDetails ="ConsultationDetails";
    public   String KEY_ProfileSettingDetails ="ProfileSettingDetails()";
    public   String KEY_MemberContactInformation ="MemberContactInformation()";
    public String Key_age="age";

    public String Key_jeevCriteria="jeevCriteria";
    public String Key_filter="filter";
    public String Key_isNearMeChecked="isNearMeChecked";

    public String getKey_jeevCriteria() {

        return pref.getString(Key_jeevCriteria, null);
    }


    public String getKey_HomeId() {

        return pref.getString(Key_HomeId, null);
    }
    public String getKey_profile() {

        return pref.getString(Key_Profile, null);
    }


    public String getKey_filter() {
        return pref.getString(Key_filter, null);
    }

    public void setKey_jeevCriteria(String jeevCriteria) {

        editor.putString(Key_jeevCriteria, jeevCriteria);
        editor.commit();
    }


    public void setKey_HomeId(String homeid) {

        editor.putString(Key_HomeId, homeid);
        editor.commit();
    }
    public void setKey_Profile(String profile) {

        editor.putString(Key_Profile, profile);
        editor.commit();
    }


    public void setKey_filter(String filter) {

        editor.putString(Key_filter, filter);
        editor.commit();
    }

    public void setKey_isNearMeChecked(String isNearMeChecked) {
        editor.putString(Key_isNearMeChecked, isNearMeChecked);
        editor.commit();
    }

    public String getKey_isNearMeChecked() {


        return pref.getString(Key_isNearMeChecked, null);
    }

    // Constructor
    public Home(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public void logoutUser() {
        // Clearing all data from Home Preferences
        editor.clear();
        editor.commit();
    }

    public String getKey_age() {
        return pref.getString(Key_age, null);
    }


    public String getKEY_ConsultationDetails() {
        return pref.getString(KEY_ConsultationDetails, null);
    }

    public String getKEY_ProfileSettingDetails() {
        return pref.getString(KEY_ProfileSettingDetails, null);
    }

    public String getKEY_MemberContactInformation() {
       return pref.getString(KEY_MemberContactInformation, null);
    }


    public String getKEY_ProfileId() {

        return pref.getString(KEY_ProfileId, null);
    }

    public String getKEY_MemberId() {

        return pref.getString(KEY_MemberId, null);
    }

    public String getKEY_FirstName() {

        return pref.getString(KEY_FirstName, null);
    }

    public String getKEY_LastName() {

        return pref.getString(KEY_LastName, null);
    }

    public String getKEY_Weight() {

        return pref.getString(KEY_Weight, null);
    }

    public String getKEY_Height() {

        return pref.getString(KEY_Height, null);
    }

    public String getKEY_HeightUnit() {

        return pref.getString(KEY_HeightUnit, null);
    }

    public String getKEY_Title() {

        return pref.getString(KEY_Title, null);
    }

    public String getKEY_FullName() {

        return pref.getString(KEY_FullName, null);
    }

    public String getKEY_Gender() {

        return pref.getString(KEY_Gender, null);
    }

    public String getKEY_Email() {

        return pref.getString(KEY_Email, null);
    }

    public String getKEY_CellNumber() {

        return pref.getString(KEY_CellNumber, null);
    }

    public String getKEY_DateOfBirth() {

        return pref.getString(KEY_DateOfBirth, null);
    }

    public String getKEY_PhotoURL() {

        return pref.getString(KEY_PhotoURL, null);
    }

    public String getKEY_ConsultationFees() {

        return pref.getString(KEY_ConsultationFees, null);
    }

    public String getKEY_IdentityMarks() {

        return pref.getString(KEY_IdentityMarks, null);
    }

    public String getKEY_StartYear() {

        return pref.getString(KEY_StartYear, null);
    }

    public String getKEY_MedicalConditions() {

        return pref.getString(KEY_MedicalConditions, null);
    }

    public String getKEY_Allergies() {

        return pref.getString(KEY_Allergies, null);
    }

    public String getKEY_BloodGroupType() {

        return pref.getString(KEY_BloodGroupType, null);
    }

    public String getKEY_ServicesOffered() {

        return pref.getString(KEY_ServicesOffered, null);
    }

    public String getKEY_Features() {

        return pref.getString(KEY_Features, null);
    }

    public String getKEY_Degrees() {

        return pref.getString(KEY_Degrees, null);
    }

    public String getKEY_Expertises() {

        return pref.getString(KEY_Expertises, null);
    }

    public String getKEY_Specialities() {

        return pref.getString(KEY_Specialities, null);
    }

    public String getKEY_IsFacilityProfile() {
        return KEY_IsFacilityProfile;
    }


    /////////////////////////setters
    /////////////////////////setters
    /////////////////////////setters
    /////////////////////////setters


    public void setKey_age(String age) {

         editor.putString(Key_age, age);
       editor.commit();            }

    public void setKEY_ConsultationDetails(String ConsultationDetails) {

        editor.putString(KEY_ConsultationDetails, ConsultationDetails);
        editor.commit();
    }

    public void setKEY_ProfileSettingDetails(String ProfileSettingDetails) {
        editor.putString(KEY_ProfileSettingDetails, ProfileSettingDetails);
        editor.commit();
    }

    public void setKEY_MemberContactInformation(String MemberContactInformation) {
        editor.putString(KEY_MemberContactInformation, MemberContactInformation);
        editor.commit();
    }
    public void setKEY_ProfileId(String ProfileId) {

        editor.putString(KEY_ProfileId, ProfileId);
        editor.commit();
    }

    public void setKEY_MemberId(String MemberId) {

        editor.putString(KEY_MemberId, MemberId);
        editor.commit();
    }

    public void setKEY_FirstName(String FirstName) {

        editor.putString(KEY_FirstName, FirstName);
        editor.commit();
    }

    public void setKEY_LastName(String LastName) {

        editor.putString(KEY_LastName, LastName);
        editor.commit();
    }

    public void setKEY_Weight(String Weight) {

        editor.putString(KEY_Weight, Weight);
        editor.commit();
    }

    public void setKEY_Height(String Height) {

        editor.putString(KEY_Height, Height);
        editor.commit();
    }

    public void setKEY_HeightUnit(String HeightUnit) {

        editor.putString(KEY_HeightUnit, HeightUnit);
        editor.commit();
    }

    public void setKEY_Title(String Title) {

        editor.putString(KEY_Title, Title);
        editor.commit();
    }

    public void setKEY_FullName(String FullName) {

        editor.putString(KEY_FullName, FullName);
        editor.commit();
    }

    public void setKEY_Gender(String Gender) {

        editor.putString(KEY_Gender, Gender);
        editor.commit();
    }

    public void setKEY_Email(String Email) {

        editor.putString(KEY_Email, Email);
        editor.commit();
    }

    public void setKEY_CellNumber(String CellNumber) {

        editor.putString(KEY_CellNumber, CellNumber);
        editor.commit();
    }

    public void setKEY_DateOfBirth(String DateOfBirth) {

        editor.putString(KEY_DateOfBirth, DateOfBirth);
        editor.commit();
    }

    public void setKEY_PhotoURL(String PhotoURL) {

        editor.putString(KEY_PhotoURL, PhotoURL);
        editor.commit();
    }

    public void setKEY_ConsultationFees(String ConsultationFees) {

        editor.putString(KEY_ConsultationFees, ConsultationFees);
        editor.commit();
    }

    public void setKEY_IdentityMarks(String IdentityMarks) {

        editor.putString(KEY_IdentityMarks, IdentityMarks);
        editor.commit();
    }

    public void setKEY_StartYear(String StartYear) {

        editor.putString(KEY_StartYear, StartYear);
        editor.commit();
    }

    public void setKEY_MedicalConditions(String MedicalConditions) {

        editor.putString(KEY_MedicalConditions, MedicalConditions);
        editor.commit();
    }

    public void setKEY_Allergies(String Allergies) {

        editor.putString(KEY_Allergies, Allergies);
        editor.commit();
    }

    public void setKEY_BloodGroupType(String BloodGroupType) {

        editor.putString(KEY_BloodGroupType, BloodGroupType);
        editor.commit();
    }

    public void setKEY_ServicesOffered(String ServicesOffered) {

        editor.putString(KEY_ServicesOffered, ServicesOffered);
        editor.commit();
    }

    public void setKEY_Features(String Features) {

        editor.putString(KEY_Features, Features);
        editor.commit();
    }

    public void setKEY_Degrees(String Degrees) {

        editor.putString(KEY_Degrees, Degrees);
        editor.commit();
    }

    public void setKEY_Expertises(String Expertises) {

        editor.putString(KEY_Expertises, Expertises);
        editor.commit();
    }

    public void setKEY_Specialities(String Specialities) {

        editor.putString(KEY_Specialities, Specialities);
        editor.commit();
    }

    public void setKEY_IsFacilityProfile(String IsFacilityProfile) {

        editor.putString(KEY_IsFacilityProfile, IsFacilityProfile);
        editor.commit();
    }



}
