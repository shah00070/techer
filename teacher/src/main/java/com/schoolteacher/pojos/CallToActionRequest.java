package com.schoolteacher.pojos;

import java.io.Serializable;
import java.util.List;

public class CallToActionRequest implements Serializable {

    private static final long serialVersionUID = 1230938946211380404L;
    private int Id;
    private String DefaultServiceConfigurationId;
    private String ServiceConfigurationId;
    private boolean IsSupportRequest;
    private String ToProfessionalId;
    private String ToFacilityId;
    private String WhereFacilityId;
    private boolean IsAppointment;
    private boolean IsRequestedByVisitor;
    private String VisitorTitle;
    private String VisitorFirstName;
    private String VisitorLastName;
    private String VisitorGender;
    private String VisitorAge;
    private String VisitorDateOfBirth;
    private String VisitorEmail;
    private String VisitorCellNumber;
    private boolean IsBasicDetailsShared;
    private boolean IsMedicalInfoShared;
    private boolean IsHealthTrackShared;
    private String DocumentIds;
    private String ByMemberId;
    private String ForMemberId;
    private String ForName;
    private String ForAge;
    private String ForGender;
    private boolean IsRequestedAnonymous;
    private String Fees;
    private List<CallToActionMessage> Messages;
    private CallToActionAppointment AppointmentDetails;
    private boolean IsIndirectRequest;
    private String CreatedBy;
    private String UpdatedBy;
    private OrderAddress DeliveryRequestedAt;
    private OrderAttributes Attributes;

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getUpdatedBy() {
        return UpdatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        UpdatedBy = updatedBy;
    }


    public boolean isIndirectRequest() {
        return IsIndirectRequest;
    }

    public void setIsIndirectRequest(boolean isIndirectRequest) {
        IsIndirectRequest = isIndirectRequest;
    }


    public OrderAddress getDeliveryRequestedAt() {
        return DeliveryRequestedAt;
    }

    public void setDeliveryRequestedAt(OrderAddress deliveryRequestedAt) {
        DeliveryRequestedAt = deliveryRequestedAt;
    }

    public OrderAttributes getAttributes() {
        return Attributes;
    }

    public void setAttributes(OrderAttributes attributes) {
        Attributes = attributes;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }


    public boolean isIsSupportRequest() {
        return IsSupportRequest;
    }

    public void setIsSupportRequest(boolean isSupportRequest) {
        IsSupportRequest = isSupportRequest;
    }

    public boolean isIsAppointment() {
        return IsAppointment;
    }

    public void setIsAppointment(boolean isAppointment) {
        IsAppointment = isAppointment;
    }

    public boolean isIsRequestedByVisitor() {
        return IsRequestedByVisitor;
    }

    public void setIsRequestedByVisitor(boolean isRequestedByVisitor) {
        IsRequestedByVisitor = isRequestedByVisitor;
    }

    public String getVisitorTitle() {
        return VisitorTitle;
    }

    public void setVisitorTitle(String visitorTitle) {
        VisitorTitle = visitorTitle;
    }

    public String getVisitorFirstName() {
        return VisitorFirstName;
    }

    public void setVisitorFirstName(String visitorFirstName) {
        VisitorFirstName = visitorFirstName;
    }

    public String getVisitorLastName() {
        return VisitorLastName;
    }

    public void setVisitorLastName(String visitorLastName) {
        VisitorLastName = visitorLastName;
    }

    public String getVisitorGender() {
        return VisitorGender;
    }

    public void setVisitorGender(String visitorGender) {
        VisitorGender = visitorGender;
    }

    public String getVisitorDateOfBirth() {
        return VisitorDateOfBirth;
    }

    public void setVisitorDateOfBirth(String visitorDateOfBirth) {
        VisitorDateOfBirth = visitorDateOfBirth;
    }

    public String getVisitorEmail() {
        return VisitorEmail;
    }

    public void setVisitorEmail(String visitorEmail) {
        VisitorEmail = visitorEmail;
    }

    public boolean isIsBasicDetailsShared() {
        return IsBasicDetailsShared;
    }

    public void setIsBasicDetailsShared(boolean isBasicDetailsShared) {
        IsBasicDetailsShared = isBasicDetailsShared;
    }

    public boolean isIsMedicalInfoShared() {
        return IsMedicalInfoShared;
    }

    public void setIsMedicalInfoShared(boolean isMedicalInfoShared) {
        IsMedicalInfoShared = isMedicalInfoShared;
    }

    public boolean isIsHealthTrackShared() {
        return IsHealthTrackShared;
    }

    public void setIsHealthTrackShared(boolean isHealthTrackShared) {
        IsHealthTrackShared = isHealthTrackShared;
    }

    public String getDocumentIds() {
        return DocumentIds;
    }

    public void setDocumentIds(String documentIds) {
        DocumentIds = documentIds;
    }

    public String getForName() {
        return ForName;
    }

    public void setForName(String forName) {
        ForName = forName;
    }

    public String getForAge() {
        return ForAge;
    }

    public void setForAge(String forAge) {
        ForAge = forAge;
    }

    public String getForGender() {
        return ForGender;
    }

    public void setForGender(String forGender) {
        ForGender = forGender;
    }

    public CallToActionAppointment getAppointmentDetails() {
        return AppointmentDetails;
    }

    public void setAppointmentDetails(CallToActionAppointment appointmentDetails) {
        AppointmentDetails = appointmentDetails;
    }

    public String getVisitorAge() {
        return VisitorAge;
    }

    public void setVisitorAge(String visitorAge) {
        VisitorAge = visitorAge;
    }

    public String getFees() {
        return Fees;
    }

    public void setFees(String fees) {
        Fees = fees;
    }

    public List<CallToActionMessage> getMessages() {
        return Messages;
    }

    public void setMessages(List<CallToActionMessage> messages) {
        Messages = messages;
    }

    public String getWhereFacilityId() {
        return WhereFacilityId;
    }

    public void setWhereFacilityId(String whereFacilityId) {
        WhereFacilityId = whereFacilityId;
    }

    public String getByMemberId() {
        return ByMemberId;
    }

    public void setByMemberId(String byMemberId) {
        ByMemberId = byMemberId;
    }

    public String getForMemberId() {
        return ForMemberId;
    }

    public void setForMemberId(String forMemberId) {
        ForMemberId = forMemberId;
    }

    public boolean isIsRequestedAnonymous() {
        return IsRequestedAnonymous;
    }

    public void setIsRequestedAnonymous(boolean isRequestedAnonymous) {
        IsRequestedAnonymous = isRequestedAnonymous;
    }

    public String getToProfessionalId() {
        return ToProfessionalId;
    }

    public void setToProfessionalId(String toProfessionalId) {
        ToProfessionalId = toProfessionalId;
    }

    public String getToFacilityId() {
        return ToFacilityId;
    }

    public void setToFacilityId(String toFacilityId) {
        ToFacilityId = toFacilityId;
    }

    public String getVisitorCellNumber() {
        return VisitorCellNumber;
    }

    public void setVisitorCellNumber(String visitorCellNumber) {
        VisitorCellNumber = visitorCellNumber;
    }

    public String getDefaultServiceConfigurationId() {
        return DefaultServiceConfigurationId;
    }

    public void setDefaultServiceConfigurationId(
            String defaultServiceConfigurationId) {
        DefaultServiceConfigurationId = defaultServiceConfigurationId;
    }

    public String getServiceConfigurationId() {
        return ServiceConfigurationId;
    }

    public void setServiceConfigurationId(String serviceConfigurationId) {
        ServiceConfigurationId = serviceConfigurationId;
    }

}
