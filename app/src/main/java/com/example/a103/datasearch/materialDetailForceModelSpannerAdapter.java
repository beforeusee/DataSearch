package com.example.a103.datasearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zhengxiaohu on 2017/4/11.
 */

public class MaterialDetailForceModelSpannerAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList;

    public MaterialDetailForceModelSpannerAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(
                    R.layout.material_detail_force_model_spinner_item,null);
        }
        TextView tv_material_detail_sp_forceModel= (TextView) convertView.findViewById(
                R.id.tv_material_detail_sp_forceModel);
        tv_material_detail_sp_forceModel.setText(getItem(position).toString());
        return convertView;
    }
}
