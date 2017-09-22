package com.superbalist.sbpractical.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by japhetndhlovu on 2017/09/21.
 */

public class PhotosResponse {

    @SerializedName("current_page")
    private int currentPage;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("total_items")
    private int totalItems;

    private List<PhotoObject> photos;
    private Filter filters;
    private String feature;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public List<PhotoObject> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoObject> photos) {
        this.photos = photos;
    }

    public Filter getFilters() {
        return filters;
    }

    public void setFilters(Filter filters) {
        this.filters = filters;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }
}
