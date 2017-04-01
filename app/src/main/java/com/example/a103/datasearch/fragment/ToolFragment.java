package com.example.a103.datasearch.fragment;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a103.datasearch.utils.Constant;
import com.example.a103.datasearch.utils.DatabaseApplication;
import com.example.a103.datasearch.R;
import com.example.a103.datasearch.dao.DaoSession;
import com.example.a103.datasearch.data.Tool;
import com.example.a103.datasearch.ToolDetailActivity;
import com.example.a103.datasearch.ToolSimpleCursorAdapter;

/**
 * Created by A103 on 2017/2/10.
 * 刀具页面的fragment
 */

public class ToolFragment extends Fragment {

    private ListView mListView;
    private ToolSimpleCursorAdapter mCursorAdapter;
    private Button mAddButton;
    private SQLiteDatabase db;
    private DaoSession mDaoSession;
    private static final String TAG = "ToolFragment";
    private BroadcastReceiver mRefreshBroadcastReceiver;  //广播接收

    public static ToolFragment newInstance(String s){
        ToolFragment toolFragment=new ToolFragment();
        Bundle bundle=new Bundle();
        bundle.putString(Constant.ARGS,s);
        toolFragment.setArguments(bundle);
        return toolFragment;
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
        mListView= (ListView) view.findViewById(R.id.lv_tools);
        mListView.setDivider(null);

        db= DatabaseApplication.getDb();
        mDaoSession=DatabaseApplication.getDaoSession();

        mAddButton= (Button) view.findViewById(R.id.btn_tool_add);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fireToolDetailActivity(null);
                Cursor cursor=db.query("TOOL",null,null,null,null,null,null);
                mCursorAdapter.changeCursor(cursor);
            }
        });

        Cursor cursor= db.query("TOOL",null,null,null,null,null,null); //获取数据库中TOOL表的cursor对象

        //from columns defined in the db,来自数据库中刀具表定义的列
        final String[] from=new String[]{"NAME"};

        //to the ids of views in the layout,到布局中视图的id
        final int[] to=new int[]{R.id.tool_row_text};
        mCursorAdapter=new ToolSimpleCursorAdapter(getContext(),R.layout.tools_row,cursor,from,to,0);
        mListView.setAdapter(mCursorAdapter);

        //Abbreviated for brevity
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,final int masterPosition, long id) {
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());

                ListView modeListView=new ListView(view.getContext());
                String[] modes=new String[]{"查看刀具","删除刀具"};
                ArrayAdapter<String> modeAdapter=new ArrayAdapter<String>(view.getContext(),
                        android.R.layout.simple_list_item_1,android.R.id.text1,modes);
                modeListView.setAdapter(modeAdapter);
                builder.setView(modeListView);
                final Dialog dialog=builder.create();
                dialog.show();

                //模式对话框(编辑和删除)的选项点击监听函数
                modeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //edit tool,编辑刀具
                        if (position==0){
                            long nId=getIdFromPosition(masterPosition);
                            Tool tool=mDaoSession.getToolDao().load(nId);
                            fireToolDetailActivity(tool);
                            Toast.makeText(view.getContext(),"点击了查看刀具",Toast.LENGTH_SHORT).show();

                        }else {   //delete tool,删除刀具
                            //确认删除刀具的警告对话框
                            AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(getContext());
                            alertDialogBuilder.setTitle("提示");
                            alertDialogBuilder.setMessage("确定删除该刀具吗？");
                            alertDialogBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //点击“确定”，删除所选刀具
                                    mDaoSession.getToolDao().deleteByKey(getIdFromPosition(masterPosition));
                                    Cursor cursor=db.query("TOOL",null,null,null,null,null,null);
                                    mCursorAdapter.changeCursor(cursor);
                                }
                            });

                            alertDialogBuilder.setNegativeButton("取消",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog alertDialog=alertDialogBuilder.create();
                            alertDialog.show();

                            Toast.makeText(view.getContext(),"点击了删除",Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();  //彻底删除对话框
                    }
                });
                Toast.makeText(view.getContext(),"点击了"+masterPosition,Toast.LENGTH_SHORT).show();
            }
        });
        //注册广播来进行listView列表的刷新
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("action.refreshTool");
        mRefreshBroadcastReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                //如果接收到广播消息，更新listView列表
                String action=intent.getAction();
                if (action.equals("action.refreshTool")){
                    Log.d("接收到了广播：","是的");

                    Cursor cursor=db.query("TOOL",null,null,null,null,null,null);
                    mCursorAdapter.changeCursor(cursor);
                }
            }
        };
        getActivity().registerReceiver(mRefreshBroadcastReceiver,intentFilter);
        Log.d(TAG, "onStart: 注册广播");
        Log.d(TAG, "onCreateView: 执行");
        return view;
    }

    @Override
    public void onStart() {

        Log.d(TAG, "onStart: 执行onStart方法");
        super.onStart();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 广播接收
     */


    @Override
    public void onStop() {

        super.onStop();
    }

    @Override
    public void onDestroyView() {
        if (mRefreshBroadcastReceiver!=null){
            getActivity().unregisterReceiver(mRefreshBroadcastReceiver);
        }

        Log.d(TAG, "onDestroy: 注销广播接收器");
        Log.d(TAG, "onDestroyView: 执行onDestroyView");
        super.onDestroyView();
    }

    /**
     * 根据刀具在ListView中的位置来获取它的Id
     * @param nC
     * @return (int)mCursorAdapter.getItemId(nC)
     */
    private long getIdFromPosition(int nC){
        return mCursorAdapter.getItemId(nC);
    }

    private void fireToolDetailActivity(final Tool tool){
        ToolDetailActivity.actionStart(getContext(),tool);
        Toast.makeText(getContext(),"进入了刀具详细页面",Toast.LENGTH_SHORT).show();
    }

}
