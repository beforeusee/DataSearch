package com.example.a103.datasearch;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhengxiaohu on 2017/3/9.
 */

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    //成员变量声明
    private Context context;
    private Map<String,List<String>> data;
    private List<String> groupList;
    private static final String TAG = "ExpandableListViewAdapter";

 /*   public List<String> getGroupList() {
        for (String key:data.keySet()){
            groupList.add(key);
        }
        return groupList;
    }*/

    //构造函数
    public ExpandableListViewAdapter(){
    }

    //传入数据适配参数的构造函数
    public ExpandableListViewAdapter(Context context,Map<String,List<String>> data,List<String> groupList){
        this.context=context;
        this.data=data;
        this.groupList=groupList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        context = context;
    }

    public Map<String, List<String>> getData() {
        return data;
    }

    public void setData(Map<String, List<String>> data) {
        this.data = data;
    }

/*    public ExpandableListViewAdapter(Context context, Map<String,List<String>> data){
        this.mContext=context;
        this.data=data;
    }*/

    public void updateExpandableListViewData(Map<String,List<String>> data,List<String> groupList){
        this.data=data;
        this.groupList=groupList;
        notifyDataSetChanged();
    }
    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return data.get(groupList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return data.get(groupList.get(groupPosition));
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(groupList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inflater=LayoutInflater.from (context);
            convertView=inflater.inflate(R.layout.material_expandablelistview_group_item,null);
        }
        convertView.setTag(R.layout.material_expandablelistview_group_item,groupPosition);
        convertView.setTag(R.layout.material_expandablelistview_child_item,-1);
        TextView tv= (TextView) convertView.findViewById(R.id.tv_material_parent_title);
        tv.setText(String.valueOf(groupList.get(groupPosition)));
        Log.d(TAG, "getGroupView: "+"groupPosition: "+groupPosition+
                " groupName: "+ groupList.get(groupPosition)+
                " childList: "+data.get(groupList.get(groupPosition)));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.material_expandablelistview_child_item,null);
        }

        convertView.setTag(R.layout.material_expandablelistview_group_item,groupPosition);
        convertView.setTag(R.layout.material_expandablelistview_child_item,childPosition);
        TextView tv= (TextView) convertView.findViewById(R.id.tv_material_child_title);
        tv.setText(data.get(groupList.get(groupPosition)).get(childPosition));
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击了内置的textView",Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
