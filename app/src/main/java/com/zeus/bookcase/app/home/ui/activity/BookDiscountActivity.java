package com.zeus.bookcase.app.home.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;
import com.zeus.bookcase.app.home.adapter.BookDiscountAdapter;
import com.zeus.bookcase.app.home.api.BaseAsyncHttp;
import com.zeus.bookcase.app.home.api.HttpResponseHandler;
import com.zeus.bookcase.app.home.data.BookDiscountSampleData;
import com.zeus.bookcase.app.home.model.book.Book;
import com.zeus.common.view.StaggeredGridView;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeus_coder on 2015/12/1.
 */
public class BookDiscountActivity extends BaseActivity implements AbsListView.OnScrollListener, AbsListView.OnItemClickListener, AdapterView.OnItemLongClickListener{

    private static final String TAG = "BookDiscountActivity";
    public static final String SAVED_DATA_KEY = "SAVED_DATA";

    private StaggeredGridView mBookGridView;
    private boolean mHasRequestedMore;
    private BookDiscountAdapter mAdapter;

    private ArrayList<String> mData;

    private List<Book> books;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home__activity_book_discount);
        initTopButton(R.string.activity_discount, R.mipmap.app__top_bar_arrow_back, 0);

        initViews();
        initData(savedInstanceState);
    }

    private void initViews() {
        mBookGridView = (StaggeredGridView) findViewById(R.id.stagger_grid_view);
/*        LayoutInflater layoutInflater = getLayoutInflater();
        View header = layoutInflater.inflate(R.layout.list_item_header_footer, null);
        View footer = layoutInflater.inflate(R.layout.list_item_header_footer, null);
        TextView txtHeaderTitle = (TextView) header.findViewById(R.id.txt_title);
        TextView txtFooterTitle =  (TextView) footer.findViewById(R.id.txt_title);
        txtHeaderTitle.setText("THE HEADER!");
        txtFooterTitle.setText("THE FOOTER!");
        mGridView.addHeaderView(header);
        mGridView.addFooterView(footer);*/

    }

    private void initData(Bundle savedInstanceState) {
        if (getIntent().hasExtra("category")) {
            category = getIntent().getStringExtra("category");
            books = new ArrayList<Book>();
            getRequestData(category, savedInstanceState);
        }

    }

    /*加载查询数据 */
    public void getRequestData(String str, final Bundle savedInstanceState){
        RequestParams params=new RequestParams();
        params.put("q", str.trim());
        BaseAsyncHttp.getReq("/v2/book/search", params, new HttpResponseHandler() {

            @Override
            public void jsonSuccess(JSONObject resp) {
                books.clear();
                JSONArray jsonbooks = resp.optJSONArray("books");
                Gson gson = new Gson();
                books = gson.fromJson(String.valueOf(jsonbooks), new TypeToken<List<Book>>() {
                }.getType());
                updateToView(savedInstanceState);
            }

            @Override
            public void jsonFail(JSONObject resp) {
                Toast.makeText(BookDiscountActivity.this, "网络出错", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateToView(Bundle savedInstanceState) {
        mAdapter = new BookDiscountAdapter(this, books);

        // do we have saved data?
        if (savedInstanceState != null) {
            mData = savedInstanceState.getStringArrayList(SAVED_DATA_KEY);
        }

        if (mData == null) {
            mData = BookDiscountSampleData.generateSampleData();
        }

        mBookGridView.setAdapter(mAdapter);
        mBookGridView.setOnScrollListener(this);
        mBookGridView.setOnItemClickListener(this);
        mBookGridView.setOnItemLongClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList(SAVED_DATA_KEY, mData);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(BookDiscountActivity.this, BookIntroductionActivity.class);
        intent.putExtra("bookId", books.get(position).getId());
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return true;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        Log.d(TAG, "onScrollStateChanged:" + scrollState);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//        Log.d(TAG, "onScroll firstVisibleItem:" + firstVisibleItem +
//                " visibleItemCount:" + visibleItemCount +
//                " totalItemCount:" + totalItemCount);
//        // our handling
//        if (!mHasRequestedMore) {
//            int lastInScreen = firstVisibleItem + visibleItemCount;
//            if (lastInScreen >= totalItemCount) {
//                Log.d(TAG, "onScroll lastInScreen - so load more");
//                mHasRequestedMore = true;
//                onLoadMoreItems();
//            }
//        }
    }

    private void onLoadMoreItems() {
        // notify the adapter that we can update now
        mAdapter.notifyDataSetChanged();
        mHasRequestedMore = false;
    }
}
