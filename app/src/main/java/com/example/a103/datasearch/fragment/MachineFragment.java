package com.example.a103.datasearch.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.a103.datasearch.MachineAddActivity;
import com.example.a103.datasearch.MachineDetailActivity;
import com.example.a103.datasearch.MachineRecyclerViewAdapter;
import com.example.a103.datasearch.dao.DaoSession;
import com.example.a103.datasearch.data.Machine;
import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.R;
import com.example.a103.datasearch.utils.DatabaseApplication;

import java.util.List;

/**
 * Created by A103 on 2017/2/10.
 * 机床页面的fragment
 */

public class MachineFragment extends Fragment {

    private static final String TAG = "MachineFragment";
    Toolbar mToolbar;
    RecyclerView mRecyclerView;
    private DaoSession mDaoSession;
    private List<Machine> mMachineList;
    private MachineRecyclerViewAdapter mAdapter;
    private BroadcastReceiver mRefreshBroadcastReceiver;

    //factory method to get a instance
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
        initMachineList();

        //set layout for RecyclerView
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        //create adapter and set adapter for mRecyclerView
        mAdapter=new MachineRecyclerViewAdapter(mMachineList);
        mRecyclerView.setAdapter(mAdapter);

        //set the click event of machine list item
        mAdapter.setOnItemClickListener(new MachineRecyclerViewAdapter.OnMachineRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Long machineId=mMachineList.get(position).getId();
                MachineDetailActivity.actionStart(getContext(),machineId);
            }
        });
        //set the click event of toolbar
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.add_machine:
                        //start MachineAddActivity
                        MachineAddActivity.actionStart(getContext());
                }
                return MachineFragment.super.onOptionsItemSelected(item);
            }
        });

        registerRefreshMachineListBroadcastReceiver();
        return view;
    }

    /**
     * update machine list of recyclerView
     */
    private void updateMachines(){
        mMachineList=mDaoSession.getMachineDao().loadAll();
        mAdapter.updateMachineList(mMachineList);
        Log.d(TAG, "updateMachines: update machine list");
    }

    /**
     * register BroadcastReceiver to refresh machine list
     */
    private void registerRefreshMachineListBroadcastReceiver(){
        //create intentFilter
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(Constant.ACTION_REFRESH_MACHINE);
        //create broadcastReceiver
        if (mRefreshBroadcastReceiver==null){
            mRefreshBroadcastReceiver=new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                    //if action is ACTION_REFRESH_TOOL,refresh machine list
                    String action=intent.getAction();
                    if (action.equals(Constant.ACTION_REFRESH_MACHINE)){
                        updateMachines();
                    }
                }
            };
        }
        getActivity().registerReceiver(mRefreshBroadcastReceiver,intentFilter);
        Log.d(TAG, "registerRefreshMachineListBroadcastReceiver: register broadcast receiver of refresh machine list");
    }

    /**
     * initial the machine list from SQLite database
     */
    private void initMachineList() {
        mDaoSession= DatabaseApplication.getDaoSession();
        mMachineList=mDaoSession.getMachineDao().loadAll();
    }

    /**
     * 初始化机床信息控件
     * @param view 参数view
     */
    private void initView(View view) {
        //初始化toolbar
        mToolbar= (Toolbar) view.findViewById(R.id.toolbar_fragment_machine);
        mToolbar.inflateMenu(R.menu.menu_fragment_machine);
        mRecyclerView= (RecyclerView) view.findViewById(R.id.rv_machine);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
    }

}
