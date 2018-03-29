package com.example.a103.datasearch;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.a103.datasearch.fragment.CuttingConditionsDetailsFragment;
import com.example.a103.datasearch.utils.CustomTitleBar;
import com.example.a103.datasearch.utils.DatabaseApplication;
import com.example.a103.datasearch.utils.Constant;

import static com.example.a103.datasearch.utils.Constant.ACTION_REFRESH_CUTTING_CONDITIONS;
import static com.example.a103.datasearch.utils.Constant.EDIT_MODE;

public class CuttingConditionsEditActivity extends AppCompatActivity {

    private static final String TAG="CuttingConditionsEditActivity";
    public static final String CUTTING_CONDITIONS_ID="cuttingConditionsId";

    CustomTitleBar cutting_conditions_edit_customTitleBar;
    FrameLayout fl_cutting_conditions_edit_fragment_container;
    CuttingConditionsDetailsFragment mDetailsFragment;
    Long cuttingConditionsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cutting_conditions_edit);

        initView();

        cuttingConditionsId=getIntent().getLongExtra(CUTTING_CONDITIONS_ID,0);
        addFragmentToActivity(cuttingConditionsId);

        cutting_conditions_edit_customTitleBar.setTitleBarLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cutting_conditions_edit_customTitleBar.setTitleBarRightBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseApplication.getDaoSession().getCuttingConditionsDao().
                        save(mDetailsFragment.getCuttingConditions());
                sendCuttingConditionsRefreshBroadcast();
                finish();
            }
        });
    }

    /**
     * send broadcast
     */
    private void sendCuttingConditionsRefreshBroadcast() {

        Intent intent=new Intent(ACTION_REFRESH_CUTTING_CONDITIONS);
        sendBroadcast(intent);
    }

    /**
     * add fragment to {@link CuttingConditionsEditActivity}
     * @param cuttingConditionsId id of cuttingConditions
     */
    private void addFragmentToActivity(Long cuttingConditionsId) {

        FragmentManager fragmentManager=getSupportFragmentManager();
        mDetailsFragment= (CuttingConditionsDetailsFragment) fragmentManager.
                findFragmentById(R.id.fl_cutting_conditions_edit_fragment_container);

        if (mDetailsFragment== null){

            mDetailsFragment=CuttingConditionsDetailsFragment.newInstance(cuttingConditionsId,EDIT_MODE);

            fragmentManager.beginTransaction().
                    add(R.id.fl_cutting_conditions_edit_fragment_container,mDetailsFragment).commit();
        }
    }

    /**
     * init view of {@link CuttingConditionsEditActivity}
     */
    private void initView() {

        cutting_conditions_edit_customTitleBar= (CustomTitleBar) findViewById(R.id.cutting_conditions_edit_customTitleBar);
        fl_cutting_conditions_edit_fragment_container= (FrameLayout) findViewById(R.id.fl_cutting_conditions_edit_fragment_container);
    }

    /**
     * a method to start {@link CuttingConditionsEditActivity}
     * @param context context of where to start the activity
     * @param cuttingConditionsId the id of CuttingConditions
     */
    public static void actionStart(Context context,Long cuttingConditionsId){

        Intent intent=new Intent(context,CuttingConditionsEditActivity.class);
        intent.putExtra(CUTTING_CONDITIONS_ID,cuttingConditionsId);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(intent);
    }
}
