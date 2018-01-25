package com.example.a103.datasearch;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.example.a103.datasearch.fragment.ToolDetailFragment;
import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.utils.CustomTitleBar;
import com.example.a103.datasearch.utils.DatabaseApplication;

public class ToolEditActivity extends AppCompatActivity {

    private static final String TAG = "ToolEditActivity";
    public static final String TOOL_ID="toolId";

    CustomTitleBar tool_edit_customTitleBar;
    FrameLayout fl_tool_edit_detail_fragment_container;
    ToolDetailFragment mToolDetailFragment;
    Long toolId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_edit);

        initView();
        toolId=getIntent().getLongExtra(TOOL_ID,0);
        addFragmentToActivity(toolId);

        tool_edit_customTitleBar.setTitleBarLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tool_edit_customTitleBar.setTitleBarRightBtnClickListener(new View.OnClickListener() {
            //save tool
            @Override
            public void onClick(View v) {
                DatabaseApplication.getDaoSession().getToolDao().save(mToolDetailFragment.getTool());
                sendToolRefreshBroadcast();
                finish();
            }
        });
    }

    /**
     * send the broadcast to refresh tool list
     */
    private void sendToolRefreshBroadcast() {
        Intent intent=new Intent(Constant.ACTION_REFRESH_TOOL);
        sendBroadcast(intent);
    }

    /**
     * load {@link ToolDetailFragment} to {@link ToolEditActivity}
     * @param toolId id of tool
     */
    private void addFragmentToActivity(Long toolId) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        mToolDetailFragment= (ToolDetailFragment)
                fragmentManager.findFragmentById(R.id.fl_tool_edit_detail_fragment_container);
        if (mToolDetailFragment==null){
            mToolDetailFragment=ToolDetailFragment.newInstance(toolId, Constant.EDIT_MODE);
            fragmentManager.beginTransaction().
                    add(R.id.fl_tool_edit_detail_fragment_container,mToolDetailFragment).commit();
        }
        Log.i(TAG, "addFragmentToActivity: load ToolDetailFragment to ToolEditActivity");
    }

    /**
     * initial the view of {@link ToolEditActivity}
     */
    private void initView() {
        tool_edit_customTitleBar= (CustomTitleBar)
                findViewById(R.id.tool_edit_customTitleBar);
        fl_tool_edit_detail_fragment_container= (FrameLayout)
                findViewById(R.id.fl_tool_edit_detail_fragment_container);
    }

    /**
     * static method to start {@link MachineEditActivity}
     * @param context the context of where to start the activity
     * @param toolId the id of machine
     */
    public static void actionStart(Context context, Long toolId){
        Intent intent=new Intent(context,ToolEditActivity.class);
        intent.putExtra(TOOL_ID,toolId);
        context.startActivity(intent);
    }
}
