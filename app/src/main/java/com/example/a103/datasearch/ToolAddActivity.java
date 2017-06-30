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

import com.example.a103.datasearch.data.Tool;
import com.example.a103.datasearch.fragment.ToolDetailFragment;
import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.utils.CustomTitleBar;
import com.example.a103.datasearch.utils.DatabaseApplication;

public class ToolAddActivity extends AppCompatActivity {

    private static final String TAG = "ToolAddActivity";
    //title bar
    CustomTitleBar tool_add_customTitleBar;
    //fragment's container
    FrameLayout fl_tool_add_detail_fragment_container;
    //define ToolDetailFragment
    ToolDetailFragment mToolDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_add);
        initView();
        addFragmentToActivity();
        setOnCustomTitleBarClickListener();
    }

    /**
     * set the CustomTitleBarClickListener
     */
    private void setOnCustomTitleBarClickListener() {
        //left button
        tool_add_customTitleBar.setTitleBarLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //right button
        tool_add_customTitleBar.setTitleBarRightBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createTool();
                //send broadcast before finish the activity
                sendToolRefreshBroadcast();
                Log.d(TAG, "onClick: "+"create a new tool success.");
                finish();
            }
        });
    }

    /**
     * create the tool and store to the database
     */
    private void createTool() {
        Tool tool=mToolDetailFragment.getTool();
        DatabaseApplication.getDaoSession().getToolDao().save(tool);
    }

    /**
     * send broadcast to refresh the tool list in ToolFragment
     */
    private void sendToolRefreshBroadcast(){
        Intent intent=new Intent();
        intent.setAction(Constant.ACTION_REFRESH_TOOL);
        sendBroadcast(intent);
    }

    /**
     * add toolDetailFragment to ToolAddActivity
     */
    private void addFragmentToActivity() {
        FragmentManager fragmentManager=getSupportFragmentManager();
        mToolDetailFragment= (ToolDetailFragment) fragmentManager.
                findFragmentById(R.id.fl_tool_add_detail_fragment_container);
        if (mToolDetailFragment==null){
            mToolDetailFragment=new ToolDetailFragment();
            FragmentTransaction transaction=fragmentManager.beginTransaction();
            transaction.add(R.id.fl_tool_add_detail_fragment_container,mToolDetailFragment);
            transaction.commit();
        }
        Log.d(TAG, "addFragmentToActivity: load mToolDetailFragment to ToolAddActivity.");
    }

    /**
     * initial the view of ToolAddActivity
     */
    private void initView() {
        tool_add_customTitleBar= (CustomTitleBar) findViewById(R.id.tool_add_customTitleBar);
        fl_tool_add_detail_fragment_container= (FrameLayout) findViewById(R.id.fl_tool_add_detail_fragment_container);
    }

    /**
     * provide a public static method for activity or fragment to start ToolAddActivity
     * @param context context of the activity or fragment
     */
    public static void actionStart(Context context){
        Intent intent=new Intent(context,ToolAddActivity.class);
        context.startActivity(intent);
    }
}
