package com.zeus.common.app;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zeus.lib_common.R;

/**
 * Helper class for creating a custom dialog
 * Created by zeus_coder on 2015/10/14.
 */
public class CustomSimpleDialogBuilder implements SimpleDialogBuilder {

    private Context mContext;
    private CharSequence mTitle;
    private CharSequence mMessage;
    private CharSequence mPositiveButtonText;
    private CharSequence mNegativeButtonText;
    private CharSequence mNeutralButtonText;
    private View mContentView;

    private DialogInterface.OnClickListener mPositiveButtonClickListener;
    private DialogInterface.OnClickListener mNegativeButtonClickListener;
    private DialogInterface.OnClickListener mNeutralButtonClickListener;

    private ListAdapter mListAdapter;
    private DialogInterface.OnClickListener mListClickListener;

    CustomSimpleDialogBuilder(Context context) {
        this.mContext = context;
    }

    /**
     * Set the Dialog message from String
     *
     * @param message
     * @return
     */
    @Override
    public CustomSimpleDialogBuilder setMessage(CharSequence message) {
        this.mMessage = message;
        return this;
    }

    /**
     * Set the Dialog message from resource
     *
     * @param message
     * @return
     */
    @Override
    public CustomSimpleDialogBuilder setMessage(int message) {
        this.mMessage = mContext.getText(message);
        return this;
    }

    /**
     * Set the Dialog title from resource
     *
     * @param title
     * @return
     */
    @Override
    public CustomSimpleDialogBuilder setTitle(int title) {
        this.mTitle = mContext.getText(title);
        return this;
    }

    /**
     * Set the Dialog title from String
     *
     * @param title
     * @return
     */
    @Override
    public CustomSimpleDialogBuilder setTitle(CharSequence title) {
        this.mTitle = title;
        return this;
    }

    /**
     * Set a custom content view for the Dialog. If a message is set, the
     * contentView is not added to the Dialog...
     *
     * @param v
     * @return
     */
    @Override
    public CustomSimpleDialogBuilder setView(View v) {
        this.mContentView = v;
        return this;
    }

    /**
     * Set the positive button resource and it's listener
     *
     * @param positiveButtonText
     * @param listener
     * @return
     */
    @Override
    public CustomSimpleDialogBuilder setPositiveButton(int positiveButtonText,
                                                       DialogInterface.OnClickListener listener) {
        this.mPositiveButtonText = mContext
                .getText(positiveButtonText);
        this.mPositiveButtonClickListener = listener;
        return this;
    }

    /**
     * Set the positive button text and it's listener
     *
     * @param positiveButtonText
     * @param listener
     * @return
     */
    @Override
    public CustomSimpleDialogBuilder setPositiveButton(CharSequence positiveButtonText,
                                                       DialogInterface.OnClickListener listener) {
        this.mPositiveButtonText = positiveButtonText;
        this.mPositiveButtonClickListener = listener;
        return this;
    }

    /**
     * Set the negative button resource and it's listener
     *
     * @param negativeButtonText
     * @param listener
     * @return
     */
    @Override
    public CustomSimpleDialogBuilder setNegativeButton(int negativeButtonText,
                                                       DialogInterface.OnClickListener listener) {
        this.mNegativeButtonText = mContext
                .getText(negativeButtonText);
        this.mNegativeButtonClickListener = listener;
        return this;
    }

    /**
     * Set the negative button text and it's listener
     *
     * @param negativeButtonText
     * @param listener
     * @return
     */
    @Override
    public CustomSimpleDialogBuilder setNegativeButton(CharSequence negativeButtonText,
                                                       DialogInterface.OnClickListener listener) {
        this.mNegativeButtonText = negativeButtonText;
        this.mNegativeButtonClickListener = listener;
        return this;
    }

    @Override
    public CustomSimpleDialogBuilder setNeutralButton(int textId, DialogInterface.OnClickListener listener) {
        this.mNeutralButtonText = mContext
                .getText(textId);
        this.mNeutralButtonClickListener = listener;
        return this;
    }

    @Override
    public CustomSimpleDialogBuilder setNeutralButton(CharSequence text, DialogInterface.OnClickListener listener) {
        this.mNeutralButtonText = text;
        this.mNeutralButtonClickListener = listener;
        return this;
    }

    @Override
    public CustomSimpleDialogBuilder setAdapter(ListAdapter adapter, DialogInterface.OnClickListener listener) {
        mListAdapter = adapter;
        mListClickListener = listener;
        return this;
    }

    /**
     * Create the custom dialog
     */
    @Override
    public Dialog create() {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // instantiate the dialog with the custom Theme
        final Dialog dialog = new Dialog(mContext,
                R.style.common__CustomAlertDialogTheme);
        View layout = inflater.inflate(R.layout.common__custom_dialog, null);
//            dialog.addContentView(layout, new LayoutParams(
//                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        // set the dialog title
        ((TextView) layout.findViewById(R.id.tv_title)).setText(mTitle);
        layout.findViewById(R.id.ll_title_bar).setVisibility(
                mTitle != null ? View.VISIBLE : View.GONE);
        // set the confirm button
        if (mPositiveButtonText != null) {
            Button button = (Button) layout
                    .findViewById(R.id.b_positive_button);
            button.setText(mPositiveButtonText);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (mPositiveButtonClickListener != null) {
                        mPositiveButtonClickListener.onClick(dialog,
                                DialogInterface.BUTTON_POSITIVE);
                    }
                    dialog.dismiss();
                }
            });
            layout.findViewById(R.id.b_positive_button).setVisibility(
                    View.VISIBLE
            );
            layout.findViewById(R.id.v_positive_divider).setVisibility(
                    mNegativeButtonText == null && mNeutralButtonText == null
                            ? View.GONE : View.VISIBLE
            );
        } else {
            // if no confirm button just set the visibility to GONE
            layout.findViewById(R.id.b_positive_button).setVisibility(
                    View.GONE
            );
            layout.findViewById(R.id.v_positive_divider).setVisibility(
                    View.GONE
            );
        }
        // set the cancel button
        if (mNegativeButtonText != null) {
            Button button = (Button) layout
                    .findViewById(R.id.b_negative_button);
            button.setText(mNegativeButtonText);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (mNegativeButtonClickListener != null) {
                        mNegativeButtonClickListener.onClick(dialog,
                                DialogInterface.BUTTON_NEGATIVE);
                    }
                    dialog.dismiss();
                }
            });
            layout.findViewById(R.id.b_negative_button).setVisibility(
                    View.VISIBLE
            );
            layout.findViewById(R.id.v_negative_divider).setVisibility(
                    mPositiveButtonText == null && mNeutralButtonText == null
                            ? View.GONE : View.VISIBLE
            );
        } else {
            // if no confirm button just set the visibility to GONE
            layout.findViewById(R.id.b_negative_button).setVisibility(
                    View.GONE
            );
            layout.findViewById(R.id.v_negative_divider).setVisibility(
                    View.GONE
            );
        }
        if (mNeutralButtonText != null) {
            Button button = (Button) layout
                    .findViewById(R.id.b_neutral_button);
            button.setText(mNeutralButtonText);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (mNeutralButtonClickListener != null) {
                        mNeutralButtonClickListener.onClick(dialog,
                                DialogInterface.BUTTON_NEUTRAL);
                    }
                    dialog.dismiss();
                }
            });
            layout.findViewById(R.id.b_neutral_button).setVisibility(
                    View.VISIBLE);
        } else {
            layout.findViewById(R.id.b_neutral_button).setVisibility(
                    View.GONE);

            //
            layout.findViewById(R.id.v_negative_divider).setVisibility(
                    View.GONE
            );
        }
        if (mPositiveButtonText == null && mNeutralButtonText == null && mNegativeButtonText == null) {
            layout.findViewById(R.id.ll_button_bar).setVisibility(View.GONE);
        }
        layout.findViewById(R.id.tv_message).setVisibility(
                mMessage != null ? View.VISIBLE : View.GONE);
        // set the content message
        if (mMessage != null) {
            ((TextView) layout.findViewById(R.id.tv_message))
                    .setText(mMessage);
        } else if (mListAdapter != null) {
            layout.findViewById(R.id.ll_message).setVisibility(View.GONE);
            ListView listView = (ListView) layout.findViewById(R.id.lv_list);
            listView.setAdapter(mListAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    if (mListClickListener != null) {
                        mListClickListener.onClick(dialog,
                                position);
                    }
                    dialog.dismiss();
                }
            });
        } else if (mContentView != null) {
            // if no message set
            // add the contentView to the dialog body
            FrameLayout content = (FrameLayout) layout.findViewById(R.id.fl_content);
            content.removeAllViews();
            content.addView(
                    mContentView);
        }
        dialog.setContentView(layout);
        return dialog;
    }

    @Override
    public Dialog show() {
        Dialog dialog = create();
        dialog.show();
        return dialog;
    }
}
