package com.dutao.viewpager;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dutao.listview.R;
import com.dutao.main.BaseActivity;
import com.dutao.model.BottomViewItem;

public class ViewPagerActivity extends BaseActivity implements OnClickListener, OnPageChangeListener {

	ViewPager viewPager;
	ViewPagerAdapter viewAdapter;
	BottomViewItem item;
	ArrayList<View> mViewItems = new ArrayList<View>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager_layout);
		item = BottomViewItem.getInstance();
		initViews();
		setTabSelection(0);
	}

	/**
	 * 控件初始化
	 */
	private void initViews() {
		viewPager = (ViewPager) findViewById(R.id.main_viewpager);
		for (int i = 0; i < item.viewNum; i++) {
		    View view = getLayoutInflater().inflate(item.layouts_id[i], null);
			mViewItems.add(view);
		}
		viewAdapter = new ViewPagerAdapter(this, mViewItems);
		viewPager.setAdapter(viewAdapter);
		viewPager.setOnPageChangeListener(this);
		for (int i = 0; i < item.viewNum; i++) {
			item.linears[i] = (LinearLayout) findViewById(item.linears_id[i]);
			item.linears[i].setOnClickListener(this);
			item.images[i] = (ImageView) findViewById(item.images_id[i]);
			item.texts[i] = (TextView) findViewById(item.texts_id[i]);
		}
	}

	/**
	 * @param index
	 *            根据索引值切换fragment
	 */
	private void setTabSelection(int index) {
		clearSelection();
		item.images[index].setImageResource(item.images_selected[index]);
		item.texts[index].setTextColor(getResources().getColor(R.color.bottom_text_selected));
	}

	/**
	 * 清空所有图标和文字状态
	 */
	private void clearSelection() {
		for (int i = 0; i < item.viewNum; i++) {
			item.images[i].setImageResource(item.images_unselected[i]);
			item.texts[i].setTextColor(getResources().getColor(R.color.bottom_text_unselected));
		}
	}

	@Override
	public void onClick(View v) {
		for (int i = 0; i < item.linears_id.length; i++)
			if (v.getId() == item.linears_id[i]) {
				viewPager.setCurrentItem(i);
				setTabSelection(i);
			}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int arg0) {
		setTabSelection(arg0);
	}
}
