package com.auto.di.guan.manager.adapter;


import android.widget.LinearLayout;

import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.db.User;
import com.auto.di.guan.manager.utils.GlideUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

public class ManagerAdapter extends BaseQuickAdapter<User, BaseViewHolder> {

    public ManagerAdapter(List<User> data) {
        super(R.layout.manager_list_item, data);
    }

    public void setData(List<User> data) {
        getData().clear();
        getData().addAll(data);
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder holder, User data) {
        LinearLayout layout = holder.getView(R.id.manager_item_layout);

        if (data.getLoginStatus() != 0) {
            holder.setVisible(R.id.manager_item_status, true);
            layout.setBackgroundResource(R.drawable.manager_status_bg_n);
            holder.setBackgroundResource(R.id.manager_item_video, R.drawable.bg_red_buttom_rect_n);
            holder.setBackgroundResource(R.id.manager_item_login, R.drawable.bg_red_buttom_rect_n);
            holder.setTextColor(R.id.manager_item_name,R.color.main_item_desc_color);
            holder.setTextColor(R.id.manager_item_phone,R.color.main_item_desc_color);
        }else {
            holder.setVisible(R.id.manager_item_status, false);
            layout.setBackgroundResource(R.drawable.manager_status_bg);
            holder.setBackgroundResource(R.id.manager_item_video, R.drawable.bg_red_buttom_rect);
            holder.setBackgroundResource(R.id.manager_item_login, R.drawable.bg_red_buttom_rect);
            holder.setTextColor(R.id.manager_item_name,R.color.black);
            holder.setTextColor(R.id.manager_item_phone,R.color.main_item_title_color);
        }
        holder.setText(R.id.manager_item_name, data.getUserName());
        GlideUtil.glideCircleImage(holder.getView(R.id.manager_item_icon), R.mipmap.ic_launcher);
        holder.setText(R.id.manager_item_phone,data.getPhonenumber());
    }


}
