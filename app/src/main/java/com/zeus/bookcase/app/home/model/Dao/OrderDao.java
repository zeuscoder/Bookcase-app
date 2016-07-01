package com.zeus.bookcase.app.home.model.dao;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.zeus.bookcase.app.home.model.Order;
import com.zeus.bookcase.app.user.model.User;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
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

    private List<Order> orders;


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

    public List<Order> getAllOrder(User user) {
        orders = new ArrayList<Order>();
        BmobQuery<Order> query = new BmobQuery<Order>();
        query.addWhereEqualTo("uid", user.getObjectId().toString());
        query.findObjects(context, new FindListener<Order>() {
            @Override
            public void onSuccess(List<Order> list) {
                orders = list;
                Log.e("lvzimou---order", orders.toArray().toString());
            }

            @Override
            public void onError(int i, String s) {
                Toast.makeText(context, "查询订单失败", Toast.LENGTH_SHORT).show();
                Log.e("lvzimou---order", s.toString());
            }
        });
        return orders;
    }

    public boolean deleteOrder() {
        return true;
    }

    public boolean queryOrder() {
        return true;
    }

}
