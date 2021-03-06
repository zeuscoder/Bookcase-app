package com.zeus.bookcase.app.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.zeus.bookcase.app.R;

import java.util.List;

/**
 * Created by zeus_coder on 2016/2/25.
 */
public class GeekSummaryAdapter extends BaseAdapter {

    private List<String> data;
    private Context context;

    public GeekSummaryAdapter(Context context, List<String> data) {
        this.data = data;
        this.context = context;
    }
    @Override
    public int getCount() {
        return null == data ? 0 : data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (null == view) {
            view = LayoutInflater.from(context).inflate(R.layout.home__item_geek_summary, null);
        }
        return view;
    }

    class ViewHolder {

    }
}
