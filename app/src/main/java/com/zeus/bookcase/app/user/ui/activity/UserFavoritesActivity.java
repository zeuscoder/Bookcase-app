package com.zeus.bookcase.app.user.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;
import com.zeus.bookcase.app.cabinet.model.MyBook;
import com.zeus.bookcase.app.cabinet.ui.activity.BookViewActivity;
import com.zeus.bookcase.app.home.model.Favorite;
import com.zeus.bookcase.app.home.model.dao.FavoriteDao;
import com.zeus.bookcase.app.home.ui.activity.BookIntroductionActivity;
import com.zeus.bookcase.app.user.adapter.FavoriteAdapter;
import com.zeus.bookcase.app.user.model.User;

import java.util.List;

import cn.bmob.v3.BmobUser;


/**
 * Created by zeus_coder on 2016/1/10.
 */
public class UserFavoritesActivity extends BaseActivity {

    private SwipeMenuListView favoriteList;
    private SwipeMenuCreator creator;

    private FavoriteDao favoriteDao;
    private List<Favorite> favorites;
    private FavoriteAdapter favoriteAdapter;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user__activity_favorites);
        initTopButton(R.string.activity_favorites, R.mipmap.app__top_bar_arrow_back, 0);

        initViews();
        initData();
        initCreator();
        initClick();
    }

    private void initViews() {
        favoriteList = (SwipeMenuListView) findViewById(R.id.list_favorites);
    }

    private void initData() {
        favoriteDao = new FavoriteDao(this);
        user = BmobUser.getCurrentUser(this, User.class);
        favorites = favoriteDao.getAllData(user);
        if (favorites != null) {
            favoriteAdapter = new FavoriteAdapter(this, favorites);
            favoriteList.setAdapter(favoriteAdapter);
        } else {
            Toast.makeText(UserFavoritesActivity.this, "收藏夹空空的", Toast.LENGTH_SHORT).show();
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
                openItem.setBackground(R.color.app_background_dark);
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
        favoriteList.setMenuCreator(creator);
    }

    private void initClick() {
        favoriteList.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                Favorite favorite = favorites.get(position);
                switch (index) {
                    case 0:
                        // edit
                        edit(favorite);
                        break;
                    case 1:
                        // delete
                        delete(favorite);
                        break;
                }
                return false;
            }
        });
        favoriteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Favorite favorite = favorites.get(i);
                Intent intent = new Intent(UserFavoritesActivity.this, BookIntroductionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("bookId", favorite.getBid().toString());
                startActivity(intent);
            }
        });
    }

    private void delete(Favorite favorite) {
        if (favoriteDao.deleteFavorite(favorite, user)) {
            Toast.makeText(UserFavoritesActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
            refreshFavoritesList();
        } else {
            Toast.makeText(UserFavoritesActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void edit(Favorite favorite) {

    }

    private void refreshFavoritesList() {
        favorites.clear();
        favorites.addAll(favoriteDao.getAllData(BmobUser.getCurrentUser(this, User.class)));
        favoriteAdapter.notifyDataSetChanged();
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}
