package com.schoolteacher.pojos;

/**
 * Created by chandan on 2/12/15.
 */
public class InstantAppointmentData {

    public String memberPublicId;
    public String serviceRequisitionType;
    public String professionalPubicId;
    public String FacilityPublicId;

    public String getMemberPublicId() {
        return memberPublicId;
    }

    public void setMemberPublicId(String memberPublicId) {
        this.memberPublicId = memberPublicId;
    }

    public String getServiceRequisitionType() {
        return serviceRequisitionType;
    }

    public void setServiceRequisitionType(String serviceRequisitionType) {
        this.serviceRequisitionType = serviceRequisitionType;
    }

    public String getProfessionalPubicId() {
        return professionalPubicId;
    }

    public void setProfessionalPubicId(String professionalPubicId) {
        this.professionalPubicId = professionalPubicId;
    }

    public String getFacilityPublicId() {
        return FacilityPublicId;
    }

    public void setFacilityPublicId(String facilityPublicId) {
        FacilityPublicId = facilityPublicId;
    }


}
