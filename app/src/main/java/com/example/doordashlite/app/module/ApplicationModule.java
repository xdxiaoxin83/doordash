package com.example.doordashlite.app.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application application;
    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context applicationContext() {
        return application.getApplicationContext();
    }
}
