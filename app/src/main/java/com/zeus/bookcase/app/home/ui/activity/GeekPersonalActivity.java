package com.zeus.bookcase.app.home.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;
import com.zeus.bookcase.app.home.adapter.GeekMessageAdapter;
import com.zeus.bookcase.app.home.api.BaseAsyncHttp;
import com.zeus.bookcase.app.home.api.HttpResponseHandler;
import com.zeus.bookcase.app.home.model.Geek;
import com.zeus.bookcase.app.home.model.annotation.Annotations;
import com.zeus.bookcase.app.home.model.annotation.BookAnnotation;
import com.zeus.bookcase.app.user.model.User;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.GetListener;

/**
 * Created by zeus_coder on 2016/2/19.
 */
public class GeekPersonalActivity extends BaseActivity {

    @Bind(R.id.geek_list_comment)
    ListView commentList;

    private ImageView image;
    private TextView nameTV;
    private TextView idTV;
    private TextView descTV;
    private TextView numberTV;

    private List<String> data = new ArrayList<>();
    private List<Annotations> annotationses = new ArrayList<>();
    private BookAnnotation annotation;

    private String userId;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home__activity_geek_personal);
        initTopButton(R.string.activity_geek, R.mipmap.app__top_bar_arrow_back, 0);
        ButterKnife.bind(this);

        initHeaderView();
//        finishListView();
        initData();
    }


    private void initHeaderView() {
        final View headerView = this.getLayoutInflater().inflate(R.layout.home__activity_geek_personal_header_view, null);
        image = (ImageView) headerView.findViewById(R.id.home_geek_image);
        nameTV = (TextView) headerView.findViewById(R.id.home_geek_name);
        idTV = (TextView) headerView.findViewById(R.id.home_geek_id);
        descTV = (TextView) headerView.findViewById(R.id.home_geek_decr);
        numberTV = (TextView) headerView.findViewById(R.id.home_geek_comment_number);
        commentList.addHeaderView(headerView, null, false);
    }

//    private void finishListView() {
//        for (int i = 0; i < 10; i++) {
//            data.add("data: " + i);
//        }
//        commentList.setAdapter(new GeekMessageAdapter(data, this));
//        commentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                startActivity(new Intent(GeekPersonalActivity.this, BookArticleActivity.class));
//            }
//        });
//    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Geek geek = (Geek) bundle.getSerializable("geek");
            userId = geek.getUid();
            reqUserInformation(userId);
        } else {
            GeekPersonalActivity.this.finish();
        }
    }

    private void reqUserInformation(String userId) {
        BmobQuery<User> query = new BmobQuery<User>();
        query.getObject(this, userId, new GetListener<User>() {
            @Override
            public void onSuccess(User user) {
                Toast.makeText(GeekPersonalActivity.this, "获取用户信息成功", Toast.LENGTH_SHORT).show();
                ImageLoader.getInstance().displayImage(user.getImage(), image);
                nameTV.setText(user.getNickName());
                idTV.setText("ID:" + user.getObjectId());
                descTV.setText(user.getSign().toString());
                name = user.getNickName();
                reqAnnotationList(0, 20, name);
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(GeekPersonalActivity.this, "获取用户信息失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void reqAnnotationList(int start, int count, String name) {
        RequestParams params=new RequestParams();
        params.put("start", start);
        params.put("count", count);
        BaseAsyncHttp.getReq("/v2/book/user/" + name + "/annotations", params, new HttpResponseHandler() {
            @Override
            public void jsonSuccess(JSONObject resp) {
                Gson gson = new Gson();
                annotation = gson.fromJson(String.valueOf(resp), BookAnnotation.class);
                annotationses = annotation.getAnnotations();
                updateToListView(annotationses);
            }

            @Override
            public void jsonFail(JSONObject resp) {
                Toast.makeText(GeekPersonalActivity.this, String.valueOf(resp).toString(), Toast.LENGTH_SHORT).show();
                Log.i("lvzimou--comment", String.valueOf(resp).toString());
            }
        });
    }

    private void updateToListView(final List<Annotations> annotationses) {
        if (annotationses != null) {
            numberTV.setText(annotationses.size() + "篇笔记");
            commentList.setAdapter(new GeekMessageAdapter(annotationses, this));
            commentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(GeekPersonalActivity.this, BookArticleActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("annotations", annotationses.get(i-1));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            Toast.makeText(GeekPersonalActivity.this, "获取评论成功----", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(GeekPersonalActivity.this, "获取评论失败----", Toast.LENGTH_SHORT).show();
        }
    }
}
