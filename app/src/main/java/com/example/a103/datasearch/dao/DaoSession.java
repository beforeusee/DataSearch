package com.example.a103.datasearch.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.a103.datasearch.data.CoefficientParameters;
import com.example.a103.datasearch.data.Machine;
import com.example.a103.datasearch.data.Material;
import com.example.a103.datasearch.data.MaterialCategories;
import com.example.a103.datasearch.data.MaterialCuttingLimits;
import com.example.a103.datasearch.data.Tool;
import com.example.a103.datasearch.data.CuttingParaDetails;
import com.example.a103.datasearch.data.CuttingConditions;

import com.example.a103.datasearch.dao.CoefficientParametersDao;
import com.example.a103.datasearch.dao.MachineDao;
import com.example.a103.datasearch.dao.MaterialDao;
import com.example.a103.datasearch.dao.MaterialCategoriesDao;
import com.example.a103.datasearch.dao.MaterialCuttingLimitsDao;
import com.example.a103.datasearch.dao.ToolDao;
import com.example.a103.datasearch.dao.CuttingParaDetailsDao;
import com.example.a103.datasearch.dao.CuttingConditionsDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig coefficientParametersDaoConfig;
    private final DaoConfig machineDaoConfig;
    private final DaoConfig materialDaoConfig;
    private final DaoConfig materialCategoriesDaoConfig;
    private final DaoConfig materialCuttingLimitsDaoConfig;
    private final DaoConfig toolDaoConfig;
    private final DaoConfig cuttingParaDetailsDaoConfig;
    private final DaoConfig cuttingConditionsDaoConfig;

    private final CoefficientParametersDao coefficientParametersDao;
    private final MachineDao machineDao;
    private final MaterialDao materialDao;
    private final MaterialCategoriesDao materialCategoriesDao;
    private final MaterialCuttingLimitsDao materialCuttingLimitsDao;
    private final ToolDao toolDao;
    private final CuttingParaDetailsDao cuttingParaDetailsDao;
    private final CuttingConditionsDao cuttingConditionsDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        coefficientParametersDaoConfig = daoConfigMap.get(CoefficientParametersDao.class).clone();
        coefficientParametersDaoConfig.initIdentityScope(type);

        machineDaoConfig = daoConfigMap.get(MachineDao.class).clone();
        machineDaoConfig.initIdentityScope(type);

        materialDaoConfig = daoConfigMap.get(MaterialDao.class).clone();
        materialDaoConfig.initIdentityScope(type);

        materialCategoriesDaoConfig = daoConfigMap.get(MaterialCategoriesDao.class).clone();
        materialCategoriesDaoConfig.initIdentityScope(type);

        materialCuttingLimitsDaoConfig = daoConfigMap.get(MaterialCuttingLimitsDao.class).clone();
        materialCuttingLimitsDaoConfig.initIdentityScope(type);

        toolDaoConfig = daoConfigMap.get(ToolDao.class).clone();
        toolDaoConfig.initIdentityScope(type);

        cuttingParaDetailsDaoConfig = daoConfigMap.get(CuttingParaDetailsDao.class).clone();
        cuttingParaDetailsDaoConfig.initIdentityScope(type);

        cuttingConditionsDaoConfig = daoConfigMap.get(CuttingConditionsDao.class).clone();
        cuttingConditionsDaoConfig.initIdentityScope(type);

        coefficientParametersDao = new CoefficientParametersDao(coefficientParametersDaoConfig, this);
        machineDao = new MachineDao(machineDaoConfig, this);
        materialDao = new MaterialDao(materialDaoConfig, this);
        materialCategoriesDao = new MaterialCategoriesDao(materialCategoriesDaoConfig, this);
        materialCuttingLimitsDao = new MaterialCuttingLimitsDao(materialCuttingLimitsDaoConfig, this);
        toolDao = new ToolDao(toolDaoConfig, this);
        cuttingParaDetailsDao = new CuttingParaDetailsDao(cuttingParaDetailsDaoConfig, this);
        cuttingConditionsDao = new CuttingConditionsDao(cuttingConditionsDaoConfig, this);

        registerDao(CoefficientParameters.class, coefficientParametersDao);
        registerDao(Machine.class, machineDao);
        registerDao(Material.class, materialDao);
        registerDao(MaterialCategories.class, materialCategoriesDao);
        registerDao(MaterialCuttingLimits.class, materialCuttingLimitsDao);
        registerDao(Tool.class, toolDao);
        registerDao(CuttingParaDetails.class, cuttingParaDetailsDao);
        registerDao(CuttingConditions.class, cuttingConditionsDao);
    }
    
    public void clear() {
        coefficientParametersDaoConfig.clearIdentityScope();
        machineDaoConfig.clearIdentityScope();
        materialDaoConfig.clearIdentityScope();
        materialCategoriesDaoConfig.clearIdentityScope();
        materialCuttingLimitsDaoConfig.clearIdentityScope();
        toolDaoConfig.clearIdentityScope();
        cuttingParaDetailsDaoConfig.clearIdentityScope();
        cuttingConditionsDaoConfig.clearIdentityScope();
    }

    public CoefficientParametersDao getCoefficientParametersDao() {
        return coefficientParametersDao;
    }

    public MachineDao getMachineDao() {
        return machineDao;
    }

    public MaterialDao getMaterialDao() {
        return materialDao;
    }

    public MaterialCategoriesDao getMaterialCategoriesDao() {
        return materialCategoriesDao;
    }

    public MaterialCuttingLimitsDao getMaterialCuttingLimitsDao() {
        return materialCuttingLimitsDao;
    }

    public ToolDao getToolDao() {
        return toolDao;
    }

    public CuttingParaDetailsDao getCuttingParaDetailsDao() {
        return cuttingParaDetailsDao;
    }

    public CuttingConditionsDao getCuttingConditionsDao() {
        return cuttingConditionsDao;
    }

}
