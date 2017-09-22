package com.superbalist.sbpractical.network;

import com.superbalist.sbpractical.entities.PhotosResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by japhetndhlovu on 2017/09/21.
 */

public interface PhotosService {
    @GET("photos/")
    Call<PhotosResponse> getPhotos(@QueryMap Map<String, String> options);
}
