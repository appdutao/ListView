package com.dutao.btten;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dutao.listview.R;
import com.dutao.model.BottomViewItem;

public class BttenTabbar implements IViewFactory, OnClickListener {

	/**
	 * 用于保存当前activity
	 */
	BttenActivity mContext;
	/**
	 * 当前页面焦点,即显示的页面索引
	 */
	int mCurrentFocus = -1;
	/**
	 * 切换页面时上一个view的索引
	 */
	int lastIndex = -1;
	/**
	 * 底部菜单栏初始化所有控件类的一个实例
	 */
	BottomViewItem item;

	/**
	 * 存放view的布局容器
	 */
	ViewContainer viewContainer;

	/**
	 * 首次创建页面的临时view
	 */
	public BaseView newView;

	public BttenTabbar(BttenActivity context) {
		mContext = context;
		item = BottomViewItem.getInstance();
		initTab();
	}

	/**
	 * 控件初始化
	 */
	private void initTab() {
		for (int i = 0; i < item.viewNum; i++) {
			item.linears[i] = (LinearLayout) mContext.findViewById(item.linears_id[i]);
			item.linears[i].setOnClickListener(this);
			item.images[i] = (ImageView) mContext.findViewById(item.images_id[i]);
			item.texts[i] = (TextView) mContext.findViewById(item.texts_id[i]);
		}
		viewContainer = (ViewContainer) mContext.findViewById(R.id.main_view_container);
		viewContainer.setViewFactory(this);
		switchViewTab(0);
	}

	/**
	 * @param index
	 *            根据索引值切换view
	 */
	public void switchViewTab(int index) {
		if (index == mCurrentFocus)
			return;
		setViewTab(index);
		viewContainer.flipToView(index);
	}

	/**
	 * @param index
	 *            根据索引值切换背景
	 */
	private void setViewTab(int index) {
		if (index == mCurrentFocus)
			return;
		lastIndex = mCurrentFocus;
		mCurrentFocus = index;
		for (int i = 0; i < item.viewNum; i++) {
			item.images[i].setBackgroundResource(i == index ? item.images_selected[i] : item.images_unselected[i]);
			item.texts[i].setTextColor(i == index ? mContext.getResources().getColor(R.color.bottom_text_selected) : mContext.getResources().getColor(R.color.bottom_text_unselected));
		}
	}

	@Override
	public void onClick(View v) {
		for (int i = 0; i < item.viewNum; i++) {
			if (v.getId() == item.linears_id[i]) {
				switchViewTab(i);
			}
		}
	}

	@Override
	public View createView(int index) {
		newView = new BaseView(mContext, item.layouts_id[index], this);
		return newView.getView();
	}
}
