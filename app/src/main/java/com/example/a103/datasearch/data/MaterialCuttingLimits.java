package com.example.a103.datasearch.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.example.a103.datasearch.dao.DaoSession;
import com.example.a103.datasearch.dao.MaterialDao;
import com.example.a103.datasearch.dao.MaterialCuttingLimitsDao;

/**
 * Created by zhengxiaohu on 2017/3/10.
 * 工件材料的切削限制
 */

@Entity
public class MaterialCuttingLimits {
    //材料极限参数声明
    @Id(autoincrement = true)
    private Long id;

    private Long materialId;            //材料切削极限的外键
    @ToOne(joinProperty = "materialId")
    private Material material;

    private String minChipThickness;   //切屑厚度(最小)
    private String maxChipThickness;   //切屑厚度(最大)
    private String minCuttingSpeed;    //切削速度(最小)
    private String maxCuttingSpeed;    //切削速度(最大)
    private String minRakeAngle;        //前角(最小)
    private String maxRakeAngle;        //前角(最大)

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 666868486)
    private transient MaterialCuttingLimitsDao myDao;
    @Generated(hash = 496611596)
    public MaterialCuttingLimits(Long id, Long materialId, String minChipThickness,
            String maxChipThickness, String minCuttingSpeed, String maxCuttingSpeed,
            String minRakeAngle, String maxRakeAngle) {
        this.id = id;
        this.materialId = materialId;
        this.minChipThickness = minChipThickness;
        this.maxChipThickness = maxChipThickness;
        this.minCuttingSpeed = minCuttingSpeed;
        this.maxCuttingSpeed = maxCuttingSpeed;
        this.minRakeAngle = minRakeAngle;
        this.maxRakeAngle = maxRakeAngle;
    }
    @Generated(hash = 1416812315)
    public MaterialCuttingLimits() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getMaterialId() {
        return this.materialId;
    }
    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }
    public String getMinChipThickness() {
        return this.minChipThickness;
    }
    public void setMinChipThickness(String minChipThickness) {
        this.minChipThickness = minChipThickness;
    }
    public String getMaxChipThickness() {
        return this.maxChipThickness;
    }
    public void setMaxChipThickness(String maxChipThickness) {
        this.maxChipThickness = maxChipThickness;
    }
    public String getMinCuttingSpeed() {
        return this.minCuttingSpeed;
    }
    public void setMinCuttingSpeed(String minCuttingSpeed) {
        this.minCuttingSpeed = minCuttingSpeed;
    }
    public String getMaxCuttingSpeed() {
        return this.maxCuttingSpeed;
    }
    public void setMaxCuttingSpeed(String maxCuttingSpeed) {
        this.maxCuttingSpeed = maxCuttingSpeed;
    }
    public String getMinRakeAngle() {
        return this.minRakeAngle;
    }
    public void setMinRakeAngle(String minRakeAngle) {
        this.minRakeAngle = minRakeAngle;
    }
    public String getMaxRakeAngle() {
        return this.maxRakeAngle;
    }
    public void setMaxRakeAngle(String maxRakeAngle) {
        this.maxRakeAngle = maxRakeAngle;
    }
    @Generated(hash = 812033836)
    private transient Long material__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 199544555)
    public Material getMaterial() {
        Long __key = this.materialId;
        if (material__resolvedKey == null || !material__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MaterialDao targetDao = daoSession.getMaterialDao();
            Material materialNew = targetDao.load(__key);
            synchronized (this) {
                material = materialNew;
                material__resolvedKey = __key;
            }
        }
        return material;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2102404791)
    public void setMaterial(Material material) {
        synchronized (this) {
            this.material = material;
            materialId = material == null ? null : material.getId();
            material__resolvedKey = materialId;
        }
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1004975564)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getMaterialCuttingLimitsDao()
                : null;
    }

}
