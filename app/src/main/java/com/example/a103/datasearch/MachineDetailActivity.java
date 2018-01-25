package com.example.a103.datasearch;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.a103.datasearch.dao.DaoSession;
import com.example.a103.datasearch.fragment.MachineDetailFragment;
import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.utils.DatabaseApplication;

public class MachineDetailActivity extends AppCompatActivity {

    private static final String TAG = "MachineDetailActivity";
    public static final String MACHINE_ID="machineId";
    FrameLayout fl_machine_show_detail_fragment_container;
    MachineDetailFragment mMachineDetailFragment;
    Long machineId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_detail);

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar_activity_machine_detail);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initView();
        machineId=getIntent().getLongExtra(MACHINE_ID,0);
        addFragmentToActivity(machineId);
    }

    /**
     * load MachineDetailFragment to MachineDetailActivity
     * @param machineId the id of machine
     */
    private void addFragmentToActivity(Long machineId) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        mMachineDetailFragment= (MachineDetailFragment) fragmentManager.
                findFragmentById(R.id.fl_machine_show_detail_fragment_container);
        if (mMachineDetailFragment==null){
            mMachineDetailFragment=MachineDetailFragment.newInstance(machineId,Constant.SHOW_MODE);
            fragmentManager.beginTransaction().
                    add(R.id.fl_machine_show_detail_fragment_container,mMachineDetailFragment).commit();
        }
        Log.i(TAG, "addFragmentToActivity: load mMachineDetailFragment to MachineDetailActivity.");
    }

    /**
     * init the view of activity
     */
    private void initView() {
        fl_machine_show_detail_fragment_container= (FrameLayout)
                findViewById(R.id.fl_machine_show_detail_fragment_container);
    }

    /**
     * create OptionsMenu
     * @param menu menu of this activity
     * @return if success
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_machine_detail,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * the click event of item click
     * @param item the item of menu
     * @return the result of item being selected
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.edit_machine:
                MachineEditActivity.actionStart(this,machineId);
                finish();
                return true;
            case R.id.delete_machine:
                onMenuItemDeleteSelected();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * the click of delete machine
     */
    private void onMenuItemDeleteSelected() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("确定删除机床吗？");
        builder.setPositiveButton("删除机床", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //get daoSession
                DaoSession daoSession= DatabaseApplication.getDaoSession();
                daoSession.getMachineDao().deleteByKey(machineId);
                //send broadcast to refresh list of ToolFragment
                sendMachineRefreshBroadcast();
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
     * send the broadcast to refresh machine lists
     */
    private void sendMachineRefreshBroadcast() {
        Intent intent=new Intent();
        intent.setAction(Constant.ACTION_REFRESH_MACHINE);
        sendBroadcast(intent);
    }

    /**
     * provide the method to start {@link MachineDetailActivity}
     * @param context the context of where to start the activity
     * @param machineId the id of machine
     */
    public static void actionStart(Context context,Long machineId){
        Intent intent=new Intent(context,MachineDetailActivity.class);
        intent.putExtra(MACHINE_ID,machineId);
        context.startActivity(intent);
        Log.d(TAG, "actionStart: create MachineDetailActivity.");
    }
}
