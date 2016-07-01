package com.zeus.bookcase.app.home.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
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
import com.zeus.bookcase.app.home.adapter.BookIntroductionCommentAdapter;
import com.zeus.bookcase.app.home.api.BaseAsyncHttp;
import com.zeus.bookcase.app.home.api.HttpResponseHandler;
import com.zeus.bookcase.app.home.model.annotation.Annotations;
import com.zeus.bookcase.app.home.model.annotation.BookAnnotation;
import com.zeus.bookcase.app.home.model.book.Book;
import com.zeus.bookcase.app.home.model.dao.FavoriteDao;
import com.zeus.bookcase.app.home.widget.ExpandableTextView;
import com.zeus.bookcase.app.user.model.User;
import com.zeus.bookcase.app.user.ui.activity.LogInActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;

import static com.zeus.bookcase.app.R.id.book_picture;

/**
 * Created by zeus_coder on 2016/2/21.
 */
public class BookIntroductionActivity extends BaseActivity implements View.OnClickListener{

    @Bind(R.id.book_introduction_comment_list)
    ListView comments;
    private List<Annotations> data = new ArrayList<>();
    private BookAnnotation annotation;

    private ImageView picture;
    private TextView title;
    private TextView author;
    private TextView price;
    private TextView menu;
    private TextView elses;
    private ExpandableTextView content;

    @Bind(R.id.book_add_favorite)
    TextView favorite;
    @Bind(R.id.book_purchase)
    TextView purchase;

    private String bookId;
    private Book book;
    private FavoriteDao favoriteDao;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home__activity_book_introduction);
        initTopButton(R.string.activity_book_introduction, R.mipmap.app__top_bar_arrow_back, 0);
        ButterKnife.bind(this);

        initView();
        initHeaderView();

        initData();
    }

    private void initView() {
        favorite.setOnClickListener(this);
        purchase.setOnClickListener(this);
    }

    public void initData() {
        bookId = getIntent().getStringExtra("bookId");
        favoriteDao = new FavoriteDao(this);
        user = BmobUser.getCurrentUser(this, User.class);
        reqBookInformation(bookId);
        reqAnnotationList(0, 20, bookId);
    }

    private void reqBookInformation(String bookId) {
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
                updateToHeaderView(book);
            }

            @Override
            public void jsonFail(JSONObject resp) {
                if (resp.optInt("code") == 6000) {
                    Toast.makeText(BookIntroductionActivity.this, "没有找到该图书", Toast.LENGTH_LONG).show();
                    BookIntroductionActivity.this.finish();
                }
            }
        });
    }

    private void reqAnnotationList(int start, int count, String bookId) {
        RequestParams params=new RequestParams();
        params.put("start", start);
        params.put("count", count);
        BaseAsyncHttp.getReq("/v2/book/" + bookId + "/annotations", params, new HttpResponseHandler() {
            @Override
            public void jsonSuccess(JSONObject resp) {
                Gson gson = new Gson();
                annotation = gson.fromJson(String.valueOf(resp), BookAnnotation.class);
                data = annotation.getAnnotations();
                updateToListView(data);
            }

            @Override
            public void jsonFail(JSONObject resp) {
                Toast.makeText(BookIntroductionActivity.this, "获取评论失败！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initHeaderView() {
        View headerView = this.getLayoutInflater().inflate(R.layout.home__activity_book_introduction_header_view, null);
        picture = (ImageView) headerView.findViewById(book_picture);
        title = (TextView) headerView.findViewById(R.id.book_title);
        author = (TextView) headerView.findViewById(R.id.book_author);
        content = (ExpandableTextView) headerView.findViewById(R.id.expand_text_view);
        price = (TextView) headerView.findViewById(R.id.book_price);
        menu = (TextView) headerView.findViewById(R.id.book_menu);
        elses = (TextView) headerView.findViewById(R.id.book_else);
        comments.addHeaderView(headerView, null, false);
    }

    private void finishListView() {
//        for (int i = 0; i < 10; i++) {
//            Annotations annotations = new Annotations();
//            data.add(annotations);
//        }

    }

    private void updateToHeaderView(Book book) {
        title.setText(book.getTitle());
        author.setText(book.getAuthors());
        price.setText("￥" + book.getPrice());
        content.setText("        " + book.getSummary());
        ImageLoader.getInstance().displayImage(book.getImage(), picture);
    }

    private void updateToListView(final List<Annotations> data) {
        comments.setAdapter(new BookIntroductionCommentAdapter(data, this));
        comments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(BookIntroductionActivity.this, BookArticleActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("annotations", data.get(i-1));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.book_add_favorite:
                addFavorite();
                break;
            case R.id.book_purchase:
                goPurchaseActivity();
                break;
        }
    }

    private void addFavorite() {
        if (user != null) {
            if (favoriteDao.insertFavorite(book, user)) {
                Toast toast = Toast.makeText(BookIntroductionActivity.this, "收藏成功", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            } else {
                Toast toast = Toast.makeText(BookIntroductionActivity.this, "收藏失败", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        } else {
            startActivity(new Intent(BookIntroductionActivity.this, LogInActivity.class));
        }

    }

    private void goPurchaseActivity() {
        Intent intent = new Intent(BookIntroductionActivity.this, BookPurchaseDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("book", book);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
