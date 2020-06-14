package com.example.doordashlite.app;

import android.app.Application;

import com.example.doordashlite.app.module.ApplicationModule;

public class DoorDashLiteApplication extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}