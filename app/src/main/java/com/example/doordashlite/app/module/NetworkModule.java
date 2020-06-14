package com.example.doordashlite.app.module;

import com.example.doordashlite.network.NetworkApi;
import com.example.doordashlite.network.NetworkClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class NetworkModule {

    public static final String BASE_URL = "https://api.doordash.com/";

    @Singleton
    @Provides
    static NetworkClient networkClient(NetworkApi api) {
        return new NetworkClient(api);
    }

    @Singleton
    @Provides
    static NetworkApi api() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(NetworkApi.class);
    }
}
