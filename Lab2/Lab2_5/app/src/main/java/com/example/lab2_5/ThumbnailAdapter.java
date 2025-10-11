package com.example.lab2_5;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import java.util.List;

public class ThumbnailAdapter extends ArrayAdapter<Thumbnail> {
    private Activity context;
    private List<Thumbnail> thumbnails;

    public ThumbnailAdapter(Activity context, List<Thumbnail> thumbnails) {
        super(context, R.layout.item_thumbnail, thumbnails);
        this.context = context;
        this.thumbnails = thumbnails;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent, R.layout.item_selected_thumbnail);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent, R.layout.item_thumbnail);
    }

    private View initView(int position, View convertView, ViewGroup parent, int layoutId) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        }

        ImageView imageView = convertView.findViewById(
                layoutId == R.layout.item_thumbnail ? R.id.item_thumbnail_iv : R.id.item_selected_iv);
        TextView textView = convertView.findViewById(
                layoutId == R.layout.item_thumbnail ? R.id.item_thumbnail_tv : R.id.item_selected_tv);

        Thumbnail item = thumbnails.get(position);
        imageView.setImageResource(item.getImageRes());
        textView.setText(item.getName());

        return convertView;
    }
}

