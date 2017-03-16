package com.example.a103.datasearch;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a103.datasearch.fragment.AdvancedFragment;
import com.example.a103.datasearch.fragment.MachineFragment;
import com.example.a103.datasearch.fragment.MaterialFragment;
import com.example.a103.datasearch.fragment.ParameterFragment;
import com.example.a103.datasearch.fragment.ToolFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<String> mTabList=new ArrayList<>();
    private DataSearchFragmentPagerAdapter mAdapter;
    private int[] mTabImgs=new int[]{R.drawable.mac,R.drawable.tool,
            R.drawable.mat,R.drawable.para,R.drawable.adv};
    private List<Fragment> mFragments=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout= (TabLayout) findViewById(R.id.tab_main);
        mViewPager= (ViewPager) findViewById(R.id.vp_main);
        initTabList();
        mAdapter=new DataSearchFragmentPagerAdapter(getSupportFragmentManager(),mTabList,MainActivity.this,mFragments,mTabImgs);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        for (int i=0;i<mTabLayout.getTabCount();i++){
            mTabLayout.getTabAt(i).setCustomView(mAdapter.getTabView(i));
        }
        mTabLayout.addOnTabSelectedListener(this);  //添加tab标签页选择侦听事件
    }

    /**
     *  init the tab list
     *  初始化tab列表
     */
    private void initTabList(){
        mTabList.clear();
        mTabList.add(getString(R.string.item_machine));
        mTabList.add(getString(R.string.item_tool));
        mTabList.add(getString(R.string.item_material));
        mTabList.add(getString(R.string.item_parameter));
        mTabList.add(getString(R.string.item_advanced));
    }

    @Override
    protected void onStart() {
        super.onStart();
        initFragmentList();   //初始化fragment列表
    }

    /*
    * add Fragment
    * 添加fragment
    */
    private void initFragmentList(){
        mFragments.clear();
        mFragments.add(MachineFragment.newInstance(getString(R.string.item_machine)));      //
        mFragments.add(ToolFragment.newInstance(getString(R.string.item_tool)));            //
        mFragments.add(MaterialFragment.newInstance(getString(R.string.item_material)));   //
        mFragments.add(ParameterFragment.newInstance(getString(R.string.item_parameter))); //
        mFragments.add(AdvancedFragment.newInstance(getString(R.string.item_advanced)));   //
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        setTabSelectedState(tab);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        setTabUnSelectedState(tab);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    /**
     * set the state of the tab that has been selected
     * 设置选中的标签页的状态，图标颜色的转换，用绿色的替代黑色的
     */
    private void setTabSelectedState(TabLayout.Tab tab){
        View customView=tab.getCustomView();
        TextView tabText= (TextView) customView.findViewById(R.id.tv_tab_text);
        ImageView tabIcon= (ImageView) customView.findViewById(R.id.iv_tab_icon);
        tabText.setTextColor(ContextCompat.getColor(this,R.color.colorPrimary));
        String s=tabText.getText().toString();
        if (getString(R.string.item_machine).equals(s)){
            tabIcon.setImageResource(R.drawable.mac_fill);
        }else if (getString(R.string.item_tool).equals(s)){
            tabIcon.setImageResource(R.drawable.tool_fill);
        }else if (getString(R.string.item_material).equals(s)){
            tabIcon.setImageResource(R.drawable.mat_fill);
        }else if (getString(R.string.item_advanced).equals(s)){
            tabIcon.setImageResource(R.drawable.adv_fill);
        }else if (getString(R.string.item_parameter).equals(s)){
            tabIcon.setImageResource(R.drawable.para_fill);
        }
    }

    /**
     * set the state of the tab that has not been selected
     * 设置未被选中的标签页的状态图片
     */
    private void setTabUnSelectedState(TabLayout.Tab tab){
        View customView=tab.getCustomView();
        TextView tabText= (TextView) customView.findViewById(R.id.tv_tab_text);
        ImageView tabIcon= (ImageView) customView.findViewById(R.id.iv_tab_icon);
        tabText.setTextColor(ContextCompat.getColor(this,R.color.black));
        String s=tabText.getText().toString();
        if (getString(R.string.item_machine).equals(s)){
            tabIcon.setImageResource(R.drawable.mac);
        }else if (getString(R.string.item_tool).equals(s)){
            tabIcon.setImageResource(R.drawable.tool);
        }else if (getString(R.string.item_material).equals(s)){
            tabIcon.setImageResource(R.drawable.mat);
        }else if (getString(R.string.item_advanced).equals(s)){
            tabIcon.setImageResource(R.drawable.adv);
        }else if (getString(R.string.item_parameter).equals(s)){
            tabIcon.setImageResource(R.drawable.para);
        }
    }
}
