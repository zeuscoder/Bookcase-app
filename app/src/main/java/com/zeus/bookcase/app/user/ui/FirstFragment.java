package com.zeus.bookcase.app.user.ui;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.user.view.ErrorView;


/**
 * Created by zeus_coder on 2015/12/2.
 */
public class FirstFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user__fragment_order, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ErrorView mErrorView = (ErrorView) view.findViewById(R.id.error_view);
        mErrorView.setOnRetryListener(new ErrorView.RetryListener() {
            @Override
            public void onRetry() {
                Toast.makeText(getActivity(), R.string.info_retrying, Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mErrorView.setConfig(ErrorView.Config.create()
                                .title(getString(R.string.error_title_damn))
                                .titleColor(getResources().getColor(R.color.apptheme_primary))
                                .subtitle(getString(R.string.error_subtitle_failed_one_more_time))
                                .retryText(getString(R.string.error_view_retry))
                                .build());
                    }
                }, 5000);
            }
        });
    }
}
