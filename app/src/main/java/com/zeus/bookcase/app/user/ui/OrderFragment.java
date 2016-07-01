package com.zeus.bookcase.app.user.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.home.model.Order;
import com.zeus.bookcase.app.home.model.book.Book;
import com.zeus.bookcase.app.user.adapter.OrderAdapter;
import com.zeus.bookcase.app.user.model.User;
import com.zeus.bookcase.app.user.view.ErrorView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by zeus_coder on 2016/4/6.
 */
public class OrderFragment extends Fragment {

    private ListView orderList;
    private User user;
    private int state;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user__fragment_order, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initErrorView(view);
        initViews(view);
        initData();
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            state = bundle.getInt("state");
        }
        if (state == 0) {
            setAllOrder();
        } else if (state == 1) {
            setWillOrder();
        } else if (state == 2) {
            setOverOrder();
        }
    }

    private void setWillOrder() {
        BmobQuery<Order> query = new BmobQuery<Order>();
        query.addWhereEqualTo("uid", user.getObjectId().toString());
        query.addWhereEqualTo("state", "待发货");
        query.findObjects(getActivity(), new FindListener<Order>() {
            @Override
            public void onSuccess(List<Order> list) {
                orderList.setAdapter(new OrderAdapter(getActivity(), list));
            }

            @Override
            public void onError(int i, String s) {
                Toast.makeText(getActivity(), "查询订单失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setOverOrder() {
        BmobQuery<Order> query = new BmobQuery<Order>();
        query.addWhereEqualTo("uid", user.getObjectId().toString());
        query.addWhereEqualTo("state", "已发货");
        query.findObjects(getActivity(), new FindListener<Order>() {
            @Override
            public void onSuccess(List<Order> list) {
                orderList.setAdapter(new OrderAdapter(getActivity(), list));
            }

            @Override
            public void onError(int i, String s) {
                Toast.makeText(getActivity(), "查询订单失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAllOrder() {
        BmobQuery<Order> query = new BmobQuery<Order>();
        query.addWhereEqualTo("uid", user.getObjectId().toString());
        query.findObjects(getActivity(), new FindListener<Order>() {
            @Override
            public void onSuccess(List<Order> list) {
                orderList.setAdapter(new OrderAdapter(getActivity(), list));
            }

            @Override
            public void onError(int i, String s) {
                Toast.makeText(getActivity(), "查询订单失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews(View view) {
        orderList = (ListView) view.findViewById(R.id.user_all_order);
        user = BmobUser.getCurrentUser(getActivity(), User.class);
    }

    private void initErrorView(View view) {
        final ErrorView mErrorView = (ErrorView) view.findViewById(R.id.error_view);
        mErrorView.setOnRetryListener(new ErrorView.RetryListener() {
            @Override
            public void onRetry() {
                Toast.makeText(getActivity(), R.string.info_retrying, Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mErrorView.setConfig(ErrorView.Config.create()
                                .title(getString(R.string.error_title_damn))
                                .titleColor(getResources().getColor(R.color.apptheme_primary))
                                .subtitle(getString(R.string.error_subtitle_failed_one_more_time))
                                .retryText(getString(R.string.error_view_retry))
                                .build());
                    }
                }, 5000);
            }
        });
    }
}
