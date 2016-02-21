package com.zeus.bookcase.app.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.zeus.bookcase.app.R;

import java.util.List;

/**
 * Created by zeus_coder on 2016/2/21.
 */
public class BookIntroductionCommentAdapter extends BaseAdapter {

    private List<String> data;
    private Context context;

    public BookIntroductionCommentAdapter(List<String> data, Context context) {
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
            view = LayoutInflater.from(context).inflate(R.layout.home__item_book_article_comment, null);
        }
        return view;
    }

    class ViewHolder {

    }
}
