package com.example.a103.datasearch;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorTreeAdapter;

/**
 * Created by zhengxiaohu on 2017/3/22.
 */

public class MaterialCursorTreeAdapter extends CursorTreeAdapter {


    public MaterialCursorTreeAdapter(Cursor cursor, Context context) {
        super(cursor, context);
    }

    @Override
    protected Cursor getChildrenCursor(Cursor groupCursor) {
        return null;
    }

    @Override
    protected View newGroupView(Context context, Cursor cursor, boolean isExpanded, ViewGroup parent) {
        return null;
    }

    @Override
    protected void bindGroupView(View view, Context context, Cursor cursor, boolean isExpanded) {

    }

    @Override
    protected View newChildView(Context context, Cursor cursor, boolean isLastChild, ViewGroup parent) {
        return null;
    }

    @Override
    protected void bindChildView(View view, Context context, Cursor cursor, boolean isLastChild) {

    }
}
