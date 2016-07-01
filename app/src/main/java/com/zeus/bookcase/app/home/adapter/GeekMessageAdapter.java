package com.zeus.bookcase.app.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.home.model.annotation.Annotations;
import com.zeus.common.widget.LikeView;

import java.util.List;
import java.util.Random;

/**
 * Created by zeus_coder on 2016/2/20.
 */
public class GeekMessageAdapter extends BaseAdapter {

    private List<Annotations> data;
    private Context context;

    public GeekMessageAdapter(List<Annotations> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return null == data ? 0 : data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        Annotations annotations = data.get(i);
        if (view != null) {
            viewHolder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.home__item_geek_message, null);
            viewHolder = new ViewHolder();
            viewHolder.image = (ImageView) view.findViewById(R.id.home_comment_book_image);
            viewHolder.abstracts = (TextView) view.findViewById(R.id.home_comment_book_abstract);
            viewHolder.like = (LikeView) view.findViewById(R.id.home_comment_book_like);
            view.setTag(viewHolder);
        }
        Random random = new Random();
        ImageLoader.getInstance().displayImage(annotations.getBook().getImage(), viewHolder.image);
        viewHolder.abstracts.setText(annotations.getAbstracts());
        viewHolder.like.setText(random.nextInt(2016) + "");
        return view;
    }

    class ViewHolder {
        private ImageView image;
        private TextView abstracts;
        private LikeView like;
    }
}
