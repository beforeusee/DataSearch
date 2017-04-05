package com.example.a103.datasearch.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.a103.datasearch.MaterialSimpleCursorTreeAdapter;
import com.example.a103.datasearch.dao.DaoSession;
import com.example.a103.datasearch.dao.MaterialCategoriesDao;
import com.example.a103.datasearch.data.Material;
import com.example.a103.datasearch.data.MaterialCategories;
import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.ExpandableListViewAdapter;
import com.example.a103.datasearch.R;
import com.example.a103.datasearch.utils.DatabaseApplication;

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
    ExpandableListView mExpandableListView;             //材料种类分类二级目录
    private ExpandableListViewAdapter mAdapter;                   //二级目录适配器
    private Context mContext;
    private static final String TAG = "MaterialFragment";
    private SQLiteDatabase db;
    private DaoSession daoSession;
    private View groupView;
    private static final String MATERIAL_CATEGORIES_FRAGMENT_TAG="materialCategoriesFragment";
    private static final String MATERIAL_DETAIL_FRAGMENT_TAG="materialDetailFragment";

    MaterialCategoriesFragment materialCategoriesFragment=new MaterialCategoriesFragment();
    MaterialDetailFragment materialDetailFragment=new MaterialDetailFragment();

    //材料属性控件声明
    EditText et_material_properties_name;                                 //材料名称
    EditText et_material_properties_categories;                          //材料类别
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

    //TODO 数据集，后期数据接入数据库的数据
    private Map<String,List<String>> data=new HashMap<>();
    List<String> groupList=new ArrayList<>();
    private List<String> childrenList1=new ArrayList<>();
    private List<String> childrenList2=new ArrayList<>();
    private List<String> childrenList3=new ArrayList<>();

    //Test数据
    public static final String[] parentList=new String[]{"Aluminum","MAL Materials","Copper",
            "Copper[high-Alloy]", "Iron[Chilled Cast]","Steel[Casting]","Steel[High Alloy]","Titanium",
            "Wood"};


    public static MaterialFragment newInstance(String s){
        MaterialFragment materialFragment=new MaterialFragment();
       /* Bundle bundle=new Bundle();
        bundle.putString(Constant.ARGS,s);
        materialFragment.setArguments(bundle);*/
        return materialFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        groupView = inflater.inflate(R.layout.fragment_material, container, false);
        initChildFragment(groupView);
        return groupView;
    }

    private void initChildFragment(View groupView) {

        //判断groupView中是否能找到子fragment的布局

        //如果在布局中找到material_categories_fragment_container,将materialCategoriesFragment动态添加到布局中
        if (groupView.findViewById(R.id.material_categories_fragment_container)!=null){
            Fragment fragment=getChildFragmentManager().findFragmentByTag(MATERIAL_CATEGORIES_FRAGMENT_TAG);
            if (fragment==null){
                Log.d(TAG, "onCreateView: add new fragment: "+MATERIAL_CATEGORIES_FRAGMENT_TAG);
                MaterialCategoriesFragment materialCategoriesFragment=new MaterialCategoriesFragment();
                FragmentTransaction fragmentTransaction=getChildFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.material_categories_fragment_container, materialCategoriesFragment,MATERIAL_CATEGORIES_FRAGMENT_TAG);
                fragmentTransaction.commit();
            }else {
                Log.d(TAG, "onCreateView: fragment: "+MATERIAL_CATEGORIES_FRAGMENT_TAG+" already existed,no need to add it again.");
            }
        }
        //布局中含material_detail_fragment_container，将materialDetailFragment动态添加到布局中，加载的是(layout-sw600dp)fragment_material
        if (groupView.findViewById(R.id.material_detail_fragment_container)!=null){
            Fragment fragment=getChildFragmentManager().findFragmentByTag(MATERIAL_DETAIL_FRAGMENT_TAG);
            if (fragment==null){
                Log.d(TAG, "onCreateView: add new fragment: "+MATERIAL_DETAIL_FRAGMENT_TAG);
                MaterialDetailFragment materialDetailFragment=new MaterialDetailFragment();
                FragmentTransaction fragmentTransaction=getChildFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.material_detail_fragment_container,materialDetailFragment,MATERIAL_DETAIL_FRAGMENT_TAG);
                fragmentTransaction.commit();
            }else {
                Log.d(TAG, "onCreateView: fragment: "+MATERIAL_DETAIL_FRAGMENT_TAG+" already existed ,no need to add it again.");
            }
        }
    }


    private Material initialMaterialFromDetail(Material material){

        //material.setMaterialCategoriesId(et_material_properties_categories.getText().toString());
        material.setName(et_material_properties_name.getText().toString());
        material.setIngredient(et_material_properties_ingredient.getText().toString());
        material.setHardness(et_material_properties_hardness.getText().toString());
        material.setDensity(et_material_properties_density.getText().toString());
        material.setThermalConductivity(et_material_properties_thermalConductivity.getText().toString());
        material.setSpecificHeatCapacity(et_material_properties_specificHeatCapacity.getText().toString());
        material.setYoungsModulus(et_material_properties_youngsModulus.getText().toString());
        material.setImpactStrength(et_material_properties_impactStrength.getText().toString());
        material.setExtension(et_material_properties_extension.getText().toString());
        material.setAreaReduction(et_material_properties_areaReduction.getText().toString());
        material.setConductiveCoefficient(et_material_properties_conductiveCoefficient.getText().toString());
        material.setCondition(et_material_properties_condition.getText().toString());
        material.setTensileStrength(et_material_properties_tensileStrength.getText().toString());
        material.setYieldStrength(et_material_properties_yieldStrength.getText().toString());
        material.setShearStrength(et_material_properties_shearStrength.getText().toString());
        material.setHeatTreatment(et_material_properties_heatTreatment.getText().toString());
        material.setLowMeltingPoint(et_material_properties_lowMeltingPoint.getText().toString());
        material.setHighMeltingPoint(et_material_properties_highMeltingPoint.getText().toString());
        material.setThermalExpansionCoefficient(et_material_properties_thermalExpansionCoefficient.getText().toString());
        material.setStandard(cb_material_standards_AISI.getText().toString());
        return material;
    }
    private void initialMaterialDetailView(View view) {

        mExpandableListView= (ExpandableListView)view.findViewById(R.id.elv_material_categories);
        et_material_properties_name= (EditText) view.findViewById(R.id.et_material_detail_properties_name);
//        et_material_properties_categories= (EditText) view.findViewById(R.id.et_material_detail_properties_categories);
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

//        btn_material_add_userCategories= (Button) view.findViewById(R.id.btn_material_add_categories);
//        btn_material_delete_userCategories= (Button) view.findViewById(R.id.btn_material_delete_categories);
//        btn_material_add_userMaterial= (Button) view.findViewById(R.id.btn_material_add_userMaterial);
//        btn_material_delete_userMaterial= (Button) view.findViewById(R.id.btn_material_delete_userMaterial);
//        btn_material_commit_userMaterial= (Button) view.findViewById(R.id.btn_material_commit_userMaterial);
//        btn_material_cancel_userMaterial= (Button) view.findViewById(R.id.btn_material_cancel_userMaterial);
    }

    private void initialSysData() {

        if (childrenList1.size()==0)
        {
            childrenList1.add("Aluminum 6061-T6 [95]");
            childrenList1.add("Aluminum 6063");
        }

        if (childrenList2.size()==0){
            childrenList2.add("Aluminum 7050-T7451 [140]");

        }

        if (childrenList3.size()==0){
            childrenList3.add("Copper 3021");

        }

        if (data.size()==0){
            data.put(parentList[0],childrenList1);
            data.put(parentList[1],childrenList2);
            data.put(parentList[2],childrenList3);

        }

        for (int i=0;i<parentList.length;i++){
            groupList.add(parentList[i]);
        }
    }
}
