package com.auto.di.guan.manager.fragment;

import android.view.View;
import android.widget.Button;

import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.event.AutoTimeEvent;
import com.auto.di.guan.manager.event.DateChangeEvent;
import com.auto.di.guan.manager.event.UpdateEvent;
import com.auto.di.guan.manager.rtm.MessageSend;
import com.auto.di.guan.manager.utils.FloatStatusUtil;
import com.auto.di.guan.manager.utils.LogUtils;
import com.auto.di.guan.manager.utils.ToastUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class FragmentTab11 extends BaseFragment {


    @BindView(R.id.exit)
    Button exit;
    @BindView(R.id.update)
    Button update;
    @Override
    public int setLayout() {
        return R.layout.fragment_11;
    }

    @Override
    public void init() {
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageSend.doUpdate();
            }
        });
    }

    @Override
    public void dataChange(DateChangeEvent event) {

    }


    @OnClick({R.id.exit,})
    public void onViewClicked() {
        getActivity().finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateEvent(UpdateEvent event) {
        if ("1".equals(event.getType())) {
            ToastUtils.showToast("同步成功");
        }else {
            ToastUtils.showToast("同步失败");
        }

    }
}
