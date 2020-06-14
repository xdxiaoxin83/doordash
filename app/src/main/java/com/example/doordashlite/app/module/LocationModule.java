package com.example.doordashlite.app.module;

import com.example.doordashlite.location.DoorDashLocationManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class LocationModule {

    @Singleton
    @Provides
    static DoorDashLocationManager locationManager() {
        return new DoorDashLocationManager();
    }
}
