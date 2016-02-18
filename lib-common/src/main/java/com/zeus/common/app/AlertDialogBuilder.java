package com.zeus.common.app;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;

/**
 * 用于隐藏 android.app.AlertDialog / android.support.v7.app.AlertDialog
 *
 * Created by zeus_coder on 2015/10/14.
 */
public interface AlertDialogBuilder extends SimpleDialogBuilder {

    AlertDialogBuilder setMessage(CharSequence message);

    AlertDialogBuilder setMessage(int message);

    AlertDialogBuilder setTitle(int title);

    AlertDialogBuilder setTitle(CharSequence title);

    AlertDialogBuilder setView(View v);

    AlertDialogBuilder setPositiveButton(int positiveButtonText,
                                         DialogInterface.OnClickListener listener);

    AlertDialogBuilder setPositiveButton(CharSequence positiveButtonText,
                                         DialogInterface.OnClickListener listener);

    AlertDialogBuilder setNegativeButton(int negativeButtonText,
                                         DialogInterface.OnClickListener listener);

    AlertDialogBuilder setNegativeButton(CharSequence negativeButtonText,
                                         DialogInterface.OnClickListener listener);

    AlertDialogBuilder setNeutralButton(int textId, DialogInterface.OnClickListener listener);

    AlertDialogBuilder setNeutralButton(CharSequence text, DialogInterface.OnClickListener listener);

    AlertDialogBuilder setAdapter(ListAdapter adapter, DialogInterface.OnClickListener listener);

    Dialog create();

    Dialog show();

    AlertDialogBuilder setIcon(Drawable icon);

    AlertDialogBuilder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener);

    AlertDialogBuilder setCancelable(boolean cancelable);

    AlertDialogBuilder setItems(CharSequence[] items, DialogInterface.OnClickListener listener);

    AlertDialogBuilder setInverseBackgroundForced(boolean useInverseBackground);

    AlertDialogBuilder setSingleChoiceItems(Cursor cursor, int checkedItem, String labelColumn, DialogInterface.OnClickListener listener);

    AlertDialogBuilder setOnKeyListener(DialogInterface.OnKeyListener onKeyListener);

    AlertDialogBuilder setCustomTitle(View customTitleView);

    AlertDialogBuilder setItems(int itemsId, DialogInterface.OnClickListener listener);

    AlertDialogBuilder setCursor(Cursor cursor, DialogInterface.OnClickListener listener, String labelColumn);

    AlertDialogBuilder setSingleChoiceItems(ListAdapter adapter, int checkedItem, DialogInterface.OnClickListener listener);

    AlertDialogBuilder setSingleChoiceItems(int itemsId, int checkedItem, DialogInterface.OnClickListener listener);

    AlertDialogBuilder setSingleChoiceItems(CharSequence[] items, int checkedItem, DialogInterface.OnClickListener listener);

    AlertDialogBuilder setIcon(int iconId);

    AlertDialogBuilder setMultiChoiceItems(int itemsId, boolean[] checkedItems, DialogInterface.OnMultiChoiceClickListener listener);

    AlertDialogBuilder setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener);

    AlertDialogBuilder setMultiChoiceItems(CharSequence[] items, boolean[] checkedItems, DialogInterface.OnMultiChoiceClickListener listener);

    AlertDialogBuilder setMultiChoiceItems(Cursor cursor, String isCheckedColumn, String labelColumn, DialogInterface.OnMultiChoiceClickListener listener);
}
