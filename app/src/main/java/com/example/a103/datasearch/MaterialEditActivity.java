package com.example.a103.datasearch;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.example.a103.datasearch.dao.DaoSession;
import com.example.a103.datasearch.data.MaterialCategories;
import com.example.a103.datasearch.fragment.MaterialDetailFragment;
import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.utils.CustomTitleBar;
import com.example.a103.datasearch.utils.DatabaseApplication;

public class MaterialEditActivity extends AppCompatActivity {

    private static final String TAG = "MaterialEditActivity";
    public static final String MATERIAL_ID="mMaterialId";

    CustomTitleBar material_edit_customTitleBar;
    FrameLayout fl_material_edit_detail_fragment_container;
    MaterialDetailFragment mMaterialDetailFragment;

    Long mMaterialId;
    Long mMaterialCategoriesId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_edit);

        initView();
        mMaterialId =getIntent().getLongExtra(MATERIAL_ID,0);
        mMaterialCategoriesId=DatabaseApplication.getDaoSession().getMaterialDao().load(mMaterialId).getMaterialCategoriesId();

        addFragmentToActivity(mMaterialId);

        material_edit_customTitleBar.setTitleBarLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        material_edit_customTitleBar.setTitleBarRightBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 2017/7/14
                DaoSession daoSession=DatabaseApplication.getDaoSession();

                daoSession.getMaterialDao().save(mMaterialDetailFragment.getMaterialDetail().getMaterial());
                daoSession.getCoefficientParametersDao().save(mMaterialDetailFragment.getMaterialDetail().getCoefficientParameters());
                daoSession.getMaterialCuttingLimitsDao().save(mMaterialDetailFragment.getMaterialDetail().getMaterialCuttingLimits());

                MaterialCategories materialCategories=daoSession.getMaterialCategoriesDao().
                        load(mMaterialDetailFragment.getMaterialDetail().getMaterial().getMaterialCategoriesId());

                //判断该材料对应的MaterialCategories的id是否变化
                if (materialCategories.getId().equals(mMaterialCategoriesId)){

                    materialCategories.resetMaterials();
                }else {

                    materialCategories.resetMaterials();

                    daoSession.getMaterialCategoriesDao().load(mMaterialCategoriesId).resetMaterials();
                }

                sendMaterialRefreshBroadcast();
                finish();
            }
        });
    }

    /**
     * send the broadcast to refresh material list
     */
    private void sendMaterialRefreshBroadcast() {
        Intent intent=new Intent(Constant.ACTION_REFRESH_MATERIAL_CATEGORIES);
        sendBroadcast(intent);
    }

    /**
     * load {@link MaterialDetailFragment} to {@link MaterialEditActivity}
     * @param materialId id of material
     */
    private void addFragmentToActivity(Long materialId) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        mMaterialDetailFragment= (MaterialDetailFragment) fragmentManager.
                findFragmentById(R.id.fl_material_edit_detail_fragment_container);
        if (mMaterialDetailFragment==null){
            mMaterialDetailFragment=MaterialDetailFragment.
                    getNewInstance(materialId, Constant.EDIT_MODE);
            fragmentManager.beginTransaction().
                    add(R.id.fl_material_edit_detail_fragment_container,mMaterialDetailFragment).commit();
        }
        Log.i(TAG, "addFragmentToActivity: load MaterialDetailFragment to MaterialEditActivity");
    }

    /**
     * init the view of {@link MaterialEditActivity}
     */
    private void initView() {
        material_edit_customTitleBar= (CustomTitleBar) findViewById(R.id.material_edit_customTitleBar);
        fl_material_edit_detail_fragment_container= (FrameLayout)
                findViewById(R.id.fl_material_edit_detail_fragment_container);
    }

    /**
     * static method to start {@link MaterialEditActivity}
     * @param context the context of where to start the activity
     * @param materialId the id of material
     */
    public static void actionStart(Context context, Long materialId){
        Intent intent=new Intent(context,MaterialEditActivity.class);
        intent.putExtra(MATERIAL_ID,materialId);
        context.startActivity(intent);
    }
}
