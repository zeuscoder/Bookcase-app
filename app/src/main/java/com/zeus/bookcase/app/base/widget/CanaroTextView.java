package com.zeus.bookcase.app.base.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by zeus_coder on 2016/2/3.
 */
public class CanaroTextView extends TextView {

    private static final String CANARO_EXTRA_BOLD_PATH = "fonts/canaro_extra_bold.otf";

    public CanaroTextView(Context context) {
        super(context);
    }

    public CanaroTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanaroTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setTypeface(Typeface tf, int style) {
        super.setTypeface(Typeface.createFromAsset(getContext().getAssets(),CANARO_EXTRA_BOLD_PATH));
    }
}
