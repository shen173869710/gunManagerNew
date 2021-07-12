package com.auto.di.guan.manager.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.adapter.Tab4Adapter;
import com.auto.di.guan.manager.api.ApiUtil;
import com.auto.di.guan.manager.api.HttpManager;
import com.auto.di.guan.manager.basemodel.model.request.BaseRequest;
import com.auto.di.guan.manager.basemodel.model.respone.ApplyFertilizerRecord;
import com.auto.di.guan.manager.basemodel.model.respone.BaseRespone;
import com.auto.di.guan.manager.db.User;
import com.auto.di.guan.manager.entity.Entiy;
import com.auto.di.guan.manager.event.DateChangeEvent;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;

public class ManagerTab4 extends BaseFragment {


    @BindView(R.id.tab_4_list)
    RecyclerView tabList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private Tab4Adapter mAdapter;
    @BindView(R.id.tab_4_spinner)
    Spinner tabSpinner;
    private long userId = 0;

    List<ApplyFertilizerRecord> list = new ArrayList<>();
    private List<User> users;

    public static ManagerTab4 newInstance(Bundle bundle) {
        ManagerTab4 fragment = new ManagerTab4();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int setLayout() {
        return R.layout.manager_tab_4;
    }

    @Override
    public void init() {

        users = (List<User>)getArguments().getSerializable(Entiy.INTENT_USER_LIST);
        if (users == null) {
            users = new ArrayList<>();
        }

        tabList.setLayoutManager(new LinearLayoutManager(activity));
        mAdapter = new Tab4Adapter(new ArrayList<>());
        tabList.setAdapter(mAdapter);
        refreshLayout.setRefreshHeader(new ClassicsHeader(activity));

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                loadMore();
            }
        });

        int size = users.size();
        ArrayList<String> mItems = new ArrayList<>();
        mItems.add("全部");
        for (int i = 0; i < size; i++) {
            mItems.add(users.get(i).getLoginName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, mItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tabSpinner.setAdapter(adapter);
        tabSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    userId = 0;
                }else {
                    userId = users.get(position -1).getUserId();
                }
                mAdapter.setData(list, userId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        loadMore();
    }

    public void loadMore() {
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("pageNum", 1);
        treeMap.put("pageSize", 100);
        HttpManager.syncData(ApiUtil.createApiService().getApplyList(BaseRequest.toMerchantTreeMap(treeMap)), new HttpManager.OnResultListener() {
            @Override
            public void onSuccess(BaseRespone respone) {
                refreshLayout.finishRefresh(1000);
                if (respone.getData() != null) {
                    list = (List<ApplyFertilizerRecord>) respone.getData();
                    if (list != null) {
                        mAdapter.setData(list, userId);
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
