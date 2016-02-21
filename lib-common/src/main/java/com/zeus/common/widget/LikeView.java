package com.zeus.common.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.zeus.lib_common.R;

/**
 * Created by zeus_coder on 2016/2/20.
 */
public class LikeView extends CheckBox implements CompoundButton.OnCheckedChangeListener  {

    private int likeCount;
    private OnLikeChangedListener onLikeChangedListener;

    public LikeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setGravity(Gravity.CENTER_VERTICAL);
        refresh(isChecked());
        String text = getText().toString().trim();
        if (TextUtils.isEmpty(text)) {
            likeCount = 0;
        } else {
            likeCount = Integer.parseInt(text);
        }
        setText(likeCount + "");

        setOnCheckedChangeListener(this);
    }

    private void refresh(boolean checked) {
        String text = getText().toString().trim();
        if (TextUtils.isEmpty(text)) {
            likeCount = 0;
        } else {
            likeCount = Integer.parseInt(text);
        }
        if (checked) {
            setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.ic_liked), null, null, null);
            likeCount++;
            setText(likeCount + "");
        } else {
            setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.ic_like), null, null, null);
            if (likeCount > 0) {
                likeCount--;
            }
            setText(likeCount + "");
        }
    }

    public void addOnLikeChangeListener(OnLikeChangedListener onLikeChangedListener) {
        this.onLikeChangedListener = onLikeChangedListener;
    }

    public int getLikeCount(){
        return likeCount;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        refresh(isChecked);
        if (onLikeChangedListener != null) {
            onLikeChangedListener.onLikeChanged();
        }
    }

    public interface OnLikeChangedListener {
        void onLikeChanged();
    }
}
