package com.zeus.bookcase.app.user.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.home.ui.activity.BookIntroductionActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeus_coder on 2016/2/26.
 */
public class MyFortuneAdapter extends RecyclingPagerAdapter {

    private List<Integer> mList;
    private Context context;

    public MyFortuneAdapter(Context context) {
        this.context = context;
        addAll();
    }

    public void addAll() {
        mList = new ArrayList<>();
        //mList.add(R.layout.user__layout_my_fortune_credit);
        mList.add(R.layout.user__layout_my_fortune_discount);
        mList.add(R.layout.user__layout_my_fortune_points);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(mList.get(position), null);
            viewHolder = new ViewHolder();
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    class ViewHolder {

    }
}
