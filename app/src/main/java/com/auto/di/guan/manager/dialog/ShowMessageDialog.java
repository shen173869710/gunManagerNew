package com.auto.di.guan.manager.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.utils.NoFastClickUtils;


public class ShowMessageDialog extends Dialog {

	public Button sure_load_cancle;
	public Button sure_load_sure;
	public TextView sure_load_title;
	public EditText sure_load_count_value;
	public EditText sure_load_group_value;
	public View mView;
	public ShowMessageDialog(Context context) {
		super(context, R.style.UpdateDialog);
		setCustomView();
	}

	public ShowMessageDialog(Context context, boolean cancelable,
                             OnCancelListener cancelListener) {
		super(context, R.style.UpdateDialog);
		this.setCancelable(cancelable);
		this.setOnCancelListener(cancelListener);
		setCustomView();
	}

	public ShowMessageDialog(Context context, int theme) {
		super(context, R.style.UpdateDialog);
		setCustomView();
	}

	private void setCustomView() {
		mView = LayoutInflater.from(getContext()).inflate(R.layout.sure_load_dialog, null);
		sure_load_title =  (TextView) mView.findViewById(R.id.sure_load_title);
		sure_load_count_value = (EditText) mView.findViewById(R.id.sure_load_count_value);
		sure_load_group_value = (EditText) mView.findViewById(R.id.sure_load_group_value);
		sure_load_sure = (Button) mView.findViewById(R.id.sure_load_sure);
		sure_load_cancle = (Button) mView.findViewById(R.id.sure_load_cancle);
	
		super.setContentView(mView);
	}

	@Override
	public void setCanceledOnTouchOutside(boolean cancel) {
		super.setCanceledOnTouchOutside(cancel);
	}

	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
	}

	public void setOnPositiveListener(View.OnClickListener listener) {
		sure_load_sure.setOnClickListener(listener);
	}

	public void setOnNegativeListener(View.OnClickListener listener) {
		sure_load_cancle.setOnClickListener(listener);
	}

	public static void ShowDialog(final Activity context,final View.OnClickListener listener) {
		final ShowMessageDialog dialog = new ShowMessageDialog(context);
		dialog.setOnPositiveListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(NoFastClickUtils.isFastClick()){
					return;
				}
				if (listener != null) {
					String tag = dialog.sure_load_count_value.getText().toString().trim();
					String group = dialog.sure_load_group_value.getText().toString().trim();

					if (TextUtils.isEmpty(tag) || TextUtils.isEmpty(group)) {
						Toast.makeText(context, "??????????????????",Toast.LENGTH_LONG).show();
						return;
					}

//					ShareUtil.setStringLocalValue(context,Entiy.GROUP_NAME, group);
//					if (!TextUtils.isEmpty(tag)) {
//						v.setTag(tag);
//						listener.onClick(v);
//					}
				}
				dialog.dismiss();
			}
		});

		dialog.setOnNegativeListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		LayoutParams lay = dialog.getWindow().getAttributes();
		DisplayMetrics dm = new DisplayMetrics();// ?????????????????????
		context.getWindowManager().getDefaultDisplay().getMetrics(dm);//
		Rect rect = new Rect();
		View view = context.getWindow().getDecorView();
		view.getWindowVisibleDisplayFrame(rect);
		lay.width = dm.widthPixels * 9 / 10;
		dialog.show();
	}


}
