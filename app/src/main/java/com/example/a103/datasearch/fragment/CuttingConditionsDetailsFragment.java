package com.example.a103.datasearch.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.a103.datasearch.R;
import com.example.a103.datasearch.dao.DaoSession;
import com.example.a103.datasearch.data.CuttingConditions;
import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.utils.DatabaseApplication;

/**
 * Created by XiaoHu Zheng on 2018/3/22.
 * Email: 1050087728@qq.com
 */

public class CuttingConditionsDetailsFragment extends Fragment {

    //界面变量声明
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

    public static final String CUTTING_CONDITIONS_ID="cuttingConditionsId";
    public static final String MODE="mode";

    private Long mCuttingConditionsId;
    private String mode;

    public CuttingConditionsDetailsFragment(){

    }

    /**
     * method to ge an object of {@link CuttingConditionsDetailsFragment}
     * @param cuttingConditionsId id of CuttingConditions
     * @param mode mode of add or edit
     * @return a new instance
     */
    public static CuttingConditionsDetailsFragment newInstance(Long cuttingConditionsId,String mode){

        CuttingConditionsDetailsFragment conditionsDetailsFragment=new CuttingConditionsDetailsFragment();
        Bundle args=new Bundle();
        args.putLong(CUTTING_CONDITIONS_ID,cuttingConditionsId);
        args.putString(MODE,mode);

        conditionsDetailsFragment.setArguments(args);

        return conditionsDetailsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments()!=null){

            mCuttingConditionsId=getArguments().getLong(CUTTING_CONDITIONS_ID);
            mode=getArguments().getString(MODE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_cutting_conditions_details,container,false);

        initView(view);

        initCuttingConditionsDetailsStatus(mCuttingConditionsId,mode);

        return view;
    }

    /**
     * init status of {@link CuttingConditionsDetailsFragment}
     * @param cuttingConditionsId id
     * @param mode mode
     */
    private void initCuttingConditionsDetailsStatus(Long cuttingConditionsId, String mode) {

        if (cuttingConditionsId!=null){

            setCuttingConditionsDetails(cuttingConditionsId);

            if (mode.equals(Constant.EDIT_MODE)){
                setCuttingConditionsDetailsViewEnabled(false);
            }
            if (mode.equals(Constant.SHOW_MODE)){
                setCuttingConditionsDetailsViewEnabled(true);
            }
        }else {
            setCuttingConditionsDetailsViewEnabled(true);
        }
    }

    private void setCuttingConditionsDetailsViewEnabled(boolean enabled) {
/*
        et_cutting_conditions_machine_info;
        et_cutting_conditions_material_info;
        et_cutting_conditions_tool_info;
        et_cutting_conditions_tool_suspension_len;
        et_cutting_conditions_cutting_method;
        et_cutting_conditions_cutting_dir;
        et_cutting_conditions_workpiece_type;
        et_cutting_conditions_process_step;
        et_cutting_conditions_process_surface;
        et_cutting_conditions_workpiece_remark;
        et_cutting_conditions_model_x1;
        et_cutting_conditions_model_x2;
        et_cutting_conditions_model_x3;
        et_cutting_conditions_model_y1;
        et_cutting_conditions_model_y2;
        et_cutting_conditions_model_y3;*/

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
    }

    private void setCuttingConditionsDetails(Long cuttingConditionsId) {

        DaoSession daoSession= DatabaseApplication.getDaoSession();
        CuttingConditions cuttingConditions=daoSession.getCuttingConditionsDao().load(cuttingConditionsId);

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
    }

    /**
     * init view of {@link CuttingConditionsDetailsFragment}
     * @param view view of {@link CuttingConditionsDetailsFragment}
     */
    private void initView(View view) {

        et_cutting_conditions_machine_info= (EditText) view.findViewById(R.id.et_cutting_conditions_machine_info);
        et_cutting_conditions_material_info= (EditText) view.findViewById(R.id.et_cutting_conditions_material_info);
        et_cutting_conditions_tool_info= (EditText) view.findViewById(R.id.et_cutting_conditions_tool_info);
        et_cutting_conditions_tool_suspension_len= (EditText) view.findViewById(R.id.et_cutting_conditions_tool_suspension_len);
        et_cutting_conditions_cutting_method= (EditText) view.findViewById(R.id.et_cutting_conditions_cutting_method);
        et_cutting_conditions_cutting_dir= (EditText) view.findViewById(R.id.et_cutting_conditions_cutting_dir);
        et_cutting_conditions_workpiece_type= (EditText) view.findViewById(R.id.et_cutting_conditions_workpiece_type);
        et_cutting_conditions_process_step= (EditText) view.findViewById(R.id.et_cutting_conditions_process_step);
        et_cutting_conditions_process_surface= (EditText) view.findViewById(R.id.et_cutting_conditions_process_surface);
        et_cutting_conditions_workpiece_remark= (EditText) view.findViewById(R.id.et_cutting_conditions_workpiece_remark);
        et_cutting_conditions_model_x1= (EditText) view.findViewById(R.id.et_cutting_conditions_model_x1);
        et_cutting_conditions_model_x2= (EditText) view.findViewById(R.id.et_cutting_conditions_model_x2);
        et_cutting_conditions_model_x3= (EditText) view.findViewById(R.id.et_cutting_conditions_model_x3);
        et_cutting_conditions_model_y1= (EditText) view.findViewById(R.id.et_cutting_conditions_model_y1);
        et_cutting_conditions_model_y2= (EditText) view.findViewById(R.id.et_cutting_conditions_model_y2);
        et_cutting_conditions_model_y3= (EditText) view.findViewById(R.id.et_cutting_conditions_model_y3);
    }

    /**
     * a method to get CuttingConditions
     * @return a object
     */
    public CuttingConditions getCuttingConditions(){

        //if CuttingConditions exists ,return
        if (mCuttingConditionsId!=null){

            CuttingConditions cuttingConditions=DatabaseApplication.getDaoSession().
                    getCuttingConditionsDao().load(mCuttingConditionsId);

            setCuttingConditionsData(cuttingConditions);
            return cuttingConditions;
        }

        //if does not exist,create a new object and return
        CuttingConditions cuttingConditions=new CuttingConditions();
        setCuttingConditionsData(cuttingConditions);
        return cuttingConditions;
    }

    private void setCuttingConditionsData(CuttingConditions cuttingConditions) {

        cuttingConditions.setMachineInfo(et_cutting_conditions_machine_info.getText().toString());
        cuttingConditions.setMaterialInfo(et_cutting_conditions_material_info.getText().toString());
        cuttingConditions.setToolInfo(et_cutting_conditions_tool_info.getText().toString());
        cuttingConditions.setToolSuspensionLen(et_cutting_conditions_tool_suspension_len.getText().toString());
        cuttingConditions.setCuttingMethod(et_cutting_conditions_cutting_method.getText().toString());
        cuttingConditions.setCuttingDirection(et_cutting_conditions_cutting_dir.getText().toString());
        cuttingConditions.setProcessStep(et_cutting_conditions_process_step.getText().toString());
        cuttingConditions.setProcessSurface(et_cutting_conditions_process_surface.getText().toString());
        cuttingConditions.setWorkpieceType(et_cutting_conditions_workpiece_type.getText().toString());
        cuttingConditions.setWorkpieceRemark(et_cutting_conditions_workpiece_remark.getText().toString());
        cuttingConditions.setModeX1(et_cutting_conditions_model_x1.getText().toString());
        cuttingConditions.setModeX2(et_cutting_conditions_model_x2.getText().toString());
        cuttingConditions.setModeX3(et_cutting_conditions_model_x3.getText().toString());
        cuttingConditions.setModeY1(et_cutting_conditions_model_y1.getText().toString());
        cuttingConditions.setModeY2(et_cutting_conditions_model_y2.getText().toString());
        cuttingConditions.setModeY3(et_cutting_conditions_model_y3.getText().toString());
    }


}
