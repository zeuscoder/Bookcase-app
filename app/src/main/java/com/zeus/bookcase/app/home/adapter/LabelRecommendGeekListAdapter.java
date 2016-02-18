package com.zeus.bookcase.app.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.home.model.Geek;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zeus_coder on 2016/2/6.
 */
public class LabelRecommendGeekListAdapter extends BaseAdapter {

    private List<Geek> geeks;
    private Context context;

    public LabelRecommendGeekListAdapter(Context context, List geeks) {
        this.context = context;
        this.geeks = geeks;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if(convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.home__item_geek, null);
            viewHolder = new ViewHolder();
            viewHolder.photo = (CircleImageView) view.findViewById(R.id.home_geek_photo);
            viewHolder.name = (TextView) view.findViewById(R.id.home_geek_name);
            viewHolder.title = (TextView) view.findViewById(R.id.home_geek_title);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        Geek geek = geeks.get(position);
        //viewHolder.photo.setBackgroundResource(geek.getPhoto());
        viewHolder.photo.setImageResource(geek.getPhoto());
        viewHolder.name.setText(geek.getName());
        viewHolder.title.setText(geek.getTitle());
        return view;
    }

    class ViewHolder {
        private CircleImageView photo;
        private TextView name;
        private TextView title;
    }
}
