package com.zeus.bookcase.app.home.adapter;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.home.model.book.Book;
import com.zeus.common.util.DynamicHeightTextView;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zeus_coder on 2015/12/1.
 */
public class BookDiscountAdapter extends BaseAdapter {

    private static final String TAG = "SampleAdapter";

    static class ViewHolder {
//        DynamicHeightTextView txtLineOne;
//        Button btnGo;
        private ImageView image;
        private TextView summary;
        private TextView price;
    }

    private final LayoutInflater mLayoutInflater;
    private List<Book> books;
//    private final Random mRandom;
//    private final ArrayList<Integer> mBackgroundColors;

    private static final SparseArray<Double> sPositionHeightRatios = new SparseArray<Double>();

    public BookDiscountAdapter(Context context, List<Book> books) {
        mLayoutInflater = LayoutInflater.from(context);
        this.books = books;
//        mRandom = new Random();
//        mBackgroundColors = new ArrayList<Integer>();
//        mBackgroundColors.add(R.color.orange);
//        mBackgroundColors.add(R.color.green);
//        mBackgroundColors.add(R.color.blue);
//        mBackgroundColors.add(R.color.yellow);
//        mBackgroundColors.add(R.color.grey);
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder;
        Book book = books.get(position);
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.home__item_activity_book_discount, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.image = (ImageView) convertView.findViewById(R.id.home_ca_book_image);
            viewHolder.summary = (TextView) convertView.findViewById(R.id.home_ca_book_summary);
            viewHolder.price = (TextView) convertView.findViewById(R.id.home_ca_book_price);
//            viewHolder.txtLineOne = (DynamicHeightTextView) convertView.findViewById(R.id.txt_line1);
//            viewHolder.btnGo = (Button) convertView.findViewById(R.id.btn_go);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(book.getImage(), viewHolder.image);
        viewHolder.summary.setText(book.getSummary());
        viewHolder.price.setText("￥" + book.getPrice());
//        double positionHeight = getPositionRatio(position);
//        int backgroundIndex = position >= mBackgroundColors.size() ?
//                position % mBackgroundColors.size() : position;
//
//        convertView.setBackgroundResource(mBackgroundColors.get(backgroundIndex));

//        Log.d(TAG, "getView position:" + position + " h:" + positionHeight);

//        viewHolder.txtLineOne.setHeightRatio(positionHeight);
//        viewHolder.txtLineOne.setText(getItem(position) + position);

//        viewHolder.btnGo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View v) {
//                Toast.makeText(getContext(), "Button Clicked Position " +
//                        position, Toast.LENGTH_SHORT).show();
//            }
//        });
        return convertView;
    }
//
//    private double getPositionRatio(final int position) {
//        double ratio = sPositionHeightRatios.get(position, 0.0);
//        // if not yet done generate and stash the columns height
//        // in our real world scenario this will be determined by
//        // some match based on the known height and width of the image
//        // and maybe a helpful way to get the column height!
//        if (ratio == 0) {
//            ratio = getRandomHeightRatio();
//            sPositionHeightRatios.append(position, ratio);
//            Log.d(TAG, "getPositionRatio:" + position + " ratio:" + ratio);
//        }
//        return ratio;
//    }

//    private double getRandomHeightRatio() {
//        return (mRandom.nextDouble() / 2.0) + 1.0; // height will be 1.0 - 1.5 the width
//    }
}
