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
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.a103.datasearch.dao.DaoSession;
import com.example.a103.datasearch.data.MaterialCategories;
import com.example.a103.datasearch.utils.CustomTitleBar;
import com.example.a103.datasearch.utils.DatabaseApplication;

import java.util.ArrayList;
import java.util.List;

public class MaterialCategoriesManagementActivity extends AppCompatActivity {

    CustomTitleBar mCustomTitleBar;
    RecyclerView mRecyclerView;
    LinearLayout mAddBarLinearLayout;
    private List<MaterialCategories> materialCategoriesList=new ArrayList<>();
    private DaoSession daoSession= DatabaseApplication.getDaoSession();  //获取应用的全局数据库管理变量daoSession
    private MaterialCategoriesAdapter adapter;

    private static final String TAG = "MaterialCategoriesManagementActivity";

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
                // TODO: 2017/3/28 弹出添加材料分类的对话框
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
        adapter.setOnItemClickListener(new MaterialCategoriesAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                final MaterialCategories materialCategories=materialCategoriesList.get(position);
                Log.d(TAG, "onItemClick: click materialCategories: "+materialCategories.getName());

                //弹出删除的警告对话框
                AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(MaterialCategoriesManagementActivity.this);
                alertDialogBuilder.setTitle("提示");
                alertDialogBuilder.setMessage("确定删除该材料分类吗？");
                alertDialogBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //点击“确定”，删除所选刀具
                        //判断数据表中是否有materialCategories类型的数据，有则删除并更新materialCategoriesList列表
                        // 如果没有打印调试信息表示不存在该数据
                        if (daoSession.getMaterialCategoriesDao().hasKey(materialCategories)){
                            daoSession.getMaterialCategoriesDao().delete(materialCategories);
                            updateMaterialCategories();
                            Log.d(TAG, "onItemClick: successfully deleted: "+materialCategories.getName());
                        }else{
                            Log.d(TAG, "onItemClick: failed to delete: "+materialCategories.getName()+
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

        //设置右边按钮“完成”的监听函数
        mCustomTitleBar.setTitleBarRightBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2017/3/28 点击,finish该对话框,并负责刷新fragment_material的材料分类列表
                finish();

                //TODO 刷新fragment_material的分类列表的业务逻辑
            }
        });

        Log.d(TAG, "onCreate: successfully execution!");
    }

    private void initialMaterialCategories() {
        materialCategoriesList=daoSession.getMaterialCategoriesDao().loadAll();
        Log.d(TAG, "initialMaterialCategories: 加载数据库中材料分类列表的数据库");
    }

    private void updateMaterialCategories(){
        materialCategoriesList=daoSession.getMaterialCategoriesDao().loadAll();
        adapter.updateMaterialCategoriesList(materialCategoriesList);
        Log.d(TAG, "updateMaterialCategories: 执行材料分类列表更新");
    }

    private void initialMaterialCategoriesManagementView() {
        mAddBarLinearLayout= (LinearLayout) findViewById(R.id.ll_add_bar);
        mCustomTitleBar= (CustomTitleBar) findViewById(R.id.custom_title_bar);
        mRecyclerView= (RecyclerView) findViewById(R.id.
                material_categories_management_recycler_view);
    }

    /**
     * 该activity类为外界提供了启动本activity的方法和需要传入的参数，有时可能利用intent传入参数
     * @param context
     */
    public static void actionStart(Context context){
        Intent intent=new Intent(context,MaterialCategoriesManagementActivity.class);
        context.startActivity(intent);
    }
}
