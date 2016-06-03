package com.schoolteacher.pojos;

/**
 * Created by chandan on 31/12/15.
 */
public class MemberPackageTransactionRequest {

    public String Status;
    public String MemberId;
    public String Amount;
    public String FirstName;
    public String Email;
    public String CellNumber;

    /// External transaction id ///
    public String TxnId;

    public String PackageId;
    public String ProfileId;
    public String ProfileType;
    public int Quantity;
    public String MemberIP;
    public int ServiceRequestId;
    public String ServiceRequisitionid;
    public String ServiceRequisitionPublicId;
    public String AppointmentId;
    public String PaymentSource;
    public String FinalPkgAmountAfterPromotion;
    public boolean IsAppointmentTransaction;
    public int TransactionId;
    public String CreatedBy;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMemberId() {
        return MemberId;
    }

    public void setMemberId(String memberId) {
        MemberId = memberId;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCellNumber() {
        return CellNumber;
    }

    public void setCellNumber(String cellNumber) {
        CellNumber = cellNumber;
    }

    public String getTxnId() {
        return TxnId;
    }

    public void setTxnId(String txnId) {
        TxnId = txnId;
    }

    public String getPackageId() {
        return PackageId;
    }

    public void setPackageId(String packageId) {
        PackageId = packageId;
    }

    public String getProfileId() {
        return ProfileId;
    }

    public void setProfileId(String profileId) {
        ProfileId = profileId;
    }

    public String getProfileType() {
        return ProfileType;
    }

    public void setProfileType(String profileType) {
        ProfileType = profileType;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getMemberIP() {
        return MemberIP;
    }

    public void setMemberIP(String memberIP) {
        MemberIP = memberIP;
    }

    public int getServiceRequestId() {
        return ServiceRequestId;
    }

    public void setServiceRequestId(int serviceRequestId) {
        ServiceRequestId = serviceRequestId;
    }

    public String getServiceRequisitionid() {
        return ServiceRequisitionid;
    }

    public void setServiceRequisitionid(String serviceRequisitionid) {
        ServiceRequisitionid = serviceRequisitionid;
    }

    public String getServiceRequisitionPublicId() {
        return ServiceRequisitionPublicId;
    }

    public void setServiceRequisitionPublicId(String serviceRequisitionPublicId) {
        ServiceRequisitionPublicId = serviceRequisitionPublicId;
    }

    public String getAppointmentId() {
        return AppointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        AppointmentId = appointmentId;
    }

    public String getPaymentSource() {
        return PaymentSource;
    }

    public void setPaymentSource(String paymentSource) {
        PaymentSource = paymentSource;
    }

    public String getFinalPkgAmountAfterPromotion() {
        return FinalPkgAmountAfterPromotion;
    }

    public void setFinalPkgAmountAfterPromotion(String finalPkgAmountAfterPromotion) {
        FinalPkgAmountAfterPromotion = finalPkgAmountAfterPromotion;
    }

    public boolean isAppointmentTransaction() {
        return IsAppointmentTransaction;
    }

    public void setIsAppointmentTransaction(boolean isAppointmentTransaction) {
        IsAppointmentTransaction = isAppointmentTransaction;
    }

    public int getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(int transactionId) {
        TransactionId = transactionId;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }
}
