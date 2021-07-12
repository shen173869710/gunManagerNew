package com.auto.di.guan.manager.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.adapter.RecyclerListAdapter;
import com.auto.di.guan.manager.app.BaseApp;
import com.auto.di.guan.manager.basemodel.presenter.BasePresenter;
import com.auto.di.guan.manager.db.GroupInfo;
import com.auto.di.guan.manager.dialog.DialogUtil;
import com.auto.di.guan.manager.dialog.OnDialogClick;
import com.auto.di.guan.manager.event.DateChangeEvent;
import com.auto.di.guan.manager.rtm.MessageEntiy;
import com.auto.di.guan.manager.rtm.MessageSend;
import com.auto.di.guan.manager.utils.NoFastClickUtils;
import com.auto.di.guan.manager.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *  设置轮灌相关参数
 */
public class GroupOptionActivity extends IBaseActivity  {

	private static final String TAG = "GroupOptionActivity";
	private View view;
	private TextView textView;
	private TextView title_bar_status;
	private RecyclerView recyclerView;
	private ArrayList<GroupInfo> groupInfos = new ArrayList<>();


	@Override
	protected int setLayout() {
		return R.layout.activity_group_option_layout;
	}

	@Override
	protected void init() {
		view = findViewById(R.id.title_bar);
		groupInfos = BaseApp.getGroupInfos();
		textView = (TextView)view.findViewById(R.id.title_bar_title);
		textView.setText("自动轮灌设置");
		title_bar_status  = (TextView)view.findViewById(R.id.title_bar_status);
		title_bar_status.setVisibility(View.VISIBLE);
		title_bar_status.setText("保存设置");
		title_bar_status.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(NoFastClickUtils.isFastClick()){
					return;
				}
				int size = groupInfos.size();
				HashMap<Integer, Integer> lv = new HashMap<>();
				for (int i = 0; i < size; i++) {
					GroupInfo groupInfo = groupInfos.get(i);
					if (groupInfo.getGroupIsJoin()) {
						if (groupInfo.getGroupTime() == 0 || groupInfo.getGroupLevel() == 0) {
							ToastUtils.showLongToast("轮灌优先级或者轮灌时长不能为0");
							return;
						}
					}else {
						groupInfo.setGroupRunTime(0);
						groupInfo.setGroupLevel(0);
						groupInfo.setGroupTime(0);
					}
					int level = groupInfo.getGroupLevel();
					if (level > 0) {
						if (lv.containsKey(level)) {
							ToastUtils.showLongToast("不能设置相同的轮灌优先级,或者优先级不能为空");
							return;
						}
						lv.put(level,level);
					}
				}

				DialogUtil.setGunLevel(GroupOptionActivity.this, new OnDialogClick() {
					@Override
					public void onDialogOkClick(String value) {
						MessageSend.doGroupLevel(groupInfos);
					}

					@Override
					public void onDialogCloseClick(String value) {

					}
				});

			}
		});
		view.findViewById(R.id.title_bar_back_layout).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				GroupOptionActivity.this.finish();
			}
		});
		recyclerView = (RecyclerView) findViewById(R.id.group_option_view);
		RecyclerListAdapter adapter = new RecyclerListAdapter(groupInfos);
		recyclerView.setHasFixedSize(true);
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
	}

	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onDateChangeEvent(DateChangeEvent event) {
		if (event.getType() == MessageEntiy.TYPE_GROUP_LEVEL) {
			ToastUtils.showLongToast("设置成功");
		}
	}

}
