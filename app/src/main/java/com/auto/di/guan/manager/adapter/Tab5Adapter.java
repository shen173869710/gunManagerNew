package com.auto.di.guan.manager.adapter;

import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.basemodel.model.respone.NoticeMessage;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

public class Tab5Adapter extends BaseQuickAdapter<NoticeMessage, BaseViewHolder> {

    public Tab5Adapter(List<NoticeMessage> data) {
        super(R.layout.manager_tab_5_item, data);
    }

    public void setData(List<NoticeMessage> data) {
        getData().clear();
        getData().addAll(data);
        notifyDataSetChanged();
    }
    @Override
    protected void convert(BaseViewHolder holder, NoticeMessage data) {
        holder.setText(R.id.item_title, data.getNoticeTitle()+"");
        holder.setText(R.id.item_desc,data.getNoticeContent()+"");
        holder.setText(R.id.item_time,data.getCreateTime()+"");
    }

}
