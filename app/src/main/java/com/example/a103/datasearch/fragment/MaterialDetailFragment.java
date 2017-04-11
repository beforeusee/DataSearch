package com.example.a103.datasearch.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.a103.datasearch.MaterialDetailCategoriesSpinnerAdapter;
import com.example.a103.datasearch.MaterialDetailForceModelSpannerAdapter;
import com.example.a103.datasearch.R;
import com.example.a103.datasearch.dao.DaoSession;
import com.example.a103.datasearch.data.MaterialCategories;
import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.utils.DatabaseApplication;

import java.util.ArrayList;
import java.util.List;

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
//    Button btn_material_delete_material;   //删除材料按钮
//    Button btn_material_cancel_material;   //取消按钮
//    Button btn_material_commit_material;   //完成按钮

    private static final String TAG = "MaterialDetailFragment";
    private MaterialDetailCategoriesSpinnerAdapter mCategoriesSpinnerAdapter;
    private MaterialDetailForceModelSpannerAdapter mForceModelSpannerAdapter;
    private List<String> materialCategoriesNameList=new ArrayList<>();
    private List<String> forceModelList=new ArrayList<>();
    private List<MaterialCategories> materialCategoriesList;
    private DaoSession daoSession= DatabaseApplication.getDaoSession();
    private BroadcastReceiver mRefreshMaterialCategoriesSpinnerBroadcastReceiver;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_material_detail,container,false);

        initialMaterialCategoriesSpinnerData();
        initialMaterialForceModelSpinnerData();
        initialMaterialDetailView(view);

        mCategoriesSpinnerAdapter=new MaterialDetailCategoriesSpinnerAdapter(getContext(),materialCategoriesNameList);
        sp_material_properties_categories.setAdapter(mCategoriesSpinnerAdapter);
        mForceModelSpannerAdapter=new MaterialDetailForceModelSpannerAdapter(getContext(),forceModelList);
        sp_material_coefficientParameters_forceModel.setAdapter(mForceModelSpannerAdapter);

        refreshMaterialCategoriesSpinnerBroadcastReceiver();
        return view;
    }
    
    // 2017/4/7 注册一个广播接收器
    private void refreshMaterialCategoriesSpinnerBroadcastReceiver() {
        //创建过滤器
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(Constant.ACTION_REFRESH_MATERIAL_CATEGORIES);
        //创建广播接收器
        if (mRefreshMaterialCategoriesSpinnerBroadcastReceiver ==null){
            mRefreshMaterialCategoriesSpinnerBroadcastReceiver =new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    //如果接收到对应的广播消息，更新材料分类列表
                    String action=intent.getAction();
                    if (action.equals(Constant.ACTION_REFRESH_MATERIAL_CATEGORIES)){
                        Log.d(TAG, "onReceive: 接收到刷新材料分类列表Spinner的广播");
                        updateMaterialCategoriesSpinnerData();
                    }
                }
            };
        }
        //当前活动注册该广播
        getActivity().registerReceiver(mRefreshMaterialCategoriesSpinnerBroadcastReceiver,intentFilter);
        Log.d(TAG, "registerRefreshMaterialCategoriesSpinnerBroadcastReceiver: 注册刷新材料分类Spinner列表的广播");
    }

    private void initialMaterialForceModelSpinnerData(){
        forceModelList.add(0,"Average Cutting Coefficient");
        forceModelList.add(1,"Variable Cut Coefficient");
        forceModelList.add(2,"High-Order Mech");
    }
    private void initialMaterialCategoriesSpinnerData() {
        materialCategoriesNameList=getMaterialCategoriesNameList();
    }

    private void updateMaterialCategoriesSpinnerData(){
        materialCategoriesNameList=getMaterialCategoriesNameList();
        //更新的代码
        mCategoriesSpinnerAdapter.updateMaterialCategoriesNameList(materialCategoriesNameList);
    }

    //封装获取materialCategoriesNameList的方法
    private List<String> getMaterialCategoriesNameList() {
        materialCategoriesList = daoSession.getMaterialCategoriesDao().loadAll();
        List<String> categoriesNameList=new ArrayList<>();
        for (int i = 0; i< materialCategoriesList.size(); i++){
            categoriesNameList.add(i, materialCategoriesList.get(i).getName());
        }
        return categoriesNameList;
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

//        btn_material_delete_material= (Button) view.findViewById(R.id.btn_material_delete_material);
//        btn_material_cancel_material= (Button) view.findViewById(R.id.btn_material_cancel_material);
//        btn_material_commit_material= (Button) view.findViewById(R.id.btn_material_commit_material);
    }

    /**
     * 在onDestroyView()中注销广播mRefreshMaterialCategoriesSpinnerBroadcastReceiver
     */
    @Override
    public void onDestroyView() {
        if (mRefreshMaterialCategoriesSpinnerBroadcastReceiver!=null){
            getActivity().unregisterReceiver(mRefreshMaterialCategoriesSpinnerBroadcastReceiver);
            Log.d(TAG, "onDestroyView: "+"注销刷新材料种类广播"+"mRefreshMaterialCategoriesSpinnerBroadcastReceiver");
        }
        super.onDestroyView();
    }

    /**
     * 获取材料分类MaterialCategories的实例列表
     * @return
     */
    public List<MaterialCategories> getMaterialCategoriesList() {
        return materialCategoriesList;
    }

    /**
     * 对外提供获取SpinnerAdapter的方法
     * @return
     */
//    public MaterialDetailCategoriesSpinnerAdapter getSpinnerAdapter() {
//        return mSpinnerAdapter;
//    }

    /**
     * 由于{@link MaterialDetailFragment}要被复用，因此对外提供访问和设置成员变量的方法
     *
     */


    public EditText getEt_material_properties_name() {
        return et_material_properties_name;
    }

    public void setEt_material_properties_name(EditText et_material_properties_name) {
        this.et_material_properties_name = et_material_properties_name;
    }

    public Spinner getSp_material_properties_categories() {
        return sp_material_properties_categories;
    }

    public void setSp_material_properties_categories(Spinner sp_material_properties_categories) {
        this.sp_material_properties_categories = sp_material_properties_categories;
    }

    public EditText getEt_material_properties_ingredient() {
        return et_material_properties_ingredient;
    }

    public void setEt_material_properties_ingredient(EditText et_material_properties_ingredient) {
        this.et_material_properties_ingredient = et_material_properties_ingredient;
    }

    public EditText getEt_material_properties_hardness() {
        return et_material_properties_hardness;
    }

    public void setEt_material_properties_hardness(EditText et_material_properties_hardness) {
        this.et_material_properties_hardness = et_material_properties_hardness;
    }

    public EditText getEt_material_properties_density() {
        return et_material_properties_density;
    }

    public void setEt_material_properties_density(EditText et_material_properties_density) {
        this.et_material_properties_density = et_material_properties_density;
    }

    public EditText getEt_material_properties_thermalConductivity() {
        return et_material_properties_thermalConductivity;
    }

    public void setEt_material_properties_thermalConductivity(EditText et_material_properties_thermalConductivity) {
        this.et_material_properties_thermalConductivity = et_material_properties_thermalConductivity;
    }

    public EditText getEt_material_properties_specificHeatCapacity() {
        return et_material_properties_specificHeatCapacity;
    }

    public void setEt_material_properties_specificHeatCapacity(EditText et_material_properties_specificHeatCapacity) {
        this.et_material_properties_specificHeatCapacity = et_material_properties_specificHeatCapacity;
    }

    public EditText getEt_material_properties_youngsModulus() {
        return et_material_properties_youngsModulus;
    }

    public void setEt_material_properties_youngsModulus(EditText et_material_properties_youngsModulus) {
        this.et_material_properties_youngsModulus = et_material_properties_youngsModulus;
    }

    public EditText getEt_material_properties_impactStrength() {
        return et_material_properties_impactStrength;
    }

    public void setEt_material_properties_impactStrength(EditText et_material_properties_impactStrength) {
        this.et_material_properties_impactStrength = et_material_properties_impactStrength;
    }

    public EditText getEt_material_properties_extension() {
        return et_material_properties_extension;
    }

    public void setEt_material_properties_extension(EditText et_material_properties_extension) {
        this.et_material_properties_extension = et_material_properties_extension;
    }

    public EditText getEt_material_properties_areaReduction() {
        return et_material_properties_areaReduction;
    }

    public void setEt_material_properties_areaReduction(EditText et_material_properties_areaReduction) {
        this.et_material_properties_areaReduction = et_material_properties_areaReduction;
    }

    public EditText getEt_material_properties_conductiveCoefficient() {
        return et_material_properties_conductiveCoefficient;
    }

    public void setEt_material_properties_conductiveCoefficient(EditText et_material_properties_conductiveCoefficient) {
        this.et_material_properties_conductiveCoefficient = et_material_properties_conductiveCoefficient;
    }

    public EditText getEt_material_properties_condition() {
        return et_material_properties_condition;
    }

    public void setEt_material_properties_condition(EditText et_material_properties_condition) {
        this.et_material_properties_condition = et_material_properties_condition;
    }

    public EditText getEt_material_properties_tensileStrength() {
        return et_material_properties_tensileStrength;
    }

    public void setEt_material_properties_tensileStrength(EditText et_material_properties_tensileStrength) {
        this.et_material_properties_tensileStrength = et_material_properties_tensileStrength;
    }

    public EditText getEt_material_properties_yieldStrength() {
        return et_material_properties_yieldStrength;
    }

    public void setEt_material_properties_yieldStrength(EditText et_material_properties_yieldStrength) {
        this.et_material_properties_yieldStrength = et_material_properties_yieldStrength;
    }

    public EditText getEt_material_properties_shearStrength() {
        return et_material_properties_shearStrength;
    }

    public void setEt_material_properties_shearStrength(EditText et_material_properties_shearStrength) {
        this.et_material_properties_shearStrength = et_material_properties_shearStrength;
    }

    public EditText getEt_material_properties_heatTreatment() {
        return et_material_properties_heatTreatment;
    }

    public void setEt_material_properties_heatTreatment(EditText et_material_properties_heatTreatment) {
        this.et_material_properties_heatTreatment = et_material_properties_heatTreatment;
    }

    public EditText getEt_material_properties_lowMeltingPoint() {
        return et_material_properties_lowMeltingPoint;
    }

    public void setEt_material_properties_lowMeltingPoint(EditText et_material_properties_lowMeltingPoint) {
        this.et_material_properties_lowMeltingPoint = et_material_properties_lowMeltingPoint;
    }

    public EditText getEt_material_properties_highMeltingPoint() {
        return et_material_properties_highMeltingPoint;
    }

    public void setEt_material_properties_highMeltingPoint(EditText et_material_properties_highMeltingPoint) {
        this.et_material_properties_highMeltingPoint = et_material_properties_highMeltingPoint;
    }

    public EditText getEt_material_properties_thermalExpansionCoefficient() {
        return et_material_properties_thermalExpansionCoefficient;
    }

    public void setEt_material_properties_thermalExpansionCoefficient(EditText et_material_properties_thermalExpansionCoefficient) {
        this.et_material_properties_thermalExpansionCoefficient = et_material_properties_thermalExpansionCoefficient;
    }

    public Spinner getSp_material_coefficientParameters_forceModel() {
        return sp_material_coefficientParameters_forceModel;
    }

    public void setSp_material_coefficientParameters_forceModel(Spinner sp_material_coefficientParameters_forceModel) {
        this.sp_material_coefficientParameters_forceModel = sp_material_coefficientParameters_forceModel;
    }

    public EditText getEt_material_coefficientParameters_Kte() {
        return et_material_coefficientParameters_Kte;
    }

    public void setEt_material_coefficientParameters_Kte(EditText et_material_coefficientParameters_Kte) {
        this.et_material_coefficientParameters_Kte = et_material_coefficientParameters_Kte;
    }

    public EditText getEt_material_coefficientParameters_Kre() {
        return et_material_coefficientParameters_Kre;
    }

    public void setEt_material_coefficientParameters_Kre(EditText et_material_coefficientParameters_Kre) {
        this.et_material_coefficientParameters_Kre = et_material_coefficientParameters_Kre;
    }

    public EditText getEt_material_coefficientParameters_Kae() {
        return et_material_coefficientParameters_Kae;
    }

    public void setEt_material_coefficientParameters_Kae(EditText et_material_coefficientParameters_Kae) {
        this.et_material_coefficientParameters_Kae = et_material_coefficientParameters_Kae;
    }

    public EditText getEt_material_coefficientParameters_Ktc() {
        return et_material_coefficientParameters_Ktc;
    }

    public void setEt_material_coefficientParameters_Ktc(EditText et_material_coefficientParameters_Ktc) {
        this.et_material_coefficientParameters_Ktc = et_material_coefficientParameters_Ktc;
    }

    public EditText getEt_material_coefficientParameters_Krc() {
        return et_material_coefficientParameters_Krc;
    }

    public void setEt_material_coefficientParameters_Krc(EditText et_material_coefficientParameters_Krc) {
        this.et_material_coefficientParameters_Krc = et_material_coefficientParameters_Krc;
    }

    public EditText getEt_material_coefficientParameters_Kac() {
        return et_material_coefficientParameters_Kac;
    }

    public void setEt_material_coefficientParameters_Kac(EditText et_material_coefficientParameters_Kac) {
        this.et_material_coefficientParameters_Kac = et_material_coefficientParameters_Kac;
    }

    public EditText getEt_material_limits_minChipThickness() {
        return et_material_limits_minChipThickness;
    }

    public void setEt_material_limits_minChipThickness(EditText et_material_limits_minChipThickness) {
        this.et_material_limits_minChipThickness = et_material_limits_minChipThickness;
    }

    public EditText getEt_material_limits_maxChipThickness() {
        return et_material_limits_maxChipThickness;
    }

    public void setEt_material_limits_maxChipThickness(EditText et_material_limits_maxChipThickness) {
        this.et_material_limits_maxChipThickness = et_material_limits_maxChipThickness;
    }

    public EditText getEt_material_limits_minCuttingSpeed() {
        return et_material_limits_minCuttingSpeed;
    }

    public void setEt_material_limits_minCuttingSpeed(EditText et_material_limits_minCuttingSpeed) {
        this.et_material_limits_minCuttingSpeed = et_material_limits_minCuttingSpeed;
    }

    public EditText getEt_material_limits_maxCuttingSpeed() {
        return et_material_limits_maxCuttingSpeed;
    }

    public void setEt_material_limits_maxCuttingSpeed(EditText et_material_limits_maxCuttingSpeed) {
        this.et_material_limits_maxCuttingSpeed = et_material_limits_maxCuttingSpeed;
    }

    public EditText getEt_material_limits_minRakeAngle() {
        return et_material_limits_minRakeAngle;
    }

    public void setEt_material_limits_minRakeAngle(EditText et_material_limits_minRakeAngle) {
        this.et_material_limits_minRakeAngle = et_material_limits_minRakeAngle;
    }

    public EditText getEt_material_limits_maxRakeAngle() {
        return et_material_limits_maxRakeAngle;
    }

    public void setEt_material_limits_maxRakeAngle(EditText et_material_limits_maxRakeAngle) {
        this.et_material_limits_maxRakeAngle = et_material_limits_maxRakeAngle;
    }

    public CheckBox getCb_material_standards_AFNOR() {
        return cb_material_standards_AFNOR;
    }

    public void setCb_material_standards_AFNOR(CheckBox cb_material_standards_AFNOR) {
        this.cb_material_standards_AFNOR = cb_material_standards_AFNOR;
    }

    public CheckBox getCb_material_standards_AISI() {
        return cb_material_standards_AISI;
    }

    public void setCb_material_standards_AISI(CheckBox cb_material_standards_AISI) {
        this.cb_material_standards_AISI = cb_material_standards_AISI;
    }

    public CheckBox getCb_material_standards_BS() {
        return cb_material_standards_BS;
    }

    public void setCb_material_standards_BS(CheckBox cb_material_standards_BS) {
        this.cb_material_standards_BS = cb_material_standards_BS;
    }

    public CheckBox getCb_material_standards_CMC() {
        return cb_material_standards_CMC;
    }

    public void setCb_material_standards_CMC(CheckBox cb_material_standards_CMC) {
        this.cb_material_standards_CMC = cb_material_standards_CMC;
    }

    public CheckBox getCb_material_standards_DIN_nr() {
        return cb_material_standards_DIN_nr;
    }

    public void setCb_material_standards_DIN_nr(CheckBox cb_material_standards_DIN_nr) {
        this.cb_material_standards_DIN_nr = cb_material_standards_DIN_nr;
    }

    public CheckBox getCb_material_standards_EN() {
        return cb_material_standards_EN;
    }

    public void setCb_material_standards_EN(CheckBox cb_material_standards_EN) {
        this.cb_material_standards_EN = cb_material_standards_EN;
    }

    public CheckBox getCb_material_standards_JIS() {
        return cb_material_standards_JIS;
    }

    public void setCb_material_standards_JIS(CheckBox cb_material_standards_JIS) {
        this.cb_material_standards_JIS = cb_material_standards_JIS;
    }

    public CheckBox getCb_material_standards_SAE() {
        return cb_material_standards_SAE;
    }

    public void setCb_material_standards_SAE(CheckBox cb_material_standards_SAE) {
        this.cb_material_standards_SAE = cb_material_standards_SAE;
    }

    public CheckBox getCb_material_standards_SS() {
        return cb_material_standards_SS;
    }

    public void setCb_material_standards_SS(CheckBox cb_material_standards_SS) {
        this.cb_material_standards_SS = cb_material_standards_SS;
    }

    public CheckBox getCb_material_standards_UNF() {
        return cb_material_standards_UNF;
    }

    public void setCb_material_standards_UNF(CheckBox cb_material_standards_UNF) {
        this.cb_material_standards_UNF = cb_material_standards_UNF;
    }

    public CheckBox getCb_material_standards_UNI() {
        return cb_material_standards_UNI;
    }

    public void setCb_material_standards_UNI(CheckBox cb_material_standards_UNI) {
        this.cb_material_standards_UNI = cb_material_standards_UNI;
    }

    public CheckBox getCb_material_standards_W_nr() {
        return cb_material_standards_W_nr;
    }

    public void setCb_material_standards_W_nr(CheckBox cb_material_standards_W_nr) {
        this.cb_material_standards_W_nr = cb_material_standards_W_nr;
    }
}
