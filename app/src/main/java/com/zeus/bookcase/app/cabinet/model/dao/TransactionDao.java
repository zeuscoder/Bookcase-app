package com.zeus.bookcase.app.cabinet.model.dao;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.zeus.bookcase.app.cabinet.model.MyBook;
import com.zeus.bookcase.app.cabinet.model.Transaction;
import com.zeus.bookcase.app.home.model.Cart;
import com.zeus.bookcase.app.home.model.Order;
import com.zeus.bookcase.app.user.model.User;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by zeus_coder on 2016/4/6.
 */
public class TransactionDao {

    private final String BID = "bid";

    private final String UID = "uid";

    private final String TID = "tid";

    private final String CONDITION = "condition";

    private final String ISDEAL = "isdeal";

    private final String HASDEAL = "hasdeal";

    private final String OWNER = "owner";

    private final String IMAGE = "image";

    private final String TITLE = "title";

    private final String AUTHOR = "author";

    private final String STATE = "state";

    private final String LIKE = "like";

    private final String SUMMARY = "summary";


    private Context context;
    private List<Transaction> transactions;
    private Transaction transaction;
    private boolean flag = false;

    public TransactionDao(Context context) {
        this.context = context;
    }

    public void insertTransaction(MyBook book, User user, final String condition) {
        transaction = new Transaction();
        transaction.setBid(book.getId().toString());
        transaction.setUid(user.getObjectId().toString());
        transaction.setTitle(book.getTitle().toString());
        transaction.setAuthor(book.getPublisher().toString());
        transaction.setImage(book.getImage().toString());
        transaction.setSummary(book.getSummary().toString());
        transaction.setState("待交易");
        transaction.setCondition(condition);
        transaction.setOwner(user.getNickName().toString());
        transaction.setLike(String.valueOf(0));
        transaction.setHasdeal(false);
        transaction.setIsdeal(false);
        transaction.save(context, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(context, "已提交交易", Toast.LENGTH_SHORT).show();
                ((Activity)context).finish();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(context, s.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public List<Transaction> getAllTransactions() {
        transactions = new ArrayList<Transaction>();
        BmobQuery<Transaction> query = new BmobQuery<Transaction>();
        query.addWhereEqualTo("isdeal", false);
        query.findObjects(context, new FindListener<Transaction>() {
            @Override
            public void onSuccess(List<Transaction> list) {
                transactions = list;
            }

            @Override
            public void onError(int i, String s) {
                Toast.makeText(context, s.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return transactions;
    }
}
