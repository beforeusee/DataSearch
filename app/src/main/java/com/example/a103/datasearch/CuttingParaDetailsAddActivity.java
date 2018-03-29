package com.example.a103.datasearch;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.example.a103.datasearch.dao.DaoSession;
import com.example.a103.datasearch.data.CuttingConditions;
import com.example.a103.datasearch.data.CuttingParaDetails;
import com.example.a103.datasearch.fragment.CuttingParaDetailsFragment;
import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.utils.CustomTitleBar;
import com.example.a103.datasearch.utils.DatabaseApplication;

import static android.R.id.undo;

public class CuttingParaDetailsAddActivity extends AppCompatActivity {

    //custom title bar
    CustomTitleBar cutting_para_details_add_customTitleBar;
    //container of CuttingParaDetailsFragment
    FrameLayout fl_cutting_para_add_details_fragment_container;

    CuttingParaDetailsFragment mCuttingParaDetailsFragment;

    private static final String TAG = "CuttingParaDetailsAddAc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cutting_para_details_add);

        initView();
        addFragmentToActivity();
        setOnCustomTitleBarClickListener();
    }

    /**
     * set the CustomTitleBarClickListener
     */
    private void setOnCustomTitleBarClickListener() {

        //back(left) button
        cutting_para_details_add_customTitleBar.setTitleBarLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //finish(right) button
        cutting_para_details_add_customTitleBar.setTitleBarRightBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //create cutting para details
                createCuttingParaDetails();

                //send broadcast before finish the activity
                sendRefreshCuttingConditionsExpandableListViewBroadcast();

                finish();
            }
        });
    }

    private void sendRefreshCuttingConditionsExpandableListViewBroadcast() {

        Intent intent=new Intent();
        intent.setAction(Constant.ACTION_REFRESH_CUTTING_CONDITIONS);
        sendBroadcast(intent);
    }

    /**
     * create CuttingParaDetails
     */
    private void createCuttingParaDetails() {

        //create a new cutting para details
        CuttingParaDetails cuttingParaDetails=mCuttingParaDetailsFragment.getCuttingParaDetails();

        //save CuttingParaDetails to database
        DaoSession daoSession= DatabaseApplication.getDaoSession();
        daoSession.getCuttingParaDetailsDao().save(cuttingParaDetails);

        Long cuttingParaDetailsId=cuttingParaDetails.getId();

        //reset CuttingParaDetailsList
        CuttingConditions cuttingConditions=daoSession.getCuttingConditionsDao().
                load(daoSession.getCuttingParaDetailsDao().load(cuttingParaDetailsId).getCuttingConditionsId());
        cuttingConditions.resetMCuttingParaDetailsList();
    }

    /**
     * add {@link CuttingParaDetailsFragment} to {@link CuttingParaDetailsAddActivity} layout
     */
    private void addFragmentToActivity() {

        FragmentManager fragmentManager=getSupportFragmentManager();
        mCuttingParaDetailsFragment= (CuttingParaDetailsFragment) fragmentManager.
                findFragmentById(R.id.fl_cutting_para_add_details_fragment_container);

        if (mCuttingParaDetailsFragment==null){
            mCuttingParaDetailsFragment=new CuttingParaDetailsFragment();
            FragmentTransaction transaction=fragmentManager.beginTransaction();
            transaction.add(R.id.fl_cutting_para_add_details_fragment_container,mCuttingParaDetailsFragment);
            transaction.commit();
        }
    }

    /**
     * init the view of {@link CuttingParaDetailsAddActivity}
     */
    private void initView() {

        cutting_para_details_add_customTitleBar= (CustomTitleBar) findViewById(R.id.cutting_para_details_add_customTitleBar);
        fl_cutting_para_add_details_fragment_container= (FrameLayout) findViewById(R.id.fl_cutting_para_add_details_fragment_container);
    }

    public static void actionStart(Context context){

        Intent intent=new Intent(context,CuttingParaDetailsAddActivity.class);
        context.startActivity(intent);
        Log.d(TAG, "actionStart: start CuttingParaDetailsAddActivity.");
    }
}
