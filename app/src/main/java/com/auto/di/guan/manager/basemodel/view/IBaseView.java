package com.auto.di.guan.manager.basemodel.view;


import com.auto.di.guan.manager.basemodel.model.respone.BaseRespone;

/**
 * Created by czl on 2019/7/9.
 * 登录页面抽象的接口
 */

public interface IBaseView extends BaseView{

    /***登录成功**/
    void success(BaseRespone respone);
    /***登录失败**/
    void fail(Throwable error, Integer code, String msg);
}
