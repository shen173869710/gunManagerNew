package com.auto.di.guan.manager.basemodel.model.respone;

import com.auto.di.guan.manager.db.User;
import java.util.List;

/**
 *   用户登录返回相关数据
 */
public class LoginRespone {
    private User loginUser;
    private List<User> memberList;

    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }

    public List<User> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<User> memberList) {
        this.memberList = memberList;
    }
}
