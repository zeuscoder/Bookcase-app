package com.zeus.bookcase.app.home.ui;


import android.content.Context;
import android.support.v4.app.Fragment;

import com.zeus.bookcase.app.home.ui.inter.ISlideCallback;

/**
 * Created by zeus_coder on 2016/2/3.
 */
public class BaseFragment extends Fragment {

    private ISlideCallback mISlideCallback;

    public BaseFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof ISlideCallback)) {
            throw new IllegalArgumentException("Activity must be implements ISlideCallback");
        }
        mISlideCallback = (ISlideCallback) context;
    }

    protected void open(boolean smooth) {
        mISlideCallback.openDetails(smooth);
    }

    protected void close(boolean smooth) {
        mISlideCallback.closeDetails(smooth);
    }
}
