package com.zeus.bookcase.app.user.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;
import com.zeus.bookcase.app.user.adapter.ExpressTimeLineAdapter;
import com.zeus.bookcase.app.user.model.Express;
import com.zeus.bookcase.app.user.view.FullyLinearLayoutManager;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeus_coder on 2015/12/26.
 */
public class ExpressTimeLineActivity extends BaseActivity {

    private RecyclerView expressRecyclerView;

    private ExpressTimeLineAdapter expressTimeLineAdapter;

    private List<Express> mDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user__list_express_timeline);
        initTopButton(R.string.activity_express, R.mipmap.app__top_bar_arrow_back, 0);

        expressRecyclerView = (RecyclerView) findViewById(R.id.label_express_timeline);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        expressRecyclerView.setLayoutManager(new FullyLinearLayoutManager(ExpressTimeLineActivity.this));
        //解决expressRecyclerView一开始显示底部的问题
        expressRecyclerView.setFocusable(false);
        initView();
    }

    private void initView() {
        for(int i = 0; i < 10; i++) {
            Express model = new Express();
            model.setThing("【广州市】广州天河东圃公司神舟分部进行签收扫描，快件已被收发室签收，感谢使用顺丰快递，期待再次为您服务。");
            model.setTime("2016-1-6 10:57:35");
            mDataList.add(model);
        }
        expressTimeLineAdapter = new ExpressTimeLineAdapter(mDataList);
        expressRecyclerView.setAdapter(expressTimeLineAdapter);
    }

}
