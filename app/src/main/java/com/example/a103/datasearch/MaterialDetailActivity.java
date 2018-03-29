package com.example.a103.datasearch;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.a103.datasearch.dao.DaoSession;
import com.example.a103.datasearch.data.CoefficientParameters;
import com.example.a103.datasearch.data.Material;
import com.example.a103.datasearch.data.MaterialCategories;
import com.example.a103.datasearch.data.MaterialCuttingLimits;
import com.example.a103.datasearch.fragment.MaterialDetailFragment;
import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.utils.DatabaseApplication;

public class MaterialDetailActivity extends AppCompatActivity {

    private static final String TAG = "MaterialDetailActivity";
    public static final String MATERIAL_ID="mMaterialId";
    LinearLayout ll_material_show_detail_fragment_container;
    MaterialDetailFragment mMaterialDetailFragment;
    Long materialId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initView();
        //get the mMaterialId
        Intent intent=getIntent();
        materialId=intent.getLongExtra(MATERIAL_ID,0);
        addFragmentToActivity(materialId);
    }

    /**
     * load mMaterialDetailFragment to activity
     * @param materialId the id of the material
     */
    private void addFragmentToActivity(Long materialId) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        mMaterialDetailFragment= (MaterialDetailFragment) fragmentManager.
                findFragmentById(R.id.ll_material_show_detail_fragment_container);
        if (mMaterialDetailFragment==null){
            //create MaterialDetailFragment(with argument mMaterialId)
            mMaterialDetailFragment=MaterialDetailFragment.getNewInstance(materialId,Constant.SHOW_MODE);
            FragmentTransaction transaction=fragmentManager.beginTransaction();
            transaction.add(R.id.ll_material_show_detail_fragment_container,mMaterialDetailFragment);
            transaction.commit();
        }
        Log.d(TAG, "addFragmentToActivity: load mMaterialDetailFragment to MaterialDetailActivity");
    }

    /**
     * initial view
     */
    private void initView() {
        ll_material_show_detail_fragment_container = (LinearLayout) findViewById(R.id.ll_material_show_detail_fragment_container);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_material_detail,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * item of the MenuItem being selected
     * @param item the item of the menu
     * @return the item being selected
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.edit_material:
                MaterialEditActivity.actionStart(this,materialId);
                finish();
                return true;
            case R.id.delete_material:
                onMenuItemDeleteSelected();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 点击Delete的操作的函数
     */
    private void onMenuItemDeleteSelected(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("确定删除材料吗？");
        builder.setPositiveButton("删除材料", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //获取数据库管理对象
                DaoSession daoSession= DatabaseApplication.getDaoSession();
                //删除材料，切削力系数和极限限制，并更新数据库中materialCategories关联的材料
                Material material=daoSession.getMaterialDao().load(materialId);
                CoefficientParameters coefficientParameters=material.getCoefficientParameters();
                MaterialCuttingLimits materialCuttingLimits=material.getMaterialCuttingLimits();
                daoSession.getMaterialDao().delete(material);
                daoSession.getCoefficientParametersDao().delete(coefficientParameters);
                daoSession.getMaterialCuttingLimitsDao().delete(materialCuttingLimits);
                MaterialCategories materialCategories=daoSession.getMaterialCategoriesDao().
                        load(material.getMaterialCategoriesId());
                materialCategories.resetMaterials();
                //删除完毕后结束当前Activity并发送消息更新MaterialCategories中的列表
                sendRefreshExpandableListViewBroadcast();
                finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
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
     * 对外提供启动本活动{@link MaterialDetailActivity}的静态方法
     * @param context context
     */
    public static void actionStart(Context context,Long materialId){
        Intent intent=new Intent(context,MaterialDetailActivity.class);
        intent.putExtra(MATERIAL_ID,materialId);
        context.startActivity(intent);
        Log.d(TAG, "actionStart: "+"启动MaterialDetailActivity.");
    }

}
