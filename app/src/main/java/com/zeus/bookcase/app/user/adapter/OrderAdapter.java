package com.zeus.bookcase.app.user.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.home.api.BaseAsyncHttp;
import com.zeus.bookcase.app.home.api.HttpResponseHandler;
import com.zeus.bookcase.app.home.model.Order;
import com.zeus.bookcase.app.home.model.book.Book;
import com.zeus.bookcase.app.user.model.Address;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by zeus_coder on 2016/4/6.
 */
public class OrderAdapter extends BaseAdapter {

    private Context context;
    private List<Order> orders;

    private Book book;

    public OrderAdapter(Context context, List<Order> orders) {
        this.context = context;
        this.orders = orders;
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Object getItem(int i) {
        return orders.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Order order = orders.get(i);
        ViewHolder viewHolder;
        if (view != null) {
            viewHolder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.user__item_order, null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) view.findViewById(R.id.order_book_title);
            viewHolder.price = (TextView) view.findViewById(R.id.order_book_price);
            viewHolder.number = (TextView) view.findViewById(R.id.order_book_number);
            viewHolder.address = (TextView) view.findViewById(R.id.order_book_address);
            viewHolder.name = (TextView) view.findViewById(R.id.order_address_name);
            viewHolder.phone = (TextView) view.findViewById(R.id.order_address_phone);
            viewHolder.pay = (TextView) view.findViewById(R.id.order_address_pay);
            viewHolder.image = (ImageView) view.findViewById(R.id.order_book_image);
            view.setTag(viewHolder);
        }
        viewHolder.price.setText("￥" + order.getPrice());
        viewHolder.number.setText(order.getCount() + "本");
        viewHolder.pay.setText("￥" + order.getPay());
        reqBookInformation(order.getBid().toString(), viewHolder);
        return view;
    }

    class ViewHolder {
        private TextView title;
        private TextView price;
        private TextView number;
        private TextView address;
        private TextView name;
        private TextView phone;
        private TextView pay;
        private ImageView image;
    }

    private void reqBookInformation(String bookId, final OrderAdapter.ViewHolder viewHolder) {
        RequestParams params = new RequestParams();
        BaseAsyncHttp.getReq("/v2/book/" + bookId, params, new HttpResponseHandler() {
            @Override
            public void jsonSuccess(JSONObject resp) {
                Gson gson = new Gson();
                book = gson.fromJson(String.valueOf(resp), Book.class);
                String authors = "";
                for (String author : book.getAuthor()) {
                    authors = authors + " " + author;
                }
                book.setAuthors(authors);
                updateToView(book, viewHolder);
            }

            @Override
            public void jsonFail(JSONObject resp) {
                if (resp.optInt("code") == 6000) {
                    Toast.makeText(context, "没有找到该图书", Toast.LENGTH_LONG).show();
                    ((Activity)context).finish();
                }
            }
        });
    }

    private void updateToView(Book book, OrderAdapter.ViewHolder viewHolder) {
        viewHolder.title.setText(book.getTitle());
        ImageLoader.getInstance().displayImage(book.getImage(), viewHolder.image);
    }

}
