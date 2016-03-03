package com.zeus.bookcase.app.user.ui;



import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.home.ui.activity.BookIntroductionActivity;
import com.zeus.bookcase.app.home.ui.activity.BookPurchaseDetailActivity;

/**
 * Created by zeus_coder on 2016/2/26.
 */
public class BookRecommendDetailFragment extends Fragment {

    private TextView checkBook;
    private TextView buyNow;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user__fragment_book_recommend_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        checkBook = (TextView) view.findViewById(R.id.look_book_instruction);
        buyNow = (TextView) view.findViewById(R.id.book_right_now_purchase);
        checkBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), BookIntroductionActivity.class));
            }
        });
        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), BookPurchaseDetailActivity.class));
            }
        });
    }
}
