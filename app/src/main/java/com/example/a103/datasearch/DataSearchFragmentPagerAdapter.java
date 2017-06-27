package com.example.a103.datasearch;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a103.datasearch.fragment.AdvancedFragment;
import com.example.a103.datasearch.fragment.MachineFragment;
import com.example.a103.datasearch.fragment.MaterialFragment;
import com.example.a103.datasearch.fragment.ParameterFragment;
import com.example.a103.datasearch.fragment.ToolFragment;

import java.util.List;

/**
 * Created by A103 on 2017/2/10.
 * 创建ViewPager适配器DataSearchFragmentPagerAdapter
 */

public class DataSearchFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<String> mTabList;
    private List<Fragment> mFragments;

    private ImageView mTabIcon;
    private TextView mTabText;

    public DataSearchFragmentPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    public DataSearchFragmentPagerAdapter(FragmentManager fragmentManager,List<String> tabList,List<Fragment> fragments){
        super(fragmentManager);
        mTabList=tabList;
        mFragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mTabList.size();
    }

    //用来获取tab的标题

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabList.get(position);
    }

    /**
     *  获取tab view
     *  @param position
     *  @return
     */
/*
    public View getTabView(int position){
        View view= LayoutInflater.from(mContext).inflate(R.layout.layout_tab_view,null);
        mTabText= (TextView) view.findViewById(R.id.tv_tab_text);
        mTabIcon= (ImageView) view.findViewById(R.id.iv_tab_icon);
        mTabText.setText(mTabList.get(position));
        mTabIcon.setImageResource(mTabImgs[position]);
        //默认机床选项是绿色的
        if (0==position){
            mTabText.setTextColor(ContextCompat.getColor(mContext,R.color.colorPrimary));
            mTabIcon.setImageResource(R.drawable.mac_fill);
        }
        return view;
    }*/
}
