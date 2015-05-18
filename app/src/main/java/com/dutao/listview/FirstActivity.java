package com.dutao.listview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;

import com.dutao.listview.R;
import com.dutao.listview.pager.ViewPagerAdapter;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class FirstActivity extends Activity {

	@ViewInject(R.id.list_view_first)
	private ListView listview;
	
	private final String data[] = { "one", "two", "three", "four", "five", "six", "seven" }; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		
		ViewUtils.inject(this);
		initHeadView();
	}

	private void initHeadView()
    {
        View view = LayoutInflater.from(this).inflate(R.layout.viewpager_list, null);
        ViewPager viewPager = (ViewPager)view.findViewById(R.id.head);    
        List<ImageView> listtemp = new ArrayList<ImageView>();
        for(int i = 0;i<4;i++)
        {
            ImageView img = new ImageView(this);
            img.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,100));
            img.setScaleType(ScaleType.FIT_XY);
            img.setBackgroundResource(R.drawable.ic_launcher);
            listtemp.add(img);
        }      
//        ViewPagerAdapter viewadapter = new ViewPagerAdapter(listtemp,m);       
        listview.addHeaderView(view);
        listview.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data));
//        viewPager.setAdapter(viewadapter);
    }

}
