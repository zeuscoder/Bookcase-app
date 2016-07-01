package com.zeus.bookcase.app.cabinet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.baoyz.swipemenulistview.BaseSwipListAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.cabinet.model.MyBook;

import java.util.List;

/**
 * Created by zeus_coder on 2016/4/1.
 */
public class MySelfBookAdapter extends BaseSwipListAdapter {

    private Context context;
    private List<MyBook> books;

    public MySelfBookAdapter(Context context, List<MyBook> books) {
        this.context = context;
        this.books = books;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int i) {
        return books.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyBook book = books.get(i);
        ViewHolder viewHolder;
        if (view != null) {
            viewHolder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.case__item_myself_book, null);
            viewHolder = new ViewHolder();
            viewHolder.image = (ImageView) view.findViewById(R.id.case_book_image);
            viewHolder.title = (TextView) view.findViewById(R.id.case_book_title);
            viewHolder.author = (TextView) view.findViewById(R.id.case_book_author_pubdate);
            viewHolder.summary = (TextView) view.findViewById(R.id.case_book_summary);
            viewHolder.state = (TextView) view.findViewById(R.id.case_book_state);
            viewHolder.choose = (ImageButton) view.findViewById(R.id.case_book_choose );
            view.setTag(viewHolder);
        }
        ImageLoader.getInstance().displayImage(book.getImage(), viewHolder.image);
        viewHolder.title.setText(book.getTitle());
        viewHolder.author.setText(book.getPublisher() + " " + book.getPubdate());
        viewHolder.summary.setText(book.getSummary());
        viewHolder.state.setText(book.getState());
        viewHolder.choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }

    class ViewHolder {
        private ImageView image;
        private TextView title;
        private TextView author;
        private TextView summary;
        private TextView state;
        private ImageButton choose;
    }
}
