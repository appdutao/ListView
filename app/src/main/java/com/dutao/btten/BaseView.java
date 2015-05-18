package com.dutao.btten;

import android.app.Activity;
import android.view.View;

public class BaseView {

	protected Activity mContext;
	protected View view;
	public static BttenTabbar mTab;

	public BaseView(Activity context, int layoutId, BttenTabbar tab) {
		mContext = context;
		mTab = tab;
		view = mContext.getLayoutInflater().inflate(layoutId, null);
		view.setTag(this);
	}

	/**
	 * @return 返回真实的view
	 */
	public View getView() {
		return view;
	}

	/**
	 * @return 返回容器
	 */
	public BttenTabbar getTab() {
		return mTab;
	}

	/**
	 * 进入view时触发
	 */
	public void OnViewShow() {

	};

	/**
	 * 退出view时触发
	 */
	public void OnViewHide() {

	};
}
