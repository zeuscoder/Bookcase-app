package com.zeus.bookcase.app.cabinet.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;
import com.zeus.bookcase.app.cabinet.adapter.TransactionAdapter;
import com.zeus.bookcase.app.cabinet.model.Transaction;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by zeus_coder on 2016/4/7.
 */
public class NewTransactionActivity extends BaseActivity {

    private ListView transactionList;
    private TransactionAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.case__activity_new_transaction);
        initTopButton(R.string.activity_charge, R.mipmap.app__top_bar_arrow_back, 0);
        initViews();
        initData();
    }

    private void initViews() {
        transactionList = (ListView) findViewById(R.id.case_list_transaction);
    }

    private void initData() {
        BmobQuery<Transaction> query = new BmobQuery<Transaction>();
        query.addWhereEqualTo("hasdeal", false);
        query.findObjects(this, new FindListener<Transaction>() {
            @Override
            public void onSuccess(List<Transaction> list) {
                updateView(list);
            }

            @Override
            public void onError(int i, String s) {
                Toast.makeText(NewTransactionActivity.this, s.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateView(final List<Transaction> list) {
        if (list != null) {
            adapter = new TransactionAdapter(this, list);
            transactionList.setAdapter(adapter);
            transactionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Transaction transaction = list.get(i);
                    Intent intent = new Intent(NewTransactionActivity.this, TransactionConditionActivity.class);
                    Bundle bundle =new Bundle();
                    bundle.putSerializable("transaction", transaction);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        } else {
            Toast.makeText(NewTransactionActivity.this, "暂时没有交易", Toast.LENGTH_SHORT).show();
        }
    }


}
