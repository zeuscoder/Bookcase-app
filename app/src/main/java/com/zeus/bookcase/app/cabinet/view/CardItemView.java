package com.zeus.bookcase.app.cabinet.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.cabinet.model.Transaction;


/**
 * 卡片View项
 * @author xmuSistone
 */
@SuppressLint("NewApi")
public class CardItemView extends LinearLayout {

//    private ImageView imageView;
//    private TextView userNameTv;
//    private TextView imageNumTv;
//    private TextView likeNumTv;
//
//    public CardItemView(Context context) {
//        this(context, null);
//    }
//
//    public CardItemView(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public CardItemView(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//        inflate(context, R.layout.case__card_item, this);
//        imageView = (ImageView) findViewById(R.id.card_image_view);
//        userNameTv = (TextView) findViewById(R.id.card_user_name);
//        imageNumTv = (TextView) findViewById(R.id.card_pic_num);
//        likeNumTv = (TextView) findViewById(R.id.card_like);
//    }
//
//    public void fillData(CardDataItem itemData) {
//        ImageLoader.getInstance().displayImage(itemData.imagePath, imageView);
//        userNameTv.setText(itemData.userName);
//        imageNumTv.setText(itemData.imageNum + "");
//        likeNumTv.setText(itemData.likeNum + "");
//    }

    private ImageView image;
    private TextView title;
    private TextView author;
    private TextView condition;
    private TextView owner;
    private TextView state;
    private TextView like;

    public CardItemView(Context context) {
        this(context, null);
    }

    public CardItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CardItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        inflate(context, R.layout.case__item_transaction, this);
        image = (ImageView) findViewById(R.id.transaction_book_image);
        title = (TextView) findViewById(R.id.transaction_book_title);
        author = (TextView) findViewById(R.id.transaction_book_author);
        condition = (TextView) findViewById(R.id.transaction_book_condition);
        owner = (TextView) findViewById(R.id.transaction_book_owner);
        state = (TextView) findViewById(R.id.transaction_book_state);
        like = (TextView) findViewById(R.id.transaction_book_like);
    }

//    public void fillData(CardDataItem itemData) {
//        ImageLoader.getInstance().displayImage(itemData.imagePath, image);
//    }

    public void fillData(Transaction transaction) {
        ImageLoader.getInstance().displayImage(transaction.getImage(), image);
        title.setText(transaction.getTitle().toString());
        author.setText(transaction.getAuthor().toString());
        condition.setText(transaction.getCondition().toString());
        owner.setText(transaction.getOwner().toString());
        state.setText(transaction.getState().toString());
        like.setText(transaction.getLike().toString());
    }

}
