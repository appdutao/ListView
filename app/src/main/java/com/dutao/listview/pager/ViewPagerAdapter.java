package com.dutao.listview.pager;

import java.util.List;

import com.dutao.listview.ListContentActivity;
import com.dutao.viewpager.ViewPagerActivity;
import com.lidroid.xutils.util.LogUtils;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ViewPagerAdapter extends PagerAdapter implements OnClickListener{
	
	private List<ImageView> list;
	private ViewPagerActivity mContext;
	private ImageView imageView;
	
	public ViewPagerAdapter(List<ImageView> list,ViewPagerActivity mContext) {
		this.list = list;
		this.mContext = mContext;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager)container).removeView(list.get(position));
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
	    imageView = list.get(position);
	    imageView.setTag(position);
		((ViewPager) container).addView(imageView);
		
		imageView.setOnClickListener(this);
		return list.get(position);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return (arg0 == arg1);
	}

    @Override
    public void onClick(View v)
    {
        Intent intent = new Intent(mContext,ListContentActivity.class);
        LogUtils.i("第"+v.getTag()+"张图片被点击了");
    }

}
