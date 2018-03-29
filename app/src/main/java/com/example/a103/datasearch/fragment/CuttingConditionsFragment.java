package com.example.a103.datasearch.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.a103.datasearch.CuttingParaShowDetailsActivity;
import com.example.a103.datasearch.ExpandableListViewAdapter;
import com.example.a103.datasearch.R;
import com.example.a103.datasearch.dao.DaoSession;
import com.example.a103.datasearch.data.CuttingConditions;
import com.example.a103.datasearch.data.CuttingParaDetails;
import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.utils.DatabaseApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiaoHu Zheng on 2018/3/13.
 * Email: 1050087728@qq.com
 */

public class CuttingConditionsFragment extends Fragment {

    //切削条件二级目录
    ExpandableListView mExpandableListView;

    // /二级目录适配器
    private ExpandableListViewAdapter mAdapter;

    private static final String TAG = "CuttingCondFragment";
    private DaoSession mDaoSession= DatabaseApplication.getDaoSession();

    private List<CuttingConditions> mCuttingConditionsList=new ArrayList<>();

    //更新广播
    private BroadcastReceiver mRefreshExpandableListViewBroadcastReceiver;

    private LocalBroadcastManager mLocalBroadcastManager;
    private  BroadcastReceiver mReceiver;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_cutting_conditions,container,false);

        initCuttingConditionsView(view);

        //初始化groupList和childList
        mCuttingConditionsList=mDaoSession.getCuttingConditionsDao().loadAll();
        List<String> groupList=getGroupList(mCuttingConditionsList);
        List<List<String>> childList=getChildList(mCuttingConditionsList);

        //设置二级目录的适配器
        mAdapter=new ExpandableListViewAdapter(getContext(),groupList,childList);
        mExpandableListView.setAdapter(mAdapter);

        //mExpandableListView列表的监听函数
        setOnExpandableListViewClickListener(mExpandableListView);


        //注册刷新mExpandableListView列表的广播接收器
        registerRefreshExpandableListViewBroadcastReceiver();

        return view;
    }

    /**
     * 传入mExpandableListView并为其设置group和child的点击监听函数
     * @param mExpandableListView 二级列表
     */
    private void setOnExpandableListViewClickListener(ExpandableListView mExpandableListView) {
        if (mExpandableListView==null){
            throw new IllegalArgumentException("The expandableListView is null.");
        }

        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//                Toast.makeText(getContext(),"第"+groupPosition+"组被点击了",Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getContext(),"第"+groupPosition+"组展开",Toast.LENGTH_SHORT).show();
            }
        });

        mExpandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getContext(),"第"+groupPosition+"组合拢",Toast.LENGTH_SHORT).show();
            }
        });

        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                Toast.makeText(getContext(), "第" + groupPosition + "组的第" + childPosition +
//                        "被点击了.", Toast.LENGTH_SHORT).show();
                //点击后加载CuttingParaShowDetailsFragment，并在其中显示详细的信息
                List<CuttingConditions> cuttingConditionsList=mDaoSession.getCuttingConditionsDao().loadAll();
                CuttingConditions cuttingConditions=cuttingConditionsList.get(groupPosition);
                CuttingParaDetails cuttingParaDetails=cuttingConditions.getMCuttingParaDetailsList().get(childPosition);
                Long cuttingParaDetailsId=cuttingParaDetails.getId();

                // 展示切削参数详情的Activity
                CuttingParaShowDetailsActivity.actionStart(getContext(),cuttingParaDetailsId);
                return true;
            }
        });

    }

    private void registerRefreshExpandableListViewBroadcastReceiver() {
        //创建过滤器
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(Constant.ACTION_REFRESH_CUTTING_CONDITIONS);
        //创建并注册广播接收器mRefreshExpandableListViewBroadcastReceiver
        if (mRefreshExpandableListViewBroadcastReceiver ==null){
            mRefreshExpandableListViewBroadcastReceiver =new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    //如果接收到对应的广播消息，更新切削参数列表
                    String action=intent.getAction();
                    if (action.equals(Constant.ACTION_REFRESH_CUTTING_CONDITIONS)){
                        Log.d(TAG, "onReceive: 接收到刷新切削参数列表的广播 mRefreshExpandableListViewBroadcastReceiver");
                        mCuttingConditionsList=mDaoSession.getCuttingConditionsDao().loadAll();
                        List<String> groupList=getGroupList(mCuttingConditionsList);
                        List<List<String>> childList=getChildList(mCuttingConditionsList);
                        updateMaterialCategoriesExpandableListView(groupList,childList);
                    }
                }
            };
        }
        //注册广播
        getActivity().registerReceiver(mRefreshExpandableListViewBroadcastReceiver,intentFilter);

        //创建并注册广播接收器mReceiver,专用于接收父Fragment即MaterialFragment中的广播
        if (mReceiver==null){
            mReceiver=new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String action=intent.getAction();
                    if (action.equals(Constant.ACTION_REFRESH_MATERIAL_CATEGORIES)){
                        Log.d(TAG, "onReceive: 接收到刷新切削参数列表的广播 mReceiver");
                        mCuttingConditionsList=mDaoSession.getCuttingConditionsDao().loadAll();
                        List<String> groupList=getGroupList(mCuttingConditionsList);
                        List<List<String>> childList=getChildList(mCuttingConditionsList);
                        updateMaterialCategoriesExpandableListView(groupList,childList);
                    }
                }
            };
        }
        mLocalBroadcastManager=LocalBroadcastManager.getInstance(getActivity());
        mLocalBroadcastManager.registerReceiver(mReceiver,intentFilter);
    }

    private void updateMaterialCategoriesExpandableListView(List<String> groupList, List<List<String>> childList) {

        mAdapter.updateExpandableListViewData(groupList,childList);
    }

    private List<List<String>> getChildList(List<CuttingConditions> cuttingConditionsList) {
        List<List<String>> childList=new ArrayList<>();
        if (cuttingConditionsList.size()>0){
            for (int i=0;i<cuttingConditionsList.size();i++){
                if (cuttingConditionsList.get(i).getMCuttingParaDetailsList().size()>0){
                    List<String> child=new ArrayList<>();
                    for (int j=0;j<cuttingConditionsList.get(i).getMCuttingParaDetailsList().size();j++){
                        child.add(j,"转速:"+ cuttingConditionsList.get(i).getMCuttingParaDetailsList().get(j).getSpindleSpeed()+
                                "-进给:"+ cuttingConditionsList.get(i).getMCuttingParaDetailsList().get(j).getFeedRate()+
                                "-切宽:"+ cuttingConditionsList.get(i).getMCuttingParaDetailsList().get(j).getCuttingWidth()+
                        "-切深:"+cuttingConditionsList.get(i).getMCuttingParaDetailsList().get(j).getCuttingDepth());
                    }
                    childList.add(i,child);
                }else {
                    Log.d(TAG, "getChildList: "+cuttingConditionsList.get(i).getToolInfo()+
                            " has no child element.");
                    List<String> child=new ArrayList<>();
                    childList.add(i,child);
                }
            }
        }else {
            Log.d(TAG, "getChildList: "+"CuttingParaDetailsList has no element.");
        }

        return childList;
    }

    private List<String> getGroupList(List<CuttingConditions> cuttingConditionsList) {

        List<String> groupList=new ArrayList<>();
        if (cuttingConditionsList.size()>0){
            for (int i=0;i<cuttingConditionsList.size();i++){
                groupList.add(i,cuttingConditionsList.get(i).getMachineInfo()+"|"+
                        cuttingConditionsList.get(i).getToolInfo()+"|"+
                cuttingConditionsList.get(i).getMaterialInfo());
            }
        }else {
            Log.d(TAG, "getGroupList: no element");
        }

        return groupList;
    }

    private void initCuttingConditionsView(View view) {

        mExpandableListView= (ExpandableListView) view.findViewById(R.id.elv_cutting_conditions);
    }

    /**
     * 注销广播
     */
    @Override
    public void onDestroyView() {

        if (mRefreshExpandableListViewBroadcastReceiver!=null){
            getActivity().unregisterReceiver(mRefreshExpandableListViewBroadcastReceiver);
        }

        if (mReceiver!=null){
            mLocalBroadcastManager.unregisterReceiver(mReceiver);
        }

        super.onDestroyView();
    }
}
