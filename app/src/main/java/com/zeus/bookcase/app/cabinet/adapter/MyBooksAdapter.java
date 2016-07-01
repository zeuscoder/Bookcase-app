package com.zeus.bookcase.app.cabinet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.cabinet.model.Model;
import com.zeus.bookcase.app.cabinet.view.RoundedTransform;
import com.zeus.bookcase.app.cabinet.view.SlidingDeck;

/**
 * Created by zeus_coder on 2016/3/16.
 */
public class MyBooksAdapter extends ArrayAdapter<Model> {

    public MyBooksAdapter(Context context) {
        super(context, R.layout.case__item_my_book);
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.case__item_my_book, parent, false);
        }
        Model item = getItem(position);
        view.setTag(item);
        ((TextView)view.findViewById(R.id.description)).setText(item.getDescription());
        ((TextView)view.findViewById(R.id.name)).setText(item.getName());
        ImageView avatar = (ImageView)view.findViewById(R.id.case_choose_my_book_image);
        Picasso.with(parent.getContext())
                .load(item.getAvatarUri())
                .placeholder(R.mipmap.user__normal_photo)
                .transform(new RoundedTransform())
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
                        final Model slidingDeckModel = (Model)item.getTag();
                        remove(slidingDeckModel);
                        notifyDataSetChanged();
                    }
                });
            }
        });
        return view;
    }
}
