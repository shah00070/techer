package com.schoolteacher.mylibrary.util;

public interface JeevOMUtil {

    //  TEST URLS
  /*  String baseUrl = "https://core-services-test.jv-lab.net/api/v1/";
    String searchUrl = "http://search-services-test.jv-lab.net/api/v1/";
    String PaymentUrl = "https://mainapp-test.jv-lab.net/RequestProcess/BuyWalletPackageViaMobile";
    String PayNowUrl = "https://mainapp-test.jv-lab.net/";
*/
            /////// STAGING URLS
//    String baseUrl = "https://core-services-stage.jv-lab.net/api/v1/";
//    String searchUrl = "http://services-search-stage.jv-lab.net/api/v1/";
//    String PaymentUrl = "https://mainapp-test.jv-lab.net/RequestProcess/BuyWalletPackageViaMobile";
//    String PayNowUrl = "https://mainapp-test.jv-lab.net/";

    ///// PRODUCTION URLS
    String baseUrl = "https://services-core.jeevom.com/api/v1/";
    String searchUrl = "/*https://services-search.jeevom.com/api/v1/*/";
    String PaymentUrl = "/*https://www.jeevom.com/RequestProcess/BuyWalletPackageViaMobile*/";
    String PayNowUrl = "/*https://www.jeevom.com/*/";
//test-school-triangle
 // String baseurl="https://services-core-test.study-lab.biz:9091/";

  //production url school triangle
  String baseurl="https://services-core.studytriangle.com:9091/";








    String getFacilityProfile = "Facility/Profile/";
    String ForgetPassword = "Email/Send";
    String ForgetPhonePassword = "Phone/Send";
    String verification = "membership/Verify";
    String missedCallFirst = "membership/phone/";
    String missedCallSecond = "/verifyViaMissedCall";
    String callVerificationStatus = "/missedCallVerificationStatus";

    String regenerateCode = "membership/ResendEmailVerificationCode";
    String resetPassword = "Membership/ResetPassword";
    String phoneResendCode = "membership/ResendPhoneVerificationCode";

    String EMPTY_EMAIL_PHONE = "Enter Email/Phone";
    String EMPTY_CODE = " Please enter code";
    String EMPTY_EMAIL_OR_PHONE = " Please enter your email id/Student Id.";

    String EMPTY_ADDRESS="Please enter your address.";
    String EMPTY_EMAIL="Please enter your email id.";
    String EMPTY_PHONE="Please enter your phone no.";
    String EMPTY_DOB="Please enter your date of birth.";
    String PASSWORD_CHANGED_SUCCESSFULLY_MESSAGE = "The password is changed succesfully";
    String EMPTY_FIRST_NAME = " Please enter your First Name";
    String EMPTY_LAST_NAME = "Please enter your Last  Name";
    String EMPTY_PHONE_EMAIL = "Please enter valid email or phone no.";
    String VALID_EMAIL = "Please enter a valid email id";
    String VALID_Student_Id = "Please enter a valid Student Id";
    String MANDATORY_DATE_OF_BIRTH = "Please enter Date  of Birth";
    String EMPTY_PASSWORD = "Enter Password";
    String EMPTY_NEW_PASSWORD = "Enter New Password";
    String EMPTY_CURRENT_PASSWORD = "Enter Current Password";
    String EMPTY_CONFIRM_PASSWORD = "Confirm Password Can't  be Empty";
    String PASSWORD_MATCH = "Please ensure that the passwords match";
    String PASSWORD_VALID_MSG = "Password must be between 5-20 characters and must have at least 1 special character or a number or a capital letter.";
    String SOMETHING_WRONG = "Not able to connect you to SchoolTriangle right now.Please try after some time.";
    String INTERNET_CONNECTION = "Please Check Your Internet Connection.";
    String INTERNET_CONNECTION_SLOW = "Internet Connection is slow. Try Again.";
    String VALID_DOB = "Minimum Age Should be 18 years";

    String IMAGE_NOT_SELECTEDE = "no image selected";
    String OLD_NEW_PASSWORD_SAME = "Your current password and your new password cannot be the same";

    int TOKEN_FORGET_PASSWORD = 4;

    int TOKEN_VERIFICATION = 2;
    int TOKEN_VERIFICATION_PHONE = 1;

    int TOKEN_PASSWORD_CHANGE_PHONE = 7;
    int TOKEN_PASSWORD_CHANGE_EMAIL = 8;


    String mapCountryList = "ContactInformation/country/list";
    String mapStateList = "ContactInformation/state/list/";
    String mapCityList = "ContactInformation/city/list/";
    String mapAreaList = "ContactInformation/area/list/";

    String missedCallText = "Give us a missed call on ";
    String tollFreeNo = "1800 700 600";
    String tollFreeText = "(tollfree)";
    String emailText = "Enter below the code that is mentioned in your E-mail";
    String phoneText = "Enter below the code that is received by SMS";
    String emailVerification = "E-mail Verification";
    String phoneVerification = "Phone Verification";
    String emailVerificationMessage = "We sent you a verification message on your e-mail. Please click the link that you have received in your e-mail.";

    String MANDATORY_COUNTRY = "Country is mandatory";
    //String MANDATORY_POSTAL_CODE = "postal code is mandatory";
    String MANDATORY_STATE = "State is mandatory";
    String MANDATORY_AREA = "Area is mandatory";
    String MANDATORY_CITY = "City is mandatory";
    String MANDATORY_LOCAL_ADDRESS = "Enter Local Address";

    // verification code regenerate
    String verification_code_regenerate = "Successfully generated Code, Please check your email/phone";

    String FORGET_PASSWORD_MESSAGE = "Enter the email address / phone no. that you registered with and we will send you the instructions to reset your password.";
    String RESET_PASSWORD_MESSAGE = "Please verify the code sent to you on your registered email/phone no. and enter password and confirm password";


    int SIGNUP_MAIN_REQUEST_CODE_FROM_SIGNIN_PAGE = 9;
    int PHONE_EMAIL_VER_REQUEST_CODE_FROM_SIGNUP_PAGE = 10;
    int PHONE_AND_EMAIL_VER_REQUEST_CODE_FROM_SIGNUP_PAGE = 11;
    int SIGNUP_REQUEST_CODE_FROM_PHONE_EMAIL_VER_PAGE = 8;
    int SIGNUP_REQUEST_CODE_FROM_PHONE_AND_EMAIL_VER_PAGE = 12;

    int CAMERA_REQUEST_CODE = 48;
    int GALLERY_REQUEST_CODE = 49;
    int IMAGE_CROP_REQUEST_CODE = 50;
    String PHONE_TYPE = "phone";
    String EMAIL_TYPE = "email";
    String PHONE_MANDATORY_FOR_REQUEST = "phone";
    String EMAIL_MANDATORY_FOR_REQUEST = "email";
    String SELECT_GENDER = "Please select Gender";

}
