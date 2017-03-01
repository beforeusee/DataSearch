package com.example.a103.datasearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

public class ToolDetailActivity extends AppCompatActivity {

    //刀具信息控件声明
    ImageView iv_tool_picture;               //刀具图片
    CheckBox cb_tool_used;                   //是否使用该刀具

    EditText et_tool_name;                   //刀具名称
    EditText et_tool_type;                   //刀具类型
    EditText et_tool_serial;                 //刀具型号
    EditText et_tool_brand;                  //刀具制造厂商
    EditText et_tool_cuttingDiameter;       //切削直径
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
    EditText et_tool_radialRakeAngle;         //径向前角
    EditText et_tool_axialRakeAngle;          //轴向前角
    EditText et_tool_maximumRegrinds;         //最大重新研磨次数
    EditText et_tool_maxRotationalSpeed;      //最大转速
    EditText et_tool_weight;                    //部件重量
    EditText et_tool_lifeCycleState;           //寿命周期状态
    EditText et_tool_suitableForMaterial;      //适合加工材料
    EditText et_tool_application;               //适用场合
    EditText et_tool_todo;                       //待定项

    //取消，编辑，提交控件声明
    Button btn_tool_cancel;      //取消按钮
    Button btn_tool_edit;        //编辑按钮
    Button btn_tool_commit;      //提交按钮



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_detail);

        initToolDetailView();
    }

    /**
     * 初始化刀具的详细页面
     */
    private void initToolDetailView() {
        iv_tool_picture= (ImageView) findViewById(R.id.iv_tool_picture);
        cb_tool_used= (CheckBox) findViewById(R.id.cb_tool_used);
        et_tool_name= (EditText) findViewById(R.id.et_tool_name);
        et_tool_type= (EditText) findViewById(R.id.et_tool_type);
        et_tool_serial= (EditText) findViewById(R.id.et_tool_serial);
        et_tool_brand= (EditText) findViewById(R.id.et_tool_brand);
        et_tool_cuttingDiameter= (EditText) findViewById(R.id.et_tool_cuttingDiameter);
        et_tool_depthOfCutMaximum= (EditText) findViewById(R.id.et_tool_depthOfCutMaximum);
        et_tool_maxRampingAngle= (EditText) findViewById(R.id.et_tool_maxRampingAngle);
        et_tool_usableLength= (EditText) findViewById(R.id.et_tool_usableLength);
        et_tool_peripheralEffectiveCuttingEdgeCount=
                (EditText) findViewById(R.id.et_tool_peripheralEffectiveCuttingEdgeCount);
        et_tool_adaptiveInterfaceMachineDirection=
                (EditText) findViewById(R.id.et_tool_adaptiveInterfaceMachineDirection);
        et_tool_connectionDiameterTolerance=
                (EditText) findViewById(R.id.et_tool_connectionDiameterTolerance);
        et_tool_grade= (EditText) findViewById(R.id.et_tool_grade);
        et_tool_substrate= (EditText) findViewById(R.id.et_tool_substrate);
        et_tool_coating= (EditText) findViewById(R.id.et_tool_coating);
        et_tool_basicStandardGroup= (EditText) findViewById(R.id.et_tool_basicStandardGroup);
        et_tool_coolantEntryStyleCode= (EditText) findViewById(R.id.et_tool_coolantEntryStyleCode);
        et_tool_connectionDiameter= (EditText) findViewById(R.id.et_tool_connectionDiameter);
        et_tool_functionalLength= (EditText) findViewById(R.id.et_tool_functionalLength);
        et_tool_fluteHelixAngle= (EditText) findViewById(R.id.et_tool_fluteHelixAngle);
        et_tool_radialRakeAngle= (EditText) findViewById(R.id.et_tool_radialRakeAngle);
        et_tool_axialRakeAngle= (EditText) findViewById(R.id.et_tool_axialRakeAngle);
        et_tool_maximumRegrinds= (EditText) findViewById(R.id.et_tool_maximumRegrinds);
        et_tool_maxRotationalSpeed= (EditText) findViewById(R.id.et_tool_maxRotationalSpeed);
        et_tool_weight= (EditText) findViewById(R.id.et_tool_weight);
        et_tool_lifeCycleState= (EditText) findViewById(R.id.et_tool_lifeCycleState);
        et_tool_suitableForMaterial= (EditText) findViewById(R.id.et_tool_suitableForMaterial);
        et_tool_application= (EditText) findViewById(R.id.et_tool_application);
        et_tool_todo= (EditText) findViewById(R.id.et_tool_todo);

        //取消，编辑，提交控件
        btn_tool_cancel= (Button) findViewById(R.id.btn_tool_cancel);
        btn_tool_edit= (Button) findViewById(R.id.btn_tool_edit);
        btn_tool_commit= (Button) findViewById(R.id.btn_tool_commit);
    }
}
