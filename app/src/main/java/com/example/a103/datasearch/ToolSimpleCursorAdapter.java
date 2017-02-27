package com.example.a103.datasearch;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhengxiaohu on 2017/2/27.
 */

public class ToolSimpleCursorAdapter extends SimpleCursorAdapter {
    public ToolSimpleCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return super.newView(context, cursor, parent);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor);

        ViewHolder holder= (ViewHolder) view.getTag();
        if (holder==null){
            holder=new ViewHolder();
            holder.colImp=cursor.getColumnIndexOrThrow(DataSearchDbAdapter.COL_ID);
            view.setTag(holder);
        }

        if (cursor.getInt(holder.colImp)>0){
            //do something
        }else{
            //do something
        }
    }

    static class ViewHolder{
        //存储索引
        int colImp;
        //存储视图
        View listTab;
    }
}
