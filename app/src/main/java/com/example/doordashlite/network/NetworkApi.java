package com.example.doordashlite.network;

import com.example.doordashlite.model.Restaurant;
import com.example.doordashlite.model.RestaurantDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NetworkApi {
    @GET("/v2/restaurant/")
    Call<List<Restaurant>> getRestaurants(@Query("lat") double lat, @Query("lng") double lang);

    @GET("/v2/restaurant/{id}")
    Call<RestaurantDetail> getRestaurantDetail(@Path("id") String id);
}
