package com.example.a103.datasearch.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.a103.datasearch.R;
import com.example.a103.datasearch.dao.DaoSession;
import com.example.a103.datasearch.data.Tool;
import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.utils.DatabaseApplication;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ToolDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ToolDetailFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TOOL_ID = "toolId";
    public static final String MODE="mode";

    //刀具信息控件声明
//    ImageView iv_tool_picture;               //刀具图片
    CheckBox cb_tool_used;                   //是否使用该刀具
    EditText et_tool_name;                   //刀具名称
    EditText et_tool_type;                   //刀具类型
    EditText et_tool_serial;                 //刀具型号
    EditText et_tool_brand;                  //刀具制造厂商
    EditText et_tool_cuttingDiameter;       //切削直径
    EditText et_tool_cuttingDiameterTOLUpper; //切削直径公差上限
    EditText et_tool_cuttingDiameterTOLLower; //切削直径公差下限
    EditText et_tool_filletRadius;           //R刀圆角半径
    EditText et_tool_depthOfCutMaximum;     //最大切深
    EditText et_tool_maxRampingAngle;       //最大坡走铣角度
    EditText et_tool_usableLength;           //有用长度
    EditText et_tool_peripheralEffectiveCuttingEdgeCount;  //周边有效切削刃数
    EditText et_tool_adaptiveInterfaceMachineDirection;    //机床侧适配接口
    EditText et_tool_connectionDiameterTolerance;           //刀柄直径公差
    EditText et_tool_grade;                   //材质
    EditText et_tool_substrate;               //基底
    EditText et_tool_coating;                 //涂层
    EditText et_tool_basicStandardGroup;     //基本标准组
    EditText et_tool_coolantEntryStyleCode;  //冷却液入口型式代码
    EditText et_tool_connectionDiameter;      //连接直径
    EditText et_tool_functionalLength;        //功能长度
    EditText et_tool_fluteHelixAngle;         //容屑槽螺旋角
    EditText et_tool_axialRakeAngle;          //轴向前角(周刃前角)
    EditText et_tool_radialRakeAngle;         //径向前角(底刃前角)
    EditText et_tool_axialRearAngle;          //轴向后角(周刃后角)
    EditText et_tool_radialRearAngle;          //径向后角(底刃后角)
    EditText et_tool_cuttingEdgeAngle;        //切削刃角(主偏角)
    EditText et_tool_faceContactDiameter;     //面接触直径
    EditText et_tool_tipChamfer;               //刀尖倒角
    EditText et_tool_chamferWidth;             //倒角宽度
    EditText et_tool_centerCuttingCapability; //中心切削能力
    EditText et_tool_maximumRegrinds;         //最大重新研磨次数
    EditText et_tool_maxRotationalSpeed;      //最大转速
    EditText et_tool_weight;                    //部件重量
    EditText et_tool_lifeCycleState;           //寿命周期状态
    EditText et_tool_suitableForMaterial;      //适合加工材料
    EditText et_tool_application;               //适用场合
    EditText et_tool_todo;                       //待定项

    private Long mToolId;
    private String mode;

    public ToolDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param toolId Parameter.
     * @return A new instance of fragment ToolDetailFragment.
     */
    public static ToolDetailFragment newInstance(Long toolId,String mode) {
        ToolDetailFragment fragment = new ToolDetailFragment();
        Bundle args = new Bundle();
        args.putLong(TOOL_ID,toolId);
        args.putString(MODE,mode);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mToolId = getArguments().getLong(TOOL_ID);
            mode=getArguments().getString(MODE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_tool_detail, container, false);
        initView(view);
        initToolDetailStatus(mToolId,mode);
        return view;
    }

    /**
     * initial the tool detail status of the fragment,if toolId is null,means create the tool,or query the tool
     * @param toolId the id of the tool
     */
    private void initToolDetailStatus(Long toolId,String mode) {
        if (toolId!=null){
            setToolDetail(toolId);
            if (mode.equals(Constant.SHOW_MODE)){
                setToolDetailViewEnabled(false);
            }
            if (mode.equals(Constant.EDIT_MODE)){
                setToolDetailViewEnabled(true);
            }
        }else {
            setToolDetailViewEnabled(true);
        }
    }

    /**
     * set the view is enabled
     * @param enabled bool type
     */
    private void setToolDetailViewEnabled(boolean enabled) {
        //设置刀具信息各editText是否可编辑,enabled 为true时可编辑，enabled 为false时不可编辑
        cb_tool_used.setEnabled(enabled);
        et_tool_name.setEnabled(enabled);
        et_tool_type.setEnabled(enabled);
        et_tool_serial.setEnabled(enabled);
        et_tool_brand.setEnabled(enabled);
        et_tool_cuttingDiameter.setEnabled(enabled);
        et_tool_cuttingDiameterTOLUpper.setEnabled(enabled);
        et_tool_cuttingDiameterTOLLower.setEnabled(enabled);
        et_tool_filletRadius.setEnabled(enabled);
        et_tool_depthOfCutMaximum.setEnabled(enabled);
        et_tool_maxRampingAngle.setEnabled(enabled);
        et_tool_usableLength.setEnabled(enabled);
        et_tool_peripheralEffectiveCuttingEdgeCount.setEnabled(enabled);
        et_tool_adaptiveInterfaceMachineDirection.setEnabled(enabled);
        et_tool_connectionDiameterTolerance.setEnabled(enabled);
        et_tool_grade.setEnabled(enabled);
        et_tool_substrate.setEnabled(enabled);
        et_tool_coating.setEnabled(enabled);
        et_tool_basicStandardGroup.setEnabled(enabled);
        et_tool_coolantEntryStyleCode.setEnabled(enabled);
        et_tool_connectionDiameter.setEnabled(enabled);
        et_tool_functionalLength.setEnabled(enabled);
        et_tool_fluteHelixAngle.setEnabled(enabled);
        et_tool_axialRakeAngle.setEnabled(enabled);
        et_tool_radialRakeAngle.setEnabled(enabled);
        et_tool_axialRearAngle.setEnabled(enabled);
        et_tool_radialRearAngle.setEnabled(enabled);
        et_tool_cuttingEdgeAngle.setEnabled(enabled);
        et_tool_faceContactDiameter.setEnabled(enabled);
        et_tool_tipChamfer.setEnabled(enabled);
        et_tool_chamferWidth.setEnabled(enabled);
        et_tool_centerCuttingCapability.setEnabled(enabled);
        et_tool_maximumRegrinds.setEnabled(enabled);
        et_tool_maxRotationalSpeed.setEnabled(enabled);
        et_tool_weight.setEnabled(enabled);
        et_tool_lifeCycleState.setEnabled(enabled);
        et_tool_suitableForMaterial.setEnabled(enabled);
        et_tool_application.setEnabled(enabled);
        et_tool_todo.setEnabled(enabled);
    }

    private void setToolDetail(Long toolId) {
        DaoSession daoSession= DatabaseApplication.getDaoSession();
        Tool tool=daoSession.getToolDao().load(toolId);

        et_tool_name.setText(tool.getName());
        et_tool_type.setText(tool.getType());
        et_tool_serial.setText(tool.getSerial());
        et_tool_brand.setText(tool.getBrand());
        et_tool_cuttingDiameter.setText(tool.getCuttingDiameter());
        et_tool_cuttingDiameterTOLUpper.setText(tool.getCuttingDiameterTOLUpper());
        et_tool_cuttingDiameterTOLLower.setText(tool.getCuttingDiameterTOLLower());
        et_tool_filletRadius.setText(tool.getFilletRadius());
        et_tool_depthOfCutMaximum.setText(tool.getDepthOfCutMaximum());
        et_tool_maxRampingAngle.setText(tool.getMaxRampingAngle());
        et_tool_usableLength.setText(tool.getUsableLength());
        et_tool_peripheralEffectiveCuttingEdgeCount.setText(tool.getPeripheralEffectiveCuttingEdgeCount());
        et_tool_adaptiveInterfaceMachineDirection.setText(tool.getAdaptiveInterfaceMachineDirection());
        et_tool_connectionDiameterTolerance.setText(tool.getConnectionDiameterTolerance());
        et_tool_grade.setText(tool.getGrade());
        et_tool_substrate.setText(tool.getSubstrate());
        et_tool_coating.setText(tool.getCoating());
        et_tool_basicStandardGroup.setText(tool.getBasicStandardGroup());
        et_tool_coolantEntryStyleCode.setText(tool.getCoolantEntryStyleCode());
        et_tool_connectionDiameter.setText(tool.getConnectionDiameter());
        et_tool_functionalLength.setText(tool.getFunctionalLength());
        et_tool_fluteHelixAngle.setText(tool.getFluteHelixAngle());
        et_tool_axialRakeAngle.setText(tool.getAxialRakeAngle());
        et_tool_radialRakeAngle.setText(tool.getRadialRakeAngle());
        et_tool_axialRearAngle.setText(tool.getAxialRearAngle());
        et_tool_radialRearAngle.setText(tool.getRadialRearAngle());
        et_tool_cuttingEdgeAngle.setText(tool.getFilletRadius());
        et_tool_faceContactDiameter.setText(tool.getFaceContactDiameter());
        et_tool_tipChamfer.setText(tool.getTipChamfer());
        et_tool_chamferWidth.setText(tool.getChamferWidth());
        et_tool_filletRadius.setText(tool.getFilletRadius());
        et_tool_maximumRegrinds.setText(tool.getMaximumRegrinds());
        et_tool_maxRotationalSpeed.setText(tool.getMaxRotationalSpeed());
        et_tool_weight.setText(tool.getWeight());
        et_tool_lifeCycleState.setText(tool.getLifeCycleState());
        et_tool_suitableForMaterial.setText(tool.getSuitableForMaterial());
        et_tool_application.setText(tool.getApplication());
        et_tool_todo.setText("刀具的备注");
        cb_tool_used.setChecked(tool.getUsed()==1);
    }

    /**
     * initial the view of fragment
     * @param view UI of fragment
     */
    private void initView(View view) {
        cb_tool_used= (CheckBox) view.findViewById(R.id.cb_tool_used);
        et_tool_name= (EditText) view.findViewById(R.id.et_tool_name);
        et_tool_type= (EditText) view.findViewById(R.id.et_tool_type);
        et_tool_serial= (EditText) view.findViewById(R.id.et_tool_serial);
        et_tool_brand= (EditText) view.findViewById(R.id.et_tool_brand);
        et_tool_cuttingDiameter= (EditText) view.findViewById(R.id.et_tool_cuttingDiameter);
        et_tool_cuttingDiameterTOLUpper= (EditText) view.findViewById(R.id.et_tool_cuttingDiameterTOLUpper);
        et_tool_cuttingDiameterTOLLower= (EditText) view.findViewById(R.id.et_tool_cuttingDiameterTOLLower);
        et_tool_filletRadius= (EditText) view.findViewById(R.id.et_tool_filletRadius);
        et_tool_depthOfCutMaximum= (EditText) view.findViewById(R.id.et_tool_depthOfCutMaximum);
        et_tool_maxRampingAngle= (EditText) view.findViewById(R.id.et_tool_maxRampingAngle);
        et_tool_usableLength= (EditText) view.findViewById(R.id.et_tool_usableLength);
        et_tool_peripheralEffectiveCuttingEdgeCount= (EditText) view.findViewById(R.id.et_tool_peripheralEffectiveCuttingEdgeCount);
        et_tool_adaptiveInterfaceMachineDirection= (EditText) view.findViewById(R.id.et_tool_adaptiveInterfaceMachineDirection);
        et_tool_connectionDiameterTolerance= (EditText) view.findViewById(R.id.et_tool_connectionDiameterTolerance);
        et_tool_grade= (EditText) view.findViewById(R.id.et_tool_grade);
        et_tool_substrate= (EditText) view.findViewById(R.id.et_tool_substrate);
        et_tool_coating= (EditText) view.findViewById(R.id.et_tool_coating);
        et_tool_basicStandardGroup= (EditText) view.findViewById(R.id.et_tool_basicStandardGroup);
        et_tool_coolantEntryStyleCode= (EditText) view.findViewById(R.id.et_tool_coolantEntryStyleCode);
        et_tool_connectionDiameter= (EditText) view.findViewById(R.id.et_tool_connectionDiameter);
        et_tool_functionalLength= (EditText) view.findViewById(R.id.et_tool_functionalLength);
        et_tool_fluteHelixAngle= (EditText) view.findViewById(R.id.et_tool_fluteHelixAngle);
        et_tool_axialRakeAngle= (EditText) view.findViewById(R.id.et_tool_axialRakeAngle);
        et_tool_radialRakeAngle= (EditText) view.findViewById(R.id.et_tool_radialRakeAngle);
        et_tool_axialRearAngle= (EditText) view.findViewById(R.id.et_tool_axialRearAngle);
        et_tool_radialRearAngle= (EditText) view.findViewById(R.id.et_tool_radialRearAngle);
        et_tool_cuttingEdgeAngle= (EditText) view.findViewById(R.id.et_tool_cuttingEdgeAngle);
        et_tool_faceContactDiameter= (EditText) view.findViewById(R.id.et_tool_faceContactDiameter);
        et_tool_tipChamfer= (EditText) view.findViewById(R.id.et_tool_tipChamfer);
        et_tool_chamferWidth= (EditText) view.findViewById(R.id.et_tool_chamferWidth);
        et_tool_centerCuttingCapability= (EditText) view.findViewById(R.id.et_tool_centerCuttingCapability);
        et_tool_maximumRegrinds= (EditText) view.findViewById(R.id.et_tool_maximumRegrinds);
        et_tool_maxRotationalSpeed= (EditText) view.findViewById(R.id.et_tool_maxRotationalSpeed);
        et_tool_weight= (EditText) view.findViewById(R.id.et_tool_weight);
        et_tool_lifeCycleState= (EditText) view.findViewById(R.id.et_tool_lifeCycleState);
        et_tool_suitableForMaterial= (EditText) view.findViewById(R.id.et_tool_suitableForMaterial);
        et_tool_application= (EditText) view.findViewById(R.id.et_tool_application);
        et_tool_todo= (EditText) view.findViewById(R.id.et_tool_todo);
    }

    public Tool getTool(){
        //if tool exist,return
        if (mToolId!=null){
            Tool tool=DatabaseApplication.getDaoSession().getToolDao().load(mToolId);
            setToolData(tool);
            return tool;
        }
        //if tool does not exist,create a new tool and return
        Tool tool=new Tool();
        setToolData(tool);
        return tool;
    }

    /**
     * set the data of tool
     * @param tool tool
     */
    private void setToolData(Tool tool) {
        tool.setName(et_tool_name.getText().toString());
        tool.setType(et_tool_type.getText().toString());
        tool.setSerial(et_tool_serial.getText().toString());
        tool.setBrand(et_tool_brand.getText().toString());
        tool.setCuttingDiameter(et_tool_cuttingDiameter.getText().toString());
        tool.setCuttingDiameterTOLUpper(et_tool_cuttingDiameterTOLUpper.getText().toString());
        tool.setCuttingDiameterTOLLower(et_tool_cuttingDiameterTOLLower.getText().toString());
        tool.setFilletRadius(et_tool_filletRadius.getText().toString());
        tool.setDepthOfCutMaximum(et_tool_depthOfCutMaximum.getText().toString());
        tool.setMaxRampingAngle(et_tool_maxRampingAngle.getText().toString());
        tool.setUsableLength(et_tool_usableLength.getText().toString());
        tool.setPeripheralEffectiveCuttingEdgeCount(et_tool_peripheralEffectiveCuttingEdgeCount.getText().toString());
        tool.setAdaptiveInterfaceMachineDirection(et_tool_adaptiveInterfaceMachineDirection.getText().toString());
        tool.setConnectionDiameterTolerance(et_tool_connectionDiameterTolerance.getText().toString());
        tool.setGrade(et_tool_grade.getText().toString());
        tool.setSubstrate(et_tool_substrate.getText().toString());
        tool.setCoating(et_tool_coating.getText().toString());
        tool.setBasicStandardGroup(et_tool_basicStandardGroup.getText().toString());
        tool.setCoolantEntryStyleCode(et_tool_coolantEntryStyleCode.getText().toString());
        tool.setConnectionDiameter(et_tool_connectionDiameter.getText().toString());
        tool.setFunctionalLength(et_tool_functionalLength.getText().toString());
        tool.setFluteHelixAngle(et_tool_fluteHelixAngle.getText().toString());
        tool.setAxialRakeAngle(et_tool_axialRakeAngle.getText().toString());
        tool.setRadialRakeAngle(et_tool_radialRakeAngle.getText().toString());
        tool.setAxialRearAngle(et_tool_axialRearAngle.getText().toString());
        tool.setRadialRearAngle(et_tool_radialRearAngle.getText().toString());
        tool.setCuttingEdgeAngle(et_tool_cuttingEdgeAngle.getText().toString());
        tool.setFaceContactDiameter(et_tool_faceContactDiameter.getText().toString());
        tool.setTipChamfer(et_tool_tipChamfer.getText().toString());
        tool.setChamferWidth(et_tool_chamferWidth.getText().toString());
        tool.setFilletRadius(et_tool_filletRadius.getText().toString());
        tool.setMaximumRegrinds(et_tool_maximumRegrinds.getText().toString());
        tool.setMaxRotationalSpeed(et_tool_maxRotationalSpeed.getText().toString());
        tool.setWeight(et_tool_weight.getText().toString());
        tool.setLifeCycleState(et_tool_lifeCycleState.getText().toString());
        tool.setSuitableForMaterial(et_tool_suitableForMaterial.getText().toString());
        tool.setApplication(et_tool_application.getText().toString());
        tool.setUsed(cb_tool_used.isChecked()?1:0);
    }

}
