package com.zeus.common.util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * A {@link android.widget.TextView} that maintains a consistent width to height aspect ratio.
 * In the real world this would likely extend ImageView.
 * Created by zeus_coder on 2015/11/22.
 */
public class DynamicHeightTextView extends TextView {

    private double mHeightRatio;

    public DynamicHeightTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DynamicHeightTextView(Context context) {
        super(context);
    }

    public void setHeightRatio(double ratio) {
        if (ratio != mHeightRatio) {
            mHeightRatio = ratio;
            requestLayout();
        }
    }

    public double getHeightRatio() {
        return mHeightRatio;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mHeightRatio > 0.0) {
            // set the image views size
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = (int) (width * mHeightRatio);
            setMeasuredDimension(width, height);
        }
        else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
