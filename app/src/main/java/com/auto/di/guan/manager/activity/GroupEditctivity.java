package com.auto.di.guan.manager.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.adapter.GroupEditListAdapter;
import com.auto.di.guan.manager.db.ControlInfo;
import com.auto.di.guan.manager.db.GroupInfo;
import com.auto.di.guan.manager.db.LevelInfo;
import com.auto.di.guan.manager.db.sql.ControlInfoSql;
import com.auto.di.guan.manager.db.sql.GroupInfoSql;
import com.auto.di.guan.manager.dialog.MainShowDialog;
import com.auto.di.guan.manager.event.DateChangeEvent;
import com.auto.di.guan.manager.event.LoginEvent;
import com.auto.di.guan.manager.rtm.MessageSend;
import com.auto.di.guan.manager.utils.LogUtils;
import com.auto.di.guan.manager.utils.NoFastClickUtils;
import com.auto.di.guan.manager.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**

 */
public class GroupEditctivity extends Activity {
	private Button group_edit_add;
	private View view;
	private TextView textView;
	private RecyclerView group_edit_listview;
	private GroupEditListAdapter adapter;

	private GroupInfo groupInfo;
	private List<ControlInfo> controls = new ArrayList<>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group_layout);

		EventBus.getDefault().register(this);
		view = findViewById(R.id.title_bar);
		textView = (TextView)view.findViewById(R.id.title_bar_title);
		textView.setText("编辑阀门组");
		groupInfo = (GroupInfo) getIntent().getSerializableExtra("groupInfo");

		view.findViewById(R.id.title_bar_back_layout).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				GroupEditctivity.this.finish();
			}
		});

		group_edit_add = (Button)findViewById(R.id.group_edit_add);
		group_edit_add.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(NoFastClickUtils.isFastClick()){
					return;
				}
				Intent intent = new Intent(GroupEditctivity.this, ChooseGroupctivity.class);
				intent.putExtra("groupInfo", groupInfo);
				startActivity(intent);
				GroupEditctivity.this.finish();
			}
		});

		controls = ControlInfoSql.queryControlList(groupInfo.getGroupId());
		group_edit_listview = (RecyclerView) findViewById(R.id.group_edit_listview);
		group_edit_listview.setLayoutManager(new LinearLayoutManager(this));
		adapter = new GroupEditListAdapter(controls);
		group_edit_listview.setAdapter(adapter);
		findViewById(R.id.group_edit_del).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MainShowDialog.ShowDialog(GroupEditctivity.this, "解散分组", "是否解散当前分组", new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						MessageSend.doDissGroup(groupInfo);
					}
				});

			}
		});
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onDataChangeEvent(DateChangeEvent event) {
		LogUtils.e(this.getClass().getSimpleName(), "--------数据更新");
		controls = ControlInfoSql.queryControlList(groupInfo.getGroupId());
		adapter.setNewData(controls);
	}


	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onLoginEvent(LoginEvent event) {
		if (event != null && !event.isLogin()) {
			GroupEditctivity.this.finish();
		}
	}
}
