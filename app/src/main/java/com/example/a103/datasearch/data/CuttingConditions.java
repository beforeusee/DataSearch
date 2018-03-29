package com.example.a103.datasearch.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.example.a103.datasearch.dao.DaoSession;
import com.example.a103.datasearch.dao.CuttingParaDetailsDao;
import com.example.a103.datasearch.dao.CuttingConditionsDao;

/**
 * Created by XiaoHu Zheng on 2018/3/8.
 * Email: 1050087728@qq.com
 * 切削参数与机床，刀具，材料及切削条件有关，在查询时，应联合机床，刀具及材料组合查询
 */

@Entity
public class CuttingConditions {

    //The key of cuttingParameters
    @Id(autoincrement = true)
    private Long id;

    @ToMany(referencedJoinProperty = "cuttingConditionsId")
    private List<CuttingParaDetails> mCuttingParaDetailsList;

    //machine info机床信息
    private String machineInfo;

    //material info材料信息
    private String materialInfo;

    //tool info刀具信息
    private String toolInfo;

    //刀具悬长
    private String toolSuspensionLen;

    //切削方式
    private String cuttingMethod;

    //切削方向
    private String cuttingDirection;

    //工件类型
    private String workpieceType;

    //加工阶段
    private String processStep;

    //加工型面
    private String processSurface;

    //工件说明
    private String workpieceRemark;

    //机床-刀具模态参数信息,仅保留三阶模态信息
    private String modeX1;
    private String modeX2;
    private String modeX3;
    private String modeY1;
    private String modeY2;
    private String modeY3;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1650178083)
    private transient CuttingConditionsDao myDao;
    @Generated(hash = 1062189637)
    public CuttingConditions(Long id, String machineInfo, String materialInfo,
            String toolInfo, String toolSuspensionLen, String cuttingMethod,
            String cuttingDirection, String workpieceType, String processStep,
            String processSurface, String workpieceRemark, String modeX1,
            String modeX2, String modeX3, String modeY1, String modeY2,
            String modeY3) {
        this.id = id;
        this.machineInfo = machineInfo;
        this.materialInfo = materialInfo;
        this.toolInfo = toolInfo;
        this.toolSuspensionLen = toolSuspensionLen;
        this.cuttingMethod = cuttingMethod;
        this.cuttingDirection = cuttingDirection;
        this.workpieceType = workpieceType;
        this.processStep = processStep;
        this.processSurface = processSurface;
        this.workpieceRemark = workpieceRemark;
        this.modeX1 = modeX1;
        this.modeX2 = modeX2;
        this.modeX3 = modeX3;
        this.modeY1 = modeY1;
        this.modeY2 = modeY2;
        this.modeY3 = modeY3;
    }
    @Generated(hash = 573482468)
    public CuttingConditions() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMachineInfo() {
        return this.machineInfo;
    }
    public void setMachineInfo(String machineInfo) {
        this.machineInfo = machineInfo;
    }
    public String getMaterialInfo() {
        return this.materialInfo;
    }
    public void setMaterialInfo(String materialInfo) {
        this.materialInfo = materialInfo;
    }
    public String getToolInfo() {
        return this.toolInfo;
    }
    public void setToolInfo(String toolInfo) {
        this.toolInfo = toolInfo;
    }
    public String getToolSuspensionLen() {
        return this.toolSuspensionLen;
    }
    public void setToolSuspensionLen(String toolSuspensionLen) {
        this.toolSuspensionLen = toolSuspensionLen;
    }
    public String getCuttingMethod() {
        return this.cuttingMethod;
    }
    public void setCuttingMethod(String cuttingMethod) {
        this.cuttingMethod = cuttingMethod;
    }
    public String getCuttingDirection() {
        return this.cuttingDirection;
    }
    public void setCuttingDirection(String cuttingDirection) {
        this.cuttingDirection = cuttingDirection;
    }
    public String getWorkpieceType() {
        return this.workpieceType;
    }
    public void setWorkpieceType(String workpieceType) {
        this.workpieceType = workpieceType;
    }
    public String getProcessStep() {
        return this.processStep;
    }
    public void setProcessStep(String processStep) {
        this.processStep = processStep;
    }
    public String getProcessSurface() {
        return this.processSurface;
    }
    public void setProcessSurface(String processSurface) {
        this.processSurface = processSurface;
    }
    public String getWorkpieceRemark() {
        return this.workpieceRemark;
    }
    public void setWorkpieceRemark(String workpieceRemark) {
        this.workpieceRemark = workpieceRemark;
    }
    public String getModeX1() {
        return this.modeX1;
    }
    public void setModeX1(String modeX1) {
        this.modeX1 = modeX1;
    }
    public String getModeX2() {
        return this.modeX2;
    }
    public void setModeX2(String modeX2) {
        this.modeX2 = modeX2;
    }
    public String getModeX3() {
        return this.modeX3;
    }
    public void setModeX3(String modeX3) {
        this.modeX3 = modeX3;
    }
    public String getModeY1() {
        return this.modeY1;
    }
    public void setModeY1(String modeY1) {
        this.modeY1 = modeY1;
    }
    public String getModeY2() {
        return this.modeY2;
    }
    public void setModeY2(String modeY2) {
        this.modeY2 = modeY2;
    }
    public String getModeY3() {
        return this.modeY3;
    }
    public void setModeY3(String modeY3) {
        this.modeY3 = modeY3;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 905584689)
    public List<CuttingParaDetails> getMCuttingParaDetailsList() {
        if (mCuttingParaDetailsList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CuttingParaDetailsDao targetDao = daoSession.getCuttingParaDetailsDao();
            List<CuttingParaDetails> mCuttingParaDetailsListNew = targetDao
                    ._queryCuttingConditions_MCuttingParaDetailsList(id);
            synchronized (this) {
                if (mCuttingParaDetailsList == null) {
                    mCuttingParaDetailsList = mCuttingParaDetailsListNew;
                }
            }
        }
        return mCuttingParaDetailsList;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1125857766)
    public synchronized void resetMCuttingParaDetailsList() {
        mCuttingParaDetailsList = null;
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
    @Generated(hash = 1260566447)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCuttingConditionsDao() : null;
    }

}
