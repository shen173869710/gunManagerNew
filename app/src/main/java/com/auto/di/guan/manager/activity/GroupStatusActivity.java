package com.auto.di.guan.manager.activity;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.adapter.GroupStatusAdapter;
import com.auto.di.guan.manager.adapter.StatusAdapter;
import com.auto.di.guan.manager.basemodel.presenter.BasePresenter;
import com.auto.di.guan.manager.db.ControlInfo;
import com.auto.di.guan.manager.db.GroupInfo;
import com.auto.di.guan.manager.db.sql.ControlInfoSql;
import com.auto.di.guan.manager.db.sql.GroupInfoSql;
import com.auto.di.guan.manager.dialog.GroupOptionDialog;
import com.auto.di.guan.manager.event.AutoTimeEvent;
import com.auto.di.guan.manager.event.DateChangeEvent;
import com.auto.di.guan.manager.rtm.MessageEntiy;
import com.auto.di.guan.manager.rtm.MessageSend;
import com.auto.di.guan.manager.utils.DiffStatusCallback;
import com.auto.di.guan.manager.utils.LogUtils;
import com.auto.di.guan.manager.utils.NoFastClickUtils;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.ArrayList;
import java.util.List;

/**
 *   轮灌设置
 */
public class GroupStatusActivity extends IBaseActivity {

    private String TAG = "GroupStatusActivity";
    private View view;
    private TextView textView;
    private TextView title_bar_status;
    private RecyclerView recyclerView;
    private List<GroupInfo> groupInfos = new ArrayList<>();
    private GroupStatusAdapter adapter;

    @Override
    protected int setLayout() {
        return R.layout.activity_group_status_layout;
    }

    @Override
    protected void init() {
        view = findViewById(R.id.title_bar);

        textView = (TextView) view.findViewById(R.id.title_bar_title);
        textView.setText("自动轮灌");

        title_bar_status = (TextView) view.findViewById(R.id.title_bar_status);
        title_bar_status.setVisibility(View.VISIBLE);
        title_bar_status.setText("轮灌操作");
        title_bar_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NoFastClickUtils.isFastClick()){
                    return;
                }
                GroupOptionDialog.ShowDialog(GroupStatusActivity.this, "自动轮灌操作", new GroupOptionDialog.ItemClick() {
                    @Override
                    public void onItemClick(int index) {
                        if (index == 1) {
                            MessageSend.doAutoOpen();
                        }else if (index == 2) {
                            MessageSend.doAutoClose();
                        }else if (index == 3) {

                        }
                    }
                });
            }
        });
        view.findViewById(R.id.title_bar_back_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupStatusActivity.this.finish();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.group_option_view);

        adapter = new GroupStatusAdapter(groupInfos);
        adapter.setDiffCallback(new DiffStatusCallback());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setData(GroupInfoSql.getJoinGroup());
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAutoTimeEvent(AutoTimeEvent event) {
        if (event == null || event.getGroupInfo() == null) {
            LogUtils.e(TAG, " 轮灌时间更新 数据异常");
            return;
        }
        GroupInfo groupInfo = event.getGroupInfo();
        int groupId = groupInfo.getGroupId();

        int size = groupInfos.size();
        int positin = 0;
        for (int i = 0; i < size; i++) {
            if (groupId == groupInfos.get(i).getGroupId()) {
                positin = i;
            }
        }
        adapter.getData().set(positin, groupInfo);
        adapter.notifyItemChanged(positin, positin);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDateChangeEvent(DateChangeEvent event) {
        LogUtils.e(TAG, " 设备状态数据更--------------------------");
        if (adapter != null) {
            adapter.setData(GroupInfoSql.getJoinGroup());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MessageSend.doActivityEvent(MessageEntiy.TYPE_ACTIVITY_STATUS_FINISH);
    }
}
