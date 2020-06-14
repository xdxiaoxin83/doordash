package com.example.doordashlite.restaurant_list;

import android.content.Context;
import android.view.LayoutInflater;

import com.example.doordashlite.R;
import com.example.doordashlite.location.DoorDashLocationManager;
import com.example.doordashlite.network.NetworkClient;
import com.example.doordashlite.restaurant_list.full_state.RestaurantListAdapter;
import com.example.doordashlite.restaurant_list.full_state.RestaurantListFullStateView;

import dagger.Binds;
import dagger.BindsInstance;
import dagger.Provides;

public class RestaurantListBuilder {

    private ParentComponent parentComponent;

    public RestaurantListBuilder(ParentComponent parentComponent) {
        this.parentComponent = parentComponent;
    }

    RestaurantListInteractor build(RestaurantListView parentView) {
        RestaurantListInteractor interactor = new RestaurantListInteractor();
        Component component = DaggerRestaurantListBuilder_Component.builder()
                .parentComponent(parentComponent)
                .parentView(parentView)
                .interactor(interactor)
                .build();
        component.inject(interactor);
        return interactor;
    }

    public interface ParentComponent {
        NetworkClient networkClient();

        DoorDashLocationManager locationManager();

        Context context();
    }

    @dagger.Component(modules = RestaurantListActivityModule.class, dependencies = ParentComponent.class)
    interface Component {
        void inject(RestaurantListInteractor interactor);

        @dagger.Component.Builder
        interface Builder {
            Builder parentComponent(ParentComponent component);
            @BindsInstance
            Builder parentView(RestaurantListView view);
            @BindsInstance
            Builder interactor(RestaurantListInteractor interactor);
            Component build();
        }
    }

    @dagger.Module
    public abstract static class RestaurantListActivityModule {

        @Binds
        abstract RestaurantListInteractor.RestaurantListPresenter restaurantListPresenter(RestaurantListView view);

        @Provides
        static RestaurantListAdapter adapter(RestaurantListInteractor interactor) {
            return new RestaurantListAdapter(interactor);
        }

        @Provides
        static RestaurantListFullStateView fullStateView(RestaurantListView parentView) {
            return (RestaurantListFullStateView) LayoutInflater.from(parentView.getContext()).inflate(R.layout.restaurants_list_full_state_view, parentView, false);
        }
    }
}
