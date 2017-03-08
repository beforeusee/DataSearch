package com.example.a103.datasearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

/**
 * Created by zhengxiaohu on 2017/3/9.
 */

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    //成员变量声明
    private Context mContext;
    private Map<String,List<String>> data;

    //构造函数
    public ExpandableListViewAdapter(){
    }

    //传入数据适配参数的构造函数

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public Map<String, List<String>> getData() {
        return data;
    }

    public void setData(Map<String, List<String>> data) {
        this.data = data;
    }

    public ExpandableListViewAdapter(Context context, Map<String,List<String>> data){
        this.mContext=context;
        this.data=data;
    }


    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return data.get(String.valueOf(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return data.get(String.valueOf(groupPosition));
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(String.valueOf(groupPosition)).get(childPosition);
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
            LayoutInflater inflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.material_expandablelistview_group_item,null);
        }
        convertView.setTag(R.layout.material_expandablelistview_group_item,groupPosition);
        convertView.setTag(R.layout.material_expandablelistview_child_item,-1);
        TextView tv= (TextView) convertView.findViewById(R.id.tv_material_parent_title);
        tv.setText(String.valueOf(groupPosition));
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
        tv.setText(data.get(String.valueOf(groupPosition)).get(childPosition));
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击了内置的textview",Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
