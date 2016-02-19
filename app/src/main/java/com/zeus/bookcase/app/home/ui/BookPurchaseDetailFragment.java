package com.zeus.bookcase.app.home.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.zeus.bookcase.app.R;

/**
 * Created by ajb on 16-2-19.
 */
public class BookPurchaseDetailFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home__fragment_purchase_detail, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final WebView webView = (WebView) view.findViewById(R.id.slidedetails_behind);
        webView.loadUrl("http://www.cnbleu.com");
    }
}
