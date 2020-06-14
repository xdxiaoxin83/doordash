package com.example.doordashlite.restaurant_detail;

import android.content.Context;

import com.example.doordashlite.model.RestaurantDetail;
import com.example.doordashlite.network.NetworkClient;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class RestaurantDetailInteractor {

    private Disposable disposable;

    @Inject
    NetworkClient networkClient;

    @Inject Context context;

    @Inject RestaurantDetailPresenter presenter;
    @Inject String id;

    void bind() {
        dispose();
        disposable = networkClient.getRestaurantDetail(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseOptional -> {
                    if (responseOptional.isPresent() && responseOptional.get().isSuccessful()) {
                        presenter.update(responseOptional.get().body(),  context);
                    }
                });
    }

    void dispose() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public interface RestaurantDetailPresenter {
        void update(RestaurantDetail restaurantDetail, Context context);
    }
}
