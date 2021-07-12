package com.auto.di.guan.manager.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.activity.LogInfoActivity;
import com.auto.di.guan.manager.adapter.Tab2Adapter;
import com.auto.di.guan.manager.api.ApiUtil;
import com.auto.di.guan.manager.api.HttpManager;
import com.auto.di.guan.manager.basemodel.model.request.BaseRequest;
import com.auto.di.guan.manager.basemodel.model.respone.BaseRespone;
import com.auto.di.guan.manager.basemodel.model.respone.WateringRecord;
import com.auto.di.guan.manager.db.User;
import com.auto.di.guan.manager.entity.Entiy;
import com.auto.di.guan.manager.event.DateChangeEvent;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;

public class ManagerTab2 extends BaseFragment {

    @BindView(R.id.tab_2_list)
    RecyclerView tabList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tab_2_spinner)
    Spinner tabSpinner;
    Tab2Adapter mAdapter;

    private List<User> users;

    private List<WateringRecord> wateringRecords = new ArrayList<>();
    private long userId = 0;

    public static ManagerTab2 newInstance(Bundle bundle){
        ManagerTab2 fragment=new ManagerTab2();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int setLayout() {
        return R.layout.manager_tab_2;
    }

    @Override
    public void init() {
        users = (List<User>)getArguments().getSerializable(Entiy.INTENT_USER_LIST);
        if (users == null) {
            users = new ArrayList<>();
        }

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
                mAdapter.setData(wateringRecords, userId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        tabList.setLayoutManager(new LinearLayoutManager(activity));
        mAdapter = new Tab2Adapter(new ArrayList<>());
        tabList.setAdapter(mAdapter);
        mAdapter.addChildClickViewIds(R.id.manager_item_login);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                Intent intent = new Intent(activity, LogInfoActivity.class);
                intent.putExtra(Entiy.INTENT_WATER, wateringRecords.get(position));
                intent.putExtra(Entiy.INTENT_TITLE, Entiy.MANAGER_ITEM[2]);
                activity.startActivity(intent);
            }
        });

        refreshLayout.setRefreshHeader(new ClassicsHeader(activity));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                loadMore();
            }
        });
        loadMore();
    }

    public void loadMore() {
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("pageNum", 1);
        treeMap.put("pageSize", 100);
        HttpManager.syncData(ApiUtil.createApiService().getWaterList(BaseRequest.toMerchantTreeMap(treeMap)), new HttpManager.OnResultListener() {
            @Override
            public void onSuccess(BaseRespone respone) {
                refreshLayout.finishRefresh(1000);
                if (respone.getData() != null) {
                    wateringRecords = (List<WateringRecord>) respone.getData();
                    if (wateringRecords != null) {
                        mAdapter.setData(wateringRecords, userId);
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
