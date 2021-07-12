package com.auto.di.guan.manager.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.activity.LogInfoActivity;
import com.auto.di.guan.manager.adapter.Tab5Adapter;
import com.auto.di.guan.manager.api.ApiUtil;
import com.auto.di.guan.manager.api.HttpManager;
import com.auto.di.guan.manager.basemodel.model.request.BaseRequest;
import com.auto.di.guan.manager.basemodel.model.respone.BaseRespone;
import com.auto.di.guan.manager.basemodel.model.respone.NoticeMessage;
import com.auto.di.guan.manager.entity.Entiy;
import com.auto.di.guan.manager.event.DateChangeEvent;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;

public class ManagerTab5 extends BaseFragment {

    @BindView(R.id.tab_5_list)
    RecyclerView tab5List;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private Tab5Adapter tab5Adapter;

    List<NoticeMessage> messages = new ArrayList<>();

    public static ManagerTab5 newInstance(Bundle bundle) {
        ManagerTab5 fragment = new ManagerTab5();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int setLayout() {
        return R.layout.manager_tab_5;
    }

    @Override
    public void init() {
        tab5List.setLayoutManager(new LinearLayoutManager(activity));
        tab5Adapter = new Tab5Adapter(messages);
        tab5List.setAdapter(tab5Adapter);
        refreshLayout.setRefreshHeader(new ClassicsHeader(activity));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

                loadMore();
            }
        });

        tab5Adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                Intent intent = new Intent(activity, LogInfoActivity.class);
                intent.putExtra(Entiy.INTENT_NOTICE, messages.get(position));
                intent.putExtra(Entiy.INTENT_TITLE, Entiy.MANAGER_ITEM[5]);
                activity.startActivity(intent);
            }
        });
        loadMore();
    }

    public void loadMore() {
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("pageNum", 1);
        treeMap.put("pageSize", 100);
        HttpManager.syncData(ApiUtil.createApiService().getNotice(BaseRequest.toMerchantTreeMap(treeMap)), new HttpManager.OnResultListener() {
            @Override
            public void onSuccess(BaseRespone respone) {
                refreshLayout.finishRefresh(1000);
                if (respone.getData() != null) {
                    List<NoticeMessage> list = (List<NoticeMessage>) respone.getData();
                    if (list != null) {
                        tab5Adapter.setData(list);
                    }
                }
            }

            @Override
            public void onError(Throwable error, Integer code, String msg) {
                refreshLayout.finishRefresh(false);
            }
        });
    }
    @Override
    public void dataChange(DateChangeEvent event) {

    }
}
