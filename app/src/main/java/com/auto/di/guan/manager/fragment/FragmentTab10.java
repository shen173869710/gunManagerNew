package com.auto.di.guan.manager.fragment;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.adapter.QuareUserAdapter;
import com.auto.di.guan.manager.api.ApiUtil;
import com.auto.di.guan.manager.api.HttpManager;
import com.auto.di.guan.manager.app.BaseApp;
import com.auto.di.guan.manager.basemodel.model.request.BaseRequest;
import com.auto.di.guan.manager.basemodel.model.respone.BaseRespone;
import com.auto.di.guan.manager.db.ControlInfo;
import com.auto.di.guan.manager.db.User;
import com.auto.di.guan.manager.db.UserAction;
import com.auto.di.guan.manager.dialog.MainChooseDialog;
import com.auto.di.guan.manager.dialog.MainChooseIdDialog;
import com.auto.di.guan.manager.entity.Entiy;
import com.auto.di.guan.manager.entity.TableDataInfo;
import com.auto.di.guan.manager.event.DateChangeEvent;
import com.auto.di.guan.manager.utils.LogUtils;
import com.auto.di.guan.manager.utils.NoFastClickUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;


public class FragmentTab10 extends BaseFragment {
	@BindView(R.id.querybytime)
	TextView querybytime;
	@BindView(R.id.querybyid)
	TextView querybyid;
	@BindView(R.id.querylistview)
	RecyclerView querylistview;

	private QuareUserAdapter adapter;
	private List<User> users = new ArrayList<>();
	private List<UserAction> userActions = new ArrayList<>();
	private List<ControlInfo> controlInfo = new ArrayList<>();
	int i = 0;


	private void showChooseTimeDialog(final TextView tv) {
		List<ControlInfo> controlInfoList = new ArrayList<>();
		ControlInfo info1 = new ControlInfo();
		ControlInfo info2 = new ControlInfo();
		info1.setValveName("删除全部");
		info2.setValveName("删除报警");
		controlInfoList.add(info1);
		controlInfoList.add(info2);
		final MainChooseIdDialog chooseDialog = new MainChooseIdDialog(getActivity(), controlInfoList);
		chooseDialog.setOnPositiveListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (NoFastClickUtils.isFastClick()) {
					return;
				}
				showChooseListByTime(chooseDialog.currentItem);
				chooseDialog.dismiss();
			}


		});
		chooseDialog.setOnNegativeListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				chooseDialog.dismiss();
			}
		});
		chooseDialog.show();
	}

	private void showChooseDialog(final TextView tv) {
		//	users = UserSql.queryUserList();
		if (users == null && users.size() == 0) {
			Toast.makeText(getActivity(), "暂无可以查询的用户", Toast.LENGTH_LONG).show();
			return;
		}
		final MainChooseDialog chooseDialog = new MainChooseDialog(getActivity(), users);
		chooseDialog.setOnPositiveListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (NoFastClickUtils.isFastClick()) {
					return;
				}
				if (chooseDialog.currentItem >= 0) {
					String name = users.get(chooseDialog.currentItem).getUserName();
					tv.setText(name);
					showChooseListByName(chooseDialog.currentItem);
				} else {
					Toast.makeText(getActivity(), "暂无可以查询的信息", Toast.LENGTH_LONG).show();
				}
				chooseDialog.dismiss();
			}


		});
		chooseDialog.setOnNegativeListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				chooseDialog.dismiss();
			}
		});
		chooseDialog.show();
	}


	private void showChooseIdDialog(final TextView tv) {
		List<ControlInfo> controlInfoList = new ArrayList<>();
		ControlInfo info1 = new ControlInfo();
		ControlInfo info2 = new ControlInfo();
		ControlInfo info3 = new ControlInfo();
		ControlInfo info4 = new ControlInfo();
		ControlInfo info5 = new ControlInfo();
		info1.setValveName("全部");
		info2.setValveName("只看手动单个轮灌");
		info3.setValveName("只看手动分组轮灌");
		info4.setValveName("只看自动分组轮灌");
		info5.setValveName("只看报警");
		controlInfoList.add(info1);
		controlInfoList.add(info2);
		controlInfoList.add(info3);
		controlInfoList.add(info4);
		controlInfoList.add(info5);
		final MainChooseIdDialog chooseDialog = new MainChooseIdDialog(getActivity(), controlInfoList);
		chooseDialog.setOnPositiveListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (NoFastClickUtils.isFastClick()) {
					return;
				}
				int actionStatus = -1;
				int type = 0;
				switch (chooseDialog.currentItem) {
					case 0:
						type = 0;
						break;
					case 1:
						type = Entiy.ACTION_TYPE_4;
						break;
					case 2:
						type = Entiy.ACTION_TYPE_31;
						break;
					case 3:
						type = Entiy.ACTION_TYPE_32;
						break;
					case 4:
						type = Entiy.ACTION_TYPE_ERROR;
						actionStatus = 0;
						break;
				}
				showListByType(type, actionStatus);
				chooseDialog.dismiss();
			}


		});
		chooseDialog.setOnNegativeListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				chooseDialog.dismiss();
			}
		});
		chooseDialog.show();
	}

	private void showChooseListByName(int postion) {
//		List<UserAction>action = UserActionSql.queryUserActionlList(users.get(postion).getLoginName());
//		showEnd(action);
	}

	private void showListByType(int type, int actionStatus) {
//		List<UserAction>action= new ArrayList<>();
//		if (type == 0) {
//			action	= UserActionSql.queryUserActionlList();
//		}else {
//			if (type == Entiy.ACTION_TYPE_ERROR) {
//				action	= UserActionSql.queryUserActionlListByEnd(false);
//			}else {
//				action	= UserActionSql.queryUserActionlListByType(type);
//			}
//
//		}
//		showEnd(action);
		syncData(type, actionStatus);
	}

	private void showChooseListByTime(int type) {
//		List<UserAction>action= new ArrayList<>();
//		if (type == 0) {
//			UserActionSql.deleteUserActionAll();
//		}else {
//			UserActionSql.deleteUserActionType();
//		}
//		action	= UserActionSql.queryUserActionlList(0);
//		showEnd(action);
	}

	private void showEnd(List<UserAction> action) {
		if (action.size() == 0) {
			Toast.makeText(getActivity(), "暂无可以查询的信息", Toast.LENGTH_LONG).show();
			userActions.clear();
			adapter.notifyDataSetChanged();
		} else {
			userActions.clear();
			userActions.addAll(action);
			adapter.notifyDataSetChanged();
		}
	}




	@Override
	public int setLayout() {
		return R.layout.fragment_10;
	}

	@Override
	public void init() {
		adapter = new QuareUserAdapter(userActions);
		querylistview.setLayoutManager(new LinearLayoutManager(getActivity()));
		querylistview.setAdapter(adapter);

		querybytime.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(NoFastClickUtils.isFastClick()){
					return;
				}
				showChooseTimeDialog(querybytime);
			}
		});

		querybyid.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(NoFastClickUtils.isFastClick()){
					return;
				}
				showChooseIdDialog(querybyid);
			}
		});
		syncData(0, -1);
	}

	@Override
	public void dataChange(DateChangeEvent event) {

	}

	/**
	 *  数据同步
	 */
	public void syncData(int actionType, int actionStatus) {
		TreeMap<String, Object> treeMap = new TreeMap<>();
		treeMap.put("userId", Integer.valueOf(BaseApp.getLoginId()));
		if (actionStatus == -1) {
			treeMap.put("actionType",actionType);
		}else {
			treeMap.put("actionStatus",actionStatus);
		}
		HttpManager.syncData(ApiUtil.createApiService().getActions(BaseRequest.toMerchantTreeMap(treeMap)), new HttpManager.OnResultListener() {
			@Override
			public void onSuccess(BaseRespone t) {
				LogUtils.e("TAG", "获取数据成");
				if (t != null && t.getData() != null) {
					TableDataInfo info = (TableDataInfo) t.getData();
					if (info != null && info.getRows() != null) {
						showEnd(info.getRows());
					}
				}
			}
			@Override
			public void onError(Throwable error, Integer code, String msg) {
				LogUtils.e("TAG", "获取数据失败");
			}
		});

	}


}
