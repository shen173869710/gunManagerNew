package com.auto.di.guan.manager.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.adapter.ChooseGridAdapter;
import com.auto.di.guan.manager.db.ControlInfo;
import com.auto.di.guan.manager.db.DeviceInfo;
import com.auto.di.guan.manager.db.GroupInfo;
import com.auto.di.guan.manager.db.sql.DeviceSql;
import com.auto.di.guan.manager.dialog.MainShowDialog;
import com.auto.di.guan.manager.entity.Entiy;
import com.auto.di.guan.manager.event.DateChangeEvent;
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
 *
 */
public class ChooseGroupctivity extends Activity {
	private RecyclerView recyclerView;
	private ChooseGridAdapter adapter;
	private ArrayList<DeviceInfo> deviceInfos = new ArrayList<>();
	private GroupInfo groupInfo;
	View view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_group_layout);
		EventBus.getDefault().register(this);
		groupInfo = (GroupInfo) getIntent().getSerializableExtra("groupInfo");
		view = findViewById(R.id.title_bar);
		((TextView)view.findViewById(R.id.title_bar_title)).setText("设备分组");
		view.findViewById(R.id.title_bar_back_layout).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		TextView textView = view.findViewById(R.id.title_bar_status);
		recyclerView = findViewById(R.id.choose_gridview);
		deviceInfos = DeviceSql.getAllDevice();

//		for (int i = 0; i < deviceInfos.size(); i++) {
//			LogUtils.e("i----", ""+i);
//			LogUtils.e("0----", ""+deviceInfos.get(i).getValveDeviceSwitchList().get(0).isSelect());
//			LogUtils.e("1----", ""+deviceInfos.get(i).getValveDeviceSwitchList().get(1).isSelect());
//		}
		adapter = new ChooseGridAdapter( deviceInfos);
		LinearLayoutManager manager = new GridLayoutManager(this, Entiy.GRID_COLUMNS);
		recyclerView.setLayoutManager(manager);
		recyclerView.setAdapter(adapter);
		textView.setVisibility(View.VISIBLE);
		textView.setText("保存分组");
		textView.setTextColor(getResources().getColor(R.color.white));
		textView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(NoFastClickUtils.isFastClick()){
					return;
				}
				int count = 0;
				int size = deviceInfos.size();

				List<ControlInfo> list = new ArrayList<>();
				for (int i = 0; i < size; i++) {
					DeviceInfo deviceInfo = deviceInfos.get(i);
					ControlInfo controlInfo0 = deviceInfo.getValveDeviceSwitchList().get(0);
					ControlInfo controlInfo1 = deviceInfo.getValveDeviceSwitchList().get(1);
					if (controlInfo0.isSelect()) {
						list.add(controlInfo0);
					}
					if (controlInfo1.isSelect()) {
						list.add(controlInfo1);
					}
				}

				count = list.size();
				if (count == 0) {
					Toast.makeText(ChooseGroupctivity.this, "没有选中设备",Toast.LENGTH_LONG).show();
					return;
				}

				MainShowDialog.ShowDialog(ChooseGroupctivity.this, "创建分组", "是否创建当前分组", new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						MessageSend.doCreateGroup(groupInfo, deviceInfos);
					}
				});

			}
		});
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onDataChangeEvent(DateChangeEvent event) {
		LogUtils.e(this.getClass().getSimpleName(), "--------数据更新");
		ToastUtils.showLongToast("创建分组成功");
		deviceInfos = DeviceSql.getAllDevice();
		adapter.setNewData(deviceInfos);
	}
}
