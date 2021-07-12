package com.auto.di.guan.manager.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.adapter.Submit2Adapter;
import com.auto.di.guan.manager.app.BaseApp;
import com.auto.di.guan.manager.basemodel.model.respone.ApplyFertilizerRecord;
import com.auto.di.guan.manager.basemodel.model.respone.BaseRespone;
import com.auto.di.guan.manager.basemodel.presenter.CommonPresenter;
import com.auto.di.guan.manager.basemodel.view.IBaseView;
import com.auto.di.guan.manager.db.User;
import com.auto.di.guan.manager.entity.Entiy;
import com.auto.di.guan.manager.entity.SubmitInfo;
import com.auto.di.guan.manager.utils.DateUtils;
import com.auto.di.guan.manager.utils.ToastUtils;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 施肥日志
 */
public class AddApplyActivity extends IBaseActivity<CommonPresenter> implements IBaseView {

    @BindView(R.id.title_bar_back_layout)
    RelativeLayout titleBarBackLayout;
    @BindView(R.id.add_spinner)
    Spinner addSpinner;
    @BindView(R.id.add_submit)
    Button addSubmit;
    @BindView(R.id.title_bar_title)
    TextView titleBarTitle;
    @BindView(R.id.add_list)
    RecyclerView addList;
    @BindView(R.id.add_time)
    TextView addTime;

    private List<User> users;
    private int index = 0;
    private Submit2Adapter submitAdapter;
    private List<SubmitInfo> submitInfos = new ArrayList<>();
    private ApplyFertilizerRecord record = new ApplyFertilizerRecord();

    @Override
    protected int setLayout() {
        return R.layout.activity_add_apply;
    }

    @Override
    protected void init() {
        users = (List<User>) getIntent().getSerializableExtra(Entiy.INTENT_USER_LIST);
        if (users == null) {
            return;
        }
        titleBarTitle.setText(R.string.manager_tab_4);
        int size = users.size();
        ArrayList<String> mItems = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            mItems.add(users.get(i).getLoginName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addSpinner.setAdapter(adapter);
        addSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                index = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addList.setLayoutManager(new LinearLayoutManager(this));

        int length = Entiy.APPLY.length;
        submitInfos.clear();
        for (int i = 0; i < length; i++) {
            SubmitInfo submitInfo = new SubmitInfo(Entiy.APPLY[i], "", i);
//            String[] temp = null;
//            ArrayList<String> type = new ArrayList<>();
//            if (i == 0) {
//                temp = getResources().getStringArray(R.array.nitrogenFertilizer);
//            } else if (i == 1) {
//                temp = getResources().getStringArray(R.array.phosphateFertilizer);
//            } else if (i == 2) {
//                temp = getResources().getStringArray(R.array.potashFertilizer);
//            } else if (i == 3) {
//                temp = getResources().getStringArray(R.array.compoundFertilizer);
//            } else if (i == 4) {
//                temp = getResources().getStringArray(R.array.otherFertilizers);
//            }
//            for (int j = 0; j < temp.length; j++) {
//                type.add(temp[j]);
//            }
//            submitInfo.setLists(type);
            submitInfos.add(submitInfo);
        }
        submitAdapter = new Submit2Adapter(submitInfos);
        addList.setAdapter(submitAdapter);
    }

    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter();
    }


    @Override
    public void success(BaseRespone respone) {
        ToastUtils.showToast(R.string.submit_suc);
    }

    @Override
    public void fail(Throwable error, Integer code, String msg) {
        ToastUtils.showToast(R.string.submit_faile);
    }

    public void submitInfo() {
        int size = submitInfos.size();
        if (record.getApplyFertilizerDate() == null) {
            ToastUtils.showToast("请选择施肥日期");
            return;
        }
        User user = users.get(index);
        for (int i = 0; i < size; i++) {
            SubmitInfo info = submitInfos.get(i);
            if (TextUtils.isEmpty(info.getDesc())) {
                ToastUtils.showToast("输入信息为空");
                return;
            }
            if (info.getIndex() < 0) {
                ToastUtils.showToast("请选择化肥种类");
                return;
            }
            if (info.getIndex() == 0) {
                record.setNitrogenFertilizerName(info.getInfo());
                record.setCompoundFertilizerNum(info.getDesc());
            } else if (info.getIndex() == 1) {
                record.setPhosphateFertilizerName(info.getInfo());
                record.setPhosphateFertilizerNum(info.getDesc());
            } else if (info.getIndex() == 2) {
                record.setPotashFertilizerName(info.getInfo());
                record.setPotashFertilizerNum(info.getDesc());
            } else if (info.getIndex() == 3) {
                record.setCompoundFertilizerName(info.getInfo());
                record.setPotashFertilizerNum(info.getDesc());
            } else if (info.getIndex() == 4) {
                record.setOtherFertilizersName(info.getInfo());
                record.setOtherFertilizersNum(info.getDesc());
            }
        }
        record.setWaterUserId(BaseApp.getUser().getUserId());
        record.setMemberUserId(user.getUserId());
        record.setProjectName(user.getLoginName());
        mPresenter.addApply(record);
    }


    @OnClick({R.id.title_bar_back_layout, R.id.add_submit, R.id.add_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back_layout:
                finish();
                break;
            case R.id.add_submit:
                submitInfo();
                break;
            case R.id.add_time:
                initStartTimePicker();
                break;
        }
    }


    /**初始化开始日期选择器控件*/
    private void initStartTimePicker() {
        TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date,View v) {//选中事件回调
                addTime.setText(DateUtils.timet(String.valueOf(date.getTime())));
//                tvTime.setText(getTime(date));
                record.setApplyFertilizerDate(date.getTime());
            }
        }).build();
        // pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }

}
