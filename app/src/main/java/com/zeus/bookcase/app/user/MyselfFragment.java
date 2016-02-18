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
import com.zeus.bookcase.app.user.adapter.WelfareAdapter;
import com.zeus.bookcase.app.user.interfaces.OnDragStateChangeListener;
import com.zeus.bookcase.app.user.ui.activity.BookCollectionTabActivity;
import com.zeus.bookcase.app.user.ui.activity.ExpressTimeLineActivity;
import com.zeus.bookcase.app.user.ui.activity.LogInActivity;
import com.zeus.bookcase.app.user.ui.activity.UserFavoritesActivity;
import com.zeus.bookcase.app.user.ui.activity.UserShoppingCartActivity;
import com.zeus.bookcase.app.user.ui.activity.UserWelfareActivity;
import com.zeus.bookcase.app.user.view.InboxBackgroundScrollView;
import com.zeus.bookcase.app.user.view.InboxLayoutBase;
import com.zeus.bookcase.app.user.view.InboxLayoutListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zeus_coder on 2016/2/3.
 */
public class MySelfFragment extends Fragment {

    @Bind(R.id.inboxlayout) InboxLayoutListView inboxLayoutListView;
    @Bind(R.id.user_profile_scroll_view) InboxBackgroundScrollView inboxBackgroundScrollView;
    @Bind(R.id.zichan) LinearLayout zichan;
    @Bind(R.id.jifen) LinearLayout jifen;
    @Bind(R.id.tuijian) LinearLayout tuijian;
    @Bind(R.id.member) LinearLayout member;
    @Bind(R.id.choujiang) LinearLayout choujiang;
    @Bind(R.id.diyongquan) LinearLayout diyongquan;
    @Bind(R.id.user_setting) Button setting;
    @Bind(R.id.user_favorites) LinearLayout favorites;
    @Bind(R.id.user_shopping_cart) LinearLayout shoppingCart;
    @Bind(R.id.user_welfare) LinearLayout welfare;
    @Bind(R.id.user_profile_photo) CircleImageView photo;

    @Bind(R.id.user_account_all_order) TextView allOrder;
    @Bind(R.id.user_account_pay) Button payOrder;
    @Bind(R.id.user_account_deliver) Button deliverOrder;
    @Bind(R.id.user_account_receive) Button receiveOrder;
    @Bind(R.id.user_account_command) Button commandOrder;

    @OnClick(R.id.zichan) void openZiChanList() {
        inboxLayoutListView.setAdapter(new WelfareAdapter(getActivity()));
        inboxLayoutListView.openWithAnim(zichan);
    }
    @OnClick(R.id.jifen) void openJiFenList() {
        inboxLayoutListView.setAdapter(new WelfareAdapter(getActivity()));
        inboxLayoutListView.openWithAnim(jifen);
    }
    @OnClick(R.id.tuijian) void openTuiJianList() {
        inboxLayoutListView.setAdapter(new WelfareAdapter(getActivity()));
        inboxLayoutListView.openWithAnim(tuijian);
    }
    @OnClick(R.id.member) void openMemberList() {
        inboxLayoutListView.setAdapter(new WelfareAdapter(getActivity()));
        inboxLayoutListView.openWithAnim(member);
    }
    @OnClick(R.id.choujiang) void openChouJiangList() {
        inboxLayoutListView.setAdapter(new WelfareAdapter(getActivity()));
        inboxLayoutListView.openWithAnim(choujiang);
    }
    @OnClick(R.id.diyongquan) void openDiYongQuanList() {
        inboxLayoutListView.setAdapter(new WelfareAdapter(getActivity()));
        inboxLayoutListView.openWithAnim(diyongquan);
    }
    @OnClick(R.id.user_favorites) void startUserFavoritesActivity() {
        startActivity(new Intent(getActivity(), UserFavoritesActivity.class));
    }
    @OnClick(R.id.user_shopping_cart) void startUserShoppingCartActivity() {
        startActivity(new Intent(getActivity(), UserShoppingCartActivity.class));
    }
    @OnClick(R.id.user_welfare) void startUserWelfareActivity() {
        startActivity(new Intent(getActivity(), UserWelfareActivity.class));
    }
    @OnClick(R.id.user_setting) void startLogInActivity() {
        startActivity(new Intent(getActivity(), LogInActivity.class));
    }
    @OnClick(R.id.user_profile_photo) void startExpressTimeLineActivity() {
        startActivity(new Intent(getActivity(), ExpressTimeLineActivity.class));
    }
    @OnClick(R.id.user_account_all_order) void startBookAllTabActivity() {
        startActivity(new Intent(getActivity(), BookCollectionTabActivity.class).putExtra("position",0));
    }
    @OnClick(R.id.user_account_pay) void startBookPayTabActivity() {
        startActivity(new Intent(getActivity(), BookCollectionTabActivity.class).putExtra("position",1));
    }
    @OnClick(R.id.user_account_deliver) void startBookDeliverTabActivity() {
        startActivity(new Intent(getActivity(), BookCollectionTabActivity.class).putExtra("position",2));
    }
    @OnClick(R.id.user_account_receive) void startBookReceiveTabActivity() {
        startActivity(new Intent(getActivity(), BookCollectionTabActivity.class).putExtra("position",3));
    }
    @OnClick(R.id.user_account_command) void startBookCommandTabActivity() {
        startActivity(new Intent(getActivity(), BookCollectionTabActivity.class).putExtra("position",4));
    }

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
        //initUIView(view);
        initScrollView(view);
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

}
