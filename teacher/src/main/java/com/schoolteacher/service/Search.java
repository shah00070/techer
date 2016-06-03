package com.schoolteacher.service;

import com.schoolteacher.library.pojo.ApiResponse;
import com.schoolteacher.pojos.SaveSearchRequest;
import com.schoolteacher.pojos.SavedSearch;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by user on 9/3/2015.
 */
public interface Search
{
    @GET("/Search/Save/{id}")
    void getSavedSearch(@Header("X-Jeevom-RequestLocation") String locationObject, @Header("authorization") String token,
                        @Path("id") String id,
                        Callback<ApiResponse<List<SavedSearch>>> callback);
    @GET("/Search")
    void search(@Header("X-Jeevom-RequestLocation") String locationObject, @Header("X-Jeevom-searchCriteria") String criteria,
                @Header("X-Jeevom-searchFilter") String filter,
                @Header("X-Jeevom-profileClass") String memberId,
                @Header("x-jeevom-android-app-version") String version, @Header("authorization") String token,
                Callback<com.schoolteacher.pojos.JeevSearch> callback);
        @POST("/Search/Save")
        void saveSearch(@Header("X-Jeevom-RequestLocation") String locationObject, @Header("authorization") String token,
                        @Body SaveSearchRequest saveSearchRequest,
                        Callback<ApiResponse<Boolean>> callback);

    @PUT("/Search/Save/{id}")
    void modifySaveSearch(@Header("X-Jeevom-RequestLocation") String locationObject, @Header("authorization") String token,
                          @Body SaveSearchRequest saveSearchRequest, @Path("id") int id,
                          Callback<ApiResponse<Boolean>> callback);

}
