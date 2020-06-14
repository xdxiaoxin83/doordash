package com.example.doordashlite.network;

import com.example.doordashlite.model.Restaurant;
import com.example.doordashlite.model.RestaurantDetail;
import com.google.common.base.Optional;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkClient {
    private final PublishSubject<Optional<Response<List<Restaurant>>>> responseSubject;
    private final PublishSubject<Optional<Response<RestaurantDetail>>> detailSubject;

    private final NetworkApi api;
    final Callback<List<Restaurant>> callback;
    final Callback<RestaurantDetail> restaurantDetailCallback;

    public NetworkClient(NetworkApi api) {
        responseSubject = PublishSubject.create();
        detailSubject = PublishSubject.create();
        this.api = api;
        callback = buildCallback();
        restaurantDetailCallback = buildDetailCallback();
    }

    public Observable<Optional<Response<List<Restaurant>>>> getRestaurants(double latitude, double longitude) {
        api.getRestaurants(latitude, longitude).enqueue(callback);
        return responseSubject.hide();
    }

    private Callback<List<Restaurant>> buildCallback() {
        return new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                responseSubject.onNext(Optional.of(response));
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                responseSubject.onNext(Optional.absent());
            }
        };
    }

    public Observable<Optional<Response<RestaurantDetail>>> getRestaurantDetail(String id) {
        api.getRestaurantDetail(id).enqueue(restaurantDetailCallback);
        return detailSubject.hide();
    }

    private Callback<RestaurantDetail> buildDetailCallback() {
        return new Callback<RestaurantDetail>() {
            @Override
            public void onResponse(Call<RestaurantDetail> call, Response<RestaurantDetail> response) {
                detailSubject.onNext(Optional.of(response));
            }

            @Override
            public void onFailure(Call<RestaurantDetail> call, Throwable t) {
                detailSubject.onNext(Optional.absent());
            }
        };
    }


}
