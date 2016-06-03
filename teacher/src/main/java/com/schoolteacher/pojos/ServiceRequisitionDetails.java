package com.schoolteacher.pojos;

/**
 * Created by Chandan on 11/5/2015.
 */
public class ServiceRequisitionDetails {
    public String ServiceRequisitionType;
    public String SRPublicId;
    public String SRUpdatedOnUtc;
    public String OnProfessionalFullName;
    public String FacilityFullName;
    public String SRCreatedOnUtc;
    public String ToProfessionalFullName;

    public String getServiceRequisitionType() {
        return ServiceRequisitionType;
    }

    public void setServiceRequisitionType(String serviceRequisitionType) {
        ServiceRequisitionType = serviceRequisitionType;
    }

    public String getOnProfessionalFullName() {
        return OnProfessionalFullName;
    }

    public void setOnProfessionalFullName(String onProfessionalFullName) {
        OnProfessionalFullName = onProfessionalFullName;
    }

    public String getSRUpdatedOnUtc() {
        return SRUpdatedOnUtc;
    }

    public void setSRUpdatedOnUtc(String SRUpdatedOnUtc) {
        this.SRUpdatedOnUtc = SRUpdatedOnUtc;
    }

    public String getSRPublicId() {
        return SRPublicId;
    }

    public void setSRPublicId(String SRPublicId) {
        this.SRPublicId = SRPublicId;
    }

    public String getFacilityFullName() {
        return FacilityFullName;
    }

    public void setFacilityFullName(String facilityFullName) {
        FacilityFullName = facilityFullName;
    }

    public String getSRCreatedOnUtc() {
        return SRCreatedOnUtc;
    }

    public void setSRCreatedOnUtc(String SRCreatedOnUtc) {
        this.SRCreatedOnUtc = SRCreatedOnUtc;
    }

    public String getToProfessionalFullName() {
        return ToProfessionalFullName;
    }

    public void setToProfessionalFullName(String toProfessionalFullName) {
        ToProfessionalFullName = toProfessionalFullName;
    }


}
