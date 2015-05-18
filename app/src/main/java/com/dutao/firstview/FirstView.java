package com.dutao.firstview;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dutao.bean.ItemTittle;
import com.dutao.listview.ListContentActivity;
import com.dutao.listview.R;
import com.dutao.listview.pager.ViewPagerAdapter;
import com.dutao.viewpager.ViewPagerActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.util.LogUtils;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京紫光华宇软件股份有限公司<br>
 * @author dutao
 * @version 1.0 
 * @date 2015-5-14
 */
public class FirstView implements OnPageChangeListener
{

    /**
     * 保存当前activity引用
     */
    private ViewPagerActivity mContext;
    
    /**
     * 保存当前View引用
     */
    private View view;
    
    public FirstView(ViewPagerActivity context,View view)
    {
        this.mContext = context;
        this.view = view;
    }

    private ListView lv_second;// 首页List
    private LinearLayout viewpager_list_linear;//下方的切换点

    private BitmapUtils bitmapUtils;

    private LayoutInflater inflater;
    private ViewPagerHolder pagerHolder;
    private ViewHolder holder;
    private int currentIndex;
    private int DOT_COUNT = 4;//viewpager图片数量
    private ImageView[] ivs = new ImageView[DOT_COUNT];

    /**
     * 初始化主页ListView
     */
    public void initFirstView()
    {
        lv_second = (ListView) view.findViewById(R.id.lv_second);
        viewpager_list_linear = (LinearLayout) view.findViewById(R.id.viewpager_list_linear);
        bitmapUtils = new BitmapUtils(mContext);
        inflater = mContext.getLayoutInflater();
        initListViewPager();
    }

    /**
     * 初始化ViewPager
     */
    private void initListViewPager()
    {
        //发送请求，解析数据
        Gson gson = new Gson();
        ArrayList<ItemTittle> mData = gson.fromJson(getJsonFromObj(), new TypeToken<List<ItemTittle>>(){}.getType());
        
        lv_second.setAdapter(new MyAdapter(mData));
        //设置ListView中Item的点击事件
        lv_second.setOnItemClickListener(new MyOnItemClick(mData));
    }

    /**
     * Title: ListView中item点击事件<br>
     * Description: <br>
     * Copyright: Copyright (c) 2007<br>
     * Company: 北京紫光华宇软件股份有限公司<br>
     * @author dutao
     * @version 1.0 
     * @date 2015-5-14
     */
    private class MyOnItemClick implements OnItemClickListener{
        private ArrayList<ItemTittle> mData;
        
        public MyOnItemClick(ArrayList<ItemTittle> mData)
        {
            this.mData = mData;
        }

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int position,long arg3)
        {
            Intent intent = new Intent(mContext,ListContentActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("ItemTittle", mData.get(position-1));
            intent.putExtras(bundle);
            mContext.startActivity(intent);
        }
        
    }
    
    /**
     * Title:ListView自定义数据适配器 <br>
     * Description: <br>
     * Copyright: Copyright (c) 2007<br>
     * Company: 北京紫光华宇软件股份有限公司<br>
     * @author dutao
     * @version 1.0 
     * @date 2015-5-14
     */
    private class MyAdapter extends BaseAdapter
    {

        private ArrayList<ItemTittle> mData;

        public MyAdapter(ArrayList<ItemTittle> Data)
        {
            this.mData = Data;// 初始化数据
        }

        /**
         * ListView的长度
         */
        @Override
        public int getCount()
        {
            return mData.size()+1;//因为第0个为ViewPager
        }

        /**
         * 当有Item点击的时候返回的点击对象
         */
        @Override
        public Object getItem(int position)
        {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        /**
         * 绘制ListView中的没一个Item
         * 
         * @param position
         *            当前位置
         * @param convertView
         *            当前的view
         * @param parent
         *            parent-View
         * @return 最终表现出的item view
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View view = null;
            if (getItemViewType(position) == 0)// 顶部的viewpager
            {
                if (convertView == null)
                {
                    pagerHolder = new ViewPagerHolder();

                    view = inflater.inflate(R.layout.viewpager_list, null);
                    pagerHolder.viewPager = (ViewPager) view.findViewById(R.id.head);
                    view.setTag(pagerHolder);

                    List<ImageView> listtemp = new ArrayList<ImageView>();
                    for (int i = 0; i < DOT_COUNT; i++)
                    {
                        ImageView img = new ImageView(mContext);
                        img.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, 100));
                        img.setScaleType(ScaleType.FIT_XY);
//                        bitmapUtils.display(img, "http://g.hiphotos.baidu.com/image/pic/item/d8f9d72a6059252d028952b1359b033b5ab5b9ed.jpg");
                        img.setBackgroundResource(R.drawable.ic_launcher);
                        listtemp.add(img);
                    }

                    ViewPagerAdapter viewadapter = new ViewPagerAdapter(listtemp,mContext);
                    pagerHolder.viewPager.setAdapter(viewadapter);
                    view.setTag(pagerHolder);
                }
                else
                {
                    view = convertView;
                    pagerHolder = (ViewPagerHolder) view.getTag();
                }
                mHandler.sendEmptyMessageDelayed(AUTO_MSG, PHOTO_CHANGE_TIME); 
                pagerHolder.viewPager.setOnPageChangeListener(FirstView.this);
                initDot(view);
            }
            else
            {// 普通的item
                if (convertView == null)
                {
                    holder = new ViewHolder();

                    view = inflater.inflate(R.layout.list_item, null);
                    holder.textViewTittle = (TextView) view.findViewById(R.id.tv_item_tittle);
                    holder.textViewTime = (TextView) view.findViewById(R.id.tv_item_time);
                    holder.imageViewItem = (ImageView) view.findViewById(R.id.iv_item);
                    view.setTag(holder);
                    holder.imageViewItem.setTag(mData.get(position-1).getImageUrl());
                }
                else
                {
                    view = convertView;
                    holder = (ViewHolder) view.getTag();
                }
                holder.textViewTittle.setText(mData.get(position-1).getTittle());
                holder.textViewTime.setText(DateFormat.format("yyyy-MM-dd", mData.get(position-1).getTime()));
                //防止因复用view带来的图片错乱问题，保持显示的View显示对应位置的图片
                if(!holder.imageViewItem.getTag().equals(mData.get(position-1).getImageUrl())){
                    bitmapUtils.display(holder.imageViewItem,mData.get(position-1).getImageUrl());
                }else{
                    bitmapUtils.display(holder.imageViewItem,(String)holder.imageViewItem.getTag());
                }
            }
            return view;
        }

        /**
         * 获取该位置的Item类型
         * 
         * @param position
         *            当前位置
         * @return
         */
        @Override
        public int getItemViewType(int position)
        {
            return position == 0 ? 0 : 1;// -1为viewpager1为普通item
        }

        /**
         * 获取ListView中Item类型个数
         * 
         * @return
         */
        @Override
        public int getViewTypeCount()
        {
            return 2;
        }

    }

    /**
     * 1.找到LinerLaylout,然后找到对应的ImageView 2.设置点击事件，设置Tag方便读取位置
     */
    private void initDot(View view)
    {
        viewpager_list_linear = (LinearLayout) view.findViewById(R.id.viewpager_list_linear);
        for (int i = 0; i < DOT_COUNT; i++)
        {
            ivs[i] = (ImageView) viewpager_list_linear.getChildAt(i);
            ivs[i].setEnabled(true);
            ivs[i].setOnClickListener(new OnClickListener()
            {

                @Override
                public void onClick(View v)
                {
                    int position = (Integer) v.getTag();
                    setCurView(position);
                    setCurDot(position);
                }
            });
            ivs[i].setTag(i);
        }

        int currentIndex = 0;
        ivs[currentIndex].setEnabled(false);
    }

    /**
     * 设置当前页面的位置
     */
    private void setCurView(int position)
    {
        if (position < 0 || position >= 4)
        {
            return;
        }
        pagerHolder.viewPager.setCurrentItem(position);
    }

    /**
     * 设置当前的小点的位置
     */
    private void setCurDot(int positon)
    {
        if (positon < 0 || positon >= DOT_COUNT || currentIndex == positon)
        {
            return;
        }
        ivs[positon].setEnabled(false);
        ivs[currentIndex].setEnabled(true);

        currentIndex = positon;
    }

    /**
     * 此方法是在状态改变的时候调用，其中arg0这个参数有三种状态（0，1，2）。
     * 
     * @param arg0
     *            arg0 ==1的时辰默示正在滑动，arg0==2的时辰默示滑动完毕了，arg0==0的时默示什么都没做。
     */
    @Override
    public void onPageScrollStateChanged(int arg0)
    {
        if(arg0 == 0){
            LogUtils.i("什么都没做");
        }else if(arg0 == 1){
            LogUtils.i("正在滑动");
        }else{
            LogUtils.i("滑动完毕了");
        }
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2)
    {

    }

    @Override
    public void onPageSelected(int arg0)
    {
        setCurDot(arg0);

    }
    private final int AUTO_MSG = 1;
    private final int HANDLE_MSG = AUTO_MSG + 1;
    private static final int PHOTO_CHANGE_TIME = 4000;//定时变量
    private int index = 0;
    private Handler mHandler = new Handler() {
        
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case AUTO_MSG:
                if(index >= DOT_COUNT){
                    index = 0;
                }else{
                    index++;
                }
                setCurView(index);//收到消息后设置当前要显示的图片
                setCurDot(index);  
                mHandler.sendEmptyMessageDelayed(AUTO_MSG, PHOTO_CHANGE_TIME);
                break;
            case HANDLE_MSG:
                mHandler.sendEmptyMessageDelayed(AUTO_MSG, PHOTO_CHANGE_TIME);
                break;
            default:
                break;
            }
        };
    };
    /**
     * Title: ViewHolder暂存Item中的View<br>
     * Description: <br>
     * Copyright: Copyright (c) 2007<br>
     * Company: 北京紫光华宇软件股份有限公司<br>
     * 
     * @author dutao
     * @version 1.0
     * @date 2015-5-14
     */
    private static class ViewHolder
    {
        private TextView textViewTittle;
        private TextView textViewTime;
        private ImageView imageViewItem;
    }

    /**
     * Title: ViewPagerHolder暂存viewPager<br>
     * Description: <br>
     * Copyright: Copyright (c) 2007<br>
     * Company: 北京紫光华宇软件股份有限公司<br>
     * 
     * @author dutao
     * @version 1.0
     * @date 2015-5-14
     */
    private static class ViewPagerHolder
    {
        private ViewPager viewPager;
    }
    
    private String getJsonFromObj(){
        Date today = new Date();
        List<ItemTittle> listItem = new ArrayList<ItemTittle>();
        ItemTittle tittle1 = new ItemTittle("我是第一个Item", today, "http://g.hiphotos.baidu.com/image/pic/item/d8f9d72a6059252d028952b1359b033b5ab5b9ed.jpg");
        ItemTittle tittle2 = new ItemTittle("我是第二个Item", today, "http://imgt7.bdstatic.com/it/u=2,596167679&fm=25&gp=0.jpg");
        ItemTittle tittle3 = new ItemTittle("我是第三个Item", today, "http://c.hiphotos.baidu.com/image/pic/item/6609c93d70cf3bc7b5c46dfcd000baa1cc112add.jpg");
        ItemTittle tittle4 = new ItemTittle("我是第四个Item", today, "http://e.hiphotos.baidu.com/image/w%3D230/sign=b978669fa486c9170803553af93c70c6/e7cd7b899e510fb32396f5f0da33c895d0430ccd.jpg");
        ItemTittle tittle5 = new ItemTittle("我是第五个Item", today, "http://f.hiphotos.baidu.com/image/w%3D230/sign=2f620233d5ca7bcb7d7bc02c8e086b3f/902397dda144ad3404eebc77d2a20cf430ad85e8.jpg");
        ItemTittle tittle6 = new ItemTittle("我是第六个Item", today, "http://c.hiphotos.baidu.com/image/pic/item/f3d3572c11dfa9ec78e256df60d0f703908fc12e.jpg");
        ItemTittle tittle7 = new ItemTittle("我是第七个Item", today, "http://a.hiphotos.baidu.com/image/pic/item/4b90f603738da977e9bd86e3b251f8198718e36c.jpg");
        ItemTittle tittle8 = new ItemTittle("我是第八个Item", today, "http://a.hiphotos.baidu.com/image/w%3D230/sign=39e24064544e9258a63481edac83d1d1/fd039245d688d43fb75539f27f1ed21b0ef43b35.jpg");
        ItemTittle tittle9 = new ItemTittle("我是第九个Item", today, "http://a.hiphotos.baidu.com/image/w%3D230/sign=c203f5c508d162d985ee651f21dfa950/500fd9f9d72a6059e5265a1b2a34349b033bbaf9.jpg");
        ItemTittle tittle10 = new ItemTittle("我是第十个Item", today, "http://e.hiphotos.baidu.com/image/pic/item/1b4c510fd9f9d72aac01a41cd62a2834349bbb4e.jpg");
        listItem.add(tittle1);
        listItem.add(tittle2);
        listItem.add(tittle3);
        listItem.add(tittle4);
        listItem.add(tittle5);
        listItem.add(tittle6);
        listItem.add(tittle7);
        listItem.add(tittle8);
        listItem.add(tittle9);
        listItem.add(tittle10);
        Gson gson =new Gson();
        return gson.toJson(listItem);
    }
}
