package com.sphy.stetic.api;

import androidx.room.Delete;

import com.sphy.stetic.Domain.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductApiInterface {
    @GET("products")
    Call<List<Product>> getProducts();

    @POST("products")
    Call<Product> addProduct(@Body Product product);

    @DELETE("product/{productId}")
    Call<Void> deleteProduct(@Path("productId") long productId);
}
