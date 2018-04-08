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

import com.example.a103.datasearch.data.CoefficientParameters;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "COEFFICIENT_PARAMETERS".
*/
public class CoefficientParametersDao extends AbstractDao<CoefficientParameters, Long> {

    public static final String TABLENAME = "COEFFICIENT_PARAMETERS";

    /**
     * Properties of entity CoefficientParameters.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property MaterialId = new Property(1, Long.class, "materialId", false, "MATERIAL_ID");
        public final static Property ForceModel = new Property(2, String.class, "forceModel", false, "FORCE_MODEL");
        public final static Property Kte = new Property(3, String.class, "Kte", false, "KTE");
        public final static Property Kre = new Property(4, String.class, "Kre", false, "KRE");
        public final static Property Kae = new Property(5, String.class, "Kae", false, "KAE");
        public final static Property Ktc = new Property(6, String.class, "Ktc", false, "KTC");
        public final static Property Krc = new Property(7, String.class, "Krc", false, "KRC");
        public final static Property Kac = new Property(8, String.class, "Kac", false, "KAC");
    }

    private DaoSession daoSession;


    public CoefficientParametersDao(DaoConfig config) {
        super(config);
    }
    
    public CoefficientParametersDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"COEFFICIENT_PARAMETERS\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"MATERIAL_ID\" INTEGER," + // 1: materialId
                "\"FORCE_MODEL\" TEXT," + // 2: forceModel
                "\"KTE\" TEXT," + // 3: Kte
                "\"KRE\" TEXT," + // 4: Kre
                "\"KAE\" TEXT," + // 5: Kae
                "\"KTC\" TEXT," + // 6: Ktc
                "\"KRC\" TEXT," + // 7: Krc
                "\"KAC\" TEXT);"); // 8: Kac
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"COEFFICIENT_PARAMETERS\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CoefficientParameters entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long materialId = entity.getMaterialId();
        if (materialId != null) {
            stmt.bindLong(2, materialId);
        }
 
        String forceModel = entity.getForceModel();
        if (forceModel != null) {
            stmt.bindString(3, forceModel);
        }
 
        String Kte = entity.getKte();
        if (Kte != null) {
            stmt.bindString(4, Kte);
        }
 
        String Kre = entity.getKre();
        if (Kre != null) {
            stmt.bindString(5, Kre);
        }
 
        String Kae = entity.getKae();
        if (Kae != null) {
            stmt.bindString(6, Kae);
        }
 
        String Ktc = entity.getKtc();
        if (Ktc != null) {
            stmt.bindString(7, Ktc);
        }
 
        String Krc = entity.getKrc();
        if (Krc != null) {
            stmt.bindString(8, Krc);
        }
 
        String Kac = entity.getKac();
        if (Kac != null) {
            stmt.bindString(9, Kac);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CoefficientParameters entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long materialId = entity.getMaterialId();
        if (materialId != null) {
            stmt.bindLong(2, materialId);
        }
 
        String forceModel = entity.getForceModel();
        if (forceModel != null) {
            stmt.bindString(3, forceModel);
        }
 
        String Kte = entity.getKte();
        if (Kte != null) {
            stmt.bindString(4, Kte);
        }
 
        String Kre = entity.getKre();
        if (Kre != null) {
            stmt.bindString(5, Kre);
        }
 
        String Kae = entity.getKae();
        if (Kae != null) {
            stmt.bindString(6, Kae);
        }
 
        String Ktc = entity.getKtc();
        if (Ktc != null) {
            stmt.bindString(7, Ktc);
        }
 
        String Krc = entity.getKrc();
        if (Krc != null) {
            stmt.bindString(8, Krc);
        }
 
        String Kac = entity.getKac();
        if (Kac != null) {
            stmt.bindString(9, Kac);
        }
    }

    @Override
    protected final void attachEntity(CoefficientParameters entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public CoefficientParameters readEntity(Cursor cursor, int offset) {
        CoefficientParameters entity = new CoefficientParameters( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // materialId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // forceModel
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // Kte
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // Kre
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // Kae
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // Ktc
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // Krc
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // Kac
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, CoefficientParameters entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMaterialId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setForceModel(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setKte(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setKre(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setKae(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setKtc(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setKrc(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setKac(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(CoefficientParameters entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(CoefficientParameters entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(CoefficientParameters entity) {
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
            builder.append(" FROM COEFFICIENT_PARAMETERS T");
            builder.append(" LEFT JOIN MATERIAL T0 ON T.\"MATERIAL_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected CoefficientParameters loadCurrentDeep(Cursor cursor, boolean lock) {
        CoefficientParameters entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Material material = loadCurrentOther(daoSession.getMaterialDao(), cursor, offset);
        entity.setMaterial(material);

        return entity;    
    }

    public CoefficientParameters loadDeep(Long key) {
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
    public List<CoefficientParameters> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<CoefficientParameters> list = new ArrayList<CoefficientParameters>(count);
        
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
    
    protected List<CoefficientParameters> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<CoefficientParameters> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
