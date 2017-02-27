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
 * 材料页面的fragment
 */

public class MaterialFragment extends Fragment {
    public static MaterialFragment newInstance(String s){
        MaterialFragment materialFragment=new MaterialFragment();
        Bundle bundle=new Bundle();
        bundle.putString(Constant.ARGS,s);
        materialFragment.setArguments(bundle);
        return materialFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_material,container,false);
        Bundle bundle = getArguments();
        String s = bundle.getString(Constant.ARGS);
        TextView textView = (TextView) view.findViewById(R.id.title_material);
        textView.setText(s);
        return view;
    }
}
