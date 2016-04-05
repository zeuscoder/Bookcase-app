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
import android.widget.Toast;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.home.model.dao.CartDao;
import com.zeus.bookcase.app.home.model.dao.FavoriteDao;
import com.zeus.bookcase.app.user.adapter.DayRecommendAdapter;
import com.zeus.bookcase.app.user.interfaces.OnDragStateChangeListener;
import com.zeus.bookcase.app.user.model.User;
import com.zeus.bookcase.app.user.model.dao.AddressDao;
import com.zeus.bookcase.app.user.ui.activity.AddressActivity;
import com.zeus.bookcase.app.user.ui.activity.BookOrderTabActivity;
import com.zeus.bookcase.app.user.ui.activity.ExpressTimeLineActivity;
import com.zeus.bookcase.app.user.ui.activity.LogInActivity;
import com.zeus.bookcase.app.user.ui.activity.MyFortuneActivity;
import com.zeus.bookcase.app.user.ui.activity.SettingsActivity;
import com.zeus.bookcase.app.user.ui.activity.UserFavoritesActivity;
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


    @Bind(R.id.user_account_all_order) LinearLayout allOrder;
    @Bind(R.id.user_all_address) LinearLayout allAddress;
//    @Bind(R.id.user_account_pay) Button payOrder;
//    @Bind(R.id.user_account_deliver) Button deliverOrder;
//    @Bind(R.id.user_account_receive) Button receiveOrder;
//    @Bind(R.id.user_account_command) Button commandOrder;

    @Bind(R.id.user_all_order_count) TextView allOrderCount;
    @Bind(R.id.user_all_address_count) TextView allAddressCount;

    //用户信息
    @Bind(R.id.user_profile_photo) CircleImageView photo;
    @Bind(R.id.user_nick_name) TextView nickName;
    @Bind(R.id.user_level) TextView level;
    @Bind(R.id.user_favorites_number) TextView favoriteNumber;
    @Bind(R.id.user_shopping_cart_number) TextView cartNumber;
    @Bind(R.id.user_welfare_number) TextView welfareNumber;

    private boolean FLAG = false;
    private User user;
    private AddressDao addressDao;
    private CartDao cartDao;
    private FavoriteDao favoriteDao;

    private final int LOGINREQUESTCODE = 100;
    private final int EXITREQUESTCODE = 101;

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
        initUIView();
        initScrollView();
    }

    @Override
    public void onStart() {
        super.onStart();
        favoriteDao = new FavoriteDao(getActivity());
        addressDao = new AddressDao(getActivity());
        cartDao = new CartDao(getActivity());
        user = BmobUser.getCurrentUser(getActivity(), User.class);
        initData();
    }

    private void initData() {
        if (user != null) {
            setDataWithUser();
        } else {
            setDataWithoutUser();
        }
    }

    private void setDataWithUser() {
        nickName.setText(user.getNickName());
        settingOrLogin.setText("设置");
        level.setVisibility(View.VISIBLE);
        photo.setImageResource(R.mipmap.user__profile_photo);

        allOrderCount.setText(String.valueOf(1));
        favoriteNumber.setText(String.valueOf(favoriteDao.getCount(user)));
        cartNumber.setText(String.valueOf(cartDao.getCount(user)));
        allAddressCount.setText(String.valueOf(addressDao.getCount(user)));
    }

    private void setDataWithoutUser() {
        nickName.setText("柜客");
        settingOrLogin.setText("登录");
        level.setVisibility(View.INVISIBLE);
        photo.setImageResource(R.mipmap.user__normal_photo);
        cartNumber.setText(String.valueOf(0));
        favoriteNumber.setText(String.valueOf(0));
        allOrderCount.setText(String.valueOf(0));
        allAddressCount.setText(String.valueOf(0));
    }

    private void initUIView() {
        fortune.setOnClickListener(this);
        recommend.setOnClickListener(this);
        favorites.setOnClickListener(this);
        shoppingCart.setOnClickListener(this);
        welfare.setOnClickListener(this);
        photo.setOnClickListener(this);
        allOrder.setOnClickListener(this);
        allAddress.setOnClickListener(this);
//        payOrder.setOnClickListener(this);
//        deliverOrder.setOnClickListener(this);
//        receiveOrder.setOnClickListener(this);
//        commandOrder.setOnClickListener(this);
        settingOrLogin.setOnClickListener(this);
    }

    private void initScrollView() {
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
        if(user == null) {
//            Toast.makeText(getActivity(), "还未登录", Toast.LENGTH_SHORT).show();
            goLoginActivity();
        } else {
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
                case R.id.user_all_address:
                    goAddressActivity();
                    break;
//                case R.id.user_account_pay:
//                    goBookOrderTabActivity(1);
//                    break;
//                case R.id.user_account_deliver:
//                    goBookOrderTabActivity(2);
//                    break;
//                case R.id.user_account_receive:
//                    goBookOrderTabActivity(3);
//                    break;
//                case R.id.user_account_command:
//                    goBookOrderTabActivity(4);
//                    break;
                case R.id.fortune:
                    goMyFortuneActivity();
                    break;
                case R.id.recommend:
                    openRecommendList();
                    break;
            }
        }
    }

    private void goAddressActivity() {
        startActivity(new Intent(getActivity(), AddressActivity.class));
    }

    private void goUserFavoritesActivity() {
        startActivity(new Intent(getActivity(), UserFavoritesActivity.class));
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
        if (user != null) {
            startActivityForResult(new Intent(getActivity(), SettingsActivity.class), EXITREQUESTCODE);
        } else {
            startActivityForResult(new Intent(getActivity(), LogInActivity.class), LOGINREQUESTCODE);
        }
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
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == 0) {
            switch (requestCode) {
                case LOGINREQUESTCODE:
//                    Bundle data = intent.getExtras();
//                    user = (User) data.getSerializable("user");
                    break;
                case EXITREQUESTCODE:

                    break;
            }
        }
    }
}
