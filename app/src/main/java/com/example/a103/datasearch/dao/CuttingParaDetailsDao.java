package com.example.a103.datasearch.dao;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import com.example.a103.datasearch.data.CuttingParaDetails;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CUTTING_PARA_DETAILS".
*/
public class CuttingParaDetailsDao extends AbstractDao<CuttingParaDetails, Long> {

    public static final String TABLENAME = "CUTTING_PARA_DETAILS";

    /**
     * Properties of entity CuttingParaDetails.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property CuttingConditionsId = new Property(1, Long.class, "cuttingConditionsId", false, "CUTTING_CONDITIONS_ID");
        public final static Property SpindleSpeed = new Property(2, String.class, "spindleSpeed", false, "SPINDLE_SPEED");
        public final static Property FeedRate = new Property(3, String.class, "feedRate", false, "FEED_RATE");
        public final static Property CuttingWidth = new Property(4, String.class, "cuttingWidth", false, "CUTTING_WIDTH");
        public final static Property CuttingDepth = new Property(5, String.class, "cuttingDepth", false, "CUTTING_DEPTH");
        public final static Property FeedPerTooth = new Property(6, String.class, "feedPerTooth", false, "FEED_PER_TOOTH");
        public final static Property CuttingLinearVelocity = new Property(7, String.class, "cuttingLinearVelocity", false, "CUTTING_LINEAR_VELOCITY");
        public final static Property MaxForceY = new Property(8, String.class, "maxForceY", false, "MAX_FORCE_Y");
        public final static Property MaxForceXY = new Property(9, String.class, "maxForceXY", false, "MAX_FORCE_XY");
        public final static Property MaxForceTangential = new Property(10, String.class, "maxForceTangential", false, "MAX_FORCE_TANGENTIAL");
        public final static Property MaxTorque = new Property(11, String.class, "maxTorque", false, "MAX_TORQUE");
        public final static Property MaxCuttingPower = new Property(12, String.class, "maxCuttingPower", false, "MAX_CUTTING_POWER");
        public final static Property MaterialRemovalRate = new Property(13, String.class, "materialRemovalRate", false, "MATERIAL_REMOVAL_RATE");
        public final static Property ToolLife = new Property(14, String.class, "toolLife", false, "TOOL_LIFE");
        public final static Property DataSource = new Property(15, String.class, "dataSource", false, "DATA_SOURCE");
        public final static Property DataMaturity = new Property(16, String.class, "dataMaturity", false, "DATA_MATURITY");
    }

    private Query<CuttingParaDetails> cuttingConditions_MCuttingParaDetailsListQuery;

    public CuttingParaDetailsDao(DaoConfig config) {
        super(config);
    }
    
    public CuttingParaDetailsDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CUTTING_PARA_DETAILS\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"CUTTING_CONDITIONS_ID\" INTEGER," + // 1: cuttingConditionsId
                "\"SPINDLE_SPEED\" TEXT," + // 2: spindleSpeed
                "\"FEED_RATE\" TEXT," + // 3: feedRate
                "\"CUTTING_WIDTH\" TEXT," + // 4: cuttingWidth
                "\"CUTTING_DEPTH\" TEXT," + // 5: cuttingDepth
                "\"FEED_PER_TOOTH\" TEXT," + // 6: feedPerTooth
                "\"CUTTING_LINEAR_VELOCITY\" TEXT," + // 7: cuttingLinearVelocity
                "\"MAX_FORCE_Y\" TEXT," + // 8: maxForceY
                "\"MAX_FORCE_XY\" TEXT," + // 9: maxForceXY
                "\"MAX_FORCE_TANGENTIAL\" TEXT," + // 10: maxForceTangential
                "\"MAX_TORQUE\" TEXT," + // 11: maxTorque
                "\"MAX_CUTTING_POWER\" TEXT," + // 12: maxCuttingPower
                "\"MATERIAL_REMOVAL_RATE\" TEXT," + // 13: materialRemovalRate
                "\"TOOL_LIFE\" TEXT," + // 14: toolLife
                "\"DATA_SOURCE\" TEXT," + // 15: dataSource
                "\"DATA_MATURITY\" TEXT);"); // 16: dataMaturity
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CUTTING_PARA_DETAILS\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CuttingParaDetails entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long cuttingConditionsId = entity.getCuttingConditionsId();
        if (cuttingConditionsId != null) {
            stmt.bindLong(2, cuttingConditionsId);
        }
 
        String spindleSpeed = entity.getSpindleSpeed();
        if (spindleSpeed != null) {
            stmt.bindString(3, spindleSpeed);
        }
 
        String feedRate = entity.getFeedRate();
        if (feedRate != null) {
            stmt.bindString(4, feedRate);
        }
 
        String cuttingWidth = entity.getCuttingWidth();
        if (cuttingWidth != null) {
            stmt.bindString(5, cuttingWidth);
        }
 
        String cuttingDepth = entity.getCuttingDepth();
        if (cuttingDepth != null) {
            stmt.bindString(6, cuttingDepth);
        }
 
        String feedPerTooth = entity.getFeedPerTooth();
        if (feedPerTooth != null) {
            stmt.bindString(7, feedPerTooth);
        }
 
        String cuttingLinearVelocity = entity.getCuttingLinearVelocity();
        if (cuttingLinearVelocity != null) {
            stmt.bindString(8, cuttingLinearVelocity);
        }
 
        String maxForceY = entity.getMaxForceY();
        if (maxForceY != null) {
            stmt.bindString(9, maxForceY);
        }
 
        String maxForceXY = entity.getMaxForceXY();
        if (maxForceXY != null) {
            stmt.bindString(10, maxForceXY);
        }
 
        String maxForceTangential = entity.getMaxForceTangential();
        if (maxForceTangential != null) {
            stmt.bindString(11, maxForceTangential);
        }
 
        String maxTorque = entity.getMaxTorque();
        if (maxTorque != null) {
            stmt.bindString(12, maxTorque);
        }
 
        String maxCuttingPower = entity.getMaxCuttingPower();
        if (maxCuttingPower != null) {
            stmt.bindString(13, maxCuttingPower);
        }
 
        String materialRemovalRate = entity.getMaterialRemovalRate();
        if (materialRemovalRate != null) {
            stmt.bindString(14, materialRemovalRate);
        }
 
        String toolLife = entity.getToolLife();
        if (toolLife != null) {
            stmt.bindString(15, toolLife);
        }
 
        String dataSource = entity.getDataSource();
        if (dataSource != null) {
            stmt.bindString(16, dataSource);
        }
 
        String dataMaturity = entity.getDataMaturity();
        if (dataMaturity != null) {
            stmt.bindString(17, dataMaturity);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CuttingParaDetails entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long cuttingConditionsId = entity.getCuttingConditionsId();
        if (cuttingConditionsId != null) {
            stmt.bindLong(2, cuttingConditionsId);
        }
 
        String spindleSpeed = entity.getSpindleSpeed();
        if (spindleSpeed != null) {
            stmt.bindString(3, spindleSpeed);
        }
 
        String feedRate = entity.getFeedRate();
        if (feedRate != null) {
            stmt.bindString(4, feedRate);
        }
 
        String cuttingWidth = entity.getCuttingWidth();
        if (cuttingWidth != null) {
            stmt.bindString(5, cuttingWidth);
        }
 
        String cuttingDepth = entity.getCuttingDepth();
        if (cuttingDepth != null) {
            stmt.bindString(6, cuttingDepth);
        }
 
        String feedPerTooth = entity.getFeedPerTooth();
        if (feedPerTooth != null) {
            stmt.bindString(7, feedPerTooth);
        }
 
        String cuttingLinearVelocity = entity.getCuttingLinearVelocity();
        if (cuttingLinearVelocity != null) {
            stmt.bindString(8, cuttingLinearVelocity);
        }
 
        String maxForceY = entity.getMaxForceY();
        if (maxForceY != null) {
            stmt.bindString(9, maxForceY);
        }
 
        String maxForceXY = entity.getMaxForceXY();
        if (maxForceXY != null) {
            stmt.bindString(10, maxForceXY);
        }
 
        String maxForceTangential = entity.getMaxForceTangential();
        if (maxForceTangential != null) {
            stmt.bindString(11, maxForceTangential);
        }
 
        String maxTorque = entity.getMaxTorque();
        if (maxTorque != null) {
            stmt.bindString(12, maxTorque);
        }
 
        String maxCuttingPower = entity.getMaxCuttingPower();
        if (maxCuttingPower != null) {
            stmt.bindString(13, maxCuttingPower);
        }
 
        String materialRemovalRate = entity.getMaterialRemovalRate();
        if (materialRemovalRate != null) {
            stmt.bindString(14, materialRemovalRate);
        }
 
        String toolLife = entity.getToolLife();
        if (toolLife != null) {
            stmt.bindString(15, toolLife);
        }
 
        String dataSource = entity.getDataSource();
        if (dataSource != null) {
            stmt.bindString(16, dataSource);
        }
 
        String dataMaturity = entity.getDataMaturity();
        if (dataMaturity != null) {
            stmt.bindString(17, dataMaturity);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public CuttingParaDetails readEntity(Cursor cursor, int offset) {
        CuttingParaDetails entity = new CuttingParaDetails( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // cuttingConditionsId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // spindleSpeed
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // feedRate
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // cuttingWidth
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // cuttingDepth
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // feedPerTooth
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // cuttingLinearVelocity
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // maxForceY
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // maxForceXY
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // maxForceTangential
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // maxTorque
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // maxCuttingPower
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // materialRemovalRate
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // toolLife
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // dataSource
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16) // dataMaturity
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, CuttingParaDetails entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCuttingConditionsId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setSpindleSpeed(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setFeedRate(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setCuttingWidth(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setCuttingDepth(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setFeedPerTooth(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setCuttingLinearVelocity(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setMaxForceY(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setMaxForceXY(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setMaxForceTangential(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setMaxTorque(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setMaxCuttingPower(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setMaterialRemovalRate(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setToolLife(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setDataSource(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setDataMaturity(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(CuttingParaDetails entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(CuttingParaDetails entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(CuttingParaDetails entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "mCuttingParaDetailsList" to-many relationship of CuttingConditions. */
    public List<CuttingParaDetails> _queryCuttingConditions_MCuttingParaDetailsList(Long cuttingConditionsId) {
        synchronized (this) {
            if (cuttingConditions_MCuttingParaDetailsListQuery == null) {
                QueryBuilder<CuttingParaDetails> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.CuttingConditionsId.eq(null));
                cuttingConditions_MCuttingParaDetailsListQuery = queryBuilder.build();
            }
        }
        Query<CuttingParaDetails> query = cuttingConditions_MCuttingParaDetailsListQuery.forCurrentThread();
        query.setParameter(0, cuttingConditionsId);
        return query.list();
    }

}
