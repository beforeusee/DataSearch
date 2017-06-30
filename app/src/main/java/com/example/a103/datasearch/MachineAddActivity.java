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

import com.example.a103.datasearch.fragment.MachineDetailFragment;
import com.example.a103.datasearch.utils.CustomTitleBar;

public class MachineAddActivity extends AppCompatActivity {

    private static final String TAG = "MachineAddActivity";
    CustomTitleBar machine_add_customTitleBar;
    MachineDetailFragment mMachineDetailFragment;
    FrameLayout fl_machine_add_detail_fragment_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_add);

        initView();
        addFragmentToActivity();
        setOnCustomTitleBarClickListener();
    }

    private void setOnCustomTitleBarClickListener() {
        //set the left button
        machine_add_customTitleBar.setTitleBarLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //set the right button
        machine_add_customTitleBar.setTitleBarRightBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2017/7/1 create a new machine and save to the database
            }
        });
    }

    /**
     * add {@link MachineDetailFragment} to {@link MachineAddActivity}
     */
    private void addFragmentToActivity() {
        FragmentManager fragmentManager=getSupportFragmentManager();
        mMachineDetailFragment= (MachineDetailFragment) fragmentManager.
               findFragmentById(R.id.fl_machine_add_detail_fragment_container);
        if (mMachineDetailFragment==null){
            mMachineDetailFragment=new MachineDetailFragment();
            FragmentTransaction transaction=fragmentManager.beginTransaction();
            transaction.add(R.id.fl_machine_add_detail_fragment_container,mMachineDetailFragment).commit();
        }
        Log.d(TAG, "addFragmentToActivity: load MachineDetailFragment to MachineAddActivity");
    }

    /**
     * init the view of {@link MachineAddActivity}
     */
    private void initView() {
        machine_add_customTitleBar= (CustomTitleBar) findViewById(R.id.machine_add_customTitleBar);
        fl_machine_add_detail_fragment_container= (FrameLayout) findViewById(R.
                id.fl_machine_add_detail_fragment_container);
    }

    /**
     * provide static method to start{@link MachineAddActivity}
     * @param context context
     */
    public static void actionStart(Context context){
        Intent intent=new Intent(context,MachineAddActivity.class);
        context.startActivity(intent);
    }
}
