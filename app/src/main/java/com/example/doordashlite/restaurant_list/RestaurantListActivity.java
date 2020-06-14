package com.example.doordashlite.restaurant_list;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doordashlite.R;
import com.example.doordashlite.app.ApplicationComponent;
import com.example.doordashlite.app.DoorDashLiteApplication;

public class RestaurantListActivity extends AppCompatActivity {

    @Nullable
    RestaurantListInteractor interactor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ApplicationComponent applicationComponent = ((DoorDashLiteApplication) getApplication()).getComponent();
        RestaurantListBuilder builder = new RestaurantListBuilder(applicationComponent);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurants_list_view);
        RestaurantListView view = findViewById(R.id.restaurant_list);
        interactor = builder.build(view);
        interactor.bind();
    }

    @Override
    protected void onDestroy() {
        if (interactor != null) {
            interactor.dispose();
            interactor = null;
        }
        super.onDestroy();
    }
}
