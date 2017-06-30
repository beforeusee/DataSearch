package com.example.a103.datasearch.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.a103.datasearch.ToolAddActivity;
import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.utils.DatabaseApplication;
import com.example.a103.datasearch.R;
import com.example.a103.datasearch.ToolDetailActivity;
import com.example.a103.datasearch.ToolSimpleCursorAdapter;

/**
 * Created by A103 on 2017/2/10.
 * 刀具页面的fragment
 */

public class ToolFragment extends Fragment {

    private ListView mListView;
    Toolbar toolbar;
    private ToolSimpleCursorAdapter mCursorAdapter;
    private SQLiteDatabase db;
    private static final String TAG = "ToolFragment";
    private BroadcastReceiver mRefreshBroadcastReceiver;  //广播接收
    Cursor mCursor;

    public static ToolFragment newInstance(String s){
        ToolFragment toolFragment=new ToolFragment();
        Bundle bundle=new Bundle();
        bundle.putString(Constant.ARGS,s);
        toolFragment.setArguments(bundle);
        return toolFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)  //版本
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tool,container,false);
/*        Bundle bundle = getArguments();
        String s = bundle.getString(Constant.ARGS);
        TextView textView = (TextView) view.findViewById(R.id.title_tool);
        textView.setText(s);*/
        initView(view);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.add_tool:
                        ToolAddActivity.actionStart(getContext());
                        return true;
                }
                return ToolFragment.super.onOptionsItemSelected(item);
            }
        });
        db= DatabaseApplication.getDb();
        mCursor = db.query("TOOL",null,null,null,null,null,null); //获取数据库中TOOL表的cursor对象

        //from columns defined in the db,来自数据库中刀具表定义的列
        final String[] from=new String[]{"NAME"};

        //to the ids of views in the layout,到布局中视图的id
        final int[] to=new int[]{R.id.tool_row_text};
        mCursorAdapter=new ToolSimpleCursorAdapter(getContext(),R.layout.tools_row, mCursor,from,to,0);
        mListView.setAdapter(mCursorAdapter);

        //Abbreviated for brevity
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,final int masterPosition, long id) {
                Long toolId=getIdFromPosition(masterPosition);
                ToolDetailActivity.actionStart(getContext(),toolId);
            }
        });

        //注册更新刀具列表广播接收器，收到广播后更新刀具列表
        registerRefreshToolListBroadcastReceiver();
        return view;
    }

    /**
     * 初始化ToolFragment界面
     * @param view toolFragment界面
     */
    private void initView(View view) {
        mListView= (ListView) view.findViewById(R.id.lv_tools);
        mListView.setDivider(null);
        toolbar= (Toolbar) view.findViewById(R.id.toolbar_fragment_tool);
//        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.menu_fragment_tool);
    }

    /**
     * 创建菜单栏
     * @param menu 菜单
     * @param inflater 菜单布局
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
//        inflater.inflate(R.menu.menu_fragment_tool,menu);
    }

    /**
     * 菜单选项
     * @param item 菜单选项
     * @return 返回结果
     */
    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_tool:
                fireToolDetailActivity(null);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    /**
     * 注册刷新刀具列表的接收器
     */
    private void registerRefreshToolListBroadcastReceiver(){
        //注册广播来进行listView列表的刷新
        //创建消息过滤器
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(Constant.ACTION_REFRESH_TOOL);
        //创建广播接收器
        if (mRefreshBroadcastReceiver==null){
            mRefreshBroadcastReceiver=new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                    //如果接收到广播消息，更新listView列表
                    String action=intent.getAction();
                    if (action.equals(Constant.ACTION_REFRESH_TOOL)){
                        Log.d(TAG, "onReceive: 接收到刷新刀具列表的广播");

                        Cursor cursor=db.query("TOOL",null,null,null,null,null,null);
                        mCursorAdapter.changeCursor(cursor);
                    }
                }
            };
        }
        getActivity().registerReceiver(mRefreshBroadcastReceiver,intentFilter);
        Log.d(TAG, "registerRefreshToolListBroadcastReceiver: 注册刷新刀具列表的广播");
    }

    @Override
    public void onDestroyView() {
        if (mRefreshBroadcastReceiver!=null){
            getActivity().unregisterReceiver(mRefreshBroadcastReceiver);
            Log.d(TAG, "onDestroyView: 注销刷新刀具列表的广播");
        }
        mCursor.close();
        super.onDestroyView();
    }

    /**
     * 根据刀具在ListView中的位置来获取它的Id
     * @param position position of tool list
     * @return (int)mCursorAdapter.getItemId(nC)
     */
    private Long getIdFromPosition(int position){
        return mCursorAdapter.getItemId(position);
    }
}
