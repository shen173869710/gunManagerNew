package com.auto.di.guan.manager.adapter;

import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.basemodel.model.respone.WateringRecord;
import com.auto.di.guan.manager.utils.DateUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import java.util.List;
public class Tab2Adapter extends BaseQuickAdapter<WateringRecord, BaseViewHolder> {
    public Tab2Adapter(List<WateringRecord> data) {
        super(R.layout.manager_tab_2_item, data);
    }

    public void setData(List<WateringRecord> data, long userId) {
        getData().clear();
        if (userId == 0) {
            getData().addAll(data);
        }else {
            int size = data.size();
            for (int i = 0; i < size; i++) {
                if (data.get(i).getMemberUserId() == userId) {
                    getData().add(data.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }
    @Override
    protected void convert(BaseViewHolder holder, WateringRecord data) {
        holder.setText(R.id.item_0, data.getProjectName()+"");
        holder.setText(R.id.item_1, data.getFlowMeterCount()+"");
        holder.setText(R.id.item_2, DateUtils.timet(String.valueOf(data.getRecordDate()))+"");
    }
}
