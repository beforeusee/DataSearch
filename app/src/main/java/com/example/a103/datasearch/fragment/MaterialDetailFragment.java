package com.example.a103.datasearch.fragment;

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

import com.example.a103.datasearch.R;

/**
 * Created by zhengxiaohu on 2017/3/26.
 */

public class MaterialDetailFragment extends Fragment {

    //材料属性控件声明
    EditText et_material_properties_name;                                 //材料名称
    Spinner sp_material_properties_categories;                           //材料分类
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

    //删除，取消，应用
    Button btn_material_delete_material;   //删除材料按钮
    Button btn_material_cancel_material;   //取消按钮
    Button btn_material_commit_material;   //完成按钮

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_material_detail,container,false);

        initialMaterialDetailView(view);

        return view;
    }

    private void initialMaterialDetailView(View view) {

        et_material_properties_name= (EditText) view.findViewById(R.id.et_material_detail_properties_name);
        sp_material_properties_categories= (Spinner) view.findViewById(R.id.sp_material_detail_properties_categories);
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

        btn_material_delete_material= (Button) view.findViewById(R.id.btn_material_delete_material);
        btn_material_cancel_material= (Button) view.findViewById(R.id.btn_material_cancel_material);
        btn_material_commit_material= (Button) view.findViewById(R.id.btn_material_commit_material);
    }
}
