package com.example.a103.datasearch;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.a103.datasearch.dao.DaoSession;
import com.example.a103.datasearch.data.CuttingConditions;
import com.example.a103.datasearch.fragment.CuttingParaDetailsFragment;
import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.utils.CustomTitleBar;
import com.example.a103.datasearch.utils.DatabaseApplication;

public class CuttingParaDetailsEditActivity extends AppCompatActivity {


    private static final String TAG = "CuttingParaDetailsEditA";
    public static final String CUTTING_PARA_DETAILS_ID="mCuttingParaDetailsId";

    CustomTitleBar cutting_para_details_edit_customTitleBar;
    FrameLayout fl_cutting_para_edit_details_fragment_container;

    CuttingParaDetailsFragment mParaDetailsFragment;
    Long mCuttingParaDetailsId;
    Long mCuttingConditionsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cutting_para_details_edit);

        initView();

        mCuttingParaDetailsId =getIntent().getLongExtra(CUTTING_PARA_DETAILS_ID,0);
        mCuttingConditionsId=DatabaseApplication.getDaoSession().getCuttingParaDetailsDao().
                load(mCuttingParaDetailsId).getCuttingConditionsId();

        addFragmentToActivity(mCuttingParaDetailsId);

        cutting_para_details_edit_customTitleBar.setTitleBarLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        cutting_para_details_edit_customTitleBar.setTitleBarRightBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DaoSession daoSession=DatabaseApplication.getDaoSession();
                daoSession.getCuttingParaDetailsDao().save(mParaDetailsFragment.getCuttingParaDetails());

                //重置该切削参数对应的切削条件的切削参数列表
                //如果CuttingConditions的id变化了，则需要同时更新变化前的切削条件对应的切削参数和变化后的切削条件对应的切削参数

                CuttingConditions cuttingConditions=daoSession.getCuttingConditionsDao().
                        load(mParaDetailsFragment.getCuttingParaDetails().getCuttingConditionsId());

                if (cuttingConditions.getId().equals(mCuttingConditionsId)){

                    cuttingConditions.resetMCuttingParaDetailsList();
                }else {

                    cuttingConditions.resetMCuttingParaDetailsList();

                    daoSession.getCuttingConditionsDao().load(mCuttingConditionsId).resetMCuttingParaDetailsList();
                }

                //删除完毕后结束当前Activity并发送广播更新切削参数列表
                sendRefreshCuttingConditionsExpandListViewBroadcast();

                finish();
            }
        });
    }

    /**
     * add {@link CuttingParaDetailsFragment} TO {@link CuttingParaDetailsEditActivity}
     * @param cuttingParaDetailsId id of {@link com.example.a103.datasearch.data.CuttingParaDetails}
     */
    private void addFragmentToActivity(Long cuttingParaDetailsId) {

        FragmentManager fragmentManager=getSupportFragmentManager();

        mParaDetailsFragment= (CuttingParaDetailsFragment) fragmentManager.
                findFragmentById(R.id.fl_cutting_para_edit_details_fragment_container);

        if (mParaDetailsFragment==null){

            mParaDetailsFragment=CuttingParaDetailsFragment.getInstance(cuttingParaDetailsId,Constant.EDIT_MODE);

            fragmentManager.beginTransaction().
                    add(R.id.fl_cutting_para_edit_details_fragment_container,mParaDetailsFragment).commit();
        }
    }

    /**
     * init view of {@link CuttingParaDetailsEditActivity}
     */
    private void initView() {

        cutting_para_details_edit_customTitleBar= (CustomTitleBar) findViewById(R.id.cutting_para_details_edit_customTitleBar);
        fl_cutting_para_edit_details_fragment_container= (FrameLayout) findViewById(R.id.fl_cutting_para_edit_details_fragment_container);
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
     * static method to start {@link CuttingParaDetailsEditActivity}
     * @param context the context of where to start the activity
     * @param cuttingParaDetailsId the id of material
     */
    public static void actionStart(Context context, Long cuttingParaDetailsId){

        Intent intent=new Intent(context,CuttingParaDetailsEditActivity.class);
        intent.putExtra(CUTTING_PARA_DETAILS_ID,cuttingParaDetailsId);

        context.startActivity(intent);
    }
}
