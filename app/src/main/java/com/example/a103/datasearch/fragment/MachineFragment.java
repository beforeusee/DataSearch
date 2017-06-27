package com.example.a103.datasearch.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.R;

/**
 * Created by A103 on 2017/2/10.
 * 机床页面的fragment
 */

public class MachineFragment extends Fragment {
    //控件声明
    //机床信息控件
    ImageView iv_machine_picture;
    TextView tv_machine_name;
    TextView tv_machine_type;
    TextView tv_machine_series;
    TextView tv_machine_brand;
    TextView tv_machine_axis_num;
    TextView tv_machine_CSYS;
    TextView tv_machine_autoToolChange;
    TextView tv_machine_machineTable_size;
    TextView tv_machine_rotaryTable_size;
    TextView tv_machine_machineTable_maxLoad;
    TextView tv_machine_rotaryTable_maxLoad;
    TextView tv_machine_size;
    TextView tv_machine_weight;
    TextView tv_machine_layout;

    //主轴单元控件
    TextView tv_machine_spindle_type;
    TextView tv_machine_spindle_speed;
    TextView tv_machine_spindle_toolAdopter;
    TextView tv_machine_spindle_power;
    TextView tv_machine_spindle_maxTorque;
    TextView tv_machine_spindle_distanceToMachineTable;
    TextView tv_machine_spindle_distanceToRotaryTable;
    TextView tv_machine_spindle_distanceToColumn;



    //进给单元控件
    TextView tv_machine_feedAxis_distance_xyz; //行程
    TextView tv_machine_feedAxis_distance_ac;
    TextView tv_machine_feedAxis_accuracy_xyz; //定位精度
    TextView tv_machine_feedAxis_accuracy_ac;
    TextView tv_machine_feedAxis_repeatablity_xyz; //重复定位精度
    TextView tv_machine_feedAxis_repeatablity_ac;
    TextView tv_machine_feedAxis_fastSpeed_xyz;
    TextView tv_machine_feedAxis_fastSpeed_ac;
    TextView tv_machine_feedAxis_cuttingSpeed;
    TextView tv_machine_feedAxis_maxAcceleration;
    TextView tv_machine_feedAxis_maxAngularAcceleration;
    TextView tv_machine_feedAxis_power_xyz;
    TextView tv_machine_feedAxis_power_ac;
    TextView tv_machine_feedAxis_torque_xyz;
    TextView tv_machine_feedAxis_torque_ac;
    TextView tv_machine_feedAxis_maxTorque_xyz;
    TextView tv_machine_feedAxis_maxTorque_ac;
    TextView tv_machine_feedAxis_slideway;

    //刀库信息说明
    TextView tv_machine_toolLibrary_toolNum;
    TextView tv_machine_toolLibrary_maxToolDiameter;
    TextView tv_machine_toolLibrary_maxToolLength;
    TextView tv_machine_toolLibrary_maxToolWeight;

    //机床附加说明

    TextView tv_machine_introduction;

    //实例函数
    public static MachineFragment newInstance(String s){
        MachineFragment machineFragment=new MachineFragment();
        Bundle bundle=new Bundle();
        bundle.putString(Constant.ARGS,s);
        machineFragment.setArguments(bundle);
        return machineFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_machine,container,false);
//        Bundle bundle=getArguments();
//        String s=bundle.getString(Constant.ARGS);
//        TextView textView= (TextView) view.findViewById(R.id.title_machine);
//        textView.setText(s);
        initView(view);
        setMachineInfo();
        return view;
    }

    /**
     * 初始化机床信息控件
     * @param view 参数view
     */
    private void initView(View view) {
        //机床信息控件
        tv_machine_name= (TextView) view.findViewById(R.id.tv_machine_name);
        tv_machine_type= (TextView) view.findViewById(R.id.tv_machine_type);
        tv_machine_brand= (TextView) view.findViewById(R.id.tv_machine_brand);
        tv_machine_series= (TextView) view.findViewById(R.id.tv_machine_series);
        tv_machine_axis_num= (TextView) view.findViewById(R.id.tv_machine_axis_num);
        tv_machine_CSYS= (TextView) view.findViewById(R.id.tv_machine_CSYS);
        tv_machine_autoToolChange= (TextView) view.findViewById(R.id.tv_machine_autoToolChange);
        tv_machine_machineTable_size= (TextView) view.findViewById(R.id.tv_machine_machineTable_size);
        tv_machine_rotaryTable_size= (TextView) view.findViewById(R.id.tv_machine_rotaryTable_size);
        tv_machine_machineTable_maxLoad= (TextView) view.findViewById(R.id.tv_machine_machineTable_maxLoad);
        tv_machine_rotaryTable_maxLoad= (TextView) view.findViewById(R.id.tv_machine_rotaryTable_maxLoad);
        tv_machine_size= (TextView) view.findViewById(R.id.tv_machine_size);
        tv_machine_weight= (TextView) view.findViewById(R.id.tv_machine_weight);
        tv_machine_layout= (TextView) view.findViewById(R.id.tv_machine_layout);

        //主轴信息控件
        tv_machine_spindle_type= (TextView) view.findViewById(R.id.tv_machine_spindle_type);
        tv_machine_spindle_speed= (TextView) view.findViewById(R.id.tv_machine_spindle_speed);
        tv_machine_spindle_toolAdopter= (TextView) view.findViewById(R.id.tv_machine_spindle_toolAdopter);
        tv_machine_spindle_maxTorque= (TextView) view.findViewById(R.id.tv_machine_spindle_maxTorque);
        tv_machine_spindle_distanceToMachineTable= (TextView) view.findViewById(R.id.tv_machine_spindle_distanceToMachineTable);
        tv_machine_spindle_distanceToRotaryTable= (TextView) view.findViewById(R.id.tv_machine_spindle_distanceToRotaryTable);
        tv_machine_spindle_distanceToColumn= (TextView) view.findViewById(R.id.tv_machine_spindle_distanceToColumn);
        tv_machine_spindle_power= (TextView) view.findViewById(R.id.tv_machine_spindle_power);

        //进给轴信息控件
        tv_machine_feedAxis_distance_xyz= (TextView) view.findViewById(R.id.tv_machine_feedAxis_distance_xyz);
        tv_machine_feedAxis_distance_ac= (TextView) view.findViewById(R.id.tv_machine_feedAxis_distance_ac);
        tv_machine_feedAxis_accuracy_xyz= (TextView) view.findViewById(R.id.tv_machine_feedAxis_accuracy_xyz);
        tv_machine_feedAxis_accuracy_ac= (TextView) view.findViewById(R.id.tv_machine_feedAxis_accuracy_ac);
        tv_machine_feedAxis_repeatablity_xyz= (TextView) view.findViewById(R.id.tv_machine_feedAxis_repeatablity_xyz);
        tv_machine_feedAxis_repeatablity_ac= (TextView) view.findViewById(R.id.tv_machine_feedAxis_repeatablity_ac);
        tv_machine_feedAxis_fastSpeed_xyz= (TextView) view.findViewById(R.id.tv_machine_feedAxis_fastSpeed_xyz);
        tv_machine_feedAxis_fastSpeed_ac= (TextView) view.findViewById(R.id.tv_machine_feedAxis_fastSpeed_ac);
        tv_machine_feedAxis_cuttingSpeed= (TextView) view.findViewById(R.id.tv_machine_feedAxis_cuttingSpeed);
        tv_machine_feedAxis_maxAcceleration= (TextView) view.findViewById(R.id.tv_machine_feedAxis_maxAcceleration);
        tv_machine_feedAxis_maxAngularAcceleration= (TextView) view.findViewById(R.id.tv_machine_feedAxis_maxAngularAcceleration);
        tv_machine_feedAxis_power_xyz= (TextView) view.findViewById(R.id.tv_machine_feedAxis_power_xyz);
        tv_machine_feedAxis_power_ac= (TextView) view.findViewById(R.id.tv_machine_feedAxis_power_ac);
        tv_machine_feedAxis_torque_xyz= (TextView) view.findViewById(R.id.tv_machine_feedAxis_torque_xyz);
        tv_machine_feedAxis_torque_ac= (TextView) view.findViewById(R.id.tv_machine_feedAxis_torque_ac);
        tv_machine_feedAxis_maxTorque_xyz= (TextView) view.findViewById(R.id.tv_machine_feedAxis_maxTorque_xyz);
        tv_machine_feedAxis_maxTorque_ac= (TextView) view.findViewById(R.id.tv_machine_feedAxis_maxTorque_ac);
        tv_machine_feedAxis_slideway= (TextView) view.findViewById(R.id.tv_machine_feedAxis_slideway);

        //刀库信息控件
        tv_machine_toolLibrary_toolNum= (TextView) view.findViewById(R.id.tv_machine_toolLibrary_toolNum);
        tv_machine_toolLibrary_maxToolDiameter= (TextView) view.findViewById(R.id.tv_machine_toolLibrary_maxToolDiameter);
        tv_machine_toolLibrary_maxToolLength= (TextView) view.findViewById(R.id.tv_machine_toolLibrary_maxToolLength);
        tv_machine_toolLibrary_maxToolWeight= (TextView) view.findViewById(R.id.tv_machine_toolLibrary_maxToolWeight);

        //机床说明信息控件
        iv_machine_picture= (ImageView) view.findViewById(R.id.iv_machine_picture);
        tv_machine_introduction= (TextView) view.findViewById(R.id.tv_machine_introduction);
    }

    /**
     * 设置机床信息
     */
    private void setMachineInfo(){
        //机床信息设置
        tv_machine_name.setText("XH7132A五轴联动加工中心");
        tv_machine_type.setText("五轴联动加工中心");
        tv_machine_brand.setText("山东鲁南机床有限公司");
        tv_machine_series.setText("XH7132A");
        tv_machine_axis_num.setText("五轴");
        tv_machine_CSYS.setText("Siemens 840D");
        tv_machine_autoToolChange.setText("支持");
        tv_machine_machineTable_size.setText("800×320");
        tv_machine_rotaryTable_size.setText("φ210");
        tv_machine_machineTable_maxLoad.setText("300");
        tv_machine_rotaryTable_maxLoad.setText("35");
        tv_machine_size.setText("1600x1840x2110");
        tv_machine_weight.setText("3000");
        tv_machine_layout.setText("摇篮式五轴联动加工中心");

        //主轴信息设置
        tv_machine_spindle_type.setText("电主轴");
        tv_machine_spindle_speed.setText("100-10000");
        tv_machine_spindle_toolAdopter.setText("BT30");
        tv_machine_spindle_maxTorque.setText("30");
        tv_machine_spindle_distanceToMachineTable.setText("355-755");
        tv_machine_spindle_distanceToRotaryTable.setText("75-475");
        tv_machine_spindle_distanceToColumn.setText("360");
        tv_machine_spindle_power.setText("3.7");

        //进给轴信息设置
        tv_machine_feedAxis_distance_xyz.setText("500/320/400");
        tv_machine_feedAxis_distance_ac.setText("±90/360");
        tv_machine_feedAxis_accuracy_xyz.setText("0.01/0.01/0.01");
        tv_machine_feedAxis_accuracy_ac.setText("20/20");
        tv_machine_feedAxis_repeatablity_xyz.setText("0.006/0.006/0.006");
        tv_machine_feedAxis_repeatablity_ac.setText("4/4");
        tv_machine_feedAxis_fastSpeed_xyz.setText("18/18/18");
        tv_machine_feedAxis_fastSpeed_ac.setText("250/250");
        tv_machine_feedAxis_cuttingSpeed.setText("1-5000");
        tv_machine_feedAxis_maxAcceleration.setText("20");
        tv_machine_feedAxis_maxAngularAcceleration.setText("");
        tv_machine_feedAxis_power_xyz.setText("1.57/1.57/1.57");
        tv_machine_feedAxis_power_ac.setText("1.69/1.69");
        tv_machine_feedAxis_torque_xyz.setText("7.5/7.5/7.5");
        tv_machine_feedAxis_torque_ac.setText("4.6/4.6");
        tv_machine_feedAxis_maxTorque_xyz.setText("35/35/35");
        tv_machine_feedAxis_maxTorque_ac.setText("17.2/17.2");
        tv_machine_feedAxis_slideway.setText("台湾滚动直线导轨");

        //刀库信息设置
        tv_machine_toolLibrary_toolNum.setText("10");
        tv_machine_toolLibrary_maxToolDiameter.setText("φ60/φ100");
        tv_machine_toolLibrary_maxToolLength.setText("200");
        tv_machine_toolLibrary_maxToolWeight.setText("5");

        //机床说明
        iv_machine_picture.setImageResource(R.drawable.machine_lunan_xh7132a_small);
        tv_machine_introduction.setText("本机床是在X、Y、Z三轴立式加工中心基础上增加了" +
                "A、C两个旋转轴而组成的五轴立式数控加工中心，其中X轴采用全闭环控制,Y、Z" +
                "轴采用半闭环控制.本机床能适应从粗加工到精加工的加工要求，可完成五轴铣削等" +
                "加工要求适用于机械加工及模具制造领域.");
    }
}
