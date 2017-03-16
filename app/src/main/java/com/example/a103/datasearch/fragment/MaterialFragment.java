package com.example.a103.datasearch.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import com.example.a103.datasearch.utils.Constant;
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
    //声明与二级目录了相关的成员变量
    private ExpandableListView mExpandableListView;             //材料种类分类二级目录
    private ExpandableListViewAdapter mAdapter;                   //二级目录适配器
    private Context mContext;

    //compile 'org.litepal.android:core:1.5.0'

    //材料属性控件声明
    EditText et_material_properties_name;                                 //材料名称
    EditText et_material_properties_categories;                          //种类
    EditText et_material_properties_ingredient;                          //成分
    EditText et_material_properties_hardness;                            //硬度
    EditText et_material_properties_density;                             //密度
    EditText et_material_properties_thermalConductivity;               //导热系数
    EditText et_material_properties_specificHeatCapacity;             //比热容
    EditText et_material_properties_youngsModulus;                     //杨氏模量
    EditText et_material_properties_impactStrength;                    //冲击强度
    EditText et_material_properties_extension;                         //延伸量
    EditText et_material_properties_areaReduction;                    //面积减少量
    EditText et_material_properties_conductiveCoefficient;           //导电系数
    EditText et_material_properties_condition;                        //条件
    EditText et_material_properties_tensileStrength;                 //拉伸强度
    EditText et_material_properties_yieldStrength;                   //屈服强度
    EditText et_material_properties_shearStrength;                   //剪切强度
    EditText et_material_properties_heatTreatment;                   //热处理
    EditText et_material_properties_lowMeltingPoint;                //熔点(低)
    EditText et_material_properties_highMeltingPoint;               //熔点(高)
    EditText et_material_properties_thermalExpansionCoefficient;  //热膨胀系数

    //材料切削力系数参数控件声明
    Spinner sp_material_coefficientParameters_forceModel;     //力模型
    EditText et_material_coefficientParameters_Kte;            //切向刃口力系数
    EditText et_material_coefficientParameters_Kre;            //径向刃口力系数
    EditText et_material_coefficientParameters_Kae;            //轴向刃口力系数
    EditText et_material_coefficientParameters_Ktc;            //切向铣削力系数
    EditText et_material_coefficientParameters_Krc;            //径向铣削力系数
    EditText et_material_coefficientParameters_Kac;            //轴向铣削力系数

    //材料限制条件控件声明
    EditText et_material_limits_minChipThickness;               //切屑厚度(最小)
    EditText et_material_limits_maxChipThickness;               //切屑厚度(最大)
    EditText et_material_limits_minCuttingSpeed;                //切削速度(最小)
    EditText et_material_limits_maxCuttingSpeed;                //切削速度(最大)
    EditText et_material_limits_minRakeAngle;                    //前角(最小)
    EditText et_material_limits_maxRakeAngle;                    //前角(最大)

    //材料标准控件声明
    CheckBox cb_material_standards_AFNOR;
    CheckBox cb_material_standards_AISI;                          //材料标准-美国钢铁学会标准
    CheckBox cb_material_standards_BS;
    CheckBox cb_material_standards_CMC;
    CheckBox cb_material_standards_DIN_nr;
    CheckBox cb_material_standards_EN;
    CheckBox cb_material_standards_JIS;
    CheckBox cb_material_standards_SAE;
    CheckBox cb_material_standards_SS;
    CheckBox cb_material_standards_UNF;
    CheckBox cb_material_standards_UNI;
    CheckBox cb_material_standards_W_nr;

    //新建类，删除类，新建，删除，应用，取消等按钮控件声明
    Button btn_material_add_userCategories;     //新建用户材料分类
    Button btn_material_delete_userCategories;  //删除用户材料分类
    Button btn_material_add_userMaterial;       //新建用户材料
    Button btn_material_delete_userMaterial;    //删除用户材料
    Button btn_material_commit_userMaterial;    //提交用户材料
    Button btn_material_cancel_userMaterial;    //取消用户材料


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
        mContext=getContext();
        initialMaterialDetailView(view);
        initialData();
        mAdapter=new ExpandableListViewAdapter(mContext,data);
        mExpandableListView.setAdapter(mAdapter);
        return view;
    }

    private void initialMaterialDetailView(View view) {

        mExpandableListView= (ExpandableListView)view.findViewById(R.id.elv_material_categories);
        et_material_properties_name= (EditText) view.findViewById(R.id.et_material_detail_properties_name);
        et_material_properties_categories= (EditText) view.findViewById(R.id.et_material_detail_properties_categories);
        et_material_properties_ingredient= (EditText) view.findViewById(R.id.et_material_detail_properties_ingredient);
        et_material_properties_hardness= (EditText) view.findViewById(R.id.et_material_detail_properties_hardness);
        et_material_properties_density= (EditText) view.findViewById(R.id.et_material_detail_properties_density);
        et_material_properties_thermalConductivity= (EditText) view.findViewById(R.id.et_material_detail_properties_thermalConductivity);
        et_material_properties_specificHeatCapacity= (EditText) view.findViewById(R.id.et_material_detail_properties_specificHeatCapacity);
        et_material_properties_youngsModulus= (EditText) view.findViewById(R.id.et_material_detail_properties_youngsModulus);
        et_material_properties_impactStrength= (EditText) view.findViewById(R.id.et_material_detail_properties_impactStrength);
        et_material_properties_extension= (EditText) view.findViewById(R.id.et_material_detail_properties_extension);
        et_material_properties_areaReduction= (EditText) view.findViewById(R.id.et_material_detail_properties_areaReduction);
        et_material_properties_conductiveCoefficient= (EditText) view.findViewById(R.id.et_material_detail_properties_conductiveCoefficient);
        et_material_properties_condition= (EditText) view.findViewById(R.id.et_material_detail_properties_condition);
        et_material_properties_tensileStrength= (EditText) view.findViewById(R.id.et_material_detail_properties_tensileStrength);
        et_material_properties_yieldStrength= (EditText) view.findViewById(R.id.et_material_detail_properties_yieldStrength);
        et_material_properties_shearStrength= (EditText) view.findViewById(R.id.et_material_detail_properties_shearStrength);
        et_material_properties_heatTreatment= (EditText) view.findViewById(R.id.et_material_detail_properties_heatTreatment);
        et_material_properties_lowMeltingPoint= (EditText) view.findViewById(R.id.et_material_detail_properties_lowMeltingPoint);
        et_material_properties_highMeltingPoint= (EditText) view.findViewById(R.id.et_material_detail_properties_highMeltingPoint);
        et_material_properties_thermalExpansionCoefficient= (EditText) view.findViewById(R.id.et_material_detail_properties_thermalExpansionCoefficient);

        sp_material_coefficientParameters_forceModel= (Spinner) view.findViewById(R.id.sp_material_detail_coefficientParameters_forceModel);
        et_material_coefficientParameters_Kte= (EditText) view.findViewById(R.id.et_material_detail_coefficientParameters_Kte);
        et_material_coefficientParameters_Kre= (EditText) view.findViewById(R.id.et_material_detail_coefficientParameters_Kre);
        et_material_coefficientParameters_Kae= (EditText) view.findViewById(R.id.et_material_detail_coefficientParameters_Kae);
        et_material_coefficientParameters_Ktc= (EditText) view.findViewById(R.id.et_material_detail_coefficientParameters_Ktc);
        et_material_coefficientParameters_Krc= (EditText) view.findViewById(R.id.et_material_detail_coefficientParameters_Krc);
        et_material_coefficientParameters_Kac= (EditText) view.findViewById(R.id.et_material_detail_coefficientParameters_Kac);

        et_material_limits_minChipThickness= (EditText) view.findViewById(R.id.et_material_detail_limits_minChipThickness);
        et_material_limits_maxChipThickness= (EditText) view.findViewById(R.id.et_material_detail_limits_maxChipThickness);
        et_material_limits_minCuttingSpeed= (EditText) view.findViewById(R.id.et_material_detail_limits_minCuttingSpeed);
        et_material_limits_maxCuttingSpeed= (EditText) view.findViewById(R.id.et_material_detail_limits_maxCuttingSpeed);
        et_material_limits_minRakeAngle= (EditText) view.findViewById(R.id.et_material_detail_limits_minRakeAngle);
        et_material_limits_maxRakeAngle= (EditText) view.findViewById(R.id.et_material_detail_limits_maxRakeAngle);

        cb_material_standards_AFNOR= (CheckBox) view.findViewById(R.id.cb_material_standards_AFNOR);
        cb_material_standards_AISI= (CheckBox) view.findViewById(R.id.cb_material_standards_AISI);
        cb_material_standards_BS= (CheckBox) view.findViewById(R.id.cb_material_standards_BS);
        cb_material_standards_CMC= (CheckBox) view.findViewById(R.id.cb_material_standards_CMC);
        cb_material_standards_DIN_nr= (CheckBox) view.findViewById(R.id.cb_material_standards_DIN_nr);
        cb_material_standards_EN= (CheckBox) view.findViewById(R.id.cb_material_standards_EN);
        cb_material_standards_JIS= (CheckBox) view.findViewById(R.id.cb_material_standards_JIS);
        cb_material_standards_SAE= (CheckBox) view.findViewById(R.id.cb_material_standards_SAE);
        cb_material_standards_SS= (CheckBox) view.findViewById(R.id.cb_material_standards_SS);
        cb_material_standards_UNF= (CheckBox) view.findViewById(R.id.cb_material_standards_UNF);
        cb_material_standards_UNI= (CheckBox) view.findViewById(R.id.cb_material_standards_UNI);
        cb_material_standards_W_nr= (CheckBox) view.findViewById(R.id.cb_material_standards_W_nr);

        btn_material_add_userCategories= (Button) view.findViewById(R.id.btn_material_add_userCategories);
        btn_material_delete_userCategories= (Button) view.findViewById(R.id.btn_material_delete_userCategories);
        btn_material_add_userMaterial= (Button) view.findViewById(R.id.btn_material_add_userMaterial);
        btn_material_delete_userMaterial= (Button) view.findViewById(R.id.btn_material_delete_userMaterial);
        btn_material_commit_userMaterial= (Button) view.findViewById(R.id.btn_material_commit_userMaterial);
        btn_material_cancel_userMaterial= (Button) view.findViewById(R.id.btn_material_cancel_userMaterial);
    }

    private void initialData() {

        if (childrenList1.size()==0)
        {
            childrenList1.add("Aluminum 6061-T6 [95]");
            childrenList1.add("Aluminum 6063");
            childrenList1.add("Aluminum 7075-T6 [150]");
            childrenList1.add("Aluminum 6061-T6 [95]");
            childrenList1.add("Aluminum 6063");
            childrenList1.add("Aluminum 7075-T6 [150]");
        }

        if (childrenList2.size()==0){
            childrenList2.add("Aluminum 7050-T7451 [140]");
            childrenList2.add("Titanium Alloy Ti6Al4V[340]");
            childrenList2.add("Aluminum 7050-T7451 [140]");
            childrenList2.add("Titanium Alloy Ti6Al4V[340] 12334390t3043t3");
        }

        if (childrenList3.size()==0){
            childrenList3.add("Copper 3021");
            childrenList3.add("Copper 3022");
            childrenList3.add("Copper 3023");
        }

        if (data.size()==0){
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
}
