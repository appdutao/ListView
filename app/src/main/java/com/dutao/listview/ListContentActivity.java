package com.dutao.listview;

import com.dutao.bean.ItemTittle;
import com.dutao.listview.R;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.ImageView;
import android.widget.TextView;

public class ListContentActivity extends Activity
{
    @ViewInject(R.id.content_tittle)
    private TextView content_tittle;
    @ViewInject(R.id.content_time)
    private TextView content_time;
    @ViewInject(R.id.content_image)
    private ImageView content_image;
    
    private BitmapUtils bitmapUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_content);
        
        ViewUtils.inject(this);
        bitmapUtils = new BitmapUtils(this);
        Bundle bundle = this.getIntent().getExtras();
        initContent(bundle);
    }

    /**
     * ��ʼ������
     * 
     * @param bundle
     */
    private void initContent(Bundle bundle){
        ItemTittle itemTittle = (ItemTittle) bundle.get("ItemTittle");
        content_tittle.setText(itemTittle.getTittle());
        content_time.setText(DateFormat.format("yyyy-MM-dd", itemTittle.getTime()));
        bitmapUtils.display(content_image, itemTittle.getImageUrl());
    }
}
