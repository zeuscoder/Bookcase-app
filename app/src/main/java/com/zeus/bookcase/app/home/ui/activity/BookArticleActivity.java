package com.zeus.bookcase.app.home.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;
import com.zeus.bookcase.app.home.model.annotation.Annotations;
import com.zeus.common.widget.LikeView;

import org.w3c.dom.Text;

/**
 * Created by zeus_coder on 2016/2/20.
 */
public class BookArticleActivity extends BaseActivity {


    private TextView date;
    private TextView title;
    private TextView author;
    private TextView content;
    private LikeView like;
    private ImageView image;

    private Button purchase;

    private Annotations annotations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home__activity_book_article);
        initTopButton(R.string.activity_article, R.mipmap.app__top_bar_arrow_back, 0);

        initViews();
        initData();
        initClick();

    }

    private void initViews() {
        date = (TextView) findViewById(R.id.article_time);
        title = (TextView) findViewById(R.id.article_title);
        author = (TextView) findViewById(R.id.article_author);
        content = (TextView) findViewById(R.id.article_content);
        image = (ImageView) findViewById(R.id.article_image);
        like = (LikeView) findViewById(R.id.article_like);
        purchase = (Button) findViewById(R.id.book_article_purchase);
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            annotations = (Annotations) bundle.getSerializable("annotations");
            date.setText(annotations.getTime().toString());
            title.setText(annotations.getBook().getTitle().toString());
            author.setText(annotations.getAuthor_user().getName().toString());
            content.setText(annotations.getContent().toString());
            ImageLoader.getInstance().displayImage(annotations.getBook().getImage().toString(), image);
        }
    }

    private void initClick() {
        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookArticleActivity.this, BookPurchaseDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("book", annotations.getBook());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
