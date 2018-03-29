package com.example.a103.datasearch.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.a103.datasearch.R;
import com.example.a103.datasearch.data.CuttingConditions;
import com.example.a103.datasearch.data.CuttingParaDetails;
import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.utils.DatabaseApplication;

/**
 * Created by XiaoHu Zheng on 2018/3/14.
 * Email: 1050087728@qq.com
 */

public class CuttingParaShowDetailsFragment extends Fragment {

    public static final String CUTTING_PARA_ID="cuttingParaId";
    public static final String MODE="mode";

    EditText et_cutting_conditions_machine_info;
    EditText et_cutting_conditions_material_info;
    EditText et_cutting_conditions_tool_info;
    EditText et_cutting_conditions_tool_suspension_len;
    EditText et_cutting_conditions_cutting_method;
    EditText et_cutting_conditions_cutting_dir;
    EditText et_cutting_conditions_workpiece_type;
    EditText et_cutting_conditions_process_step;
    EditText et_cutting_conditions_process_surface;
    EditText et_cutting_conditions_workpiece_remark;
    EditText et_cutting_conditions_model_x1;
    EditText et_cutting_conditions_model_x2;
    EditText et_cutting_conditions_model_x3;
    EditText et_cutting_conditions_model_y1;
    EditText et_cutting_conditions_model_y2;
    EditText et_cutting_conditions_model_y3;

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

    private Long mCuttingParaId;
    private String mode;

    public CuttingParaShowDetailsFragment(){

        //Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param cuttingParaId Parameter.
     * @return A new instance of fragment {@link CuttingParaShowDetailsFragment}.
     */
    public static CuttingParaShowDetailsFragment newInstance(Long cuttingParaId,String mode){

        CuttingParaShowDetailsFragment showDetailsFragment=new CuttingParaShowDetailsFragment();

        Bundle args=new Bundle();
        args.putLong(CUTTING_PARA_ID,cuttingParaId);
        args.putString(MODE,mode);

        showDetailsFragment.setArguments(args);

        return showDetailsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments()!=null){

            mCuttingParaId=getArguments().getLong(CUTTING_PARA_ID);
            mode=getArguments().getString(MODE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_cutting_para_show_details,container,false);

        initView(view);

        initCuttingParaDetailsStatus(mCuttingParaId,mode);

        return view;
    }

    /**
     * init status of {@link CuttingParaShowDetailsFragment}
     * @param cuttingParaId id
     * @param mode mode
     */
    private void initCuttingParaDetailsStatus(Long cuttingParaId, String mode) {

        if (cuttingParaId!=null){

            setCuttingParaShowDetails(cuttingParaId);

            if (mode.equals(Constant.SHOW_MODE)){

                setCuttingParaShowDetailsViewEnabled(false);
            }

            if (mode.equals(Constant.EDIT_MODE)){

                setCuttingParaShowDetailsViewEnabled(true);
            }
        }else {

            setCuttingParaShowDetailsViewEnabled(true);
        }
    }

    private void setCuttingParaShowDetailsViewEnabled(boolean enabled) {

        et_cutting_conditions_machine_info.setEnabled(enabled);
        et_cutting_conditions_material_info.setEnabled(enabled);
        et_cutting_conditions_tool_info.setEnabled(enabled);
        et_cutting_conditions_tool_suspension_len.setEnabled(enabled);
        et_cutting_conditions_cutting_method.setEnabled(enabled);
        et_cutting_conditions_cutting_dir.setEnabled(enabled);
        et_cutting_conditions_workpiece_type.setEnabled(enabled);
        et_cutting_conditions_process_step.setEnabled(enabled);
        et_cutting_conditions_process_surface.setEnabled(enabled);
        et_cutting_conditions_workpiece_remark.setEnabled(enabled);
        et_cutting_conditions_model_x1.setEnabled(enabled);
        et_cutting_conditions_model_x2.setEnabled(enabled);
        et_cutting_conditions_model_x3.setEnabled(enabled);
        et_cutting_conditions_model_y1.setEnabled(enabled);
        et_cutting_conditions_model_y2.setEnabled(enabled);
        et_cutting_conditions_model_y3.setEnabled(enabled);

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

    /**
     * set
     * @param cuttingParaId id
     */
    private void setCuttingParaShowDetails(Long cuttingParaId) {

        Long cuttingCondId=DatabaseApplication.getDaoSession().getCuttingParaDetailsDao().load(cuttingParaId).getCuttingConditionsId();
        CuttingConditions cuttingConditions= DatabaseApplication.getDaoSession().getCuttingConditionsDao().load(cuttingCondId);
        CuttingParaDetails cuttingParaDetails=DatabaseApplication.getDaoSession().getCuttingParaDetailsDao().load(cuttingParaId);

        et_cutting_conditions_machine_info.setText(cuttingConditions.getMachineInfo());
        et_cutting_conditions_material_info.setText(cuttingConditions.getMaterialInfo());
        et_cutting_conditions_tool_info.setText(cuttingConditions.getToolInfo());
        et_cutting_conditions_tool_suspension_len.setText(cuttingConditions.getToolSuspensionLen());
        et_cutting_conditions_cutting_method.setText(cuttingConditions.getCuttingMethod());
        et_cutting_conditions_cutting_dir.setText(cuttingConditions.getCuttingDirection());
        et_cutting_conditions_workpiece_type.setText(cuttingConditions.getWorkpieceType());
        et_cutting_conditions_process_step.setText(cuttingConditions.getProcessStep());
        et_cutting_conditions_process_surface.setText(cuttingConditions.getProcessSurface());
        et_cutting_conditions_workpiece_remark.setText(cuttingConditions.getWorkpieceRemark());
        et_cutting_conditions_model_x1.setText(cuttingConditions.getModeX1());
        et_cutting_conditions_model_x2.setText(cuttingConditions.getModeX2());
        et_cutting_conditions_model_x3.setText(cuttingConditions.getModeX3());
        et_cutting_conditions_model_y1.setText(cuttingConditions.getModeY1());
        et_cutting_conditions_model_y2.setText(cuttingConditions.getModeY2());
        et_cutting_conditions_model_y3.setText(cuttingConditions.getModeY3());

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

    private void initView(View view) {

        et_cutting_conditions_machine_info= (EditText) view.findViewById(R.id.et_cutting_conditions_machine_info);
        et_cutting_conditions_material_info=(EditText) view.findViewById(R.id.et_cutting_conditions_material_info);
        et_cutting_conditions_tool_info=(EditText) view.findViewById(R.id.et_cutting_conditions_tool_info);
        et_cutting_conditions_tool_suspension_len=(EditText) view.findViewById(R.id.et_cutting_conditions_tool_suspension_len);
        et_cutting_conditions_cutting_method=(EditText) view.findViewById(R.id.et_cutting_conditions_cutting_method);
        et_cutting_conditions_cutting_dir=(EditText) view.findViewById(R.id.et_cutting_conditions_cutting_dir);
        et_cutting_conditions_workpiece_type=(EditText) view.findViewById(R.id.et_cutting_conditions_workpiece_type);
        et_cutting_conditions_process_step=(EditText) view.findViewById(R.id.et_cutting_conditions_process_step);
        et_cutting_conditions_process_surface=(EditText) view.findViewById(R.id.et_cutting_conditions_process_surface);
        et_cutting_conditions_workpiece_remark=(EditText) view.findViewById(R.id.et_cutting_conditions_workpiece_remark);
        et_cutting_conditions_model_x1=(EditText) view.findViewById(R.id.et_cutting_conditions_model_x1);
        et_cutting_conditions_model_x2=(EditText) view.findViewById(R.id.et_cutting_conditions_model_x2);
        et_cutting_conditions_model_x3=(EditText) view.findViewById(R.id.et_cutting_conditions_model_x3);
        et_cutting_conditions_model_y1=(EditText) view.findViewById(R.id.et_cutting_conditions_model_y1);
        et_cutting_conditions_model_y2=(EditText) view.findViewById(R.id.et_cutting_conditions_model_y2);
        et_cutting_conditions_model_y3=(EditText) view.findViewById(R.id.et_cutting_conditions_model_y3);

        et_cutting_para_spindle_speed=(EditText) view.findViewById(R.id.et_cutting_para_spindle_speed);
        et_cutting_para_feedRate=(EditText) view.findViewById(R.id.et_cutting_para_feedRate);
        et_cutting_para_cutting_width=(EditText) view.findViewById(R.id.et_cutting_para_cutting_width);
        et_cutting_para_cutting_depth=(EditText) view.findViewById(R.id.et_cutting_para_cutting_depth);
        et_cutting_para_feed_per_tooth=(EditText) view.findViewById(R.id.et_cutting_para_feed_per_tooth);
        et_cutting_para_cutting_linear_velocity=(EditText) view.findViewById(R.id.et_cutting_para_cutting_linear_velocity);
        et_cutting_para_max_force_y=(EditText) view.findViewById(R.id.et_cutting_para_max_force_y);
        et_cutting_para_max_force_xy=(EditText) view.findViewById(R.id.et_cutting_para_max_force_xy);
        et_cutting_para_max_force_tangential=(EditText) view.findViewById(R.id.et_cutting_para_max_force_tangential);
        et_cutting_para_max_torque=(EditText) view.findViewById(R.id.et_cutting_para_max_torque);
        et_cutting_para_max_cutting_power=(EditText) view.findViewById(R.id.et_cutting_para_max_cutting_power);
        et_cutting_para_material_removal_rate=(EditText) view.findViewById(R.id.et_cutting_para_material_removal_rate);
        et_cutting_para_tool_life=(EditText) view.findViewById(R.id.et_cutting_para_tool_life);
        et_cutting_para_data_source=(EditText) view.findViewById(R.id.et_cutting_para_data_source);
        et_cutting_para_data_maturity=(EditText) view.findViewById(R.id.et_cutting_para_data_maturity);
    }

    public CuttingParaDetails getCuttingParaDetails(){

        //if CuttingParaDetails exists ,return
        if (mCuttingParaId!=null){

            CuttingParaDetails cuttingParaDetails=DatabaseApplication.getDaoSession().
                    getCuttingParaDetailsDao().load(mCuttingParaId);

            setCuttingParaDetailsData(cuttingParaDetails);

            return cuttingParaDetails;
        }

        //if not exits,create a new object and return
        CuttingParaDetails cuttingParaDetails=new CuttingParaDetails();
        setCuttingParaDetailsData(cuttingParaDetails);

        return cuttingParaDetails;
    }

    private void setCuttingParaDetailsData(CuttingParaDetails cuttingParaDetails) {

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
