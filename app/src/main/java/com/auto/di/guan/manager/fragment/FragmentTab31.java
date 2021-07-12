package com.auto.di.guan.manager.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.adapter.GroupExpandableListViewaAdapter31;
import com.auto.di.guan.manager.db.GroupInfo;
import com.auto.di.guan.manager.db.GroupList;
import com.auto.di.guan.manager.event.DateChangeEvent;
import com.auto.di.guan.manager.utils.LogUtils;
import org.greenrobot.eventbus.EventBus;
import java.util.ArrayList;
import java.util.List;


public class FragmentTab31 extends BaseFragment {

	private View view;
	private ExpandableListView expandableListView;
	private GroupExpandableListViewaAdapter31 adapter;
	private List<GroupList> groupLists = new ArrayList<>();
	private  List<GroupInfo> groupInfos = new ArrayList<>();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_3_1, null);
		expandableListView =(ExpandableListView)view.findViewById(R.id.fragment_3_1_expand);
		adapter = new GroupExpandableListViewaAdapter31(getActivity(), groupLists);
		expandableListView.setAdapter(adapter);
		expandableListView.setGroupIndicator(null);
		EventBus.getDefault().register(this);
		initData();
		LogUtils.e("fragmnet31", "time == "+System.currentTimeMillis());
		return view;
	}



	public void startWork(final GroupInfo groupInfo) {
		String title;
		if (groupInfo.getGroupStatus() == 0) {
			title = "当前分组处于关闭状态";
		}else {
			title = "当前分组处于运行状态";
		}

//		Main31Dialog.ShowDialog(getActivity(),title, new Main31Dialog.ItemClick(){
//
//			@Override
//			public void onItemClick(int index) {
//				if (index == 1) {
//					TaskFactory.createGroupOpenTask(groupInfo);
//					TaskManager.getInstance().startTask();
//				}else if (index == 2) {
//					TaskFactory.createGroupCloseTask(groupInfo);
//					TaskManager.getInstance().startTask();
//				}
//			}
//		});
	}

	public void initData() {
//		groupInfos.clear();
//		groupLists.clear();
//		int size = groupInfos.size();
//		if (size > 0) {
//			for (int i = 0; i < size; i++) {
//				List<ControlInfo>clist = ControlInfoSql.queryControlList(groupInfos.get(i).getGroupId());
//				if (clist.size() > 0) {
//					GroupList list = new GroupList();
//					list.groupInfo = groupInfos.get(i);
//					list.controlInfos.addAll(clist);
//					groupLists.add(list);
//				}
//			}
//
//			if (adapter != null) {
//				adapter.setData(groupLists);
//			}
//		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
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

//	/**
//	 *        自动轮灌组状态更新
//	 * @param event
//	 */
//	@Subscribe(threadMode = ThreadMode.MAIN)
//	public void onGroupStatusEvent(GroupStatusEvent event) {
//		if (adapter != null) {
//			initData();
//		}
//	};


//	/**
//	 *   接收自动轮灌相关操作
//	 */
//	@Subscribe(threadMode = ThreadMode.MAIN)
//	public void onAutoTaskEvent(AutoTaskEvent event) {
//		if (event != null) {
//			if (event.getType() == Entiy.RUN_DO_FINISH) {
//				if (adapter != null) {
//					initData();
//				}
//			}
//		}
//	}

}
