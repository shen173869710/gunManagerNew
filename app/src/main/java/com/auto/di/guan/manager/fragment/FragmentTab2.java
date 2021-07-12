package com.auto.di.guan.manager.fragment;

import android.content.Intent;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.activity.GroupOptionActivity;
import com.auto.di.guan.manager.adapter.GroupExpandableListViewaAdapter;
import com.auto.di.guan.manager.app.BaseApp;
import com.auto.di.guan.manager.db.ControlInfo;
import com.auto.di.guan.manager.db.GroupInfo;
import com.auto.di.guan.manager.db.GroupList;
import com.auto.di.guan.manager.db.sql.ControlInfoSql;
import com.auto.di.guan.manager.event.DateChangeEvent;
import com.auto.di.guan.manager.utils.NoFastClickUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class FragmentTab2 extends BaseFragment {


    @BindView(R.id.fragment_2_expand)
    ExpandableListView fragment2Expand;
    @BindView(R.id.fragment_2_setting)
    Button fragment2Setting;

	private List<GroupList> groupLists = new ArrayList<>();
	private  List<GroupInfo> groupInfos = new ArrayList<>();
	private GroupExpandableListViewaAdapter adapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_2;
    }

    @Override
    public void init() {
		adapter = new GroupExpandableListViewaAdapter(getActivity(), groupLists);
		fragment2Expand.setGroupIndicator(null);
		fragment2Expand.setAdapter(adapter);
//		fragment2Expand.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				Intent intent = new Intent(getActivity(), OptionSettingActivity.class);
//				intent.putExtra("id",groupLists.get(position).groupInfo.getGroupId());
//				startActivity(intent);
//			}
//		});
		initData();
    }

	private void initData() {
		groupInfos.clear();
		groupLists.clear();
		groupInfos = BaseApp.getGroupInfos();
		int size = groupInfos.size();
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				List<ControlInfo>clist = ControlInfoSql.queryControlList(groupInfos.get(i).getGroupId());
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

    @OnClick(R.id.fragment_2_setting)
    public void onViewClicked() {
		if(NoFastClickUtils.isFastClick()){
			return;
		}
		activity.startActivity(new Intent(activity, GroupOptionActivity.class));
    }

	@Override
	public void dataChange(DateChangeEvent event) {
		initData();
	}
}
