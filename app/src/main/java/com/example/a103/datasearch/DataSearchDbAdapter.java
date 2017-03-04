package com.example.a103.datasearch;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by zhengxiaohu on 2017/2/24.
 * 创建数据库的代理DataSearchDbAdapter，把简单的应用调用转换为底层的SQLite API调用
 */

public class DataSearchDbAdapter {
    private static final String DATABASE_NAME="dataSearchDatabase";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME_TOOL="tool";

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    private final Context mContext;

    //刀具表的列名称
    public static final String COL_ID="_id";
    public static final String COL_NAME="name";
    public static final String COL_TYPE="type";
    public static final String COL_SERIAL="serial";
    public static final String COL_BRAND="brand";
    public static final String COL_CUTTINGDIAMETER="cuttingDiameter";
    public static final String COL_DEPTHOFCUTMAXIMUM="depthOfCutMaximum";
    public static final String COL_MAXRAMPINGANGLE="maxRampingAngle";
    public static final String COL_USABLELENGTH="usableLength";
    public static final String COL_TEETHNUM="peripheralEffectiveCuttingEdgeCount";
    public static final String COL_TOOLADAPTER="adaptiveInterfaceMachineDirection";
    public static final String COL_CONNECTIONDIAMETERTOLERANCE="connectionDiameterTolerance";
    public static final String COL_GRADE="grade";
    public static final String COL_SUBSTRATE="substrate";
    public static final String COL_COATING="coating";
    public static final String COL_BASICSTANDARDGROUP="basicStandardGroup";
    public static final String COL_COOLANTENTRYSTYLECODE="coolantEntryStyleCode";
    public static final String COL_CONNECTIONDIAMETER="connectionDiameter";
    public static final String COL_FUNCTIONALLENGTH="functionalLength";
    public static final String COL_FLUTEHELIXANGLE="fluteHelixAngle";
    public static final String COL_RADIALRAKEANGLE="radialRakeAngle";
    public static final String COL_AXIALRAKEANGLE="axialRakeAngle";
    public static final String COL_MAXIMUMREGRINDS="maximumRegrinds";
    public static final String COL_MAXROTATIONSPEED="maxRotationSpeed";
    public static final String COL_WEIGHT="weight";
    public static final String COL_LIFECYCLESTATE="lifeCycleState";
    public static final String COL_SUITABLEFORMATERIAL="suitableForMaterial";
    public static final String COL_APPLICATION="application";
    public static final String COL_USED="used";

    //对应的列索引
    private static final int INDEX_ID=0;
    private static final int INDEX_NAME=INDEX_ID+1;
    private static final int INDEX_TYPE=INDEX_ID+2;
    private static final int INDEX_SERIAL=INDEX_ID+3;
    private static final int INDEX_BRAND=INDEX_ID+4;
    private static final int INDEX_CUTTINGDIAMETER=INDEX_ID+5;
    private static final int INDEX_DEPTHOFCUTMAXIMUM=INDEX_ID+6;
    private static final int INDEX_MAXRAMPINGANGLE=INDEX_ID+7;
    private static final int INDEX_USABLELENGTH=INDEX_ID+8;
    private static final int INDEX_TEETHNUM=INDEX_ID+9;
    private static final int INDEX_TOOLADAPTER=INDEX_ID+10;
    private static final int INDEX_CONNECTIONDIAMETERTOLERANCE=INDEX_ID+11;
    private static final int INDEX_GRADE=INDEX_ID+12;
    private static final int INDEX_SUBSTRATE=INDEX_ID+13;
    private static final int INDEX_COATING=INDEX_ID+14;
    private static final int INDEX_BASICSTANDARDGROUP=INDEX_ID+15;
    private static final int INDEX_COOLANTENTRYSTYLECODE=INDEX_ID+16;
    private static final int INDEX_CONNECTIONDIAMETER=INDEX_ID+17;
    private static final int INDEX_FUNCTIONALLENGTH=INDEX_ID+18;
    private static final int INDEX_FLUTEHELIXANGLE=INDEX_ID+19;
    private static final int INDEX_RADIALRAKEANGLE=INDEX_ID+20;
    private static final int INDEX_AXIALRAKEANGLE=INDEX_ID+21;
    private static final int INDEX_MAXIMUMREGRINDS=INDEX_ID+22;
    private static final int INDEX_MAXROTATIONSPEED=INDEX_ID+23;
    private static final int INDEX_WEIGHT=INDEX_ID+24;
    private static final int INDEX_LIFECYCLESTATE=INDEX_ID+25;
    private static final int INDEX_SUITABLEFORMATERIAL=INDEX_ID+26;
    private static final int INDEX_APPLICATION=INDEX_ID+27;
    private static final int INDEX_USED=INDEX_ID+28;

    //用于日志
    private static final String TAG="DataSearchDbAdapter";

    //SQL statement used to create the database
    private static final String DATABASE_CREATE_TOOL="CREATE TABLE IF NOT EXISTS "+TABLE_NAME_TOOL+ " ( "+
            COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            COL_NAME+" TEXT, "+
            COL_TYPE+" TEXT, "+
            COL_SERIAL+" TEXT, "+
            COL_BRAND+" TEXT, "+
            COL_CUTTINGDIAMETER+" TEXT, "+
            COL_DEPTHOFCUTMAXIMUM+" TEXT, "+
            COL_MAXRAMPINGANGLE+" TEXT, "+
            COL_USABLELENGTH+" TEXT, "+
            COL_TEETHNUM+" TEXT, "+
            COL_TOOLADAPTER+" TEXT, "+
            COL_CONNECTIONDIAMETERTOLERANCE+" TEXT, "+
            COL_GRADE+" TEXT, "+
            COL_SUBSTRATE+" TEXT, "+
            COL_COATING+" TEXT, "+
            COL_BASICSTANDARDGROUP+" TEXT, "+
            COL_COOLANTENTRYSTYLECODE+" TEXT, "+
            COL_CONNECTIONDIAMETER+" TEXT, "+
            COL_FUNCTIONALLENGTH+" TEXT, "+
            COL_FLUTEHELIXANGLE+" TEXT, "+
            COL_RADIALRAKEANGLE+" TEXT, "+
            COL_AXIALRAKEANGLE+" TEXT, "+
            COL_MAXIMUMREGRINDS+" TEXT, "+
            COL_MAXROTATIONSPEED+" TEXT, "+
            COL_WEIGHT+" TEXT, "+
            COL_LIFECYCLESTATE+" TEXT, "+
            COL_SUITABLEFORMATERIAL+" TEXT, "+
            COL_APPLICATION+" TEXT, "+
            COL_USED+" INTEGER );";

    /**
     * DatabaseHelper—是一个用于打开和关闭数据库的SQLite API的类，被定义为内部类，它使用Context，
     * Context是一个Android抽象类，用以提供对Android操作系统的访问。
     */
    private static class DatabaseHelper extends SQLiteOpenHelper{

        DatabaseHelper(Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }

        //创建数据库
        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.w(TAG,DATABASE_CREATE_TOOL);
            db.execSQL(DATABASE_CREATE_TOOL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG,"Upgrading database from version"+oldVersion+" to "+newVersion+",which will " +
                    "destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_TOOL);
            onCreate(db);
        }
    }


    public DataSearchDbAdapter(Context context){
        this.mContext=context;
    }

    //数据库的open方法
    public void open() throws SQLException{
        mDbHelper=new DatabaseHelper(mContext);
        Log.w("mDbHelper是否为空", String.valueOf((mDbHelper!=null)));
        mDb=mDbHelper.getWritableDatabase();
        Log.w("mDb不为空",String.valueOf(mDb!=null));
    }

    //数据库的close方法
    public void close(){
        if (mDbHelper!=null){
            mDbHelper.close();
        }
    }

    /**
     * 数据库的CRUD操作；CRUD表示创建，读取，更新，删除
     */

    //CREATE
    public void createTool(String name, String type, String serial, String brand, String cuttingDiameter,
                           String depthOfCutMaximum, String maxRampingAngle, String usableLength,
                           String peripheralEffectiveCuttingEdgeCount, String adaptiveInterfaceMachineDirection,
                           String connectionDiameterTolerance, String grade, String substrate, String coating,
                           String basicStandardGroup, String coolantEntryStyleCode, String connectionDiameter,
                           String functionalLength, String fluteHelixAngle, String radialRakeAngle,
                           String axialRakeAngle, String maximumRegrinds, String maxRotationalSpeed, String weight,
                           String lifeCycleState, String suitableForMaterial, String application,boolean used){
        ContentValues values=new ContentValues();
        values.put(COL_NAME,name);
        values.put(COL_TYPE,type);
        values.put(COL_SERIAL,serial);
        values.put(COL_BRAND,brand);
        values.put(COL_CUTTINGDIAMETER,cuttingDiameter);
        values.put(COL_DEPTHOFCUTMAXIMUM,depthOfCutMaximum);
        values.put(COL_MAXRAMPINGANGLE,maxRampingAngle);
        values.put(COL_USABLELENGTH,usableLength);
        values.put(COL_TEETHNUM,peripheralEffectiveCuttingEdgeCount);
        values.put(COL_TOOLADAPTER,adaptiveInterfaceMachineDirection);
        values.put(COL_CONNECTIONDIAMETERTOLERANCE,connectionDiameterTolerance);
        values.put(COL_GRADE,grade);
        values.put(COL_SUBSTRATE,substrate);
        values.put(COL_COATING,coating);
        values.put(COL_BASICSTANDARDGROUP,basicStandardGroup);
        values.put(COL_COOLANTENTRYSTYLECODE,coolantEntryStyleCode);
        values.put(COL_CONNECTIONDIAMETER,connectionDiameter);
        values.put(COL_FUNCTIONALLENGTH,functionalLength);
        values.put(COL_FLUTEHELIXANGLE,fluteHelixAngle);
        values.put(COL_RADIALRAKEANGLE,radialRakeAngle);
        values.put(COL_AXIALRAKEANGLE,axialRakeAngle);
        values.put(COL_MAXIMUMREGRINDS,maximumRegrinds);
        values.put(COL_MAXROTATIONSPEED,maxRotationalSpeed);
        values.put(COL_WEIGHT,weight);
        values.put(COL_LIFECYCLESTATE,lifeCycleState);
        values.put(COL_SUITABLEFORMATERIAL,suitableForMaterial);
        values.put(COL_APPLICATION,application);
        values.put(COL_USED,used ?1:0);

        Log.w("刀具名称:",name);
        mDb.insert(TABLE_NAME_TOOL,null,values);
    }

    //overload to take a tool
    public long createTool(Tool tool){
        ContentValues values=new ContentValues();
        putToolToContentValues(values, tool);

        //Inserting Row
        return mDb.insert(TABLE_NAME_TOOL,null,values);
    }

    /**
     * 数据库的查询操作READ,有根据ID查找，和查找全部
     * @param id
     * @return
     */
    //fetch tool by id
    public Tool fecthToolById(int id){
        Cursor cursor=mDb.query(TABLE_NAME_TOOL,new String[]{COL_ID,COL_NAME,COL_TYPE,COL_SERIAL,COL_BRAND,
        COL_CUTTINGDIAMETER,COL_DEPTHOFCUTMAXIMUM,COL_MAXRAMPINGANGLE,COL_USABLELENGTH,COL_TEETHNUM,
        COL_TOOLADAPTER,COL_CONNECTIONDIAMETERTOLERANCE,COL_GRADE,COL_SUBSTRATE,COL_COATING,
        COL_BASICSTANDARDGROUP,COL_COOLANTENTRYSTYLECODE,COL_CONNECTIONDIAMETER,COL_FUNCTIONALLENGTH,
        COL_FLUTEHELIXANGLE,COL_RADIALRAKEANGLE,COL_AXIALRAKEANGLE,COL_MAXIMUMREGRINDS,
                COL_MAXROTATIONSPEED,COL_WEIGHT, COL_LIFECYCLESTATE,COL_SUITABLEFORMATERIAL,
                COL_APPLICATION,COL_USED},COL_ID+"=?",new String[]{String.valueOf(id)},null,null,null,null);

        if (cursor!=null){
            cursor.moveToFirst();
        }
        return new Tool(cursor.getInt(INDEX_ID),cursor.getString(INDEX_NAME),cursor.getString(INDEX_TYPE),
                cursor.getString(INDEX_SERIAL),cursor.getString(INDEX_BRAND),
                cursor.getString(INDEX_CUTTINGDIAMETER),cursor.getString(INDEX_DEPTHOFCUTMAXIMUM),
                cursor.getString(INDEX_MAXRAMPINGANGLE),cursor.getString(INDEX_USABLELENGTH),
                cursor.getString(INDEX_TEETHNUM),cursor.getString(INDEX_TOOLADAPTER),
                cursor.getString(INDEX_CONNECTIONDIAMETERTOLERANCE),cursor.getString(INDEX_GRADE),
                cursor.getString(INDEX_SUBSTRATE),cursor.getString(INDEX_COATING),
                cursor.getString(INDEX_BASICSTANDARDGROUP),cursor.getString(INDEX_COOLANTENTRYSTYLECODE),
                cursor.getString(INDEX_CONNECTIONDIAMETER),cursor.getString(INDEX_FUNCTIONALLENGTH),
                cursor.getString(INDEX_FLUTEHELIXANGLE),cursor.getString(INDEX_RADIALRAKEANGLE),
                cursor.getString(INDEX_AXIALRAKEANGLE),cursor.getString(INDEX_MAXIMUMREGRINDS),
                cursor.getString(INDEX_MAXROTATIONSPEED),cursor.getString(INDEX_WEIGHT),
                cursor.getString(INDEX_LIFECYCLESTATE),cursor.getString(INDEX_SUITABLEFORMATERIAL),
                cursor.getString(INDEX_APPLICATION),cursor.getInt(INDEX_USED)
                );
    }

    //fetch all tools
    public Cursor fetchAllTools() {
        Cursor mCursor = mDb.query(TABLE_NAME_TOOL, new String[]{COL_ID, COL_NAME, COL_TYPE, COL_SERIAL,
                COL_BRAND, COL_CUTTINGDIAMETER, COL_DEPTHOFCUTMAXIMUM, COL_MAXRAMPINGANGLE,
                COL_USABLELENGTH, COL_TEETHNUM, COL_TOOLADAPTER, COL_CONNECTIONDIAMETERTOLERANCE,
                COL_GRADE, COL_SUBSTRATE, COL_COATING, COL_BASICSTANDARDGROUP, COL_COOLANTENTRYSTYLECODE,
                COL_CONNECTIONDIAMETER, COL_FUNCTIONALLENGTH, COL_FLUTEHELIXANGLE, COL_RADIALRAKEANGLE,
                COL_AXIALRAKEANGLE, COL_MAXIMUMREGRINDS, COL_MAXROTATIONSPEED, COL_WEIGHT,
                COL_LIFECYCLESTATE, COL_SUITABLEFORMATERIAL,
                COL_APPLICATION,COL_USED}, null, null, null, null, null);

        if (mCursor!=null){
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //UPDATE
    public void updateTool(Tool tool){
        ContentValues values=new ContentValues();
        putToolToContentValues(values, tool);

        mDb.update(TABLE_NAME_TOOL,values,COL_ID+"=?",new String[]{String.valueOf(tool.getId())});
    }

    /**
     * DELETE 方法，有两种，一种是根据nId来删除刀具，另一种是删除所有的刀具数据
     * @param nId
     */
    //delete tools by id
    public void deleteToolsById(int nId){
        mDb.delete(TABLE_NAME_TOOL,COL_ID+"=?",new String[]{String.valueOf(nId)});
    }

    //delete all tools
    public void deleteAllTools(){
        mDb.delete(TABLE_NAME_TOOL,null,null);
    }

    /**
     * 功能：把刀具信息放入ContentValues的对象values中，values以键值对的形式储存Tools的信息
     * @param values
     * @param tool
     */
    private void putToolToContentValues(ContentValues values, Tool tool) {
        values.put(COL_NAME,tool.getName());
        values.put(COL_TYPE,tool.getType());
        values.put(COL_SERIAL,tool.getSerial());
        values.put(COL_BRAND,tool.getBrand());
        values.put(COL_CUTTINGDIAMETER,tool.getCuttingDiameter());
        values.put(COL_DEPTHOFCUTMAXIMUM,tool.getDepthOfCutMaximum());
        values.put(COL_MAXRAMPINGANGLE,tool.getMaxRampingAngle());
        values.put(COL_USABLELENGTH,tool.getUsableLength());
        values.put(COL_TEETHNUM,tool.getPeripheralEffectiveCuttingEdgeCount());
        values.put(COL_TOOLADAPTER,tool.getAdaptiveInterfaceMachineDirection());
        values.put(COL_CONNECTIONDIAMETERTOLERANCE,tool.getConnectionDiameterTolerance());
        values.put(COL_GRADE,tool.getGrade());
        values.put(COL_SUBSTRATE,tool.getSubstrate());
        values.put(COL_COATING,tool.getCoating());
        values.put(COL_BASICSTANDARDGROUP,tool.getBasicStandardGroup());
        values.put(COL_COOLANTENTRYSTYLECODE,tool.getCoolantEntryStyleCode());
        values.put(COL_CONNECTIONDIAMETER,tool.getConnectionDiameter());
        values.put(COL_FUNCTIONALLENGTH,tool.getFunctionalLength());
        values.put(COL_FLUTEHELIXANGLE,tool.getFluteHelixAngle());
        values.put(COL_RADIALRAKEANGLE,tool.getRadialRakeAngle());
        values.put(COL_AXIALRAKEANGLE,tool.getAxialRakeAngle());
        values.put(COL_MAXIMUMREGRINDS,tool.getMaximumRegrinds());
        values.put(COL_MAXROTATIONSPEED,tool.getMaxRotationalSpeed());
        values.put(COL_WEIGHT,tool.getWeight());
        values.put(COL_LIFECYCLESTATE,tool.getLifeCycleState());
        values.put(COL_SUITABLEFORMATERIAL,tool.getSuitableForMaterial());
        values.put(COL_APPLICATION,tool.getApplication());
        values.put(COL_USED,tool.getUsed());
    }
}
