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

    private String[] mTitles=new String[]{"机床","刀具","材料","参数","高级"};
    private List<String> mTabList;
    private Context mContext;
    private List<Fragment> mFragments;
    private ImageView mTabIcon;
    private TextView mTabText;
    private int[] mTabImgs;
    private LinearLayout mTabView;

    public DataSearchFragmentPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    public DataSearchFragmentPagerAdapter(FragmentManager fragmentManager,List<String> tabList,Context context,List<Fragment> fragments,int[] TabImgs){
        super(fragmentManager);
        mTabList=tabList;
        mContext=context;
        mFragments=fragments;
        mTabImgs=TabImgs;
    }

    @Override
    public Fragment getItem(int position) {
//        switch (position){
//            case 0:
//                return MachineFragment.newInstance("机床");
//            case 1:
//                return ToolFragment.newInstance("刀具");
//            case 2:
//                return MaterialFragment.newInstance("材料");
//            case 3:
//                return ParameterFragment.newInstance("参数");
//            case 4:
//                return AdvancedFragment.newInstance("高级");
//            default:
//                return MachineFragment.newInstance("机床");
//        }
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mTabList.size();
    }

    //用来设置tab的标题

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabList.get(position);
    }

    /**
     *  设置tab view
     *  @param position
     *  @return
     */

    public View getTabView(int position){
        View view= LayoutInflater.from(mContext).inflate(R.layout.layout_tab_view,null);
        mTabView= (LinearLayout) view.findViewById(R.id.ll_tab_view);
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
    }
}
