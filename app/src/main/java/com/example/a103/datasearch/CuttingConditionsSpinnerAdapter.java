package com.example.a103.datasearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by XiaoHu Zheng on 2018/3/18.
 * Email: 1050087728@qq.com
 */

public class CuttingConditionsSpinnerAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mList;
    private static final String TAG = "CuttingConditionsSpinnerAdapter";

    public CuttingConditionsSpinnerAdapter(Context context, List<String> list){

        this.mContext=context;
        this.mList=list;
    }

    /**
     * 更新材料详情切削条件列表
     * @param list 切削条件列表
     */
    public void updateCuttingConditionsList(List<String> list){

        this.mList=list;
        notifyDataSetChanged();
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        if (convertView==null){
            convertView= LayoutInflater.from(mContext).
                    inflate(R.layout.cutting_conditions_spinner_getdropdownview,null);
        }

        TextView tv_cutting_para_details_sp_getDropDownView= (TextView) convertView.
                findViewById(R.id.tv_cutting_para_details_sp_getDropDownView);

        tv_cutting_para_details_sp_getDropDownView.setText(getItem(position));

        return convertView;
    }

    @Override
    public int getCount() {

        return mList.size();
    }

    @Override
    public String getItem(int position) {

        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView==null){
            convertView=LayoutInflater.from(mContext).
                    inflate(R.layout.cutting_conditions_spinner_getview,null);
        }

        TextView tv_cutting_para_details_sp_getView= (TextView) convertView.
                findViewById(R.id.tv_cutting_para_details_sp_getView);

        tv_cutting_para_details_sp_getView.setText(getItem(position));
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return (getCount()==0);
    }
}
