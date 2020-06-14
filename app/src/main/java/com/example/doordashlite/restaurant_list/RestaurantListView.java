package com.example.doordashlite.restaurant_list;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doordashlite.restaurant_list.full_state.RestaurantListFullStateView;

public class RestaurantListView extends FrameLayout implements RestaurantListInteractor.RestaurantListPresenter {
    public RestaurantListView(@NonNull Context context) {
        super(context);
    }

    public RestaurantListView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RestaurantListView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void showFullState(RestaurantListFullStateView fullStateView) {
        removeAllViews();
        addView(fullStateView);
    }
}
