package com.example.doordashlite.restaurant_detail;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.doordashlite.R;
import com.example.doordashlite.model.RestaurantDetail;

public class RestaurantDetailView extends ScrollView implements RestaurantDetailInteractor.RestaurantDetailPresenter {

    private ImageView  imageView;
    private TextView nameView;
    private TextView addressView;
    private TextView phoneView;

    public RestaurantDetailView(Context context) {
        super(context);
    }

    public RestaurantDetailView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RestaurantDetailView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        imageView = findViewById(R.id.restaurant_detail_image);
        nameView = findViewById(R.id.restaurant_detail_name);
        addressView = findViewById(R.id.restaurant_detail_address);
        phoneView = findViewById(R.id.restaurant_detail_phone);
    }

    @Override
    public void update(RestaurantDetail detail, Context context) {
        nameView.setText(detail.getName());
        if (detail.getAddress() != null) {
            addressView.setText(detail.getAddress().getPrintableAddress());
        }
        phoneView.setText(detail.getPhoneNumber());
        Glide.with(context)
                .load(detail.getImageUrl())
                .centerInside()
                .into(imageView);
    }

}
