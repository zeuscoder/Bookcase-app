package com.zeus.bookcase.app.home.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nineoldandroids.view.ViewHelper;
import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.home.model.book.Book;
import com.zeus.bookcase.app.home.view.BannerLayout;
import com.zeus.bookcase.app.home.widget.ExpandableTextView;
import com.zeus.bookcase.app.home.widget.ObservableScrollView;
import com.zeus.bookcase.app.home.widget.ObservableScrollViewCallbacks;
import com.zeus.bookcase.app.home.widget.ScrollState;
import com.zeus.bookcase.app.home.widget.ScrollUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ajb on 16-2-19.
 */
public class BookPurchaseMessageFragment extends BaseFragment implements ObservableScrollViewCallbacks {

    //广告栏
    private BannerLayout banner;

    //
    private ExpandableTextView content;
    private TextView title;
    private TextView author;
    private TextView price;
    private TextView date;
    private TextView printer;

    //向上显示topbar
    private View mToolbarView;
    private ObservableScrollView mScrollView;

    private int mParallaxImageHeight;

    private Book book;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home__fragment_purchase_message, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initView(View view) {
        mToolbarView = view.findViewById(R.id.top_bar);
        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, getResources().getColor(R.color.app_background)));

        mScrollView = (ObservableScrollView) view.findViewById(R.id.purchase_scroll);
        mScrollView.setScrollViewCallbacks(this);
        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.parallax_image_height);

        banner = (BannerLayout) view.findViewById(R.id.banner);
        content = (ExpandableTextView) view.findViewById(R.id.book_detail).findViewById(R.id.expand_text_view);
        title = (TextView) view.findViewById(R.id.book_detail).findViewById(R.id.book_title);
        author = (TextView) view.findViewById(R.id.book_detail).findViewById(R.id.book_author);
        price = (TextView) view.findViewById(R.id.book_detail).findViewById(R.id.book_price);
        date = (TextView) view.findViewById(R.id.book_detail).findViewById(R.id.book_date);
        printer = (TextView) view.findViewById(R.id.book_detail).findViewById(R.id.book_printer);
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            book = (Book) bundle.getSerializable("book");
        }
        List<String> urls = new ArrayList<>();
        urls.add(book.getImages().getLarge());
        urls.add(book.getImages().getMedium());
        urls.add(book.getImages().getSmall());
//        urls.add("http://ww1.sinaimg.cn/mw690/af493ad3jw1ezeauostboj20nm07vq6q.jpg");
//        urls.add("http://ww2.sinaimg.cn/mw690/af493ad3jw1ezeauo0y7yj20nm07vad0.jpg");
//        urls.add("http://ww1.sinaimg.cn/mw690/af493ad3jw1ezeaumya2aj20nn07wdit.jpg");
//        urls.add("http://ww3.sinaimg.cn/mw690/af493ad3jw1ezeaunitmvj20no07wn0u.jpg");
        banner.setViewUrls(urls);
        content.setText("        " + book.getSummary());
        title.setText(book.getTitle());
        author.setText(book.getAuthors());
        price.setText(book.getPrice());
        printer.setText(book.getPublisher());
        date.setText(book.getPubdate());

        //添加监听事件
        banner.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });

        mToolbarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        onScrollChanged(mScrollView.getCurrentScrollY(), false, false);
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        int baseColor = getResources().getColor(R.color.app_background);
        float alpha = Math.min(1, (float) scrollY / mParallaxImageHeight);
        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, baseColor));
        ViewHelper.setTranslationY(banner, scrollY / 2);
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }
}
