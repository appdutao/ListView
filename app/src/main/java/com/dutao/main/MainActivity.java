package com.dutao.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.dutao.btten.BttenActivity;
import com.dutao.listview.ActionBarActivity;
import com.dutao.listview.R;
import com.dutao.viewpager.ViewPagerActivity;

public class MainActivity extends Activity implements OnClickListener
{

    Intent intent;
    private int[] viewsId = new int[]{ R.id.btten_view_btn, R.id.btten_viewpager_btn, R.id.btten_actionBar_btn };
    private Button[] viewsBtn = new Button[viewsId.length];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        initViews();
    }

    private void initViews()
    {
        for (int i = 0; i < viewsId.length; i++)
        {
            viewsBtn[i] = (Button) findViewById(viewsId[i]);
            viewsBtn[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btten_view_btn:
                intent = new Intent(this, BttenActivity.class);
                startActivity(intent);
                break;

            case R.id.btten_viewpager_btn:
                intent = new Intent(this, ViewPagerActivity.class);
                startActivity(intent);
                break;
                
            case R.id.btten_actionBar_btn:
                intent = new Intent(this, ActionBarActivity.class);
                startActivity(intent);
                break;
        }
    }
}
