package com.auto.di.guan.manager.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;
import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.app.BaseApp;
import com.auto.di.guan.manager.basemodel.model.respone.BaseRespone;
import com.auto.di.guan.manager.basemodel.model.respone.LoginRespone;
import com.auto.di.guan.manager.basemodel.presenter.LoginPresenter;
import com.auto.di.guan.manager.basemodel.view.IBaseView;
import com.auto.di.guan.manager.customview.XEditText;
import com.auto.di.guan.manager.db.User;
import com.auto.di.guan.manager.utils.FloatStatusUtil;
import com.auto.di.guan.manager.utils.FloatWindowUtil;
import com.auto.di.guan.manager.utils.ToastUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends IBaseActivity<LoginPresenter> implements IBaseView {
    @BindView(R.id.login_name)
    XEditText loginName;
    @BindView(R.id.login_pwd)
    XEditText loginPwd;
    @BindView(R.id.login)
    Button login;

    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }


    @Override
    public void success(BaseRespone respone) {
        LoginRespone loginRespone = (LoginRespone) respone.getData();
        if (loginRespone != null && loginRespone.getLoginUser() != null) {
            BaseApp.setUser(loginRespone.getLoginUser());
//            List<User>users = loginRespone.getMemberList();
            List<User>users = new ArrayList<>();
            users.addAll(loginRespone.getMemberList());
//            users.addAll(loginRespone.getMemberList());
//            users.addAll(loginRespone.getMemberList());
            Intent intent = new Intent(LoginActivity.this, ManagerActivity.class);
            if (users == null) {
                users = new ArrayList<>();
            }
            intent.putExtra("list", (Serializable)users);
            startActivity(intent);
            finish();
        }else {
            ToastUtils.showLongToast(respone.getMessage()+"");
        }
    }

    @Override
    public void fail(Throwable error, Integer code, String msg) {
        ToastUtils.showLongToast(msg+"");
    }


    @OnClick(R.id.login)
    public void onViewClicked() {
        String name = loginName.getText().toString().trim();
//        name = "18545678903";
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(LoginActivity.this, "请输入账号", Toast.LENGTH_LONG).show();
            return;
        }
        String pwd = loginPwd.getText().toString().trim();
//        pwd = "123456";
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_LONG).show();
            return;
        }
        mPresenter.doLogin(name, pwd);
    }
}
