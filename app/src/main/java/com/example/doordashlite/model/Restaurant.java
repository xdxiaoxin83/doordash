package com.example.doordashlite.model;

import com.google.gson.annotations.SerializedName;

public class Restaurant {

    @SerializedName("id")
    String id;

    @SerializedName("name")
    private
    String name;

    @SerializedName("description")
    private
    String description;

    @SerializedName("cover_img_url")
    private
    String imageUrl;

    @SerializedName("status")
    private
    String status;

    public Restaurant(
            String id,
            String name,
            String description,
            String imageUrl,
            String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getStatus() {
        return status;
    }

}
