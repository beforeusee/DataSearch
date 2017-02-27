package com.example.a103.datasearch.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a103.datasearch.Constant;
import com.example.a103.datasearch.R;

/**
 * Created by A103 on 2017/2/10.
 * 参数页面的fragment
 */

public class ParameterFragment extends Fragment {
    public static ParameterFragment newInstance(String s){
        ParameterFragment parameterFragment=new ParameterFragment();
        Bundle bundle=new Bundle();
        bundle.putString(Constant.ARGS,s);
        parameterFragment.setArguments(bundle);
        return parameterFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_parameter,container,false);
        Bundle bundle = getArguments();
        String s = bundle.getString(Constant.ARGS);
        TextView textView = (TextView) view.findViewById(R.id.title_parameter);
        textView.setText(s);
        return view;
    }
}
