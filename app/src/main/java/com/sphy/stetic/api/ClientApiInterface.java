package com.sphy.stetic.api;

import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.Domain.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ClientApiInterface {
    @GET("clients")
    Call<List<Client>> getClients();

    @GET("clients/{clientId}")
    Call<Client> getClientById(@Path("clientId") long clientId);

    @POST("clients")
    Call<Client> addClient(@Body Client client);

    @DELETE("client/{dni}")
    Call<Void> deleteClient(@Path("dni") String dni);

    @PUT("clients/{clientId}")
    Call<Client> editClientById(@Path("clientId") long clientId, @Body Client client);

    @GET("clients")
    Call<List<Client>> searchClientsByName(
            @Query("name") String searchText
    );
    @GET("clients")
    Call<List<Client>> searchClientsByDni(
            @Query("dni") String searchText
    );

    @GET("clients")
    Call<List<Client>> searchClientsByVip(
            @Query("Vip") boolean vip
    );

}
