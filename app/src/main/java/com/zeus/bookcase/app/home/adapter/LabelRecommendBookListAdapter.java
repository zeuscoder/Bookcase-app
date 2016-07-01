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

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.home.model.BookList;
import com.zeus.bookcase.app.home.model.book.Book;
import com.zeus.bookcase.app.home.ui.activity.BookIntroductionActivity;
import com.zeus.bookcase.app.home.ui.activity.BookPurchaseDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeus_coder on 2016/2/6.
 */
public class LabelRecommendBookListAdapter extends BaseAdapter {

    private List<BookList> bookLists;
    private Context context;
    private int id;

    private ImageLoader imageLoader = ImageLoader.getInstance();

    private BookList bookList;
    private List<String> ids;

    public LabelRecommendBookListAdapter(Context context, List<BookList> bookLists) {
        this.bookLists = bookLists;
        this.context = context;
        initIds(bookLists);
    }

    private void initIds(List<BookList> bookLists) {
        ids = new ArrayList<>();
        for(BookList bookList: bookLists) {
            ids.add(bookList.getIds().get(0));
            ids.add(bookList.getIds().get(1));
            ids.add(bookList.getIds().get(2));
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return bookLists.get(position);
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
        //这里需要传入id
        ImageLoader.getInstance().displayImage(bookList.getUrl(), viewHolder.banner);
        viewHolder.title.setText(bookList.getTitle());
        viewHolder.number.setText(bookList.getNumber() + "人看过");
        //显示图片的配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        imageLoader.displayImage(bookList.getUrls().get(0), viewHolder.item1, options);
        imageLoader.displayImage(bookList.getUrls().get(1), viewHolder.item2, options);
        imageLoader.displayImage(bookList.getUrls().get(2), viewHolder.item3, options);

        viewHolder.item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,BookIntroductionActivity.class);
                intent.putExtra("bookId", ids.get(position * 3  + 0));
                context.startActivity(intent);
            }
        });
        viewHolder.item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,BookIntroductionActivity.class);
                intent.putExtra("bookId", ids.get(position * 3 + 1));
                context.startActivity(intent);
            }
        });
        viewHolder.item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,BookIntroductionActivity.class);
                intent.putExtra("bookId", ids.get(position * 3  + 2));
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
