package com.zeus.bookcase.app.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.home.model.Comment;
import com.zeus.bookcase.app.home.model.annotation.Annotations;
import com.zeus.common.widget.LikeView;

import java.util.List;

/**
 * Created by zeus_coder on 2016/2/21.
 */
public class BookIntroductionCommentAdapter extends BaseAdapter {

    private List<Annotations> data;
    private Context context;

    public BookIntroductionCommentAdapter(List<Annotations> data, Context context) {
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
        if (null == view) {
            view = LayoutInflater.from(context).inflate(R.layout.home__item_book_article_comment, null);
            viewHolder = new ViewHolder();
            viewHolder.image = (ImageView) view.findViewById(R.id.comment_user_image);
            viewHolder.author = (TextView) view.findViewById(R.id.comment_user_name);
            viewHolder.abstracts = (TextView) view.findViewById(R.id.comment_user_abstract);
            viewHolder.time = (TextView) view.findViewById(R.id.comment_time);
            viewHolder.like = (LikeView) view.findViewById(R.id.comment_like);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        ImageLoader.getInstance().displayImage(annotations.getAuthor_user().getAvatar(), viewHolder.image);
        viewHolder.author.setText(annotations.getAuthor_user().getName());
        viewHolder.abstracts.setText(annotations.getAbstracts());
        viewHolder.time.setText(annotations.getTime());
        viewHolder.like.setText(String.valueOf(0));
        return view;
    }

    class ViewHolder {
        private ImageView image;
        private TextView author;
        private TextView abstracts;
        private TextView time;
        private LikeView like;
    }
}
