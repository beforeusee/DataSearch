package com.example.a103.datasearch.fragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.a103.datasearch.ExpandableListViewAdapter;
import com.example.a103.datasearch.MainActivity;
import com.example.a103.datasearch.MaterialCategoriesManagementActivity;
import com.example.a103.datasearch.MaterialDetailActivity;
import com.example.a103.datasearch.MessageEvent;
import com.example.a103.datasearch.R;
import com.example.a103.datasearch.dao.DaoSession;
import com.example.a103.datasearch.data.Material;
import com.example.a103.datasearch.data.MaterialCategories;
import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.utils.DatabaseApplication;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhengxiaohu on 2017/3/26.
 */

public class MaterialCategoriesFragment extends Fragment {

    Spinner sp_material_categories_standard;           //材料标准spinner
    Spinner sp_material_categories_unit;                //材料单位(米制或英制)
    ExpandableListView mExpandableListView;              //材料种类分类二级目录
    private ExpandableListViewAdapter mAdapter;          //二级目录适配器
    private static final String TAG = "MaterialCategoriesFragment";
    private DaoSession daoSession= DatabaseApplication.getDaoSession();
    private List<MaterialCategories> materialCategoriesList=new ArrayList<>();
    private BroadcastReceiver mRefreshExpandableListViewBroadcastReceiver;
    private ExpandableListViewChildSelectListener mExpandableListViewChildSelectListener;

    private LocalBroadcastManager localBroadcastManager;
    private BroadcastReceiver mReceiver;

    /*
    //Test数据
    public static final String[] parentList=new String[]{"Aluminum","MAL Materials","Copper",
            "Copper[high-Alloy]", "Iron[Chilled Cast]","Steel[Casting]","Steel[High Alloy]",
            "Titanium", "Wood"};
    private List<String> childrenList=new ArrayList<>();
    */
//    List<String> groupList=new ArrayList<>();
//    List<List<String>> childList=new ArrayList<>();
//    List<String> child=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_material_categories,container,false);

        initialMaterialCategoriesView(view);

        //初始化数据groupList和childList
        materialCategoriesList=daoSession.getMaterialCategoriesDao().loadAll();
        List<String> groupList=getGroupList(materialCategoriesList);
        List<List<String>> childList=getChildList(materialCategoriesList);

        mAdapter=new ExpandableListViewAdapter(getContext(),groupList,childList);
        mExpandableListView.setAdapter(mAdapter);

        //mExpandableListView列表的监听函数
        setOnExpandableListViewClickListener(mExpandableListView);


        //注册刷新mExpandableListView列表的广播接收器
        registerRefreshExpandableListViewBroadcastReceiver();

        Log.d(TAG, "onCreateView: execution success.");
        return view;
    }

    /**
     * 点击onChildClick的回调接口
     */
    public interface ExpandableListViewChildSelectListener{
        void onExpandableListViewChildSelect(Long materialId);
    }
    /**
     * 传入mExpandableListView并为其设置group和child的点击监听函数
     * @param mExpandableListView
     */
    private void setOnExpandableListViewClickListener(ExpandableListView mExpandableListView) {
        if (mExpandableListView==null){
            throw new IllegalArgumentException("The expandableListView is null.");
        }

        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Toast.makeText(getContext(),"第"+groupPosition+"组被点击了",Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getContext(),"第"+groupPosition+"组展开",Toast.LENGTH_SHORT).show();
            }
        });

        mExpandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getContext(),"第"+groupPosition+"组合拢",Toast.LENGTH_SHORT).show();
            }
        });

        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getContext(), "第" + groupPosition + "组的第" + childPosition +
                        "被点击了.", Toast.LENGTH_SHORT).show();
                //点击后加载MaterialDetailFragment，并在其中显示详细的信息
                List<MaterialCategories> materialCategoriesList=daoSession.getMaterialCategoriesDao().loadAll();
                MaterialCategories materialCategories=materialCategoriesList.get(groupPosition);
                Material material=materialCategories.getMaterials().get(childPosition);
                Long materialId=material.getId();

                EventBus.getDefault().post(new MessageEvent(materialId));
                return true;
            }
        });

    }

    private void registerRefreshExpandableListViewBroadcastReceiver() {
        //创建过滤器
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(Constant.ACTION_REFRESH_MATERIAL_CATEGORIES);
        //创建并注册广播接收器mRefreshExpandableListViewBroadcastReceiver
        if (mRefreshExpandableListViewBroadcastReceiver ==null){
            mRefreshExpandableListViewBroadcastReceiver =new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    //如果接收到对应的广播消息，更新材料分类列表
                    String action=intent.getAction();
                    if (action.equals(Constant.ACTION_REFRESH_MATERIAL_CATEGORIES)){
                        Log.d(TAG, "onReceive: 接收到刷新材料分类列表的广播 mRefreshExpandableListViewBroadcastReceiver");
                        materialCategoriesList=daoSession.getMaterialCategoriesDao().loadAll();
                        List<String> groupList=getGroupList(materialCategoriesList);
                        List<List<String>> childList=getChildList(materialCategoriesList);
                        updateMaterialCategoriesExpandableListView(groupList,childList);
                    }
                }
            };
        }
        getActivity().registerReceiver(mRefreshExpandableListViewBroadcastReceiver,intentFilter);
        Log.d(TAG, "registerRefreshExpandableListViewBroadcastReceiver: 注册刷新材料分类ExpandableListView列表的广播");

        //创建并注册广播接收器mReceiver,专用于接收父Fragment即MaterialFragment中的广播
        if (mReceiver==null){
            mReceiver=new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String action=intent.getAction();
                    if (action.equals(Constant.ACTION_REFRESH_MATERIAL_CATEGORIES)){
                        Log.d(TAG, "onReceive: 接收到刷新材料分类列表的广播 mReceiver");
                        materialCategoriesList=daoSession.getMaterialCategoriesDao().loadAll();
                        List<String> groupList=getGroupList(materialCategoriesList);
                        List<List<String>> childList=getChildList(materialCategoriesList);
                        updateMaterialCategoriesExpandableListView(groupList,childList);
                    }
                }
            };
        }
        localBroadcastManager=LocalBroadcastManager.getInstance(getActivity());
        localBroadcastManager.registerReceiver(mReceiver,intentFilter);
    }

    private void updateMaterialCategoriesExpandableListView(List<String> groupList,List<List<String>> childList) {
        mAdapter.updateExpandableListViewData(groupList,childList);
    }

    private void initialMaterialCategoriesView(View view) {
        sp_material_categories_standard= (Spinner) view.findViewById(R.id.sp_material_categories_standard);
        sp_material_categories_unit= (Spinner) view.findViewById(R.id.sp_material_categories_unit);
        mExpandableListView= (ExpandableListView) view.findViewById(R.id.elv_material_categories);
    }

    @Override
    public void onDestroyView() {
        if (mRefreshExpandableListViewBroadcastReceiver !=null){
            getActivity().unregisterReceiver(mRefreshExpandableListViewBroadcastReceiver);
            Log.d(TAG, "onDestroyView: 注销刷新材料分类列表的广播");
        }

        if (mReceiver!=null){
            localBroadcastManager.unregisterReceiver(mReceiver);
            Log.d(TAG, "onDestroyView: 注销'删除'按钮的刷新材料分类列表的广播");
        }
        super.onDestroyView();
    }
    
    /*
    *//**
     * Test: 测试用数据初始化
     *//*
    private void initialSysData() {

        //Data for test，将数组数据转化成List集合
//        groupList=Arrays.asList(parentList);

        if (childrenList.size()==0)
        {
            childrenList.add("Aluminum 6061-T6 [95]");
            childrenList.add("Aluminum 6063");
        }
        if (data.size()==0){
            for (int i=0;i<parentList.length;i++){
                data.put(parentList[i],childrenList);
            }
        }
    }
*/

/*    private void initialData(){
        getData();
    }*/
/*    private void getData() {
        materialCategoriesList=daoSession.getMaterialCategoriesDao().loadAll();

    }*/

    private List<String> getGroupList(List<MaterialCategories> materialCategoriesList){
        List<String> groupList=new ArrayList<>();
        if (materialCategoriesList.size()>0){
            for (int i=0;i<materialCategoriesList.size();i++){
                groupList.add(i,materialCategoriesList.get(i).getName());
            }
        }else {
            Log.d(TAG, "getGroupList: "+"groupList has no element.");
        }
        return groupList;
    }
    private List<List<String>> getChildList(List<MaterialCategories> materialCategoriesList){
        List<List<String>> childList=new ArrayList<>();
        if (materialCategoriesList.size()>0){
            for (int i=0;i<materialCategoriesList.size();i++){
                if (materialCategoriesList.get(i).getMaterials().size()>0){
                    List<String> child=new ArrayList<>();
                    for (int j=0;j<materialCategoriesList.get(i).getMaterials().size();j++){
                        child.add(j,materialCategoriesList.get(i).getMaterials().get(j).getName());
                        Log.d(TAG, "getChildList: "+"add "+child.get(j)+" to "+
                                materialCategoriesList.get(i).getName());
                    }
                    childList.add(i,child);
                }else {
                    Log.d(TAG, "getChildList: "+materialCategoriesList.get(i).getName()+
                            " has no child element.");
                    List<String> child=new ArrayList<>();
                    childList.add(i,child);
                }
            }
        }else {
            Log.d(TAG, "getChildList: "+"materialCategoriesList has no element.");
        }

        return childList;
    }
}
