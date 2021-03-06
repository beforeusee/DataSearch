package com.example.a103.datasearch;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.a103.datasearch.dao.DaoSession;
import com.example.a103.datasearch.data.MaterialCategories;
import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.utils.CustomTitleBar;
import com.example.a103.datasearch.utils.DatabaseApplication;

import java.util.ArrayList;
import java.util.List;

public class MaterialCategoriesManagementActivity extends AppCompatActivity {

    CustomTitleBar material_categories_management_customTitleBar;
    RecyclerView mRecyclerView;
    LinearLayout mAddBarLinearLayout;
    private List<MaterialCategories> materialCategoriesList=new ArrayList<>();
    private DaoSession daoSession;  //获取应用的全局数据库管理变量daoSession
    private MaterialCategoriesAdapter adapter;
    private static final String TAG = "MaterialCategoriesManag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_categories_management);

        //初始化界面
        initialMaterialCategoriesManagementView();

        //初始化材料分类，从数据库获取分类信息
        initialMaterialCategories();

        //给RecyclerView设置布局管理器，此处设置为线性布局
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        //创建并设置mRecyclerView的适配器
        adapter=new MaterialCategoriesAdapter(materialCategoriesList);
        mRecyclerView.setAdapter(adapter);

        //设置mAddBarLinearLayout的监听回调函数
        mAddBarLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 2017/3/28 弹出添加材料分类的对话框
                final Dialog dialog=new Dialog(MaterialCategoriesManagementActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.add_material_categories_dialog);

                //添加材料分类对话框的控件
                final EditText editMaterialCategories= (EditText) dialog.findViewById(R.id.et_add_material_categories);
                final Button categoriesCommitButton= (Button) dialog.findViewById(R.id.add_material_categories_button_commit);
                final Button categoriesCancelButton= (Button) dialog.findViewById(R.id.add_material_categories_button_cancel);

                //初始化categoriesCommitButton不可用
                categoriesCommitButton.setEnabled(false);

                //设置EditText的监听器，根据用户是否输入有效字符串来判断categoriesCommitButton是否可点击，
                // 禁止用户不做任何输入，从而在数据库中创建空名称的(无意义)材料分类数据
                editMaterialCategories.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    /**
                     * @param s
                     * tag s为变化后的所有字符
                     */
                    @Override
                    public void afterTextChanged(Editable s) {
                        if ("".equals(s.toString().trim())){
                            categoriesCommitButton.setEnabled(false); //如果去掉所有空白字符的s为空，则设置commit按钮不可点击
                        }else {
                            categoriesCommitButton.setEnabled(true);  //s不为空，可点击
                        }
                    }
                });

                //设置commitButton的监听器
                categoriesCommitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //创建MaterialCategories对象
                        MaterialCategories materialCategories=new MaterialCategories();
                        materialCategories.setName(editMaterialCategories.getText().toString());

                        //存入数据库
                        daoSession.getMaterialCategoriesDao().save(materialCategories);

                        //如果数据库中存在刚保存的数据则创建成功并更新材料列表
                        if (daoSession.getMaterialCategoriesDao().hasKey(materialCategories)){
                            Log.d(TAG, "categoriesCommitButton:onClick: successfully saved: "+materialCategories.getName());
                            //更新材料分类列表
                            updateMaterialCategories();
                            //发送广播，通知MaterialFragment中的数据进行更新
                            sendMaterialCategoriesRefreshBroadcast();
                        }else {
                            Log.d(TAG, "categoriesCommitButton:onClick: failed to save: "+materialCategories.getName()+
                            " in the database table MATERIAL_CATEGORIES");
                        }

                        //销毁对话框
                        dialog.dismiss();
                    }
                });

                //设置categoriesCancelButton的监听器
                categoriesCancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();  //销毁添加材料的对话框
                    }
                });

                //显示添加材料的对话框
                dialog.show();
                Log.d(TAG, "mAddBarLinearLayout:onClick: 添加材料分类的对话框顺利弹出");
            }
        });

        //RecyclerView的点击事件：采用了设计模式的中的观察者模式
        //利用adapter设置列表单击事件，监听接口自定义设置在MaterialCategoriesAdapter中
        adapter.setOnItemDeleteViewClickListener(new MaterialCategoriesAdapter.OnRecyclerViewItemDeleteViewClickListener() {
            @Override
            public void onItemDeleteViewClick(View view, int position) {
                final MaterialCategories materialCategories=materialCategoriesList.get(position);
                Log.d(TAG, "onItemDeleteViewClick: click materialCategories: "+materialCategories.getName());

                //弹出删除的警告对话框
                AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(MaterialCategoriesManagementActivity.this);
                alertDialogBuilder.setTitle("提示");
                alertDialogBuilder.setMessage("确定删除材料分类:"+materialCategories.getName()+"及其材料列表吗?");
                alertDialogBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //点击“确定”，删除所选刀具
                        //判断数据表中是否有materialCategories类型的数据，有则删除并更新materialCategoriesList列表
                        // 如果没有打印调试信息表示不存在该数据
                        if (daoSession.getMaterialCategoriesDao().hasKey(materialCategories)){

                            //删除该分组对应的所有材料列表
                            if (materialCategories.getMaterials().size()>0){

                                for (int i=0;i<materialCategories.getMaterials().size();i++){

                                    daoSession.getMaterialDao().delete(materialCategories.getMaterials().get(i));
                                }
                            }

                            //删除该材料分组
                            daoSession.getMaterialCategoriesDao().delete(materialCategories);

                            updateMaterialCategories();
                            Log.d(TAG, "onItemDeleteViewClick: successfully deleted: "+materialCategories.getName());
                            //2017/4/7 刷新fragment_material中的UI，
                            // 包括MaterialCategoriesFragment中ExpandableListView分类显示的数据更新
                            //和MaterialDetailFragment中材料种类控件Spinner的数据更新
                            //利用广播机制，通知数据进行更新
                            sendMaterialCategoriesRefreshBroadcast();
                        }else{
                            Log.d(TAG, "onItemDeleteViewClick: failed to delete: "+materialCategories.getName()+
                                    ",the data does not exist in the database table MATERIAL_CATEGORIES");
                        }
                    }
                });

                alertDialogBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog=alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        adapter.setOnItemTextViewClickViewListener(new MaterialCategoriesAdapter.OnRecyclerViewItemTextViewClickListener() {
            @Override
            public void onItemTextViewClick(View view, int position) {
                final MaterialCategories materialCategories=materialCategoriesList.get(position);
                final Dialog dialog=new Dialog(MaterialCategoriesManagementActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.edit_material_categories_dialog);

                //添加材料分类对话框的控件
                final EditText editMaterialCategories= (EditText) dialog.findViewById(R.id.et_edit_material_categories);
                final Button categoriesCommitButton= (Button) dialog.findViewById(R.id.edit_material_categories_button_commit);
                final Button categoriesCancelButton= (Button) dialog.findViewById(R.id.edit_material_categories_button_cancel);

                //初始化categoriesCommitButton可用
                editMaterialCategories.setText(materialCategories.getName());
                categoriesCommitButton.setEnabled(true);

                //设置EditText的监听器，根据用户是否输入有效字符串来判断categoriesCommitButton是否可点击，
                // 禁止用户不做任何输入，从而在数据库中创建空名称的(无意义)材料分类数据
                editMaterialCategories.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    /**
                     * @param s
                     * tag s为变化后的所有字符
                     */
                    @Override
                    public void afterTextChanged(Editable s) {
                        if ("".equals(s.toString().trim())){
                            categoriesCommitButton.setEnabled(false); //如果去掉所有空白字符的s为空，则设置commit按钮不可点击
                        }else {
                            categoriesCommitButton.setEnabled(true);  //s不为空，可点击
                        }
                    }
                });

                //设置commitButton的监听器
                categoriesCommitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //创建MaterialCategories对象
                        materialCategories.setName(editMaterialCategories.getText().toString());

                        //存入数据库
                        daoSession.getMaterialCategoriesDao().save(materialCategories);

                        //如果数据库中存在刚保存的数据则创建成功并更新材料列表
                        if (daoSession.getMaterialCategoriesDao().hasKey(materialCategories)){
                            Log.d(TAG, "categoriesCommitButton:onClick: successfully updated: "+materialCategories.getName());
                            //更新材料分类列表
                            updateMaterialCategories();
                            //发送广播，通知MaterialFragment中的数据进行更新
                            sendMaterialCategoriesRefreshBroadcast();
                        }else {
                            Log.d(TAG, "categoriesCommitButton:onClick: failed to save: "+materialCategories.getName()+
                                    " in the database table MATERIAL_CATEGORIES");
                        }

                        //销毁对话框
                        dialog.dismiss();
                    }
                });

                //设置categoriesCancelButton的监听器
                categoriesCancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();  //销毁添加材料的对话框
                    }
                });

                //显示添加材料的对话框
                dialog.show();
            }
        });

        //设置右边按钮“完成”的监听函数
        material_categories_management_customTitleBar.setTitleBarRightBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //结束当前Activity
                finish();
            }
        });

        //set the left button of customTitleBar
        material_categories_management_customTitleBar.setTitleBarLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish current activity
                finish();
            }
        });

        Log.d(TAG, "onCreate: successfully execution.");
    }

    /**
     * 发送更新MaterialCategories的广播
     */
    private void sendMaterialCategoriesRefreshBroadcast(){
        Intent intent=new Intent();
        intent.setAction(Constant.ACTION_REFRESH_MATERIAL_CATEGORIES);
        sendBroadcast(intent);
    }

    /**
     * 初始化材料分类列表materialCategoriesList
     */
    private void initialMaterialCategories() {
        daoSession= DatabaseApplication.getDaoSession();
        materialCategoriesList=daoSession.getMaterialCategoriesDao().loadAll();
        Log.d(TAG, "initialMaterialCategories: 加载数据库中材料分类列表的数据库");
    }

    /**
     * 更新材料分类列表
     */
    private void updateMaterialCategories(){
        materialCategoriesList=daoSession.getMaterialCategoriesDao().loadAll();
        adapter.updateMaterialCategoriesList(materialCategoriesList);
        Log.d(TAG, "updateMaterialCategories: 执行材料分类列表更新");
    }

    /**
     * 初始化材料分类界面的UI
     */
    private void initialMaterialCategoriesManagementView() {
        mAddBarLinearLayout= (LinearLayout) findViewById(R.id.ll_add_bar);
        material_categories_management_customTitleBar = (CustomTitleBar) findViewById(R.id.material_categories_management_customTitleBar);
        mRecyclerView= (RecyclerView) findViewById(R.id.
                material_categories_management_recycler_view);
    }

    /**
     * 该activity类为外界提供了启动本activity的方法和需要传入的参数，有时可能利用intent传入参数
     * @param context context of start MaterialCategoriesManagementActivity
     */
    public static void actionStart(Context context){
        Intent intent=new Intent(context,MaterialCategoriesManagementActivity.class);
        context.startActivity(intent);
    }
}
