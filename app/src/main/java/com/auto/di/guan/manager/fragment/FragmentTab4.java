package com.auto.di.guan.manager.fragment;

import android.widget.ExpandableListView;
import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.adapter.GroupExpandableListViewaAdapter31;
import com.auto.di.guan.manager.app.BaseApp;
import com.auto.di.guan.manager.db.ControlInfo;
import com.auto.di.guan.manager.db.GroupInfo;
import com.auto.di.guan.manager.db.GroupList;
import com.auto.di.guan.manager.db.sql.ControlInfoSql;
import com.auto.di.guan.manager.event.DateChangeEvent;
import com.auto.di.guan.manager.utils.LogUtils;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

public class FragmentTab4 extends BaseFragment {

    @BindView(R.id.fragment_4_expand)
    ExpandableListView fragment4Expand;

	private List<GroupList> groupLists = new ArrayList<>();
	private  List<GroupInfo> groupInfos;

    private GroupExpandableListViewaAdapter31 adapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_4;
    }

    @Override
    public void init() {

		adapter = new GroupExpandableListViewaAdapter31(activity, groupLists);
		fragment4Expand.setAdapter(adapter);
		fragment4Expand.setGroupIndicator(null);

		groupInfos = BaseApp.getGroupInfos();
		groupLists.clear();
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

	@Override
	public void dataChange(DateChangeEvent event) {
		LogUtils.e("fragmenttab4", "更新数据");
		if (event == null) {
			return;
		}
		init();
//		if (event.getPostion() >= 0 && fragment4Expand != null) {
//			fragment4Expand.expandGroup(event.getPostion());
//		}
	}
}
