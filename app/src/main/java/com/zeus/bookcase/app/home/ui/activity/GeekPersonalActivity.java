package com.zeus.bookcase.app.home.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeus_coder on 2016/2/19.
 */
public class GeekPersonalActivity extends BaseActivity {

    private ListView messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home__activity_geek_personal);
        initTopButton(R.string.activity_geek, R.mipmap.app__top_bar_arrow_back, 0);
        initListView();
        initHeaderView();
    }

    private void initListView() {
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            datas.add("data: " + i);
        }
        messageList = (ListView) findViewById(R.id.geek_list_message);
        messageList.setAdapter(new Adapter(datas));
        messageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplication(), i+"", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initHeaderView() {
        final View headerView = this.getLayoutInflater().inflate(R.layout.home__activity_geek_personal_header_view, null);
        messageList.addHeaderView(headerView);

    }

    private class Adapter extends BaseAdapter {

        private List<String> datas;

        Adapter(List<String> datas) {
            this.datas = datas;
        }

        @Override
        public int getCount() {
            return null == datas ? 0 : datas.size();
        }

        @Override
        public String getItem(int position) {
            return null == datas ? null : datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = convertView.inflate(getApplicationContext(), R.layout.home__item_geek_message, null);
            }
            return convertView;
        }
    }
}
