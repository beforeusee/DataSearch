package com.example.a103.datasearch.forcesimulation;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.a103.datasearch.R;
import com.example.a103.datasearch.dao.DaoSession;
import com.example.a103.datasearch.data.Material;
import com.example.a103.datasearch.data.MaterialCategories;
import com.example.a103.datasearch.forcesimulation.algorithm.ForceSimulationHelix;
import com.example.a103.datasearch.forcesimulation.algorithm.SimuBase;
import com.example.a103.datasearch.forcesimulation.basicdata.datacutcoeffient.DataCutCoefficient;
import com.example.a103.datasearch.forcesimulation.basicdata.datacuttingpara.DataCuttingPara;
import com.example.a103.datasearch.forcesimulation.basicdata.datasimumax.DataSimulationMax;
import com.example.a103.datasearch.forcesimulation.basicdata.datasimupara.DataSimulationPara;
import com.example.a103.datasearch.forcesimulation.basicdata.datatool.DataTool;
import com.example.a103.datasearch.forcesimulation.basicdata.datatool.DataToolBallEnd;
import com.example.a103.datasearch.forcesimulation.basicdata.datatool.DataToolHelix;
import com.example.a103.datasearch.forcesimulation.basicdata.datatool.DataToolR;
import com.example.a103.datasearch.utils.DatabaseApplication;
import com.example.a103.datasearch.utils.MPAndroidLineChartManager;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import static com.example.a103.datasearch.utils.Constant.DOWN_MILL;
import static com.example.a103.datasearch.utils.Constant.PI;
import static com.example.a103.datasearch.utils.Constant.TOOL_TYPE_BALL_END;
import static com.example.a103.datasearch.utils.Constant.TOOL_TYPE_HELIX;
import static com.example.a103.datasearch.utils.Constant.TOOL_TYPE_R;
import static com.example.a103.datasearch.utils.Constant.UP_MILL;

public class ForceSimulationActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "ForceSimulationActivity";

    private DaoSession mDaoSession;

    //确定输入参数按钮
    Button btn_para_confirmed;
    //运行仿真按钮
    Button btn_run_simulation;

    //刀具属性
    //刀具类型
    Spinner sp_tool_type;

    //刀具材料
    Spinner sp_tool_material;

    //刀具牌号
    Spinner sp_tool_num;

    //刀具图片
    ImageView iv_tool_type;

    //刀具半径
    EditText et_tool_radius;
    //刀具螺旋角
    EditText et_tool_helix_angle;
    //圆角半径(R刀)
    EditText et_tool_round_radius;
    //刀具刃长
    EditText et_tool_length_flute;
    //刀具悬长
    EditText et_tool_length_gauge;
    //齿数
    EditText et_tool_teeth_num;

    //工件属性
    //材料类型
    Spinner sp_workpiece_material_type;
    //材料牌号
    Spinner sp_workpiece_material_grade;

    //切向切削力系数
    EditText et_material_ktc;
    //径向切削力系数
    EditText et_material_krc;
    //轴向切削力系数
    EditText et_material_kac;
    //切向刃口力系数
    EditText et_material_kte;
    //径向刃口力系数
    EditText et_material_kre;
    //轴向刃口力系数
    EditText et_material_kae;

    //仿真参数
    EditText et_cutting_para_spindle_speed;
    //进给速度
    EditText et_cutting_para_feedRate;
    //径向切宽
    EditText et_cutting_para_cutting_width;
    //轴向切深
    EditText et_cutting_para_cutting_depth;
    //切削方向(顺铣/逆铣)
    RadioGroup mCuttingTypeRadioGroup;
    //顺铣
    RadioButton rb_up_mill;
    //逆铣
    RadioButton rb_down_mill;
    //当前的铣削类型
    String mCurrentCuttingType;
    //铣削类型的图片
    ImageView iv_mill_type;

    //仿真结果
    //仿真数据
    TextView tv_max_fx;
    TextView tv_max_fy;
    TextView tv_max_fz;
    TextView tv_max_fxy;
    TextView tv_max_power;
    TextView tv_max_torque;
    TextView tv_max_tool_bend;

    //仿真图形
    RadioGroup rg_force_result_image;
    RadioButton rb_fx_fy_fz;
    RadioButton rb_ft_fr_fa;
    RadioButton rb_fxy_fxyz;
    RadioButton rb_torque;
    RadioButton rb_power;
    RadioButton rb_bend;

    LineChart mLineChart;
    LineData mLineData;
    LineDataSet mLineDataSet;
    ArrayList<Entry>  mEntries;



    //初始化刀具
    DataTool mTool;
    DataToolHelix mDataToolHelix;
    DataToolBallEnd mDataToolBallEnd;
    DataToolR mDataToolR;
    //刀具类型
    public static final String[] mToolTypes={TOOL_TYPE_HELIX,TOOL_TYPE_R,TOOL_TYPE_BALL_END};
    private ArrayAdapter<String> mToolTypesAdapter;
    private List<String> mToolTypeList;

    //刀具材料类型
    public static final String[] mToolMaterials={"硬质合金","高速钢"};
    private ArrayAdapter<String> mToolMaterialsAdapter;
    private List<String> mToolMaterialList;
    private String mCurrentToolType;
    //刀具属性是否为空
    private boolean mToolNotNull;

    //刀具材料的牌号
    public static final String[] mToolNum={"硬质合金(通用)","高速钢(通用)"};
    private ArrayAdapter<String> mToolNumAdapter;
    private List<String> mToolNumList;

    //材料类型
    List<MaterialCategories> mMaterialCategoriesList;
    private ArrayAdapter<String> mMaterialTypeAdapter;
    private List<String> mMaterialTypeList;

    //材料牌号
    List<Material> mMaterialList;
    private ArrayAdapter<String> mMaterialGradeAdapter;
    private List<String> mMaterialGradeList;

    //初始化切削力系数
    DataCutCoefficient mCoefficient;
    //材料切削力系数是否为空
    private boolean mCutCoefficientNotNull;

    //仿真参数
    DataSimulationPara mSimulationPara;

    //切削参数
    DataCuttingPara mCuttingPara;
    private boolean mCuttingParaNotNull;

    //存储切削力各过程量的变量
    private static List<Float> mFxList =new ArrayList<>();
    private static List<Float> mFyList =new ArrayList<>();
    private static List<Float> mFzList =new ArrayList<>();
    private static List<Float> mFxyList =new ArrayList<>();
    private static List<Float> mFxyzList =new ArrayList<>();
    private static List<Float> mFtList =new ArrayList<>();
    private static List<Float> mFrList =new ArrayList<>();
    private static List<Float> mFaList =new ArrayList<>();
    private static List<Float> mTList =new ArrayList<>();
    private static List<Float> mPList =new ArrayList<>();
    private static List<Float> mToolBendList =new ArrayList<>();

    private static DataSimulationMax mDataSimulationMax =new DataSimulationMax();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_force_simulation);

        initView();

        //顶部工具栏
        Toolbar mToolbar= (Toolbar) findViewById(R.id.toolbar_activity_force_simulation);
        setSupportActionBar(mToolbar);

        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initData();

        //刀具类型选项选中监听函数
        sp_tool_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //加载刀具图片
                String toolType=mToolTypesAdapter.getItem(position);
            if (toolType!=null){

                //圆柱螺旋铣刀
                if (toolType.equals(TOOL_TYPE_HELIX)){
                    iv_tool_type.setImageResource(R.drawable.helixtool);
                    setToolRoundRadiusEnabled(false);
                }
                //R刀
                if (toolType.equals(TOOL_TYPE_R)){
                    iv_tool_type.setImageResource(R.drawable.rtool);
                    setToolRoundRadiusEnabled(true);
                }
                //球头铣刀
                if (toolType.equals(TOOL_TYPE_BALL_END)){
                    iv_tool_type.setImageResource(R.drawable.balltool);
                    setToolRoundRadiusEnabled(false);
                }
            }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                //未选中时，加载圆柱螺旋立铣刀的图片
                iv_tool_type.setImageResource(R.drawable.helixtool);
                setToolRoundRadiusEnabled(false);
            }
        });

        //材料类型选项选中监听函数，选中时加载材料的牌号列表
        sp_workpiece_material_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //选中的材料牌号名称
                //清空mMaterialGradeList
                mMaterialGradeList.clear();
                mMaterialList=mMaterialCategoriesList.get(position).getMaterials();

                for (int i=0;i<mMaterialList.size();i++){

                    mMaterialGradeList.add(mMaterialList.get(i).getName());
                }
                mMaterialGradeAdapter.notifyDataSetChanged();

                //设置默认的材料牌号
                if (mMaterialList.size()>0){
                    Material material=mMaterialList.get(0);
                    setCutCoefficient(material);
                }else {

                    //如果没有材料牌号，则设置为空
                    et_material_ktc.setText("");
                    et_material_krc.setText("");
                    et_material_kac.setText("");
                    et_material_kte.setText("");
                    et_material_kre.setText("");
                    et_material_kae.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //材料牌号选项选中监听函数
        sp_workpiece_material_grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Material material=mMaterialList.get(position);
                setCutCoefficient(material);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //切削方式选项选中监听函数
        mCuttingTypeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId==rb_down_mill.getId()){

                    iv_mill_type.setImageResource(R.drawable.downmilling);
                    mCurrentCuttingType=DOWN_MILL;
                }else {

                    iv_mill_type.setImageResource(R.drawable.upmilling);
                    mCurrentCuttingType=UP_MILL;
                }

            }
        });

        //仿真结果图形选择显示监听
        rg_force_result_image.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                    setForceSimulationChart();
            }
        });

        btn_para_confirmed.setOnClickListener(this);
        btn_run_simulation.setOnClickListener(this);
    }

    /**
     * 设置材料的切削力系数
     * @param material 被选中的材料牌号
     */
    private void setCutCoefficient(Material material) {

        et_material_ktc.setText(material.getCoefficientParameters().getKtc());
        et_material_krc.setText(material.getCoefficientParameters().getKrc());
        et_material_kac.setText(material.getCoefficientParameters().getKac());
        et_material_kte.setText(material.getCoefficientParameters().getKte());
        et_material_kre.setText(material.getCoefficientParameters().getKre());
        et_material_kae.setText(material.getCoefficientParameters().getKae());
    }

    /**
     * 设置圆角半径编辑框是否可用
     * @param enabled 是否可用
     */
    public void setToolRoundRadiusEnabled(boolean enabled){

        et_tool_round_radius.setEnabled(enabled);
    }

    /**
     * 初始化刀具和材料
     */
    private void initData() {

        //设置刀具类型
        mToolTypeList=new ArrayList<>();

        for (int i=0;i<mToolTypes.length;i++){
            mToolTypeList.add(mToolTypes[i]);
        }

        mToolTypesAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,mToolTypeList);
        mToolTypesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp_tool_type.setAdapter(mToolTypesAdapter);

        //测试-设置刀具属性
        et_tool_radius.setText("8");
        et_tool_helix_angle.setText("30");
        et_tool_length_flute.setText("30");
        et_tool_length_gauge.setText("50");
        et_tool_teeth_num.setText("4");

        //设置刀具材料的类型
        mToolMaterialList=new ArrayList<>();
        for (int i=0;i<mToolMaterials.length;i++){
            mToolMaterialList.add(mToolMaterials[i]);
        }
        mToolMaterialsAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,mToolMaterialList);
        mToolMaterialsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tool_material.setAdapter(mToolMaterialsAdapter);

        //设置刀具材料的牌号
        mToolNumList=new ArrayList<>();
        for (String aMToolNum : mToolNum) {
            mToolNumList.add(aMToolNum);
        }
        mToolNumAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,mToolNumList);
        mToolNumAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tool_num.setAdapter(mToolNumAdapter);

        //从数据库读取材料类型,并设置
        mMaterialCategoriesList = DatabaseApplication.getDaoSession().getMaterialCategoriesDao().loadAll();
        mMaterialTypeList=new ArrayList<>();


        if (mMaterialCategoriesList !=null){

            if (mMaterialCategoriesList.size()>0){

                for (int i = 0; i< mMaterialCategoriesList.size(); i++){
                    mMaterialTypeList.add(mMaterialCategoriesList.get(i).getName());
                }

            }
        }
        mMaterialTypeAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,mMaterialTypeList);
        mMaterialTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_workpiece_material_type.setAdapter(mMaterialTypeAdapter);

        //设置材料牌号
        //默认的材料牌号
        if (mMaterialCategoriesList !=null){

            if (mMaterialCategoriesList.size()>0){

                mMaterialList = mMaterialCategoriesList.get(0).getMaterials();

                if (mMaterialList !=null){
                    if (mMaterialList.size()>0){

                        mMaterialGradeList=new ArrayList<>();
                        for (int i = 0; i< mMaterialList.size(); i++){
                            mMaterialGradeList.add(mMaterialList.get(i).getName());
                        }
                        mMaterialGradeAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,mMaterialGradeList);
                        mMaterialGradeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sp_workpiece_material_grade.setAdapter(mMaterialGradeAdapter);
                    }
                }
            }
        }

        //设置切削参数
        et_cutting_para_spindle_speed.setText("2000");
        et_cutting_para_feedRate.setText("500");
        et_cutting_para_cutting_width.setText("8");
        et_cutting_para_cutting_depth.setText("2");

        //设置铣削方式
        if (rb_up_mill.isChecked()){
            iv_mill_type.setImageResource(R.drawable.upmilling);
            mCurrentCuttingType=UP_MILL;
        }else {
            iv_mill_type.setImageResource(R.drawable.downmilling);
            mCurrentCuttingType=DOWN_MILL;
        }

    }

    private void initView() {

        //参数确认，运行仿真，退出按钮
        btn_para_confirmed= (Button) findViewById(R.id.btn_para_confirmed);
        btn_run_simulation= (Button) findViewById(R.id.btn_run_simulation);

        //刀具属性
        sp_tool_type= (Spinner) findViewById(R.id.sp_tool_type);
        sp_tool_material= (Spinner) findViewById(R.id.sp_tool_material);
        sp_tool_num= (Spinner) findViewById(R.id.sp_tool_num);
        iv_tool_type= (ImageView) findViewById(R.id.iv_tool_type);

        et_tool_radius= (EditText) findViewById(R.id.et_tool_radius);
        et_tool_helix_angle= (EditText) findViewById(R.id.et_tool_helix_angle);
        et_tool_round_radius= (EditText) findViewById(R.id.et_tool_round_radius);
        et_tool_length_flute= (EditText) findViewById(R.id.et_tool_length_flute);
        et_tool_length_gauge= (EditText) findViewById(R.id.et_tool_length_gauge);
        et_tool_teeth_num= (EditText) findViewById(R.id.et_tool_teeth_num);

        //工件属性
        sp_workpiece_material_type= (Spinner) findViewById(R.id.sp_workpiece_material_type);
        sp_workpiece_material_grade= (Spinner) findViewById(R.id.sp_workpiece_material_grade);

        et_material_ktc= (EditText) findViewById(R.id.et_material_ktc);
        et_material_krc= (EditText) findViewById(R.id.et_material_krc);
        et_material_kac= (EditText) findViewById(R.id.et_material_kac);
        et_material_kte= (EditText) findViewById(R.id.et_material_kte);
        et_material_kre= (EditText) findViewById(R.id.et_material_kre);
        et_material_kae= (EditText) findViewById(R.id.et_material_kae);

        //仿真参数
        et_cutting_para_spindle_speed= (EditText) findViewById(R.id.et_cutting_para_spindle_speed);
        et_cutting_para_feedRate= (EditText) findViewById(R.id.et_cutting_para_feedRate);
        et_cutting_para_cutting_width= (EditText) findViewById(R.id.et_cutting_para_cutting_width);
        et_cutting_para_cutting_depth= (EditText) findViewById(R.id.et_cutting_para_cutting_depth);

        mCuttingTypeRadioGroup= (RadioGroup) findViewById(R.id.rg_cutting_type);
        rb_down_mill= (RadioButton) findViewById(R.id.rb_down_mill);
        rb_up_mill= (RadioButton) findViewById(R.id.rb_up_mill);
        iv_mill_type= (ImageView) findViewById(R.id.iv_mill_type);

        //仿真结果
        //仿真数据
        tv_max_fx= (TextView) findViewById(R.id.tv_max_fx);
        tv_max_fy= (TextView) findViewById(R.id.tv_max_fy);
        tv_max_fz= (TextView) findViewById(R.id.tv_max_fz);
        tv_max_fxy= (TextView) findViewById(R.id.tv_max_fxy);
        tv_max_power= (TextView) findViewById(R.id.tv_max_power);
        tv_max_torque= (TextView) findViewById(R.id.tv_max_torque);
        tv_max_tool_bend= (TextView) findViewById(R.id.tv_max_tool_bend);

        rg_force_result_image= (RadioGroup) findViewById(R.id.rg_force_result_image);
        rb_fx_fy_fz= (RadioButton) findViewById(R.id.rb_fx_fy_fz);
        rb_ft_fr_fa= (RadioButton) findViewById(R.id.rb_ft_fr_fa);
        rb_fxy_fxyz= (RadioButton) findViewById(R.id.rb_fxy_fxyz);
        rb_torque= (RadioButton) findViewById(R.id.rb_torque);
        rb_power= (RadioButton) findViewById(R.id.rb_power);
        rb_bend= (RadioButton) findViewById(R.id.rb_bend);

        mLineChart= (LineChart) findViewById(R.id.lineChart);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            //确认输入参数
            case R.id.btn_para_confirmed:

                //检查输入是否为空，创建各个对象并赋值
                //检查各参数是否为空并提示
                mCurrentToolType =sp_tool_type.getSelectedItem().toString();
                boolean roundRadius=!et_tool_round_radius.getText().toString().trim().equals("");
                boolean radius=!et_tool_radius.getText().toString().trim().equals("");
                boolean helixAngle=!et_tool_helix_angle.getText().toString().trim().equals("");
                boolean lengthFlute=!et_tool_length_flute.getText().toString().trim().equals("");
                boolean lengthGauge=!et_tool_length_gauge.getText().toString().trim().equals("");
                boolean teethNum=!et_tool_teeth_num.getText().toString().trim().equals("");

                if (mCurrentToolType.equals(TOOL_TYPE_R)){
                    mToolNotNull =roundRadius&&radius&&helixAngle&&lengthFlute&&lengthGauge&&teethNum;
                }

                mToolNotNull =radius&&helixAngle&&lengthFlute&&lengthGauge&&teethNum;

                if (!mToolNotNull){
                    new AlertDialog.Builder(this).setTitle("警告").setMessage("刀具属性信息不完整")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
                    return;
                }
                createTool();

                //检查切削力系数的输入是否为空
                boolean ktc=!et_material_ktc.getText().toString().trim().equals("");
                boolean krc=!et_material_krc.getText().toString().trim().equals("");
                boolean kac=!et_material_kae.getText().toString().trim().equals("");
                boolean kte=!et_material_kte.getText().toString().trim().equals("");
                boolean kre=!et_material_kre.getText().toString().trim().equals("");
                boolean kae=!et_material_kae.getText().toString().trim().equals("");

                mCutCoefficientNotNull=ktc&&krc&&kac&&kte&&kre&&kae;
                if (!mCutCoefficientNotNull){
                    //如果为空弹出警告对话框
                    new AlertDialog.Builder(this).setTitle("警告").setMessage("材料切削力系数不完整")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
                    return;
                }
                createCutCoefficient();

                boolean spindle=!et_cutting_para_spindle_speed.getText().toString().trim().equals("");
                boolean feed=!et_cutting_para_feedRate.getText().toString().trim().equals("");
                boolean depth=!et_cutting_para_cutting_depth.getText().toString().trim().equals("");
                boolean width=!et_cutting_para_cutting_width.getText().toString().trim().equals("");
                mCuttingParaNotNull=spindle&&feed&&depth&&width;
                if (!mCuttingParaNotNull){

                    //如果为空弹出警告对话框
                    new AlertDialog.Builder(this).setTitle("警告").setMessage("切削参数不完整")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
                    return;
                }
                createCuttingPara();

                createSimulationPara();

                break;

            case R.id.btn_run_simulation:

                runForceSimulation();

                setForceSimulationMax();

                setForceSimulationChart();

                break;
        }
    }

    private void setForceSimulationChart() {

        List<Float> x=new ArrayList<>();
        LineData lineData;
        int count=(int)(2*PI*mSimulationPara.getPeriods()/mSimulationPara.getdPhi());
        for (int i=0;i<count;i++){
            //用角度表示
            x.add(1.0f*i);
        }
        MPAndroidLineChartManager.setLineName("功率名称");

        switch (rg_force_result_image.getCheckedRadioButtonId()){

            case R.id.rb_fx_fy_fz:
                // 初始化数据
                lineData=MPAndroidLineChartManager.initTripleLineChart(this,mLineChart,x,mFxList,mFyList,mFzList,"Fx","Fy","Fz");
                break;
            case R.id.rb_ft_fr_fa:
                lineData=MPAndroidLineChartManager.initTripleLineChart(this,mLineChart,x,mFtList,mFrList,mFaList,"Ft","Fr","Fa");
                break;
            case R.id.rb_fxy_fxyz:
                lineData=MPAndroidLineChartManager.initDoubleLineChart(this,mLineChart,x,mFxyList,mFxyzList,"Fxy","Fxyz");
                break;
            case R.id.rb_torque:
                lineData=MPAndroidLineChartManager.initSingleLineChart(this,mLineChart,x,mTList,"扭矩");
                break;
            case R.id.rb_power:
                lineData=MPAndroidLineChartManager.initSingleLineChart(this,mLineChart,x,mPList,"功率");
                break;
            case R.id.rb_bend:
                lineData=MPAndroidLineChartManager.initSingleLineChart(this,mLineChart,x,mToolBendList,"夹持面弯矩");
                break;
            default:
                lineData=MPAndroidLineChartManager.initTripleLineChart(this,mLineChart,x,mFxList,mFyList,mFzList,"Fx","Fy","Fz");
                break;
        }
        MPAndroidLineChartManager.initChartStyle(this,mLineChart,lineData);
    }

    /**
     * 设置并显示切削力最大值
     */
    private void setForceSimulationMax() {
        //显示最大值
        tv_max_fx.setText(String.valueOf(mDataSimulationMax.getFx()));
        tv_max_fy.setText(String.valueOf(mDataSimulationMax.getFy()));
        tv_max_fz.setText(String.valueOf(mDataSimulationMax.getFz()));
        tv_max_fxy.setText(String.valueOf(mDataSimulationMax.getFxy()));
        tv_max_power.setText(String.valueOf(mDataSimulationMax.getPower()));
        tv_max_torque.setText(String.valueOf(mDataSimulationMax.getTorque()));
        tv_max_tool_bend.setText(String.valueOf(mDataSimulationMax.getBend()));
    }

    /**
     * 运行切削力仿真函数
     */
    private void runForceSimulation() {
        //运行仿真功能
        ForceSimulationHelix.forceSimulationHelix(mDataToolHelix,mCuttingPara,mSimulationPara,mCoefficient);
        //存储结果
        mDataSimulationMax=ForceSimulationHelix.getDataSimulationMax();

        for (int i=0;i<ForceSimulationHelix.getFxList().size();i++) {

            mFxList.add(ForceSimulationHelix.getFxList().get(i).floatValue());
            mFyList.add(ForceSimulationHelix.getFyList().get(i).floatValue());
            mFzList.add(ForceSimulationHelix.getFzList().get(i).floatValue());
            mFxyList.add(ForceSimulationHelix.getFxyList().get(i).floatValue());
            mFxyzList.add(ForceSimulationHelix.getFxyzList().get(i).floatValue());
            mFtList.add(ForceSimulationHelix.getFtList().get(i).floatValue());
            mFrList.add(ForceSimulationHelix.getFrList().get(i).floatValue());
            mFaList.add(ForceSimulationHelix.getFaList().get(i).floatValue());
            mTList.add(ForceSimulationHelix.getTList().get(i).floatValue());
            mPList.add(ForceSimulationHelix.getPList().get(i).floatValue());
            mToolBendList.add(ForceSimulationHelix.getToolBendList().get(i).floatValue());
        }
    }


    private void createSimulationPara() {

        mSimulationPara=new DataSimulationPara();

        mSimulationPara.setDz(0.01);
        mSimulationPara.setdPhi(2*PI/360);
        mSimulationPara.setPeriods(2);

        Double ae= Double.valueOf(et_cutting_para_cutting_width.getText().toString().trim());
        Double r= Double.valueOf(et_tool_radius.getText().toString().trim());

        Double phiIn= SimuBase.getPhiIn(ae,r,mCurrentCuttingType);
        Double phiOut=SimuBase.getPhiOut(ae,r,mCurrentCuttingType);

        mSimulationPara.setPhiIn(phiIn);
        mSimulationPara.setPhiOut(phiOut);
    }

    private void createCuttingPara() {

        mCuttingPara=new DataCuttingPara();

        Double spindleSpeed= Double.valueOf(et_cutting_para_spindle_speed.getText().toString().trim());
        Double feedRate= Double.valueOf(et_cutting_para_feedRate.getText().toString().trim());
        Double cuttingDepth= Double.valueOf(et_cutting_para_cutting_depth.getText().toString().trim());
        Double cuttingWidth= Double.valueOf(et_cutting_para_cutting_width.getText().toString().trim());

        mCuttingPara.setCuttingType(mCurrentCuttingType);
        mCuttingPara.setSpindleSpeed(spindleSpeed);
        mCuttingPara.setFeedRate(feedRate);
        mCuttingPara.setCuttingDepth(cuttingDepth);
        mCuttingPara.setCuttingWidth(cuttingWidth);

    }

    private void createCutCoefficient() {

        mCoefficient=new DataCutCoefficient();

        mCoefficient.setMaterialType(sp_workpiece_material_type.getSelectedItem().toString().trim());
        mCoefficient.setMaterial(sp_workpiece_material_grade.getSelectedItem().toString().trim());
        mCoefficient.setKac(Double.valueOf(et_material_kac.getText().toString().trim()));
        mCoefficient.setKrc(Double.valueOf(et_material_krc.getText().toString().trim()));
        mCoefficient.setKtc(Double.valueOf(et_material_ktc.getText().toString().trim()));
        mCoefficient.setKte(Double.valueOf(et_material_kte.getText().toString().trim()));
        mCoefficient.setKre(Double.valueOf(et_material_kre.getText().toString().trim()));
        mCoefficient.setKae(Double.valueOf(et_material_kae.getText().toString().trim()));
    }

    private void createTool() {

        mTool=new DataTool();

        //创建刀具的基本参数
        mTool.setToolType(mCurrentToolType);
        mTool.setToolMaterial(sp_tool_material.getSelectedItem().toString());
        mTool.setToolNum(sp_tool_num.getSelectedItem().toString());
        mTool.setRadius(Double.valueOf(et_tool_radius.getText().toString().trim()));
        mTool.setHelix(Double.valueOf(et_tool_helix_angle.getText().toString().trim()));
        mTool.setLenFlute(Double.valueOf(et_tool_length_flute.getText().toString().trim()));
        mTool.setLenGauge(Double.valueOf(et_tool_length_gauge.getText().toString().trim()));
        mTool.setToolTeethNum(Double.valueOf(et_tool_teeth_num.getText().toString().trim()));

        //注意:此处一定要将角度转化为弧度，否则会出错
        Double lagFactor=Math.tan(mTool.getHelix()*PI/180)/mTool.getRadius();
        mTool.setLagFactor(lagFactor);

        //根据选择的刀具类型来创建具体的刀具对象
        if (mCurrentToolType.equals(TOOL_TYPE_HELIX)){

            mDataToolHelix= new DataToolHelix();
            mDataToolHelix.setToolType(mTool.getToolType());
            mDataToolHelix.setToolMaterial(mTool.getToolMaterial());
            mDataToolHelix.setToolNum(mTool.getToolNum());
            mDataToolHelix.setRadius(mTool.getRadius());
            mDataToolHelix.setHelix(mTool.getHelix());
            mDataToolHelix.setLenFlute(mTool.getLenFlute());
            mDataToolHelix.setLenGauge(mTool.getLenGauge());
            mDataToolHelix.setToolTeethNum(mTool.getToolTeethNum());
            mDataToolHelix.setLagFactor(mTool.getLagFactor());
        }

        if (mCurrentToolType.equals(TOOL_TYPE_R)){
            mDataToolR= new DataToolR();

            mDataToolR.setToolType(mTool.getToolType());
            mDataToolR.setToolMaterial(mTool.getToolMaterial());
            mDataToolR.setToolNum(mTool.getToolNum());
            mDataToolR.setRadius(mTool.getRadius());
            mDataToolR.setHelix(mTool.getHelix());
            mDataToolR.setLenFlute(mTool.getLenFlute());
            mDataToolR.setLenGauge(mTool.getLenGauge());
            mDataToolR.setToolNum(mTool.getToolNum());
            mDataToolR.setLagFactor(mTool.getLagFactor());
            mDataToolR.setRoundRadius(Double.valueOf(et_tool_round_radius.getText().toString().trim()));
        }

        if (mCurrentToolType.equals(TOOL_TYPE_BALL_END)){

            mDataToolBallEnd= new DataToolBallEnd();
            mDataToolBallEnd.setToolType(mTool.getToolType());
            mDataToolBallEnd.setToolMaterial(mTool.getToolMaterial());
            mDataToolBallEnd.setToolNum(mTool.getToolNum());
            mDataToolBallEnd.setRadius(mTool.getRadius());
            mDataToolBallEnd.setHelix(mTool.getHelix());
            mDataToolBallEnd.setLenFlute(mTool.getLenFlute());
            mDataToolBallEnd.setLenGauge(mTool.getLenGauge());
            mDataToolBallEnd.setToolNum(mTool.getToolNum());
            mDataToolBallEnd.setLagFactor(mTool.getLagFactor());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * provide a method to start {@link ForceSimulationActivity}
     * @param context context
     */
    public static void actionStart(Context context){

        Intent intent=new Intent(context,ForceSimulationActivity.class);
        context.startActivity(intent);
    }
}
