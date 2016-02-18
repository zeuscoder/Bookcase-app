package com.zeus.common;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.zeus.lib_common.R;

/**
 * Created by zeus_coder on 2015/11/16.
 */
public class NonGridViewAdapter extends BaseAdapter {

    private int[] colors = new int[] { Color.rgb(189, 202, 188),
            Color.rgb(222, 203, 161), Color.rgb(244, 107, 65) };
    private int[] images;
    private Context context;

    public NonGridViewAdapter(Context context, int[] images) {
        this.images = images;
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        NonGridViewHolder nonGridViewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.common_grid_view_item,null);
            nonGridViewHolder = new NonGridViewHolder();
            nonGridViewHolder.image = (ImageView) view.findViewById(R.id.name);
            view.setTag(nonGridViewHolder);
        } else {
            view = convertView;
            nonGridViewHolder = (NonGridViewHolder) view.getTag();
        }
        nonGridViewHolder.image.setBackgroundResource(images[position]);
        return view;
    }

    private class NonGridViewHolder {
        private ImageView image;

    }
}
