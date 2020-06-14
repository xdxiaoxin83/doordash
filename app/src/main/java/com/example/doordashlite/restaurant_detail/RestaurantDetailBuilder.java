package com.example.doordashlite.restaurant_detail;

import android.content.Context;

import com.example.doordashlite.network.NetworkClient;
import com.example.doordashlite.restaurant_list.RestaurantListView;

import dagger.Binds;
import dagger.BindsInstance;
import dagger.Provides;

public class RestaurantDetailBuilder {

    private ParentComponent parentComponent;

    public RestaurantDetailBuilder(ParentComponent parentComponent) {
        this.parentComponent = parentComponent;
    }

    RestaurantDetailInteractor build(RestaurantDetailView parentView, String id) {
        RestaurantDetailInteractor interactor = new RestaurantDetailInteractor();
        Component component = DaggerRestaurantDetailBuilder_Component.builder()
                .parentComponent(parentComponent)
                .parentView(parentView)
                .interactor(interactor)
                .id(id)
                .build();
        component.inject(interactor);
        return interactor;
    }

    public interface ParentComponent {
        NetworkClient networkClient();
        Context context();
    }

    @dagger.Component(modules = RestaurantDetailActivityModule.class, dependencies = ParentComponent.class)
    interface Component {
        void inject(RestaurantDetailInteractor interactor);

        @dagger.Component.Builder
        interface Builder {
            Builder parentComponent(ParentComponent component);
            @BindsInstance
            Builder parentView(RestaurantDetailView view);
            @BindsInstance
            Builder interactor(RestaurantDetailInteractor interactor);
            @BindsInstance
            Builder id(String id);
            Component build();
        }
    }

    @dagger.Module
    public abstract static class RestaurantDetailActivityModule {

        @Binds
        abstract RestaurantDetailInteractor.RestaurantDetailPresenter restaurantDetailPresenter(RestaurantDetailView view);
    }
}
