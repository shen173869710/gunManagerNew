package com.auto.di.guan.manager.adapter;

import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.basemodel.model.respone.ApplyFertilizerRecord;
import com.auto.di.guan.manager.utils.DateUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

public class Tab4Adapter extends BaseQuickAdapter<ApplyFertilizerRecord, BaseViewHolder> {

    public Tab4Adapter(List<ApplyFertilizerRecord> data) {
        super(R.layout.manager_tab_4_item, data);
    }

    public void setData(List<ApplyFertilizerRecord> data, long userId) {
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
    protected void convert(BaseViewHolder holder, ApplyFertilizerRecord data) {
        holder.setText(R.id.item_0, data.getProjectName()+"");
        holder.setText(R.id.item_1, data.getNitrogenFertilizerName()+"/"+data.getNitrogenFertilizerNum());
        holder.setText(R.id.item_2, data.getPhosphateFertilizerName()+"/"+data.getPhosphateFertilizerNum());
        holder.setText(R.id.item_3, data.getPotashFertilizerName()+"/"+data.getPotashFertilizerNum());
        holder.setText(R.id.item_4, data.getCompoundFertilizerName()+"/"+data.getCompoundFertilizerNum());
        holder.setText(R.id.item_5, data.getOtherFertilizersName()+"/"+data.getOtherFertilizersNum());
        holder.setText(R.id.item_6, DateUtils.timet(String.valueOf(data.getApplyFertilizerDate())) +"");
    }

}
