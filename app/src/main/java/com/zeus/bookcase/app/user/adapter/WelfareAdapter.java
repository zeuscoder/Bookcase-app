package com.zeus.bookcase.app.user.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zeus.bookcase.app.R;


/**
 * Created by zeus_coder on 2016/1/13.
 */
public class WelfareAdapter extends BaseAdapter {

    private Context context;

    public WelfareAdapter(Context context) {
        this.context = context;
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
            view = LayoutInflater.from(context).inflate(R.layout.user__item_welfare, null);
            viewHolder = new ViewHolder();
            viewHolder.welfareIcon = (ImageView) view.findViewById(R.id.welfare_icon);
            viewHolder.welfareTitle = (TextView) view.findViewById(R.id.welfare_title);
            viewHolder.welfareContent = (TextView) view.findViewById(R.id.welfare_content);
            viewHolder.welfareTask = (TextView) view.findViewById(R.id.welfare_task);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        return view;
    }

    class ViewHolder {
        private ImageView welfareIcon;
        private TextView welfareTitle;
        private TextView welfareContent;
        private TextView welfareTask;
    }
}
