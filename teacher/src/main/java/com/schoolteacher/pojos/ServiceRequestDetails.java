package com.schoolteacher.pojos;

import java.util.List;

public class ServiceRequestDetails {
    private int Id;
    private List<SRDocumentList> SRDocumentList;
    private LabTests LabTests;
    private PurchaseRequest PurchaseRequest;
    private AdditionalConsumerDetail AdditionalConsumerDetail;
    private List<Message> Messages;
    private Object PaymentId;
    private Object PaymentMode;
    private Object PaymentAmount;
    private Object PaymentAmountAfterPromotion;
    private String PaymentStatusText;
    private String ForName;
    private int ForAge;
    private String ForGender;
    private boolean IsMedicalInfoShared;
    private boolean IsBasicDetailsShared;
    private boolean IsHealthTrackShared;
    private String PublicId;
    private int Fees;
    private String CreatedOnUTC;
    private String ServiceConfigurationCode;
    private String ServiceConfigurationName;
    private int ByMemberId;
    private String ByMemberPublicId;
    private String ByMemberTitle;
    private String ByMemberFirstName;
    private String ByMemberLastName;
    private String ByMemberDateOfBirth;
    private String ByMemberGender;
    private Object ByMemberAddress;
    private Object ByMemberPhoto;
    private int ForMemberId;
    private String ForMemberPublicId;
    private String ForMemberTitle;
    private String ForMemberFirstName;
    private String ForMemberLastName;
    private String ForMemberDateOfBirth;
    private String ForMemberGender;
    private ForMemberAddress ForMemberAddress;
    private Object ForMemberProfilePhoto;
    private String ForMemberPhoto;
    private Object OnProfessionalId;
    private Object OnProfessionalDetails;
    private int ToProfessionalId;
    private String ToProfessionalPublicId;
    private String ToProfessionalTitle;
    private String ToProfessionalFirstName;
    private String ToProfessionalLastName;
    private String ToProfessionalDateOfBirth;
    private String ToProfessionalGender;
    private ToProfessionalDetails ToProfessionalDetails;
    private int ToFacilityId;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public LabTests getLabTests() {
        return LabTests;
    }

    public void setLabTests(LabTests labTests) {
        LabTests = labTests;
    }

    public PurchaseRequest getPurchaseRequest() {
        return PurchaseRequest;
    }

    public void setPurchaseRequest(PurchaseRequest purchaseRequest) {
        PurchaseRequest = purchaseRequest;
    }

    public AdditionalConsumerDetail getAdditionalConsumerDetail() {
        return AdditionalConsumerDetail;
    }

    public void setAdditionalConsumerDetail(
            AdditionalConsumerDetail additionalConsumerDetail) {
        AdditionalConsumerDetail = additionalConsumerDetail;
    }

    public List<Message> getMessages() {
        return Messages;
    }

    public void setMessages(List<Message> messages) {
        Messages = messages;
    }

    public Object getPaymentId() {
        return PaymentId;
    }

    public void setPaymentId(Object paymentId) {
        PaymentId = paymentId;
    }

    public Object getPaymentMode() {
        return PaymentMode;
    }

    public void setPaymentMode(Object paymentMode) {
        PaymentMode = paymentMode;
    }

    public Object getPaymentAmount() {
        return PaymentAmount;
    }

    public void setPaymentAmount(Object paymentAmount) {
        PaymentAmount = paymentAmount;
    }

    public Object getPaymentAmountAfterPromotion() {
        return PaymentAmountAfterPromotion;
    }

    public void setPaymentAmountAfterPromotion(
            Object paymentAmountAfterPromotion) {
        PaymentAmountAfterPromotion = paymentAmountAfterPromotion;
    }

    public String getPaymentStatusText() {
        return PaymentStatusText;
    }

    public void setPaymentStatusText(String paymentStatusText) {
        PaymentStatusText = paymentStatusText;
    }

    public String getForName() {
        return ForName;
    }

    public void setForName(String forName) {
        ForName = forName;
    }

    public int getForAge() {
        return ForAge;
    }

    public void setForAge(int forAge) {
        ForAge = forAge;
    }

    public String getForGender() {
        return ForGender;
    }

    public void setForGender(String forGender) {
        ForGender = forGender;
    }

    public boolean isIsMedicalInfoShared() {
        return IsMedicalInfoShared;
    }

    public void setIsMedicalInfoShared(boolean isMedicalInfoShared) {
        IsMedicalInfoShared = isMedicalInfoShared;
    }

    public boolean isIsBasicDetailsShared() {
        return IsBasicDetailsShared;
    }

    public void setIsBasicDetailsShared(boolean isBasicDetailsShared) {
        IsBasicDetailsShared = isBasicDetailsShared;
    }

    public boolean isIsHealthTrackShared() {
        return IsHealthTrackShared;
    }

    public void setIsHealthTrackShared(boolean isHealthTrackShared) {
        IsHealthTrackShared = isHealthTrackShared;
    }

    public String getPublicId() {
        return PublicId;
    }

    public void setPublicId(String publicId) {
        PublicId = publicId;
    }

    public int getFees() {
        return Fees;
    }

    public void setFees(int fees) {
        Fees = fees;
    }

    public String getCreatedOnUTC() {
        return CreatedOnUTC;
    }

    public void setCreatedOnUTC(String createdOnUTC) {
        CreatedOnUTC = createdOnUTC;
    }

    public String getServiceConfigurationCode() {
        return ServiceConfigurationCode;
    }

    public void setServiceConfigurationCode(String serviceConfigurationCode) {
        ServiceConfigurationCode = serviceConfigurationCode;
    }

    public String getServiceConfigurationName() {
        return ServiceConfigurationName;
    }

    public void setServiceConfigurationName(String serviceConfigurationName) {
        ServiceConfigurationName = serviceConfigurationName;
    }

    public int getByMemberId() {
        return ByMemberId;
    }

    public void setByMemberId(int byMemberId) {
        ByMemberId = byMemberId;
    }

    public String getByMemberPublicId() {
        return ByMemberPublicId;
    }

    public void setByMemberPublicId(String byMemberPublicId) {
        ByMemberPublicId = byMemberPublicId;
    }

    public String getByMemberTitle() {
        return ByMemberTitle;
    }

    public void setByMemberTitle(String byMemberTitle) {
        ByMemberTitle = byMemberTitle;
    }

    public String getByMemberFirstName() {
        return ByMemberFirstName;
    }

    public void setByMemberFirstName(String byMemberFirstName) {
        ByMemberFirstName = byMemberFirstName;
    }

    public String getByMemberLastName() {
        return ByMemberLastName;
    }

    public void setByMemberLastName(String byMemberLastName) {
        ByMemberLastName = byMemberLastName;
    }

    public String getByMemberDateOfBirth() {
        return ByMemberDateOfBirth;
    }

    public void setByMemberDateOfBirth(String byMemberDateOfBirth) {
        ByMemberDateOfBirth = byMemberDateOfBirth;
    }

    public String getByMemberGender() {
        return ByMemberGender;
    }

    public void setByMemberGender(String byMemberGender) {
        ByMemberGender = byMemberGender;
    }

    public Object getByMemberAddress() {
        return ByMemberAddress;
    }

    public void setByMemberAddress(Object byMemberAddress) {
        ByMemberAddress = byMemberAddress;
    }

    public Object getByMemberPhoto() {
        return ByMemberPhoto;
    }

    public void setByMemberPhoto(Object byMemberPhoto) {
        ByMemberPhoto = byMemberPhoto;
    }

    public int getForMemberId() {
        return ForMemberId;
    }

    public void setForMemberId(int forMemberId) {
        ForMemberId = forMemberId;
    }

    public String getForMemberPublicId() {
        return ForMemberPublicId;
    }

    public void setForMemberPublicId(String forMemberPublicId) {
        ForMemberPublicId = forMemberPublicId;
    }

    public String getForMemberTitle() {
        return ForMemberTitle;
    }

    public void setForMemberTitle(String forMemberTitle) {
        ForMemberTitle = forMemberTitle;
    }

    public String getForMemberFirstName() {
        return ForMemberFirstName;
    }

    public void setForMemberFirstName(String forMemberFirstName) {
        ForMemberFirstName = forMemberFirstName;
    }

    public String getForMemberLastName() {
        return ForMemberLastName;
    }

    public void setForMemberLastName(String forMemberLastName) {
        ForMemberLastName = forMemberLastName;
    }

    public String getForMemberDateOfBirth() {
        return ForMemberDateOfBirth;
    }

    public void setForMemberDateOfBirth(String forMemberDateOfBirth) {
        ForMemberDateOfBirth = forMemberDateOfBirth;
    }

    public String getForMemberGender() {
        return ForMemberGender;
    }

    public void setForMemberGender(String forMemberGender) {
        ForMemberGender = forMemberGender;
    }

    public ForMemberAddress getForMemberAddress() {
        return ForMemberAddress;
    }

    public void setForMemberAddress(ForMemberAddress forMemberAddress) {
        ForMemberAddress = forMemberAddress;
    }

    public Object getForMemberProfilePhoto() {
        return ForMemberProfilePhoto;
    }

    public void setForMemberProfilePhoto(Object forMemberProfilePhoto) {
        ForMemberProfilePhoto = forMemberProfilePhoto;
    }

    public String getForMemberPhoto() {
        return ForMemberPhoto;
    }

    public void setForMemberPhoto(String forMemberPhoto) {
        ForMemberPhoto = forMemberPhoto;
    }

    public Object getOnProfessionalId() {
        return OnProfessionalId;
    }

    public void setOnProfessionalId(Object onProfessionalId) {
        OnProfessionalId = onProfessionalId;
    }

    public Object getOnProfessionalDetails() {
        return OnProfessionalDetails;
    }

    public void setOnProfessionalDetails(Object onProfessionalDetails) {
        OnProfessionalDetails = onProfessionalDetails;
    }

    public int getToProfessionalId() {
        return ToProfessionalId;
    }

    public void setToProfessionalId(int toProfessionalId) {
        ToProfessionalId = toProfessionalId;
    }

    public String getToProfessionalPublicId() {
        return ToProfessionalPublicId;
    }

    public void setToProfessionalPublicId(String toProfessionalPublicId) {
        ToProfessionalPublicId = toProfessionalPublicId;
    }

    public String getToProfessionalTitle() {
        return ToProfessionalTitle;
    }

    public void setToProfessionalTitle(String toProfessionalTitle) {
        ToProfessionalTitle = toProfessionalTitle;
    }

    public String getToProfessionalFirstName() {
        return ToProfessionalFirstName;
    }

    public void setToProfessionalFirstName(String toProfessionalFirstName) {
        ToProfessionalFirstName = toProfessionalFirstName;
    }

    public String getToProfessionalLastName() {
        return ToProfessionalLastName;
    }

    public void setToProfessionalLastName(String toProfessionalLastName) {
        ToProfessionalLastName = toProfessionalLastName;
    }

    public String getToProfessionalDateOfBirth() {
        return ToProfessionalDateOfBirth;
    }

    public void setToProfessionalDateOfBirth(String toProfessionalDateOfBirth) {
        ToProfessionalDateOfBirth = toProfessionalDateOfBirth;
    }

    public String getToProfessionalGender() {
        return ToProfessionalGender;
    }

    public void setToProfessionalGender(String toProfessionalGender) {
        ToProfessionalGender = toProfessionalGender;
    }

    public ToProfessionalDetails getToProfessionalDetails() {
        return ToProfessionalDetails;
    }

    public void setToProfessionalDetails(
            ToProfessionalDetails toProfessionalDetails) {
        ToProfessionalDetails = toProfessionalDetails;
    }

    public void setToFacilityPublicId(Object toFacilityPublicId) {
        ToFacilityPublicId = toFacilityPublicId;
    }

    public ToFacilityDetails getToFacilityDetails() {
        return ToFacilityDetails;
    }

    public void setToFacilityDetails(ToFacilityDetails toFacilityDetails) {
        ToFacilityDetails = toFacilityDetails;
    }

    public String getAppointmentDate() {
        return AppointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        AppointmentDate = appointmentDate;
    }

    public int getAppointmentStatus() {
        return AppointmentStatus;
    }

    public void setAppointmentStatus(int appointmentStatus) {
        AppointmentStatus = appointmentStatus;
    }

    public boolean isIsRepeat() {
        return IsRepeat;
    }

    public void setIsRepeat(boolean isRepeat) {
        IsRepeat = isRepeat;
    }

    public List<SRStatusLog> getSRStatusLog() {
        return SRStatusLog;
    }

    public void setSRStatusLog(List<SRStatusLog> sRStatusLog) {
        SRStatusLog = sRStatusLog;
    }


    public int getToFacilityId() {
        return ToFacilityId;
    }

    private Object ToFacilityPublicId;

    public Object getToFacilityPublicId() {
        return ToFacilityPublicId;
    }

    public void setToFacilityId(int toFacilityId) {
        ToFacilityId = toFacilityId;
    }

    private String ToFacilityName;

    public String getToFacilityName() {
        return ToFacilityName;
    }

    public void setToFacilityName(String toFacilityName) {
        ToFacilityName = toFacilityName;
    }

    public List<SRDocumentList> getSRDocumentList() {
        return SRDocumentList;
    }

    public void setSRDocumentList(List<SRDocumentList> sRDocumentList) {
        SRDocumentList = sRDocumentList;
    }

    private ToFacilityDetails ToFacilityDetails;
    private String AppointmentDate;
    private int AppointmentStatus;
    private boolean IsRepeat;
    private List<SRStatusLog> SRStatusLog;
}
