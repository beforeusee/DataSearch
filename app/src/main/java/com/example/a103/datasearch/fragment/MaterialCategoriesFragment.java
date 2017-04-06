package com.example.a103.datasearch.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import com.example.a103.datasearch.ExpandableListViewAdapter;
import com.example.a103.datasearch.MaterialCategoriesManagementActivity;
import com.example.a103.datasearch.R;
import com.example.a103.datasearch.dao.DaoSession;
import com.example.a103.datasearch.data.MaterialCategories;
import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.utils.DatabaseApplication;

import java.util.ArrayList;
import java.util.Arrays;
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
    Button btn_material_categories_management;         //分类管理
    Button btn_material_categories_addMaterial;        //添加材料
    private ExpandableListViewAdapter mAdapter;          //二级目录适配器
    private Context mContext;
    private static final String TAG = "MaterialCategoriesFragment";
    private SQLiteDatabase db;
    private DaoSession daoSession= DatabaseApplication.getDaoSession();
    private List<MaterialCategories> materialCategoriesList=new ArrayList<>();
    private BroadcastReceiver mRefreshMaterialCategoriesBroadcastReceiver;

    //TODO 数据集，后期数据接入数据库的数据
    //Test数据
    public static final String[] parentList=new String[]{"Aluminum","MAL Materials","Copper",
            "Copper[high-Alloy]", "Iron[Chilled Cast]","Steel[Casting]","Steel[High Alloy]",
            "Titanium", "Wood"};
    private Map<String,List<String>> data=new HashMap<>();
    List<String> groupList=new ArrayList<>();
    List<String> childList=new ArrayList<>();
    private List<String> childrenList1=new ArrayList<>();
    private List<String> childrenList2=new ArrayList<>();
    private List<String> childrenList3=new ArrayList<>();



    EditText et_material_properties_categories;                          //种类

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_material_categories,container,false);

        initialMaterialCategoriesView(view);
//        initialSysData();
        initData();
        btn_material_categories_management.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialCategoriesManagementActivity.actionStart(getContext());
            }
        });

        mAdapter=new ExpandableListViewAdapter(getContext(),data,groupList);
        mExpandableListView.setAdapter(mAdapter);
        registerRefreshMaterialCategoriesBroadcastReceiver();
        return view;
    }

    private void registerRefreshMaterialCategoriesBroadcastReceiver() {
        //创建过滤器
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(Constant.ACTION_REFRESH_MATERIAL_CATEGORIES);
        //创建广播接收器
        if (mRefreshMaterialCategoriesBroadcastReceiver==null){
            mRefreshMaterialCategoriesBroadcastReceiver=new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    //如果接收到对应的广播消息，更新材料分类列表
                    String action=intent.getAction();
                    if (action.equals(Constant.ACTION_REFRESH_MATERIAL_CATEGORIES)){
                        Log.d(TAG, "onReceive: 接收到刷新材料分类列表的广播");
                        updateMaterialCategoriesExpandableListView();
                    }
                }
            };
        }
        getActivity().registerReceiver(mRefreshMaterialCategoriesBroadcastReceiver,intentFilter);
        Log.d(TAG, "registerRefreshMaterialCategoriesBroadcastReceiver: 注册刷新材料分类列表的广播");
    }

    private void updateMaterialCategoriesExpandableListView() {
        materialCategoriesList=daoSession.getMaterialCategoriesDao().loadAll();
        //如果groupList有数据，清空
        if (groupList.size()!=0){
            groupList.clear();
        }
        //如果键值对data有数据，清空
        if (data.size()!=0){
            data.clear();
        }

        for (int i=0;i<materialCategoriesList.size();i++){
            groupList.add(i,materialCategoriesList.get(i).getName());
            childList.clear();
            for (int j=0;j<materialCategoriesList.get(i).getMaterials().size();j++){
                childList.add(j,materialCategoriesList.get(i).getMaterials().get(j).getName());
            }
            data.put(groupList.get(i),childList);
        }
        mAdapter.updateExpandableListViewData(data,groupList);
    }

    private void initialMaterialCategoriesView(View view) {
        sp_material_categories_standard= (Spinner) view.findViewById(R.id.sp_material_categories_standard);
        sp_material_categories_unit= (Spinner) view.findViewById(R.id.sp_material_categories_unit);
        mExpandableListView= (ExpandableListView) view.findViewById(R.id.elv_material_categories);
        btn_material_categories_management= (Button) view.findViewById(R.id.btn_material_categories_management);
        btn_material_categories_addMaterial= (Button) view.findViewById(R.id.btn_material_categories_addMaterial);
    }

    @Override
    public void onDestroyView() {
        if (mRefreshMaterialCategoriesBroadcastReceiver!=null){
            getActivity().unregisterReceiver(mRefreshMaterialCategoriesBroadcastReceiver);
            Log.d(TAG, "onDestroyView: 注销刷新材料分类列表的广播");
        }
        super.onDestroyView();
    }

    private void initialSysData() {

        //Data for test，将数组数据转化成List集合
//        groupList=Arrays.asList(parentList);

        if (childrenList1.size()==0)
        {
            childrenList1.add("Aluminum 6061-T6 [95]");
            childrenList1.add("Aluminum 6063");
        }

        if (childrenList2.size()==0){
            childrenList2.add("Aluminum 7050-T7451 [140]");

        }

        if (childrenList3.size()==0){
            childrenList3.add("Copper 3021");

        }


        if (data.size()==0){
            // TODO: 2017/4/6 此处应该初始化数据库中的分类数据和每个类别下对应的数据
            for (int i=0;i<groupList.size();i++){
                data.put(groupList.get(i),groupList);
            }
        }
    }

    private void initData() {
        materialCategoriesList=daoSession.getMaterialCategoriesDao().loadAll();
        //如果groupList有数据，清空
        if (groupList.size()!=0){
            groupList.clear();
        }
        //如果键值对data有数据，清空
        if (data.size()!=0){
            data.clear();
        }

        for (int i=0;i<materialCategoriesList.size();i++){
            groupList.add(i,materialCategoriesList.get(i).getName());
            childList.clear();
            for (int j=0;j<materialCategoriesList.get(i).getMaterials().size();j++){
                childList.add(j,materialCategoriesList.get(i).getMaterials().get(j).getName());
            }
            data.put(groupList.get(i),childList);
        }
    }
}
