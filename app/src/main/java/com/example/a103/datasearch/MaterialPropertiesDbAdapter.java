package com.example.a103.datasearch;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 建立材料属性表类代理
 * Created by zhengxiaohu on 2017/3/10.
 */

public class MaterialPropertiesDbAdapter {
    private static final String TAG="MaterialPropertiesDbAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDatabase;

    private static final String SQLITE_TABLE="MaterialPropertiesTable";
    private final Context mContext;

    //材料属性表
    public static final String COL_ID="_id";
    public static final String COL_NAME="name";
    public static final String COL_CATEGORIES="categories";
    public static final String COL_INGREDIENT="ingredient";
    public static final String COL_HARDNESS="hardness";
    public static final String COL_DENSITY="density";
    public static final String COL_THERMALCONDUCTIVITY="thermalConductivity";
    public static final String COL_SPECIFICHEATCAPACITY="specificHeatCapacity";
    public static final String COL_YOUNGMODULUS="youngsModulus";
    public static final String COL_IMPACTSTRENGTH="impactStrength";
    public static final String COL_EXTENSION="extension";
    public static final String COL_AREAREDUCTION="areaReduction";
    public static final String COL_CONDUCTIVECOEFFICIENT="conductiveCoefficient";
    public static final String COL_CONDITION="condition";
    public static final String COL_TENSILESTRENGTH="tensileStrength";
    public static final String COL_YIELDSTRENGTH="yieldStrength";
    public static final String COL_SHEARSTRENGTH="shearStrength";
    public static final String COL_HEATTREATMENT="heatTreatment";
    public static final String COL_LOWMELTINGPOINT="lowMeltingPoint";
    public static final String COL_HIGHMELTINGPOINT="highMeltingPoint";
    public static final String COL_THERMALEXPANSIONCOEFFICIENT="thermalExpansionCoefficient";
    public static final String COL_STANDARD="standard";

    //对应的列索引
    private static final int INDEX_ID=0;
    private static final int INDEX_NAME=INDEX_ID+1;
    private static final int INDEX_CATEGORIES=INDEX_ID+2;
    private static final int INDEX_INGREDIENT=INDEX_ID+3;
    private static final int INDEX_HARDNESS=INDEX_ID+4;
    private static final int INDEX_DENSITY=INDEX_ID+5;
    private static final int INDEX_THERMALCONDUCTIVITY=INDEX_ID+6;
    private static final int INDEX_SPECIFICHEATCAPACITY=INDEX_ID+7;
    private static final int INDEX_YOUNGMODULUS=INDEX_ID+8;
    private static final int INDEX_IMPACTSTRENGTH=INDEX_ID+9;
    private static final int INDEX_EXTENSION=INDEX_ID+10;
    private static final int INDEX_AREAREDUCTION=INDEX_ID+11;
    private static final int INDEX_CONDUCTIVECOEFFICIENT=INDEX_ID+12;
    private static final int INDEX_CONDITION=INDEX_ID+13;
    private static final int INDEX_TENSILESTRENGTH=INDEX_ID+14;
    private static final int INDEX_YIELDSTRENGTH=INDEX_ID+15;
    private static final int INDEX_SHEARSTRENGTH=INDEX_ID+16;
    private static final int INDEX_HEATTREATMENT=INDEX_ID+17;
    private static final int INDEX_LOWMELTINGPOINT=INDEX_ID+18;
    private static final int INDEX_HIGHMELTINGPOINT=INDEX_ID+19;
    private static final int INDEX_THERMALEXPANSIONCOEFFICIENT=INDEX_ID+20;
    private static final int INDEX_STANDARD=INDEX_ID+21;


    public MaterialPropertiesDbAdapter(Context context){
        this.mContext=context;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper{

        /**
         * 调用父类的构造函数进行初始化
         * @param context
         */
        DatabaseHelper(Context context){
            super(context,DataSearchDbAdapter.DATABASE_NAME,null,DataSearchDbAdapter.DATABASE_VERSION);
        }

        /**
         * onCreate(SQLiteDatabase db)只在数据库第一次被创建时执行，数据库已经存在的情况下不会执行。
         * 因此在有多张数据表的情景下，应当创建一个公共的数据库代理DataSearchDbAdapter，各个表的创建都放在该代理中
         * 当需要增加数据表时，建立数据表并将该表添加到公共数据库代理DataSearchDbAdapter中
         * @param db
         */
        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG,"Upgrading database from version "+oldVersion+"to"+
            newVersion+",which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS "+SQLITE_TABLE);
            onCreate(db);
        }
    }

    /**
     * 打开数据库，返回本类MaterialPropertiesDbAdapter的对象
     * @return
     * @throws SQLException
     */
    public MaterialPropertiesDbAdapter open() throws SQLException{
        mDbHelper=new DatabaseHelper(mContext);
        mDatabase=mDbHelper.getWritableDatabase();
        return this;
    }

    //关闭数据库
    public void close(){
        if (mDbHelper!=null){
            mDbHelper.close();
        }
    }

    /**
     * 数据库表MaterialProperties的CRUD操作
     */

    //CREATE
    public void createMaterialProperties(){

    }

    /**
     * 重载函数，获取创建结果
     * @param materialProperties
     * @return
     */
    public long createMaterialProperties(MaterialProperties materialProperties){
        long createResult=0;
        ContentValues values=new ContentValues();
        putMaterialPropertiesToContentValues(values,materialProperties);
        try{
            createResult=mDatabase.insert(SQLITE_TABLE,null,values);
        }catch (Exception e){
            Log.w("TAG","MaterialProperties 创建失败");
        }
        return createResult;
    }

    //READ操作

    /**
     * 根据id查找材料属性
     * @param id
     * @return
     */
  /*  public MaterialProperties fetchMaterialPropertiesById(int id){
        //TODO
        return ;
    }*/

    /**
     * 查找所有的MaterialProperties对象
     * @return
     */
    public Cursor fetchAllMaterialProperties(){
        Cursor mCursor=null;
        //TODO
        return mCursor;
    }

    //DELETE方法，有两种，一种是根据材料属性对象的id来删除，另一种是删除所有的
    public void deleteMaterialPropertiesById(int id){
        // TODO: 2017/3/10
    }

    public void deleteAllMaterialsProperties(){
        // TODO: 2017/3/10
    }

    /**
     * 将材料属性信息以键值对的形式放入ContentValues对象中
     * @param values
     * @param materialProperties
     */
    private void putMaterialPropertiesToContentValues(ContentValues values,MaterialProperties materialProperties){
        // TODO: 2017/3/10
    }
}
