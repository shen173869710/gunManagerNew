package com.auto.di.guan.manager.adapter;

import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.entity.SubmitInfo;
import com.auto.di.guan.manager.utils.DateUtils;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.Date;
import java.util.List;
public class SubmitAdapter extends BaseQuickAdapter<SubmitInfo, BaseViewHolder> {

    public SubmitAdapter(List<SubmitInfo> data) {
        super(R.layout.submit_list_item, data);
    }

    public void setData(List<SubmitInfo> data) {
        getData().clear();
        getData().addAll(data);
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder holder, SubmitInfo data) {
        holder.setText(R.id.submit_title, data.getTitle());
        if (TextUtils.isEmpty(data.getDesc())) {
            data.setDesc("");
        }

        if (TextUtils.isEmpty(data.getDesc())){
            holder.setText(R.id.submit_info, "");
        }else {
            holder.setText(R.id.submit_info, data.getDesc());
        }

        EditText editText = holder.getView(R.id.submit_info);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                data.setDesc(s.toString());
            }
        });

        if (holder.getAdapterPosition() > getData().size() -3) {
            editText.setHint("请选择");
            editText.setInputType(InputType.TYPE_NULL);
            editText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initStartTimePicker(data, editText);
                }
            });
        }
    }

    private void initStartTimePicker(SubmitInfo info, EditText editText) {
        TimePickerView pvTime = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                info.setDesc(String.valueOf(date.getTime()));
//                tvTime.setText(getTime(date));
                editText.setText(DateUtils.timet(String.valueOf(date.getTime())));
                info.setTime(date.getTime());
            }
        }).build();
        // pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }
}
