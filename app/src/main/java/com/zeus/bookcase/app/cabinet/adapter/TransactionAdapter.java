package com.zeus.bookcase.app.cabinet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.cabinet.model.MyBook;
import com.zeus.bookcase.app.cabinet.model.Transaction;

import java.util.List;

/**
 * Created by zeus_coder on 2016/4/7.
 */
public class TransactionAdapter extends BaseAdapter {

    private Context context;
    private List<Transaction> transactions;


    public TransactionAdapter(Context context, List<Transaction> transactions) {
        this.context = context;
        this.transactions = transactions;
    }

    @Override
    public int getCount() {
        return transactions.size();
    }

    @Override
    public Object getItem(int i) {
        return transactions.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Transaction transaction = transactions.get(i);
        ViewHolder viewHolder;
        if (view != null) {
            viewHolder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.case__item_new_transaction, null);
            viewHolder = new ViewHolder();
            viewHolder.image = (ImageView) view.findViewById(R.id.transaction_book_image);
            viewHolder.title = (TextView) view.findViewById(R.id.transaction_book_title);
            viewHolder.author = (TextView) view.findViewById(R.id.transaction_book_author);
            viewHolder.condition = (TextView) view.findViewById(R.id.transaction_book_condition);
            viewHolder.owner = (TextView) view.findViewById(R.id.transaction_book_owner);
            viewHolder.state = (TextView) view.findViewById(R.id.transaction_book_state);
            view.setTag(viewHolder);
        }
        ImageLoader.getInstance().displayImage(transaction.getImage(), viewHolder.image);
        viewHolder.title.setText(transaction.getTitle());
        viewHolder.author.setText(transaction.getAuthor());
        viewHolder.condition.setText(transaction.getCondition());
        viewHolder.owner.setText(transaction.getOwner());
        viewHolder.state.setText(transaction.getState());
        return view;
    }

    class ViewHolder {
        private ImageView image;
        private TextView title;
        private TextView author;
        private TextView condition;
        private TextView owner;
        private TextView state;
    }
}
