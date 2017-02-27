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
 */

public class AdvancedFragment extends Fragment {
    public static AdvancedFragment newInstance(String s){
        AdvancedFragment advancedFragment=new AdvancedFragment();
        Bundle bundle=new Bundle();
        bundle.putString(Constant.ARGS,s);
        advancedFragment.setArguments(bundle);
        return advancedFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_advanced,container,false);
        Bundle bundle = getArguments();
        String s = bundle.getString(Constant.ARGS);
        TextView textView = (TextView) view.findViewById(R.id.title_advanced);
        textView.setText(s);
        return view;
    }
}
