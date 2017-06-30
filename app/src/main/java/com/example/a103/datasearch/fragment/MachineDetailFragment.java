package com.example.a103.datasearch.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.EditText;

import com.example.a103.datasearch.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MachineDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MachineDetailFragment extends Fragment {
    // the fragment initialization parameters
    private static final String MACHINE_ID = "machineId";

    //控件声明
    //机床信息控件
    ImageView iv_machine_picture;
    EditText et_machine_name;
    EditText et_machine_type;
    EditText et_machine_series;
    EditText et_machine_brand;
    EditText et_machine_axis_num;
    EditText et_machine_CSYS;
    EditText et_machine_autoToolChange;
    EditText et_machine_machineTable_size;
    EditText et_machine_rotaryTable_size;
    EditText et_machine_machineTable_maxLoad;
    EditText et_machine_rotaryTable_maxLoad;
    EditText et_machine_size;
    EditText et_machine_weight;
    EditText et_machine_layout;

    //主轴单元控件
    EditText et_machine_spindle_type;
    EditText et_machine_spindle_speed;
    EditText et_machine_spindle_toolAdopter;
    EditText et_machine_spindle_power;
    EditText et_machine_spindle_maxTorque;
    EditText et_machine_spindle_distanceToMachineTable;
    EditText et_machine_spindle_distanceToRotaryTable;
    EditText et_machine_spindle_distanceToColumn;

    //进给单元控件
    EditText et_machine_feedAxis_distance_xyz; //行程
    EditText et_machine_feedAxis_distance_ac;
    EditText et_machine_feedAxis_accuracy_xyz; //定位精度
    EditText et_machine_feedAxis_accuracy_ac;
    EditText et_machine_feedAxis_repeatablity_xyz; //重复定位精度
    EditText et_machine_feedAxis_repeatablity_ac;
    EditText et_machine_feedAxis_fastSpeed_xyz;
    EditText et_machine_feedAxis_fastSpeed_ac;
    EditText et_machine_feedAxis_cuttingSpeed;
    EditText et_machine_feedAxis_maxAcceleration;
    EditText et_machine_feedAxis_maxAngularAcceleration;
    EditText et_machine_feedAxis_power_xyz;
    EditText et_machine_feedAxis_power_ac;
    EditText et_machine_feedAxis_torque_xyz;
    EditText et_machine_feedAxis_torque_ac;
    EditText et_machine_feedAxis_maxTorque_xyz;
    EditText et_machine_feedAxis_maxTorque_ac;
    EditText et_machine_feedAxis_slideway;

    //刀库信息说明
    EditText et_machine_toolLibrary_toolNum;
    EditText et_machine_toolLibrary_maxToolDiameter;
    EditText et_machine_toolLibrary_maxToolLength;
    EditText et_machine_toolLibrary_maxToolWeight;

    //机床附加说明
    EditText et_machine_introduction;

    private Long machineId;

    /**
     * required empty public constructor
     */
    public MachineDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param machineId Parameter machineId.
     * @return A new instance of fragment MachineDetailFragment.
     */
    public static MachineDetailFragment newInstance(Long machineId) {
        MachineDetailFragment fragment = new MachineDetailFragment();
        Bundle args = new Bundle();
        args.putLong(MACHINE_ID, machineId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            machineId = getArguments().getLong(MACHINE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_machine_detail, container, false);
        initView(view);
        return view;
    }

    /**
     * init view of MachineDetailFragment
     * @param view the view of MachineDetailFragment
     */
    private void initView(View view) {
        //机床信息控件
        et_machine_name = (EditText) view.findViewById(R.id.et_machine_name);
        et_machine_type = (EditText) view.findViewById(R.id.et_machine_type);
        et_machine_brand = (EditText) view.findViewById(R.id.et_machine_brand);
        et_machine_series = (EditText) view.findViewById(R.id.et_machine_series);
        et_machine_axis_num = (EditText) view.findViewById(R.id.et_machine_axis_num);
        et_machine_CSYS = (EditText) view.findViewById(R.id.et_machine_CSYS);
        et_machine_autoToolChange = (EditText) view.findViewById(R.id.et_machine_autoToolChange);
        et_machine_machineTable_size = (EditText) view.findViewById(R.id.et_machine_machineTable_size);
        et_machine_rotaryTable_size = (EditText) view.findViewById(R.id.et_machine_rotaryTable_size);
        et_machine_machineTable_maxLoad = (EditText) view.findViewById(R.id.et_machine_machineTable_maxLoad);
        et_machine_rotaryTable_maxLoad = (EditText) view.findViewById(R.id.et_machine_rotaryTable_maxLoad);
        et_machine_size = (EditText) view.findViewById(R.id.et_machine_size);
        et_machine_weight = (EditText) view.findViewById(R.id.et_machine_weight);
        et_machine_layout = (EditText) view.findViewById(R.id.et_machine_layout);

        //主轴信息控件
        et_machine_spindle_type = (EditText) view.findViewById(R.id.et_machine_spindle_type);
        et_machine_spindle_speed = (EditText) view.findViewById(R.id.et_machine_spindle_speed);
        et_machine_spindle_toolAdopter = (EditText) view.findViewById(R.id.et_machine_spindle_toolAdopter);
        et_machine_spindle_maxTorque = (EditText) view.findViewById(R.id.et_machine_spindle_maxTorque);
        et_machine_spindle_distanceToMachineTable = (EditText) view.findViewById(R.id.et_machine_spindle_distanceToMachineTable);
        et_machine_spindle_distanceToRotaryTable = (EditText) view.findViewById(R.id.et_machine_spindle_distanceToRotaryTable);
        et_machine_spindle_distanceToColumn = (EditText) view.findViewById(R.id.et_machine_spindle_distanceToColumn);
        et_machine_spindle_power = (EditText) view.findViewById(R.id.et_machine_spindle_power);

        //进给轴信息控件
        et_machine_feedAxis_distance_xyz = (EditText) view.findViewById(R.id.et_machine_feedAxis_distance_xyz);
        et_machine_feedAxis_distance_ac = (EditText) view.findViewById(R.id.et_machine_feedAxis_distance_ac);
        et_machine_feedAxis_accuracy_xyz = (EditText) view.findViewById(R.id.et_machine_feedAxis_accuracy_xyz);
        et_machine_feedAxis_accuracy_ac = (EditText) view.findViewById(R.id.et_machine_feedAxis_accuracy_ac);
        et_machine_feedAxis_repeatablity_xyz = (EditText) view.findViewById(R.id.et_machine_feedAxis_repeatablity_xyz);
        et_machine_feedAxis_repeatablity_ac = (EditText) view.findViewById(R.id.et_machine_feedAxis_repeatablity_ac);
        et_machine_feedAxis_fastSpeed_xyz = (EditText) view.findViewById(R.id.et_machine_feedAxis_fastSpeed_xyz);
        et_machine_feedAxis_fastSpeed_ac = (EditText) view.findViewById(R.id.et_machine_feedAxis_fastSpeed_ac);
        et_machine_feedAxis_cuttingSpeed = (EditText) view.findViewById(R.id.et_machine_feedAxis_cuttingSpeed);
        et_machine_feedAxis_maxAcceleration = (EditText) view.findViewById(R.id.et_machine_feedAxis_maxAcceleration);
        et_machine_feedAxis_maxAngularAcceleration = (EditText) view.findViewById(R.id.et_machine_feedAxis_maxAngularAcceleration);
        et_machine_feedAxis_power_xyz = (EditText) view.findViewById(R.id.et_machine_feedAxis_power_xyz);
        et_machine_feedAxis_power_ac = (EditText) view.findViewById(R.id.et_machine_feedAxis_power_ac);
        et_machine_feedAxis_torque_xyz = (EditText) view.findViewById(R.id.et_machine_feedAxis_torque_xyz);
        et_machine_feedAxis_torque_ac = (EditText) view.findViewById(R.id.et_machine_feedAxis_torque_ac);
        et_machine_feedAxis_maxTorque_xyz = (EditText) view.findViewById(R.id.et_machine_feedAxis_maxTorque_xyz);
        et_machine_feedAxis_maxTorque_ac = (EditText) view.findViewById(R.id.et_machine_feedAxis_maxTorque_ac);
        et_machine_feedAxis_slideway = (EditText) view.findViewById(R.id.et_machine_feedAxis_slideway);

        //刀库信息控件
        et_machine_toolLibrary_toolNum = (EditText) view.findViewById(R.id.et_machine_toolLibrary_toolNum);
        et_machine_toolLibrary_maxToolDiameter = (EditText) view.findViewById(R.id.et_machine_toolLibrary_maxToolDiameter);
        et_machine_toolLibrary_maxToolLength = (EditText) view.findViewById(R.id.et_machine_toolLibrary_maxToolLength);
        et_machine_toolLibrary_maxToolWeight = (EditText) view.findViewById(R.id.et_machine_toolLibrary_maxToolWeight);

        //机床说明信息控件
        iv_machine_picture= (ImageView) view.findViewById(R.id.iv_machine_picture);
        et_machine_introduction = (EditText) view.findViewById(R.id.et_machine_introduction);
    }

    /**
     * set the info of machine
     */
    private void setMachineInfo(){
        //机床信息设置
        et_machine_name.setText("XH7132A五轴联动加工中心");
        et_machine_type.setText("五轴联动加工中心");
        et_machine_brand.setText("山东鲁南机床有限公司");
        et_machine_series.setText("XH7132A");
        et_machine_axis_num.setText("五轴");
        et_machine_CSYS.setText("Siemens 840D");
        et_machine_autoToolChange.setText("支持");
        et_machine_machineTable_size.setText("800×320");
        et_machine_rotaryTable_size.setText("φ210");
        et_machine_machineTable_maxLoad.setText("300");
        et_machine_rotaryTable_maxLoad.setText("35");
        et_machine_size.setText("1600x1840x2110");
        et_machine_weight.setText("3000");
        et_machine_layout.setText("摇篮式五轴联动加工中心");

        //主轴信息设置
        et_machine_spindle_type.setText("电主轴");
        et_machine_spindle_speed.setText("100-10000");
        et_machine_spindle_toolAdopter.setText("BT30");
        et_machine_spindle_maxTorque.setText("30");
        et_machine_spindle_distanceToMachineTable.setText("355-755");
        et_machine_spindle_distanceToRotaryTable.setText("75-475");
        et_machine_spindle_distanceToColumn.setText("360");
        et_machine_spindle_power.setText("3.7");

        //进给轴信息设置
        et_machine_feedAxis_distance_xyz.setText("500/320/400");
        et_machine_feedAxis_distance_ac.setText("±90/360");
        et_machine_feedAxis_accuracy_xyz.setText("0.01/0.01/0.01");
        et_machine_feedAxis_accuracy_ac.setText("20/20");
        et_machine_feedAxis_repeatablity_xyz.setText("0.006/0.006/0.006");
        et_machine_feedAxis_repeatablity_ac.setText("4/4");
        et_machine_feedAxis_fastSpeed_xyz.setText("18/18/18");
        et_machine_feedAxis_fastSpeed_ac.setText("250/250");
        et_machine_feedAxis_cuttingSpeed.setText("1-5000");
        et_machine_feedAxis_maxAcceleration.setText("20");
        et_machine_feedAxis_maxAngularAcceleration.setText("");
        et_machine_feedAxis_power_xyz.setText("1.57/1.57/1.57");
        et_machine_feedAxis_power_ac.setText("1.69/1.69");
        et_machine_feedAxis_torque_xyz.setText("7.5/7.5/7.5");
        et_machine_feedAxis_torque_ac.setText("4.6/4.6");
        et_machine_feedAxis_maxTorque_xyz.setText("35/35/35");
        et_machine_feedAxis_maxTorque_ac.setText("17.2/17.2");
        et_machine_feedAxis_slideway.setText("台湾滚动直线导轨");

        //刀库信息设置
        et_machine_toolLibrary_toolNum.setText("10");
        et_machine_toolLibrary_maxToolDiameter.setText("φ60/φ100");
        et_machine_toolLibrary_maxToolLength.setText("200");
        et_machine_toolLibrary_maxToolWeight.setText("5");

        //机床说明
        iv_machine_picture.setImageResource(R.drawable.machine_lunan_xh7132a_small);
        et_machine_introduction.setText("本机床是在X、Y、Z三轴立式加工中心基础上增加了" +
                "A、C两个旋转轴而组成的五轴立式数控加工中心，其中X轴采用全闭环控制,Y、Z" +
                "轴采用半闭环控制.本机床能适应从粗加工到精加工的加工要求，可完成五轴铣削等" +
                "加工要求适用于机械加工及模具制造领域.");
    }

}
