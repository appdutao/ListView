package com.dutao.btten;

import android.os.Bundle;

import com.dutao.listview.R;
import com.dutao.main.BaseActivity;

public class BttenActivity extends BaseActivity {

	BttenTabbar tabbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.btten_layout);
		tabbar = new BttenTabbar(this);
	}

}
