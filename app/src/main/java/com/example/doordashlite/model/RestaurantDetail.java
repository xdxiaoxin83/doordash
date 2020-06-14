package com.example.doordashlite.model;

import com.google.gson.annotations.SerializedName;

public class RestaurantDetail {

    @SerializedName("name")
    private
    String name;


    @SerializedName("cover_img_url")
    private
    String imageUrl;

    @SerializedName("address")
    private
    Address address;

    @SerializedName("phone_number")
    private String phoneNumber;


    public RestaurantDetail(
            String name,
            String phoneNumber,
            String imageUrl,
            Address address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.imageUrl = imageUrl;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

}
