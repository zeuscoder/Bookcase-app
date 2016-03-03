package com.zeus.bookcase.app.user.ui.activity;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.user.ui.BookRecommendDetailFragment;
import com.zeus.menu.ContextMenuDialogFragment;
import com.zeus.menu.MenuObject;
import com.zeus.menu.MenuParams;
import com.zeus.menu.interfaces.OnMenuItemClickListener;
import com.zeus.menu.interfaces.OnMenuItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeus_coder on 2016/2/26.
 */
public class BookRecommendDetailActivity extends AppCompatActivity implements OnMenuItemClickListener,OnMenuItemLongClickListener{

    private FragmentManager fragmentManager;
    private DialogFragment mMenuDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user__activity_book_recommend_detail);
        fragmentManager = getSupportFragmentManager();
        initToolbar();
        initMenuFragment();
        addFragment(new BookRecommendDetailFragment(), true, R.id.book_recommend_detail_container);
    }

    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        if(mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setTitle(null);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            mToolbar.setNavigationIcon(R.mipmap.app__top_bar_arrow_back);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    private void initMenuFragment() {
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.tool_bar_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
    }

    private List<MenuObject> getMenuObjects() {
        // You can use any [resource, bitmap, drawable, color] as image:
        // item.setResource(...)
        // item.setBitmap(...)
        // item.setDrawable(...)
        // item.setColor(...)
        // You can set image ScaleType:
        // item.setScaleType(ScaleType.FIT_XY)
        // You can use any [resource, drawable, color] as background:
        // item.setBgResource(...)
        // item.setBgDrawable(...)
        // item.setBgColor(...)
        // You can use any [color] as text color:
        // item.setTextColor(...)
        // You can set any [color] as divider color:
        // item.setDividerColor(...)

        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject();
        close.setResource(R.mipmap.user__ic_close);

        MenuObject send = new MenuObject("Send message");
        send.setResource(R.mipmap.user__ic_detail1);

        MenuObject like = new MenuObject("Like profile");
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.mipmap.user__ic_detail2);
        like.setBitmap(b);

        MenuObject addFr = new MenuObject("Add to friends");
        BitmapDrawable bd = new BitmapDrawable(getResources(),
                BitmapFactory.decodeResource(getResources(), R.mipmap.user__ic_detail3));
        addFr.setDrawable(bd);

        MenuObject addFav = new MenuObject("Add to favorites");
        addFav.setResource(R.mipmap.user__ic_detail4);

        MenuObject block = new MenuObject("Block user");
        block.setResource(R.mipmap.user__ic_detail5);

        menuObjects.add(close);
        menuObjects.add(send);
        menuObjects.add(like);
        menuObjects.add(addFr);
        menuObjects.add(addFav);
        menuObjects.add(block);
        return menuObjects;
    }

    protected void addFragment(Fragment fragment, boolean addToBackStack, int containerId) {
        invalidateOptionsMenu();
        String backStackName = fragment.getClass().getName();
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStackName, 0);
        if (!fragmentPopped) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(containerId, fragment, backStackName)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            if (addToBackStack)
                transaction.addToBackStack(backStackName);
            transaction.commit();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.context_menu) {
            if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
                mMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mMenuDialogFragment != null && mMenuDialogFragment.isAdded()) {
            mMenuDialogFragment.dismiss();
        } else{
            finish();
        }
    }

    /*
    * 设置相对应的函数
    * */
    @Override
    public void onMenuItemClick(View clickedView, int position) {
        Toast.makeText(this, "Clicked on position: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMenuItemLongClick(View clickedView, int position) {
        Toast.makeText(this, "Long clicked on position: " + position, Toast.LENGTH_SHORT).show();
    }

}
