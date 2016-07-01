package com.zeus.bookcase.app.user.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.home.model.Cart;
import com.zeus.bookcase.app.user.view.AnimCheckBox;

import java.util.List;


/**
 * Created by zeus_coder on 2016/1/10.
 */
public class ShoppingCartAdapter extends BaseAdapter implements AnimCheckBox.OnCheckedChangeListener{

    private Context context;
    private List<Cart> carts;
    private int count = 1;


    public ShoppingCartAdapter(Context context, List<Cart> carts) {
        this.context = context;
        this.carts = carts;
    }

    @Override
    public int getCount() {
        return carts.size();
    }

    @Override
    public Object getItem(int position) {
        return carts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        final ViewHolder viewHolder;
        Cart cart = carts.get(position);
        if(convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.user__item_shopping_cart, null);
            viewHolder = new ViewHolder();
//            viewHolder.goodShopIcon = (ImageView) view.findViewById(R.id.good_shop_icon);
//            viewHolder.goodShopName = (TextView) view.findViewById(R.id.good_shop_name);
            viewHolder.goodBookImage = (ImageView) view.findViewById(R.id.good_book_image);
            viewHolder.goodBookName = (TextView) view.findViewById(R.id.good_book_name);
            viewHolder.goodBookDerc = (TextView) view.findViewById(R.id.good_book_desc);
            viewHolder.goodNewPrice = (TextView) view.findViewById(R.id.good_book_new_price);
            viewHolder.goodOldPrice = (TextView) view.findViewById(R.id.good_book_old_price);
            viewHolder.bookNumber = (TextView) view.findViewById(R.id.order_book_count);
            viewHolder.minus = (ImageView) view.findViewById(R.id.order_book_count_minus);
            viewHolder.plus = (ImageView) view.findViewById(R.id.order_book_count_plus);
//            viewHolder.checkBox1 = (AnimCheckBox) view.findViewById(R.id.user_shopping_cart_check1);
            viewHolder.checkBox2 = (AnimCheckBox) view.findViewById(R.id.user_shopping_cart_check2);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        ImageLoader.getInstance().displayImage(cart.getImage(), viewHolder.goodBookImage);
        viewHolder.goodBookName.setText("包邮 " + cart.getTitle());
        viewHolder.goodBookDerc.setText(cart.getPublisher());
        viewHolder.goodNewPrice.setText(cart.getPrice());
        viewHolder.goodOldPrice.setText(String.valueOf(15));
        viewHolder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count > 1) {
                    count -= 1;
                    viewHolder.bookNumber.setText(String.valueOf(count));
                }
            }
        });
        viewHolder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count < 10) {
                    count += 1;
                    viewHolder.bookNumber.setText(String.valueOf(count));
                }
            }
        });
        //viewHolder.goodBookCount.setText();
        viewHolder.checkBox2.setChecked(false, false);
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
        private ImageView minus;
        private ImageView plus;
        private TextView bookNumber;
    }
}
