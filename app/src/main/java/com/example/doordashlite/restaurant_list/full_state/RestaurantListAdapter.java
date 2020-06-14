package com.example.doordashlite.restaurant_list.full_state;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doordashlite.R;
import com.example.doordashlite.model.Restaurant;
import com.example.doordashlite.restaurant_list.full_state.single.RestaurantListSingleEntryView;

import java.util.ArrayList;
import java.util.List;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder> {

    private List<Restaurant> restaurants = new ArrayList<>();
    private final Listener listener;

    public RestaurantListAdapter(Listener listener) {
        this.listener = listener;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        if (restaurants != null) {
            this.restaurants.clear();
            this.restaurants.addAll(restaurants);
            notifyDataSetChanged();
        }
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RestaurantListSingleEntryView view = (RestaurantListSingleEntryView) LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurants_list_single_entry_view, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        holder.bind(restaurants.get(position));
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder {

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(Restaurant restaurant) {
            ((RestaurantListSingleEntryView) itemView).setRestaurant(restaurant);
            itemView.setOnClickListener(v -> RestaurantListAdapter.this.listener.onItemClicked(restaurant));
        }
    }

    public interface Listener {
        Intent onItemClicked(Restaurant restaurant);
    }
}
