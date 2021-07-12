package com.auto.di.guan.manager.adapter;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.entity.SubmitInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

public class Submit2Adapter extends BaseQuickAdapter<SubmitInfo, BaseViewHolder> {


    public Submit2Adapter(List<SubmitInfo> data) {
        super(R.layout.submit2_list_item, data);
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

        EditText info = holder.getView(R.id.submit_info);
        if (TextUtils.isEmpty(data.getDesc())){
            holder.setText(R.id.submit_info, "");
        }else {
            holder.setText(R.id.submit_info, data.getDesc());
        }

        info.addTextChangedListener(new TextWatcher() {
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

        EditText num = holder.getView(R.id.submit_name);
        num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                data.setInfo(s.toString());
            }
        });
    }
}
