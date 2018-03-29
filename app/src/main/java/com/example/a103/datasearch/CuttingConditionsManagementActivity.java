package com.example.a103.datasearch;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.a103.datasearch.dao.DaoSession;
import com.example.a103.datasearch.data.CuttingConditions;
import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.utils.CustomTitleBar;
import com.example.a103.datasearch.utils.DatabaseApplication;

import java.util.ArrayList;
import java.util.List;

public class CuttingConditionsManagementActivity extends AppCompatActivity {

    private static final String TAG = "CuttingConditionsManage";
    CustomTitleBar cutting_conditions_management_customTitleBar;
    RecyclerView mRecyclerView;
    LinearLayout mAddBarLinearLayout;
    private List<CuttingConditions> mCuttingConditionsList=new ArrayList<>();
    private DaoSession mDaoSession;

    // 适配器
    private CuttingConditionsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cutting_conditions_management);

        //初始化界面
        initCuttingConditionsManagementView();

        //初始化切削条件信息，从数据库获切削条件分组
        initCuttingConditions();

        //RecyclerView设置布局管理器，此处设置为线性布局
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        //创建并设置mRecyclerView的适配器
        mAdapter=new CuttingConditionsAdapter(mCuttingConditionsList);
        mRecyclerView.setAdapter(mAdapter);

        //设置mAddBarLinearLayout的监听回调函数
        mAddBarLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CuttingConditionsAddActivity.actionStart(getApplicationContext());
                finish();
            }
        });

        //RecyclerView的点击事件：采用了设计模式的中的观察者模式
        //利用adapter设置列表单击事件，监听接口自定义设置在CuttingConditionsAdapter中
        mAdapter.setOnItemTextViewClickViewListener(new CuttingConditionsAdapter.OnRecyclerViewItemTextViewClickListener() {
            @Override
            public void onItemTextViewClick(View view, int position) {

                //获取点击的对象
                final CuttingConditions cuttingConditions = mCuttingConditionsList.get(position);

                CuttingConditionsEditActivity.actionStart(getApplicationContext(),cuttingConditions.getId());

                finish();
            }
        });

        mAdapter.setOnItemDeleteViewClickListener(new CuttingConditionsAdapter.OnRecyclerViewItemDeleteViewClickListener() {
            @Override
            public void onItemDeleteViewClick(View view, int position) {

                final CuttingConditions cuttingConditions=mCuttingConditionsList.get(position);

                //弹出删除的警告对话框
                AlertDialog.Builder builder=new AlertDialog.Builder(CuttingConditionsManagementActivity.this);
                builder.setTitle("提示");
                builder.setMessage("确定删除该切削条件吗?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (mDaoSession.getCuttingConditionsDao().hasKey(cuttingConditions)){

                            //删除该切削条件对应的所有切削参数
                            if (cuttingConditions.getMCuttingParaDetailsList().size()>0){
                                for (int i=0;i<cuttingConditions.getMCuttingParaDetailsList().size();i++){

                                    mDaoSession.getCuttingParaDetailsDao().
                                            delete(cuttingConditions.getMCuttingParaDetailsList().get(i));
                                }
                            }

                            //删除切削条件
                            mDaoSession.getCuttingConditionsDao().delete(cuttingConditions);

                            updateCuttingConditions();

                            //print delete message
                            Log.d(TAG, "onClick: delete"+cuttingConditions.getMachineInfo()+"|"+
                            cuttingConditions.getMaterialInfo()+"|"+
                            cuttingConditions.getToolInfo());

                            sendCuttingConditionsRefreshBroadcast();
                        }else {
                            Log.d(TAG, "onClick: "+cuttingConditions.getMachineInfo()+"|"+
                                    cuttingConditions.getMaterialInfo()+"|"+
                                    cuttingConditions.getToolInfo()+"not exist");
                        }
                    }
                });


                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });

        //设置finish(right) 按钮的监听函数
        cutting_conditions_management_customTitleBar.setTitleBarRightBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //finish current activity
                finish();
            }
        });

        //set the left button of customTitleBar
        cutting_conditions_management_customTitleBar.setTitleBarLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //finish current activity
                finish();
            }
        });
    }

    /**
     * 发送更新的广播
     */
    private void sendCuttingConditionsRefreshBroadcast(){

        Intent intent=new Intent();
        intent.setAction(Constant.ACTION_REFRESH_CUTTING_CONDITIONS);
        sendBroadcast(intent);
    }

    /**
     * 初始化切削条件列表
     */
    private void initCuttingConditions() {

        mDaoSession= DatabaseApplication.getDaoSession();
        mCuttingConditionsList=mDaoSession.getCuttingConditionsDao().loadAll();
    }

    /**
     * 更新切削条件列表
     */
    private void updateCuttingConditions(){

        mCuttingConditionsList=mDaoSession.getCuttingConditionsDao().loadAll();
        mAdapter.updateCuttingConditionsList(mCuttingConditionsList);
    }

    /**
     * init view
     */
    private void initCuttingConditionsManagementView() {

        mAddBarLinearLayout= (LinearLayout) findViewById(R.id.ll_add_bar);
        cutting_conditions_management_customTitleBar= (CustomTitleBar) findViewById(R.id.cutting_conditions_management_customTitleBar);
        mRecyclerView= (RecyclerView) findViewById(R.id.cutting_conditions_management_recycler_view);
    }

    /**
     * 对外提供启动本Activity的接口
     * @param context context
     */
    public static void actionStart(Context context){

        Intent intent=new Intent(context,CuttingConditionsManagementActivity.class);
        context.startActivity(intent);
    }
}
