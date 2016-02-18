package com.zeus.bookcase.app.home.ui.activity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.widget.TextView;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;

/**
 * Created by zeus_coder on 2016/2/17.
 */
public class BookOrderConfirmActivity extends BaseActivity {

    private TextView oldPriceTV;

    private String oldPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home__activity_order_confirm);
        initTopButton(R.string.activity_order_confirm, R.mipmap.app__top_bar_arrow_back, 0);

        oldPrice = "￥15";

        oldPriceTV = (TextView) findViewById(R.id.order_book_old_price);
        SpannableStringBuilder ssb = new SpannableStringBuilder(oldPrice);
        ssb.setSpan(new StrikethroughSpan(), 0, oldPrice.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        oldPriceTV.setText(ssb);

    }
}
