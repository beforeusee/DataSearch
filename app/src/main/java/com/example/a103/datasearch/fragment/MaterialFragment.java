package com.example.a103.datasearch.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.a103.datasearch.MaterialAddActivity;
import com.example.a103.datasearch.MaterialCategoriesManagementActivity;
import com.example.a103.datasearch.R;
import com.example.a103.datasearch.utils.Constant;

import static com.example.a103.datasearch.utils.Constant.MATERIAL_CATEGORIES_FRAGMENT_TAG;

/**
 * Created by A103 on 2017/2/10.
 * 材料页面的fragment
 */

public class MaterialFragment extends Fragment{
    //声明与二级目录了相关的成员变量
    private static final String TAG = "MaterialFragment";
    Toolbar toolbar;

    public static MaterialFragment newInstance(String s){
        MaterialFragment materialFragment=new MaterialFragment();
        Bundle bundle=new Bundle();
        bundle.putString(Constant.ARGS,s);
        materialFragment.setArguments(bundle);
        return materialFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View groupView = inflater.inflate(R.layout.fragment_material, container, false);
        initialView(groupView);
        addChildFragment();
        return groupView;
    }

    /**
     * 加载菜单
     * @param menu 菜单
     * @param inflater 菜单栏布局
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
//        inflater.inflate(R.menu.menu_fragment_material,menu);
    }

    /**
     * 菜单选项点击事件
     * @param item 菜单选项
     * @return 返回是否成功
     */
    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_material:
                MaterialAddActivity.actionStart(getContext());
                return true;
            case R.id.manage_material_categories:
                MaterialCategoriesManagementActivity.actionStart(getContext());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    /**
     * 初始化MaterialFragment的界面
     * @param view fragment_material的布局
     */
    private void initialView(View view) {
        toolbar= (Toolbar) view.findViewById(R.id.toolbar_fragment_material);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.menu_fragment_material);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.add_material:
                        MaterialAddActivity.actionStart(getContext());
                        return true;
                    case R.id.manage_material_categories:
                        MaterialCategoriesManagementActivity.actionStart(getContext());
                        return true;
                }
                return MaterialFragment.super.onOptionsItemSelected(item);
            }
        });
    }

    /**
     * 初始化子Fragment
     */
    private void addChildFragment() {
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
}
