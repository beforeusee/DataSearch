package com.example.a103.datasearch.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.EditText;

import com.example.a103.datasearch.R;
import com.example.a103.datasearch.dao.DaoSession;
import com.example.a103.datasearch.data.Machine;
import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.utils.DatabaseApplication;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MachineDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MachineDetailFragment extends Fragment {
    // the fragment initialization parameters
    private static final String MACHINE_ID = "machineId";
    private static final String MODE="mode";

    //控件声明
    //机床信息控件
    ImageView iv_machine_picture;
    EditText et_machine_name;
    EditText et_machine_type;
    EditText et_machine_series;
    EditText et_machine_brand;
    EditText et_machine_axis_num;
    EditText et_machine_CSYS;
    EditText et_machine_machineTable_size;
    EditText et_machine_maxWorkpieceWeight;
    EditText et_machine_autoToolChange;
    EditText et_machine_rotary_table;
    EditText et_machine_size;
    EditText et_machine_weight;
    EditText et_machine_layout;

    //主轴单元控件
    EditText et_machine_spindle_type;
    EditText et_machine_spindle_num;
    EditText et_machine_spindle_max_rpm;
    EditText et_machine_spindle_min_rpm;
    EditText et_machine_spindle_power;
    EditText et_machine_spindle_maxTorque;
    EditText et_machine_spindle_travel;
    EditText et_machine_spindle_distanceToMachineTable;
    EditText et_machine_spindle_toolAdopter;

    //进给单元控件
    EditText et_machine_feed_axis_num;//进给轴数
    EditText et_machine_axis_direction; //进给轴方向
    EditText et_machine_feedAxis_travel; //行程
    EditText et_machine_feedAxis_accuracy; //定位精度
    EditText et_machine_feedAxis_repeatability; //重复定位精度
    EditText et_machine_feedAxis_fastSpeed;  //快速进给速度
    EditText et_machine_feedAxis_cuttingSpeed; //切削速度
    EditText et_machine_feedAxis_maxAcceleration;  //最大加速度
    EditText et_machine_feedAxis_power;   //伺服电机功率
    EditText et_machine_feedAxis_torque;  //伺服电机扭矩
    EditText et_machine_feedAxis_maxTorque;  //伺服电机最大扭矩

    //刀库信息说明
    EditText et_machine_toolLibrary_toolNum;
    EditText et_machine_toolLibrary_maxToolDiameter;
    EditText et_machine_toolLibrary_maxToolLength;
    EditText et_machine_toolLibrary_maxToolWeight;

    //机床附加说明
    EditText et_machine_introduction;

    private Long machineId;
    private String mode;

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
    public static MachineDetailFragment newInstance(Long machineId,String mode) {
        MachineDetailFragment fragment = new MachineDetailFragment();
        Bundle args = new Bundle();
        args.putLong(MACHINE_ID, machineId);
        args.putString(MODE,mode);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            machineId = getArguments().getLong(MACHINE_ID);
            mode=getArguments().getString(MODE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_machine_detail, container, false);
        initView(view);
        initMachineDetailStatus(machineId,mode);
        return view;
    }

    private void initMachineDetailStatus(Long machineId,String mode) {
        if (machineId!=null){
            setMachineDetailData(machineId);
            if (mode.equals(Constant.SHOW_MODE)){
                setMachineDetailViewEnabled(false);
            }
            if (mode.equals(Constant.EDIT_MODE)){
                setMachineDetailViewEnabled(true);
            }
        }else {
            setMachineDetailViewEnabled(true);
        }
    }

    public void setMachineDetailViewEnabled(boolean enabled) {
        //机床信息控件
        iv_machine_picture.setEnabled(enabled);
        et_machine_name.setEnabled(enabled);
        et_machine_type.setEnabled(enabled);
        et_machine_brand.setEnabled(enabled);
        et_machine_series.setEnabled(enabled);
        et_machine_axis_num.setEnabled(enabled);
        et_machine_CSYS.setEnabled(enabled);
        et_machine_machineTable_size.setEnabled(enabled);
        et_machine_maxWorkpieceWeight.setEnabled(enabled);
        et_machine_autoToolChange.setEnabled(enabled);
        et_machine_rotary_table.setEnabled(enabled);
        et_machine_size.setEnabled(enabled);
        et_machine_weight.setEnabled(enabled);
        et_machine_layout.setEnabled(enabled);

        //主轴信息控件
        et_machine_spindle_type.setEnabled(enabled);
        et_machine_spindle_num.setEnabled(enabled);
        et_machine_spindle_max_rpm.setEnabled(enabled);
        et_machine_spindle_min_rpm.setEnabled(enabled);
        et_machine_spindle_power.setEnabled(enabled);
        et_machine_spindle_maxTorque.setEnabled(enabled);
        et_machine_spindle_travel.setEnabled(enabled);
        et_machine_spindle_distanceToMachineTable.setEnabled(enabled);
        et_machine_spindle_toolAdopter.setEnabled(enabled);

        //进给轴信息控件
        et_machine_feed_axis_num.setEnabled(enabled);
        et_machine_axis_direction.setEnabled(enabled);
        et_machine_feedAxis_travel.setEnabled(enabled);
        et_machine_feedAxis_accuracy.setEnabled(enabled);
        et_machine_feedAxis_repeatability.setEnabled(enabled);
        et_machine_feedAxis_fastSpeed.setEnabled(enabled);
        et_machine_feedAxis_cuttingSpeed.setEnabled(enabled);
        et_machine_feedAxis_maxAcceleration.setEnabled(enabled);
        et_machine_feedAxis_power.setEnabled(enabled);
        et_machine_feedAxis_torque.setEnabled(enabled);
        et_machine_feedAxis_maxTorque.setEnabled(enabled);
        et_machine_toolLibrary_toolNum.setEnabled(enabled);
        et_machine_toolLibrary_maxToolDiameter.setEnabled(enabled);
        et_machine_toolLibrary_maxToolLength.setEnabled(enabled);
        et_machine_toolLibrary_maxToolWeight.setEnabled(enabled);
        //机床说明信息控件
        et_machine_introduction.setEnabled(enabled);
    }

    private void setMachineDetailData(Long machineId) {
        DaoSession daoSession= DatabaseApplication.getDaoSession();
        Machine machine=daoSession.getMachineDao().load(machineId);

        et_machine_name.setText(machine.getName());
        et_machine_type.setText(machine.getType());
        et_machine_brand.setText(machine.getBrand());
        et_machine_series.setText(machine.getSeries());
        et_machine_axis_num.setText(machine.getAxisNum());
        et_machine_CSYS.setText(machine.getControlSYS());
        et_machine_machineTable_size.setText(machine.getMachineTableSize());
        et_machine_maxWorkpieceWeight.setText(machine.getMaxWorkpieceWeight());
        et_machine_autoToolChange.setText(machine.getAutoToolChange());
        et_machine_rotary_table.setText(machine.getRotaryTable());
        et_machine_size.setText(machine.getSize());
        et_machine_weight.setText(machine.getWeight());
        et_machine_layout.setText(machine.getLayout());

        //主轴信息控件
        et_machine_spindle_type.setText(machine.getSpindleType());
        et_machine_spindle_num.setText(machine.getSpindleNum());
        et_machine_spindle_max_rpm.setText(machine.getMaxSpindleRpm());
        et_machine_spindle_min_rpm.setText(machine.getMinSpindleRpm());
        et_machine_spindle_power.setText(machine.getSpindlePower());
        et_machine_spindle_maxTorque.setText(machine.getMaxSpindleTorque());
        et_machine_spindle_travel.setText(machine.getSpindleTravel());
        et_machine_spindle_distanceToMachineTable.setText(machine.getSpindleDistanceToTable());
        et_machine_spindle_toolAdopter.setText(machine.getSpindleToolAdopter());

        //进给轴信息控件
        et_machine_feed_axis_num.setText(machine.getFeedAxisNum());
        et_machine_axis_direction.setText(machine.getFeedAxisDirection());
        et_machine_feedAxis_travel.setText(machine.getFeedAxisTravel());
        et_machine_feedAxis_accuracy.setText(machine.getFeedAxisAccuracy());
        et_machine_feedAxis_repeatability.setText(machine.getFeedAxisRepeatability());
        et_machine_feedAxis_fastSpeed.setText(machine.getFeedAxisFastSpeed());
        et_machine_feedAxis_cuttingSpeed.setText(machine.getFeedAxisCuttingSpeed());
        et_machine_feedAxis_maxAcceleration.setText(machine.getFeedAxisMaxAcceleration());
        et_machine_feedAxis_power.setText(machine.getFeedAxisPower());
        et_machine_feedAxis_torque.setText(machine.getFeedAxisTorque());
        et_machine_feedAxis_maxTorque.setText(machine.getFeedAxisMaxTorque());
        et_machine_toolLibrary_toolNum.setText(machine.getToolLibraryToolNum());
        et_machine_toolLibrary_maxToolDiameter.setText(machine.getToolLibraryMaxToolDiameter());
        et_machine_toolLibrary_maxToolLength.setText(machine.getToolLibraryMaxToolLength());
        et_machine_toolLibrary_maxToolWeight.setText(machine.getToolLibraryMaxToolWeight());
        //机床说明信息控件
        et_machine_introduction.setText(machine.getMachineIntroduction());
    }

    public Machine getMachine(){
        //if machine exists,return
        if (machineId!=null){
            Machine machine=DatabaseApplication.getDaoSession().getMachineDao().load(machineId);
            setMachineData(machine);
            return machine;
        }
        //if machine doesn't exist,create and return
        Machine machine=new Machine();
        //basic information of machine
        setMachineData(machine);
        return machine;
    }

    /**
     * set the machine data
     * @param machine machine
     */
    private void setMachineData(Machine machine) {
        //basic information of machine
        machine.setName(et_machine_name.getText().toString());
        machine.setType(et_machine_type.getText().toString());
        machine.setBrand(et_machine_brand.getText().toString());
        machine.setSeries(et_machine_series.getText().toString());
        machine.setAxisNum(et_machine_axis_num.getText().toString());
        machine.setControlSYS(et_machine_CSYS.getText().toString());
        machine.setMachineTableSize(et_machine_machineTable_size.getText().toString());
        machine.setMaxWorkpieceWeight(et_machine_maxWorkpieceWeight.getText().toString());
        machine.setAutoToolChange(et_machine_autoToolChange.getText().toString());
        machine.setRotaryTable(et_machine_rotary_table.getText().toString());
        machine.setSize(et_machine_size.getText().toString());
        machine.setWeight(et_machine_weight.getText().toString());
        machine.setLayout(et_machine_layout.getText().toString());
        //spindle information of machine
        machine.setSpindleType(et_machine_spindle_type.getText().toString());
        machine.setSpindleNum(et_machine_spindle_num.getText().toString());
        machine.setMaxSpindleRpm(et_machine_spindle_max_rpm.getText().toString());
        machine.setMinSpindleRpm(et_machine_spindle_min_rpm.getText().toString());
        machine.setSpindlePower(et_machine_spindle_power.getText().toString());
        machine.setMaxSpindleTorque(et_machine_spindle_maxTorque.getText().toString());
        machine.setSpindleTravel(et_machine_spindle_travel.getText().toString());
        machine.setSpindleDistanceToTable(et_machine_spindle_distanceToMachineTable.getText().toString());
        machine.setSpindleToolAdopter(et_machine_spindle_toolAdopter.getText().toString());
        //feed axis information of machine
        machine.setFeedAxisNum(et_machine_feed_axis_num.getText().toString());
        machine.setFeedAxisDirection(et_machine_axis_direction.getText().toString());
        machine.setFeedAxisTravel(et_machine_feedAxis_travel.getText().toString());
        machine.setFeedAxisAccuracy(et_machine_feedAxis_accuracy.getText().toString());
        machine.setFeedAxisRepeatability(et_machine_feedAxis_repeatability.getText().toString());
        machine.setFeedAxisFastSpeed(et_machine_feedAxis_fastSpeed.getText().toString());
        machine.setFeedAxisCuttingSpeed(et_machine_feedAxis_cuttingSpeed.getText().toString());
        machine.setFeedAxisMaxAcceleration(et_machine_feedAxis_maxAcceleration.getText().toString());
        machine.setFeedAxisPower(et_machine_feedAxis_power.getText().toString());
        machine.setFeedAxisTorque(et_machine_feedAxis_torque.getText().toString());
        machine.setFeedAxisMaxTorque(et_machine_feedAxis_maxTorque.getText().toString());
        //tool library information of machine
        machine.setToolLibraryToolNum(et_machine_toolLibrary_toolNum.getText().toString());
        machine.setToolLibraryMaxToolDiameter(et_machine_toolLibrary_maxToolDiameter.getText().toString());
        machine.setToolLibraryMaxToolLength(et_machine_toolLibrary_maxToolLength.getText().toString());
        machine.setToolLibraryMaxToolWeight(et_machine_toolLibrary_maxToolWeight.getText().toString());
        //introduction of machine
        machine.setMachineIntroduction(et_machine_introduction.getText().toString());
    }

    /**
     * init view of MachineDetailFragment
     * @param view the view of MachineDetailFragment
     */
    private void initView(View view) {
        //机床信息控件
        iv_machine_picture= (ImageView) view.findViewById(R.id.iv_machine_picture);
        et_machine_name = (EditText) view.findViewById(R.id.et_machine_name);
        et_machine_type = (EditText) view.findViewById(R.id.et_machine_type);
        et_machine_brand = (EditText) view.findViewById(R.id.et_machine_brand);
        et_machine_series = (EditText) view.findViewById(R.id.et_machine_series);
        et_machine_axis_num = (EditText) view.findViewById(R.id.et_machine_axis_num);
        et_machine_CSYS = (EditText) view.findViewById(R.id.et_machine_CSYS);
        et_machine_machineTable_size = (EditText) view.findViewById(R.id.et_machine_machineTable_size);
        et_machine_maxWorkpieceWeight= (EditText) view.findViewById(R.id.et_machine_maxWorkpieceWeight);
        et_machine_autoToolChange = (EditText) view.findViewById(R.id.et_machine_autoToolChange);
        et_machine_rotary_table= (EditText) view.findViewById(R.id.et_machine_rotary_table);
        et_machine_size = (EditText) view.findViewById(R.id.et_machine_size);
        et_machine_weight = (EditText) view.findViewById(R.id.et_machine_weight);
        et_machine_layout = (EditText) view.findViewById(R.id.et_machine_layout);

        //主轴信息控件
        et_machine_spindle_type = (EditText) view.findViewById(R.id.et_machine_spindle_type);
        et_machine_spindle_num= (EditText) view.findViewById(R.id.et_machine_spindle_num);
        et_machine_spindle_max_rpm= (EditText) view.findViewById(R.id.et_machine_spindle_max_rpm);
        et_machine_spindle_min_rpm= (EditText) view.findViewById(R.id.et_machine_spindle_min_rpm);
        et_machine_spindle_power = (EditText) view.findViewById(R.id.et_machine_spindle_power);
        et_machine_spindle_maxTorque = (EditText) view.findViewById(R.id.et_machine_spindle_maxTorque);
        et_machine_spindle_travel= (EditText) view.findViewById(R.id.et_machine_spindle_travel);
        et_machine_spindle_distanceToMachineTable = (EditText) view.findViewById(R.id.et_machine_spindle_distanceToMachineTable);
        et_machine_spindle_toolAdopter = (EditText) view.findViewById(R.id.et_machine_spindle_toolAdopter);

        //进给轴信息控件
        et_machine_feed_axis_num= (EditText) view.findViewById(R.id.et_machine_feed_axis_num);
        et_machine_axis_direction= (EditText) view.findViewById(R.id.et_machine_axis_direction);
        et_machine_feedAxis_travel= (EditText) view.findViewById(R.id.et_machine_feedAxis_travel);
        et_machine_feedAxis_accuracy= (EditText) view.findViewById(R.id.et_machine_feedAxis_accuracy);
        et_machine_feedAxis_repeatability= (EditText) view.findViewById(R.id.et_machine_feedAxis_repeatability);
        et_machine_feedAxis_fastSpeed= (EditText) view.findViewById(R.id.et_machine_feedAxis_fastSpeed);
        et_machine_feedAxis_cuttingSpeed = (EditText) view.findViewById(R.id.et_machine_feedAxis_cuttingSpeed);
        et_machine_feedAxis_maxAcceleration = (EditText) view.findViewById(R.id.et_machine_feedAxis_maxAcceleration);
        et_machine_feedAxis_power= (EditText) view.findViewById(R.id.et_machine_feedAxis_power);
        et_machine_feedAxis_torque= (EditText) view.findViewById(R.id.et_machine_feedAxis_torque);
        et_machine_feedAxis_maxTorque= (EditText) view.findViewById(R.id.et_machine_feedAxis_maxTorque);
        //刀库信息控件
        et_machine_toolLibrary_toolNum = (EditText) view.findViewById(R.id.et_machine_toolLibrary_toolNum);
        et_machine_toolLibrary_maxToolDiameter = (EditText) view.findViewById(R.id.et_machine_toolLibrary_maxToolDiameter);
        et_machine_toolLibrary_maxToolLength = (EditText) view.findViewById(R.id.et_machine_toolLibrary_maxToolLength);
        et_machine_toolLibrary_maxToolWeight = (EditText) view.findViewById(R.id.et_machine_toolLibrary_maxToolWeight);

        //机床说明信息控件
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

        et_machine_size.setText("1600x1840x2110");
        et_machine_weight.setText("3000");
        et_machine_layout.setText("摇篮式五轴联动加工中心");

        //主轴信息设置
        et_machine_spindle_type.setText("电主轴");
        et_machine_spindle_toolAdopter.setText("BT30");
        et_machine_spindle_maxTorque.setText("30");
        et_machine_spindle_distanceToMachineTable.setText("355-755");

        et_machine_spindle_power.setText("3.7");

        //进给轴信息设置

        et_machine_feedAxis_cuttingSpeed.setText("1-5000");
        et_machine_feedAxis_maxAcceleration.setText("20");


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
