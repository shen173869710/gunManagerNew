package com.auto.di.guan.manager.basemodel.presenter;


import com.auto.di.guan.manager.basemodel.view.BaseView;

public interface Presenter<V extends BaseView> {
    void attachView(V mvpView);
    void detachView();
    void destroy();
}
