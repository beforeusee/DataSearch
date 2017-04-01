package com.example.a103.datasearch;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

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
    DaoSession daoSession= DatabaseApplication.getDaoSession();  //获取应用的全局数据库管理变量daoSession

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
        MaterialCategoriesAdapter adapter=new MaterialCategoriesAdapter(materialCategoriesList);
        mRecyclerView.setAdapter(adapter);

        //设置mAddBarLinearLayout的监听回调函数
        mAddBarLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2017/3/28 弹出添加材料分类的对话框
            }
        });

        // TODO: 2017/3/28 RecyclerView的点击回调函数注册,暂时没想到好办法，先在适配器类中处理


        //设置右边按钮“完成”的监听函数
        mCustomTitleBar.setTitleBarRightBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2017/3/28 点击,finish该对话框,并负责刷新fragment_material的材料分类列表
                finish();

                //刷新fragment_material的业务逻辑
            }
        });
    }

    private void initialMaterialCategories() {
        materialCategoriesList=daoSession.getMaterialCategoriesDao().loadAll();
    }

    private void initialMaterialCategoriesManagementView() {
        mAddBarLinearLayout= (LinearLayout) findViewById(R.id.ll_add_bar);
        mCustomTitleBar= (CustomTitleBar) findViewById(R.id.custom_title_bar);
        mRecyclerView= (RecyclerView) findViewById(R.id.
                material_categories_management_recycler_view);
    }

    /**
     * 该类为外界启动该活动提供了启动的方法和需要传入的参数，有时可能利用intent传入参数
     * @param context
     */
    public static void actionStart(Context context){
        Intent intent=new Intent(context,MaterialCategoriesManagementActivity.class);
        context.startActivity(intent);
    }
}
