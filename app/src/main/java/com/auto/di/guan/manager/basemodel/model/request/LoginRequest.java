package com.auto.di.guan.manager.basemodel.model.request;

/**
 * 用户登录请求传递参数
 */

public class LoginRequest extends BaseRequest{
    public String loginName;
    public String password;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
