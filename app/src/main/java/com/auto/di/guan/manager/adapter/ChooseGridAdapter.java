package com.auto.di.guan.manager.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.db.ControlInfo;
import com.auto.di.guan.manager.db.DeviceInfo;
import com.auto.di.guan.manager.entity.Entiy;
import com.auto.di.guan.manager.utils.GlideUtil;
import com.auto.di.guan.manager.utils.NoFastClickUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */

public class ChooseGridAdapter extends BaseQuickAdapter<DeviceInfo, BaseViewHolder> {
    public ChooseGridAdapter(List<DeviceInfo> data) {
        super(R.layout.grid_item, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, DeviceInfo deviceInfo) {
        TextView grid_item_device_id = holder.findView(R.id.grid_item_device_id);
        grid_item_device_id.setText(deviceInfo.getDeviceSort() + "");
        grid_item_device_id.setVisibility(View.VISIBLE);
        TextView grid_item_device_name = holder.findView(R.id.grid_item_device_name);
        ImageView grid_item_device = holder.findView(R.id.grid_item_device);
        TextView grid_item_device_value = holder.findView(R.id.grid_item_device_value);


        RelativeLayout grid_item_left_layout = holder.findView(R.id.grid_item_left_layout);
        TextView grid_item_left_sel = holder.findView(R.id.grid_item_left_sel);
        ImageView grid_item_left_image = holder.findView(R.id.grid_item_left_image);
        TextView grid_item_left_alias = holder.findView(R.id.grid_item_left_alias);
        TextView grid_item_left_id = holder.findView(R.id.grid_item_left_id);
        ImageView grid_item_left_group = holder.findView(R.id.grid_item_left_group);


        RelativeLayout grid_item_right_layout = holder.findView(R.id.grid_item_right_layout);
        TextView grid_item_right_sel = holder.findView(R.id.grid_item_right_sel);
        ImageView grid_item_right_image = holder.findView(R.id.grid_item_right_image);
        TextView grid_item_right_alias = holder.findView(R.id.grid_item_right_alias);
        TextView grid_item_right_id = holder.findView(R.id.grid_item_right_id);
        ImageView grid_item_right_group = holder.findView(R.id.grid_item_right_group);


        if (deviceInfo.getDeviceStatus() == Entiy.DEVEICE_UNBIND) {
            grid_item_device.setVisibility(View.INVISIBLE);
            grid_item_left_layout.setVisibility(View.INVISIBLE);
            grid_item_right_layout.setVisibility(View.INVISIBLE);
            grid_item_device_name.setVisibility(View.INVISIBLE);
            grid_item_device_value.setVisibility(View.INVISIBLE);
        } else {
            grid_item_device.setVisibility(View.VISIBLE);
            GlideUtil.loadDeviceImage(getContext(),grid_item_device, deviceInfo);

            if (!TextUtils.isEmpty(deviceInfo.getDeviceName())) {
                grid_item_device_name.setText(deviceInfo.getDeviceName() + "");
                grid_item_device_name.setVisibility(View.VISIBLE);
            }
            grid_item_device_value.setText(deviceInfo.getElectricQuantity() + "%");
            ControlInfo info1 = deviceInfo.getValveDeviceSwitchList().get(0);

            if (info1.getValveStatus() == 0) {
                grid_item_left_layout.setVisibility(View.INVISIBLE);
                grid_item_left_layout.setOnClickListener(null);
            } else {
                grid_item_left_layout.setVisibility(View.VISIBLE);
                grid_item_left_image.setVisibility(View.VISIBLE);
                GlideUtil.loadControlImage(getContext(), grid_item_left_image, info1);
                grid_item_left_sel.setVisibility(View.VISIBLE);
                grid_item_left_id.setText(info1.getValveName() + "");
                grid_item_left_alias.setText(info1.getValveAlias() + "");
                if (info1.isSelect()) {
                    grid_item_left_sel.setBackgroundResource(R.drawable.img_selected);
                } else {
                    grid_item_left_sel.setBackgroundResource(R.drawable.img_unselected);
                }

                if (info1.getValveGroupId() == 0) {
                    grid_item_left_group.setVisibility(View.INVISIBLE);
                } else {
                    grid_item_left_group.setVisibility(View.VISIBLE);
                    GlideUtil.loadGroupImage(grid_item_left_group, info1.getValveGroupId());
                }

                if (info1.getValveGroupId() > 0) {
                    grid_item_left_sel.setVisibility(View.GONE);
                    deviceInfo.getValveDeviceSwitchList().get(0).setSelect(false);
                } else {
                    grid_item_left_layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (NoFastClickUtils.isFastClick()) {
                                return;
                            }
                            deviceInfo.getValveDeviceSwitchList().get(0).setSelect(!deviceInfo.getValveDeviceSwitchList().get(0).isSelect());
                            notifyDataSetChanged();
                        }
                    });
                }
            }

            ControlInfo info2 = deviceInfo.getValveDeviceSwitchList().get(1);
            if (info2.getValveStatus() == 0) {
                grid_item_right_layout.setVisibility(View.INVISIBLE);
            } else {
                grid_item_right_layout.setVisibility(View.VISIBLE);
                grid_item_right_image.setVisibility(View.VISIBLE);
                GlideUtil.loadControlImage(getContext(), grid_item_right_image, info2);
                grid_item_right_sel.setVisibility(View.VISIBLE);
                grid_item_right_id.setText(info2.getValveName() + "");
                grid_item_right_alias.setText(info2.getValveAlias() + "");

                if (info2.isSelect()) {
                    grid_item_right_sel.setBackgroundResource(R.drawable.img_selected);
                } else {
                    grid_item_right_sel.setBackgroundResource(R.drawable.img_unselected);
                }

                if (info2.getValveGroupId() == 0) {
                    grid_item_right_group.setVisibility(View.INVISIBLE);
                } else {
                    grid_item_right_group.setVisibility(View.VISIBLE);
                    GlideUtil.loadGroupImage(grid_item_right_group, info2.getValveGroupId());
                }

                if (info2.getValveGroupId() > 0) {
                    grid_item_right_sel.setVisibility(View.GONE);
                    deviceInfo.getValveDeviceSwitchList().get(1).setSelect(false);
                } else {
                    grid_item_right_layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (NoFastClickUtils.isFastClick()) {
                                return;
                            }
                            deviceInfo.getValveDeviceSwitchList().get(1).setSelect(!deviceInfo.getValveDeviceSwitchList().get(1).isSelect());
                            notifyDataSetChanged();
                        }
                    });
                }
            }
        }

    }
}
