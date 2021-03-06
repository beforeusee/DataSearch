package com.example.a103.datasearch.dao;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.a103.datasearch.data.Material;

import com.example.a103.datasearch.data.MaterialCuttingLimits;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MATERIAL_CUTTING_LIMITS".
*/
public class MaterialCuttingLimitsDao extends AbstractDao<MaterialCuttingLimits, Long> {

    public static final String TABLENAME = "MATERIAL_CUTTING_LIMITS";

    /**
     * Properties of entity MaterialCuttingLimits.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property MaterialId = new Property(1, Long.class, "materialId", false, "MATERIAL_ID");
        public final static Property MinChipThickness = new Property(2, String.class, "minChipThickness", false, "MIN_CHIP_THICKNESS");
        public final static Property MaxChipThickness = new Property(3, String.class, "maxChipThickness", false, "MAX_CHIP_THICKNESS");
        public final static Property MinCuttingSpeed = new Property(4, String.class, "minCuttingSpeed", false, "MIN_CUTTING_SPEED");
        public final static Property MaxCuttingSpeed = new Property(5, String.class, "maxCuttingSpeed", false, "MAX_CUTTING_SPEED");
        public final static Property MinRakeAngle = new Property(6, String.class, "minRakeAngle", false, "MIN_RAKE_ANGLE");
        public final static Property MaxRakeAngle = new Property(7, String.class, "maxRakeAngle", false, "MAX_RAKE_ANGLE");
    }

    private DaoSession daoSession;


    public MaterialCuttingLimitsDao(DaoConfig config) {
        super(config);
    }
    
    public MaterialCuttingLimitsDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MATERIAL_CUTTING_LIMITS\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"MATERIAL_ID\" INTEGER," + // 1: materialId
                "\"MIN_CHIP_THICKNESS\" TEXT," + // 2: minChipThickness
                "\"MAX_CHIP_THICKNESS\" TEXT," + // 3: maxChipThickness
                "\"MIN_CUTTING_SPEED\" TEXT," + // 4: minCuttingSpeed
                "\"MAX_CUTTING_SPEED\" TEXT," + // 5: maxCuttingSpeed
                "\"MIN_RAKE_ANGLE\" TEXT," + // 6: minRakeAngle
                "\"MAX_RAKE_ANGLE\" TEXT);"); // 7: maxRakeAngle
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MATERIAL_CUTTING_LIMITS\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, MaterialCuttingLimits entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long materialId = entity.getMaterialId();
        if (materialId != null) {
            stmt.bindLong(2, materialId);
        }
 
        String minChipThickness = entity.getMinChipThickness();
        if (minChipThickness != null) {
            stmt.bindString(3, minChipThickness);
        }
 
        String maxChipThickness = entity.getMaxChipThickness();
        if (maxChipThickness != null) {
            stmt.bindString(4, maxChipThickness);
        }
 
        String minCuttingSpeed = entity.getMinCuttingSpeed();
        if (minCuttingSpeed != null) {
            stmt.bindString(5, minCuttingSpeed);
        }
 
        String maxCuttingSpeed = entity.getMaxCuttingSpeed();
        if (maxCuttingSpeed != null) {
            stmt.bindString(6, maxCuttingSpeed);
        }
 
        String minRakeAngle = entity.getMinRakeAngle();
        if (minRakeAngle != null) {
            stmt.bindString(7, minRakeAngle);
        }
 
        String maxRakeAngle = entity.getMaxRakeAngle();
        if (maxRakeAngle != null) {
            stmt.bindString(8, maxRakeAngle);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, MaterialCuttingLimits entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long materialId = entity.getMaterialId();
        if (materialId != null) {
            stmt.bindLong(2, materialId);
        }
 
        String minChipThickness = entity.getMinChipThickness();
        if (minChipThickness != null) {
            stmt.bindString(3, minChipThickness);
        }
 
        String maxChipThickness = entity.getMaxChipThickness();
        if (maxChipThickness != null) {
            stmt.bindString(4, maxChipThickness);
        }
 
        String minCuttingSpeed = entity.getMinCuttingSpeed();
        if (minCuttingSpeed != null) {
            stmt.bindString(5, minCuttingSpeed);
        }
 
        String maxCuttingSpeed = entity.getMaxCuttingSpeed();
        if (maxCuttingSpeed != null) {
            stmt.bindString(6, maxCuttingSpeed);
        }
 
        String minRakeAngle = entity.getMinRakeAngle();
        if (minRakeAngle != null) {
            stmt.bindString(7, minRakeAngle);
        }
 
        String maxRakeAngle = entity.getMaxRakeAngle();
        if (maxRakeAngle != null) {
            stmt.bindString(8, maxRakeAngle);
        }
    }

    @Override
    protected final void attachEntity(MaterialCuttingLimits entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public MaterialCuttingLimits readEntity(Cursor cursor, int offset) {
        MaterialCuttingLimits entity = new MaterialCuttingLimits( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // materialId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // minChipThickness
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // maxChipThickness
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // minCuttingSpeed
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // maxCuttingSpeed
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // minRakeAngle
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7) // maxRakeAngle
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, MaterialCuttingLimits entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMaterialId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setMinChipThickness(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setMaxChipThickness(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setMinCuttingSpeed(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setMaxCuttingSpeed(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setMinRakeAngle(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setMaxRakeAngle(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(MaterialCuttingLimits entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(MaterialCuttingLimits entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(MaterialCuttingLimits entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getMaterialDao().getAllColumns());
            builder.append(" FROM MATERIAL_CUTTING_LIMITS T");
            builder.append(" LEFT JOIN MATERIAL T0 ON T.\"MATERIAL_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected MaterialCuttingLimits loadCurrentDeep(Cursor cursor, boolean lock) {
        MaterialCuttingLimits entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Material material = loadCurrentOther(daoSession.getMaterialDao(), cursor, offset);
        entity.setMaterial(material);

        return entity;    
    }

    public MaterialCuttingLimits loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<MaterialCuttingLimits> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<MaterialCuttingLimits> list = new ArrayList<MaterialCuttingLimits>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<MaterialCuttingLimits> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<MaterialCuttingLimits> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
