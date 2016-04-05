package com.zeus.bookcase.app.home.model.dao;

import android.content.Context;
import android.widget.Toast;

import com.zeus.bookcase.app.home.model.Order;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by zeus_coder on 2016/4/3.
 */
public class OrderDao {

    private final String  UID = "uid";
    private final String  BID = "bid";
    private final String  PRICE = "price";
    private final String  COUNT = "count";
    private final String  PAY = "pay";
    private final String  MESSAGE = "message";
    private final String  STATE = "state";

    private Context context;
    private boolean flag;


    public OrderDao(Context context) {
        this.context = context;
    }

    public boolean insertOrder(Order order) {
        order.save(context, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(context, "已提交订单", Toast.LENGTH_SHORT).show();
                flag = true;
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(context, s.toString(), Toast.LENGTH_SHORT).show();
                flag = false;
            }
        });
        return flag;
    }

    public boolean deleteOrder() {
        return true;
    }

    public boolean queryOrder() {
        return true;
    }

}
