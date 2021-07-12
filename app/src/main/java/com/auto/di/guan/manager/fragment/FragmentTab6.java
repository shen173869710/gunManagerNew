package com.auto.di.guan.manager.fragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.adapter.PumpLeftAdapter;
import com.auto.di.guan.manager.dialog.DialogUtil;
import com.auto.di.guan.manager.dialog.OnDialogClick;
import com.auto.di.guan.manager.event.DateChangeEvent;
import com.auto.di.guan.manager.rtm.MessageSend;
import com.auto.di.guan.manager.socket.SocketBengEvent;
import com.auto.di.guan.manager.socket.SocketResult;
import com.auto.di.guan.manager.utils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;


public class FragmentTab6 extends BaseFragment {

    private PumpLeftAdapter adapter;
    private ArrayList<SocketResult> infos = new ArrayList<>();
    private RecyclerView list;


    @Override
    public int setLayout() {
        return R.layout.fragment_6;
    }

    @Override
    public void init() {
        SocketResult result = new SocketResult();
        result.setName("水泵0");
        result.setNameCode("0");
        result.setVoltage("电压");
        result.setElectricity("电流");
        result.setStatus("状态");
        result.setErrorCode("错误");
        result.setVoltageValue("");
        result.setElectricityValue("");
        result.setStatusValue("");
        result.setErrorCodeValue("");
        SocketResult result1 = new SocketResult();
        result1.setName("水泵1");
        result1.setNameCode("1");
        result1.setVoltage("电压");
        result1.setElectricity("电流");
        result1.setStatus("状态");
        result1.setErrorCode("错误");
        result1.setVoltageValue("");
        result1.setElectricityValue("");
        result1.setStatusValue("");
        result1.setErrorCodeValue("");
        infos.add(result);
        infos.add(result1);
        list = mRootView.findViewById(R.id.fragment_6_list);
        list.setLayoutManager(new LinearLayoutManager(activity));
        adapter = new PumpLeftAdapter(infos);
        list.setAdapter(adapter);
        setData();
    }

    public void setData() {
        adapter.addChildClickViewIds(R.id.item_open, R.id.item_close, R.id.item_1_value);
        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SocketResult result = infos.get(position);
                int id = view.getId();
                if (id == R.id.item_open) {
                    DialogUtil.startSocket(getActivity(), result.getName(), new OnDialogClick() {
                        @Override
                        public void onDialogOkClick(String value) {
                            MessageSend.doBengOpen(position);
                        }

                        @Override
                        public void onDialogCloseClick(String value) {

                        }
                    });

                }else if (id == R.id.item_close) {
                    DialogUtil.closeSocket(getActivity(), result.getName(), new OnDialogClick() {
                        @Override
                        public void onDialogOkClick(String value) {
                            MessageSend.doBengClose(position);
                        }

                        @Override
                        public void onDialogCloseClick(String value) {

                        }
                    });
                }else if (id == R.id.item_1_value) {
                    //new UdpSendThread(SocketEntiy.getSockenRead(result.getNameCode())).start();
                }
            }
        });

    }

    @Override
    public void dataChange(DateChangeEvent event) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSocketBengEvent(SocketBengEvent event) {
        LogUtils.e(this.getClass().getSimpleName(), "--------数据更新");

        if (adapter != null) {
            infos.clear();
            infos.addAll(event.getSocketResults());
            adapter.notifyDataSetChanged();
        }
    }

}
