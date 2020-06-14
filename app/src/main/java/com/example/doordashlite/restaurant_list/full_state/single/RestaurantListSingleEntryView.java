package com.example.doordashlite.restaurant_list.full_state.single;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.doordashlite.R;
import com.example.doordashlite.model.Restaurant;

public class RestaurantListSingleEntryView extends LinearLayout {

    private ImageView imageView;
    private TextView titleView;
    private TextView subtitleView;
    private TextView statusView;

    public RestaurantListSingleEntryView(Context context) {
        super(context);
    }

    public RestaurantListSingleEntryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RestaurantListSingleEntryView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        imageView = findViewById(R.id.restaurant_list_single_image);
        titleView = findViewById(R.id.restaurant_list_single_title);
        subtitleView = findViewById(R.id.restaurant_list_single_subtitle);
        statusView = findViewById(R.id.restaurant_list_single_status);
    }

    public void setRestaurant(Restaurant restaurant) {
        titleView.setText(restaurant.getName());
        subtitleView.setText(restaurant.getDescription());
        statusView.setText(restaurant.getStatus());
        Glide.with(getContext())
                .load(restaurant.getImageUrl())
                .centerInside()
                .into(imageView);
    }
}
