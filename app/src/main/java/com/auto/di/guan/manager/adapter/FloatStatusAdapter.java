package com.auto.di.guan.manager.adapter;


import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.db.ControlInfo;
import com.auto.di.guan.manager.utils.GlideUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

public class FloatStatusAdapter extends BaseQuickAdapter<ControlInfo, BaseViewHolder> {
    public FloatStatusAdapter(List<ControlInfo> data) {
        super(R.layout.float_status_list_item, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, ControlInfo info) {
        holder.setText(R.id.group_status_name, ""+info.getValveName());
        holder.setText(R.id.group_status_alias, ""+info.getValveAlias());
        GlideUtil.loadControlExpand(getContext(), holder.getView(R.id.group_status_image), info);
    }

}
