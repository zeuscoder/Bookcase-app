package com.zeus.bookcase.app.cabinet.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.cabinet.model.Transaction;
import com.zeus.bookcase.app.cabinet.model.dao.TransactionDao;
import com.zeus.bookcase.app.cabinet.view.CardSlidePanel;
import com.zeus.bookcase.app.home.ui.activity.BookDiscountActivity;
import com.zeus.bookcase.app.home.ui.activity.BookIntroductionActivity;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;


/**
 * 卡片Fragment
 *
 * @author xmuSistone
 */
@SuppressLint({"HandlerLeak", "NewApi", "InflateParams"})
public class CardFragment extends Fragment {

    private CardSlidePanel.CardSwitchListener cardSwitchListener;

//    private String imagePaths[] = {"assets://music/wall01.jpg",
//            "assets://music/wall02.jpg", "assets://music/wall03.jpg",
//            "assets://music/wall04.jpg", "assets://music/wall05.jpg",
//            "assets://music/wall06.jpg", "assets://music/wall07.jpg",
//            "assets://music/wall08.jpg", "assets://music/wall09.jpg",
//            "assets://music/wall10.jpg", "assets://music/wall11.jpg",
//            "assets://music/wall12.jpg", "assets://music/wall01.jpg",
//            "assets://music/wall02.jpg", "assets://music/wall03.jpg",
//            "assets://music/wall04.jpg", "assets://music/wall05.jpg",
//            "assets://music/wall06.jpg", "assets://music/wall07.jpg",
//            "assets://music/wall08.jpg", "assets://music/wall09.jpg",
//            "assets://music/wall10.jpg", "assets://music/wall11.jpg", "assets://music/wall12.jpg"}; // 24个图片资源名称
//
//    private String names[] = {"郭富城", "刘德华", "张学友", "李连杰", "成龙", "谢霆锋", "李易峰",
//            "霍建华", "胡歌", "曾志伟", "吴孟达", "梁朝伟", "周星驰", "赵本山", "郭德纲", "周润发", "邓超",
//            "王祖蓝", "王宝强", "黄晓明", "张卫健", "徐峥", "李亚鹏", "郑伊健"}; // 24个人名

    private List<Transaction> transactions;
    private CardSlidePanel slidePanel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.case__card_layout, null);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        slidePanel = (CardSlidePanel) rootView
                .findViewById(R.id.image_slide_panel);
        cardSwitchListener = new CardSlidePanel.CardSwitchListener() {

            @Override
            public void onShow(int index) {
                Log.d("CardFragment", "正在显示-" + transactions.get(index).getAuthor());

            }

            @Override
            public void onCardVanish(int index, int type) {
                Log.d("CardFragment", "正在消失-" + transactions.get(index).getAuthor() + " 消失type=" + type);
                if (index == CardSlidePanel.VANISH_TYPE_LEFT) {
                    Toast.makeText(getActivity(), "不感兴趣", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(getActivity(), BookDiscountActivity.class));
                }
            }
        };
        slidePanel.setCardSwitchListener(cardSwitchListener);

        prepareMockDataList();


    }

    private void prepareDataList() {
        BmobQuery<Transaction> query = new BmobQuery<Transaction>();
        query.addWhereEqualTo("isdeal", false);
        query.findObjects(getActivity(), new FindListener<Transaction>() {
            @Override
            public void onSuccess(List<Transaction> list) {
                updateView(list);
            }

            @Override
            public void onError(int i, String s) {
                Toast.makeText(getActivity(), s.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateView(List<Transaction> list) {
        slidePanel.fillData(list);
    }


    private void prepareMockDataList() {
        transactions = new ArrayList<>();
        Transaction transaction = new Transaction();
        transaction.setBid("886f9a31e7");
        transaction.setBid("26644935");
        transaction.setCondition("suibian");
        transaction.setIsdeal(false);
        transaction.setHasdeal(false);
        transaction.setOwner("Zeus");
        transaction.setImage("https://img3.doubanio.com/mpic/s28351121.jpg");
        transaction.setTitle("疯狂讲义");
        transaction.setAuthor("zeus lv");
        transaction.setState("未交易");
        transaction.setLike("23");
        for (int i = 0; i < 5; i++) {
            transactions.add(transaction);
        }
        updateView(transactions);
    }

}
