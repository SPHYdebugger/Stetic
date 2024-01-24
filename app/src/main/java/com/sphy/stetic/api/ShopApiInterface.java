package com.sphy.stetic.api;

import com.sphy.stetic.Domain.Shop;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ShopApiInterface {
    @GET("shops")
    Call<List<Shop>> getShops();

    @GET("shops/{shopId}")
    Call<Shop> getShopById(@Path("shopId") long shopId);

    @POST("shops")
    Call<Shop> addShop(@Body Shop shop);

    @DELETE("shop/{shopId}")
    Call<Void> deleteShop(@Path("shopId") long shopId);

    @PUT("shop/{shopId}")
    Call<Shop> editShopById(@Path("shopId") long shopId, @Body Shop shop);

    @GET("shops")
    Call<List<Shop>> getShopsByCity(
        @Query("city") String searchCity
    );



}
