package com.example.a103.datasearch.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a103.datasearch.MachineAddActivity;
import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.R;

/**
 * Created by A103 on 2017/2/10.
 * 机床页面的fragment
 */

public class MachineFragment extends Fragment {

    private static final String TAG = "MachineFragment";
    MachineDetailFragment mMachineDetailFragment;
    Toolbar mToolbar;

    //实例函数
    public static MachineFragment newInstance(String s){
        MachineFragment machineFragment=new MachineFragment();
        Bundle bundle=new Bundle();
        bundle.putString(Constant.ARGS,s);
        machineFragment.setArguments(bundle);
        return machineFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
//        Bundle bundle=getArguments();
//        String s=bundle.getString(Constant.ARGS);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_machine,container,false);
        initView(view);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.add_machine:
                        //start MachineAddActivity
                        MachineAddActivity.actionStart(getContext());
                    case R.id.edit_machine:
                        Toast.makeText(getContext(),"clicked edit machine",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.delete_machine:
                        //delete current machine

                }
                return MachineFragment.super.onOptionsItemSelected(item);
            }
        });
        addChildFragment();
        return view;
    }

    /**
     * add child fragment to the MachineFragment
     */
    private void addChildFragment() {
        mMachineDetailFragment= (MachineDetailFragment) getChildFragmentManager().
                findFragmentById(R.id.fl_machine_detail_fragment_container);
        if (mMachineDetailFragment==null){
            mMachineDetailFragment=new MachineDetailFragment();
            FragmentTransaction transaction=getChildFragmentManager().beginTransaction();
            transaction.add(R.id.fl_machine_detail_fragment_container,mMachineDetailFragment);
            transaction.commit();
        }
        Log.d(TAG, "addChildFragment: load MachineDetailFragment to MachineFragment.");
    }

    /**
     * 初始化机床信息控件
     * @param view 参数view
     */
    private void initView(View view) {
        //初始化toolbar
        mToolbar= (Toolbar) view.findViewById(R.id.toolbar_fragment_machine);
        mToolbar.inflateMenu(R.menu.menu_fragment_machine);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
    }

}
