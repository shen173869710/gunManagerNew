package com.auto.di.guan.manager.adapter;

import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.entity.GunManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;


public class GunManagerAdapter extends BaseQuickAdapter<GunManager, BaseViewHolder> {

    public GunManagerAdapter(List<GunManager> data) {
        super(R.layout.guan_manager_list_item, data);
    }


    @Override
    protected void convert(BaseViewHolder holder, GunManager info) {
        holder.setText(R.id.item_1,info.getTitle());
        holder.setText(R.id.item_2,info.getDesc());
    }


}
