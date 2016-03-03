package com.zeus.bookcase.app.user;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.user.adapter.DayRecommendAdapter;
import com.zeus.bookcase.app.user.interfaces.OnDragStateChangeListener;
import com.zeus.bookcase.app.user.model.User;
import com.zeus.bookcase.app.user.ui.activity.BookOrderTabActivity;
import com.zeus.bookcase.app.user.ui.activity.ExpressTimeLineActivity;
import com.zeus.bookcase.app.user.ui.activity.LogInActivity;
import com.zeus.bookcase.app.user.ui.activity.MyFortuneActivity;
import com.zeus.bookcase.app.user.ui.activity.UserShoppingCartActivity;
import com.zeus.bookcase.app.user.ui.activity.UserWelfareActivity;
import com.zeus.bookcase.app.user.view.InboxBackgroundScrollView;
import com.zeus.bookcase.app.user.view.InboxLayoutBase;
import com.zeus.bookcase.app.user.view.InboxLayoutListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zeus_coder on 2016/2/3.
 */
public class MySelfFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.inboxlayout) InboxLayoutListView inboxLayoutListView;
    @Bind(R.id.user_profile_scroll_view) InboxBackgroundScrollView inboxBackgroundScrollView;
    @Bind(R.id.fortune) LinearLayout fortune;
    @Bind(R.id.points) LinearLayout points;
    @Bind(R.id.recommend) LinearLayout recommend;
    @Bind(R.id.user_setting_or_login) Button settingOrLogin;
    @Bind(R.id.user_favorites) LinearLayout favorites;
    @Bind(R.id.user_shopping_cart) LinearLayout shoppingCart;
    @Bind(R.id.user_welfare) LinearLayout welfare;


    @Bind(R.id.user_account_all_order) TextView allOrder;
    @Bind(R.id.user_account_pay) Button payOrder;
    @Bind(R.id.user_account_deliver) Button deliverOrder;
    @Bind(R.id.user_account_receive) Button receiveOrder;
    @Bind(R.id.user_account_command) Button commandOrder;

    //用户信息
    @Bind(R.id.user_profile_photo) CircleImageView photo;
    @Bind(R.id.user_nick_name) TextView nickName;
    @Bind(R.id.user_level) TextView level;
    @Bind(R.id.user_favorites_number) TextView favoriteNumber;
    @Bind(R.id.user_shopping_cart_number) TextView cartNumber;
    @Bind(R.id.user_welfare_number) TextView welfareNumber;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user__fragment_profile, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        User user = BmobUser.getCurrentUser(getActivity(), User.class);
        initUIView(user);
        initScrollView(view);
        initData(user);
    }

    private void initData(User user) {
        if (user != null) {
            settingOrLogin.setText("设置");
        } else {
            settingOrLogin.setText("登录");
        }
    }

    private void initUIView(User user) {
        if (user != null) {
            fortune.setOnClickListener(this);
            recommend.setOnClickListener(this);
            favorites.setOnClickListener(this);
            shoppingCart.setOnClickListener(this);
            welfare.setOnClickListener(this);
            photo.setOnClickListener(this);
            allOrder.setOnClickListener(this);
            payOrder.setOnClickListener(this);
            deliverOrder.setOnClickListener(this);
            receiveOrder.setOnClickListener(this);
            commandOrder.setOnClickListener(this);
        }
        settingOrLogin.setOnClickListener(this);
    }

    private void initScrollView(View view) {
        inboxLayoutListView.setBackgroundScrollView(inboxBackgroundScrollView);//绑定scrollview
        inboxLayoutListView.setCloseDistance(50);
        inboxLayoutListView.setOnDragStateChangeListener(new OnDragStateChangeListener() {
            @Override
            public void dragStateChange(InboxLayoutBase.DragState state) {
                switch (state) {
                    case CANCLOSE:
                        //设置标题
                        break;
                    case CANNOTCLOSE:
                        //设置标题
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.user_favorites:
                goUserFavoritesActivity();
                break;
            case R.id.user_shopping_cart:
                goUserShoppingCartActivity();
                break;
            case R.id.user_welfare:
                goUserWelfareActivity();
                break;
            case R.id.user_profile_photo:
                goExpressTimeLineActivity();
                break;
            case R.id.user_setting_or_login:
                goLoginActivity();
                break;
            case R.id.user_account_all_order:
                goBookOrderTabActivity(0);
                break;
            case R.id.user_account_pay:
                goBookOrderTabActivity(1);
                break;
            case R.id.user_account_deliver:
                goBookOrderTabActivity(2);
                break;
            case R.id.user_account_receive:
                goBookOrderTabActivity(3);
                break;
            case R.id.user_account_command:
                goBookOrderTabActivity(4);
                break;
            case R.id.fortune:
                goMyFortuneActivity();
                break;
            case R.id.recommend:
                openRecommendList();
                break;
        }
    }

    private void goUserFavoritesActivity() {
        //startActivity(new Intent(getActivity(), UserFavoritesActivity.class));
        BmobUser.logOut(getActivity());
    }

    private void goUserShoppingCartActivity() {
        startActivity(new Intent(getActivity(), UserShoppingCartActivity.class));
    }

    private void goUserWelfareActivity() {
        startActivity(new Intent(getActivity(), UserWelfareActivity.class));
    }

    private void goExpressTimeLineActivity() {
        startActivity(new Intent(getActivity(), ExpressTimeLineActivity.class));
    }

    private void goLoginActivity() {
        startActivity(new Intent(getActivity(), LogInActivity.class));
    }

    private void goBookOrderTabActivity(int position) {
        startActivity(new Intent(getActivity(), BookOrderTabActivity.class).putExtra("position",position));
    }

    private void goMyFortuneActivity() {
        startActivity(new Intent(getActivity(), MyFortuneActivity.class));
    }

    private void openRecommendList() {
        inboxLayoutListView.setAdapter(new DayRecommendAdapter(getActivity()));
        inboxLayoutListView.openWithAnim(recommend);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
