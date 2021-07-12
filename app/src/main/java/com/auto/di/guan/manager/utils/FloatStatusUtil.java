package com.auto.di.guan.manager.utils;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.activity.GroupStatusActivity;
import com.auto.di.guan.manager.activity.LoginActivity;
import com.auto.di.guan.manager.activity.MainActivity;
import com.auto.di.guan.manager.activity.ManagerActivity;
import com.auto.di.guan.manager.adapter.FloatStatusAdapter;
import com.auto.di.guan.manager.app.BaseApp;
import com.auto.di.guan.manager.db.ControlInfo;
import com.auto.di.guan.manager.db.GroupInfo;
import com.auto.di.guan.manager.db.sql.ControlInfoSql;
import com.auto.di.guan.manager.db.sql.GroupInfoSql;
import com.auto.di.guan.manager.entity.CmdStatus;
import com.auto.di.guan.manager.entity.Entiy;
import com.auto.di.guan.manager.floatWindow.FloatWindow;
import com.auto.di.guan.manager.floatWindow.MoveType;
import com.auto.di.guan.manager.floatWindow.Screen;
import com.github.lzyzsd.circleprogress.DonutProgress;

import java.util.ArrayList;
import java.util.List;

/**
 *  显示当前正在运行的阀门
 */
public class FloatStatusUtil {

    private final String TAG = "FloatStatusUtil";

    private static FloatStatusUtil instance = new FloatStatusUtil();
    private RecyclerView mListView;
    private FloatStatusAdapter adapter;
    private ArrayList<ControlInfo> controlInfos = new ArrayList<>();
    private View view;
    private TextView textView;
    private DonutProgress donutProgress;
    private GroupInfo groupInfo;
    LinearLayout linearLayout;
    public static synchronized FloatStatusUtil getInstance() {
        return instance;
    }

    public void initFloatWindow(Context mContext) {
        view = View.inflate(BaseApp.getInstance(), R.layout.dialog_run_listview, null);
        view.setFocusableInTouchMode(true);
        mListView = (RecyclerView) view.findViewById(R.id.list);
        donutProgress = view.findViewById(R.id.progress);

        linearLayout = view.findViewById(R.id.layout_list);
        textView = view.findViewById(R.id.text);

        donutProgress.setVisibility(View.VISIBLE);
        donutProgress.setMax(100);
        donutProgress.setProgress(90);

        if (groupInfo != null && groupInfo.getGroupStatus() == Entiy.GROUP_STATUS_OPEN) {
            List<ControlInfo> list = ControlInfoSql.queryControlList(groupInfo.getGroupId());
            controlInfos.clear();
            controlInfos.addAll(list);
        }
        initProgess(groupInfo);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (controlInfos.size() == 0) {
                    ToastUtils.showLongToast("当前没有运行的设备");
                    return;
                }
                if (mListView.getVisibility() == View.VISIBLE) {
                    mListView.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.GONE);
                    LogUtils.e(TAG, "gon");
                } else {
                    mListView.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.VISIBLE);
                    LogUtils.e(TAG, "VISIBLE");
                }
            }
        });
        adapter = new FloatStatusAdapter(controlInfos);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        mListView.setLayoutManager(layoutManager);
        mListView.setAdapter(adapter);
    }

    public void cleanList() {
        if (adapter != null) {
            controlInfos.clear();
            adapter.notifyDataSetChanged();
        }
    }

    public boolean isShow() {
        if (FloatWindow.get(TAG) == null) {
            return false;
        }
        return FloatWindow.get(TAG).isShowing();
    }

    public void show() {
        if (FloatWindow.get(TAG) == null) {
            int size = Math.round(BaseApp.getContext().getResources().getDimension(R.dimen.float_status_size));
            FloatWindow.with(BaseApp.getInstance())
                    .setView(view)
                    .setHeight(size)
                    .setWidth(size)
                    .setX(Screen.width, 0.9f)
                    .setY(Screen.height, 0.5f)
                    .setFilter(true, MainActivity.class, GroupStatusActivity.class)
                    .setMoveType(MoveType.active)
                    .setTag(TAG)
                    .build();
            FloatWindow.get(TAG).show();
        } else {
            if (!FloatWindow.get(TAG).isShowing()) {
                FloatWindow.get(TAG).show();
            }
        }
    }

    public void distory() {
        controlInfos.clear();
        adapter.notifyDataSetChanged();
        FloatWindow.destroy(TAG);
    }


    public void onGroupStatusEvent(GroupInfo info) {
        if (info != null && info.getGroupStatus() == Entiy.GROUP_STATUS_OPEN) {
            List<ControlInfo> controlInfoList = ControlInfoSql.queryControlList(info.getGroupId());
            int size = controlInfoList.size();
            if (size > 0) {
                controlInfos.clear();
                controlInfos.addAll(controlInfoList);
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
            }
        }
        initProgess(info);
    }
    /**
     *        计算当前运行的时间
     * @param info
     */
    public void onAutoCountEvent(GroupInfo info) {
        initProgess(info);
    }

    /**
     *         初始化进度条
     * @param info
     */
    private void initProgess(GroupInfo info) {
        if (info != null && info.getGroupStatus() == Entiy.GROUP_STATUS_OPEN) {
            groupInfo = info;
            if (donutProgress != null) {
                donutProgress.setVisibility(View.VISIBLE);
                donutProgress.setMax(groupInfo.getGroupTime());
                donutProgress.setProgress(groupInfo.getGroupRunTime());
                textView.setText("时长:" + groupInfo.getGroupTime() + "\n运行:" + info.getGroupRunTime());
            }
        }else {
            controlInfos.clear();
            if (donutProgress != null) {
                textView.setText("无设备运行");
                donutProgress.setVisibility(View.INVISIBLE);
            }
        }
    }
}
