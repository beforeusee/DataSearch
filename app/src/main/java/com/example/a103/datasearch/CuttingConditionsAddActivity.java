package com.example.a103.datasearch;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.a103.datasearch.data.CuttingConditions;
import com.example.a103.datasearch.fragment.CuttingConditionsDetailsFragment;
import com.example.a103.datasearch.fragment.CuttingConditionsFragment;
import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.utils.CustomTitleBar;
import com.example.a103.datasearch.utils.DatabaseApplication;

public class CuttingConditionsAddActivity extends AppCompatActivity {

    private static final String TAG = "CuttingConditionsAddAct";

    CustomTitleBar mCustomTitleBar;
    FrameLayout fl_cutting_conditions_add_fragment_container;

    CuttingConditionsDetailsFragment mDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cutting_conditions_add);

        initView();

        addFragmentToActivity();

        setOnCustomTitleBarClickListener();
    }

    /**
     * CustomTitleBarClickListener of {@link CuttingConditionsAddActivity}
     */
    private void setOnCustomTitleBarClickListener() {

        //left button
        mCustomTitleBar.setTitleBarLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        //right button
        mCustomTitleBar.setTitleBarRightBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //create cutting conditions
                createCuttingConditions();

                //send broadcast
                sendCuttingConditionsRefreshBroadcast();

                finish();
            }
        });
    }

    private void sendCuttingConditionsRefreshBroadcast() {

        Intent intent=new Intent();
        intent.setAction(Constant.ACTION_REFRESH_CUTTING_CONDITIONS);
        sendBroadcast(intent);
    }

    /**
     * create a new {@link com.example.a103.datasearch.data.CuttingConditions}
     */
    private void createCuttingConditions() {

        CuttingConditions cuttingConditions=mDetailsFragment.getCuttingConditions();
        DatabaseApplication.getDaoSession().getCuttingConditionsDao().save(cuttingConditions);
    }

    /**
     * add {@link CuttingConditionsFragment} to {@link CuttingConditionsAddActivity}
     */
    private void addFragmentToActivity() {

        FragmentManager fragmentManager=getSupportFragmentManager();
        mDetailsFragment= (CuttingConditionsDetailsFragment) fragmentManager.
                findFragmentById(R.id.fl_cutting_conditions_add_fragment_container);

        if (mDetailsFragment==null){
            mDetailsFragment=new CuttingConditionsDetailsFragment();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fl_cutting_conditions_add_fragment_container,mDetailsFragment);
            fragmentTransaction.commit();
        }
    }

    /**
     * init view
     */
    private void initView() {

        mCustomTitleBar= (CustomTitleBar) findViewById(R.id.cutting_conditions_add_customTitleBar);

        fl_cutting_conditions_add_fragment_container= (FrameLayout) findViewById(R.id.fl_cutting_conditions_add_fragment_container);
    }

    /**
     * provide a public static method for activity or fragment to start {@link CuttingConditionsAddActivity}
     * @param context context of the activity or fragment
     */
    public static void actionStart(Context context){

        Intent intent=new Intent(context,CuttingConditionsAddActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
