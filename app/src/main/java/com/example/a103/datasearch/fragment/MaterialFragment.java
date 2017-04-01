package com.example.a103.datasearch.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.a103.datasearch.MaterialSimpleCursorTreeAdapter;
import com.example.a103.datasearch.dao.DaoSession;
import com.example.a103.datasearch.dao.MaterialCategoriesDao;
import com.example.a103.datasearch.data.Material;
import com.example.a103.datasearch.data.MaterialCategories;
import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.ExpandableListViewAdapter;
import com.example.a103.datasearch.R;
import com.example.a103.datasearch.utils.DatabaseApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by A103 on 2017/2/10.
 * 材料页面的fragment
 */

public class MaterialFragment extends Fragment {
    //声明与二级目录了相关的成员变量
    ExpandableListView mExpandableListView;             //材料种类分类二级目录
    private ExpandableListViewAdapter mAdapter;                   //二级目录适配器
    private Context mContext;
    private static final String TAG = "MaterialFragment";
    private SQLiteDatabase db;
    private DaoSession daoSession;
    private View groupView;
    MaterialCategoriesFragment materialCategoriesFragment;
    MaterialDetailFragment materialDetailFragment;

    //材料属性控件声明
    EditText et_material_properties_name;                                 //材料名称
    EditText et_material_properties_categories;                          //材料类别
    EditText et_material_properties_ingredient;                          //成分
    EditText et_material_properties_hardness;                            //硬度
    EditText et_material_properties_density;                             //密度
    EditText et_material_properties_thermalConductivity;               //导热系数
    EditText et_material_properties_specificHeatCapacity;             //比热容
    EditText et_material_properties_youngsModulus;                     //杨氏模量
    EditText et_material_properties_impactStrength;                    //冲击强度
    EditText et_material_properties_extension;                         //延伸量
    EditText et_material_properties_areaReduction;                    //面积减少量
    EditText et_material_properties_conductiveCoefficient;           //导电系数
    EditText et_material_properties_condition;                        //条件
    EditText et_material_properties_tensileStrength;                 //拉伸强度
    EditText et_material_properties_yieldStrength;                   //屈服强度
    EditText et_material_properties_shearStrength;                   //剪切强度
    EditText et_material_properties_heatTreatment;                   //热处理
    EditText et_material_properties_lowMeltingPoint;                //熔点(低)
    EditText et_material_properties_highMeltingPoint;               //熔点(高)
    EditText et_material_properties_thermalExpansionCoefficient;  //热膨胀系数

    //材料切削力系数参数控件声明
    Spinner sp_material_coefficientParameters_forceModel;     //力模型
    EditText et_material_coefficientParameters_Kte;            //切向刃口力系数
    EditText et_material_coefficientParameters_Kre;            //径向刃口力系数
    EditText et_material_coefficientParameters_Kae;            //轴向刃口力系数
    EditText et_material_coefficientParameters_Ktc;            //切向铣削力系数
    EditText et_material_coefficientParameters_Krc;            //径向铣削力系数
    EditText et_material_coefficientParameters_Kac;            //轴向铣削力系数

    //材料限制条件控件声明
    EditText et_material_limits_minChipThickness;               //切屑厚度(最小)
    EditText et_material_limits_maxChipThickness;               //切屑厚度(最大)
    EditText et_material_limits_minCuttingSpeed;                //切削速度(最小)
    EditText et_material_limits_maxCuttingSpeed;                //切削速度(最大)
    EditText et_material_limits_minRakeAngle;                    //前角(最小)
    EditText et_material_limits_maxRakeAngle;                    //前角(最大)

    //材料标准控件声明
    CheckBox cb_material_standards_AFNOR;
    CheckBox cb_material_standards_AISI;                          //材料标准-美国钢铁学会标准
    CheckBox cb_material_standards_BS;
    CheckBox cb_material_standards_CMC;
    CheckBox cb_material_standards_DIN_nr;
    CheckBox cb_material_standards_EN;
    CheckBox cb_material_standards_JIS;
    CheckBox cb_material_standards_SAE;
    CheckBox cb_material_standards_SS;
    CheckBox cb_material_standards_UNF;
    CheckBox cb_material_standards_UNI;
    CheckBox cb_material_standards_W_nr;

    //新建类，删除类，新建，删除，应用，取消等按钮控件声明
    Button btn_material_add_userCategories;     //新建用户材料分类
    Button btn_material_delete_userCategories;  //删除用户材料分类
    Button btn_material_add_userMaterial;       //新建用户材料
    Button btn_material_delete_userMaterial;    //删除用户材料
    Button btn_material_commit_userMaterial;    //提交用户材料
    Button btn_material_cancel_userMaterial;    //取消用户材料


    //TODO 数据集，后期数据接入数据库的数据
    private Map<String,List<String>> data=new HashMap<>();
    List<String> groupList=new ArrayList<>();
    private List<String> childrenList1=new ArrayList<>();
    private List<String> childrenList2=new ArrayList<>();
    private List<String> childrenList3=new ArrayList<>();

    public static final String[] parentList=new String[]{"Aluminum","MAL Materials","Copper",
            "Copper[high-Alloy]", "Iron[Chilled Cast]","Steel[Casting]","Steel[High Alloy]","Titanium",
            "Wood"};


    public static MaterialFragment newInstance(String s){
        MaterialFragment materialFragment=new MaterialFragment();
       /* Bundle bundle=new Bundle();
        bundle.putString(Constant.ARGS,s);
        materialFragment.setArguments(bundle);*/
        return materialFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        groupView=inflater.inflate(R.layout.fragment_material,container,false);
        initChildFragment();
        return groupView;

/*        Bundle bundle = getArguments();
        String s = bundle.getString(Constant.ARGS);*/
        /*mContext=getContext();
        initialMaterialDetailView(view);
        db= DatabaseApplication.getDb();
        daoSession=DatabaseApplication.getDaoSession();

        initialSysData();
        final Cursor groupCursor=db.query(MaterialCategoriesDao.TABLENAME,null,null,null,null,null,null);
        final String groupFrom[]=new String[]{"NAME"};
        final String childFrom[]=new String[]{"NAME"};
        final int[] groupTo=new int[]{R.id.tv_material_parent_title};
        final int[] childTo=new int[]{R.id.tv_material_child_title};
        final MaterialSimpleCursorTreeAdapter mAdapter=new MaterialSimpleCursorTreeAdapter(mContext,
                groupCursor,
                R.layout.material_expandablelistview_group_item,
                groupFrom,
                groupTo,
                R.layout.material_expandablelistview_child_item,
                childFrom,
                childTo);
        mExpandableListView.setAdapter(mAdapter);

        //材料分类按钮注册了一个回调函数，将在此处理数据创建的逻辑

        try {
            finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

*/
        /*btn_material_add_userCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.add_material_categories_dialog);

                final EditText editMaterialCategories= (EditText) dialog.findViewById(R.id.et_add_material_categories);
                Button categoriesCommitButton= (Button) dialog.findViewById(R.id.add_material_categories__button_commit);
                Button categoriesCancelButton= (Button) dialog.findViewById(R.id.add_material_categories_button_cancel);


                //给提交按钮注册了一个回调函数，回调函数实现了OnClickListener接口中的onClick()方法
                categoriesCommitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //如果输入不为空，则创建该分类，否则弹出提示对话框
                        if (!("".equals(editMaterialCategories.getText().toString().trim()))){
                            //创建MaterialCategories对象
                            MaterialCategories categories=new MaterialCategories();
                            categories.setName(editMaterialCategories.getText().toString());

                            //TODO 检查数据库中是否存在该项，如果存在则不写入并抛出异常提示，如果不存在则写入并更新UI

                            //存入数据库
                            daoSession.getMaterialCategoriesDao().save(categories);

                            //TODO UI更新逻辑不对
//                            final Cursor groupCursor=db.query("MATERIAL",null,null,null,null,null,null);
//                            mAdapter.changeCursor(groupCursor);
//                            mExpandableListView.setAdapter(mAdapter);

//                            //更新UI
//                            if (groupList.size()>0){
//                                groupList.clear();
//                                List<MaterialCategories> categoriesList=daoSession.getMaterialCategoriesDao().loadAll();
//                                for (int i=0;i<categoriesList.size();i++){
//                                    groupList.add(categoriesList.get(i).getName());
//                                }
//                                data.put(groupList.get(categoriesList.size()-1),null);
//                                //test
//                                for (int i=0;i<data.size();i++){
//                                    Log.d(TAG, "onClick: 材料分类列表:"+data.get(categories.getName()));
//                                }
//
//                                //mAdapter.notifyDataSetChanged();
//                            }

                            //销毁对话框
                            dialog.dismiss();
                        }else {
                            //输入为空的提示，更好的做法是创建自定义异常类，在此处抛出自定义异常类
                            Toast.makeText(getContext(),"输入不能为空!",Toast.LENGTH_SHORT).show();
                        }
                        Log.d(TAG, "onClick: 数据分类表的数据项总共有："+daoSession.getMaterialCategoriesDao().count());
                    }
                });

                //给取消按钮注册一个回调函数
                categoriesCancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });*/

//        //添加材料
//        btn_material_add_userMaterial.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Material material=new Material();
//                material=initialMaterialFromDetail(material);
//                daoSession.getMaterialDao().save(material);
//                //TODO 更新UI
//
//            }
//        });


//        mAdapter=new ExpandableListViewAdapter(mContext,data,groupList);

//        for (String group:groupList){
//            Log.d(TAG, "onCreateView: "+"groupListName= "+group+", childListName"+data.get(group));
//        }
    }

    private void initChildFragment() {
        Log.d(TAG, "initChildFragment: ");

        //注意：1)Fragment的嵌套使用中，操作子Fragment时一定要用getChildFragmentManager()来操作；
        //      2)而且还要注意父Fragment销毁后，子Fragment不会自动销毁，要在父Fragment中的DestroyView方法中添加
        //        销毁子Fragment的方法，而且要用commitAllowingStateLoss()
        materialCategoriesFragment= (MaterialCategoriesFragment) getChildFragmentManager().
                findFragmentById(R.id.material_categories_fragment);
        materialDetailFragment= (MaterialDetailFragment) getChildFragmentManager().
                findFragmentById(R.id.material_detail_fragment);
        if (materialCategoriesFragment!=null){
            Log.d(TAG, "initChildFragment: init materialCategoriesFragment success and no null");
        }
        if (materialDetailFragment!=null){
            Log.d(TAG, "initChildFragment: init materialDetailFragment success and no null");
        }
        /*materialCategoriesFragment=new MaterialCategoriesFragment();
        FragmentTransaction transaction=getChildFragmentManager().beginTransaction();
        transaction.add(R.id.empty_material_fragment_container,materialCategoriesFragment);
        transaction.commit();*/
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (materialCategoriesFragment!=null){
            Log.d(TAG, "onDestroyView: materialCategoriesFragment no null");
            FragmentManager fragmentManager=getChildFragmentManager();
            if (fragmentManager!=null&&!fragmentManager.isDestroyed()){
                final FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                if (fragmentTransaction!=null){
                    fragmentTransaction.remove(materialCategoriesFragment).commitAllowingStateLoss();
//                    fragmentTransaction.commitAllowingStateLoss();
//                    fragmentManager.executePendingTransactions();
                    Log.d(TAG, "onDestroyView: materialCategoriesFragment destroy");
                }
            }
        }

        if (materialDetailFragment!=null){
            Log.d(TAG, "onDestroyView: materialDetailFragment no null");
            FragmentManager fragmentManager=getChildFragmentManager();
            if (fragmentManager!=null&&!fragmentManager.isDestroyed()){
                final FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                if (fragmentTransaction!=null){
                    fragmentTransaction.remove(materialDetailFragment).commitAllowingStateLoss();
//                    fragmentTransaction.commitAllowingStateLoss();
//                    fragmentManager.executePendingTransactions();
                    Log.d(TAG, "onDestroyView: materialDetailFragment destroy");
                }
            }
        }
        /*MaterialCategoriesFragment fragment= (MaterialCategoriesFragment) getChildFragmentManager().
                findFragmentById(R.id.material_categories_fragment);
        if (fragment!=null){
            getFragmentManager().beginTransaction().remove(fragment).commit();
        }*/
    }

    private Material initialMaterialFromDetail(Material material){

        //material.setMaterialCategoriesId(et_material_properties_categories.getText().toString());
        material.setName(et_material_properties_name.getText().toString());
        material.setIngredient(et_material_properties_ingredient.getText().toString());
        material.setHardness(et_material_properties_hardness.getText().toString());
        material.setDensity(et_material_properties_density.getText().toString());
        material.setThermalConductivity(et_material_properties_thermalConductivity.getText().toString());
        material.setSpecificHeatCapacity(et_material_properties_specificHeatCapacity.getText().toString());
        material.setYoungsModulus(et_material_properties_youngsModulus.getText().toString());
        material.setImpactStrength(et_material_properties_impactStrength.getText().toString());
        material.setExtension(et_material_properties_extension.getText().toString());
        material.setAreaReduction(et_material_properties_areaReduction.getText().toString());
        material.setConductiveCoefficient(et_material_properties_conductiveCoefficient.getText().toString());
        material.setCondition(et_material_properties_condition.getText().toString());
        material.setTensileStrength(et_material_properties_tensileStrength.getText().toString());
        material.setYieldStrength(et_material_properties_yieldStrength.getText().toString());
        material.setShearStrength(et_material_properties_shearStrength.getText().toString());
        material.setHeatTreatment(et_material_properties_heatTreatment.getText().toString());
        material.setLowMeltingPoint(et_material_properties_lowMeltingPoint.getText().toString());
        material.setHighMeltingPoint(et_material_properties_highMeltingPoint.getText().toString());
        material.setThermalExpansionCoefficient(et_material_properties_thermalExpansionCoefficient.getText().toString());
        material.setStandard(cb_material_standards_AISI.getText().toString());
        return material;
    }
    private void initialMaterialDetailView(View view) {

        mExpandableListView= (ExpandableListView)view.findViewById(R.id.elv_material_categories);
        et_material_properties_name= (EditText) view.findViewById(R.id.et_material_detail_properties_name);
//        et_material_properties_categories= (EditText) view.findViewById(R.id.et_material_detail_properties_categories);
        et_material_properties_ingredient= (EditText) view.findViewById(R.id.et_material_detail_properties_ingredient);
        et_material_properties_hardness= (EditText) view.findViewById(R.id.et_material_detail_properties_hardness);
        et_material_properties_density= (EditText) view.findViewById(R.id.et_material_detail_properties_density);
        et_material_properties_thermalConductivity= (EditText) view.findViewById(R.id.et_material_detail_properties_thermalConductivity);
        et_material_properties_specificHeatCapacity= (EditText) view.findViewById(R.id.et_material_detail_properties_specificHeatCapacity);
        et_material_properties_youngsModulus= (EditText) view.findViewById(R.id.et_material_detail_properties_youngsModulus);
        et_material_properties_impactStrength= (EditText) view.findViewById(R.id.et_material_detail_properties_impactStrength);
        et_material_properties_extension= (EditText) view.findViewById(R.id.et_material_detail_properties_extension);
        et_material_properties_areaReduction= (EditText) view.findViewById(R.id.et_material_detail_properties_areaReduction);
        et_material_properties_conductiveCoefficient= (EditText) view.findViewById(R.id.et_material_detail_properties_conductiveCoefficient);
        et_material_properties_condition= (EditText) view.findViewById(R.id.et_material_detail_properties_condition);
        et_material_properties_tensileStrength= (EditText) view.findViewById(R.id.et_material_detail_properties_tensileStrength);
        et_material_properties_yieldStrength= (EditText) view.findViewById(R.id.et_material_detail_properties_yieldStrength);
        et_material_properties_shearStrength= (EditText) view.findViewById(R.id.et_material_detail_properties_shearStrength);
        et_material_properties_heatTreatment= (EditText) view.findViewById(R.id.et_material_detail_properties_heatTreatment);
        et_material_properties_lowMeltingPoint= (EditText) view.findViewById(R.id.et_material_detail_properties_lowMeltingPoint);
        et_material_properties_highMeltingPoint= (EditText) view.findViewById(R.id.et_material_detail_properties_highMeltingPoint);
        et_material_properties_thermalExpansionCoefficient= (EditText) view.findViewById(R.id.et_material_detail_properties_thermalExpansionCoefficient);

        sp_material_coefficientParameters_forceModel= (Spinner) view.findViewById(R.id.sp_material_detail_coefficientParameters_forceModel);
        et_material_coefficientParameters_Kte= (EditText) view.findViewById(R.id.et_material_detail_coefficientParameters_Kte);
        et_material_coefficientParameters_Kre= (EditText) view.findViewById(R.id.et_material_detail_coefficientParameters_Kre);
        et_material_coefficientParameters_Kae= (EditText) view.findViewById(R.id.et_material_detail_coefficientParameters_Kae);
        et_material_coefficientParameters_Ktc= (EditText) view.findViewById(R.id.et_material_detail_coefficientParameters_Ktc);
        et_material_coefficientParameters_Krc= (EditText) view.findViewById(R.id.et_material_detail_coefficientParameters_Krc);
        et_material_coefficientParameters_Kac= (EditText) view.findViewById(R.id.et_material_detail_coefficientParameters_Kac);

        et_material_limits_minChipThickness= (EditText) view.findViewById(R.id.et_material_detail_limits_minChipThickness);
        et_material_limits_maxChipThickness= (EditText) view.findViewById(R.id.et_material_detail_limits_maxChipThickness);
        et_material_limits_minCuttingSpeed= (EditText) view.findViewById(R.id.et_material_detail_limits_minCuttingSpeed);
        et_material_limits_maxCuttingSpeed= (EditText) view.findViewById(R.id.et_material_detail_limits_maxCuttingSpeed);
        et_material_limits_minRakeAngle= (EditText) view.findViewById(R.id.et_material_detail_limits_minRakeAngle);
        et_material_limits_maxRakeAngle= (EditText) view.findViewById(R.id.et_material_detail_limits_maxRakeAngle);

        cb_material_standards_AFNOR= (CheckBox) view.findViewById(R.id.cb_material_standards_AFNOR);
        cb_material_standards_AISI= (CheckBox) view.findViewById(R.id.cb_material_standards_AISI);
        cb_material_standards_BS= (CheckBox) view.findViewById(R.id.cb_material_standards_BS);
        cb_material_standards_CMC= (CheckBox) view.findViewById(R.id.cb_material_standards_CMC);
        cb_material_standards_DIN_nr= (CheckBox) view.findViewById(R.id.cb_material_standards_DIN_nr);
        cb_material_standards_EN= (CheckBox) view.findViewById(R.id.cb_material_standards_EN);
        cb_material_standards_JIS= (CheckBox) view.findViewById(R.id.cb_material_standards_JIS);
        cb_material_standards_SAE= (CheckBox) view.findViewById(R.id.cb_material_standards_SAE);
        cb_material_standards_SS= (CheckBox) view.findViewById(R.id.cb_material_standards_SS);
        cb_material_standards_UNF= (CheckBox) view.findViewById(R.id.cb_material_standards_UNF);
        cb_material_standards_UNI= (CheckBox) view.findViewById(R.id.cb_material_standards_UNI);
        cb_material_standards_W_nr= (CheckBox) view.findViewById(R.id.cb_material_standards_W_nr);

//        btn_material_add_userCategories= (Button) view.findViewById(R.id.btn_material_add_categories);
//        btn_material_delete_userCategories= (Button) view.findViewById(R.id.btn_material_delete_categories);
//        btn_material_add_userMaterial= (Button) view.findViewById(R.id.btn_material_add_userMaterial);
//        btn_material_delete_userMaterial= (Button) view.findViewById(R.id.btn_material_delete_userMaterial);
//        btn_material_commit_userMaterial= (Button) view.findViewById(R.id.btn_material_commit_userMaterial);
//        btn_material_cancel_userMaterial= (Button) view.findViewById(R.id.btn_material_cancel_userMaterial);
    }

    private void initialSysData() {

        if (childrenList1.size()==0)
        {
            childrenList1.add("Aluminum 6061-T6 [95]");
            childrenList1.add("Aluminum 6063");
        }

        if (childrenList2.size()==0){
            childrenList2.add("Aluminum 7050-T7451 [140]");

        }

        if (childrenList3.size()==0){
            childrenList3.add("Copper 3021");

        }

        if (data.size()==0){
            data.put(parentList[0],childrenList1);
            data.put(parentList[1],childrenList2);
            data.put(parentList[2],childrenList3);

        }

        for (int i=0;i<parentList.length;i++){
            groupList.add(parentList[i]);
        }
    }
}
