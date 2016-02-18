package com.zeus.bookcase.app.user.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.user.view.AnimCheckBox;


/**
 * Created by zeus_coder on 2016/1/10.
 */
public class ShoppingCartAdapter extends BaseAdapter implements AnimCheckBox.OnCheckedChangeListener{

    private Context context;

    public ShoppingCartAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if(convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.user__item_shopping_cart, null);
            viewHolder = new ViewHolder();
            viewHolder.goodShopIcon = (ImageView) view.findViewById(R.id.good_shop_icon);
            viewHolder.goodShopName = (TextView) view.findViewById(R.id.good_shop_name);
            viewHolder.goodBookImage = (ImageView) view.findViewById(R.id.good_book_image);
            viewHolder.goodBookName = (TextView) view.findViewById(R.id.good_book_name);
            viewHolder.goodBookDerc = (TextView) view.findViewById(R.id.good_book_desc);
            viewHolder.goodNewPrice = (TextView) view.findViewById(R.id.good_book_new_price);
            viewHolder.goodOldPrice = (TextView) view.findViewById(R.id.good_book_old_price);
            viewHolder.goodBookCount = (TextView) view.findViewById(R.id.good_book_count);
            viewHolder.checkBox1 = (AnimCheckBox) view.findViewById(R.id.user_shopping_cart_check1);
            viewHolder.checkBox2 = (AnimCheckBox) view.findViewById(R.id.user_shopping_cart_check2);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.checkBox1.setChecked(false, false);
        viewHolder.checkBox2.setChecked(false, false);
        viewHolder.checkBox1.setOnCheckedChangeListener(this);
        viewHolder.checkBox2.setOnCheckedChangeListener(this);
        return view;
    }

    @Override
    public void onChange(boolean checked) {

    }

    class ViewHolder {
        private ImageView goodShopIcon;
        private TextView goodShopName;
        private ImageView goodBookImage;
        private TextView goodBookName;
        private TextView goodBookDerc;
        private TextView goodNewPrice;
        private TextView goodOldPrice;
        private TextView goodBookCount;
        private AnimCheckBox checkBox1;
        private AnimCheckBox checkBox2;
    }
}
