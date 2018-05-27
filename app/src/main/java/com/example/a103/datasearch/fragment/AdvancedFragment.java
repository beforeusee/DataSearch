package com.example.a103.datasearch.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.a103.datasearch.forcesimulation.ForceSimulationActivity;
import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.R;

/**
 * Created by A103 on 2017/2/10.
 * 高级功能的fragment
 */

public class AdvancedFragment extends Fragment {

    private static final String TAG = "AdvancedFragment";

    Toolbar mToolbar;
    Button mForceSimulationModuleButton;

    public static AdvancedFragment newInstance(String s){
        AdvancedFragment advancedFragment=new AdvancedFragment();
        Bundle bundle=new Bundle();
        bundle.putString(Constant.ARGS,s);
        advancedFragment.setArguments(bundle);
        return advancedFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_advanced,container,false);

        initView(view);

        mForceSimulationModuleButton.setOnClickListener(new View.OnClickListener() {

            //点击启动切削力仿真模块
            @Override
            public void onClick(View v) {

                ForceSimulationActivity.actionStart(getContext());
            }
        });
        return view;
    }

    /**
     * init view of {@link AdvancedFragment}
     * @param view view of {@link AdvancedFragment}
     */
    private void initView(View view) {

        mToolbar= (Toolbar) view.findViewById(R.id.toolbar_fragment_advanced);

        mForceSimulationModuleButton= (Button) view.findViewById(R.id.btn_force_simulation_module);
    }

}
