package com.example.a103.datasearch.utils;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.a103.datasearch.dao.DaoMaster;
import com.example.a103.datasearch.dao.DaoSession;

/**
 * Created by zhengxiaohu on 2017/3/15.
 */

public class DatabaseApplication extends Application {

    private static DaoSession daoSession;
    private static SQLiteDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        //配置数据库
        setupDatabase();
    }

    private void setupDatabase() {
        //创建数据库
        DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(this,"dataSearch.db",null);
        //获取可写数据库
        db=helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster=new DaoMaster(db);
        //获取dao对象管理者
        daoSession=daoMaster.newSession();
    }

    public static DaoSession getDaoSession(){
        return daoSession;
    }

    public static SQLiteDatabase getDb(){
        return db;
    }
}
