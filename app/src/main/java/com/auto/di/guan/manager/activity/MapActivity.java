package com.auto.di.guan.manager.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.db.User;
import com.auto.di.guan.manager.entity.Entiy;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

public class MapActivity extends Activity{

//	@BindView(R.id.title_bar_back_layout)
	RelativeLayout titleBarBackLayout;
//	@BindView(R.id.bmapView)
	MapView mMapView;
//	@BindView(R.id.title_bar_title)
	TextView titleBarTitle;
	private BaiduMap mBaiduMap;
	private User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		// 如果数据表里面没有用户信息

		titleBarBackLayout = findViewById(R.id.title_bar_back_layout);
		titleBarTitle = findViewById(R.id.title_bar_title);
		titleBarTitle.setText("项目信息");
		titleBarBackLayout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		user = (User) getIntent().getSerializableExtra(Entiy.INTENT_USER);
		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();

		//定义Maker坐标点
		LatLng point = new LatLng(39.963175, 116.400244);
		View view = LayoutInflater.from(this).inflate(R.layout.map_maker_layout, null);

		// 项目名称
		TextView item_project = view.findViewById(R.id.item_project);
		// 地理位置
		TextView item_address = view.findViewById(R.id.item_address);
		// 地理坐标
		TextView item_local = view.findViewById(R.id.item_local);
		// 种植作物
		TextView item_raisecrops = view.findViewById(R.id.item_raisecrops);
		// 品    种
		TextView item_varieties = view.findViewById(R.id.item_varieties);
		// 种植面积
		TextView item_plantingarea = view.findViewById(R.id.item_plantingarea);
		// 负责人员姓名
		TextView item_name = view.findViewById(R.id.item_name);
		// 联系电话
		TextView item_phone = view.findViewById(R.id.item_phone);
		//管网参数
		TextView item_pram = view.findViewById(R.id.item_pram);
		//出地桩
		TextView item_pileOutNum = view.findViewById(R.id.item_pileOutNum);
		item_project.setText("项目名称: "+user.getProjectName());
		item_address.setText("地理位置: "+user.getAddress());
		item_local.setText("地理坐标: "+user.getLongitudeLatitude());
		item_raisecrops.setText("种植作物: "+user.getRaisecrops());
		item_varieties.setText("种植品种: "+user.getVarieties());
		item_plantingarea.setText("种植面积: "+user.getPlantingarea());
		item_name.setText("负责人员: "+user.getUserName()+"");
		item_phone.setText("联系电话: "+user.getPhonenumber());
		item_pram.setText("管网参数: "+user.getTrunkPipeNum()+"/"+user.getTrunkPipeMaxNum());
		item_pileOutNum.setText("出地桩: "+user.getTrunkPipeNum());

		BitmapDescriptor bitmap = BitmapDescriptorFactory.fromView(view);
//构建Marker图标
//		BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher);
//构建MarkerOption，用于在地图上添加Marker
		OverlayOptions option = new MarkerOptions()
				.position(point)
				.icon(bitmap);
//在地图上添加Marker，并显示
		mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
//		mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
		mBaiduMap.addOverlay(option);
		MapStatus mMapStatus = new MapStatus.Builder().target(point)
				.zoom(18)
				.build();
		MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
		mBaiduMap.setMapStatus(mMapStatusUpdate);
	}



	@Override
	protected void onResume() {
		super.onResume();
		//在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
		mMapView.onResume();
	}
	@Override
	protected void onPause() {
		super.onPause();
		//在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
		mMapView.onPause();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		//在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
		if (mMapView != null) {
			mMapView.onDestroy();
		}
	}
}
