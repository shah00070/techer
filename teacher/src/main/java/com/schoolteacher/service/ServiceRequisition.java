package com.schoolteacher.service;

import com.schoolteacher.appointment.AppointmentRequestResponse;
import com.schoolteacher.library.pojo.ApiResponse;
import com.schoolteacher.mylibrary.pojo.AddressOfUser;
import com.schoolteacher.pojos.CallToActionRequest;
import com.schoolteacher.pojos.DropDownResponse;
import com.schoolteacher.pojos.GrabOfferData;
import com.schoolteacher.pojos.InstantAppointmentData;
import com.schoolteacher.pojos.JeevSlotsResult;
import com.schoolteacher.pojos.LabTestData;
import com.schoolteacher.pojos.LabTestFilterResults;
import com.schoolteacher.pojos.PayLaterResponseData;
import com.schoolteacher.pojos.ResponseApi;
import com.schoolteacher.pojos.ServiceConfigInfo;
import com.schoolteacher.pojos.ServiceRequestActionData;
import com.schoolteacher.pojos.ServiceRequestDetails;
import com.schoolteacher.pojos.ServiceRequestPaymentResponse;
import com.schoolteacher.pojos.ServiceRequisitionActionData;
import com.schoolteacher.pojos.ServiceRequisitionResult;
import com.schoolteacher.pojos.ServiceResultPromotionData;
import com.schoolteacher.pojos.SystemServiceConfigurationResult;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

public interface ServiceRequisition {
    @GET("/dictionary/Allergies")
    void getAllergiesForDropDown(@Header("authorization") String token, Callback<DropDownResponse> callback);

    @GET("/MasterData/Medicines")
    void getMedicinesList(Callback<ResponseApi<LabTestData>> callback);

    @GET("/dictionary/Medical%20Condition")
    void getMedicalConditionsForDropDown(@Header("authorization") String token, Callback<DropDownResponse> callback);

    @GET("/MasterData/LabTest")
    void getLabTestHints(Callback<LabTestFilterResults> callback);

    @GET("/FacilityProfile/{id}/Slots")
    void getSlots(@Header("X-Jeevom-RequestLocation") String locationObject, @Header("authorization") String token, @Path("id") String id, @Query("date") String date,
                  @Query("availability") boolean avail,
                  @Query("serviceConfig") String config,
                  @Query("facilityId") String fac_id,
                  Callback<JeevSlotsResult> callback);

    @GET("/ProfessionalProfile/{id}/Slots")
    void getProfessionalSlots(@Header("X-Jeevom-RequestLocation") String locationObject, @Header("authorization") String token, @Path("id") String id,
                              @Query("date") String date, @Query("availability") boolean avail,
                              @Query("serviceConfig") String config,
                              @Query("facilityId") String fac_id,
                              Callback<JeevSlotsResult> callback);

    @GET("/FacilityProfile/{profileId}/ServiceConfiguration?sessions=true")
    void getFacilityServiceConfigurations(@Header("X-Jeevom-RequestLocation") String locationObject, @Header("authorization") String token, @Path("profileId") String profileId,
                                          Callback<ServiceConfigInfo> callback);


    @GET("/serviceRequisition/c/{publicId}")
    void getAllEmailRequest(@Header("X-Jeevom-RequestLocation") AddressOfUser locationObject, @Path("publicId") String publicId,
                            @Header("authorization") String token,
                            Callback<AppointmentRequestResponse> callback);

    @GET("/ProfessionalProfile/{profileId}/ServiceConfiguration?sessions=true")
    void getServiceConfigurations(@Header("X-Jeevom-RequestLocation") AddressOfUser locationObject, @Header("authorization") String token, @Path("profileId") String profileId,
                                  Callback<ServiceConfigInfo> callback);

    @GET("/ServiceRequisition/SystemServiceConfiguration")
    void getServiceRequestActions(@Header("X-Jeevom-RequestLocation") AddressOfUser locationObject, @Query("code") String code,
                                  @Query("getConfiguredStatusActions") boolean status,
                                  @Query("userType") int usertype,
                                  Callback<ApiResponse<List<ServiceRequestActionData>>> callback);

    @GET("/ServiceRequisition/{type}/{id}/{srPublicId}")
    void getServiceRequestDetail(@Header("X-Jeevom-RequestLocation") AddressOfUser locationObject, @Header("authorization") String token,
                                 @Path("type") String type, @Path("id") String id,
                                 @Path("srPublicId") String srPublicId,
                                 @Query("isAllDetails") boolean value,
                                 Callback<ApiResponse<ServiceRequestDetails>> callback);

    @GET("/ServiceRequisition/SystemServiceConfiguration")
    void getSystemServiceConfigurations(@Header("X-Jeevom-RequestLocation") AddressOfUser locationObject, @Query("Code") String code,
                                        Callback<SystemServiceConfigurationResult> callback);

    @PUT("/ServiceRequisition/UpdateStatus")
    void changeServiceRequestStatus(@Header("X-Jeevom-RequestLocation") AddressOfUser locationObject, @Header("authorization") String token,
                                    @Body ServiceRequisitionActionData data,
                                    Callback<ApiResponse<String>> callback);

    @POST("/ServiceRequisition/Create")
    void makeServiceRequest(@Header("X-Jeevom-RequestLocation") AddressOfUser locationObject, @Header("authorization") String token, @Body CallToActionRequest json,
                            Callback<ServiceRequisitionResult> callback);

    @POST("/ServiceRequisition/Create")
    void makeServiceRequestClaimOffer(@Header("X-Jeevom-RequestLocation") AddressOfUser locationObject, @Header("authorization") String token, @Body GrabOfferData json,
                                      Callback<ServiceRequisitionResult> callback);

    @PUT("/ServiceRequisition/{service_requisition_id}/UpdateStatus")
    void updateServiceRequisitionStatus(@Header("X-Jeevom-RequestLocation") AddressOfUser locationObject, @Header("authorization") String token,
                                        @Path("service_requisition_id") String id,
                                        @Query("Status") String status,
                                        Callback<ResponseApi<String>> callback);

    @POST("/ServiceRequisition/InstantAppointment")
    void makeInstantServiceRequest(@Header("X-Jeevom-RequestLocation") AddressOfUser locationObject, @Header("authorization") String token, @Body InstantAppointmentData json,
                                   Callback<ServiceRequisitionResult> callback);

    @GET("/PromotionV1/ApplicablePromotions")
    void getDealsAndOfferList(@Header("authorization") String token, @Header("X-Jeevom-applicablePromotionRequest") String requestData,
                              Callback<ServiceResultPromotionData> callback);

    @POST("/Transaction/PayOnDelivery")
    void updatePayOnDeliveryStatus(@Header("authorization") String token, @Body String data, Callback<PayLaterResponseData> callback);

    @POST("/Transaction/BeginTransaction")
    void beginPayNowTransaction(@Header("authorization") String token, @Body String data, Callback<ServiceRequestPaymentResponse> callback);

}
