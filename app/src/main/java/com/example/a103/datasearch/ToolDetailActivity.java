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
import com.example.a103.datasearch.fragment.ToolDetailFragment;
import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.utils.DatabaseApplication;

public class ToolDetailActivity extends AppCompatActivity {

    private static final String TAG = "ToolDetailActivity";
    public static final String TOOL_ID="toolId";
    FrameLayout fl_tool_show_detail_fragment_container;
    ToolDetailFragment mToolDetailFragment;
    Long toolId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_detail);

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar_activity_tool_detail);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initView();
        Intent intent=getIntent();
        toolId=intent.getLongExtra(TOOL_ID,0);
        addFragmentToActivity(toolId);
    }

    /**
     * load ToolDetailFragment to ToolDetailActivity
     * @param toolId the id Tool
     */
    private void addFragmentToActivity(Long toolId) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        mToolDetailFragment= (ToolDetailFragment) fragmentManager.
                findFragmentById(R.id.fl_tool_show_detail_fragment_container);
        if (mToolDetailFragment==null){
            mToolDetailFragment=ToolDetailFragment.newInstance(toolId,Constant.SHOW_MODE);
            fragmentManager.beginTransaction().
                    add(R.id.fl_tool_show_detail_fragment_container,mToolDetailFragment).commit();
        }
        Log.d(TAG, "addFragmentToActivity: load mToolDetailFragment to ToolDetailActivity.");
    }

    /**
     * create OptionsMenu
     * @param menu menu
     * @return if success
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_tool_detail,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * the click event of MenuItem
     * @param item menu item
     * @return if success
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.edit_tool:
                ToolEditActivity.actionStart(this,toolId);
                finish();
                return true;
            case R.id.delete_tool:
                onMenuItemDeleteSelected();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 点击Delete的操作的函数
     */
    private void onMenuItemDeleteSelected(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("确定删除刀具吗？");
        builder.setPositiveButton("删除刀具", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //get daoSession
                DaoSession daoSession= DatabaseApplication.getDaoSession();
                daoSession.getToolDao().deleteByKey(toolId);
                //send broadcast to refresh list of ToolFragment
                sendToolRefreshBroadcast();
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
     * 发送更新刀具列表UI的广播
     */
    private void sendToolRefreshBroadcast(){
        Intent intent=new Intent();
        intent.setAction(Constant.ACTION_REFRESH_TOOL);
        sendBroadcast(intent);
    }

    /**
     * 在方法中构建了Intent，另外detailActivity方法中需要的数据都是通过actionStart()方法的参数传递过来的，然后把
     * 它们存储到Intent中，最后调用startActivity方法启动ToolDetailActivity.所有需要启动的活动都应该添加类似的方法
     * @param context the context
     */
    public static void actionStart(Context context,Long toolId){
        Intent intent=new Intent(context,ToolDetailActivity.class);
        intent.putExtra(TOOL_ID,toolId);
        context.startActivity(intent);
        Log.d(TAG, "actionStart: "+"create ToolDetailActivity.");
    }

    /**
     * 初始化刀具的控件
     */
    private void initView() {
        fl_tool_show_detail_fragment_container= (FrameLayout) findViewById(R.
                id.fl_tool_show_detail_fragment_container);
    }
}
