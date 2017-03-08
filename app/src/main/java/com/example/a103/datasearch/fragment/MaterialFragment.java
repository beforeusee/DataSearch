package com.example.a103.datasearch.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;


import com.example.a103.datasearch.Constant;
import com.example.a103.datasearch.ExpandableListViewAdapter;
import com.example.a103.datasearch.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by A103 on 2017/2/10.
 * 材料页面的fragment
 */

public class MaterialFragment extends Fragment {
    //声明成员变量
    private ExpandableListView mExpandableListView;
    private ExpandableListViewAdapter mAdapter;
    private Context mContext;


    //TODO 数据集，后期数据来源接入数据库的数据
    private Map<String,List<String>> data=new HashMap<>();
    private List<String> childrenList1=new ArrayList<>();
    private List<String> childrenList2=new ArrayList<>();
    private List<String> childrenList3=new ArrayList<>();

    public static final String[] parentList=new String[]{"0","1","2","3",
            "4","5","6","7","8"};

    public static MaterialFragment newInstance(String s){
        MaterialFragment materialFragment=new MaterialFragment();
        Bundle bundle=new Bundle();
        bundle.putString(Constant.ARGS,s);
        materialFragment.setArguments(bundle);
        return materialFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_material,container,false);
/*        Bundle bundle = getArguments();
        String s = bundle.getString(Constant.ARGS);*/
        mExpandableListView= (ExpandableListView) view.findViewById(R.id.elv_material_categories);
        mContext=getContext();
        initData();
        mAdapter=new ExpandableListViewAdapter(mContext,data);
        mExpandableListView.setAdapter(mAdapter);
        return view;
    }

    private void initData() {
        childrenList1.add("Aluminum 6061-T6 [95]");
        childrenList1.add("Aluminum 6063");
        childrenList1.add("Aluminum 7075-T6 [150]");
        childrenList1.add("Aluminum 6061-T6 [95]");
        childrenList1.add("Aluminum 6063");
        childrenList1.add("Aluminum 7075-T6 [150]");

        childrenList2.add("Aluminum 7050-T7451 [140]");
        childrenList2.add("Titanium Alloy Ti6Al4V[340]");
        childrenList2.add("Aluminum 7050-T7451 [140]");
        childrenList2.add("Titanium Alloy Ti6Al4V[340] 12334390t3043t3");


        childrenList3.add("Copper 3021");
        childrenList3.add("Copper 3022");
        childrenList3.add("Copper 3023");
        childrenList2.add("Aluminum 7050-T7451 [140]");
        childrenList2.add("Titanium Alloy Ti6Al4V[340]");

        data.put(parentList[0],childrenList1);
        data.put(parentList[1],childrenList2);
        data.put(parentList[2],childrenList3);
        data.put(parentList[3],childrenList1);
        data.put(parentList[4],childrenList2);
        data.put(parentList[5],childrenList3);
        data.put(parentList[6],childrenList1);
        data.put(parentList[7],childrenList2);
    }

}
