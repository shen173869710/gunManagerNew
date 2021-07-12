package com.auto.di.guan.manager.fragment;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.adapter.MyGridOpenAdapter;
import com.auto.di.guan.manager.app.BaseApp;
import com.auto.di.guan.manager.entity.Entiy;
import com.auto.di.guan.manager.event.DateChangeEvent;
import com.auto.di.guan.manager.utils.LogUtils;

import butterknife.BindView;

public class FragmentTab3 extends BaseFragment {

    @BindView(R.id.fragment_3_list)
    RecyclerView fragment3List;
    private MyGridOpenAdapter adapter;


    @Override
    public int setLayout() {
        return R.layout.fragment_3;
    }

    @Override
    public void init() {
        LinearLayoutManager manager = new GridLayoutManager(activity, Entiy.GRID_COLUMNS);
        fragment3List.setLayoutManager(manager);
        adapter = new MyGridOpenAdapter(BaseApp.getDeviceInfos());
        fragment3List.setAdapter(adapter);
    }

    @Override
    public void dataChange(DateChangeEvent event) {
        LogUtils.e("fragmenttab3", "更新数据");
       if (adapter != null) {
           adapter.setNewData(BaseApp.getDeviceInfos());
       }
    }


}
