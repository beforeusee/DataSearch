package com.example.a103.datasearch;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.SimpleCursorTreeAdapter;

import com.example.a103.datasearch.dao.DaoSession;
import com.example.a103.datasearch.utils.DatabaseApplication;

/**
 * Created by zhengxiaohu on 2017/3/22.
 */

public class MaterialSimpleCursorTreeAdapter extends SimpleCursorTreeAdapter {

    SQLiteDatabase db;
    DaoSession daoSession;
    private Cursor groupCursor;
    private Context mContext;
    private int mGroupInt=0;

    public MaterialSimpleCursorTreeAdapter(Context context, Cursor cursor, int groupLayout, String[] groupFrom, int[] groupTo, int childLayout, String[] childFrom, int[] childTo) {
        super(context, cursor, groupLayout, groupFrom, groupTo, childLayout, childFrom, childTo);
        this.groupCursor=cursor;
        this.mContext=context;
        this.db= DatabaseApplication.getDb();
        this.daoSession=DatabaseApplication.getDaoSession();
    }

    @Override
    protected Cursor getChildrenCursor(Cursor groupCursor) {
        //获取材料分类的id
        Long groupId=groupCursor.getLong(groupCursor.getColumnIndexOrThrow("NAME"));
        Cursor childCursor=db.query("MATERIAL",null,
                "MATERIAL_CATEGORIES_ID=?", new String[]{String.valueOf(groupId)}, null,null,null);
        return childCursor;
    }

    @Override
    public Cursor getChild(int groupPosition, int childPosition) {
        return super.getChild(groupPosition, childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
//        Cursor groupCursor=getGroup(groupPosition);
//
//        return groupCursor.getLong(mGroupInt);
        return super.getGroupId(groupPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
//        Cursor childCursor=getChild(groupPosition, childPosition);
//        return childCursor.getLong(1);
        return super.getChildId(groupPosition, childPosition);
    }
}
