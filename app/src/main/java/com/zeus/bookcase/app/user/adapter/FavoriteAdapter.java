package com.zeus.bookcase.app.user.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baoyz.swipemenulistview.BaseSwipListAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.home.model.Favorite;


import java.util.List;

/**
 * Created by zeus_coder on 2016/4/2.
 */
public class FavoriteAdapter extends BaseSwipListAdapter {

    private Context context;
    private List<Favorite> favorites;

    public FavoriteAdapter(Context context, List<Favorite> favorites) {
        this.context = context;
        this.favorites = favorites;
    }

    @Override
    public int getCount() {
        return favorites.size();
    }

    @Override
    public Object getItem(int i) {
        return favorites.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        Favorite favorite = favorites.get(i);
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.user__item_favorite, null);
            viewHolder = new ViewHolder();
            viewHolder.image = (ImageView) view.findViewById(R.id.favorite_book_image);
            viewHolder.title = (TextView) view.findViewById(R.id.favorite_book_name);
            viewHolder.author = (TextView) view.findViewById(R.id.favorite_book_author);
            viewHolder.summary = (TextView) view.findViewById(R.id.favorite_book_desc);
            viewHolder.price = (TextView) view.findViewById(R.id.favorite_book_price);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        ImageLoader.getInstance().displayImage(favorite.getImage(), viewHolder.image);
        viewHolder.title.setText(favorite.getTitle());
        viewHolder.author.setText(favorite.getAuthor());
        viewHolder.summary.setText(favorite.getSummary());
        viewHolder.price.setText(favorite.getPrice());
        return view;
    }

    class ViewHolder {
        private ImageView image;
        private TextView title;
        private TextView author;
        private TextView summary;
        private TextView price;
    }
}
