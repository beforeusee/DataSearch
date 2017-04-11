package com.example.a103.datasearch;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.a103.datasearch.dao.DaoSession;
import com.example.a103.datasearch.data.CoefficientParameters;
import com.example.a103.datasearch.data.Material;
import com.example.a103.datasearch.data.MaterialCategories;
import com.example.a103.datasearch.data.MaterialCuttingLimits;
import com.example.a103.datasearch.fragment.MaterialDetailFragment;
import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.utils.CustomTitleBar;
import com.example.a103.datasearch.utils.DatabaseApplication;

/**
 * 该Activity类用于添加具体的材料
 */
public class MaterialDetailActivity extends AppCompatActivity {

    CustomTitleBar material_detail_customTitleBar;       //标题栏
    LinearLayout ll_material_detail_fragment_container; //MaterialDetailFragment的容器
    private static final String TAG = "MaterialDetailActivity";
//    private DaoSession daoSession = DatabaseApplication.getDaoSession();
    MaterialDetailFragment materialDetailFragment=new MaterialDetailFragment();

    //创建Material,MaterialCuttingLimits,CoefficientParameters实例
    Material material = new Material();
    MaterialCuttingLimits mMaterialCuttingLimits=new MaterialCuttingLimits();
    CoefficientParameters mCoefficientParameters=new CoefficientParameters();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_detail);

        initialView();
        addFragmentToActivity();
        setCustomTitleBarClickListener();
        Log.d(TAG, "onCreate: "+"successfully execution.");
    }

    /**
     * 初始化界面
     */
    private void initialView() {
        material_detail_customTitleBar= (CustomTitleBar) findViewById(R.id.material_detail_customTitleBar);
        ll_material_detail_fragment_container= (LinearLayout) findViewById(R.id.ll_material_detail_fragment_container);
    }

    /**
     * 加载MaterialDetailFragment到MaterialDetailActivity.
     */
    private void addFragmentToActivity() {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();

        transaction.add(R.id.ll_material_detail_fragment_container,materialDetailFragment);
        transaction.commit();
        Log.d(TAG, "addFragmentToActivity: "+"加载MaterialDetailFragment到MaterialDetailActivity.");
    }

    /**
     * 设置标题栏左边按钮和右边按钮的点击监听函数
     */
    private void setCustomTitleBarClickListener() {
        //设置左边按钮"取消"的监听函数
        material_detail_customTitleBar.setTitleBarLeftBtnClickListener(new View.OnClickListener() {
            //点击，结束当前活动
            @Override
            public void onClick(View v) {
                finish();
                Log.d(TAG, "setCustomTitleBarClickListener: "+"onClick: "+"finish MaterialDetailActivity.");
            }

        });

        //设置右边按钮"完成"的监听函数
        material_detail_customTitleBar.setTitleBarRightBtnClickListener(new View.OnClickListener() {
            //点击保存添加的材料，并结束当前活动
            @Override
            public void onClick(View v) {
                Toast.makeText(MaterialDetailActivity.this,"点击了'完成'按钮.",Toast.LENGTH_SHORT).show();
                // TODO: 2017/4/9 添加材料到数据的逻辑，并更新MaterialCategoriesFragment中ExpandableListView列表

                Log.d(TAG, "setCustomTitleBarClickListener: "+"onClick: "+"name:"+
                        materialDetailFragment.getEt_material_properties_name().getText().toString());
                createMaterialDetail();
                DaoSession daoSession=DatabaseApplication.getDaoSession();
                Log.d(TAG, "onClick: "+"material categories "+
                        daoSession.getMaterialCategoriesDao().load(material.getMaterialCategoriesId()).getName()+
                        " related material list size: "+
                        daoSession.getMaterialCategoriesDao().load(material.getMaterialCategoriesId()).getMaterials().size());

                //发送更新ExpandableListView的广播
                sendRefreshExpandableListViewBroadcast();

                finish();
            }

        });
    }

    /**
     * 发送更新ExpandableListView列表的广播
     */
    private void sendRefreshExpandableListViewBroadcast(){
        Intent intent=new Intent();
        intent.setAction(Constant.ACTION_REFRESH_MATERIAL_CATEGORIES);
        sendBroadcast(intent);
        Log.d(TAG, "sendRefreshExpandableListViewBroadcast: "+"发送更新expandableListView的广播");
    }
    /**
     * 创建材料细节的函数，函数内部调用了createMaterial(),createMaterialCuttingLimits(),createCoefficientParameters()
     */
    private void createMaterialDetail() {
        createMaterial();
        createMaterialCuttingLimits();
        createCoefficientParameters();
        DaoSession daoSession=DatabaseApplication.getDaoSession();
        MaterialCategories materialCategoriesList=daoSession.getMaterialCategoriesDao().load(material.getMaterialCategoriesId());
        Log.d(TAG, "createMaterialDetail: materialCategoriesList: "+materialCategoriesList.getMaterials());
    }

    /**
     * 创建coefficientParameter并向数据库中添加
     */
    private void createCoefficientParameters() {
        String forceModel=materialDetailFragment.getSp_material_coefficientParameters_forceModel().getSelectedItem().toString();
        String Kte=materialDetailFragment.getEt_material_coefficientParameters_Kte().getText().toString();
        String Kre=materialDetailFragment.getEt_material_coefficientParameters_Kre().getText().toString();
        String Kae=materialDetailFragment.getEt_material_coefficientParameters_Kae().getText().toString();
        String Ktc=materialDetailFragment.getEt_material_coefficientParameters_Ktc().getText().toString();
        String Krc=materialDetailFragment.getEt_material_coefficientParameters_Krc().getText().toString();
        String Kac=materialDetailFragment.getEt_material_coefficientParameters_Kac().getText().toString();
        Long materialId=material.getId();

        //设置CoefficientParameters的成员属性
        mCoefficientParameters.setMaterialId(materialId);
        mCoefficientParameters.setForceModel(forceModel);
        mCoefficientParameters.setKte(Kte);
        mCoefficientParameters.setKre(Kre);
        mCoefficientParameters.setKae(Kae);
        mCoefficientParameters.setKtc(Ktc);
        mCoefficientParameters.setKrc(Krc);
        mCoefficientParameters.setKac(Kac);

        DaoSession daoSession=DatabaseApplication.getDaoSession();
        daoSession.getCoefficientParametersDao().save(mCoefficientParameters);
        if (daoSession.getCoefficientParametersDao().hasKey(mCoefficientParameters)){
            Log.d(TAG, "createCoefficientParameters: "+
                    mCoefficientParameters.getForceModel()+" successful.");
        }
        Log.d(TAG, "createCoefficientParameters: related material: "+
        mCoefficientParameters.getMaterial().getName());
    }

    /**
     * 创建材料切削限制MaterialCuttingLimits，并存入数据库表MATERIAL_CUTTING_LIMITS中
     */
    private void createMaterialCuttingLimits() {
        String minChipThickness=materialDetailFragment.getEt_material_limits_minChipThickness().getText().toString();
        String maxChipThickness=materialDetailFragment.getEt_material_limits_maxChipThickness().getText().toString();
        String minCuttingSpeed=materialDetailFragment.getEt_material_limits_minCuttingSpeed().getText().toString();
        String maxCuttingSpeed=materialDetailFragment.getEt_material_limits_maxCuttingSpeed().getText().toString();
        String minRakeAngle=materialDetailFragment.getEt_material_limits_minRakeAngle().getText().toString();
        String maxRakeAngle=materialDetailFragment.getEt_material_limits_maxRakeAngle().getText().toString();
        Long materialId=material.getId();

        //设置MaterialCuttingLimits的属性
        mMaterialCuttingLimits.setMaterialId(materialId);
        mMaterialCuttingLimits.setMinChipThickness(minChipThickness);
        mMaterialCuttingLimits.setMaxChipThickness(maxChipThickness);
        mMaterialCuttingLimits.setMinCuttingSpeed(minCuttingSpeed);
        mMaterialCuttingLimits.setMaxCuttingSpeed(maxCuttingSpeed);
        mMaterialCuttingLimits.setMinRakeAngle(minRakeAngle);
        mMaterialCuttingLimits.setMaxRakeAngle(maxRakeAngle);

        DaoSession daoSession=DatabaseApplication.getDaoSession();
        daoSession.getMaterialCuttingLimitsDao().save(mMaterialCuttingLimits);
        Log.d(TAG, "createMaterialCuttingLimits: "+mMaterialCuttingLimits.getId().toString()+" successful.");
        Log.d(TAG, "createMaterialCuttingLimits: related material: "+mMaterialCuttingLimits.getMaterial().getName());
    }

    /**
     * 创建一个新材料，并存入数据库表MATERIAL中
     */
    private void createMaterial() {
        String name=materialDetailFragment.getEt_material_properties_name().getText().toString();
        //根据Spinner选择的材料分类，设置该材料的外键materialCategoriesId为分类的Id
        Long materialCategoriesListId=materialDetailFragment.getSp_material_properties_categories().getSelectedItemId();
        Long materialCategoriesId=materialDetailFragment.getMaterialCategoriesList().get(Integer.parseInt(materialCategoriesListId.toString())).getId();

        String ingredient=materialDetailFragment.getEt_material_properties_ingredient().getText().toString();
        String hardness=materialDetailFragment.getEt_material_properties_hardness().getText().toString();
        String density=materialDetailFragment.getEt_material_properties_density().getText().toString();
        String thermalConductivity=materialDetailFragment.getEt_material_properties_thermalConductivity().getText().toString();
        String specificHeatCapacity=materialDetailFragment.getEt_material_properties_specificHeatCapacity().getText().toString();
        String youngsModulus=materialDetailFragment.getEt_material_properties_youngsModulus().getText().toString();
        String impactStrength=materialDetailFragment.getEt_material_properties_impactStrength().getText().toString();
        String extension=materialDetailFragment.getEt_material_properties_extension().getText().toString();
        String areaReduction=materialDetailFragment.getEt_material_properties_areaReduction().getText().toString();
        String conductiveCoefficient=materialDetailFragment.getEt_material_properties_conductiveCoefficient().getText().toString();
        String condition=materialDetailFragment.getEt_material_properties_condition().getText().toString();
        String tensileStrength=materialDetailFragment.getEt_material_properties_tensileStrength().getText().toString();
        String yieldStrength=materialDetailFragment.getEt_material_properties_yieldStrength().getText().toString();
        String shearStrength=materialDetailFragment.getEt_material_properties_shearStrength().getText().toString();
        String heatTreatment=materialDetailFragment.getEt_material_properties_heatTreatment().getText().toString();
        String lowMeltingPoint=materialDetailFragment.getEt_material_properties_lowMeltingPoint().getText().toString();
        String highMeltingPoint=materialDetailFragment.getEt_material_properties_highMeltingPoint().getText().toString();
        String thermalExpansionCoefficient=materialDetailFragment.getEt_material_properties_thermalExpansionCoefficient().getText().toString();
        //默认设置材料标准为AISI,后期可设置为一系列的标准集合List<String>
        String standard=materialDetailFragment.getCb_material_standards_AISI().getText().toString();

        //创建一个新的材料

        material.setName(name); //设置名称
        material.setMaterialCategoriesId(materialCategoriesId);  //设置外键Id(MaterialCategoriesId)
        material.setIngredient(ingredient);
        material.setHardness(hardness);
        material.setDensity(density);
        material.setThermalConductivity(thermalConductivity);
        material.setSpecificHeatCapacity(specificHeatCapacity);
        material.setYoungsModulus(youngsModulus);
        material.setImpactStrength(impactStrength);
        material.setExtension(extension);
        material.setAreaReduction(areaReduction);
        material.setConductiveCoefficient(conductiveCoefficient);
        material.setCondition(condition);
        material.setTensileStrength(tensileStrength);
        material.setYieldStrength(yieldStrength);
        material.setShearStrength(shearStrength);
        material.setHeatTreatment(heatTreatment);
        material.setLowMeltingPoint(lowMeltingPoint);
        material.setHighMeltingPoint(highMeltingPoint);
        material.setThermalExpansionCoefficient(thermalExpansionCoefficient);
        material.setStandard(standard);

        DaoSession daoSession=DatabaseApplication.getDaoSession();
        //将新创建的材料存入材料属性表 MATERIAL中
        daoSession.getMaterialDao().save(material);
        Log.d(TAG, "createMaterial: "+material.getName()+" successful.");
        Log.d(TAG, "createMaterial: related material categories: "+
                daoSession.getMaterialCategoriesDao().load(material.getMaterialCategoriesId()).getName());
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: execution successful.");
        super.onDestroy();
    }

    /**
     * 对外提供启动本活动{@link MaterialDetailActivity}的静态方法
     * @param context
     */
    public static void actionStart(Context context){
        Intent intent=new Intent(context,MaterialDetailActivity.class);
        context.startActivity(intent);
        Log.d(TAG, "actionStart: "+"启动MaterialDetailActivity.");
    }

}
