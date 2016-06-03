package com.schoolteacher.service;

import com.schoolteacher.library.pojo.ApiResponse;
import com.schoolteacher.pojos.Packages;
import com.schoolteacher.pojos.RecentTransactionHistory;
import com.schoolteacher.pojos.WalletAmountResponse;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Query;

/**
 * Created by user on 9/3/2015.
 */
public interface Wallet {

    @GET("/Wallet/Summary")
    void getWalletAmount(@Header("X-Jeevom-RequestLocation") String locationObject, @Header("authorization") String token,
                         @Query("memberId") int memberId,
                         Callback<ApiResponse<WalletAmountResponse>> callback);

    @GET("/Transaction/list")
    void getTransactionHistory(@Header("X-Jeevom-RequestLocation") String locationObject, @Header("authorization") String token,
                               @Query("paymentSource") String source,
                               @Query("memberId") int memberId,
                               Callback<ApiResponse<List<RecentTransactionHistory>>> callback);

    @GET("/Package/list")
    void getPackageTypes(@Header("X-Jeevom-RequestLocation") String locationObject, @Query("packageType") String type,
                         Callback<ApiResponse<List<Packages>>> callback);
}
