package com.auto.di.guan.manager.fragment;

import android.widget.Button;

import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.event.DateChangeEvent;

import butterknife.BindView;
import butterknife.OnClick;

public class FragmentTab11 extends BaseFragment {


    @BindView(R.id.exit)
    Button exit;

    @Override
    public int setLayout() {
        return R.layout.fragment_11;
    }

    @Override
    public void init() {

    }

    @Override
    public void dataChange(DateChangeEvent event) {

    }


    @OnClick(R.id.exit)
    public void onViewClicked() {
        getActivity().finish();
    }
}
