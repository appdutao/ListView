package com.dutao.listview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dutao.listview.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class MainActivity extends Activity {

	@ViewInject(R.id.bt_first)
	private Button bt_first;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ViewUtils.inject(this);
	}

	@OnClick(R.id.bt_first)
	public void bt_First(View view){
		Intent intent_first = new Intent(this,FirstActivity.class);
		startActivity(intent_first);
	}
}
