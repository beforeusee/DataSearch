package com.example.a103.datasearch.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a103.datasearch.Constant;
import com.example.a103.datasearch.FaceMillingCutter;
import com.example.a103.datasearch.R;

/**
 * Created by A103 on 2017/2/10.
 * 刀具页面的fragment
 */

public class ToolFragment extends Fragment {

    private ListView mListView;

    public static ToolFragment newInstance(String s){
        ToolFragment toolFragment=new ToolFragment();
        Bundle bundle=new Bundle();
        bundle.putString(Constant.ARGS,s);
        toolFragment.setArguments(bundle);
        return toolFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tool,container,false);
//        Bundle bundle = getArguments();
//        String s = bundle.getString(Constant.ARGS);
//        TextView textView = (TextView) view.findViewById(R.id.title_tool);
//        textView.setText(s);
        mListView= (ListView) view.findViewById(R.id.lv_tools);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(view.getContext(),R.layout.tools_row,R.id.tool_row_text,
                new String[]{"面铣刀","方肩铣刀","球头铣刀"});
        mListView.setAdapter(arrayAdapter);
        return view;
    }
}
