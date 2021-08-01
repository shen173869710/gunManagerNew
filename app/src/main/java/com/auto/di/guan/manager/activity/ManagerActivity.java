package com.auto.di.guan.manager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.app.BaseApp;
import com.auto.di.guan.manager.basemodel.model.respone.BaseRespone;
import com.auto.di.guan.manager.basemodel.presenter.ManagerPresenter;
import com.auto.di.guan.manager.basemodel.view.IBaseView;
import com.auto.di.guan.manager.db.User;
import com.auto.di.guan.manager.entity.Entiy;
import com.auto.di.guan.manager.event.LoginEvent;
import com.auto.di.guan.manager.event.UserStatusEvent;
import com.auto.di.guan.manager.fragment.ManagerListFragment;
import com.auto.di.guan.manager.rtm.ChatManager;
import com.auto.di.guan.manager.utils.FloatStatusUtil;
import com.auto.di.guan.manager.utils.FloatWindowUtil;
import com.auto.di.guan.manager.utils.LogUtils;
import com.auto.di.guan.manager.utils.ToastUtils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;

public class ManagerActivity extends IBaseActivity<ManagerPresenter> implements IBaseView {

    @BindView(R.id.title_bar_title)
    TextView titleBarTitle;
    @BindView(R.id.title_bar_status)
    TextView titleBarStatus;
//    @BindView(R.id.title_bar_back_layout)
//    RelativeLayout titleBarBackLayout;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    private ChatManager mChatManager;
    private List<User> users = new ArrayList<>();

    private int index = 0;

    @Override
    protected int setLayout() {
        return R.layout.activity_manager;
    }

    @Override
    protected void init() {
        users = (List<User>) getIntent().getSerializableExtra("list");

//        titleBarBackLayout.setVisibility(View.GONE);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        ManagerListFragment managerListFragment = new ManagerListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Entiy.INTENT_USER_LIST, (Serializable) users);
        managerListFragment.setArguments(bundle);
        transaction.add(R.id.manager_item, managerListFragment, "item");
        transaction.commitAllowingStateLoss();

        mChatManager = BaseApp.getInstance().getChatManager();
        Set<String> user = new HashSet<>();
        int size = users.size();
        for (int i = 0; i < size; i++) {
            user.add(users.get(i).getUserId().toString());
        }
        /***登录同时监听在线用户的状态***/
        BaseApp.getInstance().getChatManager().doLogin(user, this);

        titleBarStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRightOnClick();
            }
        });


    }

    @Override
    protected ManagerPresenter createPresenter() {
        return new ManagerPresenter();
    }


    @Override
    public void success(BaseRespone respone) {

    }

    @Override
    public void fail(Throwable error, Integer code, String msg) {

    }


    /**
     * 远程登录相关事件
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginEvent(LoginEvent event) {
        if (event.isLogin()) {
            dismissDialog();
            startActivity(new Intent(ManagerActivity.this, MainActivity.class));
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mChatManager != null) {
            Set<String> user = new HashSet<>();
            int size = users.size();
            for (int i = 0; i < size; i++) {
                user.add(users.get(i).getUserId().toString());
            }
            mChatManager.doLogout(user);
        }
    }

    private long firstTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - firstTime > 2000) {
                ToastUtils.showToast("再按一次退出");
                firstTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    public void setTitle(String str) {
        if (titleBarTitle != null) {
            titleBarTitle.setText(str+"");
        }

    }

    public void setRightInVisible() {
        if (titleBarStatus != null) {
            titleBarStatus.setVisibility(View.GONE);
        }

    }


    public void setRightVisible(int index) {
        if (titleBarStatus != null) {
            titleBarStatus.setVisibility(View.VISIBLE);
        }
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void getRightOnClick() {
        if (index < 2 || index > 4) {
            return;
        }
        Intent intent = new Intent();
        if (index == 2) {
            intent.setClass(ManagerActivity.this, AddLogActivity.class);
        }else if (index == 3) {
            intent.setClass(ManagerActivity.this, AddRaiseActivity.class);
        }else if (index == 4) {
            intent.setClass(ManagerActivity.this, AddApplyActivity.class);
        }
        intent.putExtra(Entiy.INTENT_USER_LIST, (Serializable) users);
        startActivity(intent);
    }

    /**
     *        用户状态变化
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserStatusEvent(UserStatusEvent event) {
        int size = users.size();
        for (int i = 0; i < size; i++) {
            User user = users.get(i);
            if (event.getPeerId().equals(user.getUserId().toString())) {
                user.setLoginStatus(event.getStatus());
                LogUtils.e("ManagerActivity", "用户在线"+new Gson().toJson(event));
            }
        }

    }


}
