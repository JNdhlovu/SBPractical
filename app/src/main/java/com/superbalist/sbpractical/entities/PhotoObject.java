package com.superbalist.sbpractical.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by japhetndhlovu on 2017/09/21.
 */

public class PhotoObject {
    private String name;

    @SerializedName("image_url")
    private String imageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
