package com.example.a103.datasearch;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.example.a103.datasearch.fragment.MachineDetailFragment;
import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.utils.CustomTitleBar;
import com.example.a103.datasearch.utils.DatabaseApplication;

public class MachineEditActivity extends AppCompatActivity {

    private static final String TAG = "MachineEditActivity";
    public static final String MACHINE_ID="machineId";
    CustomTitleBar machine_edit_customTitleBar;
    FrameLayout fl_machine_edit_detail_fragment_container;
    MachineDetailFragment mMachineDetailFragment;
    Long machineId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_edit);

        initView();
        machineId=getIntent().getLongExtra(MACHINE_ID,0);
        addFragmentToActivity(machineId);

        machine_edit_customTitleBar.setTitleBarRightBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save machine and send broadcast
                DatabaseApplication.getDaoSession().getMachineDao().save(mMachineDetailFragment.getMachine());
                sendMachineRefreshBroadcast();
                finish();
            }
        });

        machine_edit_customTitleBar.setTitleBarLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * send the broadcast to refresh machine list
     */
    private void sendMachineRefreshBroadcast() {
        Intent intent=new Intent(Constant.ACTION_REFRESH_MACHINE);
        sendBroadcast(intent);
    }

    /**
     * load MachineDetailFragment to MachineDetailActivity
     * @param machineId the id of machine
     */
    private void addFragmentToActivity(Long machineId) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        mMachineDetailFragment= (MachineDetailFragment) fragmentManager.
                findFragmentById(R.id.fl_machine_edit_detail_fragment_container);
        if (mMachineDetailFragment==null){
            mMachineDetailFragment=MachineDetailFragment.newInstance(machineId, Constant.EDIT_MODE);
            fragmentManager.beginTransaction().
                    add(R.id.fl_machine_edit_detail_fragment_container,mMachineDetailFragment).commit();
        }
        Log.i(TAG, "addFragmentToActivity: load mMachineDetailFragment to MachineEditActivity.");
    }

    /**
     * initial view of {@link MachineEditActivity}
     */
    private void initView() {
        machine_edit_customTitleBar= (CustomTitleBar)
                findViewById(R.id.machine_edit_customTitleBar);
        fl_machine_edit_detail_fragment_container= (FrameLayout)
                findViewById(R.id.fl_machine_edit_detail_fragment_container);
    }

    /**
     * static method to start {@link MachineEditActivity}
     * @param context the context of where to start the activity
     * @param machineId the id machine
     */
    public static void actionStart(Context context,Long machineId){
        Intent intent=new Intent(context,MachineEditActivity.class);
        intent.putExtra(MACHINE_ID,machineId);
        context.startActivity(intent);
    }
}
