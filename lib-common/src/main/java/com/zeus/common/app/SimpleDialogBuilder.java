package com.zeus.common.app;

import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ListAdapter;

/**
 * 用于隐藏 android.app.AlertDialog / android.support.v7.app.AlertDialog / CustomSimpleDialog
 * Created by zeus_coder on 2015/10/14.
 */
public interface SimpleDialogBuilder {

    SimpleDialogBuilder setMessage(CharSequence message);

    SimpleDialogBuilder setMessage(int message);

    SimpleDialogBuilder setTitle(int title);

    SimpleDialogBuilder setTitle(CharSequence title);

    SimpleDialogBuilder setView(View v);

    SimpleDialogBuilder setPositiveButton(int positiveButtonText,
                                          DialogInterface.OnClickListener listener);

    SimpleDialogBuilder setPositiveButton(CharSequence positiveButtonText,
                                          DialogInterface.OnClickListener listener);

    SimpleDialogBuilder setNegativeButton(int negativeButtonText,
                                          DialogInterface.OnClickListener listener);

    SimpleDialogBuilder setNegativeButton(CharSequence negativeButtonText,
                                          DialogInterface.OnClickListener listener);

    SimpleDialogBuilder setNeutralButton(int textId, DialogInterface.OnClickListener listener);

    SimpleDialogBuilder setNeutralButton(CharSequence text, DialogInterface.OnClickListener listener);

    SimpleDialogBuilder setAdapter(ListAdapter adapter, DialogInterface.OnClickListener listener);

    Dialog create();

    Dialog show();
}
