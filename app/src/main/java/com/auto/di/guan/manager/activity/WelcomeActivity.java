package com.auto.di.guan.manager.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.app.BaseApp;

public class WelcomeActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		// 如果数据表里面没有用户信息
		if (BaseApp.getUser() != null) {
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
					finish();
				}
			},2000);
		}else {

		}
	}


}
