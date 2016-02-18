package com.zeus.common;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Method;

/**
 * Created by zeus_coder on 2015/11/14.
 */
public class ViewUtils {

    public static <V extends View> V setGone(V view, boolean gone) {
        if (view != null) {
            if (gone) {
                view.setVisibility(View.GONE);
            } else {
                view.setVisibility(View.VISIBLE);
            }
        }
        return view;
    }

    public static <V extends View> V setInvisible(V view, boolean invisible) {
        if (view != null) {
            if (invisible) {
                view.setVisibility(View.INVISIBLE);
            } else {
                view.setVisibility(View.VISIBLE);
            }
        }
        return view;
    }

    /**
     * 调整 parent 的 child views 到指定的数量，并存入 target
     */
    @SuppressWarnings("unchecked")
    public static <V extends View> void fitChildViews(ViewGroup parent, V[] target, ViewPool<V> pool) {
        int childCount = parent.getChildCount();
        // 移除多出的项
        while (target.length < childCount) {
            V view = (V) parent.getChildAt(0);
            parent.removeViewAt(0);
            pool.recycle(view);
            childCount--;
        }
        // 收集已有的项
        for (int i = 0; i < childCount; i++) {
            target[i] = (V) parent.getChildAt(i);
        }
        // 添加并收集新增的项
        while (target.length > childCount) {
            target[childCount] = pool.obtain(parent);
            parent.addView(target[childCount]);
            childCount++;
        }
    }

    public interface ViewPool<V extends View> {
        V obtain(ViewGroup parent);

        void recycle(V view);
    }

    private static Method dispatchDetachedFromWindow;

    static {
        try {
            dispatchDetachedFromWindow = View.class.getDeclaredMethod("dispatchDetachedFromWindow");
            dispatchDetachedFromWindow.setAccessible(true);
        } catch (NoSuchMethodException ignored) {
        }
    }

    public static void callDispatchDetachedFromWindow(View view) {
        if (dispatchDetachedFromWindow == null) {
            return;
        }
        if (view == null) {
            return;
        }
        try {
            dispatchDetachedFromWindow.invoke(view);
        } catch (Exception e) {
            //Timber.w(e, "Fail to call dispatchDetachedFromWindow");
            e.printStackTrace();
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static boolean isAttachedToWindow(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return view.isAttachedToWindow();
        } else {
            return view.getWindowToken() != null;
        }
    }

    /**
     * 过滤 500ms 内的重复事件
     */
    public static void debounceOnClick(View view, final View.OnClickListener listener) {
        if (view == null || listener == null) {
            return;
        }
        view.setOnClickListener(new View.OnClickListener() {
            private long prevClickTime;

            @Override
            public void onClick(View v) {
                long prev = prevClickTime;
                long current = System.currentTimeMillis();
                prevClickTime = current;
                // 500ms 内不允许再次操作
                if (current < prev + 500) {
                    //Timber.v("debounce onClick - time: %sms", current - prev);
                    return;
                }
                listener.onClick(v);
            }
        });
    }

    public static void setError(TextView view, int textId) {
        setError(view, view.getResources().getString(textId));
    }

    public static void setError(TextView view, CharSequence error) {
        //view.setError(Texts.withColor(error, Color.RED));
    }

    private ViewUtils() {
        // No instances.
    }
}
