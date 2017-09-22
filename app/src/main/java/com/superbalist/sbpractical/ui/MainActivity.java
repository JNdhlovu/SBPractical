package com.superbalist.sbpractical.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.superbalist.sbpractical.R;
import com.superbalist.sbpractical.entities.PhotoObject;
import com.superbalist.sbpractical.entities.PhotosResponse;
import com.superbalist.sbpractical.network.PhotosService;
import com.superbalist.sbpractical.ui.adapters.PhotosAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Retrofit mRetrofit;
    private GridView mGridView;
    private PhotosAdapter mPhotosAdapter;
    private ProgressBar loadingIndicator;
    private final String AUTH_KEY = "cC7jmWx5aFWTNRNufAHOFJaI2PU6QrLTFnCbETsa";
    private final String DEF_PAGE_SIZE = "15";
    private int mCurrentLoadedPage = 0;
    private int mTotalPages = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.500px.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mPhotosAdapter = new PhotosAdapter(new ArrayList<PhotoObject>(), this);

        mGridView = (GridView) findViewById(R.id.gridView);
        loadingIndicator = (ProgressBar) findViewById(R.id.loadingTextIndicator);

        mGridView.setAdapter(mPhotosAdapter);
        mGridView.setOnScrollListener(new ContinousScrollListener() {
            @Override
            public boolean onLoadMore() {
                fetchPhotos();
                loadingIndicator.setVisibility(View.VISIBLE);
                return true;
            }
        });

        fetchPhotos();
    }

    private void fetchPhotos() {
        if ((mCurrentLoadedPage != 0) && (mCurrentLoadedPage + 1 > mTotalPages)) {
            Toast.makeText(MainActivity.this, R.string.end_of_list, Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, String> data = new HashMap<>();
        data.put("consumer_key", AUTH_KEY);
        data.put("rpp", DEF_PAGE_SIZE);
        data.put("feature", "popular");
        data.put("page", String.valueOf(mCurrentLoadedPage + 1));
        data.put("sort", "created_at");
        data.put("sort_direction", "desc");
        data.put("image_size", "200");


        PhotosService photosService = mRetrofit.create(PhotosService.class);
        Call<PhotosResponse> call = photosService.getPhotos(data);
        call.enqueue(new Callback<PhotosResponse>() {
            @Override
            public void onResponse(Call<PhotosResponse> call, Response<PhotosResponse> response) {
                mCurrentLoadedPage = response.body().getCurrentPage();
                mTotalPages = response.body().getTotalPages();
                mPhotosAdapter.addItems(response.body().getPhotos());
                loadingIndicator.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<PhotosResponse> call, Throwable t) {
                loadingIndicator.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, R.string.error_text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
