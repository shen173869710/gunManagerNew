package com.auto.di.guan.manager.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.auto.di.guan.manager.event.DateChangeEvent;
import com.auto.di.guan.manager.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;
/**
 * Created by Administrator on 2017/7/16.
 */
public abstract class BaseFragment extends Fragment{

    public Activity activity;
    private Unbinder mUnbinder; //用于解除 butterknife 的绑定
    public View mRootView;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(setLayout(), container, false);//和 BaseActivity 一样，layout() 由子类实现，提供布局。
        mUnbinder=  ButterKnife.bind(this, mRootView);//同样把 ButterKnife 抽出来
        EventBus.getDefault().register(this);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    public abstract int setLayout();  //提供给子类实现的抽象类
    public abstract void  init();

    public abstract void dataChange(DateChangeEvent event);

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mUnbinder != Unbinder.EMPTY){
            mUnbinder.unbind();
        }
        EventBus.getDefault().unregister(this);
    }


    public void showToast(String message) {
        Toast.makeText(this.getActivity(),message,Toast.LENGTH_LONG).show();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDataChangeEvent(DateChangeEvent event) {
        LogUtils.e(this.getClass().getSimpleName(), "--------数据更新");
        dataChange(event);
    }

}
