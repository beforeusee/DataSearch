package com.example.a103.datasearch;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.a103.datasearch.fragment.MaterialDetailFragment;

import java.util.List;

/**
 * Created by zhengxiaohu on 2017/4/7.
 */

public class MaterialDetailCategoriesSpinnerAdapter extends BaseAdapter {


    private Context mContext;
    private List<String> mList;
    private static final String TAG = "MaterialDetailCategoriesSpinnerAdapter";

    public MaterialDetailCategoriesSpinnerAdapter(Context context,List<String> list){
        this.mContext=context;
        this.mList=list;
    }

    public void updateMaterialCategoriesNameList(List<String> list)
    {
        this.mList=list;
        notifyDataSetChanged();
        Log.d(TAG, "updateMaterialCategoriesNameList: 更新材料分类列表");
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(mContext).
                    inflate(R.layout.material_detail_categories_spinner_getdropdownview,null);
        }
        TextView tv_material_detail_sp_getDropDownView_categoriesName= (TextView) convertView.
                findViewById(R.id.tv_material_detail_sp_getDropDownView_categoriesName);
        tv_material_detail_sp_getDropDownView_categoriesName.setText(getItem(position));
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
            convertView= LayoutInflater.from(mContext).
                    inflate(R.layout.material_detail_categories_spinner_getview,null);
        }
        TextView tv_material_detail_sp_getView_categoriesName= (TextView) convertView.
                findViewById(R.id.tv_material_detail_sp_getView_categoriesName);
        tv_material_detail_sp_getView_categoriesName.setText(getItem(position));
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
