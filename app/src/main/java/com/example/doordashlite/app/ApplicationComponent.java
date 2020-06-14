package com.example.doordashlite.app;

import android.content.Context;

import com.example.doordashlite.app.module.ApplicationModule;
import com.example.doordashlite.app.module.LocationModule;
import com.example.doordashlite.app.module.NetworkModule;
import com.example.doordashlite.restaurant_detail.RestaurantDetailBuilder;
import com.example.doordashlite.restaurant_list.RestaurantListBuilder;

import javax.inject.Singleton;

import dagger.Component;
@Singleton
@Component(
        modules = {NetworkModule.class, LocationModule.class, ApplicationModule.class}
)
public interface ApplicationComponent extends RestaurantListBuilder.ParentComponent, RestaurantDetailBuilder.ParentComponent {
}