package com.zeus.bookcase.app.home.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.home.model.BookList;
import com.zeus.bookcase.app.home.ui.activity.BookIntroductionActivity;
import com.zeus.bookcase.app.home.ui.activity.BookPurchaseDetailActivity;

import java.util.List;

/**
 * Created by zeus_coder on 2016/2/6.
 */
public class LabelRecommendBookListAdapter extends BaseAdapter {

    private List<BookList> bookLists;
    private Context context;
    private int id;

    private BookList bookList;
    private Intent intent;

    public LabelRecommendBookListAdapter(Context context, List<BookList> bookLists) {
        this.bookLists = bookLists;
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
            viewHolder.banner = (ImageView) view.findViewById(R.id.layout_recommend_banner);
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
        bookList = bookLists.get(position);
        intent = new Intent(context, BookIntroductionActivity.class);
        //这里需要传入id

        ImageLoader.getInstance().displayImage(bookList.getUrl(), viewHolder.banner);
        viewHolder.title.setText(bookList.getTitle());
        viewHolder.number.setText(bookList.getNumber() + "人看过");
//        viewHolder.item1.setBackgroundResource(R.mipmap.app_label_recommend_book_item1);
//        viewHolder.item2.setBackgroundResource(R.mipmap.app_label_recommend_book_item2);
//        viewHolder.item3.setBackgroundResource(R.mipmap.app_label_recommend_book_item3);
        ImageLoader.getInstance().displayImage(bookList.getUrls().get(0), viewHolder.item1);
        ImageLoader.getInstance().displayImage(bookList.getUrls().get(1), viewHolder.item2);
        ImageLoader.getInstance().displayImage(bookList.getUrls().get(2), viewHolder.item3);
        viewHolder.item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("bookId", bookList.getIds().get(0));
                context.startActivity(intent);
            }
        });
        viewHolder.item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("bookId", bookList.getIds().get(1));
                context.startActivity(intent);
            }
        });
        viewHolder.item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("bookId", bookList.getIds().get(2));
                context.startActivity(intent);
            }
        });
        return view;
    }

    class ViewHolder {
        private ImageView banner;
        private TextView title;
        private TextView number;
        private ImageView item1;
        private ImageView item2;
        private ImageView item3;
    }
}
