package com.example.a103.datasearch.fragment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
    private DaoSession daoSession;

    EditText et_material_properties_categories;                          //种类

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_material_categories,container,false);

        initialMaterialCategoriesView(view);

        btn_material_categories_management.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialCategoriesManagementActivity.actionStart(getContext());
            }
        });
        return view;
    }

    private void initialMaterialCategoriesView(View view) {
        sp_material_categories_standard= (Spinner) view.findViewById(R.id.sp_material_categories_standard);
        sp_material_categories_unit= (Spinner) view.findViewById(R.id.sp_material_categories_unit);
        mExpandableListView= (ExpandableListView) view.findViewById(R.id.elv_material_categories);
        btn_material_categories_management= (Button) view.findViewById(R.id.btn_material_categories_management);
        btn_material_categories_addMaterial= (Button) view.findViewById(R.id.btn_material_categories_addMaterial);
    }
}
