package com.example.a103.datasearch.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.a103.datasearch.CuttingConditionsManagementActivity;
import com.example.a103.datasearch.CuttingParaDetailsAddActivity;
import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.R;

/**
 * Created by A103 on 2017/2/10.
 * 参数页面的fragment
 */

public class ParameterFragment extends Fragment {

    private static final String TAG = "ParameterFragment";
    Toolbar mToolbar;

    public static ParameterFragment newInstance(String s){
        ParameterFragment parameterFragment=new ParameterFragment();
        Bundle bundle=new Bundle();
        bundle.putString(Constant.ARGS,s);
        parameterFragment.setArguments(bundle);
        return parameterFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View groupView=inflater.inflate(R.layout.fragment_parameter,container,false);

        initView(groupView);

        addChildFragment();

        return groupView;
    }

    private void addChildFragment() {

        // add child fragment CuttingConditionsFragment
        Fragment fragment=getChildFragmentManager().findFragmentByTag(Constant.CUTTING_CONDITIONS_FRAGMENT_TAG);

        if (fragment==null){
            Log.d(TAG, "addChildFragment: add new child fragment"+Constant.CUTTING_CONDITIONS_FRAGMENT_TAG);
            CuttingConditionsFragment cuttingConditionsFragment=new CuttingConditionsFragment();
            FragmentTransaction fragmentTransaction=getChildFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.empty_parameter_fragment_container,cuttingConditionsFragment,Constant.CUTTING_CONDITIONS_FRAGMENT_TAG);
            fragmentTransaction.commit();
        }
    }

    private void initView(View groupView) {

        mToolbar= (Toolbar) groupView.findViewById(R.id.toolbar_fragment_parameter);

        mToolbar.inflateMenu(R.menu.menu_fragment_parameter);

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()){

                    case R.id.add_parameters:
                        // add parameters
                        CuttingParaDetailsAddActivity.actionStart(getContext());
                        return true;

                    case R.id.manage_cutting_conditions:
                        //case manage cutting conditions
                        CuttingConditionsManagementActivity.actionStart(getContext());
                }
                return ParameterFragment.super.onOptionsItemSelected(item);
            }

        });

    }
}
