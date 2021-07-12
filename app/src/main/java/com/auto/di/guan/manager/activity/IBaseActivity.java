package com.auto.di.guan.manager.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;

import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.basemodel.presenter.BasePresenter;
import com.auto.di.guan.manager.basemodel.view.BaseView;
import com.auto.di.guan.manager.dialog.LoadingDialog;
import com.auto.di.guan.manager.event.LoginEvent;
import com.auto.di.guan.manager.utils.LogUtils;
import com.auto.di.guan.manager.utils.ToastUtils;
import com.trello.rxlifecycle3.LifecycleTransformer;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class IBaseActivity<T extends BasePresenter> extends RxAppCompatActivity implements BaseView {

	private LoadingDialog mLoadingDailog;
	protected T mPresenter;
	private Unbinder unbinder;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		}
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(setLayout());
		EventBus.getDefault().register(this);
		unbinder = ButterKnife.bind(this);
		mPresenter = createPresenter();
		if (mPresenter != null) {
			mPresenter.attachView(this);
		}
		init();
	}


	@Override
	public void showDialog() {
		if (null == mLoadingDailog) {
			showWaitingDialog("");
		}
		if (!mLoadingDailog.isShowing()) {
			mLoadingDailog.show();
		}
	}

	/**
	 * 显示等待提示框
	 */
	public Dialog showWaitingDialog(String tip) {
		mLoadingDailog = new LoadingDialog(this, R.style.CustomDialog);
		return mLoadingDailog;
	}

	@Override
	public void dismissDialog() {
		hideWaitingDialog();
	}

	/**
	 * 隐藏等待提示框
	 */
	public void hideWaitingDialog() {
		if (mLoadingDailog != null && mLoadingDailog.isShowing()) {
			mLoadingDailog.dismiss();
			mLoadingDailog = null;
		}
	}

	/**
	 * 设置layout
	 * 
	 * @return
	 */
	protected abstract int setLayout();

	/**
	 * 初始化
	 */
	protected abstract void init();

	protected abstract T createPresenter();


	@Override
	public LifecycleTransformer bindLifecycle() {
		LifecycleTransformer objectLifecycleTransformer = bindToLifecycle();
		return objectLifecycleTransformer;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		try{
			if (mPresenter != null) {
				mPresenter.detachView();
			}
			ActivityStackUtil.remove(this);
			EventBus.getDefault().unregister(this);
			if(null !=unbinder){
				unbinder.unbind();//解绑
			}
		}catch (Exception e){
			LogUtils.e("BaseActivity",e.getMessage());
		}
	}

	@Override
	public Activity getActivity() {
		return this;
	}


	@Subscribe(threadMode = ThreadMode.MAIN)
	public void test(Object event) {

	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onLoginEvent(LoginEvent event) {
		if (event != null && !event.isLogin() && !(this instanceof ManagerActivity)) {
			ToastUtils.showLongToast("被app端踢下线");
			finish();
		}
	}
}
