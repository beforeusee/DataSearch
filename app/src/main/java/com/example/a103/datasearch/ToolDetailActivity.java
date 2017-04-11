package com.example.a103.datasearch;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.a103.datasearch.dao.DaoSession;
import com.example.a103.datasearch.data.Tool;
import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.utils.DatabaseApplication;

public class ToolDetailActivity extends AppCompatActivity {

    //刀具信息控件声明
    ImageView iv_tool_picture;               //刀具图片
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

    //取消，编辑，提交控件声明
    Button btn_tool_cancel;      //取消按钮
    Button btn_tool_edit;        //编辑按钮
    Button btn_tool_commit;      //提交按钮

    private boolean isEditOperation; //是否是编辑状态
    private static final String TAG = "ToolDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_detail);

        initialToolDetailView();

        Intent intent=getIntent();
        isEditOperation=false;
        isEditOperation=intent.getBooleanExtra("tool_isEditOperation",true);
        final Tool tool=intent.getParcelableExtra("Tool");

        if (isEditOperation){
            setToolDetailFromTool(tool);  //如果刀具处于查看可编辑状态，根据对象tool，初始化刀具设置页面的值
        }

        //设置edit键的监听事件
        btn_tool_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置各控件的editText为可编辑模式
                setToolEditable(true);
                Toast.makeText(ToolDetailActivity.this,"点击了编辑，可以对刀具进行编辑",
                        Toast.LENGTH_SHORT).show();
            }
        });

        //设置commit键的监听事件
        btn_tool_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setToolEditable(false);  //设置刀具界面不可编辑

                SQLiteDatabase db= DatabaseApplication.getDb();             //获取整个application的数据库对象db
                DaoSession daoSession=DatabaseApplication.getDaoSession(); //获取整个application的dao对象管理者

                if (isEditOperation){
                    Tool toolEdited=new Tool();
                    toolEdited.setId(tool.getId());
                    setToolFromToolDetail(toolEdited);
                    daoSession.getToolDao().update(toolEdited);  //编辑状态，更新刀具
                }else {
                    Tool newTool=new Tool();
                    setToolFromToolDetail(newTool);
                    daoSession.getToolDao().save(newTool);       //新建刀具
                }

                //发送广播，通知ToolFragment进行刀具列表的刷新
                sendToolRefreshBroadcast();

                Toast.makeText(ToolDetailActivity.this,"点击了提交，更新刀具列表",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        //设置cancel取消键的监听事件
        btn_tool_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Toast.makeText(ToolDetailActivity.this,"点击了取消，退出刀具详细信息页面",
                        Toast.LENGTH_SHORT).show();
            }
        });
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
     * @param context
     */
    public static void actionStart(Context context,final Tool tool){
        Intent intent=new Intent(context,ToolDetailActivity.class);

        intent.putExtra("Tool",tool);                    //刀具对象
        final boolean isEditOperation=(tool!=null);     //刀具是否已经存在处于编辑状态,传入tool不为null表示已经存在
        intent.putExtra("tool_isEditOperation",isEditOperation);
        context.startActivity(intent);
        Log.d(TAG, "actionStart: "+"启动ToolDetailActivity.");
    }

    /**
     * 根据页面值设置刀具
     * @param tool
     */
    private void setToolFromToolDetail(Tool tool) {
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
        tool.setCenterCuttingCapability(et_tool_centerCuttingCapability.getText().toString());
        tool.setMaximumRegrinds(et_tool_maximumRegrinds.getText().toString());
        tool.setMaxRotationalSpeed(et_tool_maxRotationalSpeed.getText().toString());
        tool.setWeight(et_tool_weight.getText().toString());
        tool.setLifeCycleState(et_tool_lifeCycleState.getText().toString());
        tool.setSuitableForMaterial(et_tool_suitableForMaterial.getText().toString());
        tool.setApplication(et_tool_application.getText().toString());
        tool.setUsed(cb_tool_used.isChecked()?1:0);
    }

    /**
     * 功能：获取tool对象的属性值并设置到ToolDetailActivity的页面可编辑框中
     * @param tool
     */
    private void setToolDetailFromTool(Tool tool) {
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
        et_tool_todo.setText("待定项");
        cb_tool_used.setChecked(tool.getUsed()==1);
    }

    /**
     * setToolEditable(boolean enabled)设置刀具是否可编辑
     * @param enabled
     */
    private void setToolEditable(boolean enabled) {

        //设置刀具信息各editText是否可编辑,enabled 为true时可编辑，enabled 为false时不可编辑
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

    /**
     * 初始化刀具的控件
     */
    private void initialToolDetailView() {
        cb_tool_used= (CheckBox) findViewById(R.id.cb_tool_used);
        et_tool_name= (EditText) findViewById(R.id.et_tool_name);
        et_tool_type= (EditText) findViewById(R.id.et_tool_type);
        et_tool_serial= (EditText) findViewById(R.id.et_tool_serial);
        et_tool_brand= (EditText) findViewById(R.id.et_tool_brand);
        et_tool_cuttingDiameter= (EditText) findViewById(R.id.et_tool_cuttingDiameter);
        et_tool_cuttingDiameterTOLUpper= (EditText) findViewById(R.id.et_tool_cuttingDiameterTOLUpper);
        et_tool_cuttingDiameterTOLLower= (EditText) findViewById(R.id.et_tool_cuttingDiameterTOLLower);
        et_tool_filletRadius= (EditText) findViewById(R.id.et_tool_filletRadius);
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
        et_tool_axialRakeAngle= (EditText) findViewById(R.id.et_tool_axialRakeAngle);
        et_tool_radialRakeAngle= (EditText) findViewById(R.id.et_tool_radialRakeAngle);
        et_tool_axialRearAngle= (EditText) findViewById(R.id.et_tool_axialRearAngle);
        et_tool_radialRearAngle= (EditText) findViewById(R.id.et_tool_radialRearAngle);
        et_tool_cuttingEdgeAngle= (EditText) findViewById(R.id.et_tool_cuttingEdgeAngle);
        et_tool_faceContactDiameter= (EditText) findViewById(R.id.et_tool_faceContactDiameter);
        et_tool_tipChamfer= (EditText) findViewById(R.id.et_tool_tipChamfer);
        et_tool_chamferWidth= (EditText) findViewById(R.id.et_tool_chamferWidth);
        et_tool_centerCuttingCapability= (EditText) findViewById(R.id.et_tool_centerCuttingCapability);
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
