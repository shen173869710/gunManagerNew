package com.auto.di.guan.manager.activity;

import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.app.BaseApp;
import com.auto.di.guan.manager.basemodel.presenter.BasePresenter;
import com.auto.di.guan.manager.entity.CmdStatus;
import com.auto.di.guan.manager.event.DialogEvent;
import com.auto.di.guan.manager.event.UserStatusEvent;
import com.auto.di.guan.manager.fragment.ArticleListFragment;
import com.auto.di.guan.manager.rtm.MessageSend;
import com.auto.di.guan.manager.utils.FloatStatusUtil;
import com.auto.di.guan.manager.utils.FloatWindowUtil;
import com.auto.di.guan.manager.utils.LogUtils;
import com.auto.di.guan.manager.utils.PollingUtils;
import com.auto.di.guan.manager.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class MainActivity extends IBaseActivity {
    public static int windowTop;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private final String TAG = "MainActivity";

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        windowTop = getStatusBarHeight();
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        final ArticleListFragment articleListFragment = new ArticleListFragment();
        transaction.add(R.id.center, articleListFragment, "center");
        transaction.commitAllowingStateLoss();

        View view = findViewById(R.id.title_bar);
        view.findViewById(R.id.title_bar_back_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.title_bar_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mChatManager.sendPeerMessage( "来着222222222的消息");
            }
        });

        FloatStatusUtil.getInstance().show();
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    private void inttTest() {
//        List<DeviceInfo> deviceInfos = new ArrayList<>();
//       for (int i = 1; i < 10; i++) {
//           DeviceInfo deviceInfo = new DeviceInfo();
//           deviceInfo.setDeviceId(i);
//           deviceInfo.setDeviceSort(i);
//           deviceInfo.setDeviceStatus(i);
//           deviceInfo.setDeviceName(i+"");
//           ControlInfo info1 = new ControlInfo();
//           info1.setDeviceId(i);
//           info1.setDeviceId(deviceInfo.getDeviceId());
//           info1.setValveName("0");
//           info1.setValveStatus(1);
//           info1.setValveAlias(i+"-"+1);
//           ControlInfo info2 = new ControlInfo();
//           info2.setDeviceId(i);
//           info2.setDeviceId(deviceInfo.getDeviceId());
//           info2.setValveName("1");
//           info2.setValveAlias(i+"-"+2);
//           info2.setValveStatus(1);
//           ArrayList<ControlInfo> controlInfos = new ArrayList<>(2);
//           int id = 0;
//           if (i < 3) {
//               id = 1;
//           }else if ( i >= 3 && i < 5) {
//               id = 2;
//           }else if ( i >= 5 && i < 7) {
//               id = 3;
//           }else if ( i >= 7 && i < 8) {
//               id = 4;
//           }else {
//               id = 5;
//           }
//           info1.setValveGroupId(id);
//           info2.setValveGroupId(id);
//           controlInfos.add(info1);
//           controlInfos.add(info2);
//           deviceInfo.setValveDeviceSwitchList(controlInfos);
//           deviceInfos.add(deviceInfo);
//       }

//        ArrayList<GroupInfo> groupInfos = new ArrayList<>();
//       for (int i = 1; i < 6; i++) {
//           GroupInfo groupInfo = new GroupInfo();
//           groupInfo.setGroupId(i);
//           groupInfo.setGroupIsJoin(true);
//           groupInfo.setGroupLevel(i);
//           groupInfo.setGroupName(i+"");
//           groupInfo.setGroupRunTime(0);
//           groupInfo.setGroupTime(i + 60);
//           groupInfos.add(groupInfo);
//       }
//       BaseApp.setDeviceInfos(deviceInfos);
//        BaseApp.setGroupInfos(groupInfos);
    }


    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        PollingUtils.stopPollingService(this);
        FloatWindowUtil.getInstance().distory();
        MessageSend.doLogout();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserStatusEvent(UserStatusEvent event) {
        String currentId = BaseApp.getInstance().getChatManager().getLoginId();
        LogUtils.e(TAG, "当前登录的 id = "+currentId+ "离线用户的id = "+event.getPeerId());
        if (String.valueOf(currentId).equals(event.getPeerId())){
            if (event.getStatus() == 0) {
                LogUtils.e(TAG, "用户处于登录状态");
            }else {
                ToastUtils.showLongToast("用户处于离线状态");
//                BaseApp.getInstance().getChatManager().setLoginId("");
                MainActivity.this.finish();
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStatsuEvent(CmdStatus event) {
        FloatWindowUtil.getInstance().show();
        FloatWindowUtil.getInstance().onStatsuEvent(event);
    }




    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDialogEvent(DialogEvent event) {
        if (event == null) {
            return;
        }
        if (event.isShow()) {
            showDialog();
        }else {
            dismissDialog();
        }
    }
}
