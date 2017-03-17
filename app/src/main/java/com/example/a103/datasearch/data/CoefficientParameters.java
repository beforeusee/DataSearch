package com.example.a103.datasearch.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.example.a103.datasearch.dao.DaoSession;
import com.example.a103.datasearch.dao.MaterialDao;
import com.example.a103.datasearch.dao.CoefficientParametersDao;

/**
 * Created by zhengxiaohu on 2017/3/10.
 * 切削力系数参数
 */

@Entity
public class CoefficientParameters {
    //切削力系数参数变量
    @Id(autoincrement = true)
    private Long id;                     //主键:切削力系数参数实体类的Id

    private Long materialId;            //外键
    @ToOne(joinProperty = "materialId")
    private Material material;

    private String forceModel;          //力模型
    private String Kte;                  //切向刃口力系数
    private String Kre;                  //径向刃口力系数
    private String Kae;                  //轴向刃口力系数
    private String Ktc;                  //切向铣削力系数
    private String Krc;                  //径向铣削力系数
    private String Kac;                  //轴向铣削力系数

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 14248405)
    private transient CoefficientParametersDao myDao;
    @Generated(hash = 2110514178)
    public CoefficientParameters(Long id, Long materialId, String forceModel,
            String Kte, String Kre, String Kae, String Ktc, String Krc,
            String Kac) {
        this.id = id;
        this.materialId = materialId;
        this.forceModel = forceModel;
        this.Kte = Kte;
        this.Kre = Kre;
        this.Kae = Kae;
        this.Ktc = Ktc;
        this.Krc = Krc;
        this.Kac = Kac;
    }
    @Generated(hash = 1071960495)
    public CoefficientParameters() {
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
    public String getForceModel() {
        return this.forceModel;
    }
    public void setForceModel(String forceModel) {
        this.forceModel = forceModel;
    }
    public String getKte() {
        return this.Kte;
    }
    public void setKte(String Kte) {
        this.Kte = Kte;
    }
    public String getKre() {
        return this.Kre;
    }
    public void setKre(String Kre) {
        this.Kre = Kre;
    }
    public String getKae() {
        return this.Kae;
    }
    public void setKae(String Kae) {
        this.Kae = Kae;
    }
    public String getKtc() {
        return this.Ktc;
    }
    public void setKtc(String Ktc) {
        this.Ktc = Ktc;
    }
    public String getKrc() {
        return this.Krc;
    }
    public void setKrc(String Krc) {
        this.Krc = Krc;
    }
    public String getKac() {
        return this.Kac;
    }
    public void setKac(String Kac) {
        this.Kac = Kac;
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
    @Generated(hash = 1109243900)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCoefficientParametersDao()
                : null;
    }

}
