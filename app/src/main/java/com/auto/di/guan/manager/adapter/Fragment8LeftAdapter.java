package com.auto.di.guan.manager.adapter;


import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.basemodel.model.respone.MeteoLocation;
import com.auto.di.guan.manager.basemodel.model.respone.MeteoRespone;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Fragment8LeftAdapter extends BaseQuickAdapter<MeteoRespone, BaseViewHolder> {
    public Fragment8LeftAdapter(@Nullable List<MeteoRespone> data) {
        super(R.layout.fragment_8_left_list_item, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, MeteoRespone respone) {
        holder.setText(R.id.item_0, respone.getAlias());
        holder.setText(R.id.item_1, respone.getSn());

        MeteoLocation location = respone.getLocation();
        if (location != null) {
            String local = location.getProvince()+location.getCity()+location.getDistrict();
            holder.setText(R.id.item_2, local);
        }else {
            holder.setText(R.id.item_2, "");
        }

        if (respone.isSle()) {
            holder.setBackgroundResource(R.id.item_layout, R.drawable.fragment_tab_0_sel);
        }else {
            holder.setBackgroundResource(R.id.item_layout, R.color.fragment0_tab_n);
        }
    }
}

