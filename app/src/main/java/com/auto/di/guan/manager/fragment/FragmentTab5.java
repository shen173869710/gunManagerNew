package com.auto.di.guan.manager.fragment;

import android.content.Intent;
import android.widget.Button;
import android.widget.ExpandableListView;
import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.activity.GroupStatusActivity;
import com.auto.di.guan.manager.adapter.GroupExpandableListViewaAdapter;
import com.auto.di.guan.manager.app.BaseApp;
import com.auto.di.guan.manager.db.ControlInfo;
import com.auto.di.guan.manager.db.GroupInfo;
import com.auto.di.guan.manager.db.GroupList;
import com.auto.di.guan.manager.db.sql.ControlInfoSql;
import com.auto.di.guan.manager.db.sql.GroupInfoSql;
import com.auto.di.guan.manager.event.DateChangeEvent;
import com.auto.di.guan.manager.rtm.MessageEntiy;
import com.auto.di.guan.manager.rtm.MessageSend;
import com.auto.di.guan.manager.utils.NoFastClickUtils;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
/**
 */
public class FragmentTab5 extends BaseFragment {

	@BindView(R.id.fragment_5_expand)
	ExpandableListView fragment5Expand;
	@BindView(R.id.fragment_5_start)
	Button fragment5Start;

	private List<GroupList> groupLists = new ArrayList<>();
	private  List<GroupInfo> groupInfos = new ArrayList<>();
	private GroupExpandableListViewaAdapter adapter;


	@Override
	public int setLayout() {
		return R.layout.fragment_5;
	}

	@Override
	public void init() {
		adapter = new GroupExpandableListViewaAdapter(getActivity(), groupLists);
		fragment5Expand.setGroupIndicator(null);
		fragment5Expand.setAdapter(adapter);
		initData();
	}

	@OnClick(R.id.fragment_5_start)
	public void onViewClicked() {
		if(NoFastClickUtils.isFastClick()){
			return;
		}
		activity.startActivity(new Intent(activity, GroupStatusActivity.class));
		MessageSend.doActivityEvent(MessageEntiy.TYPE_ACTIVITY_STATUS_START);
	}

	private void initData() {
		groupInfos.clear();
		groupLists.clear();
		groupInfos = BaseApp.getGroupInfos();
		int size = groupInfos.size();
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				List<ControlInfo> clist = ControlInfoSql.queryControlList(groupInfos.get(i).getGroupId());
				if (clist.size() > 0) {
					GroupList list = new GroupList();
					list.groupInfo = groupInfos.get(i);
					list.controlInfos.addAll(clist);
					groupLists.add(list);
				}
			}
			if (adapter != null) {
				adapter.setData(groupLists);
			}
		}
	}

	@Override
	public void dataChange(DateChangeEvent event) {
		initData();
	}

}
