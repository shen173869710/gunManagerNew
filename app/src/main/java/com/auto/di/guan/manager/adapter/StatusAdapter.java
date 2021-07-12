package com.auto.di.guan.manager.adapter;


import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.db.ControlInfo;
import com.auto.di.guan.manager.entity.Entiy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import java.util.List;

public class StatusAdapter extends BaseQuickAdapter<ControlInfo, BaseViewHolder> {

    public StatusAdapter( List<ControlInfo> data) {
        super(R.layout.group_status_list_item, data);
    }

    public void setData(List<ControlInfo> controlInfos) {
        getData().clear();
        getData().addAll(controlInfos);
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder holder, ControlInfo info) {
        holder.setText(R.id.group_status_name, info.getValveAlias());
        holder.setText(R.id.group_status_id, info.getValveName());
        holder.setImageResource(R.id.group_status_image, Entiy.getImageResource(info.getValveStatus()));
    }


}
