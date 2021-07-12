package com.auto.di.guan.manager.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.adapter.Tab3Adapter;
import com.auto.di.guan.manager.api.ApiUtil;
import com.auto.di.guan.manager.api.HttpManager;
import com.auto.di.guan.manager.basemodel.model.request.BaseRequest;
import com.auto.di.guan.manager.basemodel.model.respone.BaseRespone;
import com.auto.di.guan.manager.basemodel.model.respone.RaiseCropsRecord;
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

public class ManagerTab3 extends BaseFragment {

    @BindView(R.id.tab_3_list)
    RecyclerView tabList;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private Tab3Adapter mAdapter;

    List<RaiseCropsRecord> records = new ArrayList<>();
    private List<User> users;

    @BindView(R.id.tab_3_spinner)
    Spinner tabSpinner;
    private long userId = 0;
    public static ManagerTab3 newInstance(Bundle bundle){
        ManagerTab3 fragment=new ManagerTab3();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int setLayout() {
        return R.layout.manager_tab_3;
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

        tabList.setLayoutManager(new LinearLayoutManager(activity));
        mAdapter = new Tab3Adapter(new ArrayList<>());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, mItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tabList.setAdapter(mAdapter);
        tabSpinner.setAdapter(adapter);
        tabSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    userId = 0;
                }else {
                    userId = users.get(position -1).getUserId();
                }
                mAdapter.setData(records, userId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

    @Override
    public void dataChange(DateChangeEvent event) {

    }

    public void loadMore() {
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("beginTime", 1);
        treeMap.put("endTime", System.currentTimeMillis());
        HttpManager.syncData(ApiUtil.createApiService().getRaiseList(BaseRequest.toMerchantTreeMap(treeMap)), new HttpManager.OnResultListener() {
            @Override
            public void onSuccess(BaseRespone respone) {
                refreshLayout.finishRefresh(1000);
                if (respone.getData() != null) {
                    records = (List<RaiseCropsRecord>) respone.getData();
                    if (records != null) {
                        mAdapter.setData(records, userId);
                    }
                }
            }

            @Override
            public void onError(Throwable error, Integer code, String msg) {
                refreshLayout.finishRefresh(false);
            }
        });
    }
}
