package com.auto.di.guan.manager.adapter;

import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.app.BaseApp;
import com.auto.di.guan.manager.db.UserAction;
import com.auto.di.guan.manager.entity.Entiy;
import com.auto.di.guan.manager.utils.DateUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import org.jetbrains.annotations.NotNull;
import java.util.List;


public class QuareUserAdapter extends BaseQuickAdapter<UserAction, BaseViewHolder> {

	public QuareUserAdapter(List<UserAction> data) {
		super(R.layout.quare_user_list_item, data);
	}

	@Override
	protected void convert(@NotNull BaseViewHolder holder, UserAction action) {
		holder.setText(R.id.quare_user_name, BaseApp.getUser().getLoginName() +"");
		holder.setText(R.id.quare_user_type, action.getActionTypeName() +"");
		holder.setText(R.id.quare_user_desc, action.getActionName() +"");
		holder.setText(R.id.quare_user_end, action.getActionStatusName()+"");
		holder.setText(R.id.quare_user_time, DateUtils.times(action.getActionTime())+"");
		int type = action.getActionType();
		int color = 0;
		if (type == Entiy.ACTION_TYPE_4) {
			color = getContext().getResources().getColor(R.color.check_btn_sel);
		}else if (type == Entiy.ACTION_TYPE_31) {
			color = getContext().getResources().getColor(R.color.check_btn_no);
		}else if (type == Entiy.ACTION_TYPE_32) {
			color = getContext().getResources().getColor(R.color.none_transparent);
		}
		if (!action.getActionStatus()){
			color = getContext().getResources().getColor(R.color.red);
		}
		holder.setBackgroundColor(R.id.quare_user_layout,color);
	}
}
