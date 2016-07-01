package com.zeus.bookcase.app.cabinet.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.cabinet.model.MyBook;
import com.zeus.bookcase.app.cabinet.view.RoundedTransform;
import com.zeus.bookcase.app.cabinet.view.SlidingDeck;

/**
 * Created by zeus_coder on 2016/4/7.
 */
public class ChooseMyBookAdapter extends ArrayAdapter<MyBook> {

    private Context context;
    private Intent intent;

    public ChooseMyBookAdapter(Context context, Intent intent) {
        super(context, R.layout.case__item_choose_my_book);
        this.context = context;
        this.intent = intent;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.case__item_choose_my_book, parent, false);
        }
        MyBook myBook = getItem(position);
        view.setTag(myBook);
        ((TextView)view.findViewById(R.id.case_choose_my_book_title)).setText(myBook.getTitle());
        ((TextView)view.findViewById(R.id.case_choose_my_book_author)).setText(myBook.getPublisher() + " " + myBook.getPubdate());
        ImageView avatar = (ImageView)view.findViewById(R.id.case_choose_my_book_image);
        Picasso.with(parent.getContext())
                .load(myBook.getImage())
                .placeholder(R.mipmap.user__normal_photo)
                .into(avatar);
        final View completeView = view.findViewById(R.id.completeCommand);
        completeView.setTag(view);
        completeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SlidingDeck slidingDeck = (SlidingDeck)parent;
                slidingDeck.swipeItem((View)view.getTag(), new SlidingDeck.SwipeEventListener() {
                    @Override
                    public void onSwipe(SlidingDeck parent, View item) {
                        finishView(item);
                    }
                });
            }
        });
        return view;
    }

    private void finishView(View item) {
        Bundle bundle = new Bundle();
        MyBook mybook = (MyBook)item.getTag();
        bundle.putSerializable("book", mybook);
        intent.putExtras(bundle);
        ((Activity)context).setResult(Activity.RESULT_OK, intent);
        ((Activity)context).finish();
    }
}
