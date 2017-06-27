package com.example.a103.datasearch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.a103.datasearch.fragment.MaterialDetailFragment;

public class MaterialDetailActivity extends AppCompatActivity {

    private static final String TAG = "MaterialDetailActivity";
    public static final String MATERIAL_ID="materialId";
    LinearLayout ll_material_show_detail_fragment_container;
    MaterialDetailFragment mMaterialDetailFragment;

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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initView();
        Intent intent=getIntent();
        Long materialId=intent.getLongExtra(MATERIAL_ID,0);
        mMaterialDetailFragment=MaterialDetailFragment.getNewInstance(materialId);
        addFragmentToActivity(mMaterialDetailFragment,materialId);
    }

    private void addFragmentToActivity(MaterialDetailFragment materialDetailFragment,Long materialId) {
        if (materialDetailFragment==null){
            throw new IllegalArgumentException("materialDetailFragment is null");
        }
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
//        materialDetailFragment.setMaterialDetailData(materialId);
        transaction.add(R.id.ll_material_show_detail_fragment_container,materialDetailFragment);
        transaction.commit();
    }

    private void initView() {
        ll_material_show_detail_fragment_container = (LinearLayout) findViewById(R.id.ll_material_show_detail_fragment_container);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
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
