package com.zeus.bookcase.app.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.home.ui.activity.BookPurchaseDetailActivity;

import java.util.List;

/**
 * Created by zeus_coder on 2016/2/6.
 */
public class LabelRecommendBookListAdapter extends BaseAdapter {

    private List books;
    private Context context;

    public LabelRecommendBookListAdapter(Context context) {
        //this.books = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if(convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.home__item_label_recommend_book, null);
            viewHolder = new ViewHolder();
            viewHolder.banner = (LinearLayout) view.findViewById(R.id.layout_recommend_banner);
            viewHolder.title = (TextView) view.findViewById(R.id.tv_label_recommend_list_title);
            viewHolder.number = (TextView) view.findViewById(R.id.tv_label_recommend_list_number);
            viewHolder.item1 = (ImageView) view.findViewById(R.id.label_recommend_book_grid_item1);
            viewHolder.item2 = (ImageView) view.findViewById(R.id.label_recommend_book_grid_item2);
            viewHolder.item3 = (ImageView) view.findViewById(R.id.label_recommend_book_grid_item3);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.banner.setBackgroundResource(R.mipmap.app_label_recommend_book_banner_bg);
        viewHolder.title.setText("IT宅男的周末");
        viewHolder.number.setText("100");
        viewHolder.item1.setBackgroundResource(R.mipmap.app_label_recommend_book_item1);
        viewHolder.item2.setBackgroundResource(R.mipmap.app_label_recommend_book_item2);
        viewHolder.item3.setBackgroundResource(R.mipmap.app_label_recommend_book_item3);
        viewHolder.item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, BookPurchaseDetailActivity.class));
            }
        });
        return view;
    }

    class ViewHolder {
        private LinearLayout banner;
        private TextView title;
        private TextView number;
        private ImageView item1;
        private ImageView item2;
        private ImageView item3;
    }
}
