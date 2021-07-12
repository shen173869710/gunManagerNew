package com.auto.di.guan.manager.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.activity.ChooseGroupctivity;
import com.auto.di.guan.manager.adapter.FragmentTab1Adapter;
import com.auto.di.guan.manager.app.BaseApp;
import com.auto.di.guan.manager.db.ControlInfo;
import com.auto.di.guan.manager.db.DeviceInfo;
import com.auto.di.guan.manager.db.GroupInfo;
import com.auto.di.guan.manager.db.GroupList;
import com.auto.di.guan.manager.dialog.MainShowDialog;
import com.auto.di.guan.manager.event.DateChangeEvent;
import com.auto.di.guan.manager.rtm.MessageSend;
import com.auto.di.guan.manager.utils.LogUtils;
import com.auto.di.guan.manager.utils.NoFastClickUtils;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 控制阀分组界面
 */
public class FragmentTab1 extends BaseFragment {
    @BindView(R.id.expandableListView)
    ExpandableListView expandableListView;
    @BindView(R.id.addBtn)
    Button addBtn;
    @BindView(R.id.delBtn)
    Button delBtn;
    private List<GroupList> groupLists = new ArrayList<>();
    private List<GroupInfo> groupInfos = new ArrayList<>();
    private List<DeviceInfo> deviceInfos = new ArrayList<>();
    private List<ControlInfo> controlInfos = new ArrayList<>();
    private FragmentTab1Adapter adapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_1;
    }

    @Override
    public void init() {
        adapter = new FragmentTab1Adapter(getActivity(), groupLists);
        expandableListView.setAdapter(adapter);
        expandableListView.setGroupIndicator(null);
        initData();
    }

    private void initData() {
        deviceInfos = BaseApp.getDeviceInfos();
        groupInfos = BaseApp.getGroupInfos();

        groupLists.clear();
        controlInfos.clear();
        int size = deviceInfos.size();
        for (int i = 0; i < size; i++) {
            controlInfos.addAll(deviceInfos.get(i).getValveDeviceSwitchList());
        }
        int gSize = groupInfos.size();
        if (gSize > 0) {
            for (int i = 0; i < gSize; i++) {
                GroupInfo groupInfo = groupInfos.get(i);
                int controlSize = controlInfos.size();
                GroupList groupList = new GroupList();
                groupList.controlInfos = new ArrayList<>();
                groupList.groupInfo = groupInfo;
                for (int j = 0; j < controlSize; j++) {
                    if (controlInfos.get(j).getValveGroupId() == groupInfo.getGroupId()) {
                        groupList.controlInfos.add(controlInfos.get(j));
                    }

                }
                groupLists.add(groupList);
            }
        }
        if (adapter != null) {
            adapter.setData(groupLists);
        }
    }

    @OnClick({R.id.addBtn, R.id.delBtn})
    public void onViewClicked(View view) {
        if (NoFastClickUtils.isFastClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.addBtn:
                getActivity().startActivity(new Intent(getActivity(), ChooseGroupctivity.class));
                break;
            case R.id.delBtn:
                MainShowDialog.ShowDialog(getActivity(), "删所有分组除", "当前操作会删除所有的分组", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MessageSend.doDelGroup();
                    }
                });
                break;
        }
    }

    @Override
    public void dataChange(DateChangeEvent event) {
        LogUtils.e("FragmentTab1", "更新组信息");
        initData();
    }
}
