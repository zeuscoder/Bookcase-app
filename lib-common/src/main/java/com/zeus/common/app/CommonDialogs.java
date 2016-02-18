package com.zeus.common.app;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AppCompatDialog;

import com.zeus.lib_common.R;

/**
 * 通用 dialog
 * Created by zeus_coder on 2015/10/14.
 */
public class CommonDialogs {

    private static final boolean OVER_LOLLIPOP =
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;

    /**
     * 是否使用 Android 5.0 风格的 提示框
     */
    private static final boolean USER_COMPAT_DIALOG = OVER_LOLLIPOP;

    public static SimpleDialogBuilder simpleDialogBuilder(Context context) {
        if(USER_COMPAT_DIALOG) {
            return new CompatAlertDialogBuilder(context);
        }
        return new CustomSimpleDialogBuilder(context);
    }

    public static AlertDialogBuilder alertDialogBuilder(Context context) {
        if(USER_COMPAT_DIALOG) {
            return new CompatAlertDialogBuilder(context);
        }
        return new PlatformAlertDialogBuilder(context);
    }

    public static Dialog createLoadingDialog(Context context) {
        Dialog dialog = new AppCompatDialog(context, R.style.common__TranslucentDialogTheme);
        dialog.setContentView(R.layout.common__dialog_loading_view);
        return dialog;
    }

    public static Dialog showLoadFailedDialog(
            Context context, String message, final CommonDialogs.OnLoadFailedDialogCallback callback){
        if (context == null) {
            return null;
        }
        Dialog dialog = CommonDialogs.simpleDialogBuilder(context)
                .setTitle("加载失败")
                .setMessage(message)
                .setNegativeButton("返回上页", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callback.onResult(false);
                    }
                })
                .setPositiveButton("重新加载", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callback.onResult(true);
                    }
                })
                .show();
        dialog.setCancelable(false);
        return dialog;
    }

    public interface OnLoadFailedDialogCallback {
        void onResult(boolean reload);
    }

}
