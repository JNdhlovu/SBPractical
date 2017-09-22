package com.superbalist.sbpractical.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.superbalist.sbpractical.R;
import com.superbalist.sbpractical.entities.PhotoObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by japhetndhlovu on 2017/09/21.
 */

public class PhotosAdapter extends BaseAdapter {
    private ArrayList<PhotoObject> mPhotosObjects = new ArrayList<>();
    private Context mContext;

    public PhotosAdapter(ArrayList<PhotoObject> photosObjects, Context context) {
        mPhotosObjects = photosObjects;
        mContext = context;
    }

    public void addItems(List<PhotoObject> photosObjects) {
        mPhotosObjects.addAll(photosObjects);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mPhotosObjects.size();
    }

    @Override
    public Object getItem(int position) {
        return mPhotosObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.photo_item, null);
        }

        ImageView imageView = convertView.findViewById(R.id.image);
        TextView imageName = convertView.findViewById(R.id.imageName);

        Picasso.with(mContext).load(mPhotosObjects.get(position).getImageUrl())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(imageView);
        imageName.setText(mPhotosObjects.get(position).getName());
        return convertView;
    }
}
