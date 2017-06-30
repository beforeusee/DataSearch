package com.example.a103.datasearch;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
    private TabLayout.Tab machineTab;
    private TabLayout.Tab toolTab;
    private TabLayout.Tab materialTab;
    private TabLayout.Tab parameterTab;
    private TabLayout.Tab advancedTab;
    private ViewPager mViewPager;
    private List<String> mTabList=new ArrayList<>();
    private DataSearchFragmentPagerAdapter mAdapter;

    private int[] mTabIcons=new int[]{R.drawable.mac,R.drawable.tool,
            R.drawable.mat,R.drawable.para,R.drawable.adv};
    private List<Fragment> mFragments=new ArrayList<>();

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager= (ViewPager) findViewById(R.id.vp_main);
        mTabLayout= (TabLayout) findViewById(R.id.tab_main);

        initData();
        mAdapter=new DataSearchFragmentPagerAdapter(getSupportFragmentManager(),mTabList,mFragments);
        mViewPager.setAdapter(mAdapter);

        mTabLayout.setupWithViewPager(mViewPager);

        //initViews();

        //必须在mTabLayout与mViewPager绑定后，这样才知道mTabLayout中Tab的数量，mTabLayout相当于容器
        for (int i=0;i<mTabLayout.getTabCount();i++){
            mTabLayout.getTabAt(i).setCustomView(getTabView(i));
        }
        mViewPager.setCurrentItem(0);
        mTabLayout.addOnTabSelectedListener(this);  //添加tab标签页选择侦听事件

        Log.d(TAG, "onCreate: 执行mAdapter创建");
    }

    private void initViews() {
        //使用适配器mAdapter将ViewPager与Fragment绑定在一起
        mViewPager= (ViewPager) findViewById(R.id.vp_main);
        mAdapter=new DataSearchFragmentPagerAdapter(getSupportFragmentManager(),mTabList,mFragments);
        mViewPager.setAdapter(mAdapter);

        //将TabLayout与ViewPager绑定
        mTabLayout= (TabLayout) findViewById(R.id.tab_main);
        mTabLayout.setupWithViewPager(mViewPager);

        //指定Tab的位置
        machineTab=mTabLayout.getTabAt(0);
        toolTab=mTabLayout.getTabAt(1);
        materialTab=mTabLayout.getTabAt(2);
        parameterTab=mTabLayout.getTabAt(3);
        advancedTab=mTabLayout.getTabAt(4);


        //设置Tab的图标
        machineTab.setIcon(R.drawable.mac_fill);  //默认选中机床
        toolTab.setIcon(R.drawable.tool);
        materialTab.setIcon(R.drawable.mat);
        parameterTab.setIcon(R.drawable.para);
        advancedTab.setIcon(R.drawable.adv);

        mViewPager.setCurrentItem(0);
    }

    private void initData() {
        initTabList();
        initFragmentList();
    }

    /**
     *  init the tab list
     *  初始化tab列表
     */
    private void initTabList(){
        mTabList.add(getString(R.string.item_machine));
        mTabList.add(getString(R.string.item_tool));
        mTabList.add(getString(R.string.item_material));
        mTabList.add(getString(R.string.item_parameter));
        mTabList.add(getString(R.string.item_advanced));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: 执行");

    }

    /*
    * add Fragment
    * 添加fragment
    */
    private void initFragmentList(){
        mFragments.add(MachineFragment.newInstance(getString(R.string.item_machine)));      //
        mFragments.add(ToolFragment.newInstance(getString(R.string.item_tool)));            //
        mFragments.add(MaterialFragment.newInstance(getString(R.string.item_material)));   //
        mFragments.add(ParameterFragment.newInstance(getString(R.string.item_parameter))); //
        mFragments.add(AdvancedFragment.newInstance(getString(R.string.item_advanced)));   //
        Log.d(TAG, "initFragmentList: 初始化底部导航栏的五个fragmentList");
        Log.d(TAG, "initFragmentList: mFragments数量："+mFragments.size()+"个");
    }

    public View getTabView(int position){
        View view= LayoutInflater.from(this).inflate(R.layout.layout_tab_view,null);
        TextView tabTitle= (TextView) view.findViewById(R.id.tv_tab_text);
        ImageView tabIcon= (ImageView) view.findViewById(R.id.iv_tab_icon);
        tabTitle.setText(mTabList.get(position));
        tabIcon.setImageResource(mTabIcons[position]);
        if (0==position){
            tabTitle.setTextColor(this.getResources().getColor(R.color.colorPrimary));
            tabIcon.setImageResource(R.drawable.mac_fill);
        }
        return view;
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
//        if (0==tab.getPosition()){
//            tab.setIcon(R.drawable.mac_fill);
//        }else if (1==tab.getPosition()){
//            tab.setIcon(R.drawable.tool_fill);
//        }else if (2==tab.getPosition()){
//            tab.setIcon(R.drawable.mat_fill);
//        }else if (3==tab.getPosition()){
//            tab.setIcon(R.drawable.para_fill);
//        }else if (4==tab.getPosition()){
//            tab.setIcon(R.drawable.adv_fill);
//        }
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

//        if (0==tab.getPosition()){
//            tab.setIcon(R.drawable.mac);
//        }else if (1==tab.getPosition()){
//            tab.setIcon(R.drawable.tool);
//        }else if (2==tab.getPosition()){
//            tab.setIcon(R.drawable.mat);
//        }else if (3==tab.getPosition()){
//            tab.setIcon(R.drawable.para);
//        }else if (4==tab.getPosition()){
//            tab.setIcon(R.drawable.adv);
//        }
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
