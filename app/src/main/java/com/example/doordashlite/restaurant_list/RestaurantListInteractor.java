package com.example.doordashlite.restaurant_list;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;

import com.example.doordashlite.location.DoorDashLocationManager;
import com.example.doordashlite.model.Restaurant;
import com.example.doordashlite.network.NetworkClient;
import com.example.doordashlite.restaurant_detail.RestaurantDetailActivity;
import com.example.doordashlite.restaurant_list.full_state.RestaurantListAdapter;
import com.example.doordashlite.restaurant_list.full_state.RestaurantListFullStateView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class RestaurantListInteractor implements RestaurantListAdapter.Listener {

    public static String ID_EXTRA = "id";

    @Inject NetworkClient networkClient;
    @Inject DoorDashLocationManager locationManager;
    @Inject
    RestaurantListAdapter adapter;

    @Inject
    RestaurantListFullStateView restaurantListView;
    @Inject RestaurantListPresenter presenter;
    @Inject
    Context context;

    @Nullable private Disposable disposable;

    void bind() {
        dispose();
        disposable = networkClient.getRestaurants(locationManager.getLat(), locationManager.getLang())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseOptional -> {
                    if (responseOptional.isPresent() && responseOptional.get().isSuccessful()) {
                        List<Restaurant> restaurantList =  responseOptional.get().body();
                         if (restaurantList != null) {
                             presenter.showFullState(restaurantListView);
                             restaurantListView.setAdapter(adapter);
                             adapter.setRestaurants(restaurantList);
                         }
                    }
                    // else show empty or error view
                });
    }

    void dispose() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    @VisibleForTesting
    public Intent onItemClicked(Restaurant restaurant) {
        Intent detail = new Intent(context, RestaurantDetailActivity.class);
        detail.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        detail.putExtra(ID_EXTRA, restaurant.getId());
        context.startActivity(detail);
        return detail;
    }

    public interface RestaurantListPresenter {

        void showFullState(RestaurantListFullStateView view);
    }
}
