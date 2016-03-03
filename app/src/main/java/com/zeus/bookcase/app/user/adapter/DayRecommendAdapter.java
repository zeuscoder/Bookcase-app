package com.zeus.bookcase.app.user.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.home.ui.activity.BookIntroductionActivity;
import com.zeus.bookcase.app.user.ui.activity.BookRecommendDetailActivity;
import com.zeus.bookcase.app.user.view.AnimCheckBox;

/**
 * Created by zeus_coder on 2016/2/26.
 */
public class DayRecommendAdapter extends BaseAdapter {

    private Context context;

    public DayRecommendAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 5;
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
            view = LayoutInflater.from(context).inflate(R.layout.user_item_day_recommend, null);
            viewHolder = new ViewHolder();
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, BookRecommendDetailActivity.class));
            }
        });
        return view;
    }

    class ViewHolder {

    }
}
