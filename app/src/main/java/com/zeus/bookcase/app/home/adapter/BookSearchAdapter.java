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
import com.zeus.bookcase.app.home.model.Book;


import java.util.List;
import java.util.Random;

/**
 * Created by zeus_coder on 2016/1/23.
 */
public class BookSearchAdapter extends BaseAdapter {

    private Context context;
    private List<Book> books;

    public BookSearchAdapter(Context context, List<Book> books) {
        this.context = context;
        this.books = books;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int arg0) {
        return books.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null) {
            convertView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.home__item_search_book, null);
            holder=new ViewHolder();
            holder.title=(TextView)convertView.findViewById(R.id.app_search_book_title);
            holder.location=(TextView)convertView.findViewById(R.id.app_search_book_location);
            holder.price=(TextView)convertView.findViewById(R.id.app_search_book_price);
            holder.number=(TextView)convertView.findViewById(R.id.app_search_book_number);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        Random random = new Random();
        holder.image=(ImageView)convertView.findViewById(R.id.app_search_book_image);
        holder.title.setText(books.get(position).getTitle());
        holder.location.setText("广州");
        holder.price.setText("41.30");
        //holder.number.setText("("+books.get(position).getReviewCount()+"人评论)");
        holder.number.setText(random.nextInt(2016) + "");
        ImageLoader.getInstance().displayImage(books.get(position).getBitmap(),holder.image);
        return convertView;
    }


    public void setData(List<Book> books){
        this.books = books;
    }


    class ViewHolder{
        ImageView image;
        TextView title;
        TextView location;
        TextView price;
        TextView number;
    }
}
