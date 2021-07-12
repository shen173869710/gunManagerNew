package com.auto.di.guan.manager.adapter;

import android.widget.LinearLayout;

import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.db.User;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

public class ManagerItemAdapter extends BaseQuickAdapter<User, BaseViewHolder> {

    public ManagerItemAdapter(List<User> data) {
        super(R.layout.manager_item, data);
    }

    public void setData(List<User> data) {
        getData().clear();
        getData().addAll(data);
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder holder, User data) {
        LinearLayout layout = holder.getView(R.id.item_layout);
        if (data.getLoginStatus() == 0) {
            layout.setBackgroundColor(getContext().getResources().getColor(R.color.white));
        }else {
            layout.setBackgroundColor(getContext().getResources().getColor(R.color.login_edit_hint_color));
        }
        holder.setText(R.id.item_title, data.getUserName());

    }


}
