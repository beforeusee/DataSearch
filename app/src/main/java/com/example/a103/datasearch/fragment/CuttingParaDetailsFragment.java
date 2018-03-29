package com.example.a103.datasearch.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.a103.datasearch.CuttingConditionsSpinnerAdapter;
import com.example.a103.datasearch.R;
import com.example.a103.datasearch.dao.DaoSession;
import com.example.a103.datasearch.data.CuttingConditions;
import com.example.a103.datasearch.data.CuttingParaDetails;
import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.utils.DatabaseApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiaoHu Zheng on 2018/3/13.
 * Email: 1050087728@qq.com
 */

public class CuttingParaDetailsFragment extends Fragment {

    //切削参数属性声明
    //切削条件
    Spinner sp_cutting_conditions;

    //主轴转速
    EditText et_cutting_para_spindle_speed;

    //进给速度
    EditText et_cutting_para_feedRate;

    //径向切宽
    EditText et_cutting_para_cutting_width;

    //轴向切深
    EditText et_cutting_para_cutting_depth;

    //每齿进给量
    EditText et_cutting_para_feed_per_tooth;

    //切削线速度
    EditText et_cutting_para_cutting_linear_velocity;

    //Y向切削力峰值
    EditText et_cutting_para_max_force_y;

    //XY向切削力峰值
    EditText et_cutting_para_max_force_xy;

    //切向力峰值
    EditText et_cutting_para_max_force_tangential;

    //最大转矩
    EditText et_cutting_para_max_torque;

    //最大切削功率
    EditText et_cutting_para_max_cutting_power;

    //材料去除率
    EditText et_cutting_para_material_removal_rate;

    //刀具寿命
    EditText et_cutting_para_tool_life;

    //数据来源
    EditText et_cutting_para_data_source;

    //数据成熟度
    EditText et_cutting_para_data_maturity;

    private static final String TAG = "CuttingParaDetailsFrag";
    public static final String CUTTING_PARA_DETAILS_ID="mCuttingParaDetailsId";
    public static final String MODE="mode";

    private CuttingConditionsSpinnerAdapter mSpinnerAdapter;
    private List<String> cuttingConditionsTitleList =new ArrayList<>();
    private List<CuttingConditions> mConditionsList;
    private DaoSession mDaoSession= DatabaseApplication.getDaoSession();
    private BroadcastReceiver mRefreshCuttingConditionsSpinnerBroadcastReceiver;

    private Long mCuttingParaDetailsId;
    private String mode;

    /**
     * 获取实例
     * @param cuttingParaDetailsId 切削参数的id
     * @return 返回实例对象
     */
    public static CuttingParaDetailsFragment getInstance(Long cuttingParaDetailsId,String mode){

        CuttingParaDetailsFragment cuttingParaDetailsFragment=new CuttingParaDetailsFragment();
        if (cuttingParaDetailsId!=null){
            Bundle args=new Bundle();
            args.putLong(CUTTING_PARA_DETAILS_ID,cuttingParaDetailsId);
            args.putString(MODE,mode);
            cuttingParaDetailsFragment.setArguments(args);
        }

        return cuttingParaDetailsFragment;
    }

    public CuttingParaDetailsFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取切削参数的Id
        if (getArguments()!=null){
            mCuttingParaDetailsId =getArguments().getLong(CUTTING_PARA_DETAILS_ID);
            mode=getArguments().getString(MODE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_cutting_para_details,container,false);

        initCuttingConditionsSpinnerData();
        initCuttingParaDetailsView(view);

        mSpinnerAdapter=new CuttingConditionsSpinnerAdapter(getContext(), cuttingConditionsTitleList);
        sp_cutting_conditions.setAdapter(mSpinnerAdapter);

        refreshCuttingConditionsSpinnerBroadcastReceiver();
        initCuttingParaDetails(mCuttingParaDetailsId,mode);

        return view;
    }

    private void initCuttingParaDetails(Long cuttingParaDetailsId,String mode) {

        if (cuttingParaDetailsId!=null){

            setCuttingParaDetailsData(cuttingParaDetailsId);

            if (mode.equals(Constant.SHOW_MODE)){
                setCuttingParaDetailsViewEnabled(false);
            }
            if (mode.equals(Constant.EDIT_MODE)){
                setCuttingParaDetailsViewEnabled(true);
            }
        }else {
            setCuttingParaDetailsViewEnabled(true);
        }
    }

    private void setCuttingParaDetailsData(Long cuttingParaDetailsId) {

        if (cuttingParaDetailsId==null){
            throw new IllegalArgumentException("mCuttingParaDetailsId is null");
        }

        this.mCuttingParaDetailsId =cuttingParaDetailsId;
        CuttingParaDetails cuttingParaDetails=mDaoSession.getCuttingParaDetailsDao().load(cuttingParaDetailsId);
        CuttingConditions cuttingConditions=mDaoSession.getCuttingConditionsDao().load(cuttingParaDetails.getCuttingConditionsId());

        int cuttingConditionsTitleIndex=getCuttingConditionsTitleList().indexOf(cuttingConditions.getMachineInfo()+"|"+
                cuttingConditions.getMaterialInfo()+"|"+
                cuttingConditions.getToolInfo());

        sp_cutting_conditions.setSelection(cuttingConditionsTitleIndex);

        et_cutting_para_spindle_speed.setText(cuttingParaDetails.getSpindleSpeed());
        et_cutting_para_feedRate.setText(cuttingParaDetails.getFeedRate());
        et_cutting_para_cutting_width.setText(cuttingParaDetails.getCuttingWidth());
        et_cutting_para_cutting_depth.setText(cuttingParaDetails.getCuttingDepth());
        et_cutting_para_feed_per_tooth.setText(cuttingParaDetails.getFeedPerTooth());
        et_cutting_para_cutting_linear_velocity.setText(cuttingParaDetails.getCuttingLinearVelocity());
        et_cutting_para_max_force_y.setText(cuttingParaDetails.getMaxForceY());
        et_cutting_para_max_force_xy.setText(cuttingParaDetails.getMaxForceXY());
        et_cutting_para_max_force_tangential.setText(cuttingParaDetails.getMaxForceTangential());
        et_cutting_para_max_torque.setText(cuttingParaDetails.getMaxTorque());
        et_cutting_para_max_cutting_power.setText(cuttingParaDetails.getMaxCuttingPower());
        et_cutting_para_material_removal_rate.setText(cuttingParaDetails.getMaterialRemovalRate());
        et_cutting_para_tool_life.setText(cuttingParaDetails.getToolLife());
        et_cutting_para_data_source.setText(cuttingParaDetails.getDataSource());
        et_cutting_para_data_maturity.setText(cuttingParaDetails.getDataMaturity());
    }

    private void setCuttingParaDetailsViewEnabled(boolean enabled) {

        sp_cutting_conditions.setEnabled(enabled);
        et_cutting_para_spindle_speed.setEnabled(enabled);
        et_cutting_para_feedRate.setEnabled(enabled);
        et_cutting_para_cutting_width.setEnabled(enabled);
        et_cutting_para_cutting_depth.setEnabled(enabled);
        et_cutting_para_feed_per_tooth.setEnabled(enabled);
        et_cutting_para_cutting_linear_velocity.setEnabled(enabled);
        et_cutting_para_max_force_y.setEnabled(enabled);
        et_cutting_para_max_force_xy.setEnabled(enabled);
        et_cutting_para_max_force_tangential.setEnabled(enabled);
        et_cutting_para_max_torque.setEnabled(enabled);
        et_cutting_para_max_cutting_power.setEnabled(enabled);
        et_cutting_para_material_removal_rate.setEnabled(enabled);
        et_cutting_para_tool_life.setEnabled(enabled);
        et_cutting_para_data_source.setEnabled(enabled);
        et_cutting_para_data_maturity.setEnabled(enabled);
    }

    private void refreshCuttingConditionsSpinnerBroadcastReceiver() {

        //创建过滤器
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(Constant.ACTION_REFRESH_CUTTING_CONDITIONS);
        //创建广播接收器
        if (mRefreshCuttingConditionsSpinnerBroadcastReceiver ==null){
            mRefreshCuttingConditionsSpinnerBroadcastReceiver =new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    //如果接收到对应的广播消息，更新切削条件列表
                    String action=intent.getAction();
                    if (action.equals(Constant.ACTION_REFRESH_CUTTING_CONDITIONS)){
                        Log.d(TAG, "onReceive: 接收到刷新切削条件Spinner的广播");
                        updateCuttingConditionsSpinnerData();
                    }
                }
            };
        }
        //当前活动注册该广播
        getActivity().registerReceiver(mRefreshCuttingConditionsSpinnerBroadcastReceiver,intentFilter);
        Log.d(TAG, "registerRefreshCuttingConditionsSpinnerBroadcastReceiver: 注册刷新切削条件Spinner列表的广播");

    }

    private void updateCuttingConditionsSpinnerData() {

        cuttingConditionsTitleList = getCuttingConditionsTitleList();
        mSpinnerAdapter.updateCuttingConditionsList(cuttingConditionsTitleList);
    }

    private List<String> getCuttingConditionsTitleList() {

        mConditionsList=mDaoSession.getCuttingConditionsDao().loadAll();
        List<String> cuttingConditionsTitleList=new ArrayList<>();
        for (int i=0;i<mConditionsList.size();i++){
            cuttingConditionsTitleList.add(i,mConditionsList.get(i).getMachineInfo()+"|"+
            mConditionsList.get(i).getMaterialInfo()+"|"+
                    mConditionsList.get(i).getToolInfo());
        }
        return cuttingConditionsTitleList;
    }




    /**
     * 初始化界面
     * @param view 根布局
     */
    private void initCuttingParaDetailsView(View view) {

        sp_cutting_conditions= (Spinner) view.findViewById(R.id.sp_cutting_conditions);
        et_cutting_para_spindle_speed= (EditText) view.findViewById(R.id.et_cutting_para_spindle_speed);
        et_cutting_para_feedRate= (EditText) view.findViewById(R.id.et_cutting_para_feedRate);
        et_cutting_para_cutting_width= (EditText) view.findViewById(R.id.et_cutting_para_cutting_width);
        et_cutting_para_cutting_depth= (EditText) view.findViewById(R.id.et_cutting_para_cutting_depth);
        et_cutting_para_feed_per_tooth= (EditText) view.findViewById(R.id.et_cutting_para_feed_per_tooth);
        et_cutting_para_cutting_linear_velocity= (EditText) view.findViewById(R.id.et_cutting_para_cutting_linear_velocity);
        et_cutting_para_max_force_y= (EditText) view.findViewById(R.id.et_cutting_para_max_force_y);
        et_cutting_para_max_force_xy= (EditText) view.findViewById(R.id.et_cutting_para_max_force_xy);
        et_cutting_para_max_force_tangential= (EditText) view.findViewById(R.id.et_cutting_para_max_force_tangential);
        et_cutting_para_max_torque= (EditText) view.findViewById(R.id.et_cutting_para_max_torque);
        et_cutting_para_max_cutting_power= (EditText) view.findViewById(R.id.et_cutting_para_max_cutting_power);
        et_cutting_para_material_removal_rate= (EditText) view.findViewById(R.id.et_cutting_para_material_removal_rate);
        et_cutting_para_tool_life= (EditText) view.findViewById(R.id.et_cutting_para_tool_life);
        et_cutting_para_data_source= (EditText) view.findViewById(R.id.et_cutting_para_data_source);
        et_cutting_para_data_maturity= (EditText) view.findViewById(R.id.et_cutting_para_data_maturity);
    }

    /**
     * init cutting conditions title spinner data
     */
    private void initCuttingConditionsSpinnerData() {

        cuttingConditionsTitleList=getCuttingConditionsTitleList();
    }

    @Override
    public void onDestroyView() {

        if (mRefreshCuttingConditionsSpinnerBroadcastReceiver!=null){
            getActivity().unregisterReceiver(mRefreshCuttingConditionsSpinnerBroadcastReceiver);
        }
        super.onDestroyView();
    }

    public CuttingParaDetails getCuttingParaDetails(){

        //if cuttingParaDetails exists,return
        if (mCuttingParaDetailsId !=null){

            //考虑到外键变化的问题
            CuttingParaDetails cuttingParaDetails=mDaoSession.getCuttingParaDetailsDao().
                    load(mCuttingParaDetailsId);
            //考虑到外键可能会改变，因此也需要保存
            setCuttingParaDetails(cuttingParaDetails);
            return cuttingParaDetails;
        }

        //if not exists ,create and return
        CuttingParaDetails cuttingParaDetails1=new CuttingParaDetails();
        setCuttingParaDetails(cuttingParaDetails1);
        return cuttingParaDetails1;
    }

    /**
     * get CuttingConditions list
     * @return mConditionList
     */
    public List<CuttingConditions> getConditionsList(){

        return mConditionsList;
    }

    private void setCuttingParaDetails(CuttingParaDetails cuttingParaDetails) {

        Long cuttingConditionsListId=sp_cutting_conditions.getSelectedItemId();
        Long cuttingConditionsId=getConditionsList().get(Integer.parseInt(cuttingConditionsListId.toString())).getId();
        cuttingParaDetails.setCuttingConditionsId(cuttingConditionsId);

        cuttingParaDetails.setSpindleSpeed(et_cutting_para_spindle_speed.getText().toString());
        cuttingParaDetails.setFeedRate(et_cutting_para_feedRate.getText().toString());
        cuttingParaDetails.setCuttingWidth(et_cutting_para_cutting_width.getText().toString());
        cuttingParaDetails.setCuttingDepth(et_cutting_para_cutting_depth.getText().toString());
        cuttingParaDetails.setFeedPerTooth(et_cutting_para_feed_per_tooth.getText().toString());
        cuttingParaDetails.setCuttingLinearVelocity(et_cutting_para_cutting_linear_velocity.getText().toString());
        cuttingParaDetails.setMaxForceY(et_cutting_para_max_force_y.getText().toString());
        cuttingParaDetails.setMaxForceXY(et_cutting_para_max_force_xy.getText().toString());
        cuttingParaDetails.setMaxForceTangential(et_cutting_para_max_force_tangential.getText().toString());
        cuttingParaDetails.setMaxTorque(et_cutting_para_max_torque.getText().toString());
        cuttingParaDetails.setMaxCuttingPower(et_cutting_para_max_cutting_power.getText().toString());
        cuttingParaDetails.setMaterialRemovalRate(et_cutting_para_material_removal_rate.getText().toString());
        cuttingParaDetails.setToolLife(et_cutting_para_tool_life.getText().toString());
        cuttingParaDetails.setDataSource(et_cutting_para_data_source.getText().toString());
        cuttingParaDetails.setDataMaturity(et_cutting_para_data_maturity.getText().toString());
    }
}
