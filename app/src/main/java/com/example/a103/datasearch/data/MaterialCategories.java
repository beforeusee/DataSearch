package com.example.a103.datasearch.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.example.a103.datasearch.dao.DaoSession;
import com.example.a103.datasearch.dao.MaterialDao;
import com.example.a103.datasearch.dao.MaterialCategoriesDao;

/**
 * Created by XiaoHu Zheng on 2017/3/17.
 * Email: 1050087728@qq.com
 */

@Entity
public class MaterialCategories {

    @Id(autoincrement = true)
    private Long id;
    @ToMany(referencedJoinProperty = "materialCategoriesId")
    private List<Material> materials;
    private String name;               //材料分类的名称
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 272977076)
    private transient MaterialCategoriesDao myDao;
    @Generated(hash = 964304807)
    public MaterialCategories(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 1567761507)
    public MaterialCategories() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1194840549)
    public List<Material> getMaterials() {
        if (materials == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MaterialDao targetDao = daoSession.getMaterialDao();
            List<Material> materialsNew = targetDao
                    ._queryMaterialCategories_Materials(id);
            synchronized (this) {
                if (materials == null) {
                    materials = materialsNew;
                }
            }
        }
        return materials;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1148495594)
    public synchronized void resetMaterials() {
        materials = null;
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
    @Generated(hash = 915625538)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getMaterialCategoriesDao() : null;
    }
}
