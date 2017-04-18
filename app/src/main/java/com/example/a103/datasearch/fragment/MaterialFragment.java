package com.example.a103.datasearch.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.a103.datasearch.MaterialCategoriesManagementActivity;
import com.example.a103.datasearch.MaterialDetailActivity;
import com.example.a103.datasearch.MessageEvent;
import com.example.a103.datasearch.dao.DaoSession;
import com.example.a103.datasearch.data.CoefficientParameters;
import com.example.a103.datasearch.data.Material;
import com.example.a103.datasearch.ExpandableListViewAdapter;
import com.example.a103.datasearch.R;
import com.example.a103.datasearch.data.MaterialCategories;
import com.example.a103.datasearch.data.MaterialCuttingLimits;
import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.utils.DatabaseApplication;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.example.a103.datasearch.utils.Constant.MATERIAL_CATEGORIES_FRAGMENT_TAG;
import static com.example.a103.datasearch.utils.Constant.MATERIAL_DETAIL_FRAGMENT_TAG;

/**
 * Created by A103 on 2017/2/10.
 * 材料页面的fragment
 */

public class MaterialFragment extends Fragment implements MaterialCategoriesFragment.ExpandableListViewChildSelectListener{
    //声明与二级目录了相关的成员变量
    private static final String TAG = "MaterialFragment";
    private View groupView;
    private Long materialId;

    //分类管理，添加材料，删除材料控件声明

    Button btn_material_categories_management;         //分类管理
    Button btn_material_categories_addMaterial;        //添加材料
    Button btn_material_delete_material;   //删除材料

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
        groupView = inflater.inflate(R.layout.fragment_material, container, false);
        initialView(groupView);
        initialChildFragment(groupView);

        /**
         * 设置"材料管理"的点击监听事件，启动{@link MaterialCategoriesManagementActivity}
         */
        btn_material_categories_management.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialCategoriesManagementActivity.actionStart(getContext());
            }
        });

        /**
         * 设置"添加材料"的点击监听事件，启动{@link MaterialDetailActivity}
         */
        btn_material_categories_addMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDetailActivity.actionStart(getContext());
            }
        });

        /**
         * 删除材料的监听回调
         */
        btn_material_delete_material.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 2017/4/18 删除所选的材料
                Toast.makeText(getContext(),"删除所选材料",Toast.LENGTH_SHORT).show();
                Long materialId=getMaterialId();
                final DaoSession daoSession= DatabaseApplication.getDaoSession();

                final Material material=daoSession.getMaterialDao().load(materialId);
                final MaterialCategories materialCategories=daoSession.getMaterialCategoriesDao().load(material.getMaterialCategoriesId());
                final MaterialCuttingLimits materialCuttingLimits=material.getMaterialCuttingLimits();
                final CoefficientParameters coefficientParameters=material.getCoefficientParameters();

                //弹出删除的警告对话框
                AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(getContext());
                alertDialogBuilder.setTitle("提示");
                alertDialogBuilder.setMessage("确定删除材料:"+material.getName()+" 吗?");
                alertDialogBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (daoSession.getMaterialDao().hasKey(material)&&
                                daoSession.getMaterialCuttingLimitsDao().hasKey(materialCuttingLimits)&&
                                daoSession.getCoefficientParametersDao().hasKey(coefficientParameters)){
                            //删除material及其关联的materialCuttingLimits和coefficientParameters
                            daoSession.getMaterialDao().delete(material);
                            daoSession.getMaterialCuttingLimitsDao().delete(materialCuttingLimits);
                            daoSession.getCoefficientParametersDao().delete(coefficientParameters);

                            materialCategories.resetMaterials();
                            Log.d(TAG, "onItemClick: successfully deleted: "+material.getName());
                            //发送广播通知
                            sendRefreshExpandableListViewBroadcast();

                            //设置删除按钮Button：btn_material_delete_material为不可点击
                            btn_material_delete_material.setEnabled(false);

                        }else{
                            Log.d(TAG, "onItemClick: failed to delete: "+material.getName()+
                                    ",does not exist");
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
        return groupView;
    }

    /**
     * 发送更新ExpandableListView列表的广播
     */
    private void sendRefreshExpandableListViewBroadcast(){
        Intent intent=new Intent();
        intent.setAction(Constant.ACTION_REFRESH_MATERIAL_CATEGORIES);
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
        Log.d(TAG, "sendRefreshExpandableListViewBroadcast: "+"发送更新expandableListView的广播");
    }

    /**
     *注册EventBus事件
     */
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    /**
     * 注销EventBus事件
     */
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 初始化MaterialFragment的界面
     * @param view fragment_material的布局
     */
    private void initialView(View view) {
        btn_material_categories_management= (Button) view.findViewById(R.id.btn_material_categories_management);
        btn_material_categories_addMaterial= (Button) view.findViewById(R.id.btn_material_categories_addMaterial);
        btn_material_delete_material= (Button) view.findViewById(R.id.btn_material_delete_material);
        btn_material_delete_material.setEnabled(false);
    }

    /**
     * 获取根布局
     * @return 返回子fragment的根布局，也就是MaterialFragment的布局
     */
    private View getGroupView(){
        return groupView;
    }

    /**
     * 初始化子Fragment
     * @param groupView 传入的参数为父Fragment的布局
     */
    private void initialChildFragment(View groupView) {

        //判断groupView中是否能找到子fragment的布局

        //如果在布局中找到material_categories_fragment_container,将materialCategoriesFragment动态添加到布局中
        if (groupView.findViewById(R.id.empty_material_fragment_container)!=null){
            Fragment fragment=getChildFragmentManager().findFragmentByTag(MATERIAL_CATEGORIES_FRAGMENT_TAG);
            if (fragment==null){
                Log.d(TAG, "onCreateView: add new fragment: "+MATERIAL_CATEGORIES_FRAGMENT_TAG);
                MaterialCategoriesFragment materialCategoriesFragment=new MaterialCategoriesFragment();
                FragmentTransaction fragmentTransaction=getChildFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.empty_material_fragment_container, materialCategoriesFragment,MATERIAL_CATEGORIES_FRAGMENT_TAG);
                fragmentTransaction.commit();
            }else {
                Log.d(TAG, "onCreateView: fragment: "+MATERIAL_CATEGORIES_FRAGMENT_TAG+" already existed,no need to add it again.");
            }
        }
        //布局中含material_detail_fragment_container，将materialDetailFragment动态添加到布局中，加载的是(layout-sw600dp)fragment_material
        if (groupView.findViewById(R.id.material_detail_fragment_container)!=null){
            Fragment fragment=getChildFragmentManager().findFragmentByTag(MATERIAL_DETAIL_FRAGMENT_TAG);
            if (fragment==null){

                Log.d(TAG, "onCreateView: add new fragment: "+MATERIAL_DETAIL_FRAGMENT_TAG);
                MaterialDetailFragment materialDetailFragment=new MaterialDetailFragment();

                FragmentTransaction fragmentTransaction=getChildFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.material_detail_fragment_container,materialDetailFragment,MATERIAL_DETAIL_FRAGMENT_TAG);
                fragmentTransaction.commit();

            }else {
                Log.d(TAG, "onCreateView: fragment: "+MATERIAL_DETAIL_FRAGMENT_TAG+" already existed ,no need to add it again.");
            }
        }
    }

    /**
     * ExpandableListView的子节点被选中时的函数
     * @param materialId 所选中材料的Id
     */
    @Override
    public void onExpandableListViewChildSelect(Long materialId) {
        View rootView=getGroupView();
        MaterialDetailFragment materialDetailFragment= (MaterialDetailFragment) getChildFragmentManager().findFragmentByTag(MATERIAL_DETAIL_FRAGMENT_TAG);
        if (rootView.findViewById(R.id.material_detail_fragment_container)!=null){
            if (materialDetailFragment==null){
                materialDetailFragment=new MaterialDetailFragment();
                FragmentTransaction fragmentTransaction=getChildFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.material_detail_fragment_container,materialDetailFragment,MATERIAL_DETAIL_FRAGMENT_TAG);
                fragmentTransaction.commit();
            }
        }else {
            if (materialDetailFragment==null){
                materialDetailFragment=new MaterialDetailFragment();
                FragmentTransaction fragmentTransaction=getChildFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.empty_material_fragment_container,materialDetailFragment,MATERIAL_DETAIL_FRAGMENT_TAG);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        }
        materialDetailFragment.setMaterialDetailData(materialId);
    }

    /**
     * 定义接受消息MessageVent后的执行方法，在顶部用注释@Subscribe(threadMode = ThreadMode.POSTING)来表示
     * @param event 接受到的消息事件对象，其中含有数据
     */
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onMessageEvent(MessageEvent event){
        //获取对象event的materialId
        Long materialId=event.materialId;
        //本类成员变量赋值
        this.materialId=materialId;
        //MaterialDetailFragment中显示
        onExpandableListViewChildSelect(materialId);
        //设置btn_material_delete_material可点击
        btn_material_delete_material.setEnabled(true);
    }

    /**
     * 获取材料的materialId
     * @return materialId
     */
    public Long getMaterialId() {
        return materialId;
    }
}
