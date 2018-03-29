package com.example.a103.datasearch;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengxiaohu on 2017/3/9.
 */

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    //成员变量声明
    private Context context;
    private List<String> groupList=new ArrayList<>();
    private List<List<String>> childList=new ArrayList<>();
    private static final String TAG = "ExpandListViewAdapter";

    /**
     * 构造函数，初始化参数context,groupList,childList
     * @param context
     * @param groupList
     * @param childList
     */
    public ExpandableListViewAdapter(Context context,List<String> groupList,List<List<String>> childList){
        this.context=context;
        this.groupList=groupList;
        this.childList=childList;
    }

    /**
     * 对外提供刷新ExpandableListView的方法
     * @param groupList
     * @param childList
     */
    public void updateExpandableListViewData(List<String> groupList,List<List<String>> childList){
        this.groupList=groupList;
        this.childList=childList;
        notifyDataSetChanged();
        Log.d(TAG, "updateExpandableListViewData: 更新适配器ExpandableListViewAdapter的数据");
    }
    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int count=0;
        if (childList.get(groupPosition)!=null){
            count=childList.get(groupPosition).size();
        }
        return count;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
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
        GroupHolder groupHolder;
        if (convertView==null){
            LayoutInflater inflater=LayoutInflater.from (context);
            convertView=inflater.inflate(R.layout.expandablelistview_group_item,null);

            groupHolder=new GroupHolder();
            groupHolder.groupName= (TextView) convertView.findViewById(R.id.tv_parent_title);
            convertView.setTag(groupHolder);
        } else{
            groupHolder= (GroupHolder) convertView.getTag();
        }
        groupHolder.groupName.setText(groupList.get(groupPosition));
        Log.d(TAG, "getGroupView: "+"groupPosition: "+groupPosition+
                " groupName: "+ groupList.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder;
        if (convertView==null){
            LayoutInflater inflater= LayoutInflater.from(context);
            convertView=inflater.inflate(R.layout.expandablelistview_child_item,null);

            childHolder=new ChildHolder();
            childHolder.childName= (TextView) convertView.findViewById(R.id.tv_child_title);
            convertView.setTag(childHolder);
        } else{
            childHolder= (ChildHolder) convertView.getTag();
        }
        childHolder.childName.setText(childList.get(groupPosition).get(childPosition));
        Log.d(TAG, "getChildView: "+childList.get(groupPosition).get(childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private static class GroupHolder{
        TextView groupName;
    }

    private static class ChildHolder{
        TextView childName;
    }
}
