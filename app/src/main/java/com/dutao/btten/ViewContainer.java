package com.dutao.btten;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public class ViewContainer extends FrameLayout {

	IViewFactory mViewFactory;
	private View tempView = null;
	private View currentView = null;
	private BaseView baseView = null;
	private Map<Integer, View> viewMap = null;
	private int cuIndex;
	LayoutParams newLayout = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

	public ViewContainer(Context context) {
		super(context);
		init();
	}

	public ViewContainer(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ViewContainer(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		viewMap = new HashMap<Integer, View>();
		cuIndex = -1;
	}

	public void setViewFactory(IViewFactory viewFactory) {
		mViewFactory = viewFactory;
	}

	public View getCurrentView() {
		return currentView;
	}

	public void flipToView(int index) {
		if (index == cuIndex)
			return;
		if (viewMap.containsKey(index)) {
			tempView = viewMap.get(index);
			setViewState();
			return;
		}
		tempView = mViewFactory.createView(index);
		viewMap.put(index, tempView);
		this.addView(tempView, newLayout);
		setViewState();
	}

	private void setViewState() {
		if (currentView != null) {
			baseView = (BaseView) currentView.getTag();
			currentView.setVisibility(View.GONE);
			baseView.OnViewHide();
		}
		baseView = (BaseView) tempView.getTag();
		tempView.setVisibility(View.VISIBLE);
		baseView.OnViewShow();

		currentView = tempView;
	}
}
