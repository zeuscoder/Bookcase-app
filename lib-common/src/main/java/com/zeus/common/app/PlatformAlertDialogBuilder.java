package com.zeus.common.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;

/**
 * 使用系统原生的 AlertDialog
 *
 * Created by zeus_coder on 2015/10/14.
 */
public class PlatformAlertDialogBuilder implements AlertDialogBuilder {

    private final AlertDialog.Builder delegate;

    PlatformAlertDialogBuilder(Context context) {
        delegate = new AlertDialog.Builder(context);
    }

    @Override
    public AlertDialogBuilder setTitle(int titleId) {
        delegate.setTitle(titleId);
        return this;
    }

    @Override
    public AlertDialogBuilder setTitle(CharSequence title) {
        delegate.setTitle(title);
        return this;
    }

    @Override
    public AlertDialogBuilder setView(View v) {
        delegate.setView(v);
        return this;
    }

    @Override
    public AlertDialogBuilder setCustomTitle(View customTitleView) {
        delegate.setCustomTitle(customTitleView);
        return this;
    }

    @Override
    public AlertDialogBuilder setMessage(int messageId) {
        delegate.setMessage(messageId);
        return this;
    }

    @Override
    public AlertDialogBuilder setMessage(CharSequence message) {
        delegate.setMessage(message);
        return this;
    }

    @Override
    public AlertDialogBuilder setIcon(int iconId) {
        delegate.setIcon(iconId);
        return this;
    }

    @Override
    public AlertDialogBuilder setIcon(Drawable icon) {
        delegate.setIcon(icon);
        return this;
    }

    @Override
    public AlertDialogBuilder setPositiveButton(int textId, DialogInterface.OnClickListener listener) {
        delegate.setPositiveButton(textId, listener);
        return this;
    }

    @Override
    public AlertDialogBuilder setPositiveButton(CharSequence text, DialogInterface.OnClickListener listener) {
        delegate.setPositiveButton(text, listener);
        return this;
    }

    @Override
    public AlertDialogBuilder setNegativeButton(int textId, DialogInterface.OnClickListener listener) {
        delegate.setNegativeButton(textId, listener);
        return this;
    }

    @Override
    public AlertDialogBuilder setNegativeButton(CharSequence text, DialogInterface.OnClickListener listener) {
        delegate.setNegativeButton(text, listener);
        return this;
    }

    @Override
    public AlertDialogBuilder setNeutralButton(int textId, DialogInterface.OnClickListener listener) {
        delegate.setNeutralButton(textId, listener);
        return this;
    }

    @Override
    public AlertDialogBuilder setNeutralButton(CharSequence text, DialogInterface.OnClickListener listener) {
        delegate.setNeutralButton(text, listener);
        return this;
    }

    @Override
    public AlertDialogBuilder setCancelable(boolean cancelable) {
        delegate.setCancelable(cancelable);
        return this;
    }

    @Override
    public AlertDialogBuilder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        delegate.setOnCancelListener(onCancelListener);
        return this;
    }

    @Override
    public AlertDialogBuilder setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
        delegate.setOnKeyListener(onKeyListener);
        return this;
    }

    @Override
    public AlertDialogBuilder setItems(int itemsId, DialogInterface.OnClickListener listener) {
        delegate.setItems(itemsId, listener);
        return this;
    }

    @Override
    public AlertDialogBuilder setItems(CharSequence[] items, DialogInterface.OnClickListener listener) {
        delegate.setItems(items, listener);
        return this;
    }

    @Override
    public AlertDialogBuilder setAdapter(ListAdapter adapter, DialogInterface.OnClickListener listener) {
        delegate.setAdapter(adapter, listener);
        return this;
    }

    @Override
    public AlertDialogBuilder setCursor(Cursor cursor, DialogInterface.OnClickListener listener, String labelColumn) {
        delegate.setCursor(cursor, listener, labelColumn);
        return this;
    }

    @Override
    public AlertDialogBuilder setMultiChoiceItems(int itemsId, boolean[] checkedItems, DialogInterface.OnMultiChoiceClickListener listener) {
        delegate.setMultiChoiceItems(itemsId, checkedItems, listener);
        return this;
    }

    @Override
    public AlertDialogBuilder setMultiChoiceItems(CharSequence[] items, boolean[] checkedItems, DialogInterface.OnMultiChoiceClickListener listener) {
        delegate.setMultiChoiceItems(items, checkedItems, listener);
        return this;
    }

    @Override
    public AlertDialogBuilder setMultiChoiceItems(Cursor cursor, String isCheckedColumn, String labelColumn, DialogInterface.OnMultiChoiceClickListener listener) {
        delegate.setMultiChoiceItems(cursor, isCheckedColumn, labelColumn, listener);
        return this;
    }

    @Override
    public AlertDialogBuilder setSingleChoiceItems(int itemsId, int checkedItem, DialogInterface.OnClickListener listener) {
        delegate.setSingleChoiceItems(itemsId, checkedItem, listener);
        return this;
    }

    @Override
    public AlertDialogBuilder setSingleChoiceItems(Cursor cursor, int checkedItem, String labelColumn, DialogInterface.OnClickListener listener) {
        delegate.setSingleChoiceItems(cursor, checkedItem, labelColumn, listener);
        return this;
    }

    @Override
    public AlertDialogBuilder setSingleChoiceItems(CharSequence[] items, int checkedItem, DialogInterface.OnClickListener listener) {
        delegate.setSingleChoiceItems(items, checkedItem, listener);
        return this;
    }

    @Override
    public AlertDialogBuilder setSingleChoiceItems(ListAdapter adapter, int checkedItem, DialogInterface.OnClickListener listener) {
        delegate.setSingleChoiceItems(adapter, checkedItem, listener);
        return this;
    }

    @Override
    public AlertDialogBuilder setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener) {
        delegate.setOnItemSelectedListener(listener);
        return this;
    }

    @Override
    public AlertDialogBuilder setInverseBackgroundForced(boolean useInverseBackground) {
        delegate.setInverseBackgroundForced(useInverseBackground);
        return this;
    }

    @Override
    public Dialog create() {
        return delegate.create();
    }

    @Override
    public Dialog show() {
        return delegate.show();
    }
}
