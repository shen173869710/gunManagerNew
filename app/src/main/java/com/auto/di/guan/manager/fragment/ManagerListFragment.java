package com.auto.di.guan.manager.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.activity.ManagerActivity;
import com.auto.di.guan.manager.adapter.MyListAdapter;
import com.auto.di.guan.manager.db.User;
import com.auto.di.guan.manager.entity.Entiy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ManagerListFragment extends ListFragment {
	public MyListAdapter adapter;
	private FragmentManager manager;
	private FragmentTransaction transaction;
	private ArrayList<Fragment>fragments = new ArrayList<Fragment>();
	private ManagerActivity activity;

	private List<User> users = new ArrayList<>();

	@Override
	public void onAttach(@NonNull Context context) {
		super.onAttach(context);
		this.activity = (ManagerActivity)context;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		users = (List<User>) getArguments().getSerializable(Entiy.INTENT_USER_LIST);
		if (users == null) {
			users = new ArrayList<>();
		}
		manager = getFragmentManager();
		adapter = new MyListAdapter(activity, Entiy.MANAGER_ITEM);
		setListAdapter(adapter);
		fragments.add(new ManagerTab0());
		Bundle bundle = new Bundle();
		bundle.putSerializable(Entiy.INTENT_USER_LIST, (Serializable) users);
		fragments.add(ManagerTab1.newInstance(bundle));
		fragments.add(ManagerTab2.newInstance(bundle));
		fragments.add(ManagerTab3.newInstance(bundle));
		fragments.add(ManagerTab4.newInstance(bundle));
		fragments.add(ManagerTab5.newInstance(bundle));
		transaction = manager.beginTransaction();
		transaction.add(R.id.manager_info, fragments.get(0), Entiy.MANAGER_ITEM[0]).show(fragments.get(0));
		transaction.commitAllowingStateLoss();
		activity.setRightInVisible();
		activity.setTitle(Entiy.MANAGER_ITEM[0]);
		adapter.setSelectedPosition(0);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		activity.setTitle(Entiy.MANAGER_ITEM[position]);
		adapter.setSelectedPosition(position);
		showFragment(fragments.get(position));
		if (position == 2 || position == 3 || position == 4) {
			activity.setRightVisible(position);
		}else {
			activity.setRightInVisible();
		}
		activity.setIndex(position);
		adapter.notifyDataSetChanged();
		/*发送点击事件*/

	}


	private void showFragment(Fragment fragment) {
		if(null == fragment) {
			return;
		}
		FragmentManager fragmentManager = activity.getSupportFragmentManager();
		String fragmentTag = fragment.getClass().getSimpleName();
		addFragment(fragmentManager, fragment, fragmentTag);
		hideAllFragment(fragmentManager,fragment);
		fragmentManager.beginTransaction().show(fragment).commitAllowingStateLoss();
	}

	private void addFragment(FragmentManager fm, Fragment fragment, String tag) {
		if(null !=fm && !fm.isDestroyed() && null !=fragment && !fragment.isAdded() && null == fm.findFragmentByTag(tag) && !TextUtils.isEmpty(tag)){
			FragmentTransaction ft = fm.beginTransaction();
			if (null !=ft) {
				fm.executePendingTransactions();
				ft.add(R.id.manager_info, fragment,tag);
				ft.commitAllowingStateLoss();
			}
		}
	}

	/**
	 *
	 * @param fm
	 * @param fragment  隐藏 其他 fragment
	 */
	private void hideAllFragment(FragmentManager fm, Fragment fragment) {
		if(null !=fm && !fm.isDestroyed() && null !=fragment &&!TextUtils.isEmpty(fragment.getTag()) && null !=fragments && fragments.size()>0){
			FragmentTransaction ft = fm.beginTransaction();
			if(null !=ft){
				for (Fragment frag : fragments) {
					if (!fragment.getTag().equals(frag.getTag())) {
						ft.hide(frag);
					}
				}
				ft.commitAllowingStateLoss();
			}
		}
	}
}
