package com.zeus.bookcase.app.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import com.zeus.bookcase.app.R;

import net.tsz.afinal.FinalBitmap;

import java.util.List;

/**
 * Created by zeus_coder on 2015/10/25.
 */
public class BookListViewAdapter extends BaseAdapter {

    private List<String> list;
    private Context context;
    private FinalBitmap fb;

    public BookListViewAdapter(Context context, List<String> list) {
        this.context = context;
        fb = FinalBitmap.create(context);
        this.list = list;
    }

    @Override
    public int getCount() {
        //每列两项
       if(list.size() % 3 == 0) {
           return list.size() / 3;
       }
        return list.size() / 3 + 1;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder viewHolder = null;
        if(convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.home__item_new_book, null);
            viewHolder.book1 = (ImageView) convertView.findViewById(R.id.imageView1);
            viewHolder.book2 = (ImageView) convertView.findViewById(R.id.imageView2);
            viewHolder.book3 = (ImageView) convertView.findViewById(R.id.imageView3);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        int position1 = position * 3;
        int position2 = position * 3 + 1;
        int position3 = position * 3 + 2;

        String pic1 = list.get(position1);
        fb.display(viewHolder.book1,pic1);
        if (position2 < list.size()) {
            String pic2 = list.get(position2);
            fb.display(viewHolder.book2, pic2);
        } else {
            viewHolder.book2.setVisibility(View.GONE);
        }
        if (position3 < list.size()) {
            String pic3 = list.get(position3);
            fb.display(viewHolder.book3, pic3);
        } else {
            viewHolder.book3.setVisibility(View.GONE);
        }
        return convertView;
    }

    class ViewHolder {
        private ImageView book1, book2, book3;
    }
}
