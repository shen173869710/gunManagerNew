package com.auto.di.guan.manager.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.activity.GroupOptionActivity;
import com.auto.di.guan.manager.activity.GroupStatusActivity;
import com.auto.di.guan.manager.activity.OptionSettingActivity;
import com.auto.di.guan.manager.adapter.GroupExpandableListViewaAdapter;
import com.auto.di.guan.manager.db.GroupInfo;
import com.auto.di.guan.manager.db.GroupList;
import com.auto.di.guan.manager.event.DateChangeEvent;
import com.auto.di.guan.manager.utils.LogUtils;
import com.auto.di.guan.manager.utils.NoFastClickUtils;
import org.greenrobot.eventbus.EventBus;
import java.util.ArrayList;
import java.util.List;


public class FragmentTab32 extends BaseFragment {
	private View view;
	private ExpandableListView expandableListView;
	private List<GroupList> groupLists = new ArrayList<>();
	private  List<GroupInfo> groupInfos = new ArrayList<>();
	private GroupExpandableListViewaAdapter adapter;
	private Button fragment_3_2_edit;
	private Button fragment_3_2_stop;
	private Button fragment_3_2_start;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_3_2, null);

		expandableListView =(ExpandableListView)view.findViewById(R.id.fragment_3_2_expand);
		adapter = new GroupExpandableListViewaAdapter(getActivity(), groupLists);
		expandableListView.setGroupIndicator(null);
		expandableListView.setAdapter(adapter);
		expandableListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getActivity(), OptionSettingActivity.class);
				intent.putExtra("id",groupLists.get(position).groupInfo.getGroupId());
				startActivity(intent);
			}
		});
		fragment_3_2_start = (Button) view.findViewById(R.id.fragment_3_2_start);
		fragment_3_2_start.setVisibility(View.GONE);
		fragment_3_2_start.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(NoFastClickUtils.isFastClick()){
					return;
				}
//				MainShowDialog.ShowDialog(getActivity(), "开启轮灌组", "是否开启轮灌组轮灌操作", new View.OnClickListener() {
//					@Override
//					public void onClick(View v) {
//                        int size = groupInfos.size();
//                        for (int i = 0; i < size; i++) {
//                            doRun(true,groupInfos.get(i));
//                        }
//					}
//				});
			}
		});

		fragment_3_2_edit = (Button) view.findViewById(R.id.fragment_3_2_edit);
		fragment_3_2_edit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				if (MyApplication.getInstance().isGroupStart()) {
//					return;
//				}
				if(NoFastClickUtils.isFastClick()){
					return;
				}
				activity.startActivity(new Intent(activity, GroupOptionActivity.class));

			}
		});

		fragment_3_2_stop = (Button) view.findViewById(R.id.fragment_3_2_status);
		fragment_3_2_stop.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(NoFastClickUtils.isFastClick()){
					return;
				}
				activity.startActivity(new Intent(activity, GroupStatusActivity.class));

			}
		});
		EventBus.getDefault().register(this);
		LogUtils.e("fragmnet31", "time == "+System.currentTimeMillis());
		return view;
	}








	@Override
	public int setLayout() {
		return 0;
	}

	@Override
	public void init() {

	}

	@Override
	public void dataChange(DateChangeEvent event) {

	}


	@Override
	public void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}


}
