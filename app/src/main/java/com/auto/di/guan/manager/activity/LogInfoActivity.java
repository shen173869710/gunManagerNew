package com.auto.di.guan.manager.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.basemodel.model.respone.NoticeMessage;
import com.auto.di.guan.manager.basemodel.model.respone.RaiseCropsRecord;
import com.auto.di.guan.manager.basemodel.model.respone.WateringRecord;
import com.auto.di.guan.manager.basemodel.presenter.CommonPresenter;
import com.auto.di.guan.manager.db.User;
import com.auto.di.guan.manager.entity.Entiy;
import com.auto.di.guan.manager.utils.DateUtils;
import com.auto.di.guan.manager.utils.FloatStatusUtil;
import com.auto.di.guan.manager.utils.FloatWindowUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 添加操作日志
 */

public class LogInfoActivity extends IBaseActivity {

    @BindView(R.id.title_bar_back_layout)
    RelativeLayout titleBarBackLayout;
    @BindView(R.id.title_bar_title)
    TextView titleBarTitle;

    @BindView(R.id.log_title)
    TextView logTitle;
    @BindView(R.id.log_desc)
    TextView logDesc;
    @BindView(R.id.log_time)
    TextView logTime;

    WateringRecord mRecord;
    private String mTitle;
    NoticeMessage mMessage;

    @Override
    protected int setLayout() {
        return R.layout.activity_log_info;
    }

    @Override
    protected void init() {

        mTitle =  getIntent().getStringExtra(Entiy.INTENT_TITLE);
        if (TextUtils.isEmpty(mTitle)) {
            mTitle = "";
        }
        mRecord = (WateringRecord) getIntent().getSerializableExtra(Entiy.INTENT_WATER);
        mMessage = (NoticeMessage) getIntent().getSerializableExtra(Entiy.INTENT_NOTICE);
        titleBarTitle.setText(mTitle);

        String title;
        String desc;
        String time;
        if (mRecord != null) {
            title = mRecord.getProjectName();
            desc = mRecord.getFlowMeterCount();
            time = DateUtils.timet(String.valueOf(mRecord.getRecordDate()));
        }else {
            title = mMessage.getNoticeTitle();
            desc = mMessage.getNoticeContent();
            time = mMessage.getCreateTime();
        }

        logTitle.setText(title);
        logDesc.setText(desc);
        logTime.setText(time);

    }

    @Override
    protected CommonPresenter createPresenter() {
        return null;
    }






    @OnClick({R.id.title_bar_back_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back_layout:
                finish();
                break;
        }
    }

}
