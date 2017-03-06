package com.example.a103.datasearch.fragment;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.a103.datasearch.Constant;
import com.example.a103.datasearch.DataSearchDbAdapter;
import com.example.a103.datasearch.MainActivity;
import com.example.a103.datasearch.R;
import com.example.a103.datasearch.Tool;
import com.example.a103.datasearch.ToolDetailActivity;
import com.example.a103.datasearch.ToolSimpleCursorAdapter;

/**
 * Created by A103 on 2017/2/10.
 * 刀具页面的fragment
 */

public class ToolFragment extends Fragment {

    private ListView mListView;
    private DataSearchDbAdapter mDbAdapter;
    private ToolSimpleCursorAdapter mCursorAdapter;
    private Button mAddButton;

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
        mDbAdapter=new DataSearchDbAdapter(getContext());

        mDbAdapter.open();

        mAddButton= (Button) view.findViewById(R.id.btn_tool_add);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                fireToolDetailActivity(null);
                mCursorAdapter.changeCursor(mDbAdapter.fetchAllTools());
            }
        });
        Cursor cursor=mDbAdapter.fetchAllTools();

        //from columns defined in the db,来自数据库中定义的列
        String[] from=new String[]{DataSearchDbAdapter.COL_NAME};

        //to the ids of views in the layout,到布局中视图的id
        int[] to=new int[]{R.id.tool_row_text};

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
                            int nId=getIdFromPosition(masterPosition);
                            Tool tool=mDbAdapter.fecthToolById(nId);
                            fireToolDetailActivity(tool);
                            Toast.makeText(view.getContext(),"点击了查看刀具",Toast.LENGTH_SHORT).show();
                        }else {   //delete tool,删除刀具
                            //确认删除刀具的警告对话框
                            AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(getContext());
                            alertDialogBuilder.setTitle("确定删除该刀具吗？");
                            alertDialogBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //点击“确定”，删除所选刀具
                                    mDbAdapter.deleteToolsById(getIdFromPosition(masterPosition));
                                    mCursorAdapter.changeCursor(mDbAdapter.fetchAllTools());
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
/*        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(view.getContext(),R.layout.tools_row,
                R.id.tool_row_text,
                new String[]{"面铣刀","方肩铣刀","球头铣刀"});
        mListView.setAdapter(arrayAdapter);*/

        //注册广播来进行listView列表的刷新
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("action.refreshTool");
        getContext().registerReceiver(mRefreshBroadcastReceiver,intentFilter);

        return view;
    }

    /**
     * 广播接收
     */
    private BroadcastReceiver mRefreshBroadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            //如果接收到广播消息，更新listView列表
            String action=intent.getAction();
            if (action.equals("action.refreshTool")){
                Log.w("接收到了广播：","是的");
                mCursorAdapter.changeCursor(mDbAdapter.fetchAllTools());
            }
        }
    };



    /**
     * 根据刀具在ListView中的位置来获取它的Id
     * @param nC
     * @return (int)mCursorAdapter.getItemId(nC)
     */
    private int getIdFromPosition(int nC){
        return (int) mCursorAdapter.getItemId(nC);
    }

    private void fireToolDetailActivity(final Tool tool){
        Intent intent=new Intent(getActivity(),ToolDetailActivity.class);
        intent.putExtra("tool_ID",0);
        intent.putExtra("Tool",tool);

        //如何传递对象到ToolDetailActivity中？

        final boolean isEditOperation=(tool!=null);
        intent.putExtra("tool_isEditOperation",isEditOperation);
        startActivity(intent);
        Toast.makeText(getContext(),"进入了刀具详细页面",Toast.LENGTH_SHORT).show();
    }
}
