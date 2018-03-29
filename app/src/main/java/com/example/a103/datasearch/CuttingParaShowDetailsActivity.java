package com.example.a103.datasearch;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.a103.datasearch.dao.DaoSession;
import com.example.a103.datasearch.data.CuttingConditions;
import com.example.a103.datasearch.data.CuttingParaDetails;
import com.example.a103.datasearch.fragment.CuttingParaShowDetailsFragment;
import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.utils.CustomTitleBar;
import com.example.a103.datasearch.utils.DatabaseApplication;

public class CuttingParaShowDetailsActivity extends AppCompatActivity {

    private static final String TAG = "CuttingParaShowDetailsA";
    public static final String CUTTING_PARA_DETAILS_ID="mCuttingParaDetailsId";

    FrameLayout fl_cutting_para_show_details_fragment_container;
    CuttingParaShowDetailsFragment mShowDetailsFragment;
    Long cuttingParaDetailsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cutting_para_show_details);

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initView();

        //get the cuttingParaId
        Intent intent=getIntent();
        cuttingParaDetailsId=intent.getLongExtra(CUTTING_PARA_DETAILS_ID,0);

        addFragmentToActivity(cuttingParaDetailsId);
    }

    /**
     * add {@link CuttingParaShowDetailsFragment} to {@link CuttingParaShowDetailsActivity}
     * @param cuttingParaDetailsId ID
     */
    private void addFragmentToActivity(Long cuttingParaDetailsId) {

        FragmentManager fragmentManager=getSupportFragmentManager();

        mShowDetailsFragment= (CuttingParaShowDetailsFragment) fragmentManager.
                findFragmentById(R.id.fl_cutting_para_show_details_fragment_container);

        if (mShowDetailsFragment==null){

            //create CuttingParaShowDetailsFragment with mCuttingParaDetailsId
            mShowDetailsFragment=CuttingParaShowDetailsFragment.newInstance(cuttingParaDetailsId, Constant.SHOW_MODE);

            FragmentTransaction transaction=fragmentManager.beginTransaction();
            transaction.add(R.id.fl_cutting_para_show_details_fragment_container,mShowDetailsFragment);
            transaction.commit();
        }
    }


    /**
     * init view
     */
    private void initView() {

        fl_cutting_para_show_details_fragment_container= (FrameLayout) findViewById(R.id.fl_cutting_para_show_details_fragment_container);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_activity_para_detail,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.edit_parameter:
                // 编辑材料的活动
                CuttingParaDetailsEditActivity.actionStart(this,cuttingParaDetailsId);
                finish();
                return true;
            case R.id.delete_parameter:
                onMenuItemDeleteSelected();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuItemDeleteSelected() {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("确定删除该切削参数吗?");

        builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                DaoSession daoSession=DatabaseApplication.getDaoSession();
                CuttingParaDetails cuttingParaDetails= daoSession.getCuttingParaDetailsDao().load(cuttingParaDetailsId);

                daoSession.getCuttingParaDetailsDao().delete(cuttingParaDetails);

                CuttingConditions cuttingConditions=daoSession.getCuttingConditionsDao().
                        load(cuttingParaDetails.getCuttingConditionsId());

                cuttingConditions.resetMCuttingParaDetailsList();

                //删除完毕后结束当前Activity并发送广播更新切削参数列表
                sendRefreshCuttingConditionsExpandListViewBroadcast();

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
     * broadcast to refresh Expandable List View in {@link com.example.a103.datasearch.fragment.CuttingConditionsFragment}
     */
    private void sendRefreshCuttingConditionsExpandListViewBroadcast() {

        Intent intent=new Intent();
        intent.setAction(Constant.ACTION_REFRESH_CUTTING_CONDITIONS);
        sendBroadcast(intent);
    }

    /**
     * provide a method to start {@link CuttingParaShowDetailsActivity}
     * @param context current context
     * @param cuttingParaDetailsId id of {@link CuttingParaDetails}
     */
    public static void actionStart(Context context,Long cuttingParaDetailsId){

        Intent intent=new Intent(context,CuttingParaShowDetailsActivity.class);
        intent.putExtra(CUTTING_PARA_DETAILS_ID,cuttingParaDetailsId);

        context.startActivity(intent);
    }
}
