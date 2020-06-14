package com.example.doordashlite.restaurant_detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.doordashlite.R;
import com.example.doordashlite.app.ApplicationComponent;
import com.example.doordashlite.app.DoorDashLiteApplication;

import static com.example.doordashlite.restaurant_list.RestaurantListInteractor.ID_EXTRA;

public class RestaurantDetailActivity extends AppCompatActivity {

    private RestaurantDetailInteractor interactor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ApplicationComponent applicationComponent = ((DoorDashLiteApplication) getApplication()).getComponent();
        RestaurantDetailBuilder builder = new RestaurantDetailBuilder(applicationComponent);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurants_detail_view);
        RestaurantDetailView view = findViewById(R.id.restaurant_detail);
        interactor = builder.build(view, getId());
        interactor.bind();
    }

    private String getId() {
        Intent intent = getIntent();
        return intent.getStringExtra(ID_EXTRA);
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