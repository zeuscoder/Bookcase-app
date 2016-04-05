package com.zeus.bookcase.app.cabinet.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;
import com.zeus.bookcase.app.cabinet.adapter.MySelfBookAdapter;
import com.zeus.bookcase.app.cabinet.model.MyBook;
import com.zeus.bookcase.app.cabinet.model.dao.BookDao;
import com.zeus.bookcase.app.user.model.User;

import java.util.List;

import cn.bmob.v3.BmobUser;

/**
 * Created by zeus_coder on 2016/3/31.
 */
public class MyBookActivity extends BaseActivity {

    private SwipeMenuListView bookList;
    private SwipeMenuCreator creator;

    private MySelfBookAdapter bookAdapter;

    private List<MyBook> books;

    private BookDao bookDao;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.case__activity_my_book);
        initTopButton(R.string.activity_book, R.mipmap.app__top_bar_arrow_back, 0);
        bookDao = new BookDao(this);

        initView();
        initData();
        initCreator();
        initClick();
    }

    private void initView() {
        bookList = (SwipeMenuListView) findViewById(R.id.case_my_book);

    }

    private void initData() {
        books = bookDao.getAllData(BmobUser.getCurrentUser(this, User.class));
        user = BmobUser.getCurrentUser(this, User.class);
        if (books != null) {
            bookAdapter = new MySelfBookAdapter(this, books);
            bookList.setAdapter(bookAdapter);
        } else {
            Toast.makeText(MyBookActivity.this, "暂时没有数据", Toast.LENGTH_SHORT).show();
        }
    }

    private void initCreator() {
        creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(dp2px(90));
                // set item title
                openItem.setTitle("编辑");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.mipmap.app__ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        bookList.setMenuCreator(creator);
    }

    private void initClick() {
        bookList.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                MyBook book = books.get(position);
                switch (index) {
                    case 0:
                        // edit
                        edit(book);
                        break;
                    case 1:
                        // delete
                        delete(book);
                        break;
                }
                return false;
            }
        });
        bookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MyBook book = books.get(i);
                Intent intent = new Intent(MyBookActivity.this, BookViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("book", book);
                startActivity(intent);
            }
        });
    }

    private void edit(MyBook book) {
        Intent intent = new Intent(MyBookActivity.this, TransactionActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("book", book);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void delete(MyBook book) {
        if (bookDao.deleteBook(book, user)) {
            Toast.makeText(MyBookActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
            refreshBookList();
        } else {
            Toast.makeText(MyBookActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        refreshBookList();
    }

    private void refreshBookList() {
        books.clear();
        books.addAll(bookDao.getAllData(BmobUser.getCurrentUser(this, User.class)));
        bookAdapter.notifyDataSetChanged();
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}
