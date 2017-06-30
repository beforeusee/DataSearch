package com.example.a103.datasearch;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
public class MaterialAddActivity extends AppCompatActivity {

    CustomTitleBar material_add_customTitleBar;       //标题栏
    LinearLayout ll_material_add_detail_fragment_container; //MaterialDetailFragment的容器
    private static final String TAG = "MaterialAddActivity";
    MaterialDetailFragment mMaterialDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_add);

        initialView();
        addFragmentToActivity();
        setOnCustomTitleBarClickListener();
        Log.d(TAG, "onCreate: "+"successfully execution.");
    }

    /**
     * 初始化界面
     */
    private void initialView() {
        material_add_customTitleBar = (CustomTitleBar) findViewById(R.id.material_add_customTitleBar);
        ll_material_add_detail_fragment_container= (LinearLayout) findViewById(R.id.ll_material_add_detail_fragment_container);
    }

    /**
     * 加载MaterialDetailFragment到MaterialDetailActivity.
     */
    private void addFragmentToActivity() {
        FragmentManager fragmentManager=getSupportFragmentManager();
        mMaterialDetailFragment= (MaterialDetailFragment) fragmentManager.
                findFragmentById(R.id.ll_material_add_detail_fragment_container);
        if (mMaterialDetailFragment==null){
            mMaterialDetailFragment=new MaterialDetailFragment();
            FragmentTransaction transaction=fragmentManager.beginTransaction();
            transaction.add(R.id.ll_material_add_detail_fragment_container,mMaterialDetailFragment);
            transaction.commit();
        }
        Log.d(TAG, "addFragmentToActivity: "+"load MaterialDetailFragment to MaterialDetailActivity.");
    }

    /**
     * 设置标题栏左边按钮和右边按钮的点击监听函数
     */
    private void setOnCustomTitleBarClickListener() {
        //设置左边按钮"取消"的监听函数
        material_add_customTitleBar.setTitleBarLeftBtnClickListener(new View.OnClickListener() {
            //点击，结束当前活动
            @Override
            public void onClick(View v) {
                finish();
                Log.d(TAG, "setCustomTitleBarClickListener: "+"onClick: "+"finish MaterialAddActivity.");
            }

        });

        //设置右边按钮"完成"的监听函数
        material_add_customTitleBar.setTitleBarRightBtnClickListener(new View.OnClickListener() {
            //点击保存添加的材料，并结束当前活动
            @Override
            public void onClick(View v) {
                Toast.makeText(MaterialAddActivity.this,"点击了'完成'按钮.",Toast.LENGTH_SHORT).show();
                //添加材料到数据的逻辑，并更新MaterialCategoriesFragment中ExpandableListView列表
                createMaterialDetail();
                Log.d(TAG, "onClick: mMaterialDetailFragment.getMaterialDetail()==null? "+(mMaterialDetailFragment.getMaterialDetail()==null));
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
        //创建一个新的材料对象,新的切削力系数对象，新的切削极限对象
        Material material = mMaterialDetailFragment.getMaterialDetail().getMaterial();
        CoefficientParameters coefficientParameters= mMaterialDetailFragment.getMaterialDetail().getCoefficientParameters();
        MaterialCuttingLimits materialCuttingLimits= mMaterialDetailFragment.getMaterialDetail().getMaterialCuttingLimits();

        Long materialId=createMaterial(material);
        Long materialCuttingLimitsId=createMaterialCuttingLimits(materialCuttingLimits,materialId);
        Long coefficientParametersId=createCoefficientParameters(coefficientParameters,materialId);

        //将material自动生成的默认外键改为如下对应的coefficientParametersId和materialCuttingLimitsId
        Log.d(TAG, "createMaterialDetail: coeffId="+material.getCoefficientParametersId());
        Log.d(TAG, "createMaterialDetail: cuttingId="+material.getMaterialCuttingLimitsId());
        //设置material外键
        material.setCoefficientParametersId(coefficientParametersId);
        Log.d(TAG, "createMaterialDetail: coefficientParametersId="+coefficientParametersId);
        material.setMaterialCuttingLimitsId(materialCuttingLimitsId);
        Log.d(TAG, "createMaterialDetail: materialCuttingLimitsId="+materialCuttingLimitsId);

        DaoSession daoSession=DatabaseApplication.getDaoSession();
        //重新设置material的外键id后，需要更新material，需要再次调用save(material)方法，(主键key已存在的情况)在save内部调用update()更新
        daoSession.getMaterialDao().save(material);

        Log.d(TAG, "createMaterialDetail: "+"create material: "+daoSession.getMaterialDao().load(materialId).getName());
        MaterialCategories materialCategories=daoSession.getMaterialCategoriesDao().load(
                daoSession.getMaterialDao().load(materialId).getMaterialCategoriesId());
        //注意，创建了新的material后，在调用materialCategories的getMaterials():List<MaterialCategories>之前一定要调用resetMaterials();
        //才能在下一次查询时得到更新的结果
        materialCategories.resetMaterials();

        //输出日志：与新建的材料相关联的materialCategory,coefficientParameters,materialCuttingLimits
        Log.d(TAG, "createMaterialDetail: "+"create material: "+material.getName()+
                ", related materialCategory: "+ materialCategories.getName()+
                ", related coefficientParameters: "+ material.getCoefficientParameters().getForceModel()+
                ", materialCuttingLimits: "+ "minChipThickness: "+material.getMaterialCuttingLimits().getMinChipThickness());
    }

    /**
     * 创建coefficientParameter并向数据库中添加
     */
    private Long createCoefficientParameters(CoefficientParameters mCoefficientParameters,Long materialId) {

        //设置CoefficientParameters的成员属性
        mCoefficientParameters.setMaterialId(materialId);

        DaoSession daoSession=DatabaseApplication.getDaoSession();
        daoSession.getCoefficientParametersDao().save(mCoefficientParameters);
        Long coefficientParametersId=mCoefficientParameters.getId();

        if (daoSession.getCoefficientParametersDao().hasKey(mCoefficientParameters)){
            Log.d(TAG, "createCoefficientParameters: forceModel: "+
                    mCoefficientParameters.getForceModel()+" successful.");

            Log.d(TAG, "createCoefficientParameters: related material: "+
                    mCoefficientParameters.getMaterial().getName());
        }else {
            Log.d(TAG, "createCoefficientParameters: failed to save"+"forceModel: "+
                    mCoefficientParameters.getForceModel());
        }
        return coefficientParametersId;
    }

    /**
     * 创建材料切削限制MaterialCuttingLimits，并存入数据库表MATERIAL_CUTTING_LIMITS中
     */
    private Long createMaterialCuttingLimits(MaterialCuttingLimits mMaterialCuttingLimits,Long materialId) {

        //设置MaterialCuttingLimits的属性
        mMaterialCuttingLimits.setMaterialId(materialId);

        DaoSession daoSession=DatabaseApplication.getDaoSession();
        daoSession.getMaterialCuttingLimitsDao().save(mMaterialCuttingLimits);
        Long materialCuttingLimitsId=mMaterialCuttingLimits.getId();

        if (daoSession.getMaterialCuttingLimitsDao().hasKey(mMaterialCuttingLimits)){
            Log.d(TAG, "createMaterialCuttingLimits: "+
                    "create MaterialCuttingLimits minChipThickness: "+
                    mMaterialCuttingLimits.getMinChipThickness()+" successful.");
            Log.d(TAG, "createMaterialCuttingLimits: related material: "+
                    mMaterialCuttingLimits.getMaterial().getName());
        }else {
            Log.d(TAG, "createMaterialCuttingLimits: "+
                    "failed to save MaterialCuttingLimits minChipThickness: "+
                    mMaterialCuttingLimits.getMinChipThickness());
        }
        return materialCuttingLimitsId;
    }

    /**
     * 创建一个新材料，并存入数据库表MATERIAL中
     */
    private Long createMaterial(Material material) {
        DaoSession daoSession=DatabaseApplication.getDaoSession();
        //将新创建的材料存入材料属性表 MATERIAL中
        daoSession.getMaterialDao().save(material);

        //只有在存入数据库之后，才会生成默认的id值，这时才能获取
        Long materialId=material.getId();

        if (daoSession.getMaterialDao().hasKey(material)){
            Log.d(TAG, "createMaterial: create material: "+material.getName()+" successful.");
            Log.d(TAG, "createMaterial: related materialCategory: "+
                    daoSession.getMaterialCategoriesDao().load(material.getMaterialCategoriesId()).getName());
        }else {
            Log.d(TAG, "createMaterial: "+"failed to save material: "+material.getName());
        }
        return materialId;
    }

    /**
     * 对外提供启动本活动{@link MaterialAddActivity}的静态方法
     * @param context context
     */
    public static void actionStart(Context context){
        Intent intent=new Intent(context,MaterialAddActivity.class);
        context.startActivity(intent);
        Log.d(TAG, "actionStart: "+"启动MaterialDetailActivity.");
    }

}
